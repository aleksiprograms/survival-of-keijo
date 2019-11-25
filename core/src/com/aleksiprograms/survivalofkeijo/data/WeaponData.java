package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeFloat;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponUpgradeInteger;
import com.badlogic.gdx.utils.ObjectMap;

public abstract class WeaponData {

    public int ID;
    public String nameID;
    public String imageName;
    public int price;
    public boolean bought;
    public float ammoPrice;
    public int numberOfAmmo;
    public String fireType;
    public int pointsHit;
    public int pointsKill;
    public int attributes;
    public boolean isRateOfFire;
    public float rateOfFire = 1;
    public ObjectMap<Integer, WeaponUpgradeFloat> rateOfFireUpgrades;
    public int rateOfFireLevel;
    public int rateOfFireMaxLevel;
    public boolean isAmmoDamage;
    public int ammoDamage;
    public ObjectMap<Integer, WeaponUpgradeInteger> ammoDamageUpgrades;
    public int ammoDamageLevel;
    public int ammoDamageMaxLevel;
    public boolean isAmmoSpeed;
    public float ammoSpeed;
    public ObjectMap<Integer, WeaponUpgradeFloat> ammoSpeedUpgrades;
    public int ammoSpeedLevel;
    public int ammoSpeedMaxLevel;
    public boolean isMagazineSize;
    public int magazineSize = 1;
    public ObjectMap<Integer, WeaponUpgradeInteger> magazineSizeUpgrades;
    public int magazineSizeLevel;
    public int magazineSizeMaxLevel;
    public boolean isReloadTime;
    public float reloadTime;
    public ObjectMap<Integer, WeaponUpgradeFloat> reloadTimeUpgrades;
    public int reloadTimeLevel;
    public int reloadTimeMaxLevel;
    public boolean isWeight;
    public float weight;
    public ObjectMap<Integer, WeaponUpgradeFloat> weightUpgrades;
    public int weightLevel;
    public int weightMaxLevel;

    public WeaponData(
            int ID,
            String nameID,
            String imageName,
            int price,
            float ammoPrice,
            String fireType,
            int pointsHit,
            int pointsKill,
            float rateOfFire,
            ObjectMap<Integer, WeaponUpgradeFloat> rateOfFireUpgrades,
            int ammoDamage,
            ObjectMap<Integer, WeaponUpgradeInteger> ammoDamageUpgrades,
            float ammoSpeed,
            ObjectMap<Integer, WeaponUpgradeFloat> ammoSpeedUpgrades,
            int magazineSize,
            ObjectMap<Integer, WeaponUpgradeInteger> magazineSizeUpgrades,
            float reloadTime,
            ObjectMap<Integer, WeaponUpgradeFloat> reloadTimeUpgrades,
            float weight,
            ObjectMap<Integer, WeaponUpgradeFloat> weightUpgrades) {

        this.ID = ID;
        this.nameID = nameID;
        this.imageName = imageName;
        this.price = price;
        bought = false;
        this.ammoPrice = ammoPrice;
        numberOfAmmo = 1000;
        this.fireType = fireType;
        this.pointsHit = pointsHit;
        this.pointsKill = pointsKill;
        attributes = 6;
        this.rateOfFire = rateOfFire;
        this.rateOfFireUpgrades = rateOfFireUpgrades;
        rateOfFireLevel = 1;
        rateOfFireMaxLevel = 5;
        this.ammoDamage = ammoDamage;
        this.ammoDamageUpgrades = ammoDamageUpgrades;
        ammoDamageLevel = 1;
        ammoDamageMaxLevel = 5;
        this.ammoSpeed = ammoSpeed;
        this.ammoSpeedUpgrades = ammoSpeedUpgrades;
        ammoSpeedLevel = 1;
        ammoSpeedMaxLevel = 5;
        this.magazineSize = magazineSize;
        this.magazineSizeUpgrades = magazineSizeUpgrades;
        magazineSizeLevel = 1;
        magazineSizeMaxLevel = 5;
        this.reloadTime = reloadTime;
        this.reloadTimeUpgrades = reloadTimeUpgrades;
        reloadTimeLevel = 1;
        reloadTimeMaxLevel = 5;
        this.weight = weight;
        this.weightUpgrades = weightUpgrades;
        weightLevel = 1;
        weightMaxLevel = 5;
        isRateOfFire = true;
        isAmmoDamage = true;
        isAmmoSpeed = true;
        isMagazineSize = true;
        isReloadTime = true;
        isWeight = true;
    }

    public WeaponData(
            int ID,
            String nameID,
            String imageName,
            int price,
            float ammoPrice,
            String fireType,
            int pointsHit,
            int pointsKill,
            int ammoDamage,
            ObjectMap<Integer, WeaponUpgradeInteger> ammoDamageUpgrades,
            float ammoSpeed,
            ObjectMap<Integer, WeaponUpgradeFloat> ammoSpeedUpgrades,
            int magazineSize,
            ObjectMap<Integer, WeaponUpgradeInteger> magazineSizeUpgrades,
            float reloadTime,
            ObjectMap<Integer, WeaponUpgradeFloat> reloadTimeUpgrades,
            float weight,
            ObjectMap<Integer, WeaponUpgradeFloat> weightUpgrades) {

        this.ID = ID;
        this.nameID = nameID;
        this.imageName = imageName;
        this.price = price;
        bought = false;
        this.ammoPrice = ammoPrice;
        numberOfAmmo = 1000;
        this.fireType = fireType;
        this.pointsHit = pointsHit;
        this.pointsKill = pointsKill;
        attributes = 5;
        this.ammoDamage = ammoDamage;
        this.ammoDamageUpgrades = ammoDamageUpgrades;
        ammoDamageLevel = 1;
        ammoDamageMaxLevel = 5;
        this.ammoSpeed = ammoSpeed;
        this.ammoSpeedUpgrades = ammoSpeedUpgrades;
        ammoSpeedLevel = 1;
        ammoSpeedMaxLevel = 5;
        this.magazineSize = magazineSize;
        this.magazineSizeUpgrades = magazineSizeUpgrades;
        magazineSizeLevel = 1;
        magazineSizeMaxLevel = 5;
        this.reloadTime = reloadTime;
        this.reloadTimeUpgrades = reloadTimeUpgrades;
        reloadTimeLevel = 1;
        reloadTimeMaxLevel = 5;
        this.weight = weight;
        this.weightUpgrades = weightUpgrades;
        weightLevel = 1;
        weightMaxLevel = 5;
        isAmmoDamage = true;
        isAmmoSpeed = true;
        isMagazineSize = true;
        isReloadTime = true;
        isWeight = true;
        isRateOfFire = false;
    }

    public WeaponData(
            int ID,
            String nameID,
            String imageName,
            int price,
            float ammoPrice,
            String fireType,
            int pointsHit,
            int pointsKill,
            int ammoDamage,
            ObjectMap<Integer, WeaponUpgradeInteger> ammoDamageUpgrades,
            float ammoSpeed,
            ObjectMap<Integer, WeaponUpgradeFloat> ammoSpeedUpgrades,
            float reloadTime,
            ObjectMap<Integer, WeaponUpgradeFloat> reloadTimeUpgrades,
            float weight,
            ObjectMap<Integer, WeaponUpgradeFloat> weightUpgrades) {

        this.ID = ID;
        this.nameID = nameID;
        this.imageName = imageName;
        this.price = price;
        bought = false;
        this.ammoPrice = ammoPrice;
        numberOfAmmo = 1000;
        this.fireType = fireType;
        this.pointsHit = pointsHit;
        this.pointsKill = pointsKill;
        attributes = 4;
        this.ammoDamage = ammoDamage;
        this.ammoDamageUpgrades = ammoDamageUpgrades;
        ammoDamageLevel = 1;
        ammoDamageMaxLevel = 5;
        this.ammoSpeed = ammoSpeed;
        this.ammoSpeedUpgrades = ammoSpeedUpgrades;
        ammoSpeedLevel = 1;
        ammoSpeedMaxLevel = 5;
        magazineSizeLevel = 1;
        magazineSizeMaxLevel = 5;
        this.reloadTime = reloadTime;
        this.reloadTimeUpgrades = reloadTimeUpgrades;
        reloadTimeLevel = 1;
        reloadTimeMaxLevel = 5;
        this.weight = weight;
        this.weightUpgrades = weightUpgrades;
        weightLevel = 1;
        weightMaxLevel = 5;
        isAmmoDamage = true;
        isAmmoSpeed = true;
        isReloadTime = true;
        isWeight = true;
        isRateOfFire = false;
        isMagazineSize = false;
    }
}