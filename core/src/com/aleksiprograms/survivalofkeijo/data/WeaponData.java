package com.aleksiprograms.survivalofkeijo.data;

import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
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
    public WeightType weightType;
    public int level;
    public final int MAX_LEVEL = 3;
    public static final int NUMBER_OF_UPGRADES = 2;
    public ObjectMap<Integer, Integer> upgradePrices;
    public int attributes;
    public boolean isRateOfFire;
    public float rateOfFire = 1;
    public ObjectMap<Integer, Float> rateOfFireUpgrades;
    public boolean isAmmoDamage;
    public int ammoDamage;
    public ObjectMap<Integer, Integer> ammoDamageUpgrades;
    public boolean isAmmoSpeed;
    public float ammoSpeed;
    public ObjectMap<Integer, Float> ammoSpeedUpgrades;
    public boolean isMagazineSize;
    public int magazineSize = 1;
    public ObjectMap<Integer, Integer> magazineSizeUpgrades;
    public boolean isReloadTime;
    public float reloadTime;
    public ObjectMap<Integer, Float> reloadTimeUpgrades;

    public WeaponData(
            int ID,
            String nameID,
            String imageName,
            int price,
            float ammoPrice,
            String fireType,
            int pointsHit,
            int pointsKill,
            WeightType weightType,
            float rateOfFire,
            int ammoDamage,
            float ammoSpeed,
            int magazineSize,
            float reloadTime) {

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
        this.weightType = weightType;
        level = 1;
        attributes = 5;
        this.rateOfFire = rateOfFire;
        this.ammoDamage = ammoDamage;
        this.ammoSpeed = ammoSpeed;
        this.magazineSize = magazineSize;
        this.reloadTime = reloadTime;
        isRateOfFire = true;
        isAmmoDamage = true;
        isAmmoSpeed = true;
        isMagazineSize = true;
        isReloadTime = true;
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
            WeightType weightType,
            int ammoDamage,
            float ammoSpeed,
            int magazineSize,
            float reloadTime) {

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
        this.weightType = weightType;
        level = 1;
        attributes = 4;
        this.ammoDamage = ammoDamage;
        this.ammoSpeed = ammoSpeed;
        this.magazineSize = magazineSize;
        this.reloadTime = reloadTime;
        isAmmoDamage = true;
        isAmmoSpeed = true;
        isMagazineSize = true;
        isReloadTime = true;
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
            WeightType weightType,
            int ammoDamage,
            float ammoSpeed,
            float reloadTime) {

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
        this.weightType = weightType;
        level = 1;
        attributes = 3;
        this.ammoDamage = ammoDamage;
        this.ammoSpeed = ammoSpeed;
        this.reloadTime = reloadTime;
        isAmmoDamage = true;
        isAmmoSpeed = true;
        isReloadTime = true;
        isRateOfFire = false;
        isMagazineSize = false;
    }
}