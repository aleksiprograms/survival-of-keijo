package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Case;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

public class WeaponWithCase<B extends Case> extends WeaponWithAmmo {

    public Pool<B> casePool;
    public float caseOffsetX;
    public float caseOffsetY;
    public float caseOffsetZ;
    public Vector3 caseVelocity;
    public Vector3 caseAngularVelocity;

    public WeaponWithCase(final TheGame game, ModelInstance modelInstance, float ammoOffsetX, float ammoOffsetY, float ammoOffsetZ, float caseOffsetX, float caseOffsetY, float caseOffsetZ) {
        super(game, modelInstance, ammoOffsetX, ammoOffsetY, ammoOffsetZ);
        this.caseOffsetX = caseOffsetX;
        this.caseOffsetY = caseOffsetY;
        this.caseOffsetZ = caseOffsetZ;
        caseVelocity = new Vector3();
        caseAngularVelocity = new Vector3();
    }

    @Override
    public void use(float x1, float y1, float x2, float y2) {
        super.use(x1, y1, x2, y2);
        if (doThingsAtUse) {
            if (lookingRight) {
                caseVelocity.set(-MathUtils.random(2f, 3f) * MathUtils.sinDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.cosDeg(weaponAngleDeg), MathUtils.random(2f, 3f) * MathUtils.cosDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.sinDeg(weaponAngleDeg), MathUtils.random(0.3f, 0.6f));
                caseAngularVelocity.set(0, -MathUtils.random(8f, 12f), MathUtils.random(3f, 8f));
            } else {
                caseVelocity.set(MathUtils.random(2f, 3f) * MathUtils.sinDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.cosDeg(weaponAngleDeg), -MathUtils.random(2f, 3f) * MathUtils.cosDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.sinDeg(weaponAngleDeg), -MathUtils.random(0.3f, 0.6f));
                caseAngularVelocity.set(0, MathUtils.random(8f, 12f), -MathUtils.random(3f, 8f));
            }
            game.gameWorld.addCase(
                    casePool.obtain(),
                    x1 + ((caseOffsetX - ammoOffsetX) * MathUtils.cosDeg(weaponAngleDeg) - (caseOffsetY - ammoOffsetY) * MathUtils.sinDeg(weaponAngleDeg)),
                    y1 + ((caseOffsetY - ammoOffsetY) * MathUtils.cosDeg(weaponAngleDeg) + (caseOffsetX - ammoOffsetX) * MathUtils.sinDeg(weaponAngleDeg)),
                    lookingRight ? caseOffsetZ : -caseOffsetZ,
                    weaponAngleDeg,
                    caseVelocity,
                    caseAngularVelocity);
        }
    }

    @Override
    public void init(WeaponData weaponData, Person owner, Person target, boolean playerWeapon) {
        super.init(weaponData, owner, target, playerWeapon);
        if (playerWeapon) {
            this.casePool = (Pool<B>) game.gamePools.casePlayerPool;
        } else {
            this.casePool = (Pool<B>) game.gamePools.caseEnemyPool;
        }
    }
}