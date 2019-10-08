package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeFloat;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeInteger;
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
                40,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                10,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                10,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                3,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                2.5f,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4));

        bought = true;

        ammoDamageUpgrades.put(1, new WeaponUpgradeInteger(30,500));
        ammoDamageUpgrades.put(2, new WeaponUpgradeInteger(30,500));
        ammoDamageUpgrades.put(3, new WeaponUpgradeInteger(30,500));
        ammoDamageUpgrades.put(4, new WeaponUpgradeInteger(30,500));

        ammoSpeedUpgrades.put(1, new WeaponUpgradeFloat(1,500));
        ammoSpeedUpgrades.put(2, new WeaponUpgradeFloat(1,500));
        ammoSpeedUpgrades.put(3, new WeaponUpgradeFloat(1,500));
        ammoSpeedUpgrades.put(4, new WeaponUpgradeFloat(1,500));

        magazineSizeUpgrades.put(1, new WeaponUpgradeInteger(2,500));
        magazineSizeUpgrades.put(2, new WeaponUpgradeInteger(2,500));
        magazineSizeUpgrades.put(3, new WeaponUpgradeInteger(2,500));
        magazineSizeUpgrades.put(4, new WeaponUpgradeInteger(2,500));

        reloadTimeUpgrades.put(1, new WeaponUpgradeFloat(-0.5f,500));
        reloadTimeUpgrades.put(2, new WeaponUpgradeFloat(-0.5f,500));
        reloadTimeUpgrades.put(3, new WeaponUpgradeFloat(-0.5f,500));
        reloadTimeUpgrades.put(4, new WeaponUpgradeFloat(-0.5f,500));

        weightUpgrades.put(1, new WeaponUpgradeFloat(-0.5f,500));
        weightUpgrades.put(2, new WeaponUpgradeFloat(-0.5f,500));
        weightUpgrades.put(3, new WeaponUpgradeFloat(-0.5f,500));
        weightUpgrades.put(4, new WeaponUpgradeFloat(-0.5f,500));
    }
}