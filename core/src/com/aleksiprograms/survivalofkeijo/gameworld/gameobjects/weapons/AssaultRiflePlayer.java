package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class AssaultRiflePlayer extends AssaultRifle {

    public AssaultRiflePlayer(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_WEAPON_ASSAULT_RIFLE_PLAYER, Model.class)));
    }
}