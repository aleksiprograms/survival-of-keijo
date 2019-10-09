package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.Shop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Hands;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Weapon;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyState;
import com.aleksiprograms.survivalofkeijo.toolbox.UpState;
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

    public Vector3 playerPosition;
    public Quaternion playerQuaternion;
    public Vector3 playerScale;
    public Matrix4 playerTransform;
    public AnimationController animationController;
    public AnimationController animationControllerHands;
    public BodyState bodyState;
    public btBoxShape shapeStand;
    public btBoxShape shapeCrouch;
    public btBoxShape shapeProne;
    public boolean playerBodyToRight;
    public Vector3 velocity;
    public Array<Weapon> weapons;
    public Weapon currentWeapon;
    public Weapon weaponHands;
    public ControlManager controlManager;
    private float lockUpMovementTimer;
    public PhysicalObject target;
    public Vector3 targetDeadPos;
    public boolean autoShootingTarget;
    public int i;
    public int j;
    public float moveX;
    public float xVel;

    private boolean movingRight;
    private boolean lookingRight;
    private boolean shootButtonPressed;
    private boolean upButtonPressed;
    private boolean downButtonPressed;
    private boolean reloadButtonPressed;
    private boolean backpackButtonPressed;
    private boolean enoughSpace;
    public boolean inAir;
    public boolean canMoveRight;
    public boolean canMoveLeft;
    public boolean lockUpMovement;
    private Vector3 toProneHitPointLeft;
    private Vector3 toProneHitPointRight;
    private boolean enoughSpaceLeft;
    private boolean enoughSpaceRight;
    private Vector3 movementRayFrom;
    private Vector3 movementRayTo;
    private ClosestRayResultCallback movementCallback;
    private AnimationController.AnimationListener animationListener;

    public BigAreaObject bigArea;
    public SmallAreaObject smallArea;

    public float money;

    public Player(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_PERSON_PLAYER, Model.class)),
                new btBoxShape(new Vector3(0.2f, 0.9f, 0.2f)),
                new BodyDef.BodyDefBuilder()
                        .mass(80)
                        .linearDamping(0.1f)
                        .categoryBits(Constants.CATEGORY_PLAYER)
                        .maskBits(Constants.MASK_PLAYER)
                        .useMotionState(false)
                        .build());

        animationController = new AnimationController(this);
        playerPosition = new Vector3();
        playerQuaternion = new Quaternion();
        playerScale = new Vector3(1, 1, 1);
        playerTransform = new Matrix4();
        shapeStand = new btBoxShape(new Vector3(0.2f, 0.9f, 0.2f));
        shapeCrouch = new btBoxShape(new Vector3(0.2f, 0.65f, 0.2f));
        shapeProne = new btBoxShape(new Vector3(0.9f, 0.15f, 0.2f));
        playerPosition = new Vector3();
        playerQuaternion = new Quaternion();
        playerScale = new Vector3(1, 1, 1);
        playerTransform = new Matrix4();
        velocity = new Vector3();
        weapons = new Array<Weapon>();
        /*
        weaponPistol = game.gamePools.pistolPlayerPool.obtain();
        weaponPistol.updateScreen(game.weaponManagerPlayer.getWeaponData(Constants.PISTOL_ID), this, null, true, false);
        weaponHands = game.gamePools.handsPool.obtain();
        weaponHands.updateScreen(game.weaponManagerPlayer.getWeaponData(Constants.KNIFE_ID), this, null, true, false);
        currentWeapon = weaponPistol;
        */
        animationControllerHands = new AnimationController(weaponHands);
        controlManager = new ControlManager();
        rigidBody.userData = this;
        targetDeadPos = new Vector3();

        toProneHitPointLeft = new Vector3();
        toProneHitPointRight = new Vector3();

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
        backpackButtonPressed = false;
        enoughSpace = true;
        inAir = false;
        canMoveRight = true;
        canMoveLeft = true;
        lockUpMovement = true;
        bigArea = game.gameWorld.bigAreaManagers.get(0).bigAreaNull;
        smallArea = game.gameWorld.bigAreaManagers.get(0).smallAreaNull;
        money = 1000000.0f;
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
        animationController.update(deltaTime);
        if (dead) {
            velocity.set(0, rigidBody.getLinearVelocity().y, 0);
            rigidBody.setLinearVelocity(velocity);
            if (!bodyState.equals(BodyState.DEAD)) {
                bodyState = BodyState.DEAD;
                currentWeapon.ownerDead = true;
                animationController.setAnimation("Armature|die", 1, 2, animationListener);
                if (currentWeapon.animateOnMoving) {
                    currentWeapon.animationController.setAnimation("Armature|stand", -1);
                }
                for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                    game.gameScreen.stage.getActors().get(i).remove();
                }
                game.gameScreen.gameOverHud.updateHud();
                game.gameScreen.stage.addActor(game.gameScreen.gameOverHud);
            }
        } else {
            currentWeapon.target = ((Person) target);
            playerPosition.set(rigidBody.getCenterOfMassPosition());
            playerQuaternion.set(rigidBody.getOrientation());
            playerTransform.set(playerPosition, playerQuaternion, playerScale);
            transform.set(playerPosition, playerQuaternion, playerScale);
            lockUpMovementTimer += deltaTime;
            if (currentWeapon instanceof Hands) {
                animationControllerHands.update(deltaTime);
            }
            controlManager.update(deltaTime);

            for (i = 0; i < game.gameWorld.enterableBuildings.size; i++) {
                if (rigidBody.getCenterOfMassPosition().x >= game.gameWorld.enterableBuildings.get(i).x - game.gameWorld.enterableBuildings.get(i).width / 2 &&
                        rigidBody.getCenterOfMassPosition().x <= game.gameWorld.enterableBuildings.get(i).x + game.gameWorld.enterableBuildings.get(i).width / 2 &&
                        rigidBody.getCenterOfMassPosition().y >= game.gameWorld.enterableBuildings.get(i).y - game.gameWorld.enterableBuildings.get(i).height / 2 &&
                        rigidBody.getCenterOfMassPosition().y <= game.gameWorld.enterableBuildings.get(i).y + game.gameWorld.enterableBuildings.get(i).height / 2) {
                    if (game.gameWorld.enterableBuildings.get(i) instanceof Shop) {
                        game.gameScreen.inGameHud.setBtEnterGunStoreVisibility(true);
                    }
                } else {
                    if (game.gameWorld.enterableBuildings.get(i) instanceof Shop) {
                        game.gameScreen.inGameHud.setBtEnterGunStoreVisibility(false);
                    }
                }
            }

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

            canMoveRight = true;
            canMoveLeft = true;
            if (upState.equals(UpState.STAND)) {
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
            } else if (upState.equals(UpState.CROUCH)) {
                for (i = -1; i < 2; i++) {
                    if (i == 0) {
                        continue;
                    }
                    for (j = -1; j < 2; j++) {
                        movementRayFrom.set(
                                rigidBody.getCenterOfMassPosition().x,
                                rigidBody.getCenterOfMassPosition().y + j * 0.6f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementRayTo.set(
                                rigidBody.getCenterOfMassPosition().x + i * 0.4f,
                                rigidBody.getCenterOfMassPosition().y + j * 0.6f,
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
            } else if (upState.equals(UpState.PRONE)) {
                for (i = -1; i < 2; i++) {
                    if (i == 0) {
                        continue;
                    }
                    movementRayFrom.set(
                            rigidBody.getCenterOfMassPosition().x,
                            rigidBody.getCenterOfMassPosition().y,
                            rigidBody.getCenterOfMassPosition().z);
                    movementRayTo.set(
                            rigidBody.getCenterOfMassPosition().x + i * 1.1f,
                            rigidBody.getCenterOfMassPosition().y,
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

            xVel = 4f - (2f * ((game.gameWorld.weaponManagerPlayer.getWeaponData(currentWeapon.weaponData.ID).weight - game.gameWorld.weaponManagerPlayer.minWeight) / (game.gameWorld.weaponManagerPlayer.maxWeight - game.gameWorld.weaponManagerPlayer.minWeight)));

            if (controlManager.btRightPressed && canMoveRight) {
                movingRight = true;
                if (!inAir) {
                    if (upState.equals(UpState.STAND)) {
                        if (lookingRight) {
                            if (!bodyState.equals(BodyState.STAND_MOVE_RIGHT)) {
                                bodyState = BodyState.STAND_MOVE_RIGHT;
                                animationController.setAnimation("Armature|walk", -1,
                                        (xVel / 2f), animationListener);
                            }
                        } else {
                            if (!bodyState.equals(BodyState.STAND_MOVE_RIGHT_REVERSE)) {
                                bodyState = BodyState.STAND_MOVE_RIGHT_REVERSE;
                                animationController.setAnimation("Armature|walk", -1,
                                        -(xVel / 2f), animationListener);
                            }
                        }
                    } else if (upState.equals(UpState.CROUCH)) {
                        if (lookingRight) {
                            if (!bodyState.equals(BodyState.CROUCH_MOVE_RIGHT)) {
                                bodyState = BodyState.CROUCH_MOVE_RIGHT;
                                animationController.setAnimation("Armature|stealth", -1,
                                        ((xVel * 0.7f) / 2f), animationListener);
                            }
                        } else {
                            if (!bodyState.equals(BodyState.CROUCH_MOVE_RIGHT_REVERSE)) {
                                bodyState = BodyState.CROUCH_MOVE_RIGHT_REVERSE;
                                animationController.setAnimation("Armature|stealth", -1,
                                        -((xVel * 0.7f) / 2f), animationListener);
                            }
                        }
                    } else if (upState.equals(UpState.PRONE)) {
                        if (lookingRight) {
                            if (!bodyState.equals(BodyState.PRONE_MOVE_RIGHT)) {
                                bodyState = BodyState.PRONE_MOVE_RIGHT;
                                animationController.setAnimation("Armature|snake", -1,
                                        ((xVel * 0.4f) / 2f), animationListener);
                                currentWeapon = weaponHands;
                                animationControllerHands.setAnimation("Armature|snake", -1,
                                        ((xVel * 0.4f) / 2f), animationListener);
                            }
                        } else {
                            if (!bodyState.equals(BodyState.PRONE_MOVE_RIGHT_REVERSE)) {
                                bodyState = BodyState.PRONE_MOVE_RIGHT_REVERSE;
                                animationController.setAnimation("Armature|snake", -1,
                                        -((xVel * 0.4f) / 2f), animationListener);
                                currentWeapon = weaponHands;
                                animationControllerHands.setAnimation("Armature|snake", -1,
                                        -((xVel * 0.4f) / 2f), animationListener);
                            }
                        }
                    }
                }
                if (upState.equals(UpState.STAND) || bodyState.equals(BodyState.AIR_STAND)) {
                    velocity.set(xVel, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                } else if (upState.equals(UpState.CROUCH)) {
                    velocity.set(xVel * 0.7f, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                } else if (upState.equals(UpState.PRONE)) {
                    velocity.set(xVel * 0.4f, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                }
            } else if (controlManager.btLeftPressed && canMoveLeft) {
                movingRight = false;
                if (!inAir) {
                    if (upState.equals(UpState.STAND)) {
                        if (!lookingRight) {
                            if (!bodyState.equals(BodyState.STAND_MOVE_LEFT)) {
                                bodyState = BodyState.STAND_MOVE_LEFT;
                                animationController.setAnimation("Armature|walk", -1,
                                        (xVel / 2f), animationListener);
                            }
                        } else {
                            if (!bodyState.equals(BodyState.STAND_MOVE_LEFT_REVERSE)) {
                                bodyState = BodyState.STAND_MOVE_LEFT_REVERSE;
                                animationController.setAnimation("Armature|walk", -1,
                                        -(xVel / 2f), animationListener);
                            }
                        }
                    } else if (upState.equals(UpState.CROUCH)) {
                        if (!lookingRight) {
                            if (!bodyState.equals(BodyState.CROUCH_MOVE_LEFT)) {
                                bodyState = BodyState.CROUCH_MOVE_LEFT;
                                animationController.setAnimation("Armature|stealth", -1,
                                        ((xVel * 0.7f) / 2f), animationListener);
                            }
                        } else {
                            if (!bodyState.equals(BodyState.CROUCH_MOVE_LEFT_REVERSE)) {
                                bodyState = BodyState.CROUCH_MOVE_LEFT_REVERSE;
                                animationController.setAnimation("Armature|stealth", -1,
                                        -((xVel * 0.7f) / 2f), animationListener);
                            }
                        }
                    } else if (upState.equals(UpState.PRONE)) {
                        if (!lookingRight) {
                            if (!bodyState.equals(BodyState.PRONE_MOVE_LEFT)) {
                                bodyState = BodyState.PRONE_MOVE_LEFT;
                                animationController.setAnimation("Armature|snake", -1,
                                        ((xVel * 0.4f) / 2f), animationListener);
                                currentWeapon = weaponHands;
                                animationControllerHands.setAnimation("Armature|snake", -1,
                                        ((xVel * 0.4f) / 2f), animationListener);
                            }
                        } else {
                            if (!bodyState.equals(BodyState.PRONE_MOVE_LEFT_REVERSE)) {
                                bodyState = BodyState.PRONE_MOVE_LEFT_REVERSE;
                                animationController.setAnimation("Armature|snake", -1,
                                        -((xVel * 0.4f) / 2f), animationListener);
                                currentWeapon = weaponHands;
                                animationControllerHands.setAnimation("Armature|snake", -1,
                                        -((xVel * 0.4f) / 2f), animationListener);
                            }
                        }
                    }
                }
                if (upState.equals(UpState.STAND) || bodyState.equals(BodyState.AIR_STAND)) {
                    velocity.set(-xVel, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                } else if (upState.equals(UpState.CROUCH)) {
                    velocity.set(-xVel * 0.7f, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                } else if (upState.equals(UpState.PRONE)) {
                    velocity.set(-xVel * 0.4f, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                }
            } else {
                if (!inAir) {
                    if (upState.equals(UpState.STAND)) {
                        if (!bodyState.equals(BodyState.STAND)) {
                            bodyState = BodyState.STAND;
                            animationController.setAnimation("Armature|stand", -1);
                        }
                    } else if (upState.equals(UpState.CROUCH)) {
                        if (!bodyState.equals(BodyState.CROUCH)) {
                            bodyState = BodyState.CROUCH;
                            animationController.setAnimation("Armature|crouch", -1);
                        }
                    } else if (upState.equals(UpState.PRONE)) {
                        if (!bodyState.equals(BodyState.PRONE)) {
                            bodyState = BodyState.PRONE;
                            animationController.setAnimation("Armature|prone", -1);
                            //currentWeapon = weaponPistol;
                        }
                    }
                }
                velocity.set(0, rigidBody.getLinearVelocity().y, 0);
                rigidBody.setLinearVelocity(velocity);
            }

            if (inAir) {
                if (upState.equals(UpState.STAND)) {
                    if (!bodyState.equals(BodyState.AIR_STAND)) {
                        bodyState = BodyState.AIR_STAND;
                        animationController.setAnimation("Armature|air-stand", -1);
                    }
                } else if (upState.equals(UpState.CROUCH)) {
                    if (!bodyState.equals(BodyState.AIR_CROUCH)) {
                        bodyState = BodyState.AIR_CROUCH;
                        animationController.setAnimation("Armature|air-crouch", -1);
                    }
                } else if (upState.equals(UpState.PRONE)) {
                    if (!bodyState.equals(BodyState.AIR_PRONE)) {
                        bodyState = BodyState.AIR_PRONE;
                        animationController.setAnimation("Armature|air-prone", -1);
                    }
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

            if (!controlManager.btUpPressed) {
                upButtonPressed = false;
            }
            if (!controlManager.btDownPressed) {
                downButtonPressed = false;
            }
            if (controlManager.btUpPressed && !upButtonPressed) {
                upButtonPressed = true;
                if (upState.equals(UpState.STAND)) {
                    if (!inAir) {
                        lockUpMovement = false;
                        lockUpMovementTimer = 0;
                        velocity.set(rigidBody.getLinearVelocity().x, 8.8f, 0);
                        rigidBody.setLinearVelocity(velocity);
                    }
                } else if (upState.equals(UpState.CROUCH)) {
                    enoughSpace = true;
                    for (int i = -1; i < 2; i++) {
                        movementRayFrom.set(
                                rigidBody.getCenterOfMassPosition().x + 0.2f * i,
                                rigidBody.getCenterOfMassPosition().y,
                                rigidBody.getCenterOfMassPosition().z);
                        movementRayTo.set(
                                rigidBody.getCenterOfMassPosition().x + 0.2f * i,
                                rigidBody.getCenterOfMassPosition().y + 0.65f + 0.6f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementCallback.setCollisionObject(null);
                        movementCallback.setClosestHitFraction(1);
                        movementCallback.setRayFromWorld(movementRayFrom);
                        movementCallback.setRayToWorld(movementRayTo);
                        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                        if (movementCallback.hasHit()) {
                            enoughSpace = false;
                            break;
                        }
                    }
                    if (enoughSpace) {
                        upState = UpState.STAND;
                        bodyState = BodyState.STAND;
                        animationController.setAnimation("Armature|stand", -1);
                        game.gameWorld.dynamicsWorld.removeRigidBody(rigidBody);
                        rigidBody.setCollisionShape(shapeStand);
                        game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, bodyDef.categoryBits, bodyDef.maskBits);
                        playerPosition.set(
                                rigidBody.getCenterOfMassPosition().x,
                                rigidBody.getCenterOfMassPosition().y + 0.25f, 0);
                        playerTransform.set(playerPosition, playerQuaternion, playerScale);
                        rigidBody.setWorldTransform(playerTransform);
                    }
                } else if (upState.equals(UpState.PRONE)) {
                    enoughSpace = true;
                    for (int i = -1; i < 2; i++) {
                        movementRayFrom.set(
                                rigidBody.getCenterOfMassPosition().x + 0.2f * i,
                                rigidBody.getCenterOfMassPosition().y,
                                rigidBody.getCenterOfMassPosition().z);
                        movementRayTo.set(
                                rigidBody.getCenterOfMassPosition().x + 0.2f * i,
                                rigidBody.getCenterOfMassPosition().y + 0.15f + 1.1f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementCallback.setCollisionObject(null);
                        movementCallback.setClosestHitFraction(1);
                        movementCallback.setRayFromWorld(movementRayFrom);
                        movementCallback.setRayToWorld(movementRayTo);
                        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                        if (movementCallback.hasHit()) {
                            enoughSpace = false;
                            break;
                        }
                    }
                    if (enoughSpace) {
                        upState = UpState.CROUCH;
                        bodyState = BodyState.CROUCH;
                        animationController.setAnimation("Armature|crouch", -1);
                        game.gameWorld.dynamicsWorld.removeRigidBody(rigidBody);
                        rigidBody.setCollisionShape(shapeCrouch);
                        game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, bodyDef.categoryBits, bodyDef.maskBits);
                        playerPosition.set(
                                rigidBody.getCenterOfMassPosition().x,
                                rigidBody.getCenterOfMassPosition().y + 0.5f, 0);
                        playerTransform.set(playerPosition, playerQuaternion, playerScale);
                        rigidBody.setWorldTransform(playerTransform);
                        //currentWeapon = weaponPistol;
                    }
                }
            }
            if (controlManager.btDownPressed && !downButtonPressed) {
                downButtonPressed = true;
                if (upState.equals(UpState.STAND)) {
                    upState = UpState.CROUCH;
                    bodyState = BodyState.CROUCH;
                    animationController.setAnimation("Armature|crouch", -1);
                    game.gameWorld.dynamicsWorld.removeRigidBody(rigidBody);
                    rigidBody.setCollisionShape(shapeCrouch);
                    game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, bodyDef.categoryBits, bodyDef.maskBits);
                    playerPosition.set(
                            rigidBody.getCenterOfMassPosition().x,
                            rigidBody.getCenterOfMassPosition().y - 0.25f, 0);
                    playerTransform.set(playerPosition, playerQuaternion, playerScale);
                    rigidBody.setWorldTransform(playerTransform);
                } else if (upState.equals(UpState.CROUCH)) {
                    enoughSpace = false;
                    enoughSpaceLeft = true;
                    enoughSpaceRight = true;
                    moveX = 0;
                    for (i = -1; i < 2; i++) {
                        if (i == 0) {
                            continue;
                        }
                        movementRayFrom.set(
                                rigidBody.getCenterOfMassPosition().x,
                                rigidBody.getCenterOfMassPosition().y - 0.4f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementRayTo.set(
                                rigidBody.getCenterOfMassPosition().x + 0.95f * i,
                                rigidBody.getCenterOfMassPosition().y - 0.4f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementCallback.setCollisionObject(null);
                        movementCallback.setClosestHitFraction(1);
                        movementCallback.setRayFromWorld(movementRayFrom);
                        movementCallback.setRayToWorld(movementRayTo);
                        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                        if (movementCallback.hasHit() && i == -1) {
                            movementCallback.getHitPointWorld(toProneHitPointLeft);
                            enoughSpaceLeft = false;
                        }
                        if (movementCallback.hasHit() && i == 1) {
                            movementCallback.getHitPointWorld(toProneHitPointRight);
                            enoughSpaceRight = false;
                        }
                    }
                    if (enoughSpaceLeft && enoughSpaceRight) {
                        enoughSpace = true;
                    } else if (!enoughSpaceLeft && enoughSpaceRight) {
                        movementRayFrom.set(
                                rigidBody.getCenterOfMassPosition().x,
                                rigidBody.getCenterOfMassPosition().y - 0.4f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementRayTo.set(
                                rigidBody.getCenterOfMassPosition().x + 2f,
                                rigidBody.getCenterOfMassPosition().y - 0.4f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementCallback.setCollisionObject(null);
                        movementCallback.setClosestHitFraction(1);
                        movementCallback.setRayFromWorld(movementRayFrom);
                        movementCallback.setRayToWorld(movementRayTo);
                        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                        if (movementCallback.hasHit()) {
                            movementCallback.getHitPointWorld(toProneHitPointRight);
                            if (Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointRight.x) >
                                    1.9f - Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointLeft.x)) {
                                moveX = 0.95f - Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointLeft.x);
                                enoughSpace = true;
                            }
                        } else {
                            moveX = 0.95f - Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointLeft.x);
                            enoughSpace = true;
                        }
                    } else if (enoughSpaceLeft && !enoughSpaceRight) {
                        movementRayFrom.set(
                                rigidBody.getCenterOfMassPosition().x,
                                rigidBody.getCenterOfMassPosition().y - 0.4f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementRayTo.set(
                                rigidBody.getCenterOfMassPosition().x - 2f,
                                rigidBody.getCenterOfMassPosition().y - 0.4f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementCallback.setCollisionObject(null);
                        movementCallback.setClosestHitFraction(1);
                        movementCallback.setRayFromWorld(movementRayFrom);
                        movementCallback.setRayToWorld(movementRayTo);
                        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                        if (movementCallback.hasHit()) {
                            movementCallback.getHitPointWorld(toProneHitPointLeft);
                            if (Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointLeft.x) >
                                    1.9f - Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointRight.x)) {
                                moveX = -(0.95f - Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointRight.x));
                                enoughSpace = true;
                            }
                        } else {
                            moveX = -(0.95f - Math.abs(rigidBody.getCenterOfMassPosition().x - toProneHitPointRight.x));
                            enoughSpace = true;
                        }
                    }
                    if (enoughSpace) {
                        upState = UpState.PRONE;
                        bodyState = BodyState.PRONE;
                        animationController.setAnimation("Armature|prone", -1);
                        game.gameWorld.dynamicsWorld.removeRigidBody(rigidBody);
                        rigidBody.setCollisionShape(shapeProne);
                        game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, bodyDef.categoryBits, bodyDef.maskBits);
                        playerPosition.set(
                                rigidBody.getCenterOfMassPosition().x + moveX,
                                rigidBody.getCenterOfMassPosition().y - 0.5f, 0);
                        playerTransform.set(playerPosition, playerQuaternion, playerScale);
                        rigidBody.setWorldTransform(playerTransform);
                    }
                }
            }
            if (!controlManager.btShootPressed) {
                shootButtonPressed = false;
                autoShootingTarget = false;
            }
            if ((controlManager.btShootPressed && !shootButtonPressed) || (controlManager.btShootPressed && currentWeapon.automatic)) {
                shootButtonPressed = true;
                currentWeapon.usePlayer();
            /*if (target != null) {
                if (currentWeapon.automatic) {
                    autoShootingTarget = true;
                }
                if (target instanceof Enemy) {
                    shootRayTo.set(
                            target.rigidBody.getCenterOfMassPosition().x,
                            target.rigidBody.getCenterOfMassPosition().y + enemyOffsetY,
                            target.rigidBody.getCenterOfMassPosition().z);
                } else {
                    shootRayTo.set(target.rigidBody.getCenterOfMassPosition());
                }
                if (currentWeapon.automatic) {
                    targetDeadPos.set(target.rigidBody.getCenterOfMassPosition());
                }
            } else {
                if (autoShootingTarget && target == null) {
                    shootRayTo.set(
                            targetDeadPos.x,
                            targetDeadPos.y + enemyOffsetY,
                            targetDeadPos.z);
                } else {
                    if (lookingRight) {
                        shootRayTo.set(
                                rigidBody.getCenterOfMassPosition().x + 30,
                                rigidBody.getCenterOfMassPosition().y  + currentWeapon.ammoOffsetY,
                                rigidBody.getCenterOfMassPosition().z);
                    } else {
                        shootRayTo.set(
                                rigidBody.getCenterOfMassPosition().x - 30,
                                rigidBody.getCenterOfMassPosition().y  + currentWeapon.ammoOffsetY,
                                rigidBody.getCenterOfMassPosition().z);
                    }
                }
            }*/
                //currentWeapon.use(shootRayFrom.x, shootRayFrom.y, shootRayTo.x, shootRayTo.y);
            /*if (!currentWeapon.rayAmmo) {
                currentWeapon.use(shootRayFrom.x, shootRayFrom.y, shootRayTo.x, shootRayTo.y);
            } else {
                shootCallback.setCollisionObject(null);
                shootCallback.setClosestHitFraction(1);
                shootCallback.setRayFromWorld(shootRayFrom);
                shootCallback.setRayToWorld(shootRayTo);
                game.gameWorld.dynamicsWorld.rayTest(shootRayFrom, shootRayTo, shootCallback);

                if (shootCallback.hasHit()) {
                    Vector3 ammoHitPoint = new Vector3();
                    shootCallback.getHitPointWorld(ammoHitPoint);
                    currentWeapon.use(
                            shootRayFrom.x,
                            shootRayFrom.y,
                            ammoHitPoint.x,
                            ammoHitPoint.y);
                    if (shootCallback.getCollisionObject().userData instanceof Enemy) {
                        ((Enemy) shootCallback.getCollisionObject().userData).onHit(-35);
                    }
                    if (shootCallback.getCollisionObject().userData instanceof Bottle) {
                        ((Bottle) shootCallback.getCollisionObject().userData).onHit(-10);
                    }
                } else {
                    if (lookingRight) {
                        currentWeapon.use(
                                shootRayFrom.x,
                                shootRayFrom.y,
                                shootRayFrom.x + 30,
                                shootRayFrom.y);
                    } else {
                        currentWeapon.use(
                                shootRayFrom.x,
                                shootRayFrom.y,
                                shootRayFrom.x - 30,
                                shootRayFrom.y);
                    }
                }
            }*/
            }

            if (!controlManager.btReloadPressed) {
                reloadButtonPressed = false;
            }
            if (controlManager.btReloadPressed && !reloadButtonPressed) {
                reloadButtonPressed = true;
                currentWeapon.reload();
            }

        /*inAir = true;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                movementRayFrom.set(
                        rigidBody.getCenterOfMassPosition().x + i * 0.15f,
                        rigidBody.getCenterOfMassPosition().y,
                        rigidBody.getCenterOfMassPosition().z + j * 0.15f);
                movementRayTo.set(
                        rigidBody.getCenterOfMassPosition().x + i * 0.15f,
                        rigidBody.getCenterOfMassPosition().y - 0.9f - 0.05f,
                        rigidBody.getCenterOfMassPosition().z + j * 0.15f);
                movementCallback.setCollisionObject(null);
                movementCallback.setClosestHitFraction(1);
                movementCallback.setRayFromWorld(movementRayFrom);
                movementCallback.setRayToWorld(movementRayTo);
                game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                if (movementCallback.hasHit()) {
                    inAir = false;
                    break;
                }
            }
            if (!inAir) {
                break;
            }
        }*/

            inAir = true;
            if (rigidBody.getLinearVelocity().y <= 0.1f) {
                if (upState.equals(UpState.STAND)) {
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
                            break;
                        }
                    }
                } else if (upState.equals(UpState.CROUCH)) {
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
                                rigidBody.getCenterOfMassPosition().y - 0.65f - 0.05f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementCallback.setCollisionObject(null);
                        movementCallback.setClosestHitFraction(1);
                        movementCallback.setRayFromWorld(movementRayFrom);
                        movementCallback.setRayToWorld(movementRayTo);
                        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                        if (movementCallback.hasHit()) {
                            inAir = false;
                            break;
                        }
                    }
                } else if (upState.equals(UpState.PRONE)) {
                    for (i = -1; i < 2; i++) {
                        movementRayFrom.set(
                                rigidBody.getCenterOfMassPosition().x + i * 0.85f,
                                rigidBody.getCenterOfMassPosition().y,
                                rigidBody.getCenterOfMassPosition().z);
                        movementRayTo.set(
                                rigidBody.getCenterOfMassPosition().x + i * 0.85f,
                                rigidBody.getCenterOfMassPosition().y - 0.15f - 0.05f,
                                rigidBody.getCenterOfMassPosition().z);
                        movementCallback.setCollisionObject(null);
                        movementCallback.setClosestHitFraction(1);
                        movementCallback.setRayFromWorld(movementRayFrom);
                        movementCallback.setRayToWorld(movementRayTo);
                        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
                        if (movementCallback.hasHit()) {
                            inAir = false;
                            break;
                        }
                    }
                }
            }

            if (!inAir) {
                if (lockUpMovementTimer > 0.5f) {
                    lockUpMovement = true;
                }
            }
        }
    }
}