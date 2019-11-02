package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class AbstractHud extends Table {

    public TheGame game;
    InputListener inputListenerPause;
    InputListener inputListenerContinue;
    InputListener inputListenerClose;
    InputListener inputListenerRestart;
    InputListener inputListenerHome;
    InputListener inputListenerEnterShop;
    InputListener inputListenerEnterBackpack;
    InputListener inputListenerEnterHospital;

    AbstractHud(final TheGame game) {
        this.game = game;
        initializeHud();
    }

    public void updateHudData() {}

    private void initializeHud() {
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
                    game.gameScreen.setGameState(GameState.PAUSED);
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
                    if (game.gameScreen.previousGameState.equals(GameState.IN_GAME)) {
                        game.gameScreen.setGameState(GameState.IN_GAME);
                        game.gameWorld.paused = false;
                        game.gameScreen.inGameHud.updateHudData();
                        game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
                    } else if (game.gameScreen.previousGameState.equals(GameState.IN_BACKPACK)) {
                        game.gameScreen.setGameState(GameState.IN_BACKPACK);
                        game.gameWorld.paused = true;
                        game.gameScreen.backpackHud.updateHudData();
                        game.gameScreen.stage.addActor(game.gameScreen.backpackHud);
                    } else if (game.gameScreen.previousGameState.equals(GameState.IN_SHOP)) {
                        game.gameScreen.setGameState(GameState.IN_SHOP);
                        game.gameWorld.paused = true;
                        game.gameScreen.shopHud.updateHudData();
                        game.gameScreen.stage.addActor(game.gameScreen.shopHud);
                    }
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
                    game.gameScreen.setGameState(GameState.IN_GAME);
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
                    game.alertManager.confirmRestart();
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
                    game.alertManager.confirmToHome();
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
                    game.gameScreen.setGameState(GameState.IN_SHOP);
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
                    game.gameScreen.setGameState(GameState.IN_BACKPACK);
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = true;
                    game.gameScreen.backpackHud.updateHudData();
                    game.gameScreen.stage.addActor(game.gameScreen.backpackHud);
                }
            }
        };

        inputListenerEnterHospital = new InputListener() {
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
}