package com.aleksiprograms.survivalofkeijo.resources;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Styles {

    private TheGame game;

    private DecimalFormat decimalFormatFloat;
    private DecimalFormat decimalFormatInt;
    private DecimalFormat decimalFormatFloatWithSign;
    private DecimalFormat decimalFormatIntWithSign;
    private DecimalFormat decimalFormatFloatMoney;
    private DecimalFormat decimalFormatIntMoney;

    private final Color colorWhite = new Color(0xFFFFFFFF);
    private final Color colorOrange = new Color(0xFF7D19FF);
    private final Color colorGreen = new Color(0x38FF38FF);
    private final Color colorRed = new Color(0xFF3838FF);
    private final Color colorLBlue = new Color(0x42A5F5FF);
    private final Color colorDisabled = new Color(0x6d6d6dff);
    private final Color colorBlack = new Color(0x000000ff);
    private final Color colorGold = new Color(0xffd700ff);
    private final Color colorSilver = new Color(0xc0c0c0ff);
    private final Color colorBronze = new Color(0xcd7f32ff);

    private final String FONT_PATH = "fonts/RobotoCondensed-Regular.ttf";
    private final String FONT_BOLD_PATH = "fonts/RobotoCondensed-Bold.ttf";

    public Skin skinLabelWhiteHuge;
    public Skin skinLabelWhiteBig;
    public Skin skinLabelRedBig;
    public Skin skinLabelWhiteMedium;
    public Skin skinLabelWhiteSmall;
    public Skin skinLabelRedSmall;
    public Skin skinLabelWhiteTiny;
    public Skin skinLabelBlueSmall;
    public Skin skinLabelGreenSmall;

    public Skin skinTextButtonOrange;
    public Skin skinTextButtonGreen;
    public Skin skinTextButtonRed;

    public Skin skinImageButtonClose;
    public Skin skinImageButtonPlus;
    public Skin skinImageButtonMinus;
    public Skin skinImageButtonMagazines;
    public Skin skinImageButtonSettings;
    public Skin skinImageButtonPause;
    public Skin skinImageButtonRight;
    public Skin skinImageButtonLeft;
    public Skin skinImageButtonUp;
    public Skin skinImageButtonDown;
    public Skin skinImageButtonBackpack;
    public Skin skinImageButtonReload;
    public Skin skinImageButtonEnter;
    public Skin skinImageButtonAchievements;
    public Skin skinImageButtonLeaderboards;

    public Label.LabelStyle labelStyleLoggedInGPGS;
    public Label.LabelStyle labelStyleLoggedOutGPGS;

    public Skin skinScrollPane;
    public Skin skinSlider;
    public Skin skinCheckbox;
    public Skin skinDialogBox;
    public Skin skinSelectBox;
    public Skin skinProgressBar;
    public Skin skinProgressBarHealth;
    public Skin skinProgressBarValueNotBought;
    public Skin skinProgressBarValueBought;
    public Skin skinProgressBarUpgradeBought;
    public Skin skinProgressBarValueBoughtMinus;
    public Skin skinProgressBarUpgradeBoughtMinus;
    public Skin skinProgressBarUpgradeDone;

    public Styles(TheGame game) {
        this.game = game;
        setLocale();

        skinLabelWhiteHuge = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_HUGE, colorWhite);
        skinLabelWhiteBig = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_BIG, colorWhite);
        skinLabelRedBig = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_BIG, colorRed);
        skinLabelWhiteMedium = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_MEDIUM, colorWhite);
        skinLabelWhiteSmall = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_SMALL, colorWhite);
        skinLabelRedSmall = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_SMALL, colorRed);
        skinLabelWhiteTiny = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_TINY, colorWhite);
        skinLabelBlueSmall = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_SMALL, colorLBlue);
        skinLabelGreenSmall = getSkinLabel(FONT_PATH, Constants.TEXT_SIZE_SMALL, colorGreen);

        skinTextButtonOrange = getSkinTextButton(
                FONT_BOLD_PATH, Constants.TEXT_BUTTON_TEXT_SIZE, colorOrange, colorOrange, colorDisabled,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D)));
        skinTextButtonGreen = getSkinTextButton(
                FONT_BOLD_PATH, Constants.TEXT_BUTTON_TEXT_SIZE, colorGreen, colorGreen, colorDisabled,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D_GREEN)));
        skinTextButtonRed = getSkinTextButton(
                FONT_BOLD_PATH, Constants.TEXT_BUTTON_TEXT_SIZE, colorRed, colorRed, colorDisabled,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D_RED)));

        skinImageButtonClose = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_CLOSE_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_CLOSE_DOWN)));
        skinImageButtonPlus = getSkinImageButtonWithDisabled(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_PLUS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_PLUS_DOWN)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_PLUS_DISABLED)));
        skinImageButtonMinus = getSkinImageButtonWithDisabled(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_MINUS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_MINUS_DOWN)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_MINUS_DISABLED)));
        skinImageButtonMagazines = getSkinImageButtonWithChecked(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECKBOX_ON)));
        skinImageButtonSettings = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_SETTINGS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_SETTINGS_DOWN)));
        skinImageButtonPause = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_PAUSE_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_PAUSE_DOWN)));
        skinImageButtonRight = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_DOWN)));
        skinImageButtonLeft = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_LEFT_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_LEFT_DOWN)));
        skinImageButtonUp = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_UP_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_UP_DOWN)));
        skinImageButtonDown = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_DOWN_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_DOWN_DOWN)));
        skinImageButtonBackpack = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_BACKPACK_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_BACKPACK_DOWN)));
        skinImageButtonReload = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RELOAD_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RELOAD_DOWN)));
        skinImageButtonEnter = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_ENTER_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_ENTER_DOWN)));
        skinImageButtonAchievements = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_ACHIEVEMENTS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_ACHIEVEMENTS_DOWN)));
        skinImageButtonLeaderboards = getSkinImageButton(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_LEADERBOARDS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_LEADERBOARDS_DOWN)));

        labelStyleLoggedInGPGS = getLabelStyle(FONT_PATH, 35, colorGreen);
        labelStyleLoggedOutGPGS = getLabelStyle(FONT_PATH, 35, colorRed);

        skinScrollPane = getSkinScrollPane(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCROLL_PANE_KNOB)));
        skinSlider = getSkinSlider(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECKBOX_ON)));
        skinCheckbox = getSkinCheckbox(FONT_PATH, Constants.TEXT_SIZE_SMALL, colorWhite,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECKBOX_ON)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
        skinDialogBox = getSkinDialogBox(FONT_PATH, Constants.TEXT_SIZE_MEDIUM, colorWhite,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        skinSelectBox = getSkinSelectBox(FONT_PATH, Constants.TEXT_SIZE_SMALL, colorOrange, colorWhite,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_SELECT_BOX_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_SELECT_BOX_DOWN)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCROLL_PANE_KNOB)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D)));
        skinProgressBar = getSkinProgressBar(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL)));
        skinProgressBarHealth = getSkinProgressBar(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_HEALTH)));

        skinProgressBarValueNotBought = getSkinProgressBarData(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_BACKGROUND)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_VALUE)));
        skinProgressBarValueBought = getSkinProgressBarData(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_VALUE)));
        skinProgressBarUpgradeBought = getSkinProgressBarData(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_BACKGROUND)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_UPGRADE)));
        skinProgressBarValueBoughtMinus = getSkinProgressBarData(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_BACKGROUND)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_UPGRADE)));
        skinProgressBarUpgradeBoughtMinus = getSkinProgressBarData(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_VALUE_ATL)));
        skinProgressBarUpgradeDone = getSkinProgressBarData(
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_BACKGROUND)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_VALUE)));
    }

    public static void dispose() {}

    private BitmapFont getFont(
            String fontFilePath,
            int fontSize) {
        BitmapFont font;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFilePath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter;
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private Skin getSkinLabel(
            String fontFilePath,
            int fontSize,
            Color fontColor) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        labelStyle.fontColor = fontColor;
        skin.add("default", labelStyle);
        return skin;
    }

    private Label.LabelStyle getLabelStyle(
            String fontFilePath,
            int fontSize,
            Color fontColor) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(fontFilePath, fontSize);
        labelStyle.fontColor = fontColor;
        return labelStyle;
    }

    private Skin getSkinTextButton(
            String fontFilePath,
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorDisabled,
            NinePatchDrawable drawableUp,
            NinePatchDrawable drawableDown) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        textButtonStyle.up = drawableUp;
        textButtonStyle.down = drawableDown;
        textButtonStyle.disabled = drawableUp;
        skin.add("default", textButtonStyle);
        return skin;
    }

    private Skin getSkinImageButton(
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown) {
        Skin skin = new Skin();
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        skin.add("default", imageButtonStyle);
        return skin;
    }

    private Skin getSkinImageButtonWithChecked(
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown) {
        Skin skin = new Skin();
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureUp;
        imageButtonStyle.checked = textureDown;
        skin.add("default", imageButtonStyle);
        return skin;
    }

    private Skin getSkinImageButtonWithDisabled(
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown,
            NinePatchDrawable textureDisabled) {
        Skin skin = new Skin();
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        imageButtonStyle.disabled = textureDisabled;
        skin.add("default", imageButtonStyle);
        return skin;
    }

    private Skin getSkinDialogBox(
            String fontFilePath,
            int fontSize,
            Color fontColorLabel,
            NinePatchDrawable background) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        Dialog.WindowStyle windowStyle = new Dialog.WindowStyle();
        windowStyle.background = background;
        windowStyle.titleFont = skin.getFont("default");
        windowStyle.titleFontColor = fontColorLabel;
        skin.add("default", windowStyle);
        return skin;
    }

    private Skin getSkinScrollPane(
            NinePatchDrawable scroll,
            NinePatchDrawable scrollKnob) {
        Skin skin = new Skin();
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle(null, scroll, scrollKnob, scroll, scrollKnob);
        scrollPaneStyle.hScroll.setMinHeight(Constants.SCROLL_PANE_SCROLL_SIZE);
        scrollPaneStyle.hScrollKnob.setMinHeight(Constants.SCROLL_PANE_SCROLL_SIZE);
        scrollPaneStyle.vScroll.setMinWidth(Constants.SCROLL_PANE_SCROLL_SIZE);
        scrollPaneStyle.vScrollKnob.setMinWidth(Constants.SCROLL_PANE_SCROLL_SIZE);
        skin.add("default", scrollPaneStyle);
        return skin;
    }

    private Skin getSkinSlider(
            NinePatchDrawable background,
            NinePatchDrawable knobBefore,
            NinePatchDrawable knob) {
        Skin skin = new Skin();
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle(background, knob);
        sliderStyle.knobBefore = knobBefore;
        sliderStyle.background.setMinWidth(Constants.PROGRESS_BAR_WIDTH_HEALTH);
        sliderStyle.background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_WIDTH_HEALTH);
        sliderStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderStyle.knob.setMinWidth(Constants.IMAGE_BUTTON_SIZE_SMALL);
        sliderStyle.knob.setMinHeight(Constants.IMAGE_BUTTON_SIZE_SMALL);
        skin.add("default-horizontal", sliderStyle);
        return skin;
    }

    private Skin getSkinCheckbox(
            String fontFilePath,
            int fontSize,
            Color fontColor,
            NinePatchDrawable checked,
            NinePatchDrawable unchecked) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle(
                unchecked,
                checked,
                skin.getFont("default"),
                fontColor);
        checkBoxStyle.checkboxOn.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOn.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOff.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOff.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        skin.add("default", checkBoxStyle);
        return skin;
    }

    private Skin getSkinSelectBox(
            String fontFilePath,
            int fontSize,
            Color fontColorSelected,
            Color fontColorUnselected,
            NinePatchDrawable selectBoxUp,
            NinePatchDrawable selectBoxDown,
            NinePatchDrawable background,
            NinePatchDrawable scroll,
            NinePatchDrawable scrollKnob,
            NinePatchDrawable selection) {
        Skin skin = new Skin();
        skin.add("default", getFont(fontFilePath, fontSize));
        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();
        selectBoxStyle.background = selectBoxUp;
        selectBoxStyle.backgroundOpen = selectBoxDown;
        selectBoxStyle.font = skin.getFont("default");
        selectBoxStyle.fontColor = fontColorUnselected;
        selectBoxStyle.scrollStyle = new ScrollPane.ScrollPaneStyle(background, scroll, scrollKnob, scroll, scrollKnob);
        selectBoxStyle.scrollStyle.hScroll.setMinHeight(Constants.SCROLL_PANE_SCROLL_SIZE);
        selectBoxStyle.scrollStyle.hScrollKnob.setMinHeight(Constants.SCROLL_PANE_SCROLL_SIZE);
        selectBoxStyle.scrollStyle.vScroll.setMinWidth(Constants.SCROLL_PANE_SCROLL_SIZE);
        selectBoxStyle.scrollStyle.vScrollKnob.setMinWidth(Constants.SCROLL_PANE_SCROLL_SIZE);
        selectBoxStyle.listStyle = new List.ListStyle(
                getFont(fontFilePath, fontSize),
                fontColorSelected,
                fontColorUnselected,
                selection);
        skin.add("default", selectBoxStyle);
        return skin;
    }

    private Skin getSkinProgressBar(
            NinePatchDrawable background,
            NinePatchDrawable knobBefore) {
        Skin skin = new Skin();
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = background;
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
        skin.add("default-horizontal", progressBarStyle);
        return skin;
    }

    private Skin getSkinProgressBarData(
            NinePatchDrawable background,
            NinePatchDrawable knobBefore) {
        Skin skin = new Skin();
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = background;
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.background.setMinHeight(Constants.PROGRESS_BAR_DATA_HEIGHT);
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_DATA_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
        skin.add("default-horizontal", progressBarStyle);
        return skin;
    }

    private Skin getSkinProgressBarData(
            NinePatchDrawable knobBefore) {
        Skin skin = new Skin();
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_DATA_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
        skin.add("default-horizontal", progressBarStyle);
        return skin;
    }

    public String getFormattedFloat(float number) {
        return decimalFormatFloat.format(number);
    }

    public String getFormattedInt(float number) {
        return decimalFormatInt.format(number);
    }

    public String getFormattedFloatWithSign(float number) {
        return decimalFormatFloatWithSign.format(number);
    }

    public String getFormattedIntWithSign(float number) {
        return decimalFormatIntWithSign.format(number);
    }

    public String getFormattedFloatMoney(float number) {
        return decimalFormatFloatMoney.format(number);
    }

    public String getFormattedIntMoney(float number) {
        return decimalFormatIntMoney.format(number);
    }

    public void setLocale() {
        decimalFormatFloat = (DecimalFormat) NumberFormat.getNumberInstance(game.locale);
        decimalFormatFloat.applyPattern("#,##0.0");
        decimalFormatInt = (DecimalFormat) NumberFormat.getNumberInstance(game.locale);
        decimalFormatInt.applyPattern("#,##0");
        decimalFormatFloatWithSign = (DecimalFormat) NumberFormat.getNumberInstance(game.locale);
        decimalFormatFloatWithSign.applyPattern("+#,##0.0;-#,##0.0");
        decimalFormatFloatWithSign.setNegativePrefix("-");
        decimalFormatIntWithSign = (DecimalFormat) NumberFormat.getNumberInstance(game.locale);
        decimalFormatIntWithSign.applyPattern("+#,##0;-#,##0");
        decimalFormatIntWithSign.setNegativePrefix("-");
        decimalFormatFloatMoney = (DecimalFormat) NumberFormat.getNumberInstance(game.locale);
        decimalFormatFloatMoney.applyPattern("#,##0.00");
        decimalFormatIntMoney = (DecimalFormat) NumberFormat.getNumberInstance(game.locale);
        decimalFormatIntMoney.applyPattern("#,##0");
    }
}