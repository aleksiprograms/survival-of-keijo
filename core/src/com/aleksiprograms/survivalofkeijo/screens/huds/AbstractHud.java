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

public abstract class AbstractHud extends Table {

    TheGame game;
    InputListener inputListenerPause;
    InputListener inputListenerContinue;
    InputListener inputListenerClose;
    InputListener inputListenerRestart;
    InputListener inputListenerHome;
    InputListener inputListenerEnterShop;
    InputListener inputListenerEnterBackpack;
    InputListener inputListenerEnterHome;
    Table tableDialogBoxMainMenu;
    Table tableDialogBoxRestart;

    AbstractHud(final TheGame game) {
        this.game = game;

        initDialogBoxMainMenu();
        initDialogBoxRestart();

        /*inputListenerHome = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < btHome.getHeight() && x > 0 && x < btHome.getWidth()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.saveData.getSoundVolume());
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
                    for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                        game.gameScreen.stage.getActors().get(i).remove();
                    }
                    game.gameWorld.paused = true;
                    game.gameScreen.pausedHud.updateHud();
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
                    for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                        game.gameScreen.stage.getActors().get(i).remove();
                    }
                    game.gameWorld.paused = false;
                    game.gameScreen.inGameHud.updateHud();
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
                    for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                        game.gameScreen.stage.getActors().get(i).remove();
                    }
                    game.gameWorld.paused = false;
                    game.gameScreen.inGameHud.updateHud();
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
                    for (int i = 0; i < game.gameScreen.stageDialogBox.getActors().size; i++) {
                        game.gameScreen.stageDialogBox.getActors().get(i).remove();
                    }
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
                    for (int i = 0; i < game.gameScreen.stageDialogBox.getActors().size; i++) {
                        game.gameScreen.stageDialogBox.getActors().get(i).remove();
                    }
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
                    for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                        game.gameScreen.stage.getActors().get(i).remove();
                    }
                    game.gameWorld.paused = true;
                    game.gameScreen.shopHud.updateHud();
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
                    for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                        game.gameScreen.stage.getActors().get(i).remove();
                    }
                    game.gameWorld.paused = true;
                    game.gameScreen.backpackHud.updateHud();
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
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_POS).play(gameBAS.saveManager.saveData.getSoundVolume());
            hud.btPausePressed = false;
            gameBAS.gameStateManager.setLevelPaused();
        }
        if (hud.btReplayPressed) {
            gameBAS.sounds.gameMusic.stop();
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_POS).play(gameBAS.saveManager.saveData.getSoundVolume());
            if (gameBAS.gameStateManager.getLevelState().equals(LevelState.PAUSED)) {
                gameBAS.saveManager.saveData.addToLevelsAbandoned();
                gameBAS.achievementsManager.unlockAchievement(AchievementsIDs.ACH_ABANDONED);
            }
            hud.btReplayPressed = false;
            gameBAS.game.clearWorld2();
            gameBAS.setScreen(gameBAS.weaponsScreen);
        }
        if (hud.btPlayPressed) {
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_POS).play(gameBAS.saveManager.saveData.getSoundVolume());
            hud.btPlayPressed = false;
            gameBAS.gameStateManager.setLevelGaming();
        }
        if (hud.btHomePressed) {
            gameBAS.sounds.gameMusic.stop();
            gameBAS.sounds.getSoundByID(SoundIDs.BUTTON_NEG).play(gameBAS.saveManager.saveData.getSoundVolume());
            if (gameBAS.gameStateManager.getLevelState().equals(LevelState.PAUSED)) {
                gameBAS.saveManager.saveData.addToLevelsAbandoned();
                gameBAS.achievementsManager.unlockAchievement(AchievementsIDs.ACH_ABANDONED);
            }
            hud.btHomePressed = false;
            gameBAS.game.clearWorld2();
            gameBAS.setScreen(new HomeScreen(gameBAS));
        }*/
    }

    public void updateHud() {}

    private void initDialogBoxMainMenu() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        TextButton buttonYes = new TextButton("YES", game.styles.skinTextButtonGreen);
        buttonYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                        game.gameScreen.stage.getActors().get(i).remove();
                    }
                    game.gameWorld.clearWorld();
                    game.homeScreen.updateScreen();
                    game.setScreen(game.homeScreen);
                }
            }
        });
        TextButton buttonNo = new TextButton("NO", game.styles.skinTextButtonRed);
        buttonNo.addListener(new InputListener() {
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
        table.add(new Label("CONFIRM EXIT", game.styles.skinLabelWhiteMedium)).growX().align(Align.topLeft).pad(Constants.GAP);
        table.row();
        Label labelText = new Label("Do you want to return to main menu?\nUnsaved data will lost.", game.styles.skinLabelWhiteSmall);
        labelText.setAlignment(Align.center);
        table.add(labelText).expand().align(Align.center).pad(Constants.GAP);
        table.row();
        Table tableButtons = new Table();
        tableButtons.add(buttonNo).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom).padRight(Constants.GAP);
        tableButtons.add(buttonYes).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom);
        table.add(tableButtons).growX().align(Align.bottom).pad(Constants.GAP);
        tableDialogBoxMainMenu = new Table();
        tableDialogBoxMainMenu.setFillParent(true);
        tableDialogBoxMainMenu.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableDialogBoxMainMenu.add(table).width(Constants.DIALOG_BOX_WIDTH).height(Constants.DIALOG_BOX_HEIGHT);
    }

    private void initDialogBoxRestart() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        TextButton buttonYes = new TextButton("YES", game.styles.skinTextButtonGreen);
        buttonYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
                        game.gameScreen.stage.getActors().get(i).remove();
                    }
                    game.gameWorld.resetWorld();
                    game.gameScreen.inGameHud.updateHud();
                    game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
                    game.gameScreen.setShowStageDialogBox(false);
                }
            }
        });
        TextButton buttonNo = new TextButton("NO", game.styles.skinTextButtonRed);
        buttonNo.addListener(new InputListener() {
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
        table.add(new Label("CONFIRM RESTART", game.styles.skinLabelWhiteMedium)).growX().align(Align.topLeft).pad(Constants.GAP);
        table.row();
        Label labelText = new Label("Do you want to RESTART the game?\nYou will lose previous save to this level!", game.styles.skinLabelWhiteSmall);
        labelText.setAlignment(Align.center);
        table.add(labelText).expand().align(Align.center).pad(Constants.GAP);
        table.row();
        Table tableButtons = new Table();
        tableButtons.add(buttonNo).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom).padRight(Constants.GAP);
        tableButtons.add(buttonYes).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom);
        table.add(tableButtons).growX().align(Align.bottom).pad(Constants.GAP);
        tableDialogBoxRestart = new Table();
        tableDialogBoxRestart.setFillParent(true);
        tableDialogBoxRestart.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableDialogBoxRestart.add(table).width(Constants.DIALOG_BOX_WIDTH).height(Constants.DIALOG_BOX_HEIGHT);
    }
}