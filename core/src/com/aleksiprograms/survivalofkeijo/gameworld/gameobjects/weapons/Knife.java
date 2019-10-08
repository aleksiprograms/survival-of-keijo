package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;

public class Knife extends Weapon {

    public Knife(TheGame game) {
        super(game, new ModelInstance(game.assetManager.get(Constants.MODEL_WEAPON_KNIFE_ENEMY, Model.class)), 0.3f, 0, 0f, true);
    }

    @Override
    public void use(float x1, float y1, float x2, float y2) {
        if (canUse) {
            if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < 1) {
                animationController.setAnimation("Armature|use", 1, 0.75f, new AnimationController.AnimationListener() {
                    @Override
                    public void onEnd(AnimationController.AnimationDesc animation) {
                        animationController.setAnimation("Armature|moving", -1,
                                0.75f, animationListener);
                    }

                    @Override
                    public void onLoop(AnimationController.AnimationDesc animation) {

                    }
                });
                used();
            }
        }
    }
}