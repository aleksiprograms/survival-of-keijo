package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.resources.UIDimensions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class CreditsScreen extends AbstractScreen {

    private Label labelScreenTitle;

    public CreditsScreen(TheGame game) {
        super(game);
        initScreen();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.homeScreen.updateScreen();
            game.setScreen(game.homeScreen);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleCredits"));
    }

    public void initScreen() {
        Table tableCredits = new Table();
        tableCredits.top();
        tableCredits.add(new Label("DEVELOPER", game.styles.labelStyleWhiteTiny)).padBottom(UIDimensions.TEXT_BOTTOM_GAP).padRight(UIDimensions.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Aleksi Tolvanen", game.styles.labelStyleWhiteSmall)).padBottom(UIDimensions.TEXT_BOTTOM_GAP).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("POWERED BY", game.styles.labelStyleWhiteTiny)).padRight(UIDimensions.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("libGDX", game.styles.labelStyleWhiteSmall)).align(Align.left);
        tableCredits.row();
        tableCredits.add().padBottom(UIDimensions.TEXT_BOTTOM_GAP).padRight(UIDimensions.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Bullet Physics", game.styles.labelStyleWhiteSmall)).padBottom(UIDimensions.TEXT_BOTTOM_GAP).align(Align.left);
        tableCredits.row();
        tableCredits.add(new Label("FONT", game.styles.labelStyleWhiteTiny)).padBottom(UIDimensions.TEXT_BOTTOM_GAP).padRight(UIDimensions.TEXT_RIGHT_GAP).align(Align.right);
        tableCredits.add(new Label("Roboto Condensed", game.styles.labelStyleWhiteSmall)).padBottom(UIDimensions.TEXT_BOTTOM_GAP).align(Align.left);

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHuge);
        final ImageButton buttonClose = new ImageButton(game.styles.imageButtonStyleClose);
        Table tableTop = new Table();
        tableTop.add().width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).height(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).align(Align.left);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add(buttonClose).width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).height(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(tableTop).height(UIDimensions.TABLE_TOP_HEIGHT).growX().padBottom(UIDimensions.GAP).align(Align.top);
        tableContent.row();
        tableContent.add(tableCredits).expand();

        Table table = new Table();
        table.setFillParent(true);
        //table.background(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(UIDimensions.GAP);

        stage.addActor(table);
        //stage.setDebugAll(true);

        buttonClose.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonClose.getWidth() && y > 0 && y < buttonClose.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.saveData.getSoundVolume());
                    game.homeScreen.updateScreen();
                    game.setScreen(game.homeScreen);
                }
            }
        });
    }
}