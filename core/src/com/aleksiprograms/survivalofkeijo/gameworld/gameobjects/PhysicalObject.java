package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.ObjectMotionState;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SolidObject;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

public abstract class PhysicalObject extends RenderableObject {

    protected final btRigidBody rigidBody;
    protected Vector3 linearFactor;
    protected Vector3 angularFactor;
    protected BodyDef bodyDef;
    protected boolean destroyBody;

    public PhysicalObject(
            TheGame game,
            ModelInstance modelInstance,
            btCollisionShape collisionShape,
            BodyDef bodyDef) {

        super(game, modelInstance);

        this.bodyDef = bodyDef;
        ObjectMotionState motionState;
        if (bodyDef.isUseMotionState()) {
            motionState = new ObjectMotionState();
            motionState.transform = super.transform;
        } else {
            motionState = null;
        }
        Vector3 localInertia = new Vector3();
        if (bodyDef.getMass() > 0) {
            collisionShape.calculateLocalInertia(bodyDef.getMass(), localInertia);
        } else {
            localInertia.set(0, 0, 0);
        }
        btRigidBody.btRigidBodyConstructionInfo constructionInfo;
        constructionInfo = new btRigidBody.btRigidBodyConstructionInfo(
                bodyDef.getMass(), motionState, collisionShape, localInertia);
        constructionInfo.setFriction(bodyDef.getFriction());
        constructionInfo.setRestitution(bodyDef.getRestitution());
        constructionInfo.setLinearDamping(bodyDef.getLinearDamping());
        constructionInfo.setAngularDamping(bodyDef.getAngularDamping());
        rigidBody = new btRigidBody(constructionInfo);
        constructionInfo.dispose();
        if (bodyDef.isLockAxisZ()) {
            linearFactor = new Vector3(1, 1, 0);
            angularFactor = new Vector3(0, 0, 0);
        } else {
            linearFactor = new Vector3(1, 1, 1);
            angularFactor = new Vector3(1, 1, 1);
        }
        rigidBody.setLinearFactor(linearFactor);
        rigidBody.setAngularFactor(angularFactor);
        destroyBody = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (bodyDef.isLockAxisZ()) {
            rigidBody.setLinearFactor(linearFactor);
            rigidBody.setAngularFactor(angularFactor);
        }
        if (destroyBody) {
            game.getGameWorld().getDynamicsWorld().removeRigidBody(rigidBody);
        }
    }

    public void init(float x, float y, float z, float angle) {
        super.init();
        game.getGameWorld().getDynamicsWorld().addRigidBody(
                rigidBody, bodyDef.getCategoryBits(), bodyDef.getMaskBits());
        objectPosition.set(x, y, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.Z, angle);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        rigidBody.setWorldTransform(objectTransform);
        transform.set(rigidBody.getWorldTransform());
        rigidBody.setLinearVelocity(Vector3.Zero);
        rigidBody.setAngularVelocity(Vector3.Zero);
        if (!(this instanceof SolidObject)) {
            rigidBody.forceActivationState(Collision.DISABLE_DEACTIVATION);
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (!destroyBody) {
            game.getGameWorld().getDynamicsWorld().removeRigidBody(rigidBody);
        }
        destroyBody = false;
    }

    public btRigidBody getRigidBody() {
        return rigidBody;
    }
}