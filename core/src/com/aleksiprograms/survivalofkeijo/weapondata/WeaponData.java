package com.aleksiprograms.survivalofkeijo.weapondata;

import com.aleksiprograms.survivalofkeijo.toolbox.WeightType;
import com.badlogic.gdx.utils.ObjectMap;

public abstract class WeaponData {

    protected static final int NUMBER_OF_UPGRADES = 2;
    protected final int MAX_LEVEL = 3;
    protected int ID;
    protected String nameID;
    protected String imageName;
    protected int price;
    protected boolean bought;
    protected float ammoPrice;
    protected int numberOfAmmo;
    protected String fireType;
    protected int pointsHit;
    protected int pointsKill;
    protected WeightType weightType;
    protected int level;
    protected ObjectMap<Integer, Integer> upgradePrices;
    protected int numberOfAttributes;
    protected boolean isRateOfFire;
    protected float rateOfFire = 1;
    protected ObjectMap<Integer, Float> rateOfFireUpgrades;
    protected boolean isAmmoDamage;
    protected int ammoDamage;
    protected ObjectMap<Integer, Integer> ammoDamageUpgrades;
    protected boolean isAmmoSpeed;
    protected float ammoSpeed;
    protected ObjectMap<Integer, Float> ammoSpeedUpgrades;
    protected boolean isMagazineSize;
    protected int magazineSize = 1;
    protected ObjectMap<Integer, Integer> magazineSizeUpgrades;
    protected boolean isReloadTime;
    protected float reloadTime;
    protected ObjectMap<Integer, Float> reloadTimeUpgrades;

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
        numberOfAttributes = 5;
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
        numberOfAttributes = 4;
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
        numberOfAttributes = 3;
        this.ammoDamage = ammoDamage;
        this.ammoSpeed = ammoSpeed;
        this.reloadTime = reloadTime;
        isAmmoDamage = true;
        isAmmoSpeed = true;
        isReloadTime = true;
        isRateOfFire = false;
        isMagazineSize = false;
    }

    public int getID() {
        return ID;
    }

    public String getNameID() {
        return nameID;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public float getAmmoPrice() {
        return ammoPrice;
    }

    public int getNumberOfAmmo() {
        return numberOfAmmo;
    }

    public void setNumberOfAmmo(int numberOfAmmo) {
        this.numberOfAmmo = numberOfAmmo;
    }

    public String getFireType() {
        return fireType;
    }

    public int getPointsHit() {
        return pointsHit;
    }

    public int getPointsKill() {
        return pointsKill;
    }

    public WeightType getWeightType() {
        return weightType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMAX_LEVEL() {
        return MAX_LEVEL;
    }

    public ObjectMap<Integer, Integer> getUpgradePrices() {
        return upgradePrices;
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }

    public boolean isRateOfFire() {
        return isRateOfFire;
    }

    public float getRateOfFire() {
        return rateOfFire;
    }

    public void setRateOfFire(float rateOfFire) {
        this.rateOfFire = rateOfFire;
    }

    public ObjectMap<Integer, Float> getRateOfFireUpgrades() {
        return rateOfFireUpgrades;
    }

    public boolean isAmmoDamage() {
        return isAmmoDamage;
    }

    public int getAmmoDamage() {
        return ammoDamage;
    }

    public void setAmmoDamage(int ammoDamage) {
        this.ammoDamage = ammoDamage;
    }

    public ObjectMap<Integer, Integer> getAmmoDamageUpgrades() {
        return ammoDamageUpgrades;
    }

    public boolean isAmmoSpeed() {
        return isAmmoSpeed;
    }

    public float getAmmoSpeed() {
        return ammoSpeed;
    }

    public void setAmmoSpeed(float ammoSpeed) {
        this.ammoSpeed = ammoSpeed;
    }

    public ObjectMap<Integer, Float> getAmmoSpeedUpgrades() {
        return ammoSpeedUpgrades;
    }

    public boolean isMagazineSize() {
        return isMagazineSize;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public ObjectMap<Integer, Integer> getMagazineSizeUpgrades() {
        return magazineSizeUpgrades;
    }

    public boolean isReloadTime() {
        return isReloadTime;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public ObjectMap<Integer, Float> getReloadTimeUpgrades() {
        return reloadTimeUpgrades;
    }
}