package com.aleksiprograms.survivalofkeijo.resources;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
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
    private FreeTypeFontGenerator fontGeneratorRegular;
    private FreeTypeFontGenerator fontGeneratorBold;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public Label.LabelStyle labelStyleWhiteHuge;
    public Label.LabelStyle labelStyleWhiteBig;
    public Label.LabelStyle labelStyleRedBig;
    public Label.LabelStyle labelStyleWhiteMedium;
    public Label.LabelStyle labelStyleWhiteSmall;
    public Label.LabelStyle labelStyleRedSmall;
    public Label.LabelStyle labelStyleWhiteTiny;
    public Label.LabelStyle labelStyleBlueSmall;
    public Label.LabelStyle labelStyleGreenSmall;

    public TextButton.TextButtonStyle textButtonStyleOrange;
    public TextButton.TextButtonStyle textButtonStyleGreen;
    public TextButton.TextButtonStyle textButtonStyleRed;
    public TextButton.TextButtonStyle textButtonStyleNoTexture;

    public ImageButton.ImageButtonStyle imageButtonStyleClose;
    public ImageButton.ImageButtonStyle imageButtonStylePlus;
    public ImageButton.ImageButtonStyle imageButtonStyleMagazines;
    public ImageButton.ImageButtonStyle imageButtonStyleSettings;
    public ImageButton.ImageButtonStyle imageButtonStylePause;
    public ImageButton.ImageButtonStyle imageButtonStyleRight;
    public ImageButton.ImageButtonStyle imageButtonStyleLeft;
    public ImageButton.ImageButtonStyle imageButtonStyleUp;
    public ImageButton.ImageButtonStyle imageButtonStyleDown;
    public ImageButton.ImageButtonStyle imageButtonStyleBackpack;
    public ImageButton.ImageButtonStyle imageButtonStyleReload;
    public ImageButton.ImageButtonStyle imageButtonStyleEnter;
    public ImageButton.ImageButtonStyle imageButtonStyleAchievements;
    public ImageButton.ImageButtonStyle imageButtonStyleLeaderboards;

    public Label.LabelStyle labelStyleLoggedInGPGS;
    public Label.LabelStyle labelStyleLoggedOutGPGS;

    public ScrollPane.ScrollPaneStyle scrollPaneStyle;
    public Slider.SliderStyle sliderStyle;
    public CheckBox.CheckBoxStyle checkBoxStyle;
    public ProgressBar.ProgressBarStyle progressBarStyle;
    public ProgressBar.ProgressBarStyle progressBarStyleHealth;
    public ProgressBar.ProgressBarStyle progressBarStyleValueNotBought;
    public ProgressBar.ProgressBarStyle progressBarStyleValueBought;
    public ProgressBar.ProgressBarStyle progressBarStyleUpgrade;
    public ProgressBar.ProgressBarStyle progressBarStyleUpgradeDone;

    public Styles(TheGame game) {
        this.game = game;
        fontGeneratorRegular = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        fontGeneratorBold = new FreeTypeFontGenerator(Gdx.files.internal(FONT_BOLD_PATH));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        setLocale();

        labelStyleWhiteHuge = new Label.LabelStyle();
        labelStyleWhiteBig = new Label.LabelStyle();
        labelStyleRedBig = new Label.LabelStyle();
        labelStyleWhiteMedium = new Label.LabelStyle();
        labelStyleWhiteSmall = new Label.LabelStyle();
        labelStyleRedSmall = new Label.LabelStyle();
        labelStyleWhiteTiny = new Label.LabelStyle();
        labelStyleBlueSmall = new Label.LabelStyle();
        labelStyleGreenSmall = new Label.LabelStyle();

        textButtonStyleOrange = new TextButton.TextButtonStyle();
        textButtonStyleGreen = new TextButton.TextButtonStyle();
        textButtonStyleRed = new TextButton.TextButtonStyle();
        textButtonStyleNoTexture = new TextButton.TextButtonStyle();

        imageButtonStyleClose = new ImageButton.ImageButtonStyle();
        imageButtonStylePlus = new ImageButton.ImageButtonStyle();
        imageButtonStyleMagazines = new ImageButton.ImageButtonStyle();
        imageButtonStyleSettings = new ImageButton.ImageButtonStyle();
        imageButtonStylePause = new ImageButton.ImageButtonStyle();
        imageButtonStyleRight = new ImageButton.ImageButtonStyle();
        imageButtonStyleLeft = new ImageButton.ImageButtonStyle();
        imageButtonStyleUp = new ImageButton.ImageButtonStyle();
        imageButtonStyleDown = new ImageButton.ImageButtonStyle();
        imageButtonStyleBackpack = new ImageButton.ImageButtonStyle();
        imageButtonStyleReload = new ImageButton.ImageButtonStyle();
        imageButtonStyleEnter = new ImageButton.ImageButtonStyle();
        imageButtonStyleAchievements = new ImageButton.ImageButtonStyle();
        imageButtonStyleLeaderboards = new ImageButton.ImageButtonStyle();

        scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        sliderStyle = new Slider.SliderStyle();
        checkBoxStyle = new CheckBox.CheckBoxStyle();
        progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyleHealth = new ProgressBar.ProgressBarStyle();

        progressBarStyleValueNotBought = new ProgressBar.ProgressBarStyle();
        progressBarStyleValueBought = new ProgressBar.ProgressBarStyle();
        progressBarStyleUpgrade = new ProgressBar.ProgressBarStyle();
        progressBarStyleUpgradeDone = new ProgressBar.ProgressBarStyle();

        setStyles();
    }

    public static void dispose() {}

    private void setStyles() {
        setLabelStyle(labelStyleWhiteHuge, false, Constants.TEXT_SIZE_HUGE, colorWhite);
        setLabelStyle(labelStyleWhiteBig, false, Constants.TEXT_SIZE_BIG, colorWhite);
        setLabelStyle(labelStyleRedBig, false, Constants.TEXT_SIZE_BIG, colorRed);
        setLabelStyle(labelStyleWhiteMedium, false, Constants.TEXT_SIZE_MEDIUM, colorWhite);
        setLabelStyle(labelStyleWhiteSmall, false, Constants.TEXT_SIZE_SMALL, colorWhite);
        setLabelStyle(labelStyleRedSmall, false, Constants.TEXT_SIZE_SMALL, colorRed);
        setLabelStyle(labelStyleWhiteTiny, false, Constants.TEXT_SIZE_TINY, colorWhite);
        setLabelStyle(labelStyleBlueSmall, false, Constants.TEXT_SIZE_SMALL, colorLBlue);
        setLabelStyle(labelStyleGreenSmall, false, Constants.TEXT_SIZE_SMALL, colorGreen);

        setTextButtonStyle(textButtonStyleOrange,
                true, Constants.TEXT_BUTTON_TEXT_SIZE, colorOrange, colorOrange, colorDisabled,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D)));
        setTextButtonStyle(textButtonStyleGreen,
                true, Constants.TEXT_BUTTON_TEXT_SIZE, colorGreen, colorGreen, colorDisabled,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D_GREEN)));
        setTextButtonStyle(textButtonStyleRed,
                true, Constants.TEXT_BUTTON_TEXT_SIZE, colorRed, colorRed, colorDisabled,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D_RED)));
        setTextButtonStyle(textButtonStyleNoTexture,
                true, Constants.TEXT_BUTTON_TEXT_SIZE, colorWhite, colorOrange, colorOrange, colorDisabled);

        setImageButtonStyle(imageButtonStyleClose,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_CLOSE_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_CLOSE_DOWN)));
        setImageButtonStyleWithDisabled(imageButtonStylePlus,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_PLUS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_PLUS_DOWN)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_PLUS_DISABLED)));
        setImageButtonStyleWithChecked(imageButtonStyleMagazines,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECKBOX_ON)));
        setImageButtonStyle(imageButtonStyleSettings,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_SETTINGS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_SETTINGS_DOWN)));
        setImageButtonStyle(imageButtonStylePause,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_PAUSE_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_PAUSE_DOWN)));
        setImageButtonStyle(imageButtonStyleRight,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_DOWN)));
        setImageButtonStyle(imageButtonStyleLeft,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_LEFT_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_LEFT_DOWN)));
        setImageButtonStyle(imageButtonStyleUp,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_UP_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_UP_DOWN)));
        setImageButtonStyle(imageButtonStyleDown,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_DOWN_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_DOWN_DOWN)));
        setImageButtonStyle(imageButtonStyleBackpack,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_BACKPACK_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_BACKPACK_DOWN)));
        setImageButtonStyle(imageButtonStyleReload,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RELOAD_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RELOAD_DOWN)));
        setImageButtonStyle(imageButtonStyleEnter,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_ENTER_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_ENTER_DOWN)));
        setImageButtonStyleWithDisabled(imageButtonStyleAchievements,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_ACHIEVEMENTS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_ACHIEVEMENTS_DOWN)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_ACHIEVEMENTS_DISABLED)));
        setImageButtonStyleWithDisabled(imageButtonStyleLeaderboards,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_LEADERBOARDS_UP)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_LEADERBOARDS_DOWN)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_LEADERBOARDS_DISABLED)));

        setScrollPaneStyle(scrollPaneStyle,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCROLL_PANE_KNOB)));
        setSliderStyle(sliderStyle,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECKBOX_ON)));
        setCheckBoxStyle(checkBoxStyle,
                false, Constants.TEXT_SIZE_SMALL, colorWhite,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECKBOX_ON)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
        setProgressBarStyle(progressBarStyle,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL)));
        setProgressBarStyle(progressBarStyleHealth,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_HEALTH)));

        setProgressBarStyleData(progressBarStyleValueNotBought,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_BACKGROUND)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_VALUE)));
        setProgressBarStyleData(progressBarStyleValueBought,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_VALUE)));
        setProgressBarStyleData(progressBarStyleUpgrade,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_BACKGROUND)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_UPGRADE)));
        setProgressBarStyleData(progressBarStyleUpgradeDone,
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_BACKGROUND)),
                new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BAR_FILL_VALUE)));
    }

    private BitmapFont getFont(
            boolean bold,
            int fontSize) {
        fontParameter.size = fontSize;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        //fontGeneratorRegular.dispose();
        if (bold) {
            return fontGeneratorBold.generateFont(fontParameter);
        } else {
            return fontGeneratorRegular.generateFont(fontParameter);
        }
    }

    private void setLabelStyle(
            Label.LabelStyle labelStyle,
            boolean bold,
            int fontSize,
            Color fontColor) {
        labelStyle.font = getFont(bold, fontSize);
        labelStyle.fontColor = fontColor;
    }

    private void setTextButtonStyle(
            TextButton.TextButtonStyle textButtonStyle,
            boolean bold,
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorDisabled,
            NinePatchDrawable drawableUp,
            NinePatchDrawable drawableDown) {
        textButtonStyle.font = getFont(bold, fontSize);
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        textButtonStyle.up = drawableUp;
        textButtonStyle.down = drawableDown;
        textButtonStyle.disabled = drawableUp;
    }

    private void setTextButtonStyle(
            TextButton.TextButtonStyle textButtonStyle,
            boolean bold,
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorChecked,
            Color fontColorDisabled) {
        textButtonStyle.font = getFont(bold, fontSize);
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.checkedFontColor = fontColorChecked;
        textButtonStyle.disabledFontColor = fontColorDisabled;
    }

    private void setImageButtonStyle(
            ImageButton.ImageButtonStyle imageButtonStyle,
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown) {
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
    }

    private void setImageButtonStyleWithChecked(
            ImageButton.ImageButtonStyle imageButtonStyle,
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown) {
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureUp;
        imageButtonStyle.checked = textureDown;
    }

    private void setImageButtonStyleWithDisabled(
            ImageButton.ImageButtonStyle imageButtonStyle,
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown,
            NinePatchDrawable textureDisabled) {
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        imageButtonStyle.disabled = textureDisabled;
    }

    private void setScrollPaneStyle(
            ScrollPane.ScrollPaneStyle scrollPaneStyle,
            NinePatchDrawable scroll,
            NinePatchDrawable scrollKnob) {
        scrollPaneStyle.hScroll = scroll;
        scrollPaneStyle.hScrollKnob = scrollKnob;
        scrollPaneStyle.vScroll = scroll;
        scrollPaneStyle.vScrollKnob = scrollKnob;
        scrollPaneStyle.hScroll.setMinHeight(Constants.SCROLL_PANE_SCROLL_SIZE);
        scrollPaneStyle.hScrollKnob.setMinHeight(Constants.SCROLL_PANE_SCROLL_SIZE);
        scrollPaneStyle.vScroll.setMinWidth(Constants.SCROLL_PANE_SCROLL_SIZE);
        scrollPaneStyle.vScrollKnob.setMinWidth(Constants.SCROLL_PANE_SCROLL_SIZE);
    }

    private void setSliderStyle(
            Slider.SliderStyle sliderStyle,
            NinePatchDrawable background,
            NinePatchDrawable knobBefore,
            NinePatchDrawable knob) {
        sliderStyle.background = background;
        sliderStyle.knob = knob;
        sliderStyle.knobBefore = knobBefore;
        sliderStyle.background.setMinWidth(Constants.PROGRESS_BAR_WIDTH_HEALTH);
        sliderStyle.background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_WIDTH_HEALTH);
        sliderStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderStyle.knob.setMinWidth(Constants.IMAGE_BUTTON_SIZE_SMALL);
        sliderStyle.knob.setMinHeight(Constants.IMAGE_BUTTON_SIZE_SMALL);
    }

    private void setCheckBoxStyle(
            CheckBox.CheckBoxStyle checkBoxStyle,
            boolean bold,
            int fontSize,
            Color fontColor,
            NinePatchDrawable checked,
            NinePatchDrawable unchecked) {
        checkBoxStyle.checkboxOff = unchecked;
        checkBoxStyle.checkboxOn = checked;
        checkBoxStyle.font = getFont(bold, fontSize);
        checkBoxStyle.fontColor = fontColor;
        checkBoxStyle.checkboxOn.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOn.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOff.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOff.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
    }

    private void setProgressBarStyle(
            ProgressBar.ProgressBarStyle progressBarStyle,
            NinePatchDrawable background,
            NinePatchDrawable knobBefore) {
        progressBarStyle.background = background;
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
    }

    private void setProgressBarStyleData(
            ProgressBar.ProgressBarStyle progressBarStyle,
            NinePatchDrawable background,
            NinePatchDrawable knobBefore) {
        progressBarStyle.background = background;
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.background.setMinHeight(Constants.PROGRESS_BAR_DATA_HEIGHT);
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_DATA_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
    }

    private void setProgressBarStyleData(
            ProgressBar.ProgressBarStyle progressBarStyle,
            NinePatchDrawable knobBefore) {
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_DATA_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
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