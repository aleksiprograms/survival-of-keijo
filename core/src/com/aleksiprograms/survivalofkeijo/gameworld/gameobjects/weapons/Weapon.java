package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.RenderableObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Ammunition;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Case;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.utils.Pool;

public abstract class Weapon<A extends Ammunition, B extends Case> extends RenderableObject {

    public WeaponData weaponData;
    public Person owner;
    public Person target;
    public Pool<A> ammoPool;
    public boolean doThingsAtUse;
    public int inMagazine;
    public Vector3 ammoVelocity;
    public float loadTime = 0;
    public boolean useTimer = false;
    public Pool<B> casePool;
    public float caseOffsetX;
    public float caseOffsetY;
    public float caseOffsetZ;
    public Vector3 caseVelocity;
    public Vector3 caseAngularVelocity;
    private Node weaponBone;
    private Vector3 uselessPosition;
    public boolean ownerDead;
    public Vector3 ownerDeadPosition;
    public boolean lookingRight;
    public Vector3 weaponPosition;
    public Quaternion weaponQuaternion;
    public Vector3 weaponScale;
    public float weaponAngleDeg = 0;
    public boolean calculateWeaponAngle;
    public Vector3 ammoHitPoint;
    public Vector3 shootRayFrom;
    public Vector3 shootRayTo;
    public ClosestRayResultCallback shootCallback;
    public float reactionTime;
    public float readyToShootTimer;
    public float ammoStartOffsetX;
    public float ammoStartOffsetY;
    public float ammoEndOffsetX;
    public float ammoEndOffsetY;
    private float handsOffsetY;
    private float handsOffsetX;
    public float weaponTimer = 0;
    public boolean playerWeapon;
    public float lastShot;
    public boolean onCriticalAnimation;
    public boolean reloadAfterCriticalAnimation;
    public boolean automatic;
    public boolean noAmmo;
    public boolean noAmmoInMagazine;
    public boolean canUse;
    public boolean shotDelayPast;
    public float ammoOffsetX;
    public float ammoOffsetY;
    public float ammoOffsetZ;
    public boolean reloading;
    public boolean changeMagWithoutReload;
    public boolean visibleToPlayer;
    public boolean animate;
    public AnimationController animationController;
    public AnimationController.AnimationListener animationListener;
    public AnimationController.AnimationListener animationListenerAfterUse;
    public AnimationController.AnimationListener animationListenerAfterUseEmpty;
    public AnimationController.AnimationListener animationListenerAfterMagazineEmpty;
    public AnimationController.AnimationListener animationListenerAfterMagazineNotEmpty;
    public AnimationController.AnimationListener animationListenerAfterReload;

    public Weapon(final TheGame game, ModelInstance modelInstance,
                  float ammoOffsetX, float ammoOffsetY, float ammoOffsetZ,
                  float caseOffsetX, float caseOffsetY, float caseOffsetZ) {
        super(game, modelInstance);
        ammoVelocity = new Vector3();
        ownerDeadPosition = new Vector3();
        weaponPosition = new Vector3();
        weaponQuaternion = new Quaternion();
        weaponScale = new Vector3(1, 1, 1);
        uselessPosition = new Vector3();
        ammoHitPoint = new Vector3();
        lastShot = 0;
        canUse = true;
        shotDelayPast = true;
        this.ammoOffsetX = ammoOffsetX;
        this.ammoOffsetY = ammoOffsetY;
        this.ammoOffsetZ = ammoOffsetZ;
        this.caseOffsetX = caseOffsetX;
        this.caseOffsetY = caseOffsetY;
        this.caseOffsetZ = caseOffsetZ;
        handsOffsetX = 0f;
        handsOffsetY = 0.5f;
        ammoStartOffsetX = 0f;
        ammoStartOffsetY = 0.9f - (0.9f - handsOffsetY - ammoOffsetY);
        ammoEndOffsetX = 0f;
        ammoEndOffsetY = 0.3f;
        caseVelocity = new Vector3();
        caseAngularVelocity = new Vector3();
        reloading = false;
        visibleToPlayer = true;
        animate = true;
        dimensions.set(1.8f, 1.8f, 1);
        shootRayFrom = new Vector3();
        shootRayTo = new Vector3();
        shootCallback = new ClosestRayResultCallback(shootRayFrom, shootRayTo);
        animationController = new AnimationController(this);
        animationListener = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {

            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };
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
                    animationController.setAnimation("Armature|magazine-empty-stand", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
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

    public void init(WeaponData weaponData, Person owner, Person target, boolean playerWeapon) {
        super.init();
        this.weaponData = weaponData;
        this.owner = owner;
        this.target = target;
        this.playerWeapon = playerWeapon;
        this.automatic = true;
        weaponBone = owner.getNode("Empty");
        ownerDead = false;
        lookingRight = false;
        reactionTime = 0.5f;
        readyToShootTimer = 0;
        shootRayFrom.set(Vector3.Zero);
        shootRayTo.set(Vector3.Zero);
        shootCallback.setCollisionFilterGroup(Constants.CATEGORY_ENEMY_SENSOR_PLAYER);
        shootCallback.setCollisionFilterMask(Constants.MASK_ENEMY_SENSOR_PLAYER);
        canUse = true;
        noAmmo = false;
        noAmmoInMagazine = false;
        shotDelayPast = true;
        reloading = false;
        changeMagWithoutReload = false;
        visibleToPlayer = true;
        animate = true;
        onCriticalAnimation = false;
        reloadAfterCriticalAnimation = false;
        try {
            animationController.setAnimation("Armature|stand", -1);
        } catch (Exception ex) {
            animationController.setAnimation("Armature|moving", -1);
        }
        animationController.update(0.01f);

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

        if (playerWeapon) {
            this.casePool = (Pool<B>) game.gamePools.casePlayerPool;
        } else {
            this.casePool = (Pool<B>) game.gamePools.caseEnemyPool;
        }
    }

    public void updateWeaponWhenMoreAmmoIsGiven() {
        if (noAmmo) {
            if (weaponData.numberOfAmmo > 0) {
                noAmmo = false;
                noAmmoInMagazine = false;
                reloading = true;
                useTimer = true;
                animate = true;
                onCriticalAnimation = true;
                game.gameScreen.inGameHud.updateHud();
                animationController.setAnimation("Armature|magazine-empty-stand", 1, 1 / (weaponData.reloadTime * 3f / 4f), animationListenerAfterMagazineEmpty);
            }
        }
    }

    public void updateOnEquip() {
        updateTransform();
    }

    @Override
    public void update(float deltaTime) {
        if (visibleToPlayer) {
            if (animate) {
                animationController.update(deltaTime);
            }
        }
        if (automatic) {
            lastShot += deltaTime;
            if (lastShot >= (1 / weaponData.rateOfFire)) {
                lastShot = (1 / weaponData.rateOfFire) + 0.1f;
                shotDelayPast = true;
            }
            if (!reloading && shotDelayPast && !noAmmo && !noAmmoInMagazine) {
                canUse = true;
            }
        }
        if (ownerDead) {
            if (owner.rigidBody != null) {
                ownerDeadPosition.set(owner.rigidBody.getCenterOfMassPosition());
            }
            if (lookingRight) {
                weaponPosition.set(
                        ownerDeadPosition.x + weaponBone.globalTransform.getTranslation(uselessPosition).x,
                        ownerDeadPosition.y + weaponBone.globalTransform.getTranslation(uselessPosition).y,
                        ownerDeadPosition.z + weaponBone.globalTransform.getTranslation(uselessPosition).z);
                weaponQuaternion.set(Vector3.Z, -66);
                transform.set(weaponPosition, weaponQuaternion, weaponScale);
            } else {
                weaponPosition.set(
                        ownerDeadPosition.x - weaponBone.globalTransform.getTranslation(uselessPosition).x,
                        ownerDeadPosition.y + weaponBone.globalTransform.getTranslation(uselessPosition).y,
                        ownerDeadPosition.z + weaponBone.globalTransform.getTranslation(uselessPosition).z);
                weaponQuaternion.set(Vector3.Z, 66);
                transform.set(weaponPosition, weaponQuaternion, weaponScale);
                transform.rotate(Vector3.Y, 180);
            }
        } else {
            if (visibleToPlayer || playerWeapon) {
                updateTransform();
                if (!playerWeapon && !game.gameWorld.player.dead) {
                    if (shootCallback.hasHit() && shootCallback.getCollisionObject().userData == target) {
                        /*if (bigArea.area == game.gameWorld.player.bigArea.area && smallArea.area == game.gameWorld.player.smallArea.area) {
                            if (((ammoHitPoint.x - shootRayFrom.x) * (ammoHitPoint.x - shootRayFrom.x) + (ammoHitPoint.y - shootRayFrom.y) * (ammoHitPoint.y - shootRayFrom.y)) < 1) {
                                meleeAttack = true;
                            } else {
                                meleeAttack = false;
                            }
                        }*/
                        weaponTimer += deltaTime;
                        if (weaponTimer > 1) {
                            setShootRayFromAccurate();
                            shootCallback.getHitPointWorld(ammoHitPoint);
                            use(shootRayFrom.x, shootRayFrom.y, ammoHitPoint.x, ammoHitPoint.y);
                        }
                    } else {
                        weaponTimer = 0;
                    }
                }
            } else {
                weaponTimer = 0;
                weaponPosition.set(owner.rigidBody.getCenterOfMassPosition().x, owner.rigidBody.getCenterOfMassPosition().y + handsOffsetY, owner.rigidBody.getCenterOfMassPosition().z);
                transform.set(weaponPosition, weaponQuaternion, weaponScale);
            }
        }

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
                animationController.setAnimation("Armature|magazine-not-empty-stand", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
            }
        }
        if (useTimer) {
            loadTime += deltaTime;
        }
        if (!useTimer && loadTime > 0.1f) {
            loadTime = 0;
        }
    }

    private void updateTransform() {
        calculateWeaponAngle = false;
        if (!playerWeapon && !game.gameWorld.player.dead) {
            setShootRayFromNotAccurate();
            setShootRayToWithTarget();
            shootCallback.setCollisionObject(null);
            shootCallback.setClosestHitFraction(1);
            shootCallback.setRayFromWorld(shootRayFrom);
            shootCallback.setRayToWorld(shootRayTo);
            game.gameWorld.dynamicsWorld.rayTest(shootRayFrom, shootRayTo, shootCallback);
            if (shootCallback.hasHit() && shootCallback.getCollisionObject().userData == target) {
                calculateWeaponAngle = true;
            }
        }
        if ((playerWeapon && target == null) || reloading) {
            if (lookingRight) {
                weaponAngleDeg = 0;
            } else {
                weaponAngleDeg = 180;
            }
        } else if (!playerWeapon && !calculateWeaponAngle) {
            if (lookingRight) {
                weaponAngleDeg = -55; // -145
            } else {
                weaponAngleDeg = -125; // -215
            }
        } else {
            weaponAngleDeg = MathUtils.radiansToDegrees * MathUtils.atan2(
                    (target.rigidBody.getCenterOfMassPosition().y + ammoEndOffsetY) - (owner.rigidBody.getCenterOfMassPosition().y + ammoStartOffsetY),
                    (target.rigidBody.getCenterOfMassPosition().x - ammoEndOffsetX) - (owner.rigidBody.getCenterOfMassPosition().x + ammoStartOffsetX));
        }
        if (lookingRight) {
            weaponPosition.set(
                    owner.rigidBody.getCenterOfMassPosition().x + weaponBone.globalTransform.getTranslation(uselessPosition).x,
                    owner.rigidBody.getCenterOfMassPosition().y + weaponBone.globalTransform.getTranslation(uselessPosition).y,
                    owner.rigidBody.getCenterOfMassPosition().z + weaponBone.globalTransform.getTranslation(uselessPosition).z);
            weaponQuaternion.set(Vector3.Z, weaponAngleDeg - 90);
            transform.set(weaponPosition, weaponQuaternion, weaponScale);
        } else {
            weaponPosition.set(
                    owner.rigidBody.getCenterOfMassPosition().x + weaponBone.globalTransform.getTranslation(uselessPosition).x,
                    owner.rigidBody.getCenterOfMassPosition().y + weaponBone.globalTransform.getTranslation(uselessPosition).y,
                    owner.rigidBody.getCenterOfMassPosition().z + weaponBone.globalTransform.getTranslation(uselessPosition).z);
            weaponQuaternion.set(Vector3.Z, weaponAngleDeg - 90);
            transform.set(weaponPosition, weaponQuaternion, weaponScale);
            transform.rotate(Vector3.Y, 180);
        }
    }

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
            if (automatic) {
                lastShot = 0;
                canUse = false;
                shotDelayPast = false;
            }
            game.gameScreen.inGameHud.updateHud();
            if (lookingRight) {
                caseVelocity.set(-MathUtils.random(2f, 3f) * MathUtils.sinDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.cosDeg(weaponAngleDeg), MathUtils.random(2f, 3f) * MathUtils.cosDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.sinDeg(weaponAngleDeg), MathUtils.random(0.3f, 0.6f));
                caseAngularVelocity.set(0, -MathUtils.random(8f, 12f), MathUtils.random(8f, 12f));
            } else {
                caseVelocity.set(MathUtils.random(2f, 3f) * MathUtils.sinDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.cosDeg(weaponAngleDeg), -MathUtils.random(2f, 3f) * MathUtils.cosDeg(weaponAngleDeg) - MathUtils.random(1f, 1.5f) * MathUtils.sinDeg(weaponAngleDeg), -MathUtils.random(0.3f, 0.6f));
                caseAngularVelocity.set(0, MathUtils.random(8f, 12f), -MathUtils.random(8f, 12f));
            }
            game.gameWorld.addCase(
                    casePool.obtain(),
                    x1 + ((caseOffsetX - ammoOffsetX) * MathUtils.cosDeg(weaponAngleDeg) - (caseOffsetY - ammoOffsetY) * MathUtils.sinDeg(weaponAngleDeg)),
                    y1 + ((caseOffsetY - ammoOffsetY) * MathUtils.cosDeg(weaponAngleDeg) + (caseOffsetX - ammoOffsetX) * MathUtils.sinDeg(weaponAngleDeg)),
                    lookingRight ? caseOffsetZ : -caseOffsetZ,
                    weaponAngleDeg,
                    caseVelocity,
                    caseAngularVelocity);
        }
    }

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
                    animationController.setAnimation("Armature|magazine-not-empty-stand", 1, 1 / (weaponData.reloadTime * 9f / 10f), animationListenerAfterMagazineNotEmpty);
                }
            }
        }
    }

    public void usePlayer() {
        setShootRayFromAccurate();
        if (target != null) {
            setShootRayToWithTarget();
        } else {
            setShootRayToWithoutTarget();
        }
        use(shootRayFrom.x, shootRayFrom.y, shootRayTo.x, shootRayTo.y);
    }

    private void setShootRayFromAccurate() {
        if (lookingRight) {
            shootRayFrom.set(
                    owner.rigidBody.getCenterOfMassPosition().x + handsOffsetX
                            + (ammoOffsetX * MathUtils.cosDeg(weaponAngleDeg) - ammoOffsetY * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.rigidBody.getCenterOfMassPosition().y + handsOffsetY
                            + (ammoOffsetY * MathUtils.cosDeg(weaponAngleDeg) + ammoOffsetX * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.rigidBody.getCenterOfMassPosition().z);
        } else {
            shootRayFrom.set(
                    owner.rigidBody.getCenterOfMassPosition().x + handsOffsetX
                            + (ammoOffsetX * MathUtils.cosDeg(weaponAngleDeg) - (-ammoOffsetY) * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.rigidBody.getCenterOfMassPosition().y + handsOffsetY
                            + ((-ammoOffsetY) * MathUtils.cosDeg(weaponAngleDeg) + ammoOffsetX * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.rigidBody.getCenterOfMassPosition().z);
        }
    }

    private void setShootRayFromNotAccurate() {
        if (lookingRight) {
            shootRayFrom.set(
                    owner.rigidBody.getCenterOfMassPosition().x + 0.25f,
                    owner.rigidBody.getCenterOfMassPosition().y + handsOffsetY,
                    owner.rigidBody.getCenterOfMassPosition().z);
        } else {
            shootRayFrom.set(
                    owner.rigidBody.getCenterOfMassPosition().x - 0.25f,
                    owner.rigidBody.getCenterOfMassPosition().y + handsOffsetY,
                    owner.rigidBody.getCenterOfMassPosition().z);
        }
    }

    private void setShootRayToWithTarget() {
        if (lookingRight) {
            shootRayTo.set(
                    target.rigidBody.getCenterOfMassPosition().x - ammoEndOffsetX,
                    target.rigidBody.getCenterOfMassPosition().y + ammoEndOffsetY,
                    target.rigidBody.getCenterOfMassPosition().z);
        } else {
            shootRayTo.set(
                    target.rigidBody.getCenterOfMassPosition().x + ammoEndOffsetX,
                    target.rigidBody.getCenterOfMassPosition().y + ammoEndOffsetY,
                    target.rigidBody.getCenterOfMassPosition().z);
        }
    }

    private void setShootRayToWithoutTarget() {
        if (lookingRight) {
            shootRayTo.set(shootRayFrom.x + 1, shootRayFrom.y, shootRayFrom.z);
        } else {
            shootRayTo.set(shootRayFrom.x - 1, shootRayFrom.y, shootRayFrom.z);
        }
    }
}