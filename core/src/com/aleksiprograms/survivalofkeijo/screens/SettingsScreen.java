package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

public class SettingsScreen extends AbstractScreen {

    private Label labelScreenTitle;
    private Slider sliderSoundVolume;
    private SelectBox<String> selectBoxLanguage;
    private final CheckBox checkBoxSounds;
    private final CheckBox checkBoxDebugDrawWorld;
    private final CheckBox checkBoxDebugDrawMenus;
    private final CheckBox checkBoxShowPauseButton;
    private final CheckBox checkBoxAspectRatio169;

    public SettingsScreen(TheGame game) {
        super(game);
        checkBoxSounds = new CheckBox("   Sounds", game.styles.checkBoxStyle);
        checkBoxDebugDrawWorld = new CheckBox("   Debug Draw World", game.styles.checkBoxStyle);
        checkBoxDebugDrawMenus = new CheckBox("   Debug Draw Menus", game.styles.checkBoxStyle);
        checkBoxShowPauseButton = new CheckBox("   Show Pause Button", game.styles.checkBoxStyle);
        checkBoxAspectRatio169 = new CheckBox("   Aspect Ration 16:9", game.styles.checkBoxStyle);
        selectBoxLanguage = new SelectBox<String>(game.styles.selectBoxStyle);
        Array<String> languages = new Array<String>();
        languages.add(Constants.STRING_ENGLISH);
        languages.add(Constants.STRING_FINNISH);
        selectBoxLanguage.setItems(languages);
        if (game.locale.equals(Constants.LOCALE_ENGLISH)) {
            selectBoxLanguage.setSelected(Constants.STRING_ENGLISH);
        } else if (game.locale.equals(Constants.LOCALE_FINNISH)) {
            selectBoxLanguage.setSelected(Constants.STRING_FINNISH);
        } else {
            selectBoxLanguage.setSelected(Constants.STRING_ENGLISH);
        }
        initializeScreen();
    }

    @Override
    public void show() {
        //sliderSoundVolume.setValue(gameBAS.saveManager.saveData.getSoundVolume());
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.homeScreen.updateScreenData();
            game.setScreen(game.homeScreen);
        }
    }

    @Override
    public void pause() {
        //gameBAS.saveManager.saveData.setSoundVolume(sliderSoundVolume.getValue());
    }

    @Override
    public void hide() {
        //gameBAS.saveManager.saveData.setSoundVolume(sliderSoundVolume.getValue());
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void updateScreenData() {
        super.updateScreenData();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleSettings"));
        checkBoxSounds.setChecked(game.saveDataManager.saveData.isSounds());
        checkBoxDebugDrawWorld.setChecked(game.saveDataManager.saveData.isDebugDrawWorld());
        checkBoxDebugDrawMenus.setChecked(game.saveDataManager.saveData.isDebugDrawUI());
        checkBoxShowPauseButton.setChecked(game.saveDataManager.saveData.isShowPauseButton());
        checkBoxAspectRatio169.setChecked(game.saveDataManager.saveData.isAspectRatio169());
    }

    private void initializeScreen() {
        /*Table tableSoundVolume = new Table();
        Label lSoundVolumeTitle = new Label("Volume", gameBAS.styles.skinLabelSettingsTitle);
        sliderSoundVolume = new Slider(0.0f, 1.0f, 0.1f, false, gameBAS.styles.sliderStyle);
        sliderSoundVolume.getStyle().background.setMinWidth(500);
        sliderSoundVolume.getStyle().background.setMinHeight(15);
        sliderSoundVolume.getStyle().knobBefore.setMinWidth(500);
        sliderSoundVolume.getStyle().knobBefore.setMinHeight(15);
        sliderSoundVolume.getStyle().knob.setMinWidth(50);
        sliderSoundVolume.getStyle().knob.setMinHeight(50);
        tableSoundVolume.add(lSoundVolumeTitle).padRight(30);
        tableSoundVolume.add(sliderSoundVolume).width(500).height(50);*/

        Table tableSettings = new Table();
        tableSettings.add(checkBoxSounds).padBottom(Constants.SETTINGS_CELL_BOTTOM_GAP).align(Align.left);
        tableSettings.row();
        tableSettings.add(checkBoxDebugDrawWorld).padBottom(Constants.SETTINGS_CELL_BOTTOM_GAP).align(Align.left);
        tableSettings.row();
        tableSettings.add(checkBoxDebugDrawMenus).padBottom(Constants.SETTINGS_CELL_BOTTOM_GAP).align(Align.left);
        tableSettings.row();
        tableSettings.add(checkBoxShowPauseButton).padBottom(Constants.SETTINGS_CELL_BOTTOM_GAP).align(Align.left);
        tableSettings.row();
        tableSettings.add(checkBoxAspectRatio169).padBottom(Constants.SETTINGS_CELL_BOTTOM_GAP).align(Align.left);
        tableSettings.row();
        tableSettings.add(selectBoxLanguage).width(Constants.TEXT_BUTTON_WIDTH*2).height(Constants.IMAGE_BUTTON_SIZE_SMALL).padBottom(Constants.SETTINGS_CELL_BOTTOM_GAP).align(Align.left);
        //tableSettings.add(tableSoundVolume).padBottom(20);

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHuge);
        final ImageButton buttonClose = new ImageButton(game.styles.imageButtonStyleClose);
        Table tableTop = new Table();
        tableTop.add().width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.left);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add(buttonClose).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(tableTop).height(Constants.TABLE_TOP_HEIGHT).growX().padBottom(Constants.GAP).align(Align.top);
        tableContent.row();
        tableContent.add(tableSettings).expand();

        Table table = new Table();
        table.setFillParent(true);
        //table.background(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);

        checkBoxSounds.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.saveDataManager.saveData.setSounds(checkBoxSounds.isChecked());
            }
        });

        checkBoxDebugDrawWorld.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.saveDataManager.saveData.setDebugDrawWorld(checkBoxDebugDrawWorld.isChecked());
            }
        });

        checkBoxDebugDrawMenus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.saveDataManager.saveData.setDebugDrawUI(checkBoxDebugDrawMenus.isChecked());
                stage.setDebugAll(checkBoxDebugDrawMenus.isChecked());
            }
        });

        checkBoxShowPauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.saveDataManager.saveData.setShowPauseButton(checkBoxShowPauseButton.isChecked());
            }
        });

        checkBoxAspectRatio169.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.saveDataManager.saveData.setAspectRatio169(checkBoxAspectRatio169.isChecked());
            }
        });

        selectBoxLanguage.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.saveDataManager.saveData.setLanguage(selectBoxLanguage.getSelected());
                game.changeLocale();
                game.styles.setLocale();
                updateScreenData();
            }
        });

        /*sliderSoundVolume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (sliderSoundVolume.getValue() > gameBAS.saveManager.saveData.getSoundVolume() + 0.001f ||
                        sliderSoundVolume.getValue() < gameBAS.saveManager.saveData.getSoundVolume() - 0.001f) {
                    gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_POS).play(sliderSoundVolume.getValue());
                }
            }
        });*/

        buttonClose.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonClose.getWidth() && y > 0 && y < buttonClose.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.saveData.getSoundVolume());
                    game.saveDataManager.save();
                    game.homeScreen.updateScreenData();
                    game.setScreen(game.homeScreen);
                }
            }
        });
    }
}