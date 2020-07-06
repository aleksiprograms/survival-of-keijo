package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.weapondata.WeaponData;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public abstract class Pistol extends Weapon {

    public Pistol(final TheGame game, ModelInstance modelInstance) {
        super(
                game,
                modelInstance,
                0.768f + 0.1f,
                0.071f,
                0f,
                0.602f - 0.05f,
                0.071f,
                0f);
    }

    @Override
    public void init(
            WeaponData weaponData,
            Person owner,
            Person target,
            boolean playerWeapon) {
        super.init(weaponData, owner, target, playerWeapon);
        if (playerWeapon) {
            automatic = false;
        } else {
            automatic = true;
        }
    }

    @Override
    public void use(float x1, float y1, float x2, float y2) {
        super.use(x1, y1, x2, y2);
    }
}