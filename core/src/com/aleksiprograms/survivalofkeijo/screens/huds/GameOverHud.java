package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class GameOverHud extends AbstractHud {

    private Label labelScreenTitle;
    private TextButton buttonRestart;
    private TextButton buttonHome;

    public GameOverHud(TheGame game) {
        super(game);
        initializeHud();
    }

    @Override
    public void updateHud() {
        super.updateHud();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleGameOver"));
        buttonRestart.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonRestart"));
        buttonHome.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonMainMenu"));
    }

    @Override
    protected void initializeHud() {
        super.initializeHud();

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        buttonRestart = new TextButton("", game.styles.textButtonStyleOrange);
        buttonHome = new TextButton("", game.styles.textButtonStyleOrange);
        Table tableButtons = new Table();
        tableButtons.add(buttonRestart).padBottom(Constants.GAP).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);
        tableButtons.row();
        tableButtons.add(buttonHome).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHugeWithBorder);
        Table tableTop = new Table();
        tableTop.add(labelScreenTitle).align(Align.center);

        super.add(tableTop).height(Constants.TABLE_TOP_HEIGHT).growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        super.row();
        super.add(tableButtons).expand().align(Align.right);

        InputListener inputListenerRestartWithoutAlert = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.gameScreen.stage.clear();
                    game.gameWorld.resetWorld();
                    game.gameScreen.inGameHud.updateHud();
                    game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
                }
            }
        };

        InputListener inputListenerMainMenuWithoutAlert = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.gameScreen.stage.clear();
                    game.gameWorld.clearWorld();
                    game.mainMenuScreen.updateScreen();
                    game.setScreen(game.mainMenuScreen);
                }
            }
        };

        buttonRestart.addListener(inputListenerRestartWithoutAlert);
        buttonHome.addListener(inputListenerMainMenuWithoutAlert);
    }
}