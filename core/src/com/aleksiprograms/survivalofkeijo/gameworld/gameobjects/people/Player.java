package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Shop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Weapon;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.aleksiprograms.survivalofkeijo.toolbox.AnimationState;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.aleksiprograms.survivalofkeijo.managers.ControlManager;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.utils.Array;

public class Player extends Person {

    public Array<Weapon> weapons;
    public PhysicalObject target;
    public float money;

    private ControlManager controlManager;
    private boolean movingRight;
    private boolean shootButtonPressed;
    private boolean upButtonPressed;
    private boolean reloadButtonPressed;

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

        weapons = new Array<>();
        controlManager = new ControlManager();
        rigidBody.userData = this;
    }

    @Override
    public void init(float x, float y, float z, float angle) {
        super.init(x, y, z, angle);
        visibleToPlayer = true;
        health = 1000;
        maxHealth = 1000;
        movingRight = true;
        shootButtonPressed = false;
        upButtonPressed = false;
        reloadButtonPressed = false;
        money = 100000.0f;
        weapons.clear();
    }

    public void addWeapon(Weapon weapon, WeaponData weaponData, Person owner, Person target, boolean playerWeapon) {
        weapons.add(weapon);
        weapons.peek().init(weaponData, owner, target, playerWeapon);
        if (weapons.size == 1) {
            this.weapon = weapons.peek();
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        controlManager.update(deltaTime);
        if (!dead) {
            isInFrontOfBuilding();
            updateWeapon();
        }
    }

    @Override
    void updateMovement(float deltaTime) {
        super.updateMovement(deltaTime);

        if (target != null) {
            if (target.rigidBody.getCenterOfMassPosition().x >= rigidBody.getCenterOfMassPosition().x) {
                lookingRight = true;
                weapon.lookingRight = true;
            } else {
                lookingRight = false;
                weapon.lookingRight = false;
            }
        } else {
            if (movingRight) {
                lookingRight = true;
                weapon.lookingRight = true;
            } else {
                lookingRight = false;
                weapon.lookingRight = false;
            }
        }

        xVelocity = 4f - (1f * ((game.gameWorld.weaponManagerPlayer.getWeaponData(weapon.weaponData.ID).weight - game.gameWorld.weaponManagerPlayer.minWeight) / (game.gameWorld.weaponManagerPlayer.maxWeight - game.gameWorld.weaponManagerPlayer.minWeight)));

        if (controlManager.buttonRightPressed && canMoveLeftOrRight(true)) {
            movingRight = true;
            if (!inAir) {
                if (lookingRight) {
                    setAnimation(AnimationState.WALK);
                } else {
                    setAnimation(AnimationState.WALK_REVERSE);
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
        } else if (controlManager.buttonLeftPressed && canMoveLeftOrRight(false)) {
            movingRight = false;
            if (!inAir) {
                if (!lookingRight) {
                    setAnimation(AnimationState.WALK);
                } else {
                    setAnimation(AnimationState.WALK_REVERSE);
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
                setAnimation(AnimationState.STAND);
            }
            if (!onIce) {
                velocity.set(0, rigidBody.getLinearVelocity().y, 0);
                rigidBody.setLinearVelocity(velocity);
            }
        }

        if (!controlManager.buttonUpPressed) {
            upButtonPressed = false;
        }
        if (controlManager.buttonUpPressed && !upButtonPressed) {
            upButtonPressed = true;
            if (!inAir) {
                jump();
            } else {
                if (!doubleJumpDone) {
                    doubleJumpDone = true;
                    jump();
                }
            }
        }

        if (inAir) {
            setAnimation(AnimationState.AIR);
        }
    }

    @Override
    public void onHit(int damage) {
        super.onHit(damage);
        game.gameScreen.inGameHud.updateHud();
    }

    @Override
    void onDead(float deltaTime) {
        super.onDead(deltaTime);
        if (!onDeadThingsDone) {
            onDeadThingsDone = true;
            weapon.ownerDead = true;
            game.gameScreen.setGameState(GameState.GAME_OVER);
            game.gameScreen.stage.clear();
            game.gameScreen.gameOverHud.updateHud();
            game.gameScreen.stage.addActor(game.gameScreen.gameOverHud);
        }
    }

    private void isInFrontOfBuilding() {
        for (int i = 0; i < game.gameWorld.enterableBuildings.size; i++) {
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

    private boolean canMoveLeftOrRight(boolean right) {
        movementRayFrom.set(
                rigidBody.getCenterOfMassPosition().x,
                rigidBody.getCenterOfMassPosition().y,
                rigidBody.getCenterOfMassPosition().z);
        movementRayTo.set(
                rigidBody.getCenterOfMassPosition().x + (right ? 1 : -1) * 0.4f,
                rigidBody.getCenterOfMassPosition().y,
                rigidBody.getCenterOfMassPosition().z);
        movementCallback.setCollisionObject(null);
        movementCallback.setClosestHitFraction(1);
        movementCallback.setRayFromWorld(movementRayFrom);
        movementCallback.setRayToWorld(movementRayTo);
        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
        return !movementCallback.hasHit();
    }

    private void updateWeapon() {
        weapon.target = ((Person) target);
        if (!controlManager.buttonShootPressed) {
            shootButtonPressed = false;
        }
        if ((controlManager.buttonShootPressed && !shootButtonPressed) || (controlManager.buttonShootPressed && weapon.automatic)) {
            shootButtonPressed = true;
            weapon.usePlayer();
        }

        if (!controlManager.buttonReloadPressed) {
            reloadButtonPressed = false;
        }
        if (controlManager.buttonReloadPressed && !reloadButtonPressed) {
            reloadButtonPressed = true;
            weapon.reload();
        }
    }
}