package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameeffects.AmmunitionHitGrass;
import com.aleksiprograms.survivalofkeijo.gameworld.gameeffects.AmmunitionHitGround;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsInfluencer;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParticleEffectManager implements RenderableProvider {

    private TheGame game;
    private Array<ParticleBatch<?>> batches;
    private Array<ParticleEffect> effects;
    private Vector3 effectPosition;
    private Quaternion effectQuaternion;
    private Vector3 effectScale;
    private Matrix4 effectTransform;

    public ParticleEffectManager(TheGame game) {
        this.game = game;
        batches = new Array<ParticleBatch<?>>();
        effects = new Array<ParticleEffect>();
        effectPosition = new Vector3(0, 0, 0);
        effectQuaternion = new Quaternion(Vector3.Z, 0);
        effectScale = new Vector3(1, 1, 1);
        effectTransform = new Matrix4(effectPosition, effectQuaternion, effectScale);
    }

    public void add(ParticleBatch<?> batch) {
        batches.add(batch);
    }

    public void add(ParticleEffect effect, Vector3 position) {
        effect.init();
        effectPosition.set(position);
        effectTransform.set(effectPosition, effectQuaternion, effectScale);
        effect.setTransform(effectTransform);
        effect.start();
        effects.add(effect);
    }

    public void add(ParticleEffect effect, Vector3 position, float angleLow, float angleHigh) {
        //particleEffects.add(particleEffect);
        effect.init();
        effectPosition.set(position);
        effectTransform.set(effectPosition, effectQuaternion, effectScale);
        effect.setTransform(effectTransform);
        for (int i = 0; i < effect.getControllers().size; i++) {
            DynamicsInfluencer di = effect.getControllers().get(i)
                    .findInfluencer(DynamicsInfluencer.class);
            DynamicsModifier dm;
            for (int j = 0; j < di.velocities.size; j++) {
                dm = (DynamicsModifier) di.velocities.get(j);
                ((DynamicsModifier.PolarAcceleration) dm).phiValue.setHigh(angleLow, angleHigh);
            }
        }
        effect.start();
        effects.add(effect);
    }

    public void clear() {
        for (int i = 0; i < effects.size; i++) {
            freeEffect(effects.get(i));
        }
        effects.clear();
    }

    public void update() {
        for (int i = 0; i < effects.size; i++) {
            if (effects.get(i).isComplete()) {
                freeEffect(effects.get(i));
                effects.removeIndex(i);
            } else {
                effects.get(i).update();
            }
        }
    }

    private void freeEffect(ParticleEffect particleEffect) {
        if (particleEffect instanceof AmmunitionHitGround) {
            game.getGameObjectPools().getAmmunitionGroundHitPool().free(particleEffect);
        } else if (particleEffect instanceof AmmunitionHitGrass) {
            game.getGameObjectPools().getAmmunitionGrassHitPool().free(particleEffect);
        }
    }

    public void begin() {
        for (ParticleBatch<?> batch : batches)
            batch.begin();
    }

    public void draw() {
        for (ParticleEffect effect : effects) {
            effect.draw();
        }
    }

    public void end() {
        for (ParticleBatch<?> batch : batches)
            batch.end();
    }

    @Override
    public void getRenderables(Array<Renderable> renderables, Pool<Renderable> pool) {
        for (ParticleBatch<?> batch : batches)
            batch.getRenderables(renderables, pool);
    }

    public Array<ParticleBatch<?>> getBatches() {
        return batches;
    }
}