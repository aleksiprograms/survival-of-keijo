package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public abstract class AbstractScreen implements Screen {

    public TheGame game;
    public ExtendViewport viewport;
    public Stage stage;
    public Stage stageDialogBox;
    public boolean showStageDialogBox;

    public AbstractScreen(TheGame game) {
        this.game = game;
        viewport = new ExtendViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, game.cameraUI);
        stage = new Stage(viewport, game.spriteBatch);
        stageDialogBox = new Stage(viewport, game.spriteBatch);
        showStageDialogBox = false;
    }

    public void updateScreenData() {
        stage.setDebugAll(Constants.DEBUG_DRAW_UI);
        stageDialogBox.setDebugAll(Constants.DEBUG_DRAW_UI);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (showStageDialogBox) {
            stageDialogBox.act();
            stageDialogBox.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    public void setShowStageDialogBox(boolean showStageDialogBox) {
        this.showStageDialogBox = showStageDialogBox;
        if (showStageDialogBox) {
            Gdx.input.setInputProcessor(stageDialogBox);
        } else {
            Gdx.input.setInputProcessor(stage);
        }
    }
}