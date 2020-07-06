package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.RenderableObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Ammunition;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Case;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.weapondata.WeaponData;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.utils.Pool;

public abstract class Weapon<A extends Ammunition, B extends Case> extends RenderableObject {

    protected WeaponData weaponData;
    protected boolean playerWeapon;
    protected Person owner;
    protected Person target;
    protected Pool<A> ammoPool;
    protected Pool<B> casePool;
    protected boolean visibleToPlayer;
    protected int inMagazine;
    protected boolean reloading;
    protected boolean automatic;
    protected boolean noAmmo;
    protected boolean noAmmoInMagazine;
    protected boolean canUse;
    protected boolean changeMagWithoutReload;
    protected boolean doThingsAtUse;
    protected float loadTime = 0;
    protected boolean useTimer = false;
    protected boolean onCriticalAnimation;
    protected boolean reloadAfterCriticalAnimation;
    protected Node weaponBone;
    protected boolean ownerDead;
    protected Vector3 ownerDeadPosition;
    protected boolean lookingRight;
    protected Vector3 weaponPosition;
    protected Quaternion weaponQuaternion;
    protected Vector3 weaponScale;
    protected float weaponAngleDeg = 0;
    protected boolean calculateWeaponAngle;
    protected Vector3 ammoHitPoint;
    protected Vector3 shootRayFrom;
    protected Vector3 shootRayTo;
    protected ClosestRayResultCallback shootCallback;
    protected float reactionTime;
    protected float readyToShootTimer;
    protected float weaponTimer = 0;
    protected float lastShot;
    protected boolean shotDelayPast;
    protected Vector3 ammoVelocity;
    protected Vector3 caseVelocity;
    protected Vector3 caseAngularVelocity;
    protected float ammoOffsetX;
    protected float ammoOffsetY;
    protected float ammoOffsetZ;
    protected float ammoStartOffsetX;
    protected float ammoStartOffsetY;
    protected float ammoEndOffsetX;
    protected float ammoEndOffsetY;
    protected float handsOffsetY;
    protected float handsOffsetX;
    protected float caseOffsetX;
    protected float caseOffsetY;
    protected float caseOffsetZ;
    protected boolean animate;
    protected AnimationController animationController;
    protected AnimationController.AnimationListener animationListener;
    protected AnimationController.AnimationListener animationListenerAfterUse;
    protected AnimationController.AnimationListener animationListenerAfterUseEmpty;
    protected AnimationController.AnimationListener animationListenerAfterMagazineEmpty;
    protected AnimationController.AnimationListener animationListenerAfterMagazineNotEmpty;
    protected AnimationController.AnimationListener animationListenerAfterReload;

    public Weapon(final TheGame game, ModelInstance modelInstance,
                  float ammoOffsetX, float ammoOffsetY, float ammoOffsetZ,
                  float caseOffsetX, float caseOffsetY, float caseOffsetZ) {
        super(game, modelInstance);
        ammoVelocity = new Vector3();
        ownerDeadPosition = new Vector3();
        weaponPosition = new Vector3();
        weaponQuaternion = new Quaternion();
        weaponScale = new Vector3(1, 1, 1);
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
                    game.getGameScreen().getInGameHud().updateData();
                    animationController.setAnimation(
                            "Armature|magazine-empty-stand",
                            1,
                            1 / (weaponData.getReloadTime() * 3f / 4f),
                            animationListenerAfterMagazineEmpty);
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
                animationController.setAnimation(
                        "Armature|reload",
                        1,
                        1 / (weaponData.getReloadTime() * 1f / 4f),
                        animationListenerAfterReload);
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };
        animationListenerAfterMagazineNotEmpty = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                animationController.setAnimation(
                        "Armature|reload-not",
                        1,
                        1 / (weaponData.getReloadTime() * 1f / 10f),
                        animationListenerAfterReload);
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {

            }
        };
        animationListenerAfterReload = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
                animationController.setAnimation("Armature|moving", -1);
                if (weaponData.getNumberOfAmmo() >= weaponData.getMagazineSize()) {
                    inMagazine = weaponData.getMagazineSize();
                } else {
                    inMagazine = weaponData.getNumberOfAmmo();
                }
                canUse = true;
                noAmmoInMagazine = false;
                reloading = false;
                onCriticalAnimation = false;
                animate = false;
                useTimer = false;
                changeMagWithoutReload = false;
                game.getGameScreen().getInGameHud().updateData();
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
                this.ammoPool = (Pool<A>) game.getGameObjectPools().getRocketPool();
            } else {
                this.ammoPool = (Pool<A>) game.getGameObjectPools().getBulletPlayerPool();
            }
        } else {
            this.ammoPool = (Pool<A>) game.getGameObjectPools().getBulletEnemyPool();
        }
        doThingsAtUse = false;
        if (weaponData.getNumberOfAmmo() >= weaponData.getMagazineSize()) {
            inMagazine = weaponData.getMagazineSize();
            noAmmo = false;
            canUse = true;
        } else if (weaponData.getNumberOfAmmo() > 0) {
            inMagazine = weaponData.getNumberOfAmmo();
            noAmmo = false;
            canUse = true;
        } else {
            inMagazine = 0;
            noAmmo = true;
            canUse = false;
            animationController.setAnimation("Armature|moving-empty", -1);
        }

        if (playerWeapon) {
            this.casePool = (Pool<B>) game.getGameObjectPools().getCasePlayerPool();
        } else {
            this.casePool = (Pool<B>) game.getGameObjectPools().getCaseEnemyPool();
        }
    }

    public void updateWeaponWhenMoreAmmoIsGiven() {
        if (noAmmo) {
            if (weaponData.getNumberOfAmmo() > 0) {
                noAmmo = false;
                noAmmoInMagazine = false;
                reloading = true;
                useTimer = true;
                animate = true;
                onCriticalAnimation = true;
                game.getGameScreen().getInGameHud().updateData();
                animationController.setAnimation(
                        "Armature|magazine-empty-stand",
                        1,
                        1 / (weaponData.getReloadTime() * 3f / 4f),
                        animationListenerAfterMagazineEmpty);
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
            if (lastShot >= (1 / weaponData.getRateOfFire())) {
                lastShot = (1 / weaponData.getRateOfFire()) + 0.1f;
                shotDelayPast = true;
            }
            if (!reloading && shotDelayPast && !noAmmo && !noAmmoInMagazine) {
                canUse = true;
            }
        }
        if (ownerDead) {
            if (owner.getRigidBody() != null) {
                ownerDeadPosition.set(owner.getRigidBody().getCenterOfMassPosition());
            }
            if (lookingRight) {
                weaponPosition.set(
                        ownerDeadPosition.x
                                + weaponBone.globalTransform.getTranslation(new Vector3()).x,
                        ownerDeadPosition.y
                                + weaponBone.globalTransform.getTranslation(new Vector3()).y,
                        ownerDeadPosition.z
                                + weaponBone.globalTransform.getTranslation(new Vector3()).z);
                weaponQuaternion.set(Vector3.Z, -66);
                transform.set(weaponPosition, weaponQuaternion, weaponScale);
            } else {
                weaponPosition.set(
                        ownerDeadPosition.x
                                - weaponBone.globalTransform.getTranslation(new Vector3()).x,
                        ownerDeadPosition.y
                                + weaponBone.globalTransform.getTranslation(new Vector3()).y,
                        ownerDeadPosition.z
                                + weaponBone.globalTransform.getTranslation(new Vector3()).z);
                weaponQuaternion.set(Vector3.Z, 66);
                transform.set(weaponPosition, weaponQuaternion, weaponScale);
                transform.rotate(Vector3.Y, 180);
            }
        } else {
            if (visibleToPlayer || playerWeapon) {
                updateTransform();
                if (!playerWeapon && !game.getGameWorld().getPlayer().isDead()) {
                    if (shootCallback.hasHit()
                            && shootCallback.getCollisionObject().userData == target) {
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
                weaponPosition.set(
                        owner.getRigidBody().getCenterOfMassPosition().x,
                        owner.getRigidBody().getCenterOfMassPosition().y + handsOffsetY,
                        owner.getRigidBody().getCenterOfMassPosition().z);
                transform.set(weaponPosition, weaponQuaternion, weaponScale);
            }
        }

        // UNLIMITED AMMUNITION FOR ENEMIES
        if (!playerWeapon) {
            if (weaponData.getNumberOfAmmo() < 1000) {
                weaponData.setNumberOfAmmo(weaponData.getNumberOfAmmo() + 10000);
            }
        }
        if (reloadAfterCriticalAnimation) {
            reloadAfterCriticalAnimation = false;
            if (inMagazine < weaponData.getMagazineSize()
                    && (weaponData.getNumberOfAmmo() - inMagazine) > 0) {
                canUse = false;
                animate = true;
                reloading = true;
                useTimer = true;
                changeMagWithoutReload = true;
                game.getGameScreen().getInGameHud().updateData();
                animationController.setAnimation(
                        "Armature|magazine-not-empty-stand",
                        1,
                        1 / (weaponData.getReloadTime() * 9f / 10f),
                        animationListenerAfterMagazineNotEmpty);
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
        if (!playerWeapon && !game.getGameWorld().getPlayer().isDead()) {
            setShootRayFromNotAccurate();
            setShootRayToWithTarget();
            shootCallback.setCollisionObject(null);
            shootCallback.setClosestHitFraction(1);
            shootCallback.setRayFromWorld(shootRayFrom);
            shootCallback.setRayToWorld(shootRayTo);
            game.getGameWorld().getDynamicsWorld().rayTest(shootRayFrom, shootRayTo, shootCallback);
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
                    (target.getRigidBody().getCenterOfMassPosition().y + ammoEndOffsetY)
                            - (owner.getRigidBody().getCenterOfMassPosition().y + ammoStartOffsetY),
                    (target.getRigidBody().getCenterOfMassPosition().x - ammoEndOffsetX)
                            - (owner.getRigidBody().getCenterOfMassPosition().x + ammoStartOffsetX));
        }
        if (lookingRight) {
            weaponPosition.set(
                    owner.getRigidBody().getCenterOfMassPosition().x
                            + weaponBone.globalTransform.getTranslation(new Vector3()).x,
                    owner.getRigidBody().getCenterOfMassPosition().y
                            + weaponBone.globalTransform.getTranslation(new Vector3()).y,
                    owner.getRigidBody().getCenterOfMassPosition().z
                            + weaponBone.globalTransform.getTranslation(new Vector3()).z);
            weaponQuaternion.set(Vector3.Z, weaponAngleDeg - 90);
            transform.set(weaponPosition, weaponQuaternion, weaponScale);
        } else {
            weaponPosition.set(
                    owner.getRigidBody().getCenterOfMassPosition().x
                            + weaponBone.globalTransform.getTranslation(new Vector3()).x,
                    owner.getRigidBody().getCenterOfMassPosition().y
                            + weaponBone.globalTransform.getTranslation(new Vector3()).y,
                    owner.getRigidBody().getCenterOfMassPosition().z
                            + weaponBone.globalTransform.getTranslation(new Vector3()).z);
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
            weaponData.setNumberOfAmmo(weaponData.getNumberOfAmmo() - 1);
            if (inMagazine > 0) {
                animationController.setAnimation(
                        "Armature|use",
                        1,
                        8,
                        animationListenerAfterUse);
            } else {
                canUse = false;
                noAmmoInMagazine = true;
                if (weaponData.getNumberOfAmmo() <= 0) {
                    noAmmo = true;
                }
                animationController.setAnimation(
                        "Armature|use-empty",
                        1,
                        8,
                        animationListenerAfterUseEmpty);
            }
            ammoVelocity.set(x2 - x1, y2 - y1, 0);
            ammoVelocity = ammoVelocity.nor();
            ammoVelocity = ammoVelocity.scl(weaponData.getAmmoSpeed());
            game.getGameWorld().addAmmunition(
                    ammoPool.obtain(),
                    weaponData.getAmmoDamage(),
                    x1,
                    y1,
                    lookingRight ? ammoOffsetZ : -ammoOffsetZ,
                    weaponAngleDeg, ammoVelocity,
                    playerWeapon
                            ? Constants.CATEGORY_WEAPON_PLAYER : Constants.CATEGORY_WEAPON_ENEMY,
                    playerWeapon
                            ? Constants.MASK_WEAPON_PLAYER : Constants.MASK_WEAPON_ENEMY);
            if (automatic) {
                lastShot = 0;
                canUse = false;
                shotDelayPast = false;
            }
            game.getGameScreen().getInGameHud().updateData();
            if (lookingRight) {
                caseVelocity.set(
                        -MathUtils.random(2f, 3f) * MathUtils.sinDeg(weaponAngleDeg)
                                - MathUtils.random(1f, 1.5f) * MathUtils.cosDeg(weaponAngleDeg),
                        MathUtils.random(2f, 3f) * MathUtils.cosDeg(weaponAngleDeg)
                                - MathUtils.random(1f, 1.5f) * MathUtils.sinDeg(weaponAngleDeg),
                        MathUtils.random(0.3f, 0.6f));
                caseAngularVelocity.set(
                        0,
                        -MathUtils.random(8f, 12f),
                        MathUtils.random(8f, 12f));
            } else {
                caseVelocity.set(
                        MathUtils.random(2f, 3f) * MathUtils.sinDeg(weaponAngleDeg)
                                - MathUtils.random(1f, 1.5f) * MathUtils.cosDeg(weaponAngleDeg),
                        -MathUtils.random(2f, 3f) * MathUtils.cosDeg(weaponAngleDeg)
                                - MathUtils.random(1f, 1.5f) * MathUtils.sinDeg(weaponAngleDeg),
                        -MathUtils.random(0.3f, 0.6f));
                caseAngularVelocity.set(
                        0,
                        MathUtils.random(8f, 12f),
                        -MathUtils.random(8f, 12f));
            }
            game.getGameWorld().addCase(
                    casePool.obtain(),
                    x1 + ((caseOffsetX - ammoOffsetX) * MathUtils.cosDeg(weaponAngleDeg)
                            - (caseOffsetY - ammoOffsetY) * MathUtils.sinDeg(weaponAngleDeg)),
                    y1 + ((caseOffsetY - ammoOffsetY) * MathUtils.cosDeg(weaponAngleDeg)
                            + (caseOffsetX - ammoOffsetX) * MathUtils.sinDeg(weaponAngleDeg)),
                    lookingRight ? caseOffsetZ : -caseOffsetZ,
                    weaponAngleDeg,
                    caseVelocity,
                    caseAngularVelocity);
        }
    }

    public void reload() {
        if (!reloading) {
            if (inMagazine < weaponData.getMagazineSize()
                    && (weaponData.getNumberOfAmmo() - inMagazine) > 0) {
                if (onCriticalAnimation) {
                    reloadAfterCriticalAnimation = true;
                } else {
                    canUse = false;
                    animate = true;
                    reloading = true;
                    useTimer = true;
                    changeMagWithoutReload = true;
                    game.getGameScreen().getInGameHud().updateData();
                    animationController.setAnimation(
                            "Armature|magazine-not-empty-stand",
                            1,
                            1 / (weaponData.getReloadTime() * 9f / 10f),
                            animationListenerAfterMagazineNotEmpty);
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
                    owner.getRigidBody().getCenterOfMassPosition().x + handsOffsetX
                            + (ammoOffsetX * MathUtils.cosDeg(weaponAngleDeg)
                            - ammoOffsetY * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.getRigidBody().getCenterOfMassPosition().y + handsOffsetY
                            + (ammoOffsetY * MathUtils.cosDeg(weaponAngleDeg)
                            + ammoOffsetX * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.getRigidBody().getCenterOfMassPosition().z);
        } else {
            shootRayFrom.set(
                    owner.getRigidBody().getCenterOfMassPosition().x + handsOffsetX
                            + (ammoOffsetX * MathUtils.cosDeg(weaponAngleDeg)
                            - (-ammoOffsetY) * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.getRigidBody().getCenterOfMassPosition().y + handsOffsetY
                            + ((-ammoOffsetY) * MathUtils.cosDeg(weaponAngleDeg)
                            + ammoOffsetX * MathUtils.sinDeg(weaponAngleDeg)),
                    owner.getRigidBody().getCenterOfMassPosition().z);
        }
    }

    private void setShootRayFromNotAccurate() {
        if (lookingRight) {
            shootRayFrom.set(
                    owner.getRigidBody().getCenterOfMassPosition().x + 0.25f,
                    owner.getRigidBody().getCenterOfMassPosition().y + handsOffsetY,
                    owner.getRigidBody().getCenterOfMassPosition().z);
        } else {
            shootRayFrom.set(
                    owner.getRigidBody().getCenterOfMassPosition().x - 0.25f,
                    owner.getRigidBody().getCenterOfMassPosition().y + handsOffsetY,
                    owner.getRigidBody().getCenterOfMassPosition().z);
        }
    }

    private void setShootRayToWithTarget() {
        if (lookingRight) {
            shootRayTo.set(
                    target.getRigidBody().getCenterOfMassPosition().x - ammoEndOffsetX,
                    target.getRigidBody().getCenterOfMassPosition().y + ammoEndOffsetY,
                    target.getRigidBody().getCenterOfMassPosition().z);
        } else {
            shootRayTo.set(
                    target.getRigidBody().getCenterOfMassPosition().x + ammoEndOffsetX,
                    target.getRigidBody().getCenterOfMassPosition().y + ammoEndOffsetY,
                    target.getRigidBody().getCenterOfMassPosition().z);
        }
    }

    private void setShootRayToWithoutTarget() {
        if (lookingRight) {
            shootRayTo.set(shootRayFrom.x + 1, shootRayFrom.y, shootRayFrom.z);
        } else {
            shootRayTo.set(shootRayFrom.x - 1, shootRayFrom.y, shootRayFrom.z);
        }
    }

    public WeaponData getWeaponData() {
        return weaponData;
    }

    public void setTarget(Person target) {
        this.target = target;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public void setOwnerDead(boolean ownerDead) {
        this.ownerDead = ownerDead;
    }

    public int getInMagazine() {
        return inMagazine;
    }

    public boolean isReloading() {
        return reloading;
    }

    public void setLookingRight(boolean lookingRight) {
        this.lookingRight = lookingRight;
    }

    public void setVisibleToPlayer(boolean visibleToPlayer) {
        this.visibleToPlayer = visibleToPlayer;
    }
}