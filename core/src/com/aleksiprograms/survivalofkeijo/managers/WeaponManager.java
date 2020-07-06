package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.weapondata.AssaultRifleData;
import com.aleksiprograms.survivalofkeijo.weapondata.MachineGunData;
import com.aleksiprograms.survivalofkeijo.weapondata.PistolData;
import com.aleksiprograms.survivalofkeijo.weapondata.RocketLauncherData;
import com.aleksiprograms.survivalofkeijo.weapondata.ShotgunData;
import com.aleksiprograms.survivalofkeijo.weapondata.SniperData;
import com.aleksiprograms.survivalofkeijo.weapondata.WeaponData;

public class WeaponManager {

    private WeaponData[] weaponDataArray;
    private float minRateOfFire = 0;
    private float maxRateOfFire = 16;
    private float minAmmoDamage = 1;
    private float maxAmmoDamage = 300;
    private float minAmmoSpeed = 1;
    private float maxAmmoSpeed = 15;
    private float minMagazineSize = 10;
    private float maxMagazineSize = 200;
    private float minReloadTime = 0.1f;
    private float maxReloadTime = 5;

    public WeaponManager() {
        weaponDataArray = new WeaponData[Constants.NUMBER_OF_WEAPONS];
        reset();
    }

    public WeaponData getWeaponData(int weaponID) {
        for (WeaponData weaponData : weaponDataArray) {
            if (weaponID == weaponData.getID()) {
                return weaponData;
            }
        }
        return weaponDataArray[0];
    }

    public void reset() {
        weaponDataArray[0] = new PistolData();
        weaponDataArray[1] = new AssaultRifleData();
        weaponDataArray[2] = new ShotgunData();
        weaponDataArray[3] = new SniperData();
        weaponDataArray[4] = new MachineGunData();
        weaponDataArray[5] = new RocketLauncherData();
    }

    public WeaponData[] getWeaponDataArray() {
        return weaponDataArray;
    }

    public float getMinRateOfFire() {
        return minRateOfFire;
    }

    public float getMaxRateOfFire() {
        return maxRateOfFire;
    }

    public float getMinAmmoDamage() {
        return minAmmoDamage;
    }

    public float getMaxAmmoDamage() {
        return maxAmmoDamage;
    }

    public float getMinAmmoSpeed() {
        return minAmmoSpeed;
    }

    public float getMaxAmmoSpeed() {
        return maxAmmoSpeed;
    }

    public float getMinMagazineSize() {
        return minMagazineSize;
    }

    public float getMaxMagazineSize() {
        return maxMagazineSize;
    }

    public float getMinReloadTime() {
        return minReloadTime;
    }

    public float getMaxReloadTime() {
        return maxReloadTime;
    }
}