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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class MainMenuScreen extends AbstractScreen {

    private Label labelScreenTitle;

    class TableLevelStructure {
        Label title;
        TextButton buttonNewGame;
        TextButton buttonContinue;
    }
    private TableLevelStructure[] tableLevelStructure = new TableLevelStructure[Constants.NUMBER_OF_LEVELS];

    public MainMenuScreen(TheGame game) {
        super(game);
        for (int i = 0; i < Constants.NUMBER_OF_LEVELS; i++) {
            tableLevelStructure[i] = new TableLevelStructure();
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
    public void updateScreen() {
        super.updateScreen();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleMainMenu"));
        for (int i = 0; i < tableLevelStructure.length; i++) {
            tableLevelStructure[i].title.setText(game.levelManager.getArrayLevelData()[i].name);
            tableLevelStructure[i].buttonNewGame.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonNewGame"));
            tableLevelStructure[i].buttonContinue.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonContinue"));
            tableLevelStructure[i].buttonContinue.setDisabled(true);
        }
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
            tableLevels.add(initLevelTable(tableLevelStructure[i], i)).align(Align.center).width(Constants.LEVEL_BUTTON_WIDTH).height(Constants.LEVEL_BUTTON_HEIGHT).padRight(i < Constants.NUMBER_OF_LEVELS - 1 ? Constants.GAP : 0).padBottom(Constants.GAP);
        }

        ScrollPane scrollPaneLevels = new ScrollPane(tableLevels, game.styles.scrollPaneStyle);
        scrollPaneLevels.setScrollingDisabled(false, true);
        scrollPaneLevels.setForceScroll(true, false);
        scrollPaneLevels.setupOverscroll(Constants.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneLevels.setFadeScrollBars(false);
        scrollPaneLevels.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneLevels.updateVisualScroll();
        scrollPaneLevels.layout();

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHugeWithBorder);
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
                    game.settingsScreen.updateScreen();
                    game.setScreen(game.settingsScreen);
                }
            }
        });
    }

    private Table initLevelTable(final TableLevelStructure tableLevelStructure, final int index) {
        Label labelTitle = new Label("", game.styles.labelStyleWhiteBig);
        final TextButton buttonNewGame = new TextButton("", game.styles.textButtonStyleOrange);
        final TextButton buttonContinue = new TextButton("", game.styles.textButtonStyleOrange);
        Table tableButtons = new Table();
        tableButtons.add(buttonNewGame).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom).padRight(Constants.GAP);
        tableButtons.add(buttonContinue).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottom);
        Table tableLevel = new Table();
        tableLevel.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableLevel.add(labelTitle).align(Align.left).expandX().pad(Constants.GAP);
        tableLevel.row();
        tableLevel.add(tableButtons).align(Align.bottom).expandY().pad(Constants.GAP);
        buttonNewGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < buttonNewGame.getHeight() && x > 0 && x < buttonNewGame.getWidth()) {
                    game.levelManager.currentLevel = index + 1;
                    game.gameScreen.updateScreen();
                    game.setScreen(game.gameScreen);
                }
            }
        });
        buttonContinue.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (y > 0 && y < buttonContinue.getHeight() && x > 0 && x < buttonContinue.getWidth()) {
                    if (buttonContinue.isDisabled()) {
                        game.alertManager.showPopup("NO previously saved game found.");
                    } else {
                        game.levelManager.currentLevel = index + 1;
                        game.gameScreen.updateScreen();
                        game.setScreen(game.gameScreen);
                    }
                }
            }
        });
        tableLevelStructure.title = labelTitle;
        tableLevelStructure.buttonNewGame = buttonNewGame;
        tableLevelStructure.buttonContinue = buttonContinue;
        return tableLevel;
    }
}