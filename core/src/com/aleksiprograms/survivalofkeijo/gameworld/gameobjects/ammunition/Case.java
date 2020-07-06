package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public abstract class Case extends PhysicalObject {

    private float timer;

    public Case(TheGame game, ModelInstance modelInstance) {

        super(
                game,
                modelInstance,
                new btBoxShape(new Vector3(0.22f / 2f, 0.07f / 2f, 0.07f / 2f)),
                new BodyDef.BodyDefBuilder()
                        .mass(0.1f)
                        .linearDamping(0.1f)
                        .angularDamping(0.1f)
                        .friction(0.2f)
                        .lockAxisZ(false)
                        .categoryBits(Constants.CATEGORY_CASE)
                        .maskBits(Constants.MASK_CASE)
                        .build());
        getRigidBody().userData = this;
    }

    public void init(
            float x,
            float y,
            float z,
            float angle,
            Vector3 linearVelocity,
            Vector3 angularVelocity) {
        super.init(x, y, z, angle);
        getRigidBody().setLinearVelocity(linearVelocity);
        getRigidBody().setAngularVelocity(angularVelocity);
        timer = 0;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        timer += deltaTime;
        if (timer > Constants.CASE_VISIBLE_TIME) {
            free = true;
        }
    }
}