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
        initializeScreen();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.mainMenuScreen.updateScreen();
            game.setScreen(game.mainMenuScreen);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleCredits"));
        buttonSettings.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonSettings"));
    }

    private void initializeScreen() {
        Table tableCredits = new Table();
        tableCredits.top();
        tableCredits.add(new Label("DEVELOPER", game.styles.labelStyleWhiteTiny)).padBottom(Constants.TEXT_BOTTOM_GAP).padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Aleksi Tolvanen", game.styles.labelStyleWhiteSmall)).padBottom(Constants.TEXT_BOTTOM_GAP).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("POWERED BY", game.styles.labelStyleWhiteTiny)).padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("libGDX", game.styles.labelStyleWhiteSmall)).align(Align.left);
        tableCredits.row();
        tableCredits.add().padBottom(Constants.TEXT_BOTTOM_GAP).padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Bullet Physics", game.styles.labelStyleWhiteSmall)).padBottom(Constants.TEXT_BOTTOM_GAP).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("FONT", game.styles.labelStyleWhiteTiny)).padBottom(Constants.TEXT_BOTTOM_GAP).padRight(Constants.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Roboto Condensed", game.styles.labelStyleWhiteSmall)).padBottom(Constants.TEXT_BOTTOM_GAP).align(Align.left);

        buttonSettings = new TextButton("", game.styles.textButtonStyleOrange);
        Table tableButtons = new Table();
        tableButtons.add(buttonSettings).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHuge);
        final ImageButton buttonClose = new ImageButton(game.styles.imageButtonStyleClose);
        Table tableTop = new Table();
        tableTop.add().width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.left);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add(buttonClose).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(tableTop).colspan(2).height(Constants.TABLE_TOP_HEIGHT).growX().padBottom(Constants.GAP).align(Align.top);
        tableContent.row();
        tableContent.add(tableCredits).expand();
        tableContent.add(tableButtons).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS_LOADING, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND)));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);

        buttonClose.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonClose.getWidth() && y > 0 && y < buttonClose.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.savedData.getSoundVolume());
                    game.mainMenuScreen.updateScreen();
                    game.setScreen(game.mainMenuScreen);
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
                if (x > 0 && x < buttonSettings.getWidth() && y > 0 && y < buttonSettings.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.savedData.getSoundVolume());
                    game.settingsScreen.updateScreen();
                    game.setScreen(game.settingsScreen);
                }
            }
        });
    }
}