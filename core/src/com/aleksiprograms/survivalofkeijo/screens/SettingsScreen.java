package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class SettingsScreen extends AbstractScreen {

    private final CheckBox checkBoxSounds;
    private Label labelScreenTitle;
    private Label labelSoundVolumeTitle;
    private Slider sliderSoundVolume;
    private Label labelLanguageTitle;
    private Label labelLanguage;
    private TextButton buttonChangeLanguage;
    private Table tableDialogBoxLanguage;
    private Label labelDialogBoxLanguageTitle;
    private TextButton buttonConfirm;
    private TextButton buttonCancel;
    private TextButton buttonEnglish;
    private TextButton buttonSuomi;
    private String previousLanguage;
    private String selectedLanguage;
    private TextButton buttonCredits;

    public SettingsScreen(TheGame game) {
        super(game);
        checkBoxSounds = new CheckBox("   Sounds", game.getStyles().getCheckBoxStyle());
        initialize();
        initializeDialogBoxLanguage();
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
    public void pause() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void updateData() {
        super.updateData();
        labelScreenTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("titleSettings"));
        labelSoundVolumeTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("settingsSoundVolumeTitle"));
        checkBoxSounds.setChecked(game.getSavedDataManager().getSavedData().isSounds());
        labelLanguageTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("settingsLanguageTitle"));
        buttonChangeLanguage.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonChange"));
        buttonConfirm.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonConfirm"));
        buttonCancel.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonCancel"));
        labelDialogBoxLanguageTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelDialogBoxLanguageTitle"));
        if (game.getLocale().equals(Constants.LOCALE_ENGLISH)) {
            buttonEnglish.setChecked(true);
            labelLanguage.setText(Constants.STRING_ENGLISH);
        } else if (game.getLocale().equals(Constants.LOCALE_FINNISH)) {
            buttonSuomi.setChecked(true);
            labelLanguage.setText(Constants.STRING_FINNISH);
        } else {
            buttonEnglish.setChecked(true);
            labelLanguage.setText(Constants.STRING_ENGLISH);
        }
        buttonCredits.setText(game.getAssetManager()
                .get(Constants.BUNDLE, I18NBundle.class).get("buttonCredits"));
    }

    private void initialize() {
        Table tableSoundVolume = new Table();
        labelSoundVolumeTitle = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        sliderSoundVolume = new Slider(
                0f, 1f, 0.1f, false, game.getStyles().getSliderStyle());
        sliderSoundVolume.setValue(0.5f);
        sliderSoundVolume.getStyle().background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderSoundVolume.getStyle().knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderSoundVolume.getStyle().knob.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        sliderSoundVolume.getStyle().knob.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        tableSoundVolume.add(labelSoundVolumeTitle).padRight(Constants.GAP);
        tableSoundVolume.add(sliderSoundVolume).width(800);

        Table tableLanguage = new Table();
        labelLanguageTitle = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelLanguage = new Label("", game.getStyles().getLabelStyleWhiteBig());
        buttonChangeLanguage = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        tableLanguage.add(labelLanguageTitle).padRight(Constants.GAP).growX().align(Align.left);
        tableLanguage.add(labelLanguage).padRight(Constants.GAP).align(Align.right);
        tableLanguage.add(buttonChangeLanguage).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);

        Table tableSettings = new Table();
        tableSettings.add(tableSoundVolume).padBottom(Constants.GAP).align(Align.left);
        tableSettings.row();
        tableSettings.add(tableLanguage).align(Align.left).growX();

        buttonCredits = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        Table tableButtons = new Table();
        tableButtons.add(buttonCredits).align(Align.right)
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
        tableContent.add(tableTop).colspan(2).growX().padBottom(Constants.GAP).align(Align.top)
                .height(Constants.TABLE_TOP_HEIGHT);
        tableContent.row();
        tableContent.add(tableSettings).expand();
        tableContent.add(tableButtons).expandY().align(Align.right);

        Table table = new Table();
        table.setFillParent(true);
        table.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS_LOADING,
                TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND)));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);

        checkBoxSounds.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.getSavedDataManager().getSavedData().setSounds(checkBoxSounds.isChecked());
            }
        });

        sliderSoundVolume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            }
        });

        buttonChangeLanguage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonChangeLanguage.getWidth()
                        && y > 0 && y < buttonChangeLanguage.getHeight()) {
                    game.getSettingsScreen().stageExtra.clear();
                    game.getSettingsScreen().stageExtra.addActor(tableDialogBoxLanguage);
                    game.getSettingsScreen().setShowStageExtra(true);
                }
            }
        });

        buttonClose.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonClose.getWidth()
                        && y > 0 && y < buttonClose.getHeight()) {
                    game.getSavedDataManager().save();
                    game.getMainMenuScreen().updateData();
                    game.setScreen(game.getMainMenuScreen());
                }
            }
        });

        buttonCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonCredits.getWidth()
                        && y > 0 && y < buttonCredits.getHeight()) {
                    game.getSavedDataManager().save();
                    game.getCreditsScreen().updateData();
                    game.setScreen(game.getCreditsScreen());
                }
            }
        });
    }

    private void initializeDialogBoxLanguage() {
        Table table = new Table();
        table.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        buttonConfirm = new TextButton("",
                game.getStyles().getTextButtonStyleGreen());
        buttonConfirm.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    previousLanguage = selectedLanguage;
                    game.getSettingsScreen().setShowStageExtra(false);
                }
            }
        });
        buttonCancel = new TextButton("",
                game.getStyles().getTextButtonStyleRed());
        buttonCancel.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.getSavedDataManager().getSavedData().setLanguage(previousLanguage);
                    game.changeLocale();
                    game.getStyles().setLocale();
                    updateData();
                    game.getSettingsScreen().setShowStageExtra(false);
                }
            }
        });
        buttonEnglish = new TextButton(Constants.STRING_ENGLISH,
                game.getStyles().getTextButtonStyleNoTexture());
        buttonEnglish.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonEnglish.getWidth()
                        && y > 0 && y < buttonEnglish.getHeight()) {
                    game.getSavedDataManager().getSavedData()
                            .setLanguage(Constants.STRING_ENGLISH);
                    game.changeLocale();
                    game.getStyles().setLocale();
                    updateData();
                    selectedLanguage = Constants.STRING_ENGLISH;
                }
            }
        });
        buttonSuomi = new TextButton(Constants.STRING_FINNISH,
                game.getStyles().getTextButtonStyleNoTexture());
        buttonSuomi.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonSuomi.getWidth()
                        && y > 0 && y < buttonSuomi.getHeight()) {
                    game.getSavedDataManager().getSavedData()
                            .setLanguage(Constants.STRING_FINNISH);
                    game.changeLocale();
                    game.getStyles().setLocale();
                    updateData();
                    selectedLanguage = Constants.STRING_FINNISH;
                }
            }
        });
        ButtonGroup<TextButton> buttonGroupLanguages
                = new ButtonGroup<TextButton>(buttonEnglish, buttonSuomi);
        buttonGroupLanguages.setMaxCheckCount(1);
        buttonGroupLanguages.setMinCheckCount(1);
        buttonGroupLanguages.setUncheckLast(true);
        if (game.getLocale().equals(Constants.LOCALE_ENGLISH)) {
            buttonEnglish.setChecked(true);
            previousLanguage = Constants.STRING_ENGLISH;
        } else if (game.getLocale().equals(Constants.LOCALE_FINNISH)) {
            buttonSuomi.setChecked(true);
            previousLanguage = Constants.STRING_FINNISH;
        } else {
            buttonEnglish.setChecked(true);
            previousLanguage = Constants.STRING_ENGLISH;
        }

        Table tableLanguages = new Table();
        tableLanguages.add(buttonEnglish).growX().height(Constants.TEXT_BUTTON_LANGUAGE_HEIGHT);
        tableLanguages.row();
        tableLanguages.add(buttonSuomi).growX().height(Constants.TEXT_BUTTON_LANGUAGE_HEIGHT);

        ScrollPane scrollPaneWeapons = new ScrollPane(tableLanguages,
                game.getStyles().getScrollPaneStyle());
        scrollPaneWeapons.setScrollingDisabled(true, false);
        scrollPaneWeapons.setForceScroll(false, true);
        scrollPaneWeapons.setupOverscroll(
                Constants.SCROLL_PANE_OVER_SCROLL,
                Constants.SCROLL_PANE_MIN_SPEED,
                Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeapons.setFadeScrollBars(false);
        scrollPaneWeapons.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeapons.updateVisualScroll();
        scrollPaneWeapons.layout();

        labelDialogBoxLanguageTitle = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());
        table.add(labelDialogBoxLanguageTitle)
                .growX().align(Align.topLeft).pad(Constants.GAP);
        table.row();
        table.add(scrollPaneWeapons)
                .grow().align(Align.center).padLeft(Constants.GAP).padRight(Constants.GAP);
        table.row();
        Table tableButtons = new Table();
        tableButtons.add(buttonCancel).align(Align.bottom).padRight(Constants.GAP)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);
        tableButtons.add(buttonConfirm).align(Align.bottom)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);
        table.add(tableButtons).growX().align(Align.bottom).pad(Constants.GAP);
        tableDialogBoxLanguage = new Table();
        tableDialogBoxLanguage.setFillParent(true);
        tableDialogBoxLanguage.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(
                Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableDialogBoxLanguage.add(table)
                .width(Constants.DIALOG_BOX_WIDTH)
                .height(Constants.DIALOG_BOX_HEIGHT);
    }
}