package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class PausedHud extends AbstractHud {

    private Label labelScreenTitle;
    private TextButton buttonContinue;
    private TextButton buttonRestart;
    private TextButton buttonHome;

    public PausedHud(TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        super.updateData();
        labelScreenTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("titlePaused"));
        buttonContinue.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonContinue"));
        buttonRestart.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonRestart"));
        buttonHome.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonMainMenu"));
    }

    @Override
    protected void initialize() {
        super.initialize();

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        buttonContinue = new TextButton("",
                game.getStyles().getTextButtonStyleOrange());
        buttonRestart = new TextButton("",
                game.getStyles().getTextButtonStyleRed());
        buttonHome = new TextButton("",
                game.getStyles().getTextButtonStyleRed());
        Table tableButtons = new Table();
        tableButtons.add(buttonContinue).padBottom(Constants.GAP).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);
        tableButtons.row();
        tableButtons.add(buttonRestart).padBottom(Constants.GAP).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);
        tableButtons.row();
        tableButtons.add(buttonHome).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);

        labelScreenTitle = new Label("",
                game.getStyles().getLabelStyleWhiteHugeWithBorder());
        Table tableTop = new Table();
        tableTop.add(labelScreenTitle).align(Align.center);

        super.add(tableTop).growX().padBottom(Constants.GAP).align(Align.top).colspan(2)
                .height(Constants.TABLE_TOP_HEIGHT);
        super.row();
        super.add(tableButtons).expand().align(Align.right);

        InputListener inputListenerContinue = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.getGameScreen().getStage().clear();
                    if (game.getGameScreen().getPreviousGameState().equals(
                            GameState.IN_GAME)) {
                        game.getGameScreen().changeGameState(GameState.IN_GAME);
                    } else if (game.getGameScreen().getPreviousGameState().equals(
                            GameState.IN_BACKPACK)) {
                        game.getGameScreen().changeGameState(GameState.IN_BACKPACK);
                    } else if (game.getGameScreen().getPreviousGameState().equals(
                            GameState.IN_SHOP)) {
                        game.getGameScreen().changeGameState(GameState.IN_SHOP);
                    }
                }
            }
        };

        InputListener inputListenerRestart = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.getAlertManager().confirmRestart();
                }
            }
        };

        InputListener inputListenerMainMenu = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.getAlertManager().confirmToHome();
                }
            }
        };

        buttonContinue.addListener(inputListenerContinue);
        buttonRestart.addListener(inputListenerRestart);
        buttonHome.addListener(inputListenerMainMenu);
    }
}