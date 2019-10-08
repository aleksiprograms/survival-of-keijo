package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class AbstractScreen implements Screen {

    public TheGame game;
    public Viewport viewport;
    public Stage stage;
    public Viewport viewportDialogBox;
    public Stage stageDialogBox;
    public boolean showStageDialogBox;

    public AbstractScreen(TheGame game) {
        this.game = game;
        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, game.spriteBatch);
        viewportDialogBox = new ScreenViewport(new OrthographicCamera());
        stageDialogBox = new Stage(viewport, game.spriteBatch);
        showStageDialogBox = false;
    }

    public void updateScreen() {
        setShowStageDialogBox(false);
        stage.setDebugAll(game.saveDataManager.saveData.isDebugDrawUI());
        stageDialogBox.setDebugAll(game.saveDataManager.saveData.isDebugDrawUI());
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
        viewport.update(width, height);
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