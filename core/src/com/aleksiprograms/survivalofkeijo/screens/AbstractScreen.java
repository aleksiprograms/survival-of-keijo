package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractScreen implements Screen {

    protected TheGame game;
    protected Stage stage;
    protected Stage stageExtra;
    protected boolean showStageExtra;

    public AbstractScreen(TheGame game) {
        this.game = game;
        stage = new Stage(game.getViewport(), game.getSpriteBatch());
        stageExtra = new Stage(game.getViewport(), game.getSpriteBatch());
        showStageExtra = false;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (showStageExtra) {
            stageExtra.act();
            stageExtra.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public void resetScreen() {
    }

    public void updateData() {
        stage.setDebugAll(Constants.DEBUG_DRAW_UI);
        stageExtra.setDebugAll(Constants.DEBUG_DRAW_UI);
    }

    public void setShowStageExtra(boolean showStageExtra) {
        this.showStageExtra = showStageExtra;
        if (showStageExtra) {
            Gdx.input.setInputProcessor(stageExtra);
        } else {
            Gdx.input.setInputProcessor(game.getCurrentScreen().stage);
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getStageExtra() {
        return stageExtra;
    }
}