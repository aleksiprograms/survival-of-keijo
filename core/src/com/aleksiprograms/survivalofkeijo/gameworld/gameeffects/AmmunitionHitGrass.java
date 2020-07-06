package com.aleksiprograms.survivalofkeijo.gameworld.gameeffects;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;

public class AmmunitionHitGrass extends ParticleEffect {

    public AmmunitionHitGrass(TheGame game) {
        super(game.getAssetManager().get(
                Constants.PFX_AMMUNITION_GRASS_HIT, ParticleEffect.class));
    }
}