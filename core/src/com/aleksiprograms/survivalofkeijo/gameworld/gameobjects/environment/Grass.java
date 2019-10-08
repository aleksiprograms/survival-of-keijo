package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class Grass extends SolidObject {

    private btBoxShape grassShape;
    private Vector3 grassDimensions;

    public Grass(TheGame game) {
        super(
                game,
                new ModelInstance(game.assetManager.get(Constants.MODEL_WALKABLE_GRASS, Model.class)),
                new btBoxShape(new Vector3(0.25f, 0.05f, 0.75f)));

        grassDimensions = new Vector3(0.25f, 0.05f, 0.75f);
        grassShape = new btBoxShape(grassDimensions);
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
        grassDimensions.set(widthFactor*0.25f, 0.05f, 0.75f);
        grassShape = new btBoxShape(grassDimensions);
        rigidBody.setCollisionShape(grassShape);
        objectScale.set(widthFactor, 1, 1);
        transform.set(objectPosition, objectQuaternion, objectScale);
        calculateBoundingBox(boundingBox);
        boundingBox.getCenter(center);
        boundingBox.getDimensions(dimensions);
        dimensions.set(dimensions.x * widthFactor, dimensions.y, dimensions.z);
    }
}