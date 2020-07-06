package com.aleksiprograms.survivalofkeijo.gameworld.gameeffects;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;

public class AmmunitionHitGround extends ParticleEffect {

    public AmmunitionHitGround(TheGame game) {
        super(game.getAssetManager().get(
                Constants.PFX_AMMUNITION_GROUND_HIT, ParticleEffect.class));
    }
}