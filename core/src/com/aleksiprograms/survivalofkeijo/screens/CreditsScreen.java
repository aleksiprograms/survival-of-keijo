package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class CreditsScreen extends AbstractScreen {

    private Label labelScreenTitle;
    private TextButton buttonSettings;

    public CreditsScreen(TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)
                || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.getMainMenuScreen().updateData();
            game.setScreen(game.getMainMenuScreen());
        }
    }

    @Override
    public void updateData() {
        super.updateData();
        labelScreenTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("titleCredits"));
        buttonSettings.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonSettings"));
    }

    private void initialize() {
        Table tableCredits = new Table();
        tableCredits.top();
        tableCredits.add(new Label("DEVELOPER",
                game.getStyles().getLabelStyleWhiteTiny()))
                .padBottom(Constants.TEXT_BOTTOM_GAP)
                .padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Aleksi Tolvanen",
                game.getStyles().getLabelStyleWhiteSmall()))
                .padBottom(Constants.TEXT_BOTTOM_GAP).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("POWERED BY",
                game.getStyles().getLabelStyleWhiteTiny()))
                .padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("libGDX",
                game.getStyles().getLabelStyleWhiteSmall())).align(Align.left);
        tableCredits.row();
        tableCredits.add()
                .padBottom(Constants.TEXT_BOTTOM_GAP)
                .padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Bullet Physics",
                game.getStyles().getLabelStyleWhiteSmall()))
                .padBottom(Constants.TEXT_BOTTOM_GAP).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("FONT",
                game.getStyles().getLabelStyleWhiteTiny()))
                .padBottom(Constants.TEXT_BOTTOM_GAP)
                .padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Roboto Condensed",
                game.getStyles().getLabelStyleWhiteSmall()))
                .padBottom(Constants.TEXT_BOTTOM_GAP).align(Align.left);

        buttonSettings = new TextButton("",
                game.getStyles().getTextButtonStyleOrange());
        Table tableButtons = new Table();
        tableButtons.add(buttonSettings).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);

        labelScreenTitle = new Label("",
                game.getStyles().getLabelStyleWhiteHugeWithBorder());
        final ImageButton buttonClose = new ImageButton(
                game.getStyles().getImageButtonStyleClose());
        Table tableTop = new Table();
        tableTop.add().align(Align.left)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add(buttonClose).align(Align.right)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);

        Table tableContent = new Table();
        tableContent.add(tableTop).colspan(2).height(Constants.TABLE_TOP_HEIGHT)
                .growX().padBottom(Constants.GAP).align(Align.top);
        tableContent.row();
        tableContent.add(tableCredits).expand();
        tableContent.add(tableButtons).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS_LOADING,
                TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND)));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);

        buttonClose.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonClose.getWidth()
                        && y > 0 && y < buttonClose.getHeight()) {
                    game.getMainMenuScreen().updateData();
                    game.setScreen(game.getMainMenuScreen());
                }
            }
        });

        buttonSettings.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonSettings.getWidth()
                        && y > 0 && y < buttonSettings.getHeight()) {
                    game.getSettingsScreen().updateData();
                    game.setScreen(game.getSettingsScreen());
                }
            }
        });
    }
}