package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class GroundTop extends SolidObject {

    private btBoxShape groundShape;
    private Vector3 groundDimensions;

    public GroundTop(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_WALKABLE_GROUND_TOP, Model.class)),
                new btBoxShape(new Vector3(0.25f, 0.2f, 0.75f)));

        groundDimensions = new Vector3(0.25f, 0.2f, 0.75f);
        groundShape = new btBoxShape(groundDimensions);
        rigidBody.userData = this;
    }

    public void init(float x, float y, float z, int widthFactor) {
        game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, bodyDef.categoryBits, bodyDef.maskBits);
        objectPosition.set(x, y, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.Z, 0);
        objectScale.set(1, 1, 1);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        rigidBody.setWorldTransform(objectTransform);
        groundDimensions.set(widthFactor*0.25f, 0.2f, 0.75f);
        groundShape = new btBoxShape(groundDimensions);
        rigidBody.setCollisionShape(groundShape);
        objectScale.set(widthFactor, 1, 1);
        transform.set(objectPosition, objectQuaternion, objectScale);
        calculateBoundingBox(boundingBox);
        boundingBox.getCenter(center);
        boundingBox.getDimensions(dimensions);
        dimensions.set(dimensions.x * widthFactor, dimensions.y, dimensions.z);
    }
}