package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
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

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        buttonRestart = new TextButton("", game.styles.skinTextButtonRed);
        buttonHome = new TextButton("", game.styles.skinTextButtonRed);
        Table tableButtons = new Table();
        tableButtons.add(buttonRestart).padBottom(Constants.GAP).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);
        tableButtons.row();
        tableButtons.add(buttonHome).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);

        labelScreenTitle = new Label("", game.styles.skinLabelWhiteHuge);
        Table tableTop = new Table();
        tableTop.add(labelScreenTitle).align(Align.center);

        super.add(tableTop).height(Constants.TABLE_TOP_HEIGHT).growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        super.row();
        super.add(tableButtons).expand().align(Align.right);

        buttonRestart.addListener(inputListenerRestart);
        buttonHome.addListener(inputListenerHome);
    }

    @Override
    public void updateHud() {
        super.updateHud();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleGameOver"));
        buttonRestart.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonRestart"));
        buttonHome.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonHome"));
    }
}