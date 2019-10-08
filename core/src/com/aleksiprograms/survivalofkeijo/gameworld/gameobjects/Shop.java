package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Shop extends Building {

    public Shop(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_PERSON_PLAYER, Model.class)));
    }
}