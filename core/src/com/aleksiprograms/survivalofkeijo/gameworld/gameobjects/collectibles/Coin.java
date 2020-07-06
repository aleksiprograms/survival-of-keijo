package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.collectibles;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.GhostObject;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.BodyDef;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;

public class Coin extends GhostObject {

    protected boolean collected;
    protected AnimationController animationController;
    protected AnimationController.AnimationListener animationListener;

    public Coin(final TheGame game) {
        super(
                game,
                new ModelInstance(game.getAssetManager().get(
                        Constants.MODEL_AMMUNITION_BULLET_PLAYER, Model.class)),
                new btBoxShape(new Vector3(0.5f, 0.5f, 0.5f)),
                new BodyDef.BodyDefBuilder()
                        .mass(0)
                        .categoryBits(Constants.CATEGORY_COIN)
                        .maskBits(Constants.MASK_COIN)
                        .useMotionState(false)
                        .build());
        animationController = new AnimationController(this);
        animationController.setAnimation(
                "Empty|rotation", -1, 1, animationListener);
        animationListener = new AnimationController.AnimationListener() {
            @Override
            public void onEnd(AnimationController.AnimationDesc animation) {
            }

            @Override
            public void onLoop(AnimationController.AnimationDesc animation) {
            }
        };
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        animationController.update(deltaTime);
    }

    @Override
    public void init(float x, float y, float z) {
        super.init(x, y, z);
        collected = false;
    }

    public void onCollect() {
        collected = true;
        free = true;
        game.getGameWorld().getPlayer().setMoney(
                game.getGameWorld().getPlayer().getMoney() + 5000);
        game.getGameScreen().getInGameHud().updateData();
    }

    public boolean isCollected() {
        return collected;
    }
}