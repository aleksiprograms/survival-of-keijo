package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Grass;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Ground;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public abstract class Bullet extends Ammunition {

    public Bullet(TheGame game, ModelInstance modelInstance) {
        super(
                game,
                modelInstance,
                new btBoxShape(new Vector3(0.2f/2f, 0.125f/2f, 0.125f/2f)));
    }

    @Override
    public void onHit(Vector3 hitPoint, PhysicalObject hitObject) {
        if (!hit) {
            if (hitObject instanceof Ground) {
                game.particleEffectManager.add(game.gamePools.ammunitionGroundHitPool.obtain(), hitPoint);
            } else if (hitObject instanceof Grass) {
                game.particleEffectManager.add(game.gamePools.ammunitionGrassHitPool.obtain(), hitPoint);
            }
        }
        super.onHit(hitPoint, hitObject);
    }
}