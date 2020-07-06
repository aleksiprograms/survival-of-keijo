package com.aleksiprograms.survivalofkeijo.weapondata;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
import com.badlogic.gdx.utils.ObjectMap;

public class RocketLauncherData extends WeaponData {

    public RocketLauncherData() {
        super(
                Constants.ROCKET_LAUNCHER_ID,
                "rocketLauncher",
                "sad",
                1000,
                100.0f,
                "Automatic",
                10,
                100,
                WeightType.HEAVY,
                33,
                10,
                1.2f);

        upgradePrices = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoDamageUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        ammoSpeedUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);
        reloadTimeUpgrades = new ObjectMap<>(WeaponData.NUMBER_OF_UPGRADES);

        for (int i = 0; i < WeaponData.NUMBER_OF_UPGRADES; i++) {
            upgradePrices.put(i + 1, price * (i + 2));
            ammoDamageUpgrades.put(i + 1, 30);
            ammoSpeedUpgrades.put(i + 1, 2f);
            reloadTimeUpgrades.put(i + 1, -0.6f);
        }
    }
}