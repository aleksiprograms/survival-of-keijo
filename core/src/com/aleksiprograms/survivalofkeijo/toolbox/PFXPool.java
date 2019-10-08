package com.aleksiprograms.survivalofkeijo.toolbox;

import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.utils.Pool;

public class PFXPool extends Pool<ParticleEffect> {

    public ParticleEffect sourceParticleEffect;

    public PFXPool(ParticleEffect sourceParticleEffect, int initialCapacity, int max) {
        super(initialCapacity, max);
        this.sourceParticleEffect = sourceParticleEffect;
    }

    @Override
    public void free(ParticleEffect particleEffect) {
        particleEffect.reset();
        super.free(particleEffect);
    }

    @Override
    protected ParticleEffect newObject() {
        return sourceParticleEffect.copy();
    }
}