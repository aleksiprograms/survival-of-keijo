package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class CaseEnemy extends Case {

    public CaseEnemy(TheGame game) {
        super(game, new ModelInstance(game.assetManager.get(Constants.MODEL_AMMUNITION_CASE_ENEMY, Model.class)));
    }
}