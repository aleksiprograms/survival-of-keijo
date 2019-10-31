package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public abstract class AbstractHud extends Table {

    public TheGame game;
    private Table tableDialogBoxMainMenu;
    private Table tableDialogBoxRestart;
    private Label labelDialogBoxToHomeTitle;
    private Label labelDialogBoxRestartTitle;
    private Label labelDialogBoxToHomeText;
    private Label labelDialogBoxRestartText;
    private TextButton buttonToHomeYes;
    private TextButton buttonToHomeNo;
    private TextButton buttonRestartYes;
    private TextButton buttonRestartNo;
    InputListener inputListenerPause;
    InputListener inputListenerContinue;
    InputListener inputListenerClose;
    InputListener inputListenerRestart;
    InputListener inputListenerHome;
    InputListener inputListenerEnterShop;
    InputListener inputListenerEnterBackpack;
    InputListener inputListenerEnterHome;

    AbstractHud(final TheGame game) {
        this.game = game;
        initializeHud();
    }

    public void updateHudData() {
        labelDialogBoxToHomeTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxToHomeTitle"));
        labelDialogBoxRestartTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxRestartTitle"));
        labelDialogBoxToHomeText.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxToHomeText"));
        labelDialogBoxRestartText.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxRestartText"));
        buttonToHomeYes.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonYes"));
        buttonToHomeNo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonNo"));
        buttonRestartYes.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonYes"));
        buttonRestartNo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonNo"));
    }

    private void initializeHud() {
        initializeDialogBoxToHome();
        initializeDialogBoxRestart();

        /*inputListenerHome = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < btHome.getHeight() && x > 0 && x < btHome.getWidth()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.savedData.getSoundVolume());
                    stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            //gameBAS.setScreen(gameBAS.homeScreen);
                        }
                    })));
                }
            }
        };*/

        inputListenerPause = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = true;
                    game.gameScreen.pausedHud.updateHudData();
                    game.gameScreen.stage.addActor(game.gameScreen.pausedHud);
                }
            }
        };

        inputListenerContinue = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = false;
                    game.gameScreen.inGameHud.updateHudData();
                    game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
                }
            }
        };

        inputListenerClose = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_SMALL && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_SMALL) {
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = false;
                    game.gameScreen.inGameHud.updateHudData();
                    game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
                }
            }
        };

        inputListenerRestart = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.gameScreen.stageDialogBox.clear();
                    game.gameScreen.stageDialogBox.addActor(tableDialogBoxRestart);
                    game.gameScreen.setShowStageDialogBox(true);
                }
            }
        };

        inputListenerHome = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.gameScreen.stageDialogBox.clear();
                    game.gameScreen.stageDialogBox.addActor(tableDialogBoxMainMenu);
                    game.gameScreen.setShowStageDialogBox(true);
                }
            }
        };

        inputListenerEnterShop = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM * 2 && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = true;
                    game.gameScreen.shopHud.updateHudData();
                    game.gameScreen.stage.addActor(game.gameScreen.shopHud);
                }
            }
        };

        inputListenerEnterBackpack = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = true;
                    game.gameScreen.backpackHud.updateHudData();
                    game.gameScreen.stage.addActor(game.gameScreen.backpackHud);
                }
            }
        };

        inputListenerEnterHome = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM * 2 && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {}
            }
        };

        /*if (hud.btPausePressed) {
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_POS).play(gameBAS.saveManager.savedData.getSoundVolume());
            hud.btPausePressed = false;
            gameBAS.gameStateManager.setLevelPaused();
        }
        if (hud.btReplayPressed) {
            gameBAS.sounds.gameMusic.stop();
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_POS).play(gameBAS.saveManager.savedData.getSoundVolume());
            if (gameBAS.gameStateManager.getLevelState().equals(LevelState.PAUSED)) {
                gameBAS.saveManager.savedData.addToLevelsAbandoned();
                gameBAS.achievementsManager.unlockAchievement(AchievementsIDs.ACH_ABANDONED);
            }
            hud.btReplayPressed = false;
            gameBAS.game.clearWorld2();
            gameBAS.setScreen(gameBAS.weaponsScreen);
        }
        if (hud.btPlayPressed) {
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_POS).play(gameBAS.saveManager.savedData.getSoundVolume());
            hud.btPlayPressed = false;
            gameBAS.gameStateManager.setLevelGaming();
        }
        if (hud.btHomePressed) {
            gameBAS.sounds.gameMusic.stop();
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_NEG).play(gameBAS.saveManager.savedData.getSoundVolume());
            if (gameBAS.gameStateManager.getLevelState().equals(LevelState.PAUSED)) {
                gameBAS.saveManager.savedData.addToLevelsAbandoned();
                gameBAS.achievementsManager.unlockAchievement(AchievementsIDs.ACH_ABANDONED);
            }
            hud.btHomePressed = false;
            gameBAS.game.clearWorld2();
            gameBAS.setScreen(new HomeScreen(gameBAS));
        }*/
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
                    game.gameScreen.setShowStageDialogBox(false);
                    game.gameScreen.stage.clear();
                    game.gameWorld.clearWorld();
                    game.homeScreen.updateScreenData();
                    game.setScreen(game.homeScreen);
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
                    game.gameScreen.setShowStageDialogBox(false);
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
        tableDialogBoxMainMenu = new Table();
        tableDialogBoxMainMenu.setFillParent(true);
        tableDialogBoxMainMenu.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableDialogBoxMainMenu.add(table).width(Constants.DIALOG_BOX_WIDTH).height(Constants.DIALOG_BOX_HEIGHT);
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
                    game.gameScreen.setShowStageDialogBox(false);
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
                    game.gameScreen.setShowStageDialogBox(false);
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