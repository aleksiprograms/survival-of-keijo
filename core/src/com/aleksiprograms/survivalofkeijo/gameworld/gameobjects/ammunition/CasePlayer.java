package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class CasePlayer extends Case {

    public CasePlayer(TheGame game) {
        super(game, new ModelInstance(game.getAssetManager().get(
                Constants.MODEL_AMMUNITION_CASE_PLAYER, Model.class)));
    }
}