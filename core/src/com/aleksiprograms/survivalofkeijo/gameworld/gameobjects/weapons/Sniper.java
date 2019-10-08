package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Sniper extends WeaponWithCase {

    public Sniper(final TheGame game) {
        super(game, new ModelInstance(game.assetManager.get(Constants.MODEL_WEAPON_PISTOL_PLAYER, Model.class)), 0.8f, 0.075f, 0f, 0, 0, 0);
    }

    @Override
    public void init(WeaponData weaponData, Person owner, Person target, boolean playerWeapon) {
        super.init(weaponData, owner, target, playerWeapon);
        if (playerWeapon) {
            automatic = false;
        } else {
            automatic = true;
        }
    }
}