package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class LoadingScreen extends AbstractScreen {

    private ProgressBar progressBar;
    private float progress;
    private Label labelInstruction;

    public LoadingScreen(TheGame game) {
        super(game);
        initScreen();
        progress = 0f;
        game.loadAssets();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        progress = game.assetManager.getProgress();
        if (game.assetManager.update()) {
            game.loadRest();
            game.homeScreen.updateScreen();
            game.setScreen(game.homeScreen);
        } else {
            progressBar.setValue(progress);
            stage.act();
            stage.draw();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        labelInstruction.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLoading"));
    }

    private void initScreen() {
        Skin skinProgressBar = new Skin();
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS_LOADING, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF));
        progressBarStyle.knobBefore = new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS_LOADING, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL));
        progressBarStyle.background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
        skinProgressBar.add("default-horizontal", progressBarStyle);

        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/RobotoCondensed-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter;
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Constants.TEXT_SIZE_SMALL;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        font = generator.generateFont(parameter);
        generator.dispose();
        Skin skinLabel = new Skin();
        skinLabel.add("default", font);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skinLabel.getFont("default");
        labelStyle.fontColor = new Color(0xFFFFFFFF);
        skinLabel.add("default", labelStyle);

        progressBar = new ProgressBar(0,1,0.0001f,false, skinProgressBar);
        progressBar.setValue(progress);
        progressBar.setVisualInterpolation(new Interpolation.Pow(1));

        //Image imageGame = new Image(new TextureRegionDrawable(new TextureRegion(gameBAS.getStartTexture("game_logo"))));
        labelInstruction = new Label("", skinLabel);

        Table tableContent = new Table();
        //tableContent.add(imageGame).align(Align.center).expand().width(300).height(300);
        tableContent.add(progressBar).width(Constants.PROGRESS_BAR_WIDTH_LOADING).padBottom(Constants.GAP);
        tableContent.row();
        tableContent.add(labelInstruction);

        Table table = new Table();
        table.setFillParent(true);
        //table.background(new TextureRegionDrawable(new TextureRegion(gameBAS.getStartTexture("background"))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);
    }
}