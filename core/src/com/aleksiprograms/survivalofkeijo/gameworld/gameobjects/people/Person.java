package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.aleksiprograms.survivalofkeijo.toolbox.UpState;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public abstract class Person extends PhysicalObject {

    public boolean dead;
    public UpState upState;
    public int health;
    public int maxHealth;

    public Person(
            TheGame game,
            ModelInstance modelInstance,
            btCollisionShape collisionShape,
            BodyDef bodyDef) {

        super(
                game,
                modelInstance,
                collisionShape,
                bodyDef);
        dead = false;
    }

    public void onHit(int damage) {
        health += damage;
        if (health <= 0) {
            dead = true;
        }
    }

    @Override
    public void init(float x, float y, float z, float angle) {
        super.init(x, y, z, angle);
        upState = UpState.STAND;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (health <= 0) {
            dead = true;
        }
    }
}