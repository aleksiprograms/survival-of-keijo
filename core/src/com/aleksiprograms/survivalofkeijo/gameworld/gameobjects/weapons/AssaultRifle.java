package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public abstract class AssaultRifle extends Weapon {

    public AssaultRifle(TheGame game, ModelInstance modelInstance) {
        super(game, modelInstance, 0.741f + 0.1f, 0.040f, 0.095f, 0.474f - 0.05f, 0.040f, 0.095f);
    }

    @Override
    public void init(WeaponData weaponData, Person owner, Person target, boolean playerWeapon) {
        super.init(weaponData, owner, target, playerWeapon);
        automatic = true;
    }
}