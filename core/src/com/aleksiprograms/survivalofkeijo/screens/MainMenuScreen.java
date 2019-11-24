package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.LevelInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class MainMenuScreen extends AbstractScreen {

    private Label labelScreenTitle;
    private LevelInfo[] levelInfos;
    private Table[] btLevel = new Table[Constants.NUMBER_OF_LEVELS];

    class LevelData {
        Label labelScore;
        Label labelDistance;
        Image imageStar1;
        Image imageStar2;
        Image imageStar3;
    }
    private LevelData[] levelData = new LevelData[Constants.NUMBER_OF_LEVELS];

    public MainMenuScreen(TheGame game) {
        super(game);
        levelInfos = game.levelManager.getLevelInfos();
        for (int i = 0; i < 3; i++) {
            levelData[i] = new LevelData();
        }
        initializeScreen();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.alertManager.confirmExitGame();
        }
    }

    @Override
    public void updateScreenData() {
        super.updateScreenData();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleMainMenu"));
    }

    private void initializeScreen() {
        final ImageButton buttonGameControllerSignedIn = new ImageButton(game.styles.imageButtonStyleGameControllerSignedIn);
        final ImageButton buttonGameControllerSignedOut = new ImageButton(game.styles.imageButtonStyleGameControllerSignedOut);
        buttonGameControllerSignedOut.setVisible(false);
        Stack stackGameController = new Stack();
        stackGameController.add(buttonGameControllerSignedIn);
        stackGameController.add(buttonGameControllerSignedOut);
        final ImageButton buttonAchievements = new ImageButton(game.styles.imageButtonStyleAchievements);
        final ImageButton buttonLeaderboards = new ImageButton(game.styles.imageButtonStyleLeaderboards);
        final ImageButton buttonSettings = new ImageButton(game.styles.imageButtonStyleSettings);

        Table tableLevels = new Table();
        for (int i = 0; i < Constants.NUMBER_OF_LEVELS; i++) {
            tableLevels.add(initLevelTable(levelInfos[i], i)).align(Align.center).width(Constants.LEVEL_BUTTON_WIDTH).height(Constants.LEVEL_BUTTON_HEIGHT).padRight(i < Constants.NUMBER_OF_LEVELS - 1 ? Constants.GAP : 0).padBottom(Constants.GAP);
        }

        ScrollPane scrollPaneLevels = new ScrollPane(tableLevels, game.styles.scrollPaneStyle);
        scrollPaneLevels.setScrollingDisabled(false, true);
        scrollPaneLevels.setForceScroll(true, false);
        scrollPaneLevels.setupOverscroll(Constants.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneLevels.setFadeScrollBars(false);
        scrollPaneLevels.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneLevels.updateVisualScroll();
        scrollPaneLevels.layout();

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHuge);
        Table tableTop = new Table();
        tableTop.add(stackGameController).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.left);
        tableTop.add(buttonAchievements).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).padLeft(Constants.GAP).padRight(Constants.GAP).align(Align.left);
        tableTop.add(buttonLeaderboards).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.left);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add().width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);
        tableTop.add().width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).padLeft(Constants.GAP).padRight(Constants.GAP).align(Align.right);
        tableTop.add(buttonSettings).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(tableTop).height(Constants.TABLE_TOP_HEIGHT).growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(scrollPaneLevels).fill().expand().align(Align.center);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS_LOADING, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND)));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);

        buttonGameControllerSignedIn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonGameControllerSignedIn.getWidth() && y > 0 && y < buttonGameControllerSignedIn.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.savedData.getSoundVolume());
                    game.alertManager.showPopup("SIGNED IN to google play games.\nSign out from settings.");
                }
            }
        });

        buttonGameControllerSignedOut.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonGameControllerSignedOut.getWidth() && y > 0 && y < buttonGameControllerSignedOut.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.savedData.getSoundVolume());
                    game.alertManager.showPopup("SIGNED OUT from google play games.\nSign in from settings.");
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
                    game.settingsScreen.updateScreenData();
                    game.setScreen(game.settingsScreen);
                }
            }
        });
    }

    private Table initLevelTable(final LevelInfo levelInfo, final int index) {
        /*Table tableTop = new Table();
        tableTop.sky(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
        Label labelTitle = new Label(levelInfo.nameID, gameBAS.styles.skinLabelTitle2);
        tableTop.add(labelTitle).expandX().align(Align.center);

        Image imageBackground = new Image(new TextureRegionDrawable(gameBAS.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_BLACK)));
        Image imageGameMode = new Image(new TextureRegionDrawable(gameBAS.getTextureRegionByID(levelInfo.imageName)));
        Table tableImage = new Table();
        tableImage.add(imageGameMode).width(300).height(150).expand().align(Align.bottom);
        Table tableInfo = new Table();
        tableInfo.setWidth(310);
        tableInfo.setHeight(380);
        Image imageScore = new Image();
        imageScore.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_SCORE))));
        levelData[index].labelScore = new Label("0", gameBAS.styles.skinLabelNumberText);
        Image imageDistance = new Image();
        imageDistance.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_DISTANCE))));
        levelData[index].labelDistance = new Label("0", gameBAS.styles.skinLabelNumberText);
        levelData[index].imageStar1 = new Image();
        levelData[index].imageStar2 = new Image();
        levelData[index].imageStar3 = new Image();

        Table tableScore = new Table();
        tableScore.add(imageScore).width(levelData[index].labelScore.getHeight()).height(levelData[index].labelScore.getHeight());
        tableScore.add(levelData[index].labelScore).padLeft(10);
        Table tableDistance = new Table();
        tableDistance.add(imageDistance).width(levelData[index].labelScore.getHeight()).height(levelData[index].labelScore.getHeight());
        tableDistance.add(levelData[index].labelDistance).padLeft(10);
        Table tableStars = new Table();
        tableStars.add(levelData[index].imageStar1).width(levelData[index].labelScore.getHeight()).height(levelData[index].labelScore.getHeight());
        tableStars.add(levelData[index].imageStar2).padLeft(2).width(levelData[index].labelScore.getHeight()).height(levelData[index].labelScore.getHeight());
        tableStars.add(levelData[index].imageStar3).padLeft(2).width(levelData[index].labelScore.getHeight()).height(levelData[index].labelScore.getHeight());

        tableInfo.add(tableScore).align(Align.top).padTop(30).expandX();
        tableInfo.row();
        tableInfo.add(tableDistance).align(Align.top).padTop(10).expandX();
        tableInfo.row();
        tableInfo.add(tableStars).align(Align.top).padTop(10).expand();
        Stack stackInfo = new Stack();
        stackInfo.add(imageBackground);
        stackInfo.add(tableImage);
        stackInfo.add(tableInfo);
*/
        btLevel[index] = new Table();
        btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
        btLevel[index].setTouchable(Touchable.enabled);
        /*btLevel[index].add(tableTop).height(70).fill().expandX();
        btLevel[index].row();
        btLevel[index].add(stackInfo).align(Align.center).width(300).height(380).padTop(10);
*/
        btLevel[index].addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_DOWN)));
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if(!(y > 0 && y < btLevel[index].getHeight()) || !(x > 0 && x < btLevel[index].getWidth())) {
                    btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < btLevel[index].getHeight() && x > 0 && x < btLevel[index].getWidth()) {
                    //gameBAS.gameMode = levelInfo;
                    game.gameScreen.updateScreenData();
                    game.setScreen(game.gameScreen);
                }
                btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
            }
        });
        return btLevel[index];
    }
}