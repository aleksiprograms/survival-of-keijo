package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
import com.badlogic.gdx.utils.ObjectMap;

public class PistolData extends WeaponData {

    public PistolData() {
        super(
                Constants.PISTOL_ID,
                "pistol",
                "sad",
                100,
                0.8f,
                "Semi Automatic",
                20,
                200,
                WeightType.LIGHT,
                30,
                10,
                10,
                3);

        upgradePrices = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoDamageUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoSpeedUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        magazineSizeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        reloadTimeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);

        for (int i = 0; i < WeaponData.NUMBER_OF_UPGRADES; i++) {
            upgradePrices.put(i+1, price*(i+2));
            ammoDamageUpgrades.put(i+1, 30);
            ammoSpeedUpgrades.put(i+1, 1f);
            magazineSizeUpgrades.put(i+1, 2);
            reloadTimeUpgrades.put(i+1, -0.5f);
        }
    }
}