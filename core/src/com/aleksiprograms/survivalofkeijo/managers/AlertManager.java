package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class AlertManager {

    private TheGame game;
    public Stage stageDialogBox;
    private boolean showStageDialogBox;
    protected Stage stagePopup;
    protected boolean showStagePopup;
    protected float popupTimer;
    protected Label labelPopupText;
    private Table tableDialogBoxExitGame;
    private Label labelDialogBoxExitGameTitle;
    private Label labelDialogBoxExitGameText;
    private TextButton buttonExitGameYes;
    private TextButton buttonExitGameNo;
    private Table tableDialogBoxToHome;
    private Label labelDialogBoxToHomeTitle;
    private Label labelDialogBoxToHomeText;
    private TextButton buttonToHomeYes;
    private TextButton buttonToHomeNo;
    private Table tableDialogBoxRestart;
    private Label labelDialogBoxRestartTitle;
    private Label labelDialogBoxRestartText;
    private TextButton buttonRestartYes;
    private TextButton buttonRestartNo;

    public AlertManager(TheGame game) {
        this.game = game;
        stageDialogBox = new Stage(game.viewport, game.spriteBatch);
        showStageDialogBox = false;
        stageDialogBox.setDebugAll(Constants.DEBUG_DRAW_UI);
        stagePopup = new Stage(game.viewport, game.spriteBatch);
        showStagePopup = false;
        stagePopup.setDebugAll(Constants.DEBUG_DRAW_UI);
        initializePopupTable();
        initializeDialogBoxExitGame();
        initializeDialogBoxToHome();
        initializeDialogBoxRestart();
    }

    public void updateAndDraw(float deltaTime) {
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

    public void showPopup(String text) {
        showStagePopup = true;
        popupTimer = 0f;
        labelPopupText.setText(text);
    }

    public void setShowStageDialogBox(boolean showStageDialogBox) {
        this.showStageDialogBox = showStageDialogBox;
        if (showStageDialogBox) {
            Gdx.input.setInputProcessor(stageDialogBox);
        } else {
            Gdx.input.setInputProcessor(game.currentScreen.stage);
        }
    }

    public void confirmExitGame() {
        updateData();
        stageDialogBox.clear();
        stageDialogBox.addActor(tableDialogBoxExitGame);
        setShowStageDialogBox(true);
    }

    public void confirmToHome() {
        updateData();
        stageDialogBox.clear();
        stageDialogBox.addActor(tableDialogBoxToHome);
        setShowStageDialogBox(true);
    }

    public void confirmRestart() {
        updateData();
        stageDialogBox.clear();
        stageDialogBox.addActor(tableDialogBoxRestart);
        setShowStageDialogBox(true);
    }

    private void updateData() {
        labelDialogBoxExitGameTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxExitGameTitle"));
        labelDialogBoxExitGameText.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxExitGameText"));
        buttonExitGameYes.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonYes"));
        buttonExitGameNo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonNo"));
        labelDialogBoxToHomeTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxToHomeTitle"));
        labelDialogBoxToHomeText.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxToHomeText"));
        buttonToHomeYes.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonYes"));
        buttonToHomeNo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonNo"));
        labelDialogBoxRestartTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxRestartTitle"));
        labelDialogBoxRestartText.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxRestartText"));
        buttonRestartYes.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonYes"));
        buttonRestartNo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonNo"));
    }

    private void initializePopupTable() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_POPUP_BACKGROUND)));
        labelPopupText = new Label("", game.styles.labelStyleWhiteSmall);
        labelPopupText.setAlignment(Align.center);
        table.add(labelPopupText).expand().align(Align.center);
        Table tablePopup = new Table();
        tablePopup.setFillParent(true);
        tablePopup.add(table).width(Constants.POPUP_WIDTH).height(Constants.POPUP_HEIGHT).expandY().align(Align.bottom).padBottom(Constants.POPUP_BOTTOM_PAD);
        stagePopup.addActor(tablePopup);
    }

    private void initializeDialogBoxExitGame() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        buttonExitGameYes = new TextButton("", game.styles.textButtonStyleGreen);
        buttonExitGameYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    Gdx.app.exit();
                }
            }
        });
        buttonExitGameNo = new TextButton("", game.styles.textButtonStyleRed);
        buttonExitGameNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    setShowStageDialogBox(false);
                }
            }
        });
        labelDialogBoxExitGameTitle = new Label("", game.styles.labelStyleWhiteMedium);
        table.add(labelDialogBoxExitGameTitle).growX().align(Align.topLeft).pad(Constants.GAP);
        table.row();
        labelDialogBoxExitGameText = new Label("", game.styles.labelStyleWhiteSmall);
        labelDialogBoxExitGameText.setAlignment(Align.center);
        table.add(labelDialogBoxExitGameText).expand().align(Align.center).pad(Constants.GAP);
        table.row();
        Table tableButtons = new Table();
        tableButtons.add(buttonExitGameNo).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom).padRight(Constants.GAP);
        tableButtons.add(buttonExitGameYes).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom);
        table.add(tableButtons).growX().align(Align.bottom).pad(Constants.GAP);
        tableDialogBoxExitGame = new Table();
        tableDialogBoxExitGame.setFillParent(true);
        tableDialogBoxExitGame.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableDialogBoxExitGame.add(table).width(Constants.DIALOG_BOX_WIDTH).height(Constants.DIALOG_BOX_HEIGHT);
    }

    private void initializeDialogBoxToHome() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        buttonToHomeYes = new TextButton("", game.styles.textButtonStyleGreen);
        buttonToHomeYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    setShowStageDialogBox(false);
                    game.gameScreen.stage.clear();
                    game.gameWorld.clearWorld();
                    game.mainMenuScreen.updateScreenData();
                    game.setScreen(game.mainMenuScreen);
                }
            }
        });
        buttonToHomeNo = new TextButton("", game.styles.textButtonStyleRed);
        buttonToHomeNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    setShowStageDialogBox(false);
                }
            }
        });
        labelDialogBoxToHomeTitle = new Label("", game.styles.labelStyleWhiteMedium);
        table.add(labelDialogBoxToHomeTitle).growX().align(Align.topLeft).pad(Constants.GAP);
        table.row();
        labelDialogBoxToHomeText = new Label("", game.styles.labelStyleWhiteSmall);
        labelDialogBoxToHomeText.setAlignment(Align.center);
        table.add(labelDialogBoxToHomeText).expand().align(Align.center).pad(Constants.GAP);
        table.row();
        Table tableButtons = new Table();
        tableButtons.add(buttonToHomeNo).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom).padRight(Constants.GAP);
        tableButtons.add(buttonToHomeYes).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom);
        table.add(tableButtons).growX().align(Align.bottom).pad(Constants.GAP);
        tableDialogBoxToHome = new Table();
        tableDialogBoxToHome.setFillParent(true);
        tableDialogBoxToHome.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableDialogBoxToHome.add(table).width(Constants.DIALOG_BOX_WIDTH).height(Constants.DIALOG_BOX_HEIGHT);
    }

    private void initializeDialogBoxRestart() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        buttonRestartYes = new TextButton("", game.styles.textButtonStyleGreen);
        buttonRestartYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    setShowStageDialogBox(false);
                    game.gameScreen.stage.clear();
                    game.gameWorld.resetWorld();
                    game.gameScreen.inGameHud.updateHudData();
                    game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
                }
            }
        });
        buttonRestartNo = new TextButton("", game.styles.textButtonStyleRed);
        buttonRestartNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    setShowStageDialogBox(false);
                }
            }
        });
        labelDialogBoxRestartTitle = new Label("", game.styles.labelStyleWhiteMedium);
        table.add(labelDialogBoxRestartTitle).growX().align(Align.topLeft).pad(Constants.GAP);
        table.row();
        labelDialogBoxRestartText = new Label("", game.styles.labelStyleWhiteSmall);
        labelDialogBoxRestartText.setAlignment(Align.center);
        table.add(labelDialogBoxRestartText).expand().align(Align.center).pad(Constants.GAP);
        table.row();
        Table tableButtons = new Table();
        tableButtons.add(buttonRestartNo).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom).padRight(Constants.GAP);
        tableButtons.add(buttonRestartYes).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom);
        table.add(tableButtons).growX().align(Align.bottom).pad(Constants.GAP);
        tableDialogBoxRestart = new Table();
        tableDialogBoxRestart.setFillParent(true);
        tableDialogBoxRestart.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableDialogBoxRestart.add(table).width(Constants.DIALOG_BOX_WIDTH).height(Constants.DIALOG_BOX_HEIGHT);
    }
}