package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Shop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Weapon;
import com.aleksiprograms.survivalofkeijo.managers.ControlManager;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.AnimationState;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
import com.aleksiprograms.survivalofkeijo.weapondata.WeaponData;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.utils.Array;

public class Player extends Person {

    private Array<Weapon> weapons;
    private PhysicalObject target;
    private float money;

    private ControlManager controlManager;
    private boolean movingRight;
    private boolean shootButtonPressed;
    private boolean upButtonPressed;
    private boolean reloadButtonPressed;

    public Player(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_PERSON_PLAYER, Model.class)),
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
        getRigidBody().userData = this;
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

    public void addWeapon(
            Weapon weapon,
            WeaponData weaponData,
            Person owner,
            Person target,
            boolean playerWeapon) {
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
            if (target.getRigidBody().getCenterOfMassPosition().x
                    >= getRigidBody().getCenterOfMassPosition().x) {
                lookingRight = true;
                weapon.setLookingRight(true);
            } else {
                lookingRight = false;
                weapon.setLookingRight(false);
            }
        } else {
            if (movingRight) {
                lookingRight = true;
                weapon.setLookingRight(true);
            } else {
                lookingRight = false;
                weapon.setLookingRight(false);
            }
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                weapon.getWeaponData().getID()).getWeightType().equals(WeightType.LIGHT)) {
            xVelocity = 4f;
        } else if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                weapon.getWeaponData().getID()).getWeightType().equals(WeightType.MEDIUM)) {
            xVelocity = 3f;
        } else if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                weapon.getWeaponData().getID()).getWeightType().equals(WeightType.HEAVY)) {
            xVelocity = 2f;
        } else {
            xVelocity = 4f;
        }

        if (controlManager.isButtonRightPressed() && canMoveLeftOrRight(true)) {
            movingRight = true;
            if (!inAir) {
                if (lookingRight) {
                    setAnimation(AnimationState.WALK);
                } else {
                    setAnimation(AnimationState.WALK_REVERSE);
                }
            }
            if (onIce) {
                if (getRigidBody().getLinearVelocity().x <= xVelocity * 2f) {
                    forceOnIce.set(150, 0, 0);
                    getRigidBody().applyCentralForce(forceOnIce);
                }
            } else {
                velocity.set(xVelocity, getRigidBody().getLinearVelocity().y, 0);
                getRigidBody().setLinearVelocity(velocity);
            }
        } else if (controlManager.isButtonLeftPressed() && canMoveLeftOrRight(false)) {
            movingRight = false;
            if (!inAir) {
                if (!lookingRight) {
                    setAnimation(AnimationState.WALK);
                } else {
                    setAnimation(AnimationState.WALK_REVERSE);
                }
            }
            if (onIce) {
                if (getRigidBody().getLinearVelocity().x >= -xVelocity * 2f) {
                    forceOnIce.set(-150, 0, 0);
                    getRigidBody().applyCentralForce(forceOnIce);
                }
            } else {
                velocity.set(-xVelocity, getRigidBody().getLinearVelocity().y, 0);
                getRigidBody().setLinearVelocity(velocity);
            }
        } else {
            if (!inAir) {
                setAnimation(AnimationState.STAND);
            }
            if (!onIce) {
                velocity.set(0, getRigidBody().getLinearVelocity().y, 0);
                getRigidBody().setLinearVelocity(velocity);
            }
        }

        if (!controlManager.isButtonUpPressed()) {
            upButtonPressed = false;
        }
        if (controlManager.isButtonUpPressed() && !upButtonPressed) {
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
        game.getGameScreen().getInGameHud().updateData();
    }

    @Override
    void onDead(float deltaTime) {
        super.onDead(deltaTime);
        if (!onDeadThingsDone) {
            onDeadThingsDone = true;
            weapon.setOwnerDead(true);
            game.getGameScreen().changeGameState(GameState.GAME_OVER);
        }
    }

    private void isInFrontOfBuilding() {
        for (int i = 0; i < game.getGameWorld().getEnterableBuildings().size; i++) {
            if (getRigidBody().getCenterOfMassPosition().x
                    >= game.getGameWorld().getEnterableBuildings().get(i).getX()
                    - game.getGameWorld().getEnterableBuildings().get(i).getWidth() / 2
                    && getRigidBody().getCenterOfMassPosition().x
                    <= game.getGameWorld().getEnterableBuildings().get(i).getX()
                    + game.getGameWorld().getEnterableBuildings().get(i).getWidth() / 2
                    && getRigidBody().getCenterOfMassPosition().y
                    >= game.getGameWorld().getEnterableBuildings().get(i).getY()
                    - game.getGameWorld().getEnterableBuildings().get(i).getHeight() / 2
                    && getRigidBody().getCenterOfMassPosition().y
                    <= game.getGameWorld().getEnterableBuildings().get(i).getY()
                    + game.getGameWorld().getEnterableBuildings().get(i).getHeight() / 2) {
                if (game.getGameWorld().getEnterableBuildings().get(i) instanceof Shop) {
                    game.getGameScreen().getInGameHud().setButtonEnterShopVisibility(true);
                }
            } else {
                if (game.getGameWorld().getEnterableBuildings().get(i) instanceof Shop) {
                    game.getGameScreen().getInGameHud().setButtonEnterShopVisibility(false);
                }
            }
        }
    }

    private boolean canMoveLeftOrRight(boolean right) {
        movementRayFrom.set(
                getRigidBody().getCenterOfMassPosition().x,
                getRigidBody().getCenterOfMassPosition().y,
                getRigidBody().getCenterOfMassPosition().z);
        movementRayTo.set(
                getRigidBody().getCenterOfMassPosition().x + (right ? 1 : -1) * 0.4f,
                getRigidBody().getCenterOfMassPosition().y,
                getRigidBody().getCenterOfMassPosition().z);
        movementCallback.setCollisionObject(null);
        movementCallback.setClosestHitFraction(1);
        movementCallback.setRayFromWorld(movementRayFrom);
        movementCallback.setRayToWorld(movementRayTo);
        game.getGameWorld().getDynamicsWorld().rayTest(
                movementRayFrom, movementRayTo, movementCallback);
        return !movementCallback.hasHit();
    }

    private void updateWeapon() {
        weapon.setTarget(((Person) target));
        if (!controlManager.isButtonShootPressed()) {
            shootButtonPressed = false;
        }
        if ((controlManager.isButtonShootPressed() && !shootButtonPressed)
                || (controlManager.isButtonShootPressed() && weapon.isAutomatic())) {
            shootButtonPressed = true;
            weapon.usePlayer();
        }

        if (!controlManager.isButtonReloadPressed()) {
            reloadButtonPressed = false;
        }
        if (controlManager.isButtonReloadPressed() && !reloadButtonPressed) {
            reloadButtonPressed = true;
            weapon.reload();
        }
    }

    public Array<Weapon> getWeapons() {
        return weapons;
    }

    public void setTarget(PhysicalObject target) {
        this.target = target;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}