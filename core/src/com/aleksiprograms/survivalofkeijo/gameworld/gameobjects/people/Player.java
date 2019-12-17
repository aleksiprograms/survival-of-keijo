package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Ice;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Shop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SolidObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Weapon;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyState;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.aleksiprograms.survivalofkeijo.managers.ControlManager;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.utils.Array;

public class Player extends Person {

    private Vector3 playerPosition;
    private Quaternion playerQuaternion;
    private Vector3 playerScale;
    private Matrix4 playerTransform;
    private AnimationController animationController;
    private BodyState bodyState;
    private boolean playerBodyToRight;
    private Vector3 velocity;
    public Array<Weapon> weapons;
    public Weapon currentWeapon;
    private ControlManager controlManager;
    private float lockUpMovementTimer;
    public PhysicalObject target;
    private int i;
    private int j;
    private float xVelocity;

    private boolean movingRight;
    private boolean lookingRight;
    private boolean shootButtonPressed;
    private boolean upButtonPressed;
    private boolean downButtonPressed;
    private boolean reloadButtonPressed;
    private boolean inAir;
    private boolean doubleJumpDone;
    private float highestYPositionWhileOnAir;
    private boolean onIce;
    private Vector3 forceOnIce;
    private boolean headHit;
    private boolean canMoveRight;
    private boolean canMoveLeft;
    private boolean lockUpMovement;
    private Vector3 movementRayFrom;
    private Vector3 movementRayTo;
    private ClosestRayResultCallback movementCallback;
    private AnimationController.AnimationListener animationListener;

    BigAreaObject bigArea;
    SmallAreaObject smallArea;

    public float money;

    public Player(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_PERSON_PLAYER, Model.class)),
                new btBoxShape(new Vector3(0.2f, 0.9f, 0.2f)),
                new BodyDef.BodyDefBuilder()
                        .mass(80)
                        .linearDamping(0.1f)
                        .friction(0.3f)
                        .categoryBits(Constants.CATEGORY_PLAYER)
                        .maskBits(Constants.MASK_PLAYER)
                        .useMotionState(false)
                        .build());

        animationController = new AnimationController(this);
        playerPosition = new Vector3();
        playerQuaternion = new Quaternion();
        playerScale = new Vector3(1, 1, 1);
        playerTransform = new Matrix4();
        playerPosition = new Vector3();
        playerQuaternion = new Quaternion();
        playerScale = new Vector3(1, 1, 1);
        playerTransform = new Matrix4();
        velocity = new Vector3();
        weapons = new Array<Weapon>();
        controlManager = new ControlManager();
        rigidBody.userData = this;

        movementRayFrom = new Vector3();
        movementRayTo = new Vector3();
        movementCallback = new ClosestRayResultCallback(movementRayFrom, movementRayTo);
        movementCallback.setCollisionFilterGroup(Constants.CATEGORY_SENSOR_MOVEMENT);
        movementCallback.setCollisionFilterMask(Constants.MASK_SENSOR_MOVEMENT);

        animationListener = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {

            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };
    }

    @Override
    public void init(float x, float y, float z, float angle) {
        super.init(x, y, z, angle);
        dead = false;
        health = 1000;
        maxHealth = 1000;
        bodyState = BodyState.STAND;
        animationController.setAnimation("Armature|stand", -1);
        playerBodyToRight = true;
        movingRight = true;
        lookingRight = true;
        shootButtonPressed = false;
        upButtonPressed = false;
        downButtonPressed = false;
        reloadButtonPressed = false;
        inAir = false;
        doubleJumpDone = false;
        highestYPositionWhileOnAir = -10000f;
        onIce = false;
        forceOnIce = new Vector3();
        headHit = false;
        canMoveRight = true;
        canMoveLeft = true;
        lockUpMovement = true;
        bigArea = game.gameWorld.bigAreaManagers.get(0).bigAreaNull;
        smallArea = game.gameWorld.bigAreaManagers.get(0).smallAreaNull;
        money = 100000.0f;
        weapons.clear();
    }

    @Override
    public void onHit(int damage) {
        super.onHit(damage);
        game.gameScreen.inGameHud.updateHud();
    }

    public void addWeapon(Weapon weapon, WeaponData weaponData, Person owner, Person target, boolean playerWeapon) {
        weapons.add(weapon);
        weapons.peek().init(weaponData, owner, target, playerWeapon);
        if (weapons.size == 1) {
            currentWeapon = weapons.peek();
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        setPlayerTransform();
        animationController.update(deltaTime);
        controlManager.update(deltaTime);
        if (dead) {
            deadThings();
        } else {
            isInFrontOfBuilding();
            checkAreaWherePlayerIs();
            canMoveLeftOrRight();
            updateMovement(deltaTime);
            updateWeapon();
            isInAirOrOnIce();
            checkFallDamage();
            checkHeadDamage();
        }
    }

    private void isInFrontOfBuilding() {
        for (i = 0; i < game.gameWorld.enterableBuildings.size; i++) {
            if (rigidBody.getCenterOfMassPosition().x >= game.gameWorld.enterableBuildings.get(i).x - game.gameWorld.enterableBuildings.get(i).width / 2 &&
                    rigidBody.getCenterOfMassPosition().x <= game.gameWorld.enterableBuildings.get(i).x + game.gameWorld.enterableBuildings.get(i).width / 2 &&
                    rigidBody.getCenterOfMassPosition().y >= game.gameWorld.enterableBuildings.get(i).y - game.gameWorld.enterableBuildings.get(i).height / 2 &&
                    rigidBody.getCenterOfMassPosition().y <= game.gameWorld.enterableBuildings.get(i).y + game.gameWorld.enterableBuildings.get(i).height / 2) {
                if (game.gameWorld.enterableBuildings.get(i) instanceof Shop) {
                    game.gameScreen.inGameHud.setButtonEnterShopVisibility(true);
                }
            } else {
                if (game.gameWorld.enterableBuildings.get(i) instanceof Shop) {
                    game.gameScreen.inGameHud.setButtonEnterShopVisibility(false);
                }
            }
        }
    }

    private void checkAreaWherePlayerIs() {
        if (bigArea.nullObject) {
            bigArea = game.gameWorld.getNewBigArea(rigidBody.getCenterOfMassPosition().x, rigidBody.getCenterOfMassPosition().y);
        } else {
            if (rigidBody.getCenterOfMassPosition().x <= bigArea.x - bigArea.width / 2 ||
                    rigidBody.getCenterOfMassPosition().x >= bigArea.x + bigArea.width / 2 ||
                    rigidBody.getCenterOfMassPosition().y <= bigArea.y - bigArea.height / 2 ||
                    rigidBody.getCenterOfMassPosition().y >= bigArea.y + bigArea.height / 2) {
                bigArea = game.gameWorld.getNewBigArea(rigidBody.getCenterOfMassPosition().x, rigidBody.getCenterOfMassPosition().y);
            }
        }
        if (smallArea.nullObject) {
            if (bigArea.nullObject) {
                smallArea = game.gameWorld.getNewSmallArea(rigidBody.getCenterOfMassPosition().x, rigidBody.getCenterOfMassPosition().y, 1);
            } else {
                smallArea = game.gameWorld.getNewSmallArea(rigidBody.getCenterOfMassPosition().x, rigidBody.getCenterOfMassPosition().y, bigArea.area);
            }
        } else {
            if (rigidBody.getCenterOfMassPosition().x <= smallArea.x - smallArea.width / 2 ||
                    rigidBody.getCenterOfMassPosition().x >= smallArea.x + smallArea.width / 2 ||
                    rigidBody.getCenterOfMassPosition().y <= smallArea.y - smallArea.height / 2 ||
                    rigidBody.getCenterOfMassPosition().y >= smallArea.y + smallArea.height / 2) {
                if (bigArea.nullObject) {
                    smallArea = game.gameWorld.getNewSmallArea(rigidBody.getCenterOfMassPosition().x, rigidBody.getCenterOfMassPosition().y, 1);
                } else {
                    smallArea = game.gameWorld.getNewSmallArea(rigidBody.getCenterOfMassPosition().x, rigidBody.getCenterOfMassPosition().y, bigArea.area);
                }
            }
        }
    }

    private void canMoveLeftOrRight() {
        canMoveRight = true;
        canMoveLeft = true;
        for (i = -1; i < 2; i++) {
            if (i == 0) {
                continue;
            }
            for (j = -1; j < 2; j++) {
                movementRayFrom.set(
                        rigidBody.getCenterOfMassPosition().x,
                        rigidBody.getCenterOfMassPosition().y + j * 0.85f,
                        rigidBody.getCenterOfMassPosition().z);
                movementRayTo.set(
                        rigidBody.getCenterOfMassPosition().x + i * 0.4f,
                        rigidBody.getCenterOfMassPosition().y + j * 0.85f,
                        rigidBody.getCenterOfMassPosition().z);
                movementCallback.setCollisionObject(null);
                movementCallback.setClosestHitFraction(1);
                movementCallback.setRayFromWorld(movementRayFrom);
                movementCallback.setRayToWorld(movementRayTo);
                game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                if (movementCallback.hasHit() && i == -1) {
                    canMoveLeft = false;
                    break;
                }
                if (movementCallback.hasHit() && i == 1) {
                    canMoveRight = false;
                    break;
                }
            }
            if (!canMoveLeft && i == -1) {
                break;
            }
            if (!canMoveRight && i == 1) {
                break;
            }
        }
    }

    private void setPlayerTransform() {
        playerPosition.set(rigidBody.getCenterOfMassPosition());
        playerQuaternion.set(rigidBody.getOrientation());
        playerTransform.set(playerPosition, playerQuaternion, playerScale);
        transform.set(playerPosition, playerQuaternion, playerScale);
    }

    private void updateMovement(float deltaTime) {
        if (!lockUpMovement) {
            lockUpMovementTimer += deltaTime;
        }
        if (!inAir) {
            if (lockUpMovementTimer > 0.5f) {
                lockUpMovement = true;
            }
        }

        if (lockUpMovement) {
            if (rigidBody.getLinearVelocity().y > 0) {
                velocity.set(rigidBody.getLinearVelocity().x, 0, 0);
                rigidBody.setLinearVelocity(velocity);
            }
        }

        if (target != null) {
            if (target.rigidBody.getCenterOfMassPosition().x >= rigidBody.getCenterOfMassPosition().x) {
                lookingRight = true;
                currentWeapon.lookingRight = true;
            } else {
                lookingRight = false;
                currentWeapon.lookingRight = false;
            }
        } else {
            if (movingRight) {
                lookingRight = true;
                currentWeapon.lookingRight = true;
            } else {
                lookingRight = false;
                currentWeapon.lookingRight = false;
            }
        }

        xVelocity = 4f - (1f * ((game.gameWorld.weaponManagerPlayer.getWeaponData(currentWeapon.weaponData.ID).weight - game.gameWorld.weaponManagerPlayer.minWeight) / (game.gameWorld.weaponManagerPlayer.maxWeight - game.gameWorld.weaponManagerPlayer.minWeight)));

        if (controlManager.buttonRightPressed && canMoveRight) {
            movingRight = true;
            if (!inAir) {
                if (lookingRight) {
                    if (!bodyState.equals(BodyState.MOVE_RIGHT)) {
                        bodyState = BodyState.MOVE_RIGHT;
                        animationController.setAnimation("Armature|walk", -1,
                                (xVelocity / 2f), animationListener);
                    }
                } else {
                    if (!bodyState.equals(BodyState.MOVE_RIGHT_REVERSE)) {
                        bodyState = BodyState.MOVE_RIGHT_REVERSE;
                        animationController.setAnimation("Armature|walk", -1,
                                -(xVelocity / 2f), animationListener);
                    }
                }
            }
            if (onIce) {
                if (rigidBody.getLinearVelocity().x <= xVelocity * 2f) {
                    forceOnIce.set(150, 0, 0);
                    rigidBody.applyCentralForce(forceOnIce);
                }
            } else {
                velocity.set(xVelocity, rigidBody.getLinearVelocity().y, 0);
                rigidBody.setLinearVelocity(velocity);
            }
        } else if (controlManager.buttonLeftPressed && canMoveLeft) {
            movingRight = false;
            if (!inAir) {
                if (!lookingRight) {
                    if (!bodyState.equals(BodyState.MOVE_LEFT)) {
                        bodyState = BodyState.MOVE_LEFT;
                        animationController.setAnimation("Armature|walk", -1,
                                (xVelocity / 2f), animationListener);
                    }
                } else {
                    if (!bodyState.equals(BodyState.MOVE_LEFT_REVERSE)) {
                        bodyState = BodyState.MOVE_LEFT_REVERSE;
                        animationController.setAnimation("Armature|walk", -1,
                                -(xVelocity / 2f), animationListener);
                    }
                }
            }
            if (onIce) {
                if (rigidBody.getLinearVelocity().x >= -xVelocity * 2f) {
                    forceOnIce.set(-150, 0, 0);
                    rigidBody.applyCentralForce(forceOnIce);
                }
            } else {
                velocity.set(-xVelocity, rigidBody.getLinearVelocity().y, 0);
                rigidBody.setLinearVelocity(velocity);
            }
        } else {
            if (!inAir) {
                if (!bodyState.equals(BodyState.STAND)) {
                    bodyState = BodyState.STAND;
                    animationController.setAnimation("Armature|stand", -1);
                }
            }
            if (!onIce) {
                velocity.set(0, rigidBody.getLinearVelocity().y, 0);
                rigidBody.setLinearVelocity(velocity);
            }
        }

        if (inAir) {
            if (!bodyState.equals(BodyState.AIR)) {
                bodyState = BodyState.AIR;
                animationController.setAnimation("Armature|air-stand", -1);
            }
        }

        if (lookingRight) {
            if (!playerBodyToRight) {
                playerBodyToRight = true;
                playerPosition.set(rigidBody.getCenterOfMassPosition());
                playerQuaternion.set(Vector3.Y, 0);
                playerTransform.set(playerPosition, playerQuaternion, playerScale);
                rigidBody.setCenterOfMassTransform(playerTransform);
            }
        } else {
            if (playerBodyToRight) {
                playerBodyToRight = false;
                playerPosition.set(rigidBody.getCenterOfMassPosition());
                playerQuaternion.set(Vector3.Y, 180);
                playerTransform.set(playerPosition, playerQuaternion, playerScale);
                rigidBody.setCenterOfMassTransform(playerTransform);
            }
        }

        if (!controlManager.buttonUpPressed) {
            upButtonPressed = false;
        }
        if (controlManager.buttonUpPressed && !upButtonPressed) {
            upButtonPressed = true;
            if (!inAir) {
                lockUpMovement = false;
                lockUpMovementTimer = 0;
                velocity.set(rigidBody.getLinearVelocity().x, 8.8f, 0);
                rigidBody.setLinearVelocity(velocity);
            } else {
                if (!doubleJumpDone) {
                    doubleJumpDone = true;
                    lockUpMovement = false;
                    lockUpMovementTimer = 0;
                    velocity.set(rigidBody.getLinearVelocity().x, 8.8f, 0);
                    rigidBody.setLinearVelocity(velocity);
                }
            }
        }
    }

    private void updateWeapon() {
        currentWeapon.target = ((Person) target);
        if (!controlManager.buttonShootPressed) {
            shootButtonPressed = false;
        }
        if ((controlManager.buttonShootPressed && !shootButtonPressed) || (controlManager.buttonShootPressed && currentWeapon.automatic)) {
            shootButtonPressed = true;
            currentWeapon.usePlayer();
        }

        if (!controlManager.buttonReloadPressed) {
            reloadButtonPressed = false;
        }
        if (controlManager.buttonReloadPressed && !reloadButtonPressed) {
            reloadButtonPressed = true;
            currentWeapon.reload();
        }
    }

    private void isInAirOrOnIce() {
        inAir = true;
        onIce = false;
        if (rigidBody.getLinearVelocity().y <= 0.1f) {
            for (i = -1; i < 2; i++) {
                if (i == 0) {
                    continue;
                }
                movementRayFrom.set(
                        rigidBody.getCenterOfMassPosition().x + i * 0.15f,
                        rigidBody.getCenterOfMassPosition().y,
                        rigidBody.getCenterOfMassPosition().z);
                movementRayTo.set(
                        rigidBody.getCenterOfMassPosition().x + i * 0.15f,
                        rigidBody.getCenterOfMassPosition().y - 0.9f - 0.05f,
                        rigidBody.getCenterOfMassPosition().z);
                movementCallback.setCollisionObject(null);
                movementCallback.setClosestHitFraction(1);
                movementCallback.setRayFromWorld(movementRayFrom);
                movementCallback.setRayToWorld(movementRayTo);
                game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                if (movementCallback.hasHit()) {
                    inAir = false;
                    doubleJumpDone = false;
                    if (movementCallback.getCollisionObject().userData instanceof Ice) {
                        onIce = true;
                    }
                    break;
                }
            }
        }
    }

    private void deadThings() {
        velocity.set(0, rigidBody.getLinearVelocity().y, 0);
        rigidBody.setLinearVelocity(velocity);
        if (!bodyState.equals(BodyState.DEAD)) {
            bodyState = BodyState.DEAD;
            currentWeapon.ownerDead = true;
            animationController.setAnimation("Armature|die", 1, 2, animationListener);
            game.gameScreen.setGameState(GameState.GAME_OVER);
            game.gameScreen.stage.clear();
            game.gameScreen.gameOverHud.updateHud();
            game.gameScreen.stage.addActor(game.gameScreen.gameOverHud);
        }
    }

    private void checkFallDamage() {
        if (inAir) {
            if (rigidBody.getCenterOfMassPosition().y > highestYPositionWhileOnAir) {
                highestYPositionWhileOnAir = rigidBody.getCenterOfMassPosition().y;
            }
        }
        if (!inAir) {
            if (highestYPositionWhileOnAir - rigidBody.getCenterOfMassPosition().y >= 5) {
                onHit(-(int) ((highestYPositionWhileOnAir - rigidBody.getCenterOfMassPosition().y - 5) * 50));
            }
            highestYPositionWhileOnAir = -10000f;
        }
    }

    private void checkHeadDamage() {
        movementRayFrom.set(
                rigidBody.getCenterOfMassPosition().x,
                rigidBody.getCenterOfMassPosition().y,
                rigidBody.getCenterOfMassPosition().z);
        movementRayTo.set(
                rigidBody.getCenterOfMassPosition().x,
                rigidBody.getCenterOfMassPosition().y + 0.9f + 0.05f,
                rigidBody.getCenterOfMassPosition().z);
        movementCallback.setCollisionObject(null);
        movementCallback.setClosestHitFraction(1);
        movementCallback.setRayFromWorld(movementRayFrom);
        movementCallback.setRayToWorld(movementRayTo);
        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
        if (movementCallback.hasHit() && !headHit) {
            if (movementCallback.getCollisionObject().userData instanceof SolidObject) {
                headHit = true;
                onHit(-100);
            }
        }
        if (!inAir) {
            headHit = false;
        }
    }
}