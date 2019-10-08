package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class GroundH30 extends Ground {

    public GroundH30(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_WALKABLE_GROUND_H30, Model.class)),
                new btBoxShape(new Vector3(7.5f, 0.2f, 0.75f)));
    }
}