package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeFloat;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeInteger;
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
                10,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                30,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                10,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                30,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                4,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                5,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4));

        bought = true;

        rateOfFireUpgrades.put(1, new WeaponUpgradeFloat(2,5000));
        rateOfFireUpgrades.put(2, new WeaponUpgradeFloat(2,5000));
        rateOfFireUpgrades.put(3, new WeaponUpgradeFloat(2,5000));
        rateOfFireUpgrades.put(4, new WeaponUpgradeFloat(2,5000));

        ammoDamageUpgrades.put(1, new WeaponUpgradeInteger(30,500));
        ammoDamageUpgrades.put(2, new WeaponUpgradeInteger(30,500));
        ammoDamageUpgrades.put(3, new WeaponUpgradeInteger(30,500));
        ammoDamageUpgrades.put(4, new WeaponUpgradeInteger(30,500));

        ammoSpeedUpgrades.put(1, new WeaponUpgradeFloat(2,500));
        ammoSpeedUpgrades.put(2, new WeaponUpgradeFloat(2,500));
        ammoSpeedUpgrades.put(3, new WeaponUpgradeFloat(2,500));
        ammoSpeedUpgrades.put(4, new WeaponUpgradeFloat(2,500));

        magazineSizeUpgrades.put(1, new WeaponUpgradeInteger(5,500));
        magazineSizeUpgrades.put(2, new WeaponUpgradeInteger(5,500));
        magazineSizeUpgrades.put(3, new WeaponUpgradeInteger(5,500));
        magazineSizeUpgrades.put(4, new WeaponUpgradeInteger(5,500));

        reloadTimeUpgrades.put(1, new WeaponUpgradeFloat(-0.6f,500));
        reloadTimeUpgrades.put(2, new WeaponUpgradeFloat(-0.6f,500));
        reloadTimeUpgrades.put(3, new WeaponUpgradeFloat(-0.6f,500));
        reloadTimeUpgrades.put(4, new WeaponUpgradeFloat(-0.6f,500));

        weightUpgrades.put(1, new WeaponUpgradeFloat(-0.5f,500));
        weightUpgrades.put(2, new WeaponUpgradeFloat(-0.5f,500));
        weightUpgrades.put(3, new WeaponUpgradeFloat(-0.5f,500));
        weightUpgrades.put(4, new WeaponUpgradeFloat(-0.5f,500));
    }
}