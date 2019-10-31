package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.data.WeaponData;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.RenderableObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Person;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.UpState;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;

public abstract class Weapon extends RenderableObject {

    public WeaponData weaponData;
    public Person owner;
    public Person target;
    private Node weaponBone;
    private Vector3 uselessPosition;
    public boolean ownerDead;
    public Vector3 ownerDeadPosition;
    public UpState upStateCurrent;
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
    public float ownerOffsetX = 0;
    public float ownerOffsetY = 0;
    public float targetOffsetX = 0;
    public float targetOffsetY = 0;
    public float weaponTimer = 0;
    private float handsOffsetY = 0.5f;
    private float handsOffsetX = 0;
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
    public boolean animateOnMoving;
    public boolean reloading;
    public boolean changeMagWithoutReload;
    public boolean visibleToPlayer;
    public boolean animate;
    public AnimationController animationController;
    public AnimationController.AnimationListener animationListener;

    public Weapon(TheGame game, ModelInstance modelInstance,
                  float ammoOffsetX, float ammoOffsetY, float ammoOffsetZ,
                  boolean animateOnMoving) {
        super(game, modelInstance);
        ownerDeadPosition = new Vector3();
        upStateCurrent = UpState.STAND;
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
        this.animateOnMoving = animateOnMoving;
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
    }

    public void updateWeaponWhenMoreAmmoIsGiven() {}

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
                if (!animateOnMoving) {
                    weaponQuaternion.set(Vector3.Z, -66);
                } else {
                    weaponQuaternion.set(Vector3.Z, -66 + 90);
                }
                transform.set(weaponPosition, weaponQuaternion, weaponScale);
            } else {
                weaponPosition.set(
                        ownerDeadPosition.x - weaponBone.globalTransform.getTranslation(uselessPosition).x,
                        ownerDeadPosition.y + weaponBone.globalTransform.getTranslation(uselessPosition).y,
                        ownerDeadPosition.z + weaponBone.globalTransform.getTranslation(uselessPosition).z);
                if (!animateOnMoving) {
                    weaponQuaternion.set(Vector3.Z, 66);
                } else {
                    weaponQuaternion.set(Vector3.Z, 66 - 90);
                }
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
    }

    private void updateTransform() {
        if (owner.upState.equals(UpState.STAND)) {
            ownerOffsetX = 0f;
            ownerOffsetY = 0.9f - (0.9f - handsOffsetY - ammoOffsetY);
            handsOffsetY = 0.5f;
        } else if (owner.upState.equals(UpState.CROUCH)) {
            ownerOffsetX = 0f;
            ownerOffsetY = 0.65f - (0.65f - handsOffsetY - ammoOffsetY);
            handsOffsetX = 0;
            handsOffsetY = 0.33f;
        } else if (owner.upState.equals(UpState.PRONE)) {
            if (lookingRight) {
                ownerOffsetX = 0.9f - (0.9f - handsOffsetY);
                handsOffsetX = 0.5f;
            } else {
                ownerOffsetX = -(0.9f - (0.9f - handsOffsetY));
                handsOffsetX = -0.5f;
            }
            ownerOffsetY = 0f;
            handsOffsetY = 0;
        }
        if (target != null) {
            if (target.upState.equals(UpState.STAND)) {
                targetOffsetX = 0f;
                targetOffsetY = 0.5f; //0.4f
            } else if (target.upState.equals(UpState.CROUCH)) {
                targetOffsetX = 0f;
                targetOffsetY = 0.2f;
            } else if (target.upState.equals(UpState.PRONE)) {
                targetOffsetX = 0.5f; //0.4f
                targetOffsetY = 0f;
            }
        }
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
        if ((playerWeapon && target == null) || animateOnMoving || reloading) {
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
                    (target.rigidBody.getCenterOfMassPosition().y + targetOffsetY) - (owner.rigidBody.getCenterOfMassPosition().y + ownerOffsetY),
                    (target.rigidBody.getCenterOfMassPosition().x - targetOffsetX) - (owner.rigidBody.getCenterOfMassPosition().x + ownerOffsetX));
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

    public void use(float x1, float y1, float x2, float y2) {}

    public void reload() {}

    public void usePlayer() {
        setShootRayFromAccurate();
        if (target != null) {
            setShootRayToWithTarget();
        } else {
            setShootRayToWithoutTarget();
        }
        use(shootRayFrom.x, shootRayFrom.y, shootRayTo.x, shootRayTo.y);
    }

    public void used() {
        if (automatic) {
            lastShot = 0;
            canUse = false;
            shotDelayPast = false;
        }
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
                    target.rigidBody.getCenterOfMassPosition().x - targetOffsetX,
                    target.rigidBody.getCenterOfMassPosition().y + targetOffsetY,
                    target.rigidBody.getCenterOfMassPosition().z);
        } else {
            shootRayTo.set(
                    target.rigidBody.getCenterOfMassPosition().x + targetOffsetX,
                    target.rigidBody.getCenterOfMassPosition().y + targetOffsetY,
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