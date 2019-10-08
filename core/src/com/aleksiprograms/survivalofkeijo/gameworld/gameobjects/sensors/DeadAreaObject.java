package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.math.Matrix4;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public class DeadAreaObject extends SensorObject {

    public DeadAreaObject(TheGame game, Matrix4 transform, btCollisionShape shape) {
        super(
                game,
                shape,
                new BodyDef.BodyDefBuilder()
                        .categoryBits(Constants.CATEGORY_SENSOR_OBJECT)
                        .maskBits(Constants.MASK_SENSOR_OBJECT)
                        .build());

        ghostObject.setWorldTransform(transform);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}