package com.aleksiprograms.survivalofkeijo.weapondata;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
import com.badlogic.gdx.utils.ObjectMap;

public class ShotgunData extends WeaponData {

    public ShotgunData() {
        super(
                Constants.SHOTGUN_ID,
                "shotgun",
                "sad",
                1000,
                1.8f,
                "Semi Automatic",
                15,
                150,
                WeightType.MEDIUM,
                5,
                10,
                10,
                1.2f);

        upgradePrices = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoDamageUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoSpeedUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        magazineSizeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        reloadTimeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);

        for (int i = 0; i < WeaponData.NUMBER_OF_UPGRADES; i++) {
            upgradePrices.put(i + 1, price * (i + 2));
            ammoDamageUpgrades.put(i + 1, 30);
            ammoSpeedUpgrades.put(i + 1, 2f);
            magazineSizeUpgrades.put(i + 1, 5);
            reloadTimeUpgrades.put(i + 1, -0.6f);
        }
    }
}