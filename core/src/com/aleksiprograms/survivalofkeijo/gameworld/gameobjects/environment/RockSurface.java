package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class RockSurface extends Surface {

    public RockSurface(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_WALKABLE_ROCK_SURFACE, Model.class)));

        rigidBody.userData = this;
    }
}