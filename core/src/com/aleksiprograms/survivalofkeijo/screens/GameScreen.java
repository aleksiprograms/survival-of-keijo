package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Styles;
import com.aleksiprograms.survivalofkeijo.resources.UIDimensions;
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
    public void updateScreen() {
        super.updateScreen();
        game.gameWorld.createWorld();
        stage.addActor(inGameHud);
        inGameHud.updateHudData();
        game.camera.position.set(0, 0, 8);
        game.camera.near = 1;
        game.camera.far = 60;
        game.camera.update();
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
        game.camera.position.x = game.gameWorld.player.rigidBody.getCenterOfMassPosition().x;
        if (game.gameWorld.player.upState.equals(UpState.STAND)) {
            game.camera.position.y = game.gameWorld.player.rigidBody.getCenterOfMassPosition().y;
        } else if (game.gameWorld.player.upState.equals(UpState.CROUCH)) {
            game.camera.position.y = game.gameWorld.player.rigidBody.getCenterOfMassPosition().y + 0.25f;
        } else if (game.gameWorld.player.upState.equals(UpState.PRONE)) {
            game.camera.position.y = game.gameWorld.player.rigidBody.getCenterOfMassPosition().y + 0.75f;
        }
        game.camera.update();
        game.gameWorld.updateAndRenderGameWorld(deltaTime, game.camera, game.modelBatch);
        //game.spriteBatch.begin();
        //game.spriteBatch.end();
        game.spriteBatch.setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();
        if (showStageDialogBox) {
            stageDialogBox.act();
            stageDialogBox.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.camera.viewportHeight = height;
        game.camera.viewportWidth = width;
        game.camera.update();
        viewport.update(width, height, true);

        System.out.println("width = " + Gdx.graphics.getWidth() + " and height = " + Gdx.graphics.getHeight());

        UIDimensions.calculate();
        game.styles.setStyles();
        inGameHud.createHudTable();
        //stage.clear();
        //game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
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
        for (int i = 0; i < game.gameScreen.stage.getActors().size; i++) {
            game.gameScreen.stage.getActors().get(i).remove();
        }
        game.gameWorld.paused = true;
        game.gameScreen.pausedHud.updateHudData();
        game.gameScreen.stage.addActor(game.gameScreen.pausedHud);
    }
}