package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeFloat;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeInteger;
import com.badlogic.gdx.utils.ObjectMap;

public class KnifeData extends WeaponData {

    public KnifeData() {
        super(
                Constants.KNIFE_ID,
                "knife",
                "sad",
                1000,
                30,
                300,
                33,
                new ObjectMap<Integer, WeaponUpgradeInteger>(4),
                5,
                new ObjectMap<Integer, WeaponUpgradeFloat>(4));

        meleeDamageUpgrades.put(1, new WeaponUpgradeInteger(1,500));
        meleeDamageUpgrades.put(2, new WeaponUpgradeInteger(1,500));
        meleeDamageUpgrades.put(3, new WeaponUpgradeInteger(1,500));
        meleeDamageUpgrades.put(4, new WeaponUpgradeInteger(1,500));

        weightUpgrades.put(1, new WeaponUpgradeFloat(1,500));
        weightUpgrades.put(2, new WeaponUpgradeFloat(1,500));
        weightUpgrades.put(3, new WeaponUpgradeFloat(1,500));
        weightUpgrades.put(4, new WeaponUpgradeFloat(1,500));
    }
}