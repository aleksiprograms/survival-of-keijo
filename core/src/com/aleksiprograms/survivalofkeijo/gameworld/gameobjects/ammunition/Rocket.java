package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Enemy;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class Rocket extends Ammunition {

    private Vector3 explosionRayFrom;
    private Vector3 explosionRayTo;
    private ClosestRayResultCallback explosionCallback;
    private Vector3 vector;
    private float distance;

    public Rocket(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_AMMUNITION_BULLET_PLAYER, Model.class)),
                new btBoxShape(new Vector3(0.2f, 0.05f, 0.05f)));

        getRigidBody().userData = this;
        explosionRayFrom = new Vector3();
        explosionRayTo = new Vector3();
        explosionCallback = new ClosestRayResultCallback(explosionRayFrom, explosionRayTo);
        explosionCallback.setCollisionFilterGroup(Constants.CATEGORY_PARTICLE);
        explosionCallback.setCollisionFilterMask(Constants.MASK_PARTICLE);
        vector = new Vector3();
    }

    @Override
    public void onHit(Vector3 hitPoint, PhysicalObject hitObject) {
        if (!hit) {
            game.getParticleEffectManager().add(
                    game.getGameObjectPools().getRocketExplosionPool().obtain(), hitPoint);

            explosionRayFrom.set(getRigidBody().getCenterOfMassPosition());
            for (Enemy enemy : game.getGameWorld().getVisibleEnemies()) {
                explosionRayTo.set(enemy.getRigidBody().getCenterOfMassPosition());
                explosionCallback.setCollisionObject(null);
                explosionCallback.setClosestHitFraction(1);
                explosionCallback.setRayFromWorld(explosionRayFrom);
                explosionCallback.setRayToWorld(explosionRayTo);
                game.getGameWorld().getDynamicsWorld().rayTest(
                        explosionRayFrom, explosionRayTo, explosionCallback);
                if (!explosionCallback.hasHit()) {
                    vector.set(
                            explosionRayTo.x - explosionRayFrom.x,
                            explosionRayTo.y - explosionRayFrom.y,
                            explosionRayTo.z - explosionRayFrom.z
                    );
                    distance = vector.len2();
                    if (distance < 8) {
                        enemy.onExplosion(vector);
                    }
                }
            }
        }
        super.onHit(hitPoint, hitObject);
    }
}