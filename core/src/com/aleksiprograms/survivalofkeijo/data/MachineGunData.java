package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeFloat;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeInteger;
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
                5,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                20,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                8,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                100,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                5f,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4),
                10f,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4));

        rateOfFireUpgrades.put(1, new WeaponUpgradeFloat(1,500));
        rateOfFireUpgrades.put(2, new WeaponUpgradeFloat(1,500));
        rateOfFireUpgrades.put(3, new WeaponUpgradeFloat(1,500));
        rateOfFireUpgrades.put(4, new WeaponUpgradeFloat(1,500));

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