package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
import com.badlogic.gdx.utils.ObjectMap;

public class AssaultRifleData extends WeaponData {

    public AssaultRifleData() {
        super(
                Constants.ASSAULT_RIFLE_ID,
                "assaultRifle",
                "sad",
                2000,
                1.2f,
                "Automatic",
                15,
                150,
                WeightType.MEDIUM,
                10,
                30,
                10,
                30,
                4);

        upgradePrices = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        rateOfFireUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoDamageUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoSpeedUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        magazineSizeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        reloadTimeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);

        for (int i = 0; i < WeaponData.NUMBER_OF_UPGRADES; i++) {
            upgradePrices.put(i+1, price*(i+2));
            rateOfFireUpgrades.put(i+1, 2f);
            ammoDamageUpgrades.put(i+1, 30);
            ammoSpeedUpgrades.put(i+1, 2f);
            magazineSizeUpgrades.put(i+1, 5);
            reloadTimeUpgrades.put(i+1, -0.6f);
        }
    }
}