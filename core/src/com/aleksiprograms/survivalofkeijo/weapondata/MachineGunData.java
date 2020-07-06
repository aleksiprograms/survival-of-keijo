package com.aleksiprograms.survivalofkeijo.weapondata;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
import com.badlogic.gdx.utils.ObjectMap;

public class MachineGunData extends WeaponData {

    public MachineGunData() {
        super(
                Constants.MACHINE_GUN_ID,
                "machineGun",
                "sad",
                5000,
                1.2f,
                "Automatic",
                10,
                100,
                WeightType.HEAVY,
                5,
                20,
                8,
                100,
                5f);

        upgradePrices = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        rateOfFireUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoDamageUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoSpeedUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        magazineSizeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        reloadTimeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);

        for (int i = 0; i < WeaponData.NUMBER_OF_UPGRADES; i++) {
            upgradePrices.put(i + 1, price * (i + 2));
            rateOfFireUpgrades.put(i + 1, 2f);
            ammoDamageUpgrades.put(i + 1, 30);
            ammoSpeedUpgrades.put(i + 1, 2f);
            magazineSizeUpgrades.put(i + 1, 5);
            reloadTimeUpgrades.put(i + 1, -0.6f);
        }
    }
}