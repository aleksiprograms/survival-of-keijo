package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btGhostObject;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;

public abstract class SensorObject {

    public TheGame game;
    public final btGhostObject ghostObject;
    public boolean destroy;

    public SensorObject(
            TheGame game,
            btCollisionShape collisionShape,
            BodyDef bodyDef) {

        this.game = game;
        ghostObject = new btGhostObject();
        ghostObject.setCollisionShape(collisionShape);
        ghostObject.setCollisionFlags(btCollisionObject.CollisionFlags.CF_NO_CONTACT_RESPONSE);
        ghostObject.userData = this;
        game.gameWorld.dynamicsWorld.addCollisionObject(ghostObject, bodyDef.categoryBits, bodyDef.maskBits);
        destroy = false;
    }

    public void update(float deltaTime) {
        if (destroy) {
            game.gameWorld.dynamicsWorld.removeCollisionObject(ghostObject);
        }
    }
}