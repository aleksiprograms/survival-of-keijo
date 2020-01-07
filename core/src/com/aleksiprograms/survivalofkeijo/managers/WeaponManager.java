package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.data.AssaultRifleData;
import com.aleksiprograms.survivalofkeijo.data.MachineGunData;
import com.aleksiprograms.survivalofkeijo.data.PistolData;
import com.aleksiprograms.survivalofkeijo.data.RocketLauncherData;
import com.aleksiprograms.survivalofkeijo.data.ShotgunData;
import com.aleksiprograms.survivalofkeijo.data.SniperData;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.resources.Constants;

public class WeaponManager {

    public WeaponData[] weaponData;
    public float minRateOfFire = 0;
    public float maxRateOfFire = 16;
    public float minAmmoDamage = 1;
    public float maxAmmoDamage = 300;
    public float minAmmoSpeed = 1;
    public float maxAmmoSpeed = 15;
    public float minMagazineSize = 10;
    public float maxMagazineSize = 200;
    public float minReloadTime = 0.1f;
    public float maxReloadTime = 5;

    public WeaponManager() {
        weaponData = new WeaponData[Constants.NUMBER_OF_WEAPONS];
        reset();
    }

    public WeaponData getWeaponData(int weaponID) {
        for (WeaponData weaponData : weaponData) {
            if (weaponID == weaponData.ID) {
                return weaponData;
            }
        }
        return weaponData[0];
    }

    public void reset() {
        weaponData[0] = new PistolData();
        weaponData[1] = new AssaultRifleData();
        weaponData[2] = new ShotgunData();
        weaponData[3] = new SniperData();
        weaponData[4] = new MachineGunData();
        weaponData[5] = new RocketLauncherData();
    }
}