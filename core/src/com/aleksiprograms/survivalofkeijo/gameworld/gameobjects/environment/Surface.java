package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public abstract class Surface extends SolidObject {

    private btBoxShape surfaceShape;
    private Vector3 surfaceDimensions;

    public Surface(TheGame game, ModelInstance modelInstance) {
        super(
                game,
                modelInstance,
                new btBoxShape(new Vector3(0.25f, 0.05f, 0.75f)));

        surfaceDimensions = new Vector3(0.25f, 0.05f, 0.75f);
        surfaceShape = new btBoxShape(surfaceDimensions);
    }

    public void init(float x, float y, float z, int widthFactor) {
        game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, bodyDef.categoryBits, bodyDef.maskBits);
        objectPosition.set(x, y, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.Z, 0);
        objectScale.set(1, 1, 1);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        rigidBody.setWorldTransform(objectTransform);
        surfaceDimensions.set(widthFactor*0.25f, 0.05f, 0.75f);
        surfaceShape = new btBoxShape(surfaceDimensions);
        rigidBody.setCollisionShape(surfaceShape);
        objectScale.set(widthFactor, 1, 1);
        transform.set(objectPosition, objectQuaternion, objectScale);
        calculateBoundingBox(boundingBox);
        boundingBox.getCenter(center);
        boundingBox.getDimensions(dimensions);
        dimensions.set(dimensions.x * widthFactor, dimensions.y, dimensions.z);
    }
}