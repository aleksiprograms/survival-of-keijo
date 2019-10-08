package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Hands extends Weapon {

    public Hands(TheGame game) {
        super(game, new ModelInstance(game.assetManager.get(Constants.MODEL_WEAPON_PISTOL_PLAYER, Model.class)), 0.805f, 0.073f, 0f, false);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void use(float x1, float y1, float x2, float y2) {
        if (canUse) { }
    }
}