package com.aleksiprograms.survivalofkeijo.resources;

import com.badlogic.gdx.Gdx;

public class UIDimensions {

    private static float scaleX;
    private static float scaleY;
    public static float scale;

    public static int UI_WEAPONS_IN_ROW;
    public static int UI_WEAPONS_IN_FULL_ROW;

    public static int GAP;
    public static int IMAGE_BUTTON_SIZE_HUGE;
    public static int IMAGE_BUTTON_SIZE_BIG;
    public static int IMAGE_BUTTON_SIZE_MEDIUM;
    public static int IMAGE_BUTTON_SIZE_SMALL;
    public static int IMAGE_BUTTON_SIZE_TINY;
    public static int TEXT_BUTTON_WIDTH;
    public static int TEXT_BUTTON_HEIGHT;
    public static int TEXT_SIZE_HUGE;
    public static int TEXT_SIZE_BIG;
    public static int TEXT_SIZE_MEDIUM;
    public static int TEXT_SIZE_SMALL;
    public static int TEXT_SIZE_TINY;
    public static int TEXT_BUTTON_TEXT_SIZE;
    public static int BOUGHT_ICON_SIZE;
    public static int SCROLL_PANE_SQUARE_SIZE;
    public static int SCROLL_PANE_OVER_SCROLL;
    public static int SCROLL_PANE_SCROLL_SIZE;
    public static int TABLE_TOP_HEIGHT;
    public static int PROGRESS_BAR_WIDTH_LOADING;
    public static int PROGRESS_BAR_WIDTH_HEALTH;
    public static int PROGRESS_BAR_MIN_WIDTH;
    public static int PROGRESS_BAR_HEIGHT;
    public static int PROGRESS_BAR_DATA_HEIGHT;
    public static int TEXT_RIGHT_GAP;
    public static int TEXT_BOTTOM_GAP;
    public static int SETTINGS_CELL_BOTTOM_GAP;

    public static int WEAPON_TITLE_WIDTH;
    public static int WEAPON_VALUE_WIDTH;
    public static int WEAPON_UNIT_WIDTH;
    public static int WEAPON_LEVEL_WIDTH;
    public static int WEAPON_BAR_WIDTH_VALUE;
    public static int WEAPON_TABLE_WIDTH_VALUE;
    public static int WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH;
    public static int WEAPON_BASIC_INFO_DATA_TABLE_WIDTH;

    public static int WEAPON_TITLE_WIDTH_UPGRADE;
    public static int WEAPON_VALUE_WIDTH_UPGRADE;
    public static int WEAPON_UNIT_WIDTH_UPGRADE;
    public static int WEAPON_LEVEL_WIDTH_UPGRADE;
    public static int WEAPON_UPGRADE_WIDTH_UPGRADE;
    public static int WEAPON_PRICE_WIDTH_UPGRADE;
    public static int WEAPON_BAR_WIDTH_UPGRADE;
    public static int WEAPON_TABLE_WIDTH_UPGRADE;

    public static int DIALOG_BOX_WIDTH_WEAPON;
    public static int DIALOG_BOX_HEIGHT_WEAPON;
    public static int DIALOG_BOX_WIDTH;
    public static int DIALOG_BOX_HEIGHT;

    private UIDimensions() {}

    public static void calculate() {
        scaleX = Gdx.graphics.getWidth() / Gdx.graphics.getPpcX() < 7 ? Gdx.graphics.getWidth() / (Gdx.graphics.getPpcX() * 7) : 1f;
        scaleY = Gdx.graphics.getHeight() / Gdx.graphics.getPpcY() < 5 ? Gdx.graphics.getHeight() / (Gdx.graphics.getPpcY() * 5) : 1f;
        scale = scaleX <= scaleY ? scaleX : scaleY;

        UI_WEAPONS_IN_ROW = 3;
        UI_WEAPONS_IN_FULL_ROW = Constants.NUMBER_OF_WEAPONS - (Constants.NUMBER_OF_WEAPONS % UI_WEAPONS_IN_ROW);

        GAP                         = (int)(scale * Gdx.graphics.getPpcY() * 0.15f);
        IMAGE_BUTTON_SIZE_HUGE      = (int)(scale * Gdx.graphics.getPpcY() * 1.5f);
        IMAGE_BUTTON_SIZE_BIG       = (int)(scale * Gdx.graphics.getPpcY() * 1.4f);
        IMAGE_BUTTON_SIZE_MEDIUM    = (int)(scale * Gdx.graphics.getPpcY() * 1.0f);
        IMAGE_BUTTON_SIZE_SMALL     = (int)(scale * Gdx.graphics.getPpcY() * 0.8f);
        IMAGE_BUTTON_SIZE_TINY      = (int)(scale * Gdx.graphics.getPpcY() * 0.6f);
        TEXT_BUTTON_WIDTH           = 2 * IMAGE_BUTTON_SIZE_SMALL + 3 * GAP;
        TEXT_BUTTON_HEIGHT          = (int)(scale * Gdx.graphics.getPpcY() * 0.8f);
        TEXT_SIZE_HUGE              = (int)(scale * Gdx.graphics.getPpcY() * 0.5f);
        TEXT_SIZE_BIG               = (int)(scale * Gdx.graphics.getPpcY() * 0.4f);
        TEXT_SIZE_MEDIUM            = (int)(scale * Gdx.graphics.getPpcY() * 0.35f);
        TEXT_SIZE_SMALL             = (int)(scale * Gdx.graphics.getPpcY() * 0.3f);
        TEXT_SIZE_TINY              = (int)(scale * Gdx.graphics.getPpcY() * 0.24f);
        TEXT_BUTTON_TEXT_SIZE       = (int)(scale * Gdx.graphics.getPpcY() * 0.32f);
        BOUGHT_ICON_SIZE            = (int)(scale * Gdx.graphics.getPpcY() * 0.24f);
        SCROLL_PANE_SQUARE_SIZE     = (int)(scale * Gdx.graphics.getPpcY() * 0.5f);
        SCROLL_PANE_OVER_SCROLL     = (int)(scale * Gdx.graphics.getPpcY() * 1.0f);
        SCROLL_PANE_SCROLL_SIZE     = (int)(scale * Gdx.graphics.getPpcY() * 0.3f);
        TABLE_TOP_HEIGHT            = IMAGE_BUTTON_SIZE_SMALL;
        PROGRESS_BAR_WIDTH_LOADING  = (int)(scale * Gdx.graphics.getWidth() / 2f);
        PROGRESS_BAR_WIDTH_HEALTH   = (int)(scale * Gdx.graphics.getPpcY() * 3.5f);
        PROGRESS_BAR_MIN_WIDTH      = (int)(scale * Gdx.graphics.getPpcY() * 0.3f);
        PROGRESS_BAR_HEIGHT         = (int)(scale * Gdx.graphics.getPpcY() * 0.3f);
        PROGRESS_BAR_DATA_HEIGHT    = (int)(scale * Gdx.graphics.getPpcY() * 0.13f);
        TEXT_RIGHT_GAP              = (int)(scale * Gdx.graphics.getPpcY() * 0.15f);
        TEXT_BOTTOM_GAP             = (int)(scale * Gdx.graphics.getPpcY() * 0.075f);
        SETTINGS_CELL_BOTTOM_GAP    = (int)(scale * Gdx.graphics.getPpcY() * 0.15f);

        WEAPON_TITLE_WIDTH = (int)(scale * Gdx.graphics.getPpcY() * 2.3f);
        WEAPON_VALUE_WIDTH = (int)(scale * Gdx.graphics.getPpcY() * 1.0f);
        WEAPON_UNIT_WIDTH  = (int)(scale * Gdx.graphics.getPpcY() * 0.8f);
        WEAPON_LEVEL_WIDTH = (int)(scale * Gdx.graphics.getPpcY() * 1.5f);

        WEAPON_BAR_WIDTH_VALUE   = WEAPON_TITLE_WIDTH + WEAPON_VALUE_WIDTH + WEAPON_UNIT_WIDTH + WEAPON_LEVEL_WIDTH;
        WEAPON_TABLE_WIDTH_VALUE = 5 * GAP + SCROLL_PANE_SCROLL_SIZE + WEAPON_BAR_WIDTH_VALUE;
        WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH = (int) (scale * Gdx.graphics.getPpcY() * 2.3f);
        WEAPON_BASIC_INFO_DATA_TABLE_WIDTH  = WEAPON_BAR_WIDTH_VALUE - WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH;

        WEAPON_TITLE_WIDTH_UPGRADE   = (int)(scale * Gdx.graphics.getPpcY() * 2.2f);
        WEAPON_VALUE_WIDTH_UPGRADE   = (int)(scale * Gdx.graphics.getPpcY() * 0.8f);
        WEAPON_UNIT_WIDTH_UPGRADE    = (int)(scale * Gdx.graphics.getPpcY() * 0.7f);
        WEAPON_LEVEL_WIDTH_UPGRADE   = (int)(scale * Gdx.graphics.getPpcY() * 1.1f);
        WEAPON_UPGRADE_WIDTH_UPGRADE = (int)(scale * Gdx.graphics.getPpcY() * 0.8f);
        WEAPON_PRICE_WIDTH_UPGRADE   = (int)(scale * Gdx.graphics.getPpcY() * 1.0f);

        WEAPON_BAR_WIDTH_UPGRADE     = WEAPON_TITLE_WIDTH_UPGRADE + WEAPON_VALUE_WIDTH_UPGRADE + WEAPON_UNIT_WIDTH_UPGRADE + WEAPON_LEVEL_WIDTH_UPGRADE + WEAPON_UPGRADE_WIDTH_UPGRADE + WEAPON_UNIT_WIDTH_UPGRADE + WEAPON_PRICE_WIDTH_UPGRADE;
        WEAPON_TABLE_WIDTH_UPGRADE   = 6 * GAP + IMAGE_BUTTON_SIZE_TINY + SCROLL_PANE_SCROLL_SIZE + WEAPON_BAR_WIDTH_UPGRADE;

        DIALOG_BOX_WIDTH_WEAPON  = WEAPON_TABLE_WIDTH_UPGRADE;
        DIALOG_BOX_HEIGHT_WEAPON = (int)(scale * Gdx.graphics.getPpcY() * 5f);
        DIALOG_BOX_WIDTH         = (int)((2 * TEXT_BUTTON_WIDTH + 3 * GAP) * 1.5f);
        DIALOG_BOX_HEIGHT        = (int)(DIALOG_BOX_WIDTH * 0.6f);
    }
}