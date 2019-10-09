package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyGuideObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyJumpObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Weapon;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyState;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class Enemy extends Person {

    public BodyState bodyState;
    public Vector3 enemyPosition;
    public Quaternion enemyQuaternion;
    public Vector3 enemyScale;
    public Matrix4 enemyTransform;
    public Vector3 velocity;
    public boolean lookingRight;
    public boolean enemyBodyToRight;
    public boolean visibleToPlayer;
    public AnimationController animationController;
    public AnimationController.AnimationListener animationListener;
    public AnimationController.AnimationListener animationListenerOnDead;
    private boolean deadOnGround;
    private float fallenAfterDeadTimer;

    public Weapon weapon;
    public int i;
    public int j;
    public int k;

    private boolean inAir;
    private boolean jumped;
    private float jumpedTimer;
    private boolean stopToWait;
    private boolean stopToAttack;
    private boolean onExplosion;
    private boolean stuck;
    private float stuckTimer;
    private boolean initStand;
    private float initTimer;
    private EnemyGuideObject enemyGuideObject;
    private Vector3 movementRayFrom;
    private Vector3 movementRayTo;
    private ClosestRayResultCallback movementCallback;
    private float lockUpMovementTimer;
    private boolean lockUpMovement;
    public float deadOnGroundTimer;

    public BigAreaObject bigArea;
    public SmallAreaObject smallArea;
    public int lastBigArea;
    public int lastSmallArea;
    public int playerLastBigArea;
    public int playerLastSmallArea;

    public Enemy(TheGame game) {

        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_PERSON_ENEMY, Model.class)),
                new btBoxShape(new Vector3(0.2f, 0.9f, 0.2f)),
                new BodyDef.BodyDefBuilder()
                        .mass(80)
                        .linearDamping(0.1f)
                        .categoryBits(Constants.CATEGORY_ENEMY)
                        .maskBits(Constants.MASK_ENEMY)
                        .useMotionState(false)
                        .build());

        rigidBody.userData = this;
        enemyPosition = new Vector3();
        enemyQuaternion = new Quaternion();
        enemyScale = new Vector3(1, 1, 1);
        enemyTransform = new Matrix4();
        velocity = new Vector3();
        animationController = new AnimationController(this);

        //currentWeapon = new Knife(game, false, true);

        movementRayFrom = new Vector3();
        movementRayTo = new Vector3();
        movementCallback = new ClosestRayResultCallback(movementRayFrom, movementRayTo);
        movementCallback.setCollisionFilterGroup(Constants.CATEGORY_SENSOR_MOVEMENT);
        movementCallback.setCollisionFilterMask(Constants.MASK_SENSOR_MOVEMENT);
        dimensions.set(1.8f, 1.8f, 1);

        animationListener = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {

            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };
        animationListenerOnDead = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                deadOnGround = true;
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };
    }

    public void init(float x, float y, float z, float angle, int health) {
        super.init(x, y, z, angle);
        this.health = health;
        maxHealth = health;
        bodyState = BodyState.STAND;
        lookingRight = false;
        enemyBodyToRight = true;
        visibleToPlayer = false;
        animationController.setAnimation("Armature|stand", -1);

        inAir = false;
        stopToWait = false;
        stopToAttack = false;
        onExplosion = false;
        jumped = false;
        jumpedTimer = 0;
        initStand = true;
        initTimer = 0;
        stuck = false;
        stuckTimer = 0;
        enemyGuideObject = null;

        dead = false;
        destroyBody = false;
        deadOnGroundTimer = 0;
        deadOnGround = false;
        fallenAfterDeadTimer = -1.0f;

        bigArea = game.gameWorld.bigAreaManagers.get(0).bigAreaNull;
        smallArea = game.gameWorld.bigAreaManagers.get(0).smallAreaNull;
        lastBigArea = 0;
        lastSmallArea = 0;
        playerLastBigArea = 0;
        playerLastSmallArea = 0;
    }

    public void onExplosion(Vector3 force) {
        onExplosion = true;
        dead = true;
        //rigidBody.setGravity(new Vector3(0,0,0));
        rigidBody.applyCentralForce(force);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        enemyPosition.set(rigidBody.getCenterOfMassPosition());
        enemyQuaternion.set(rigidBody.getOrientation());
        enemyTransform.set(enemyPosition, enemyQuaternion, enemyScale);
        transform.set(enemyPosition, enemyQuaternion, enemyScale);
        if (initStand) {
            initTimer += deltaTime;
            if (initTimer > 1) {
                initStand = false;
            }
        }
        if (visibleToPlayer) {
            animationController.update(deltaTime);
        }
        if (dead || onExplosion) {
            if (deadOnGround && destroyBody) {
                deadOnGroundTimer += deltaTime;
            }
            if (!destroyBody) {
                velocity.set(0, rigidBody.getLinearVelocity().y, 0);
                rigidBody.setLinearVelocity(velocity);
                if (rigidBody.getLinearVelocity().y > 0.05f) {
                    fallenAfterDeadTimer = 0;
                }
                if (fallenAfterDeadTimer < -0.5f || fallenAfterDeadTimer > 0.2f) {
                    if (Math.abs(rigidBody.getLinearVelocity().y) < 0.1f) {
                        destroyBody = true;
                    }
                } else {
                    fallenAfterDeadTimer += deltaTime;
                }
            }
            if (deadOnGroundTimer > 5) {
                free = true;
                weapon.free = true;
            }
            if (!bodyState.equals(BodyState.DEAD)) {
                bodyState = BodyState.DEAD;
                weapon.ownerDead = true;
                animationController.setAnimation("Armature|die", 1, 2, animationListenerOnDead);
                if (weapon.animateOnMoving) {
                    weapon.animationController.setAnimation("Armature|stand", -1);
                }
                game.gameWorld.enemyManager.enemyDied();
                game.gameScreen.inGameHud.updateHud();
            }
        } else {
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

            if (lockUpMovement) {
                if (rigidBody.getLinearVelocity().y > 0) {
                    velocity.set(rigidBody.getLinearVelocity().x, 0, 0);
                    rigidBody.setLinearVelocity(velocity);
                }
            }

            if (Math.abs(rigidBody.getLinearVelocity().len()) < 0.1f && (bigArea.area != game.gameWorld.player.bigArea.area || smallArea.area != game.gameWorld.player.smallArea.area)) {
                stuckTimer += deltaTime;
                if (stuckTimer > 0.5f) {
                    stuck = true;
                    stuckTimer = 0;
                }
            } else {
                stuck = false;
                stuckTimer = 0;
            }

            if (!(inAir || jumped)) {
                stopToAttack = false;
                if (bigArea.area == game.gameWorld.player.bigArea.area && smallArea.area == game.gameWorld.player.smallArea.area) {
                    enemyGuideObject = null;
                    if (rigidBody.getCenterOfMassPosition().x >= game.gameWorld.player.rigidBody.getCenterOfMassPosition().x) {
                        lookingRight = false;
                        weapon.lookingRight = false;
                    } else {
                        lookingRight = true;
                        weapon.lookingRight = true;
                    }
                    if (Math.abs(rigidBody.getCenterOfMassPosition().x - game.gameWorld.player.rigidBody.getCenterOfMassPosition().x) < 1) {
                        stopToAttack = true;
                    } else {
                        stopToAttack = false;
                    }
                } else {
                    if (playerLastBigArea != game.gameWorld.player.bigArea.area || playerLastSmallArea != game.gameWorld.player.smallArea.area ||
                            lastBigArea != bigArea.area || lastSmallArea != smallArea.area || stuck) {
                        playerLastBigArea = game.gameWorld.player.bigArea.area;
                        playerLastSmallArea = game.gameWorld.player.smallArea.area;
                        lastBigArea = bigArea.area;
                        lastSmallArea = smallArea.area;
                        enemyGuideObject = null;
                        stuck = false;
                        stuckTimer = 0;
                        if (bigArea.area == game.gameWorld.player.bigArea.area) {
                            for (i = 0; i < game.gameWorld.bigAreaManagers.size; i++) {
                                if (game.gameWorld.bigAreaManagers.get(i).bigAreaId == bigArea.area) {
                                    for (j = 0; j < game.gameWorld.bigAreaManagers.get(i).enemyGuides.size; j++) {
                                        if (Math.abs(rigidBody.getCenterOfMassPosition().y - game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).y) < 0.5f) {
                                            for (k = 0; k < game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).toSmallAreas.size; k++) {
                                                if (game.gameWorld.player.smallArea.area == game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).toSmallAreas.get(k)) {
                                                    enemyGuideObject = game.gameWorld.isObjectClosestToEnemy(this, enemyGuideObject, game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            for (i = 0; i < game.gameWorld.bigAreaManagers.size; i++) {
                                if (game.gameWorld.bigAreaManagers.get(i).bigAreaId == bigArea.area) {
                                    for (j = 0; j < game.gameWorld.bigAreaManagers.get(i).enemyGuides.size; j++) {
                                        if (Math.abs(rigidBody.getCenterOfMassPosition().y - game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).y) < 0.5f) {
                                            for (k = 0; k < game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).toBigAreas.size; k++) {
                                                if (game.gameWorld.player.bigArea.area == game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).toBigAreas.get(k)) {
                                                    enemyGuideObject = game.gameWorld.isObjectClosestToEnemy(this, enemyGuideObject, game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (enemyGuideObject != null) {
                            if (rigidBody.getCenterOfMassPosition().x >= enemyGuideObject.x) {
                                lookingRight = false;
                                weapon.lookingRight = false;
                            } else {
                                lookingRight = true;
                                weapon.lookingRight = true;
                            }
                        }
                    }
                }
            }

            if (enemyGuideObject instanceof EnemyJumpObject) {
                if (Math.abs(rigidBody.getCenterOfMassPosition().x - enemyGuideObject.x) < 0.1f) {
                    lockUpMovement = false;
                    lockUpMovementTimer = 0;
                    jumped = true;
                    jumpedTimer = 0;
                    if ((((EnemyJumpObject) enemyGuideObject).rightJump)) {
                        lookingRight = (((EnemyJumpObject) enemyGuideObject).rightJump);
                        weapon.lookingRight = (((EnemyJumpObject) enemyGuideObject).rightJump);
                    } else {
                        lookingRight = false;
                        weapon.lookingRight = false;
                    }
                    enemyGuideObject = null;
                    velocity.set(rigidBody.getLinearVelocity().x, 8.8f, 0);
                    rigidBody.setLinearVelocity(velocity);
                }
            }

            if (lookingRight) {
                i = 1;
            } else {
                i = -1;
            }
            movementRayFrom.set(
                    rigidBody.getCenterOfMassPosition().x,
                    rigidBody.getCenterOfMassPosition().y - 0.6f,
                    rigidBody.getCenterOfMassPosition().z);
            movementRayTo.set(
                    rigidBody.getCenterOfMassPosition().x + i * 0.5f,
                    rigidBody.getCenterOfMassPosition().y - 0.6f,
                    rigidBody.getCenterOfMassPosition().z);
            movementCallback.setCollisionObject(null);
            movementCallback.setClosestHitFraction(1);
            movementCallback.setRayFromWorld(movementRayFrom);
            movementCallback.setRayToWorld(movementRayTo);
            game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
            if (movementCallback.hasHit() && movementCallback.getCollisionObject().userData instanceof Enemy && movementCallback.getCollisionObject().userData != this && (((Enemy) movementCallback.getCollisionObject().userData).lookingRight == lookingRight) && !(((Enemy) movementCallback.getCollisionObject().userData).inAir)) {
                stopToWait = true;
            } else {
                stopToWait = false;
            }

            if (game.gameWorld.player.dead) {
                stopToWait = true;
            }

            if (!initStand) {
                if (inAir) {
                    if (lookingRight) {
                        if (!bodyState.equals(BodyState.AIR_STAND)) {
                            bodyState = BodyState.AIR_STAND;
                            animationController.setAnimation("Armature|air-stand", -1,
                                    1, animationListener);
                            if (weapon.animateOnMoving) {
                                weapon.animationController.setAnimation("Armature|air", -1);
                            }
                        }
                    } else {
                        if (!bodyState.equals(BodyState.AIR_STAND)) {
                            bodyState = BodyState.AIR_STAND;
                            animationController.setAnimation("Armature|air-stand", -1,
                                    1, animationListener);
                            if (weapon.animateOnMoving) {
                                weapon.animationController.setAnimation("Armature|air", -1);
                            }
                        }
                    }
                } else {
                    if (stopToAttack) {
                        if (!bodyState.equals(BodyState.MELEE_ATTACK)) {
                            bodyState = BodyState.MELEE_ATTACK;
                            animationController.setAnimation("Armature|stand", -1,
                                    1, animationListener);
                            if (weapon.animateOnMoving) {
                                weapon.animationController.setAnimation("Armature|use", -1);
                            }
                        }
                    } else if (stopToWait) {
                        if (!bodyState.equals(BodyState.STAND)) {
                            bodyState = BodyState.STAND;
                            animationController.setAnimation("Armature|stand", -1,
                                    1, animationListener);
                            if (weapon.animateOnMoving) {
                                weapon.animationController.setAnimation("Armature|stand", -1);
                            }
                        }
                    } else {
                        if (lookingRight) {
                            if (!bodyState.equals(BodyState.STAND_MOVE_RIGHT)) {
                                bodyState = BodyState.STAND_MOVE_RIGHT;
                                animationController.setAnimation("Armature|walk", -1,
                                        0.75f, animationListener);
                                if (weapon.animateOnMoving) {
                                    weapon.animationController.setAnimation("Armature|moving", -1,
                                            0.75f, animationListener);
                                }
                            }
                        } else {
                            if (!bodyState.equals(BodyState.STAND_MOVE_LEFT)) {
                                bodyState = BodyState.STAND_MOVE_LEFT;
                                animationController.setAnimation("Armature|walk", -1,
                                        0.75f, animationListener);
                                if (weapon.animateOnMoving) {
                                    weapon.animationController.setAnimation("Armature|moving", -1,
                                            0.75f, animationListener);
                                }
                            }
                        }
                    }
                }

                if (inAir) {
                    if (lookingRight) {
                        velocity.set(1.3f, rigidBody.getLinearVelocity().y, 0);
                        rigidBody.setLinearVelocity(velocity);
                    } else {
                        velocity.set(-1.3f, rigidBody.getLinearVelocity().y, 0);
                        rigidBody.setLinearVelocity(velocity);
                    }
                } else {
                    if (stopToWait || stopToAttack) {
                        velocity.set(0, rigidBody.getLinearVelocity().y, 0);
                        rigidBody.setLinearVelocity(velocity);
                    } else {
                        if (lookingRight) {
                            velocity.set(1.5f, rigidBody.getLinearVelocity().y, 0);
                            rigidBody.setLinearVelocity(velocity);
                        } else {
                            velocity.set(-1.5f, rigidBody.getLinearVelocity().y, 0);
                            rigidBody.setLinearVelocity(velocity);
                        }
                    }
                }
            }

            if (lookingRight) {
                if (!enemyBodyToRight) {
                    enemyBodyToRight = true;
                    enemyPosition.set(rigidBody.getCenterOfMassPosition());
                    enemyQuaternion.set(Vector3.Y, 0);
                    enemyTransform.set(enemyPosition, enemyQuaternion, enemyScale);
                    rigidBody.setCenterOfMassTransform(enemyTransform);
                }
            } else {
                if (enemyBodyToRight) {
                    enemyBodyToRight = false;
                    enemyPosition.set(rigidBody.getCenterOfMassPosition());
                    enemyQuaternion.set(Vector3.Y, 180);
                    enemyTransform.set(enemyPosition, enemyQuaternion, enemyScale);
                    rigidBody.setCenterOfMassTransform(enemyTransform);
                }
            }
            inAir = true;
            if (rigidBody.getLinearVelocity().y <= 0.01f) {
                if (lookingRight) {
                    i = -1;
                } else {
                    i = 1;
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
                }
            }
            if (!lockUpMovement) {
                lockUpMovementTimer += deltaTime;
            }
            if (!inAir) {
                if (lockUpMovementTimer > 0.5f) {
                    lockUpMovement = true;
                }
            }
            if (jumped) {
                jumpedTimer += deltaTime;
                if (jumpedTimer > 0.5f) {
                    jumped = false;
                }
            }
        }
    }
}