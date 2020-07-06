package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Ice;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Weapon;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.AnimationState;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public abstract class Person extends PhysicalObject {

    protected boolean visibleToPlayer;
    protected Weapon weapon;
    protected boolean dead;
    protected int health;
    protected int maxHealth;

    protected Vector3 velocity;
    protected boolean lookingRight;
    protected float xVelocity;
    protected boolean inAir;
    protected boolean onIce;
    protected Vector3 forceOnIce;
    protected boolean jumped;
    protected boolean doubleJumpDone;
    protected boolean onDeadThingsDone;
    protected Vector3 movementRayFrom;
    protected Vector3 movementRayTo;
    protected ClosestRayResultCallback movementCallback;
    protected BigAreaObject bigArea;
    protected SmallAreaObject smallArea;

    protected Vector3 personPosition;
    protected Quaternion personQuaternion;
    protected Vector3 personScale;
    protected Matrix4 personTransform;
    protected boolean personBodyToRight;
    protected float jumpedTimer;
    protected boolean explosion;
    protected boolean lockUpMovement;
    protected float lockUpMovementTimer;
    protected AnimationState animationState;
    protected AnimationController animationController;
    protected AnimationController.AnimationListener animationListener;

    public Person(
            TheGame game,
            ModelInstance modelInstance,
            btCollisionShape collisionShape,
            BodyDef bodyDef) {

        super(
                game,
                modelInstance,
                collisionShape,
                bodyDef);
        personPosition = new Vector3();
        personQuaternion = new Quaternion();
        personScale = new Vector3(1, 1, 1);
        personTransform = new Matrix4();
        velocity = new Vector3();
        forceOnIce = new Vector3();
        movementRayFrom = new Vector3();
        movementRayTo = new Vector3();
        movementCallback = new ClosestRayResultCallback(movementRayFrom, movementRayTo);
        movementCallback.setCollisionFilterGroup(Constants.CATEGORY_SENSOR_MOVEMENT);
        movementCallback.setCollisionFilterMask(Constants.MASK_SENSOR_MOVEMENT);
        animationController = new AnimationController(this);
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
        animationState = AnimationState.STAND;
        lookingRight = true;
        personBodyToRight = true;
        animationController.setAnimation("Armature|stand", -1);
        inAir = false;
        onIce = false;
        jumped = false;
        jumpedTimer = 0;
        doubleJumpDone = false;
        lockUpMovement = true;
        dead = false;
        onDeadThingsDone = false;
        explosion = false;
        bigArea = game.getGameWorld().getBigAreaManagers().get(0).getBigAreaNull();
        smallArea = game.getGameWorld().getBigAreaManagers().get(0).getSmallAreaNull();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (health <= 0) {
            dead = true;
        }
        if (!destroyBody) {
            setPersonTransform();
        }
        if (visibleToPlayer) {
            animationController.update(deltaTime);
        }
        if (dead) {
            onDead(deltaTime);
        } else {
            checkArea();
            updateMovement(deltaTime);
        }
        if (!dead || inAir) {
            isInAirOrOnIce();
        }
    }

    void updateMovement(float deltaTime) {
        if (!lockUpMovement) {
            lockUpMovementTimer += deltaTime;
        }
        if (!inAir) {
            if (lockUpMovementTimer > 0.5f) {
                lockUpMovement = true;
            }
        }
        if (lockUpMovement) {
            if (getRigidBody().getLinearVelocity().y > 0) {
                velocity.set(getRigidBody().getLinearVelocity().x, 0, 0);
                getRigidBody().setLinearVelocity(velocity);
            }
        }
        if (jumped) {
            jumpedTimer += deltaTime;
            if (jumpedTimer > 0.5f) {
                jumped = false;
            }
        }
        if (lookingRight) {
            if (!personBodyToRight) {
                personBodyToRight = true;
                personPosition.set(getRigidBody().getCenterOfMassPosition());
                personQuaternion.set(Vector3.Y, 0);
                personTransform.set(personPosition, personQuaternion, personScale);
                getRigidBody().setCenterOfMassTransform(personTransform);
            }
        } else {
            if (personBodyToRight) {
                personBodyToRight = false;
                personPosition.set(getRigidBody().getCenterOfMassPosition());
                personQuaternion.set(Vector3.Y, 180);
                personTransform.set(personPosition, personQuaternion, personScale);
                getRigidBody().setCenterOfMassTransform(personTransform);
            }
        }
    }

    void jump() {
        lockUpMovement = false;
        lockUpMovementTimer = 0;
        jumped = true;
        jumpedTimer = 0;
        velocity.set(getRigidBody().getLinearVelocity().x, 8.8f, 0);
        getRigidBody().setLinearVelocity(velocity);
    }

    public void onHit(int damage) {
        health += damage;
        if (health <= 0) {
            dead = true;
        }
    }

    public void onExplosion(Vector3 force) {
        explosion = true;
        dead = true;
        getRigidBody().applyCentralForce(force);
    }

    void onDead(float deltaTime) {
        if (explosion) {
            if (inAir) {
                setAnimation(AnimationState.DEAD_AIR_EXPLOSION);
            } else {
                setAnimation(AnimationState.DEAD_EXPLOSION);
                destroyBody = true;
            }
        } else {
            if (inAir) {
                setAnimation(AnimationState.DEAD_AIR);
            } else {
                setAnimation(AnimationState.DEAD);
                destroyBody = true;
            }
        }
        if (!destroyBody) {
            velocity.set(0, getRigidBody().getLinearVelocity().y, 0);
            getRigidBody().setLinearVelocity(velocity);
        }
    }

    void setAnimation(AnimationState animationState) {
        if (!animationState.equals(this.animationState)) {
            this.animationState = animationState;
            if (animationState.equals(AnimationState.STAND)) {
                animationController.setAnimation(
                        "Armature|stand", -1);
            } else if (animationState.equals(AnimationState.WALK)) {
                animationController.setAnimation(
                        "Armature|walk", -1, (xVelocity / 2f), animationListener);
            } else if (animationState.equals(AnimationState.WALK_REVERSE)) {
                animationController.setAnimation(
                        "Armature|walk", -1, -(xVelocity / 2f), animationListener);
            } else if (animationState.equals(AnimationState.AIR)) {
                animationController.setAnimation(
                        "Armature|air", -1);
            } else if (animationState.equals(AnimationState.DEAD)) {
                animationController.setAnimation(
                        "Armature|dead", 1, 2, animationListener);
            } else if (animationState.equals(AnimationState.DEAD_AIR)) {
                animationController.setAnimation(
                        "Armature|dead-air", 1, 2, animationListener);
            } else if (animationState.equals(AnimationState.DEAD_EXPLOSION)) {
                animationController.setAnimation(
                        "Armature|dead-explosion", 1, 2, animationListener);
            } else if (animationState.equals(AnimationState.DEAD_AIR_EXPLOSION)) {
                animationController.setAnimation(
                        "Armature|dead-air-explosion", 1, 2, animationListener);
            }
        }
    }

    private void setPersonTransform() {
        personPosition.set(getRigidBody().getCenterOfMassPosition());
        personQuaternion.set(getRigidBody().getOrientation());
        personTransform.set(personPosition, personQuaternion, personScale);
        transform.set(personPosition, personQuaternion, personScale);
    }

    private void checkArea() {
        if (bigArea.isNullObject()) {
            bigArea = game.getGameWorld().getNewBigArea(
                    getRigidBody().getCenterOfMassPosition().x,
                    getRigidBody().getCenterOfMassPosition().y);
        } else {
            if (getRigidBody().getCenterOfMassPosition().x
                    <= bigArea.getX() - bigArea.getWidth() / 2
                    || getRigidBody().getCenterOfMassPosition().x
                    >= bigArea.getX() + bigArea.getWidth() / 2
                    || getRigidBody().getCenterOfMassPosition().y
                    <= bigArea.getY() - bigArea.getHeight() / 2
                    || getRigidBody().getCenterOfMassPosition().y
                    >= bigArea.getY() + bigArea.getHeight() / 2) {
                bigArea = game.getGameWorld().getNewBigArea(
                        getRigidBody().getCenterOfMassPosition().x,
                        getRigidBody().getCenterOfMassPosition().y);
            }
        }
        if (smallArea.isNullObject()) {
            if (bigArea.isNullObject()) {
                smallArea = game.getGameWorld().getNewSmallArea(
                        getRigidBody().getCenterOfMassPosition().x,
                        getRigidBody().getCenterOfMassPosition().y,
                        1);
            } else {
                smallArea = game.getGameWorld().getNewSmallArea(
                        getRigidBody().getCenterOfMassPosition().x,
                        getRigidBody().getCenterOfMassPosition().y,
                        bigArea.getArea());
            }
        } else {
            if (getRigidBody().getCenterOfMassPosition().x
                    <= smallArea.getX() - smallArea.getWidth() / 2
                    || getRigidBody().getCenterOfMassPosition().x
                    >= smallArea.getX() + smallArea.getWidth() / 2
                    || getRigidBody().getCenterOfMassPosition().y
                    <= smallArea.getY() - smallArea.getHeight() / 2
                    || getRigidBody().getCenterOfMassPosition().y
                    >= smallArea.getY() + smallArea.getHeight() / 2) {
                if (bigArea.isNullObject()) {
                    smallArea = game.getGameWorld().getNewSmallArea(
                            getRigidBody().getCenterOfMassPosition().x,
                            getRigidBody().getCenterOfMassPosition().y,
                            1);
                } else {
                    smallArea = game.getGameWorld().getNewSmallArea(
                            getRigidBody().getCenterOfMassPosition().x,
                            getRigidBody().getCenterOfMassPosition().y,
                            bigArea.getArea());
                }
            }
        }
    }

    private void isInAirOrOnIce() {
        inAir = true;
        onIce = false;
        if (getRigidBody().getLinearVelocity().y <= 0.1f) {
            for (int i = -1; i < 2; i++) {
                if (i == 0) {
                    continue;
                }
                movementRayFrom.set(
                        getRigidBody().getCenterOfMassPosition().x + i * 0.15f,
                        getRigidBody().getCenterOfMassPosition().y,
                        getRigidBody().getCenterOfMassPosition().z);
                movementRayTo.set(
                        getRigidBody().getCenterOfMassPosition().x + i * 0.15f,
                        getRigidBody().getCenterOfMassPosition().y - 0.9f - 0.05f,
                        getRigidBody().getCenterOfMassPosition().z);
                movementCallback.setCollisionObject(null);
                movementCallback.setClosestHitFraction(1);
                movementCallback.setRayFromWorld(movementRayFrom);
                movementCallback.setRayToWorld(movementRayTo);
                game.getGameWorld().getDynamicsWorld().rayTest(
                        movementRayFrom, movementRayTo, movementCallback);
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

    public void setVisibleToPlayer(boolean visibleToPlayer) {
        this.visibleToPlayer = visibleToPlayer;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isDead() {
        return dead;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}