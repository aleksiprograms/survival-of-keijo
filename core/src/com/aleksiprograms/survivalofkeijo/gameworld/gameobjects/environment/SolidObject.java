package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;

public abstract class SolidObject extends PhysicalObject {

    public SolidObject(TheGame game, ModelInstance modelInstance, btCollisionShape shape) {
        super(game, modelInstance, shape,
                new BodyDef.BodyDefBuilder()
                        .mass(0)
                        .friction(0.3f)
                        .categoryBits(Constants.CATEGORY_SOLID)
                        .maskBits(Constants.MASK_SOLID)
                        .useMotionState(false)
                        .build());
    }
}