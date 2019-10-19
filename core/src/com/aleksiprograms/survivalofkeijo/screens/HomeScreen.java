package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.resources.UIDimensions;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class HomeScreen extends AbstractScreen {

    private Label labelScreenTitle;
    private LevelInfo[] levelInfos;
    private Table[] btLevel = new Table[Constants.NUMBER_OF_LEVELS];
    private boolean quitBoxShown;

    class LevelData {
        Label labelScore;
        Label labelDistance;
        Image imageStar1;
        Image imageStar2;
        Image imageStar3;
    }
    private LevelData[] levelData = new LevelData[Constants.NUMBER_OF_LEVELS];

    public HomeScreen(TheGame game) {
        super(game);
        levelInfos = game.levelManager.getLevelInfos();
        for (int i = 0; i < 3; i++) {
            levelData[i] = new LevelData();
        }
        initScreen();
    }

    @Override
    public void show() {
        quitBoxShown = false;
        updateGameModeData();
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            showQuitBox();
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleHome"));
    }

    private void initScreen() {
        Image imageGameController = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_GAMES_CONRTROLLER_SIGNED_IN)));
        final ImageButton buttonAchievements = new ImageButton(game.styles.imageButtonStyleAchievements);
        final ImageButton buttonLeaderboards = new ImageButton(game.styles.imageButtonStyleLeaderboards);

        Table tableGPGS = new Table();
        tableGPGS.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableGPGS.add(imageGameController).pad(UIDimensions.GAP).width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).height(UIDimensions.IMAGE_BUTTON_SIZE_SMALL);
        tableGPGS.row();
        tableGPGS.add(buttonAchievements).padLeft(UIDimensions.GAP).padRight(UIDimensions.GAP).width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).height(UIDimensions.IMAGE_BUTTON_SIZE_SMALL);
        tableGPGS.row();
        tableGPGS.add(buttonLeaderboards).pad(UIDimensions.GAP).width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).height(UIDimensions.IMAGE_BUTTON_SIZE_SMALL);

        final ImageButton buttonSettings = new ImageButton(game.styles.imageButtonStyleSettings);

        Table tableLevels = new Table();
        for (int i = 0; i < Constants.NUMBER_OF_LEVELS; i++) {
            tableLevels.add(initLevelTable(levelInfos[i], i)).align(Align.center).growY().width((int)((Gdx.graphics.getWidth() - 2 * UIDimensions.GAP - UIDimensions.TEXT_BUTTON_WIDTH - 3 * UIDimensions.GAP) / 3)).padRight(i < Constants.NUMBER_OF_LEVELS - 1 ? UIDimensions.GAP : 0).padBottom(UIDimensions.GAP);
        }

        ScrollPane scrollPaneLevels = new ScrollPane(tableLevels, game.styles.scrollPaneStyle);
        scrollPaneLevels.setScrollingDisabled(false, true);
        scrollPaneLevels.setForceScroll(true, false);
        scrollPaneLevels.setupOverscroll(UIDimensions.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneLevels.setFadeScrollBars(false);
        scrollPaneLevels.setFlickScrollTapSquareSize(UIDimensions.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneLevels.updateVisualScroll();
        scrollPaneLevels.layout();

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHuge);
        Table tableTop = new Table();
        tableTop.add().width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).height(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).align(Align.left);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add(buttonSettings).width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).height(UIDimensions.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(tableTop).height(UIDimensions.TABLE_TOP_HEIGHT).growX().padBottom(UIDimensions.GAP).align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(scrollPaneLevels).fill().expand().align(Align.center).padRight(UIDimensions.GAP);
        tableContent.add(tableGPGS).align(Align.right).expandY().width(UIDimensions.IMAGE_BUTTON_SIZE_SMALL + 2 * UIDimensions.GAP).height(3 * UIDimensions.IMAGE_BUTTON_SIZE_SMALL + 4 * UIDimensions.GAP);

        Table table = new Table();
        table.setFillParent(true);
        //table.background(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(UIDimensions.GAP);

        stage.addActor(table);

        buttonSettings.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonSettings.getWidth() && y > 0 && y < buttonSettings.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.saveData.getSoundVolume());
                    game.settingsScreen.updateScreen();
                    game.setScreen(game.settingsScreen);
                }
            }
        });
    }

    private Table initLevelTable(final LevelInfo levelInfo, final int index) {
        /*Table tableTop = new Table();
        tableTop.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
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
        btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
        btLevel[index].setTouchable(Touchable.enabled);
        /*btLevel[index].add(tableTop).height(70).fill().expandX();
        btLevel[index].row();
        btLevel[index].add(stackInfo).align(Align.center).width(300).height(380).padTop(10);
*/
        btLevel[index].addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D)));
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if(!(y > 0 && y < btLevel[index].getHeight()) || !(x > 0 && x < btLevel[index].getWidth())) {
                    btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < btLevel[index].getHeight() && x > 0 && x < btLevel[index].getWidth()) {
                    //gameBAS.gameMode = levelInfo;
                    game.gameScreen.updateScreen();
                    game.setScreen(game.gameScreen);
                }
                btLevel[index].background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
            }
        });
        return btLevel[index];
    }

    private void updateGameModeData() {
        /*for (int i = 0; i < levelInfos.length; i++) {
            btLevel[i].background(new TextureRegionDrawable(gameBAS.getTextureRegionByID(Constants.TEX_SRC_RECTANGLE_GREY)));
            levelData[i].labelScore.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getLevelScore(levelInfos[i].ID)));
            levelData[i].labelDistance.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getLevelDistance(levelInfos[i].ID)) + " m");
            if (gameBAS.saveManager.saveData.getLevelStars(levelInfos[i].ID) >= 1)
                levelData[i].imageStar1.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))));
            else
                levelData[i].imageStar1.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED))));
            if (gameBAS.saveManager.saveData.getLevelStars(levelInfos[i].ID) >= 2)
                levelData[i].imageStar2.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))));
            else
                levelData[i].imageStar2.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED))));
            if (gameBAS.saveManager.saveData.getLevelStars(levelInfos[i].ID) >= 3)
                levelData[i].imageStar3.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_STAR_UNLOCKED))));
            else
                levelData[i].imageStar3.setDrawable(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_STAR_LOCKED))));
        }*/
    }

    private void showQuitBox() {
        /*if (!quitBoxShown) {
            quitBoxShown = true;
            final Dialog dialog = new Dialog("", gameBAS.styles.dialogBoxStyle) {
                @Override
                protected void result(Object object) {
                }
            };
            dialog.padTop(20);
            Label labelText = new Label("Do you want to quit the game?", gameBAS.styles.skinLabelDialogText);
            labelText.setWrap(true);
            labelText.setAlignment(Align.center);
            dialog.getContentTable().add(labelText).align(Align.center).width(550).height(250).padLeft(20).padRight(20);
            final TextButton btNo = new TextButton("No", gameBAS.styles.skinButtonDialog);
            final TextButton btYes = new TextButton("Yes", gameBAS.styles.skinButtonDialog);
            dialog.getButtonTable().pad(20);
            dialog.getButtonTable().add(btNo).padRight(20).width(Constants.BUTTON_WIDTH_DIALOG).height(Constants.BUTTON_HEIGHT_DIALOG);
            dialog.getButtonTable().add(btYes).width(Constants.BUTTON_WIDTH_DIALOG).height(Constants.BUTTON_HEIGHT_DIALOG);

            btNo.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, final int button) {
                    if (y > 0 && y < + btNo.getHeight()) {
                        if (x > 0 && x < btNo.getWidth()) {
                            quitBoxShown = false;
                            gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.saveData.getSoundVolume());
                            dialog.hide();
                        }
                    }
                }
            });

            btYes.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, final int button) {
                    if (y > 0 && y < + btYes.getHeight()) {
                        if (x > 0 && x < btYes.getWidth()) {
                            gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(gameBAS.saveManager.saveData.getSoundVolume());
                            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    Gdx.app.exit();
                                }
                            })));
                        }
                    }
                }
            });

            dialog.setModal(true);
            dialog.setMovable(false);
            dialog.setResizable(false);
            dialog.invalidateHierarchy();
            dialog.invalidate();
            dialog.layout();
            dialog.show(stage);
        }*/
    }
}