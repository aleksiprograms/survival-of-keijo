package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Shotgun extends Weapon {

    public Shotgun(TheGame game) {
        super(game, new ModelInstance(game.assetManager.get(Constants.MODEL_WEAPON_PISTOL_PLAYER, Model.class)), 0.9f, 0.05f, 0f, false);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void use(float x1, float y1, float x2, float y2) {
        if (canUse) {
            Vector3 velocity = new Vector3(
                    x2 - x1,
                    y2 - y1,
                    0
            );
            velocity = velocity.nor();
            velocity = velocity.scl(10);

            Vector3 originalVelocity = new Vector3();
            originalVelocity.set(velocity);

            for (int i = -4; i < 5; i++) {
                velocity.set(originalVelocity);
                velocity.rotate(i*0.5f, 0, 0, 1);
                game.gameWorld.addAmmunition(game.gamePools.shotPool.obtain(), weaponData.ammoDamage, x1 + MathUtils.random(-0.1f, 0.1f), y1 + MathUtils.random(-0.1f, 0.1f), 0, MathUtils.radiansToDegrees * MathUtils.atan2(y2 - y1, x2 - x1), velocity, playerWeapon ? Constants.CATEGORY_WEAPON_PLAYER : Constants.CATEGORY_WEAPON_ENEMY, playerWeapon ? Constants.MASK_WEAPON_PLAYER : Constants.MASK_WEAPON_ENEMY);
            }
            used();
        }
    }
}