package com.aleksiprograms.survivalofkeijo.gameworld.gameeffects;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;

public class AmmunitionHitGrass128px extends ParticleEffect {

    public AmmunitionHitGrass128px(TheGame game) {
        super(game.assetManager.get(Constants.PFX_AMMUNITION_GRASS_HIT_128PX, ParticleEffect.class));
    }
}