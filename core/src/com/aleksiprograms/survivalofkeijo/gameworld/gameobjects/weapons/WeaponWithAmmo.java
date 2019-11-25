package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Ammunition;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.UpState;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

public class WeaponWithAmmo<A extends Ammunition> extends Weapon {

    public Pool<A> ammoPool;
    public boolean doThingsAtUse;
    public int inMagazine;
    public Vector3 ammoVelocity;
    public float animationOffset = 0;
    public float loadTime = 0;
    public boolean useTimer = false;
    public AnimationController.AnimationListener animationListenerAfterUse;
    public AnimationController.AnimationListener animationListenerAfterUseEmpty;
    public AnimationController.AnimationListener animationListenerAfterMagazineEmpty;
    public AnimationController.AnimationListener animationListenerAfterMagazineNotEmpty;
    public AnimationController.AnimationListener animationListenerAfterReload;

    public WeaponWithAmmo(final TheGame game, ModelInstance modelInstance, float ammoOffsetX, float ammoOffsetY, float ammoOffsetZ) {
        super(game, modelInstance, ammoOffsetX, ammoOffsetY, ammoOffsetZ, false);

        ammoVelocity = new Vector3();

        animationListenerAfterUse = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                animationController.setAnimation("Armature|moving", -1);
                onCriticalAnimation = false;
                animate = false;
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };

        animationListenerAfterUseEmpty = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                if (!noAmmo) {
                    reloading = true;
                    useTimer = true;
                    game.gameScreen.inGameHud.updateHud();
                    if (owner.upState.equals(UpState.STAND)) {
                        animationController.setAnimation("Armature|magazine-empty-stand", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                    } else if (owner.upState.equals(UpState.CROUCH)) {
                        animationController.setAnimation("Armature|magazine-empty-crouch", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                    } else if (owner.upState.equals(UpState.PRONE)) {
                        animationController.setAnimation("Armature|magazine-empty-prone", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                    }
                } else {
                    animationController.setAnimation("Armature|moving-empty", -1);
                    onCriticalAnimation = false;
                    animate = false;
                }
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };

        animationListenerAfterMagazineEmpty = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                animationController.setAnimation("Armature|reload", 1, 1 / (weaponData.reloadTime * 1f / 4f), animationListenerAfterReload);
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };

        animationListenerAfterMagazineNotEmpty = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                animationController.setAnimation("Armature|reload-not", 1, 1 / (weaponData.reloadTime * 1f / 10f), animationListenerAfterReload);
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };

        animationListenerAfterReload = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                animationController.setAnimation("Armature|moving", -1);
                if (weaponData.numberOfAmmo >= weaponData.magazineSize) {
                    inMagazine = weaponData.magazineSize;
                } else {
                    inMagazine = weaponData.numberOfAmmo;
                }
                canUse = true;
                noAmmoInMagazine = false;
                reloading = false;
                onCriticalAnimation = false;
                animate = false;
                useTimer = false;
                changeMagWithoutReload = false;
                game.gameScreen.inGameHud.updateHud();
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };
    }

    @Override
    public void updateWeaponWhenMoreAmmoIsGiven() {
        super.updateWeaponWhenMoreAmmoIsGiven();
        if (noAmmo) {
            if (weaponData.numberOfAmmo > 0) {
                noAmmo = false;
                noAmmoInMagazine = false;
                reloading = true;
                useTimer = true;
                animate = true;
                onCriticalAnimation = true;
                game.gameScreen.inGameHud.updateHud();
                if (owner.upState.equals(UpState.STAND)) {
                    animationController.setAnimation("Armature|magazine-empty-stand", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                } else if (owner.upState.equals(UpState.CROUCH)) {
                    animationController.setAnimation("Armature|magazine-empty-crouch", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                } else if (owner.upState.equals(UpState.PRONE)) {
                    animationController.setAnimation("Armature|magazine-empty-prone", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                }
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // UNLIMITED AMMUNITION FOR ENEMIES
        if (!playerWeapon) {
            if (weaponData.numberOfAmmo < 1000) {
                weaponData.numberOfAmmo += 10000;
            }
        }
        if (reloadAfterCriticalAnimation) {
            reloadAfterCriticalAnimation = false;
            if (inMagazine < weaponData.magazineSize && (weaponData.numberOfAmmo - inMagazine) > 0) {
                canUse = false;
                animate = true;
                reloading = true;
                useTimer = true;
                changeMagWithoutReload = true;
                game.gameScreen.inGameHud.updateHud();
                if (owner.upState.equals(UpState.STAND)) {
                    animationController.setAnimation("Armature|magazine-not-empty-stand", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                } else if (owner.upState.equals(UpState.CROUCH)) {
                    animationController.setAnimation("Armature|magazine-not-empty-crouch", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                } else if (owner.upState.equals(UpState.PRONE)) {
                    animationController.setAnimation("Armature|magazine-not-empty-prone", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                }
            }
        }
        if (useTimer) {
            loadTime += deltaTime;
        }
        if (!useTimer && loadTime > 0.1f) {
            loadTime = 0;
        }
        if (!(upStateCurrent.equals(owner.upState))) {
            upStateCurrent = owner.upState;
            animationOffset = animationController.current.time;
            if (reloading) {
                if (owner.upState.equals(UpState.STAND)) {
                    if (!changeMagWithoutReload) {
                        animationController.setAnimation("Armature|magazine-empty-stand", animationOffset, -1, 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                    } else {
                        animationController.setAnimation("Armature|magazine-not-empty-stand", animationOffset, -1, 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                    }
                } else if (owner.upState.equals(UpState.CROUCH)) {
                    if (!changeMagWithoutReload) {
                        animationController.setAnimation("Armature|magazine-empty-crouch", animationOffset, -1, 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                    } else {
                        animationController.setAnimation("Armature|magazine-not-empty-crouch", animationOffset, -1, 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                    }
                } else if (owner.upState.equals(UpState.PRONE)) {
                    if (!changeMagWithoutReload) {
                        animationController.setAnimation("Armature|magazine-empty-prone", animationOffset, -1, 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
                    } else {
                        animationController.setAnimation("Armature|magazine-not-empty-prone", animationOffset, -1, 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                    }
                }
            }
        }
    }

    @Override
    public void use(float x1, float y1, float x2, float y2) {
        doThingsAtUse = false;
        if (canUse) {
            doThingsAtUse = true;
            animate = true;
            onCriticalAnimation = true;
            inMagazine--;
            weaponData.numberOfAmmo--;
            if (inMagazine > 0) {
                animationController.setAnimation("Armature|use", 1, 8, animationListenerAfterUse);
            } else {
                canUse = false;
                noAmmoInMagazine = true;
                if (weaponData.numberOfAmmo <= 0) {
                    noAmmo = true;
                }
                animationController.setAnimation("Armature|use-empty", 1, 8, animationListenerAfterUseEmpty);
            }
            ammoVelocity.set(x2 - x1, y2 - y1, 0);
            ammoVelocity = ammoVelocity.nor();
            ammoVelocity = ammoVelocity.scl(weaponData.ammoSpeed);
            game.gameWorld.addAmmunition(ammoPool.obtain(), weaponData.ammoDamage, x1, y1, lookingRight ? ammoOffsetZ : -ammoOffsetZ, weaponAngleDeg, ammoVelocity, playerWeapon ? Constants.CATEGORY_WEAPON_PLAYER : Constants.CATEGORY_WEAPON_ENEMY, playerWeapon ? Constants.MASK_WEAPON_PLAYER : Constants.MASK_WEAPON_ENEMY);
            used();
            game.gameScreen.inGameHud.updateHud();
            //game.gameWorld.addParticleEffect(game.gamePools.rocketSmokePool.obtain(), new Vector3(x1, y1, 0), MathUtils.radiansToDegrees * MathUtils.atan2(y2 - y1, x2 - x1)-5+90, MathUtils.radiansToDegrees * MathUtils.atan2(y2 - y1, x2 - x1)+5+90);
        }
    }

    @Override
    public void reload() {
        if (!reloading) {
            if (inMagazine < weaponData.magazineSize && (weaponData.numberOfAmmo - inMagazine) > 0) {
                if (onCriticalAnimation) {
                    reloadAfterCriticalAnimation = true;
                } else {
                    canUse = false;
                    animate = true;
                    reloading = true;
                    useTimer = true;
                    changeMagWithoutReload = true;
                    game.gameScreen.inGameHud.updateHud();
                    if (owner.upState.equals(UpState.STAND)) {
                        animationController.setAnimation("Armature|magazine-not-empty-stand", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                    } else if (owner.upState.equals(UpState.CROUCH)) {
                        animationController.setAnimation("Armature|magazine-not-empty-crouch", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                    } else if (owner.upState.equals(UpState.PRONE)) {
                        animationController.setAnimation("Armature|magazine-not-empty-prone", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                    }
                }
            }
        }
    }

    public void init(WeaponData weaponData, Person owner, Person target, boolean playerWeapon) {
        super.init(weaponData, owner, target, playerWeapon);
        if (playerWeapon) {
            if (this instanceof RocketLauncher) {
                this.ammoPool = (Pool<A>) game.gamePools.rocketPool;
            } else {
                this.ammoPool = (Pool<A>) game.gamePools.bulletPlayerPool;
            }
        } else {
            this.ammoPool = (Pool<A>) game.gamePools.bulletEnemyPool;
        }
        doThingsAtUse = false;
        if (weaponData.numberOfAmmo >= weaponData.magazineSize) {
            inMagazine = weaponData.magazineSize;
            noAmmo = false;
            canUse = true;
        } else if (weaponData.numberOfAmmo > 0) {
            inMagazine = weaponData.numberOfAmmo;
            noAmmo = false;
            canUse = true;
        } else {
            inMagazine = 0;
            noAmmo = true;
            canUse = false;
            animationController.setAnimation("Armature|moving-empty", -1);
        }
    }
}