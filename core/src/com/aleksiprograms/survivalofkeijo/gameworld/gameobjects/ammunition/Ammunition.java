package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public abstract class Ammunition extends PhysicalObject {

    public int damage;
    public boolean hit;
    public float angle;

    public Ammunition(
            TheGame game,
            ModelInstance modelInstance,
            btCollisionShape collisionShape) {

        super(
                game,
                modelInstance,
                collisionShape,
                new BodyDef.BodyDefBuilder()
                        .mass(0.1f)
                        .categoryBits(Constants.CATEGORY_WEAPON_ENEMY)
                        .maskBits(Constants.MASK_WEAPON_ENEMY)
                        .build());
        rigidBody.userData = this;
    }

    public void init(int damage, float x, float y, float z, float angle, Vector3 velocity, short categoryBits, short maskBits) {
        this.damage = -damage;
        game.gameWorld.dynamicsWorld.addRigidBody(rigidBody, categoryBits, maskBits);
        objectPosition.set(x, y, z);
        objectQuaternion.set(0, 0, 0, 0);
        objectQuaternion.set(Vector3.Z, angle);
        objectTransform.set(objectPosition, objectQuaternion, objectScale);
        rigidBody.setWorldTransform(objectTransform);
        transform.set(rigidBody.getWorldTransform());
        rigidBody.forceActivationState(Collision.DISABLE_DEACTIVATION);
        hit = false;
        this.angle = angle;
        rigidBody.setCcdMotionThreshold((float)1e-7);
        rigidBody.setCcdSweptSphereRadius(0.03f);
        rigidBody.setGravity(new Vector3(0, 0, 0));
        rigidBody.setLinearVelocity(velocity);
    }

    public void onHit(Vector3 hitPoint, PhysicalObject hitObject) {
        free = true;
        hit = true;
    }
}