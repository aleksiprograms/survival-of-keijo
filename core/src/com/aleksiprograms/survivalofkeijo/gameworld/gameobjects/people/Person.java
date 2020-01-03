package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Ice;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Weapon;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.aleksiprograms.survivalofkeijo.toolbox.AnimationState;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public abstract class Person extends PhysicalObject {

    public boolean visibleToPlayer;
    public Weapon weapon;
    public boolean dead;
    public int health;
    public int maxHealth;

    Vector3 velocity;
    boolean lookingRight;
    float xVelocity;
    boolean inAir;
    boolean onIce;
    Vector3 forceOnIce;
    boolean jumped;
    boolean doubleJumpDone;
    boolean onDeadThingsDone;
    Vector3 movementRayFrom;
    Vector3 movementRayTo;
    ClosestRayResultCallback movementCallback;
    BigAreaObject bigArea;
    SmallAreaObject smallArea;

    private Vector3 personPosition;
    private Quaternion personQuaternion;
    private Vector3 personScale;
    private Matrix4 personTransform;
    private boolean personBodyToRight;
    private float jumpedTimer;
    private boolean explosion;
    private boolean lockUpMovement;
    private float lockUpMovementTimer;
    private AnimationState animationState;
    private AnimationController animationController;
    private AnimationController.AnimationListener animationListener;

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
        bigArea = game.gameWorld.bigAreaManagers.get(0).bigAreaNull;
        smallArea = game.gameWorld.bigAreaManagers.get(0).smallAreaNull;
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
            if (rigidBody.getLinearVelocity().y > 0) {
                velocity.set(rigidBody.getLinearVelocity().x, 0, 0);
                rigidBody.setLinearVelocity(velocity);
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
                personPosition.set(rigidBody.getCenterOfMassPosition());
                personQuaternion.set(Vector3.Y, 0);
                personTransform.set(personPosition, personQuaternion, personScale);
                rigidBody.setCenterOfMassTransform(personTransform);
            }
        } else {
            if (personBodyToRight) {
                personBodyToRight = false;
                personPosition.set(rigidBody.getCenterOfMassPosition());
                personQuaternion.set(Vector3.Y, 180);
                personTransform.set(personPosition, personQuaternion, personScale);
                rigidBody.setCenterOfMassTransform(personTransform);
            }
        }
    }

    void jump() {
        lockUpMovement = false;
        lockUpMovementTimer = 0;
        jumped = true;
        jumpedTimer = 0;
        velocity.set(rigidBody.getLinearVelocity().x, 8.8f, 0);
        rigidBody.setLinearVelocity(velocity);
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
        rigidBody.applyCentralForce(force);
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
            velocity.set(0, rigidBody.getLinearVelocity().y, 0);
            rigidBody.setLinearVelocity(velocity);
        }
    }

    void setAnimation(AnimationState animationState) {
        if (!animationState.equals(this.animationState)) {
            this.animationState = animationState;
            if (animationState.equals(AnimationState.STAND)) {
                animationController.setAnimation("Armature|stand", -1);
            } else if (animationState.equals(AnimationState.WALK)) {
                animationController.setAnimation("Armature|walk", -1, (xVelocity / 2f), animationListener);
            } else if (animationState.equals(AnimationState.WALK_REVERSE)) {
                animationController.setAnimation("Armature|walk", -1, -(xVelocity / 2f), animationListener);
            } else if (animationState.equals(AnimationState.AIR)) {
                animationController.setAnimation("Armature|air", -1);
            } else if (animationState.equals(AnimationState.DEAD)) {
                animationController.setAnimation("Armature|dead", 1, 2, animationListener);
            } else if (animationState.equals(AnimationState.DEAD_AIR)) {
                animationController.setAnimation("Armature|dead-air", 1, 2, animationListener);
            } else if (animationState.equals(AnimationState.DEAD_EXPLOSION)) {
                animationController.setAnimation("Armature|dead-explosion", 1, 2, animationListener);
            } else if (animationState.equals(AnimationState.DEAD_AIR_EXPLOSION)) {
                animationController.setAnimation("Armature|dead-air-explosion", 1, 2, animationListener);
            }
        }
    }

    private void setPersonTransform() {
        personPosition.set(rigidBody.getCenterOfMassPosition());
        personQuaternion.set(rigidBody.getOrientation());
        personTransform.set(personPosition, personQuaternion, personScale);
        transform.set(personPosition, personQuaternion, personScale);
    }

    private void checkArea() {
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

    private void isInAirOrOnIce() {
        inAir = true;
        onIce = false;
        if (rigidBody.getLinearVelocity().y <= 0.1f) {
            for (int i = -1; i < 2; i++) {
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
}