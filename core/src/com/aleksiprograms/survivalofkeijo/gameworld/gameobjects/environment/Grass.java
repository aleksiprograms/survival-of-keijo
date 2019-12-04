package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Grass extends Surface {

    public Grass(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_WALKABLE_GRASS, Model.class)));

        rigidBody.userData = this;
    }
}