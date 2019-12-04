package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public abstract class SurfaceComplex extends SolidObject {

    private btCollisionShape surfaceShape;

    public SurfaceComplex(TheGame game, ModelInstance modelInstance) {
        super(
                game,
                modelInstance,
                new btBoxShape(new Vector3(0.25f, 0.05f, 0.75f)));
    }

    public void init(float x, float y, float z, int widthFactor, btCollisionShape collisionShape) {
        game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, bodyDef.categoryBits, bodyDef.maskBits);
        objectPosition.set(x, y, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.X, 90);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        rigidBody.setWorldTransform(objectTransform);

        objectQuaternion.set(0, 0, 0, 0);
        objectScale.set(widthFactor, 1, 1);
        transform.set(objectPosition, objectQuaternion, objectScale);

        rigidBody.setLinearVelocity(Vector3.Zero);
        rigidBody.setAngularVelocity(Vector3.Zero);
        surfaceShape = collisionShape;
        rigidBody.setCollisionShape(surfaceShape);
    }
}