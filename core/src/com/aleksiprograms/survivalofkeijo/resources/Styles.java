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

    private final Color colorWhite = new Color(0xFFFFFFFF);
    private final Color colorOrange = new Color(0xFF7D19FF);
    private final Color colorGreen = new Color(0x38FF38FF);
    private final Color colorRed = new Color(0xFF3838FF);
    private final Color colorLBlue = new Color(0x2196F3FF);
    private final Color colorDisabled = new Color(0x6d6d6dff);
    private TheGame game;
    private DecimalFormat decimalFormatFloat;
    private DecimalFormat decimalFormatInt;
    private DecimalFormat decimalFormatFloatWithSign;
    private DecimalFormat decimalFormatIntWithSign;
    private DecimalFormat decimalFormatFloatMoney;
    private DecimalFormat decimalFormatIntMoney;
    private FreeTypeFontGenerator fontGeneratorRegular;
    private FreeTypeFontGenerator fontGeneratorBold;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private Label.LabelStyle labelStyleWhiteHugeWithBorder;
    private Label.LabelStyle labelStyleWhiteBig;
    private Label.LabelStyle labelStyleWhiteBigWithBorder;
    private Label.LabelStyle labelStyleRedBig;
    private Label.LabelStyle labelStyleRedBigWithBorder;
    private Label.LabelStyle labelStyleWhiteMedium;
    private Label.LabelStyle labelStyleWhiteSmall;
    private Label.LabelStyle labelStyleWhiteSmallWithBorder;
    private Label.LabelStyle labelStyleRedSmall;
    private Label.LabelStyle labelStyleRedSmallWithBorder;
    private Label.LabelStyle labelStyleWhiteTiny;
    private Label.LabelStyle labelStyleBlueSmall;
    private Label.LabelStyle labelStyleGreenSmall;

    private TextButton.TextButtonStyle textButtonStyleOrange;
    private TextButton.TextButtonStyle textButtonStyleGreen;
    private TextButton.TextButtonStyle textButtonStyleRed;
    private TextButton.TextButtonStyle textButtonStyleNoTexture;
    private TextButton.TextButtonStyle textButtonStyleWeapon;
    private TextButton.TextButtonStyle textButtonStyleEnterBuilding;

    private ImageButton.ImageButtonStyle imageButtonStyleClose;
    private ImageButton.ImageButtonStyle imageButtonStylePlus;
    private ImageButton.ImageButtonStyle imageButtonStyleMagazines;
    private ImageButton.ImageButtonStyle imageButtonStyleSettings;
    private ImageButton.ImageButtonStyle imageButtonStylePause;
    private ImageButton.ImageButtonStyle imageButtonStyleRight;
    private ImageButton.ImageButtonStyle imageButtonStyleLeft;
    private ImageButton.ImageButtonStyle imageButtonStyleUp;
    private ImageButton.ImageButtonStyle imageButtonStyleDown;
    private ImageButton.ImageButtonStyle imageButtonStyleBackpack;
    private ImageButton.ImageButtonStyle imageButtonStyleReload;
    private ImageButton.ImageButtonStyle imageButtonStyleGameControllerSignedIn;
    private ImageButton.ImageButtonStyle imageButtonStyleGameControllerSignedOut;
    private ImageButton.ImageButtonStyle imageButtonStyleAchievements;
    private ImageButton.ImageButtonStyle imageButtonStyleLeaderboards;

    private ScrollPane.ScrollPaneStyle scrollPaneStyle;
    private Slider.SliderStyle sliderStyle;
    private CheckBox.CheckBoxStyle checkBoxStyle;
    private ProgressBar.ProgressBarStyle progressBarStyle;
    private ProgressBar.ProgressBarStyle progressBarStyleHealth;
    private ProgressBar.ProgressBarStyle progressBarStyleValueNotBought;
    private ProgressBar.ProgressBarStyle progressBarStyleValueBought;
    private ProgressBar.ProgressBarStyle progressBarStyleUpgrade;
    private ProgressBar.ProgressBarStyle progressBarStyleUpgradeDone;

    public Styles(TheGame game) {
        this.game = game;
        fontGeneratorRegular = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT));
        fontGeneratorBold = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_BOLD));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        setLocale();
        setStyles();
        fontGeneratorRegular.dispose();
        fontGeneratorBold.dispose();
    }

    private void setStyles() {
        labelStyleWhiteHugeWithBorder = getLabelStyle(
                true, Constants.TEXT_SIZE_HUGE, colorWhite);
        labelStyleWhiteBig = getLabelStyle(
                false, Constants.TEXT_SIZE_BIG, colorWhite);
        labelStyleWhiteBigWithBorder = getLabelStyle(
                true, Constants.TEXT_SIZE_BIG, colorWhite);
        labelStyleRedBig = getLabelStyle(
                false, Constants.TEXT_SIZE_BIG, colorRed);
        labelStyleRedBigWithBorder = getLabelStyle(
                true, Constants.TEXT_SIZE_BIG, colorRed);
        labelStyleWhiteMedium = getLabelStyle(
                false, Constants.TEXT_SIZE_MEDIUM, colorWhite);
        labelStyleWhiteSmall = getLabelStyle(
                false, Constants.TEXT_SIZE_SMALL, colorWhite);
        labelStyleWhiteSmallWithBorder = getLabelStyle(
                true, Constants.TEXT_SIZE_SMALL, colorWhite);
        labelStyleRedSmall = getLabelStyle(
                false, Constants.TEXT_SIZE_SMALL, colorRed);
        labelStyleRedSmallWithBorder = getLabelStyle(
                true, Constants.TEXT_SIZE_SMALL, colorRed);
        labelStyleWhiteTiny = getLabelStyle(
                false, Constants.TEXT_SIZE_TINY, colorWhite);
        labelStyleBlueSmall = getLabelStyle(
                false, Constants.TEXT_SIZE_SMALL, colorLBlue);
        labelStyleGreenSmall = getLabelStyle(
                false, Constants.TEXT_SIZE_SMALL, colorGreen);

        textButtonStyleOrange = getTextButtonStyle(
                Constants.TEXT_BUTTON_TEXT_SIZE, colorOrange, colorOrange, colorDisabled,
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_DOWN)));
        textButtonStyleGreen = getTextButtonStyle(
                Constants.TEXT_BUTTON_TEXT_SIZE, colorGreen, colorGreen, colorDisabled,
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_DOWN_GREEN)));
        textButtonStyleRed = getTextButtonStyle(
                Constants.TEXT_BUTTON_TEXT_SIZE, colorRed, colorRed, colorDisabled,
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_DOWN_RED)));
        textButtonStyleNoTexture = getTextButtonStyle(
                Constants.TEXT_BUTTON_TEXT_SIZE,
                colorWhite, colorOrange, colorOrange, colorDisabled);
        textButtonStyleWeapon = getTextButtonStyle(
                Constants.TEXT_BUTTON_TEXT_SIZE,
                colorWhite, colorWhite, colorDisabled,
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_DOWN)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_DOWN)));
        textButtonStyleEnterBuilding = getTextButtonStyle(
                Constants.TEXT_BUTTON_TEXT_SIZE,
                colorWhite, colorWhite, colorDisabled,
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_EMPTY_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_EMPTY_DOWN)));

        imageButtonStyleClose = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_CLOSE_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_CLOSE_DOWN)));
        imageButtonStylePlus = getImageButtonStyleWithDisabled(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_PLUS_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_PLUS_DOWN)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_PLUS_DISABLED)));
        imageButtonStyleMagazines = getImageButtonStyleWithChecked(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_CHECKBOX_ON)));
        imageButtonStyleSettings = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_SETTINGS_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_SETTINGS_DOWN)));
        imageButtonStylePause = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_PAUSE_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_PAUSE_DOWN)));
        imageButtonStyleRight = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_RIGHT_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_RIGHT_DOWN)));
        imageButtonStyleLeft = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_LEFT_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_LEFT_DOWN)));
        imageButtonStyleUp = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_UP_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_UP_DOWN)));
        imageButtonStyleDown = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_DOWN_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_DOWN_DOWN)));
        imageButtonStyleBackpack = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_BACKPACK_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_BACKPACK_DOWN)));
        imageButtonStyleReload = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_RELOAD_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_GAME_RELOAD_DOWN)));
        imageButtonStyleGameControllerSignedIn = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_GAMES_CONRTROLLER_SIGNED_IN)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_GAMES_CONRTROLLER_SIGNED_IN)));
        imageButtonStyleGameControllerSignedOut = getImageButtonStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_GAMES_CONRTROLLER_SIGNED_OUT)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_GAMES_CONRTROLLER_SIGNED_OUT)));
        imageButtonStyleAchievements = getImageButtonStyleWithDisabled(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_ACHIEVEMENTS_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_ACHIEVEMENTS_DOWN)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_ACHIEVEMENTS_DISABLED)));
        imageButtonStyleLeaderboards = getImageButtonStyleWithDisabled(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_LEADERBOARDS_UP)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_LEADERBOARDS_DOWN)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_LEADERBOARDS_DISABLED)));

        scrollPaneStyle = getScrollPaneStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_SCROLL_PANE_KNOB)));
        sliderStyle = getSliderStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_FILL)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_SLIDER_KNOB)));
        checkBoxStyle = getCheckBoxStyle(
                Constants.TEXT_SIZE_SMALL, colorWhite,
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_CHECKBOX_ON)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
        progressBarStyle = getProgressBarStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_FILL)));
        progressBarStyleHealth = getProgressBarStyle(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_FILL_HEALTH)));

        progressBarStyleValueNotBought = getProgressBarStyleData(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_DATA_BACKGROUND)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_DATA_VALUE)));
        progressBarStyleValueBought = getProgressBarStyleData(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_DATA_VALUE)));
        progressBarStyleUpgrade = getProgressBarStyleData(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_DATA_BACKGROUND)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_DATA_UPGRADE)));
        progressBarStyleUpgradeDone = getProgressBarStyleData(
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_DATA_BACKGROUND)),
                new NinePatchDrawable(game.getAssetManager().get(
                        Constants.TEXTURE_ATLAS,
                        TextureAtlas.class).createPatch(
                        Constants.TEXTURE_BAR_DATA_VALUE)));
    }

    private BitmapFont getFont(
            boolean bold,
            boolean border,
            int fontSize) {
        fontParameter.size = fontSize;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        if (border) {
            fontParameter.borderWidth = 5f;
        } else {
            fontParameter.borderWidth = 0f;
        }
        if (bold) {
            return fontGeneratorBold.generateFont(fontParameter);
        } else {
            return fontGeneratorRegular.generateFont(fontParameter);
        }
    }

    private Label.LabelStyle getLabelStyle(
            boolean stroke,
            int fontSize,
            Color fontColor) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(false, stroke, fontSize);
        labelStyle.fontColor = fontColor;
        return labelStyle;
    }

    private TextButton.TextButtonStyle getTextButtonStyle(
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorDisabled,
            NinePatchDrawable drawableUp,
            NinePatchDrawable drawableDown) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = getFont(true, false, fontSize);
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        textButtonStyle.up = drawableUp;
        textButtonStyle.down = drawableDown;
        textButtonStyle.disabled = drawableUp;
        return textButtonStyle;
    }

    private TextButton.TextButtonStyle getTextButtonStyle(
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorDisabled,
            NinePatchDrawable drawableUp,
            NinePatchDrawable drawableDown,
            NinePatchDrawable drawableChecked) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = getFont(true, false, fontSize);
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        textButtonStyle.up = drawableUp;
        textButtonStyle.down = drawableDown;
        textButtonStyle.checked = drawableChecked;
        textButtonStyle.disabled = drawableUp;
        return textButtonStyle;
    }

    private TextButton.TextButtonStyle getTextButtonStyle(
            int fontSize,
            Color fontColorUp,
            Color fontColorDown,
            Color fontColorChecked,
            Color fontColorDisabled) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = getFont(true, false, fontSize);
        textButtonStyle.fontColor = fontColorUp;
        textButtonStyle.downFontColor = fontColorDown;
        textButtonStyle.checkedFontColor = fontColorChecked;
        textButtonStyle.disabledFontColor = fontColorDisabled;
        return textButtonStyle;
    }

    private ImageButton.ImageButtonStyle getImageButtonStyle(
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        return imageButtonStyle;
    }

    private ImageButton.ImageButtonStyle getImageButtonStyleWithChecked(
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureUp;
        imageButtonStyle.checked = textureDown;
        return imageButtonStyle;
    }

    private ImageButton.ImageButtonStyle getImageButtonStyleWithDisabled(
            NinePatchDrawable textureUp,
            NinePatchDrawable textureDown,
            NinePatchDrawable textureDisabled) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = textureUp;
        imageButtonStyle.down = textureDown;
        imageButtonStyle.disabled = textureDisabled;
        return imageButtonStyle;
    }

    private ScrollPane.ScrollPaneStyle getScrollPaneStyle(
            NinePatchDrawable scroll,
            NinePatchDrawable scrollKnob) {
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.hScroll = scroll;
        scrollPaneStyle.hScrollKnob = scrollKnob;
        scrollPaneStyle.vScroll = scroll;
        scrollPaneStyle.vScrollKnob = scrollKnob;
        scrollPaneStyle.hScroll.setMinHeight(Constants.SCROLL_PANE_THICKNESS);
        scrollPaneStyle.hScrollKnob.setMinHeight(Constants.SCROLL_PANE_THICKNESS);
        scrollPaneStyle.vScroll.setMinWidth(Constants.SCROLL_PANE_THICKNESS);
        scrollPaneStyle.vScrollKnob.setMinWidth(Constants.SCROLL_PANE_THICKNESS);
        return scrollPaneStyle;
    }

    private Slider.SliderStyle getSliderStyle(
            NinePatchDrawable background,
            NinePatchDrawable knobBefore,
            NinePatchDrawable knob) {
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = background;
        sliderStyle.knob = knob;
        sliderStyle.knobBefore = knobBefore;
        sliderStyle.background.setMinWidth(Constants.PROGRESS_BAR_WIDTH_HEALTH);
        sliderStyle.background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_WIDTH_HEALTH);
        sliderStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderStyle.knob.setMinWidth(Constants.IMAGE_BUTTON_SIZE_SMALL);
        sliderStyle.knob.setMinHeight(Constants.IMAGE_BUTTON_SIZE_SMALL);
        return sliderStyle;
    }

    private CheckBox.CheckBoxStyle getCheckBoxStyle(
            int fontSize,
            Color fontColor,
            NinePatchDrawable checked,
            NinePatchDrawable unchecked) {
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.checkboxOff = unchecked;
        checkBoxStyle.checkboxOn = checked;
        checkBoxStyle.font = getFont(false, false, fontSize);
        checkBoxStyle.fontColor = fontColor;
        checkBoxStyle.checkboxOn.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOn.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOff.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        checkBoxStyle.checkboxOff.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        return checkBoxStyle;
    }

    private ProgressBar.ProgressBarStyle getProgressBarStyle(
            NinePatchDrawable background,
            NinePatchDrawable knobBefore) {
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = background;
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
        return progressBarStyle;
    }

    private ProgressBar.ProgressBarStyle getProgressBarStyleData(
            NinePatchDrawable background,
            NinePatchDrawable knobBefore) {
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = background;
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.background.setMinHeight(Constants.PROGRESS_BAR_DATA_THICKNESS);
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_DATA_THICKNESS);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
        return progressBarStyle;
    }

    private ProgressBar.ProgressBarStyle getProgressBarStyleData(
            NinePatchDrawable knobBefore) {
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.knobBefore = knobBefore;
        progressBarStyle.knobBefore.setMinHeight(Constants.PROGRESS_BAR_DATA_THICKNESS);
        progressBarStyle.knobBefore.setMinWidth(Constants.PROGRESS_BAR_MIN_WIDTH);
        return progressBarStyle;
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
        decimalFormatFloat
                = (DecimalFormat) NumberFormat.getNumberInstance(game.getLocale());
        decimalFormatFloat.applyPattern("#,##0.0");
        decimalFormatInt
                = (DecimalFormat) NumberFormat.getNumberInstance(game.getLocale());
        decimalFormatInt.applyPattern("#,##0");
        decimalFormatFloatWithSign
                = (DecimalFormat) NumberFormat.getNumberInstance(game.getLocale());
        decimalFormatFloatWithSign.applyPattern("+#,##0.0;-#,##0.0");
        decimalFormatFloatWithSign.setNegativePrefix("-");
        decimalFormatIntWithSign
                = (DecimalFormat) NumberFormat.getNumberInstance(game.getLocale());
        decimalFormatIntWithSign.applyPattern("+#,##0;-#,##0");
        decimalFormatIntWithSign.setNegativePrefix("-");
        decimalFormatFloatMoney
                = (DecimalFormat) NumberFormat.getNumberInstance(game.getLocale());
        decimalFormatFloatMoney.applyPattern("#,##0.00");
        decimalFormatIntMoney
                = (DecimalFormat) NumberFormat.getNumberInstance(game.getLocale());
        decimalFormatIntMoney.applyPattern("#,##0");
    }

    public Label.LabelStyle getLabelStyleWhiteHugeWithBorder() {
        return labelStyleWhiteHugeWithBorder;
    }

    public Label.LabelStyle getLabelStyleWhiteBig() {
        return labelStyleWhiteBig;
    }

    public Label.LabelStyle getLabelStyleWhiteBigWithBorder() {
        return labelStyleWhiteBigWithBorder;
    }

    public Label.LabelStyle getLabelStyleRedBigWithBorder() {
        return labelStyleRedBigWithBorder;
    }

    public Label.LabelStyle getLabelStyleWhiteMedium() {
        return labelStyleWhiteMedium;
    }

    public Label.LabelStyle getLabelStyleWhiteSmall() {
        return labelStyleWhiteSmall;
    }

    public Label.LabelStyle getLabelStyleWhiteSmallWithBorder() {
        return labelStyleWhiteSmallWithBorder;
    }

    public Label.LabelStyle getLabelStyleRedSmall() {
        return labelStyleRedSmall;
    }

    public Label.LabelStyle getLabelStyleRedSmallWithBorder() {
        return labelStyleRedSmallWithBorder;
    }

    public Label.LabelStyle getLabelStyleWhiteTiny() {
        return labelStyleWhiteTiny;
    }

    public Label.LabelStyle getLabelStyleBlueSmall() {
        return labelStyleBlueSmall;
    }

    public Label.LabelStyle getLabelStyleGreenSmall() {
        return labelStyleGreenSmall;
    }

    public TextButton.TextButtonStyle getTextButtonStyleOrange() {
        return textButtonStyleOrange;
    }

    public TextButton.TextButtonStyle getTextButtonStyleGreen() {
        return textButtonStyleGreen;
    }

    public TextButton.TextButtonStyle getTextButtonStyleRed() {
        return textButtonStyleRed;
    }

    public TextButton.TextButtonStyle getTextButtonStyleNoTexture() {
        return textButtonStyleNoTexture;
    }

    public TextButton.TextButtonStyle getTextButtonStyleEnterBuilding() {
        return textButtonStyleEnterBuilding;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleClose() {
        return imageButtonStyleClose;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleMagazines() {
        return imageButtonStyleMagazines;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleSettings() {
        return imageButtonStyleSettings;
    }

    public ImageButton.ImageButtonStyle getImageButtonStylePause() {
        return imageButtonStylePause;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleRight() {
        return imageButtonStyleRight;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleLeft() {
        return imageButtonStyleLeft;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleUp() {
        return imageButtonStyleUp;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleDown() {
        return imageButtonStyleDown;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleBackpack() {
        return imageButtonStyleBackpack;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleReload() {
        return imageButtonStyleReload;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleGameControllerSignedIn() {
        return imageButtonStyleGameControllerSignedIn;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleGameControllerSignedOut() {
        return imageButtonStyleGameControllerSignedOut;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleAchievements() {
        return imageButtonStyleAchievements;
    }

    public ImageButton.ImageButtonStyle getImageButtonStyleLeaderboards() {
        return imageButtonStyleLeaderboards;
    }

    public ScrollPane.ScrollPaneStyle getScrollPaneStyle() {
        return scrollPaneStyle;
    }

    public Slider.SliderStyle getSliderStyle() {
        return sliderStyle;
    }

    public CheckBox.CheckBoxStyle getCheckBoxStyle() {
        return checkBoxStyle;
    }

    public ProgressBar.ProgressBarStyle getProgressBarStyleHealth() {
        return progressBarStyleHealth;
    }

    public ProgressBar.ProgressBarStyle getProgressBarStyleValueNotBought() {
        return progressBarStyleValueNotBought;
    }

    public ProgressBar.ProgressBarStyle getProgressBarStyleValueBought() {
        return progressBarStyleValueBought;
    }

    public ProgressBar.ProgressBarStyle getProgressBarStyleUpgrade() {
        return progressBarStyleUpgrade;
    }

    public ProgressBar.ProgressBarStyle getProgressBarStyleUpgradeDone() {
        return progressBarStyleUpgradeDone;
    }
}