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
        initialize();
    }

    @Override
    public void updateData() {
        super.updateData();
        labelScreenTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("titleGameOver"));
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

        buttonRestart = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        buttonHome = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        Table tableButtons = new Table();
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

        InputListener inputListenerRestartWithoutAlert = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.getGameScreen().getStage().clear();
                    game.getGameWorld().resetWorld();
                    game.getGameScreen().getInGameHud().updateData();
                    game.getGameScreen().getStage().addActor(game.getGameScreen().getInGameHud());
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
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.getGameScreen().getStage().clear();
                    game.getGameWorld().clearWorld();
                    game.getMainMenuScreen().updateData();
                    game.setScreen(game.getMainMenuScreen());
                }
            }
        };

        buttonRestart.addListener(inputListenerRestartWithoutAlert);
        buttonHome.addListener(inputListenerMainMenuWithoutAlert);
    }
}