package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyDropObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyGuideObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyJumpObject;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.AnimationState;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class Enemy extends Person {

    private boolean stopToWait;
    private boolean stopToAttack;
    private boolean stuck;
    private float stuckTimer;
    private boolean initStand;
    private float initTimer;
    private EnemyGuideObject enemyGuideObject;
    private float deadTimer;
    private int lastBigArea;
    private int lastSmallArea;
    private int playerLastBigArea;
    private int playerLastSmallArea;

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
        dimensions.set(2f, 2f, 1);
    }

    public void init(float x, float y, float z, float angle, int health) {
        super.init(x, y, z, angle);
        this.health = health;
        maxHealth = health;
        visibleToPlayer = false;

        stopToWait = false;
        stopToAttack = false;
        initStand = true;
        initTimer = 0;
        stuck = false;
        stuckTimer = 0;
        enemyGuideObject = null;

        destroyBody = false;
        deadTimer = 0;

        lastBigArea = 0;
        lastSmallArea = 0;
        playerLastBigArea = 0;
        playerLastSmallArea = 0;
    }

    @Override
    void updateMovement(float deltaTime) {
        super.updateMovement(deltaTime);
        if (initStand) {
            initTimer += deltaTime;
            if (initTimer > 1) {
                initStand = false;
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

        guide();
        jumpIfNeeded();

        movementRayFrom.set(
                rigidBody.getCenterOfMassPosition().x,
                rigidBody.getCenterOfMassPosition().y - 0.6f,
                rigidBody.getCenterOfMassPosition().z);
        movementRayTo.set(
                rigidBody.getCenterOfMassPosition().x + (lookingRight ? 1 : -1) * 0.5f,
                rigidBody.getCenterOfMassPosition().y - 0.6f,
                rigidBody.getCenterOfMassPosition().z);
        movementCallback.setCollisionObject(null);
        movementCallback.setClosestHitFraction(1);
        movementCallback.setRayFromWorld(movementRayFrom);
        movementCallback.setRayToWorld(movementRayTo);
        game.gameWorld.dynamicsWorld.rayTest(movementRayFrom, movementRayTo, movementCallback);
        stopToWait = movementCallback.hasHit() && movementCallback.getCollisionObject().userData instanceof Enemy && movementCallback.getCollisionObject().userData != this && (((Enemy) movementCallback.getCollisionObject().userData).lookingRight == lookingRight) && !(((Enemy) movementCallback.getCollisionObject().userData).inAir);

        if (game.gameWorld.player.dead) {
            stopToWait = true;
        }

        if (!initStand) {
            setVelocity();
        }
        checkAnimation();
    }

    @Override
    void onDead(float deltaTime) {
        super.onDead(deltaTime);
        if (!onDeadThingsDone) {
            onDeadThingsDone = true;
            weapon.ownerDead = true;
            game.gameWorld.enemyManager.enemyDied();
            game.gameScreen.inGameHud.updateHud();
        }
        if (destroyBody) {
            deadTimer += deltaTime;
        }
        if (deadTimer > Constants.ENEMY_VISIBLE_TIME_AFTER_DEAD_ON_GROUND) {
            free = true;
            weapon.free = true;
        }
    }

    private void guide() {
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
                stopToAttack = Math.abs(rigidBody.getCenterOfMassPosition().x - game.gameWorld.player.rigidBody.getCenterOfMassPosition().x) < 1;
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
                        for (int i = 0; i < game.gameWorld.bigAreaManagers.size; i++) {
                            if (game.gameWorld.bigAreaManagers.get(i).bigAreaId == bigArea.area) {
                                for (int j = 0; j < game.gameWorld.bigAreaManagers.get(i).enemyGuides.size; j++) {
                                    if (Math.abs(rigidBody.getCenterOfMassPosition().y - game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).y) < 0.5f) {
                                        if (game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j) instanceof EnemyDropObject) {
                                            if (((EnemyDropObject) game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j)).approachableFromRight && rigidBody.getCenterOfMassPosition().x < game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).x) {
                                                continue;
                                            }
                                            if (!(((EnemyDropObject) game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j)).approachableFromRight) && rigidBody.getCenterOfMassPosition().x > game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).x) {
                                                continue;
                                            }
                                        }
                                        for (int k = 0; k < game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).toSmallAreas.size; k++) {
                                            if (game.gameWorld.player.smallArea.area == game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).toSmallAreas.get(k)) {
                                                enemyGuideObject = game.gameWorld.isObjectClosestToEnemy(this, enemyGuideObject, game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < game.gameWorld.bigAreaManagers.size; i++) {
                            if (game.gameWorld.bigAreaManagers.get(i).bigAreaId == bigArea.area) {
                                for (int j = 0; j < game.gameWorld.bigAreaManagers.get(i).enemyGuides.size; j++) {
                                    if (Math.abs(rigidBody.getCenterOfMassPosition().y - game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).y) < 0.5f) {
                                        if (game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j) instanceof EnemyDropObject) {
                                            if (((EnemyDropObject) game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j)).approachableFromRight && rigidBody.getCenterOfMassPosition().x < game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).x) {
                                                continue;
                                            }
                                            if (!(((EnemyDropObject) game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j)).approachableFromRight) && rigidBody.getCenterOfMassPosition().x > game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).x) {
                                                continue;
                                            }
                                        }
                                        for (int k = 0; k < game.gameWorld.bigAreaManagers.get(i).enemyGuides.get(j).toBigAreas.size; k++) {
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
    }

    private void jumpIfNeeded() {
        if (enemyGuideObject instanceof EnemyJumpObject) {
            if (Math.abs(rigidBody.getCenterOfMassPosition().x - enemyGuideObject.x) < 0.1f) {
                if ((((EnemyJumpObject) enemyGuideObject).jumpToRight)) {
                    lookingRight = (((EnemyJumpObject) enemyGuideObject).jumpToRight);
                    weapon.lookingRight = (((EnemyJumpObject) enemyGuideObject).jumpToRight);
                } else {
                    lookingRight = false;
                    weapon.lookingRight = false;
                }
                enemyGuideObject = null;
                jump();
            }
        }
    }

    private void checkAnimation() {
        if (initStand) {
            setAnimation(AnimationState.STAND);
        } else {
            if (inAir) {
                setAnimation(AnimationState.AIR);
            } else {
                if (stopToWait || stopToAttack) {
                    setAnimation(AnimationState.STAND);
                } else {
                    setAnimation(AnimationState.WALK);
                }
            }
        }
    }

    private void setVelocity() {
        xVelocity = 1.5f;
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
                    velocity.set(xVelocity, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                } else {
                    velocity.set(-xVelocity, rigidBody.getLinearVelocity().y, 0);
                    rigidBody.setLinearVelocity(velocity);
                }
            }
        }
    }
}