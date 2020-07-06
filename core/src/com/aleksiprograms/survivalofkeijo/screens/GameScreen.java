package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.screens.huds.BackpackHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.GameOverHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.InGameHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.PausedHud;
import com.aleksiprograms.survivalofkeijo.screens.huds.ShopHud;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractScreen {

    private GameState currentGameState;
    private GameState previousGameState;
    private FPSLogger fpsLogger;
    private InGameHud inGameHud;
    private PausedHud pausedHud;
    private ShopHud shopHud;
    private BackpackHud backpackHud;
    private GameOverHud gameOverHud;

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
    public void render(float deltaTime) {
        fpsLogger.log();
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)
                || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (currentGameState.equals(GameState.IN_GAME)
                    || currentGameState.equals(GameState.IN_BACKPACK)
                    || currentGameState.equals(GameState.IN_SHOP)
                    || currentGameState.equals(GameState.IN_HOSPITAL)) {
                changeGameState(GameState.PAUSED);
            } else if (currentGameState.equals(GameState.PAUSED)
                    || currentGameState.equals(GameState.GAME_OVER)) {
                game.getAlertManager().confirmToHome();
            }
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(
                GL20.GL_COLOR_BUFFER_BIT
                        | GL20.GL_DEPTH_BUFFER_BIT
                        | (Gdx.graphics.getBufferFormat().coverageSampling
                        ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        game.getCameraGame().position.x = game.getGameWorld().getPlayer()
                .getRigidBody().getCenterOfMassPosition().x;
        game.getCameraGame().position.y = game.getGameWorld().getPlayer()
                .getRigidBody().getCenterOfMassPosition().y;
        game.getCameraGame().update();
        game.getGameWorld().updateAndRender(
                deltaTime,
                game.getCameraGame(),
                game.getModelBatch());
        game.getSpriteBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();
        if (showStageExtra) {
            stageExtra.act();
            stageExtra.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        game.getCameraGame().viewportHeight = height;
        game.getCameraGame().viewportWidth = width;
        game.getCameraGame().update();
        game.getGameWorld().getSky().scale((float) width / (float) height);
    }

    @Override
    public void pause() {
        changeGameState(GameState.PAUSED);
    }

    @Override
    public void hide() {
        changeGameState(GameState.PAUSED);
    }

    @Override
    public void resetScreen() {
        super.resetScreen();
        shopHud.resetHud();
    }

    @Override
    public void updateData() {
        super.updateData();
        currentGameState = GameState.IN_GAME;
        previousGameState = GameState.IN_GAME;
        game.getGameWorld().createWorld(game.getLevelManager().getCurrentLevel());
        stage.addActor(inGameHud);
        inGameHud.updateData();
        game.getCameraGame().position.set(0, 0, 8);
        game.getCameraGame().near = 1;
        game.getCameraGame().far = 60;
        game.getCameraGame().update();
    }

    public void changeGameState(GameState nextGameState) {
        previousGameState = currentGameState;
        currentGameState = nextGameState;
        stage.clear();
        if (nextGameState.equals(GameState.PAUSED)) {
            game.getGameWorld().setPaused(true);
            pausedHud.updateData();
            stage.addActor(pausedHud);
        } else if (nextGameState.equals(GameState.IN_GAME)) {
            game.getGameWorld().setPaused(false);
            inGameHud.updateData();
            stage.addActor(inGameHud);
        } else if (nextGameState.equals(GameState.GAME_OVER)) {
            gameOverHud.updateData();
            stage.addActor(gameOverHud);
        } else if (nextGameState.equals(GameState.IN_BACKPACK)) {
            game.getGameWorld().setPaused(true);
            backpackHud.updateData();
            stage.addActor(backpackHud);
        } else if (nextGameState.equals(GameState.IN_SHOP)) {
            game.getGameWorld().setPaused(true);
            shopHud.updateData();
            stage.addActor(shopHud);
        }
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public GameState getPreviousGameState() {
        return previousGameState;
    }

    public InGameHud getInGameHud() {
        return inGameHud;
    }

    public ShopHud getShopHud() {
        return shopHud;
    }
}