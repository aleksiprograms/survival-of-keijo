package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameOverHud extends AbstractHud {

    private Label labelGameModeTitle;
    private Label labelScore;
    private Label labelDistance;
    private Label labelScoreText;
    private Label labelDistanceText;
    private Image imageStar1;
    private Image imageStar2;
    private Image imageStar3;

    private TextureRegionDrawable imageStarLocked1;
    private TextureRegionDrawable imageStarUnlocked1;
    private TextureRegionDrawable imageStarLocked2;
    private TextureRegionDrawable imageStarUnlocked2;
    private TextureRegionDrawable imageStarLocked3;
    private TextureRegionDrawable imageStarUnlocked3;

    public GameOverHud(TheGame game) {
        super(game);

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        /*imageStarLocked1 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked1 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));
        imageStarLocked2 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked2 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));
        imageStarLocked3 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED)));
        imageStarUnlocked3 = new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED)));

        final ImageButton btReplay = new ImageButton(game.styles.skinImageButtonReplay);
        final ImageButton btHome = new ImageButton(game.styles.skinImageButtonHome);
        Table tableButtons = new Table();
        tableButtons.add(btReplay).padBottom(10).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);
        tableButtons.row();
        tableButtons.add(btHome).width(Constants.BUTTON_WIDTH_IMAGE).height(Constants.BUTTON_HEIGHT_IMAGE).align(Align.right);

        labelGameModeTitle = new Label("", game.styles.skinLabelTitle2);
        Image imageScore = new Image();
        imageScore.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_SCORE))));
        Image imageDistance = new Image();
        imageDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(game.getTextureRegionByID(Constants.TEX_SRC_DISTANCE))));
        labelScore = new Label(" ", game.styles.skinLabelNumberText);
        labelDistance = new Label(" ", game.styles.skinLabelNumberText);
        imageStar1 = new Image();
        imageStar2 = new Image();
        imageStar3 = new Image();
        Table tableStars = new Table();
        tableStars.add(imageStar1).align(Align.topLeft).width(labelScore.getHeight()).height(labelScore.getHeight());
        tableStars.add(imageStar2).align(Align.topLeft).padLeft(2).width(labelScore.getHeight()).height(labelScore.getHeight());
        tableStars.add(imageStar3).align(Align.topLeft).padLeft(2).width(labelScore.getHeight()).height(labelScore.getHeight());
        labelScoreText = new Label("", game.styles.skinLabelTitle3);
        labelDistanceText = new Label("", game.styles.skinLabelTitle3);

        Table tableScore = new Table();
        tableScore.add(imageScore).align(Align.right).padBottom(20).width(labelScore.getHeight()).height(labelScore.getHeight()).expandX();
        tableScore.add(labelScore).align(Align.center).padBottom(20).padLeft(20);
        tableScore.add(labelScoreText).align(Align.left).padBottom(20).padLeft(20).expandX();

        Table tableDistance = new Table();
        tableDistance.add(imageDistance).align(Align.right).padBottom(20).width(labelDistance.getHeight()).height(labelDistance.getHeight()).expandX();
        tableDistance.add(labelDistance).align(Align.center).padBottom(20).padLeft(20);
        tableDistance.add(labelDistanceText).align(Align.left).padBottom(20).padLeft(20).expandX();

        Table tableStats = new Table();
        tableStats.pad(20);

        tableStats.add(labelGameModeTitle).colspan(3).align(Align.center).padBottom(20).expandX();
        tableStats.row();
        tableStats.add(tableScore);
        tableStats.row();
        tableStats.add(tableDistance);
        tableStats.row();
        tableStats.add(tableStars).colspan(3).align(Align.center).padBottom(20).expandX();

        super.add(new Label("GAME OVER", game.styles.skinLabelWhiteHuge)).expandX().align(Align.top).colspan(2);
        super.row();
        super.add(tableStats).expand().align(Align.center);
        super.add(tableButtons).expandY().align(Align.right);

        btReplay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(y > 0 && y <  + btReplay.getHeight()) {
                    if(x > 0 && x < btReplay.getWidth()) {
                        btReplayPressed = true;
                    }
                }
            }
        });

        btHome.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(y > 0 && y <  + btHome.getHeight()) {
                    if(x > 0 && x < btHome.getWidth()) {
                        btHomePressed = true;
                    }
                }
            }
        });*/
    }

    public void init(long newScore, long newDistance) {
        /*labelGameModeTitle.setText(gameBAS.gameMode.nameID);
        if (gameBAS.gameStateManager.isNewBestScore())
            labelScoreText.setText("New best!");
        else
            labelScoreText.setText(" ");
        labelScore.setText(gameBAS.styles.getFormattedNumber(newScore));

        if (gameBAS.gameStateManager.isNewBestDistance())
            labelDistanceText.setText("New best!");
        else
            labelDistanceText.setText(" ");
        labelDistance.setText(gameBAS.styles.getFormattedNumber(newDistance) + " m");

        if (newScore >= gameBAS.gameMode.scoreToOneStar)
            imageStar1.setDrawable(imageStarUnlocked1);
        else
            imageStar1.setDrawable(imageStarLocked1);
        if (newScore >= gameBAS.gameMode.scoreToTwoStar)
            imageStar2.setDrawable(imageStarUnlocked2);
        else
            imageStar2.setDrawable(imageStarLocked2);
        if (newScore >= gameBAS.gameMode.scoreToThreeStar)
            imageStar3.setDrawable(imageStarUnlocked3);
        else
            imageStar3.setDrawable(imageStarLocked3);*/
    }
}