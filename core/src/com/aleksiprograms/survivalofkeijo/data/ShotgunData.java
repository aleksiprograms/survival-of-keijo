package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeFloat;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeInteger;
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
                5,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                10,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                10,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                1.2f,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                5,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4));

        ammoDamageUpgrades.put(1, new WeaponUpgradeInteger(1,500));
        ammoDamageUpgrades.put(2, new WeaponUpgradeInteger(1,500));
        ammoDamageUpgrades.put(3, new WeaponUpgradeInteger(1,500));
        ammoDamageUpgrades.put(4, new WeaponUpgradeInteger(1,500));

        ammoSpeedUpgrades.put(1, new WeaponUpgradeFloat(1,500));
        ammoSpeedUpgrades.put(2, new WeaponUpgradeFloat(1,500));
        ammoSpeedUpgrades.put(3, new WeaponUpgradeFloat(1,500));
        ammoSpeedUpgrades.put(4, new WeaponUpgradeFloat(1,500));

        magazineSizeUpgrades.put(1, new WeaponUpgradeInteger(1,500));
        magazineSizeUpgrades.put(2, new WeaponUpgradeInteger(1,500));
        magazineSizeUpgrades.put(3, new WeaponUpgradeInteger(1,500));
        magazineSizeUpgrades.put(4, new WeaponUpgradeInteger(1,500));

        reloadTimeUpgrades.put(1, new WeaponUpgradeFloat(1,500));
        reloadTimeUpgrades.put(2, new WeaponUpgradeFloat(1,500));
        reloadTimeUpgrades.put(3, new WeaponUpgradeFloat(1,500));
        reloadTimeUpgrades.put(4, new WeaponUpgradeFloat(1,500));

        weightUpgrades.put(1, new WeaponUpgradeFloat(1,500));
        weightUpgrades.put(2, new WeaponUpgradeFloat(1,500));
        weightUpgrades.put(3, new WeaponUpgradeFloat(1,500));
        weightUpgrades.put(4, new WeaponUpgradeFloat(1,500));
    }
}