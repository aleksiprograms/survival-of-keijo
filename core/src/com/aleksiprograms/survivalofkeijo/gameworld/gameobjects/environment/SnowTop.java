package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class SnowTop extends Top {

    public SnowTop(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_WALKABLE_SNOW_TOP, Model.class)));
        rigidBody.userData = this;
    }
}