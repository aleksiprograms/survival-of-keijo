package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.screens.huds.BackpackHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.GameOverHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.ShopHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.InGameHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.PausedHud;
import com.aleksiprograms.survivalofkeijo.toolbox.UpState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractScreen {

    private FPSLogger fpsLogger;
    public InGameHud inGameHud;
    public PausedHud pausedHud;
    public ShopHud shopHud;
    public BackpackHud backpackHud;
    public GameOverHud gameOverHud;

    public GameScreen(TheGame game) {
        super(game);
        fpsLogger = new FPSLogger();
        inGameHud = new InGameHud(game);
        pausedHud = new PausedHud(game);
        shopHud = new ShopHud(game);
        backpackHud = new BackpackHud(game);
        gameOverHud = new GameOverHud(game);
    }

    @Override
    public void updateScreenData() {
        super.updateScreenData();
        game.gameWorld.createWorld();
        stage.addActor(inGameHud);
        inGameHud.updateHudData();
        game.cameraGame.position.set(0, 0, 8);
        game.cameraGame.near = 1;
        game.cameraGame.far = 60;
        game.cameraGame.update();
    }

    @Override
    public void show() {
        super.show();
        //stage.setDebugAll(true);
        //currentLevelState = LevelState.GAMING;
        //previousLevelState = currentLevelState;
        //stage.addActor(inGameHud);
        //InputMultiplexer inputMultiplexer = new InputMultiplexer();
        //inputMultiplexer.addProcessor(hudManager.stage);
    }

    @Override
    public void render(float deltaTime) {
        fpsLogger.log();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        game.cameraGame.position.x = game.gameWorld.player.rigidBody.getCenterOfMassPosition().x;
        if (game.gameWorld.player.upState.equals(UpState.STAND)) {
            game.cameraGame.position.y = game.gameWorld.player.rigidBody.getCenterOfMassPosition().y;
        } else if (game.gameWorld.player.upState.equals(UpState.CROUCH)) {
            game.cameraGame.position.y = game.gameWorld.player.rigidBody.getCenterOfMassPosition().y + 0.25f;
        } else if (game.gameWorld.player.upState.equals(UpState.PRONE)) {
            game.cameraGame.position.y = game.gameWorld.player.rigidBody.getCenterOfMassPosition().y + 0.75f;
        }
        game.cameraGame.update();
        game.gameWorld.updateAndRenderGameWorld(deltaTime, game.cameraGame, game.modelBatch);
        //game.spriteBatch.begin();
        //game.spriteBatch.end();
        game.spriteBatch.setProjectionMatrix(stage.getCamera().combined);
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
        super.resize(width, height);
        game.cameraGame.viewportHeight = height;
        game.cameraGame.viewportWidth = width;
        game.cameraGame.update();
    }

    @Override
    public void pause() {
        if (!game.gameWorld.paused) {
            pauseGame();
        }
    }

    @Override
    public void hide() {
        if (!game.gameWorld.paused) {
            pauseGame();
        }
    }

    private void pauseGame() {
        game.gameScreen.stage.clear();
        game.gameWorld.paused = true;
        game.gameScreen.pausedHud.updateHudData();
        game.gameScreen.stage.addActor(game.gameScreen.pausedHud);
    }
}