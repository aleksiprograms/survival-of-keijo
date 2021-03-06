package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btGhostObject;

public abstract class GhostObject extends RenderableObject {

    protected final btGhostObject ghostObject;
    protected BodyDef bodyDef;

    public GhostObject(
            TheGame game,
            ModelInstance modelInstance,
            btCollisionShape collisionShape,
            BodyDef bodyDef) {

        super(game, modelInstance);
        this.bodyDef = bodyDef;
        ghostObject = new btGhostObject();
        ghostObject.setCollisionShape(collisionShape);
        ghostObject.setCollisionFlags(btCollisionObject.CollisionFlags.CF_NO_CONTACT_RESPONSE);
        ghostObject.userData = this;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public void init(float x, float y, float z) {
        super.init();
        game.getGameWorld().getDynamicsWorld().addCollisionObject(
                ghostObject, bodyDef.getCategoryBits(), bodyDef.getMaskBits());
        objectPosition.set(x, y, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.Z, 0);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        ghostObject.setWorldTransform(objectTransform);
        transform.set(ghostObject.getWorldTransform());
    }

    @Override
    public void reset() {
        super.reset();
        game.getGameWorld().getDynamicsWorld().removeCollisionObject(ghostObject);
    }
}