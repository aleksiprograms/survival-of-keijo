package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class RockV5 extends Rock {

    public RockV5(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_WALKABLE_ROCK_V5, Model.class)),
                new btBoxShape(new Vector3(0.25f, 1.3f, 0.75f)));
    }
}