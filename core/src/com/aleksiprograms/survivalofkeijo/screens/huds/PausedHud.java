package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class PausedHud extends AbstractHud {

    private Label labelScreenTitle;
    private TextButton buttonContinue;
    private TextButton buttonRestart;
    private TextButton buttonHome;

    private Label labelLevelTitle;
    private Label labelBestScore;
    private Label labelBestDistance;
    private Image imageStar1;
    private Image imageStar2;
    private Image imageStar3;
    private Label labelStarLimit1;
    private Label labelStarLimit2;
    private Label labelStarLimit3;

    private TextureRegionDrawable imageStarLocked1;
    private TextureRegionDrawable imageStarUnlocked1;
    private TextureRegionDrawable imageStarLocked2;
    private TextureRegionDrawable imageStarUnlocked2;
    private TextureRegionDrawable imageStarLocked3;
    private TextureRegionDrawable imageStarUnlocked3;

    public PausedHud(TheGame game) {
        super(game);
        initializeHud();
    }

    @Override
    public void updateHudData() {
        super.updateHudData();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titlePaused"));
        buttonContinue.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonContinue"));
        buttonRestart.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonRestart"));
        buttonHome.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonHome"));
    }

    private void initializeHud() {
        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        /*imageStarLocked1 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked1 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));
        imageStarLocked2 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked2 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));
        imageStarLocked3 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked3 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));

        labelLevelTitle = new Label("", game.styles.skinLabelTitle2);
        Image imageBestScore = new Image();
        imageBestScore.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_SCORE))));
        labelBestScore = new Label(" ", game.styles.skinLabelNumberText);
        Image imageBestDistance = new Image();
        imageBestDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_DISTANCE))));
        labelBestDistance = new Label(" ", game.styles.skinLabelNumberText);
        imageStar1 = new Image();
        imageStar2 = new Image();
        imageStar3 = new Image();
        Table tableScoreLimitStars1 = new Table();
        tableScoreLimitStars1.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        labelStarLimit1 = new Label("", game.styles.skinLabelLimitsText);
        Table tableScoreLimitStars2 = new Table();
        tableScoreLimitStars2.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).padRight(2).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        tableScoreLimitStars2.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        labelStarLimit2 = new Label("", game.styles.skinLabelLimitsText);
        Table tableScoreLimitStars3 = new Table();
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).padRight(2).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).padRight(2).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        tableScoreLimitStars3.add(new Image(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))))).width(labelBestScore.getHeight()*0.8f).height(labelBestScore.getHeight()*0.8f);
        labelStarLimit3 = new Label("", game.styles.skinLabelLimitsText);
        Table tableScoreLimits = new Table();
        tableScoreLimits.add(tableScoreLimitStars1).padRight(10).expandX().align(Align.right);
        tableScoreLimits.add(labelStarLimit1).expandX().align(Align.left);
        tableScoreLimits.row();
        tableScoreLimits.add(tableScoreLimitStars2).padRight(10).expandX().align(Align.right);
        tableScoreLimits.add(labelStarLimit2).expandX().align(Align.left);
        tableScoreLimits.row();
        tableScoreLimits.add(tableScoreLimitStars3).padRight(10).expandX().align(Align.right);
        tableScoreLimits.add(labelStarLimit3).expandX().align(Align.left);
        Table tableStars = new Table();
        tableStars.add(imageStar1).align(Align.topLeft).width(labelBestScore.getHeight()).height(labelBestScore.getHeight());
        tableStars.add(imageStar2).align(Align.topLeft).padLeft(2).width(labelBestScore.getHeight()).height(labelBestScore.getHeight());
        tableStars.add(imageStar3).align(Align.topLeft).padLeft(2).width(labelBestScore.getHeight()).height(labelBestScore.getHeight());
        Table tableInfo = new Table();
        tableInfo.add(labelLevelTitle).colspan(2).align(Align.topLeft).padBottom(10).expandX();
        tableInfo.row();
        tableInfo.add(imageBestScore).align(Align.topLeft).width(labelBestScore.getHeight()).height(labelBestScore.getHeight()).padRight(10).padBottom(10);
        tableInfo.add(labelBestScore).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(imageBestDistance).align(Align.topLeft).width(labelBestDistance.getHeight()).height(labelBestDistance.getHeight()).padRight(10).padBottom(10);
        tableInfo.add(labelBestDistance).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(tableStars).colspan(2).align(Align.topLeft).expandX().padBottom(10);
        tableInfo.row();
        tableInfo.add(new Label("Limits", game.styles.skinLabelTitle3)).colspan(2).expandX().align(Align.topLeft);
        tableInfo.row();
        tableInfo.add(tableScoreLimits).colspan(2).align(Align.topLeft).expandX().padLeft(20);
*/
        buttonContinue = new TextButton("", game.styles.textButtonStyleOrange);
        buttonRestart = new TextButton("", game.styles.textButtonStyleRed);
        buttonHome = new TextButton("", game.styles.textButtonStyleRed);
        Table tableButtons = new Table();
        tableButtons.add(buttonContinue).padBottom(Constants.GAP).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);
        tableButtons.row();
        tableButtons.add(buttonRestart).padBottom(Constants.GAP).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);
        tableButtons.row();
        tableButtons.add(buttonHome).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHuge);
        Table tableTop = new Table();
        tableTop.add(labelScreenTitle).align(Align.center);

        super.add(tableTop).height(Constants.TABLE_TOP_HEIGHT).growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        super.row();
        //super.add(tableInfo).align(Align.topLeft).expandY();
        super.add(tableButtons).expand().align(Align.right);

        buttonContinue.addListener(inputListenerContinue);
        buttonRestart.addListener(inputListenerRestart);
        buttonHome.addListener(inputListenerHome);
    }
}