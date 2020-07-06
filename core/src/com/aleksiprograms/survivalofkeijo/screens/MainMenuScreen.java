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
    private TableLevelStructure[] tableLevelStructure;

    public MainMenuScreen(TheGame game) {
        super(game);
        tableLevelStructure = new TableLevelStructure[Constants.NUMBER_OF_LEVELS];
        for (int i = 0; i < Constants.NUMBER_OF_LEVELS; i++) {
            tableLevelStructure[i] = new TableLevelStructure();
        }
        initialize();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)
                || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.getAlertManager().confirmExitGame();
        }
    }

    @Override
    public void updateData() {
        super.updateData();
        labelScreenTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("titleMainMenu"));
        for (int i = 0; i < tableLevelStructure.length; i++) {
            tableLevelStructure[i].title.setText(
                    game.getLevelManager().getArrayLevelData()[i].getName());
            tableLevelStructure[i].buttonNewGame.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("buttonNewGame"));
            tableLevelStructure[i].buttonContinue.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("buttonContinue"));
            tableLevelStructure[i].buttonContinue.setDisabled(true);
        }
    }

    private void initialize() {
        final ImageButton buttonGameControllerSignedIn = new ImageButton(
                game.getStyles().getImageButtonStyleGameControllerSignedIn());
        final ImageButton buttonGameControllerSignedOut = new ImageButton(
                game.getStyles().getImageButtonStyleGameControllerSignedOut());
        buttonGameControllerSignedOut.setVisible(false);
        Stack stackGameController = new Stack();
        stackGameController.add(buttonGameControllerSignedIn);
        stackGameController.add(buttonGameControllerSignedOut);
        final ImageButton buttonAchievements = new ImageButton(
                game.getStyles().getImageButtonStyleAchievements());
        final ImageButton buttonLeaderboards = new ImageButton(
                game.getStyles().getImageButtonStyleLeaderboards());
        final ImageButton buttonSettings = new ImageButton(
                game.getStyles().getImageButtonStyleSettings());

        Table tableLevels = new Table();
        for (int i = 0; i < Constants.NUMBER_OF_LEVELS; i++) {
            tableLevels.add(initLevelTable(tableLevelStructure[i], i))
                    .align(Align.center)
                    .width(Constants.LEVEL_BUTTON_WIDTH)
                    .height(Constants.LEVEL_BUTTON_HEIGHT)
                    .padRight(i < Constants.NUMBER_OF_LEVELS - 1 ? Constants.GAP : 0)
                    .padBottom(Constants.GAP);
        }

        ScrollPane scrollPaneLevels = new ScrollPane(
                tableLevels, game.getStyles().getScrollPaneStyle());
        scrollPaneLevels.setScrollingDisabled(false, true);
        scrollPaneLevels.setForceScroll(true, false);
        scrollPaneLevels.setupOverscroll(
                Constants.SCROLL_PANE_OVER_SCROLL,
                Constants.SCROLL_PANE_MIN_SPEED,
                Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneLevels.setFadeScrollBars(false);
        scrollPaneLevels.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneLevels.updateVisualScroll();
        scrollPaneLevels.layout();

        labelScreenTitle = new Label("", game.getStyles().getLabelStyleWhiteHugeWithBorder());
        Table tableTop = new Table();
        tableTop.add(stackGameController).align(Align.left)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        tableTop.add(buttonAchievements).padLeft(Constants.GAP).padRight(Constants.GAP)
                .align(Align.left)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        tableTop.add(buttonLeaderboards).align(Align.left)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add().align(Align.right)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        tableTop.add().padLeft(Constants.GAP).padRight(Constants.GAP).align(Align.right)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        tableTop.add(buttonSettings).align(Align.right)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);

        Table tableContent = new Table();
        tableContent.add(tableTop)
                .height(Constants.TABLE_TOP_HEIGHT)
                .growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        tableContent.row();
        tableContent.add(scrollPaneLevels).fill().expand().align(Align.center);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS_LOADING,
                TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND)));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);

        buttonGameControllerSignedIn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonGameControllerSignedIn.getWidth()
                        && y > 0 && y < buttonGameControllerSignedIn.getHeight()) {
                    game.getAlertManager().showPopup(
                            "SIGNED IN to google play games.\nSign out from settings.");
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
                if (x > 0 && x < buttonGameControllerSignedOut.getWidth()
                        && y > 0 && y < buttonGameControllerSignedOut.getHeight()) {
                    game.getAlertManager().showPopup(
                            "SIGNED OUT from google play games.\nSign in from settings.");
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

    private Table initLevelTable(final TableLevelStructure tableLevelStructure, final int index) {
        Label labelTitle = new Label("", game.getStyles().getLabelStyleWhiteBig());
        final TextButton buttonNewGame = new TextButton("",
                game.getStyles().getTextButtonStyleOrange());
        final TextButton buttonContinue = new TextButton("",
                game.getStyles().getTextButtonStyleOrange());
        Table tableButtons = new Table();
        tableButtons.add(buttonNewGame).align(Align.bottom).padRight(Constants.GAP)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);
        tableButtons.add(buttonContinue).align(Align.bottom)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);
        Table tableLevel = new Table();
        tableLevel.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
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
                if (y > 0 && y < buttonNewGame.getHeight()
                        && x > 0 && x < buttonNewGame.getWidth()) {
                    game.getLevelManager().setCurrentLevel(index + 1);
                    game.getGameScreen().updateData();
                    game.setScreen(game.getGameScreen());
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
                if (y > 0 && y < buttonContinue.getHeight()
                        && x > 0 && x < buttonContinue.getWidth()) {
                    if (buttonContinue.isDisabled()) {
                        game.getAlertManager().showPopup("NO previously saved game found.");
                    } else {
                        game.getLevelManager().setCurrentLevel(index + 1);
                        game.getGameScreen().updateData();
                        game.setScreen(game.getGameScreen());
                    }
                }
            }
        });
        tableLevelStructure.title = labelTitle;
        tableLevelStructure.buttonNewGame = buttonNewGame;
        tableLevelStructure.buttonContinue = buttonContinue;
        return tableLevel;
    }

    class TableLevelStructure {
        Label title;
        TextButton buttonNewGame;
        TextButton buttonContinue;
    }
}