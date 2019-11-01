package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public abstract class AbstractScreen implements Screen {

    public TheGame game;
    public ExtendViewport viewport;
    public Stage stage;
    public Stage stageDialogBox;
    public boolean showStageDialogBox;
    protected Stage stagePopup;
    protected boolean showStagePopup;
    protected float popupTimer;
    protected Label labelPopupText;

    public AbstractScreen(TheGame game) {
        this.game = game;
        viewport = new ExtendViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, game.cameraUI);
        stage = new Stage(viewport, game.spriteBatch);
        stageDialogBox = new Stage(viewport, game.spriteBatch);
        stagePopup = new Stage(viewport, game.spriteBatch);
        showStageDialogBox = false;
        showStagePopup = false;
        if (!(this instanceof LoadingScreen)) {
            initializePopupTable();
        }
    }

    public void updateScreenData() {
        stage.setDebugAll(Constants.DEBUG_DRAW_UI);
        stageDialogBox.setDebugAll(Constants.DEBUG_DRAW_UI);
        stagePopup.setDebugAll(Constants.DEBUG_DRAW_UI);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        showStagePopup = false;
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (showStageDialogBox) {
            stageDialogBox.act();
            stageDialogBox.draw();
        }
        if (Gdx.input.isTouched()) {
            showStagePopup = false;
        }
        if (showStagePopup) {
            popupTimer += deltaTime;
            if (popupTimer > Constants.POPUP_VISIBLE_TIME) {
                showStagePopup = false;
            } else {
                stagePopup.act();
                stagePopup.draw();
            }
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

    public void showPopup(String text) {
        showStagePopup = true;
        popupTimer = 0f;
        labelPopupText.setText(text);
    }

    private void initializePopupTable() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        labelPopupText = new Label("", game.styles.labelStyleWhiteSmall);
        labelPopupText.setAlignment(Align.center);
        table.add(labelPopupText).expand().align(Align.center);
        Table tablePopup = new Table();
        tablePopup.setFillParent(true);
        tablePopup.add(table).width(Constants.POPUP_WIDTH).height(Constants.POPUP_HEIGHT).expandY().align(Align.bottom).padBottom(Constants.POPUP_BOTTOM_PAD);
        stagePopup.addActor(tablePopup);
    }
}