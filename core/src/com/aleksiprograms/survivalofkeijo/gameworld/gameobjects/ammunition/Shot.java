package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class Shot extends Ammunition {

    public Shot(TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_AMMUNITION_BULLET_PLAYER, Model.class)),
                new btBoxShape(new Vector3(0.05f, 0.05f, 0.05f)));
    }
}