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
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_PERSON_ENEMY, Model.class)),
                new btBoxShape(new Vector3(0.2f, 0.9f, 0.2f)),
                new BodyDef.BodyDefBuilder()
                        .mass(80)
                        .linearDamping(0.1f)
                        .categoryBits(Constants.CATEGORY_ENEMY)
                        .maskBits(Constants.MASK_ENEMY)
                        .useMotionState(false)
                        .build());

        getRigidBody().userData = this;
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

        if (Math.abs(getRigidBody().getLinearVelocity().len())
                < 0.1f && (bigArea.getArea() != game.getGameWorld().getPlayer().bigArea.getArea()
                || smallArea.getArea() != game.getGameWorld().getPlayer().smallArea.getArea())) {
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
                getRigidBody().getCenterOfMassPosition().x,
                getRigidBody().getCenterOfMassPosition().y - 0.6f,
                getRigidBody().getCenterOfMassPosition().z);
        movementRayTo.set(
                getRigidBody().getCenterOfMassPosition().x + (lookingRight ? 1 : -1) * 0.5f,
                getRigidBody().getCenterOfMassPosition().y - 0.6f,
                getRigidBody().getCenterOfMassPosition().z);
        movementCallback.setCollisionObject(null);
        movementCallback.setClosestHitFraction(1);
        movementCallback.setRayFromWorld(movementRayFrom);
        movementCallback.setRayToWorld(movementRayTo);
        game.getGameWorld().getDynamicsWorld().rayTest(
                movementRayFrom, movementRayTo, movementCallback);
        stopToWait = movementCallback.hasHit()
                && movementCallback.getCollisionObject().userData instanceof Enemy
                && movementCallback.getCollisionObject().userData != this
                && (((Enemy) movementCallback.getCollisionObject().userData).lookingRight
                == lookingRight)
                && !(((Enemy) movementCallback.getCollisionObject().userData).inAir);

        if (game.getGameWorld().getPlayer().dead) {
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
            weapon.setOwnerDead(true);
            game.getGameWorld().getEnemyManager().enemyDied();
            game.getGameScreen().getInGameHud().updateData();
        }
        if (destroyBody) {
            deadTimer += deltaTime;
        }
        if (deadTimer > Constants.ENEMY_VISIBLE_TIME_AFTER_DEAD_ON_GROUND) {
            free = true;
            weapon.setFree(true);
        }
    }

    private void guide() {
        if (!(inAir || jumped)) {
            stopToAttack = false;
            if (bigArea.getArea() == game.getGameWorld().getPlayer().bigArea.getArea()
                    && smallArea.getArea() == game.getGameWorld().getPlayer().smallArea.getArea()) {
                enemyGuideObject = null;
                if (getRigidBody().getCenterOfMassPosition().x
                        >= game.getGameWorld().getPlayer().getRigidBody()
                        .getCenterOfMassPosition().x) {
                    lookingRight = false;
                    weapon.setLookingRight(false);
                } else {
                    lookingRight = true;
                    weapon.setLookingRight(true);
                }
                stopToAttack = Math.abs(getRigidBody().getCenterOfMassPosition().x
                        - game.getGameWorld().getPlayer().getRigidBody()
                        .getCenterOfMassPosition().x) < 1;
            } else {
                if (playerLastBigArea != game.getGameWorld().getPlayer().bigArea.getArea()
                        || playerLastSmallArea
                        != game.getGameWorld().getPlayer().smallArea.getArea()
                        || lastBigArea != bigArea.getArea()
                        || lastSmallArea != smallArea.getArea()
                        || stuck) {
                    playerLastBigArea = game.getGameWorld().getPlayer().bigArea.getArea();
                    playerLastSmallArea = game.getGameWorld().getPlayer().smallArea.getArea();
                    lastBigArea = bigArea.getArea();
                    lastSmallArea = smallArea.getArea();
                    enemyGuideObject = null;
                    stuck = false;
                    stuckTimer = 0;
                    if (bigArea.getArea() == game.getGameWorld().getPlayer().bigArea.getArea()) {
                        for (int i = 0; i < game.getGameWorld().getBigAreaManagers().size; i++) {
                            if (game.getGameWorld().getBigAreaManagers().get(i).getBigAreaID()
                                    == bigArea.getArea()) {
                                for (int j = 0; j < game.getGameWorld().getBigAreaManagers()
                                        .get(i).getEnemyGuides().size; j++) {
                                    if (Math.abs(getRigidBody().getCenterOfMassPosition().y
                                            - game.getGameWorld().getBigAreaManagers().get(i)
                                            .getEnemyGuides().get(j).getY()) < 0.5f) {
                                        if (game.getGameWorld().getBigAreaManagers().get(i)
                                                .getEnemyGuides().get(j) instanceof EnemyDropObject) {
                                            if (((EnemyDropObject) game.getGameWorld().getBigAreaManagers()
                                                    .get(i).getEnemyGuides().get(j)).isApproachableFromRight()
                                                    && getRigidBody().getCenterOfMassPosition().x
                                                    < game.getGameWorld().getBigAreaManagers().get(i)
                                                    .getEnemyGuides().get(j).getX()) {
                                                continue;
                                            }
                                            if (!(((EnemyDropObject) game.getGameWorld().getBigAreaManagers()
                                                    .get(i).getEnemyGuides().get(j)).isApproachableFromRight())
                                                    && getRigidBody().getCenterOfMassPosition().x
                                                    > game.getGameWorld().getBigAreaManagers().get(i)
                                                    .getEnemyGuides().get(j).getX()) {
                                                continue;
                                            }
                                        }
                                        for (int k = 0; k < game.getGameWorld().getBigAreaManagers()
                                                .get(i).getEnemyGuides().get(j).getToSmallAreas().size; k++) {
                                            if (game.getGameWorld().getPlayer().smallArea.getArea()
                                                    == game.getGameWorld().getBigAreaManagers().get(i)
                                                    .getEnemyGuides().get(j).getToSmallAreas().get(k)) {
                                                enemyGuideObject = game.getGameWorld()
                                                        .isObjectClosestToEnemy(
                                                                this, enemyGuideObject,
                                                                game.getGameWorld().getBigAreaManagers()
                                                                        .get(i).getEnemyGuides().get(j));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getGameWorld().getBigAreaManagers().size; i++) {
                            if (game.getGameWorld().getBigAreaManagers().get(i).getBigAreaID()
                                    == bigArea.getArea()) {
                                for (int j = 0; j < game.getGameWorld().getBigAreaManagers()
                                        .get(i).getEnemyGuides().size; j++) {
                                    if (Math.abs(getRigidBody().getCenterOfMassPosition().y
                                            - game.getGameWorld().getBigAreaManagers().get(i)
                                            .getEnemyGuides().get(j).getY()) < 0.5f) {
                                        if (game.getGameWorld().getBigAreaManagers().get(i)
                                                .getEnemyGuides().get(j) instanceof EnemyDropObject) {
                                            if (((EnemyDropObject) game.getGameWorld().getBigAreaManagers()
                                                    .get(i).getEnemyGuides().get(j)).isApproachableFromRight()
                                                    && getRigidBody().getCenterOfMassPosition().x
                                                    < game.getGameWorld().getBigAreaManagers().get(i)
                                                    .getEnemyGuides().get(j).getX()) {
                                                continue;
                                            }
                                            if (!(((EnemyDropObject) game.getGameWorld().getBigAreaManagers()
                                                    .get(i).getEnemyGuides().get(j)).isApproachableFromRight())
                                                    && getRigidBody().getCenterOfMassPosition().x
                                                    > game.getGameWorld().getBigAreaManagers().get(i)
                                                    .getEnemyGuides().get(j).getX()) {
                                                continue;
                                            }
                                        }
                                        for (int k = 0; k < game.getGameWorld().getBigAreaManagers()
                                                .get(i).getEnemyGuides().get(j).getToBigAreas().size; k++) {
                                            if (game.getGameWorld().getPlayer().bigArea.getArea()
                                                    == game.getGameWorld().getBigAreaManagers().get(i)
                                                    .getEnemyGuides().get(j).getToBigAreas().get(k)) {
                                                enemyGuideObject = game.getGameWorld()
                                                        .isObjectClosestToEnemy(
                                                                this, enemyGuideObject,
                                                                game.getGameWorld().getBigAreaManagers()
                                                                        .get(i).getEnemyGuides().get(j));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (enemyGuideObject != null) {
                        if (getRigidBody().getCenterOfMassPosition().x >= enemyGuideObject.getX()) {
                            lookingRight = false;
                            weapon.setLookingRight(false);
                        } else {
                            lookingRight = true;
                            weapon.setLookingRight(true);
                        }
                    }
                }
            }
        }
    }

    private void jumpIfNeeded() {
        if (enemyGuideObject instanceof EnemyJumpObject) {
            if (Math.abs(getRigidBody().getCenterOfMassPosition().x
                    - enemyGuideObject.getX()) < 0.1f) {
                if ((((EnemyJumpObject) enemyGuideObject).isJumpToRight())) {
                    lookingRight = (((EnemyJumpObject) enemyGuideObject).isJumpToRight());
                    weapon.setLookingRight(
                            (((EnemyJumpObject) enemyGuideObject).isJumpToRight()));
                } else {
                    lookingRight = false;
                    weapon.setLookingRight(false);
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
                velocity.set(1.3f, getRigidBody().getLinearVelocity().y, 0);
                getRigidBody().setLinearVelocity(velocity);
            } else {
                velocity.set(-1.3f, getRigidBody().getLinearVelocity().y, 0);
                getRigidBody().setLinearVelocity(velocity);
            }
        } else {
            if (stopToWait || stopToAttack) {
                velocity.set(0, getRigidBody().getLinearVelocity().y, 0);
                getRigidBody().setLinearVelocity(velocity);
            } else {
                if (lookingRight) {
                    velocity.set(xVelocity, getRigidBody().getLinearVelocity().y, 0);
                    getRigidBody().setLinearVelocity(velocity);
                } else {
                    velocity.set(-xVelocity, getRigidBody().getLinearVelocity().y, 0);
                    getRigidBody().setLinearVelocity(velocity);
                }
            }
        }
    }
}