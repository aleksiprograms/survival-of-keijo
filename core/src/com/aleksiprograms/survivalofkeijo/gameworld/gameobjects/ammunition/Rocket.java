package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Enemy;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.aleksiprograms.survivalofkeijo.resources.Constants;

public class Rocket extends Ammunition {

    private Vector3 explosionRayFrom;
    private Vector3 explosionRayTo;
    private ClosestRayResultCallback explosionCallback;
    private Vector3 vector;
    private float distance;

    public Rocket(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_AMMUNITION_BULLET_PLAYER, Model.class)),
                new btBoxShape(new Vector3(0.2f, 0.05f, 0.05f)));

        rigidBody.userData = this;
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
            game.particleEffectManager.add(game.gamePools.rocketExplosionPool.obtain(), hitPoint);

            explosionRayFrom.set(rigidBody.getCenterOfMassPosition());
            for (Enemy enemy : game.gameWorld.visibleEnemies) {
                explosionRayTo.set(enemy.rigidBody.getCenterOfMassPosition());
                explosionCallback.setCollisionObject(null);
                explosionCallback.setClosestHitFraction(1);
                explosionCallback.setRayFromWorld(explosionRayFrom);
                explosionCallback.setRayToWorld(explosionRayTo);
                game.gameWorld.dynamicsWorld.rayTest(explosionRayFrom, explosionRayTo, explosionCallback);
                if (!explosionCallback.hasHit()) {
                    vector.set(
                            explosionRayTo.x - explosionRayFrom.x,
                            explosionRayTo.y - explosionRayFrom.y,
                            explosionRayTo.z - explosionRayFrom.z
                    );
                    distance = vector.len2();
                    System.out.println("Distance = " + distance);
                    if (distance < 8) { //add distance attribute to explosive weapons (distance <= weaponData.explosiveDistance)
                        enemy.onExplosion(vector);
                    }
                    //vector = vector.nor();
                    //vector = vector.scl(20 / (distance / 300));
                    //vector.add(0,100,0);//do something else
                }
            }
        }
        super.onHit(hitPoint, hitObject);
    }
}