package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.weapondata.WeaponData;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class MachineGun extends Weapon {

    public MachineGun(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_WEAPON_ASSAULT_RIFLE_PLAYER, Model.class)),
                0.9f,
                0.05f,
                0f,
                0,
                0,
                0);
    }

    @Override
    public void init(
            WeaponData weaponData,
            Person owner,
            Person target,
            boolean playerWeapon) {
        super.init(weaponData, owner, target, playerWeapon);
        automatic = true;
    }
}