package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class SnowH10 extends Snow {

    public SnowH10(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_WALKABLE_SNOW_H10, Model.class)),
                new btBoxShape(new Vector3(2.5f, 0.2f, 0.75f)));
    }
}