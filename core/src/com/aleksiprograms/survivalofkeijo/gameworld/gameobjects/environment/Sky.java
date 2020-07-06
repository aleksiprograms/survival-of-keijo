package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.RenderableObject;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Sky extends RenderableObject {

    public Sky(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_SKY_BLUE, Model.class)));
        objectScale = new Vector3(120, 55, 1);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        objectPosition.set(game.getGameWorld().getPlayer().getRigidBody().getCenterOfMassPosition());
        transform.set(objectPosition, objectQuaternion, objectScale);
    }

    public void scale(float scaleX) {
        objectScale.set(scaleX * 55, 55, 1);
        transform.set(objectPosition, objectQuaternion, objectScale);
    }
}