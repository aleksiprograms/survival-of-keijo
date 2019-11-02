package com.aleksiprograms.survivalofkeijo.resources;

import java.util.Locale;

public class Constants {

    //DEVELOPMENT SETTINGS (THESE SHOULD BE FALSE IN PRODUCTION)
    public static final boolean DEBUG_DRAW_WORLD      = false;
    public static final boolean DEBUG_DRAW_UI         = false;
    public static final boolean USE_169_ASPECT_RATION = false;

    public static final Locale LOCALE_ENGLISH = new Locale("en");
    public static final Locale LOCALE_FINNISH = new Locale("fi");
    public static final String STRING_ENGLISH = "ENGLISH";
    public static final String STRING_FINNISH = "SUOMI";

    public static final float FIXED_TIME_STEP = 1f/60f;
    public static final int MAX_SUB_STEPS = 1;
    public static final int FIELD_OF_VIEW_Y = 67;
    public static final int VIRTUAL_WIDTH = 1920;
    public static final int VIRTUAL_HEIGHT = 1080;
    public static final String SAVED_DATA_FILE_NAME = "saveddata.json";

    public static final int NUMBER_OF_LEVELS = 6;
    public static final int NUMBER_OF_WEAPONS = 7;

    public static final int UI_WEAPONS_IN_ROW = 3;
    public static final int UI_WEAPONS_IN_FULL_ROW = Constants.NUMBER_OF_WEAPONS - (Constants.NUMBER_OF_WEAPONS % UI_WEAPONS_IN_ROW);

    public static final int GAP                         = 24;
    public static final int IMAGE_BUTTON_SIZE_HUGE      = 240;
    public static final int IMAGE_BUTTON_SIZE_BIG       = 220;
    public static final int IMAGE_BUTTON_SIZE_MEDIUM    = 160;
    public static final int IMAGE_BUTTON_SIZE_SMALL     = 130;
    public static final int IMAGE_BUTTON_SIZE_TINY      = 100;
    public static final int TEXT_BUTTON_WIDTH           = 2 * IMAGE_BUTTON_SIZE_SMALL + 3 * GAP;
    public static final int TEXT_BUTTON_HEIGHT          = 130;
    public static final int TEXT_BUTTON_LANGUAGE_HEIGHT = 90;
    public static final int TEXT_SIZE_HUGE              = 80;
    public static final int TEXT_SIZE_BIG               = 65;
    public static final int TEXT_SIZE_MEDIUM            = 55;
    public static final int TEXT_SIZE_SMALL             = 45;
    public static final int TEXT_SIZE_TINY              = 35;
    public static final int TEXT_BUTTON_TEXT_SIZE       = 50;
    public static final int BOUGHT_ICON_SIZE            = 40;
    public static final int SCROLL_PANE_SQUARE_SIZE     = 80;
    public static final int SCROLL_PANE_OVER_SCROLL     = 160;
    public static final int SCROLL_PANE_SCROLL_SIZE     = 50;
    public static final int TABLE_TOP_HEIGHT            = IMAGE_BUTTON_SIZE_SMALL;
    public static final int PROGRESS_BAR_WIDTH_LOADING  = 1200;
    public static final int PROGRESS_BAR_WIDTH_HEALTH   = 500;
    public static final int PROGRESS_BAR_MIN_WIDTH      = 50;
    public static final int PROGRESS_BAR_HEIGHT         = 50;
    public static final int PROGRESS_BAR_DATA_HEIGHT    = 20;
    public static final int TEXT_RIGHT_GAP              = 25;
    public static final int TEXT_BOTTOM_GAP             = 10;

    public static final int WEAPON_TITLE_WIDTH = 360;
    public static final int WEAPON_VALUE_WIDTH = 160;
    public static final int WEAPON_UNIT_WIDTH  = 130;
    public static final int WEAPON_LEVEL_WIDTH = 240;

    public static final int WEAPON_BAR_WIDTH_VALUE   = WEAPON_TITLE_WIDTH + WEAPON_VALUE_WIDTH + WEAPON_UNIT_WIDTH + WEAPON_LEVEL_WIDTH;
    public static final int WEAPON_TABLE_WIDTH_VALUE = 5 * GAP + SCROLL_PANE_SCROLL_SIZE + WEAPON_BAR_WIDTH_VALUE;
    public static final int WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH = 370;
    public static final int WEAPON_BASIC_INFO_DATA_TABLE_WIDTH  = WEAPON_BAR_WIDTH_VALUE - WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH;

    public static final int WEAPON_TITLE_WIDTH_UPGRADE   = 350;
    public static final int WEAPON_VALUE_WIDTH_UPGRADE   = 160;
    public static final int WEAPON_UNIT_WIDTH_UPGRADE    = 110;
    public static final int WEAPON_LEVEL_WIDTH_UPGRADE   = 180;
    public static final int WEAPON_UPGRADE_WIDTH_UPGRADE = 130;
    public static final int WEAPON_PRICE_WIDTH_UPGRADE   = 160;

    public static final int WEAPON_BAR_WIDTH_UPGRADE     = WEAPON_TITLE_WIDTH_UPGRADE + WEAPON_VALUE_WIDTH_UPGRADE + WEAPON_UNIT_WIDTH_UPGRADE + WEAPON_LEVEL_WIDTH_UPGRADE + WEAPON_UPGRADE_WIDTH_UPGRADE + WEAPON_UNIT_WIDTH_UPGRADE + WEAPON_PRICE_WIDTH_UPGRADE;
    public static final int WEAPON_TABLE_WIDTH_UPGRADE   = 6 * GAP + IMAGE_BUTTON_SIZE_TINY + SCROLL_PANE_SCROLL_SIZE + WEAPON_BAR_WIDTH_UPGRADE;

    public static final int DIALOG_BOX_WIDTH_WEAPON  = WEAPON_TABLE_WIDTH_UPGRADE;
    public static final int DIALOG_BOX_HEIGHT_WEAPON = 800;
    public static final int DIALOG_BOX_WIDTH         = (int)((2 * TEXT_BUTTON_WIDTH + 3 * GAP) * 1.5f);
    public static final int DIALOG_BOX_HEIGHT        = (int)(DIALOG_BOX_WIDTH * 0.6f);
    public static final int POPUP_WIDTH              = DIALOG_BOX_WIDTH;
    public static final int POPUP_HEIGHT             = 200;
    public static final int POPUP_BOTTOM_PAD         = 150;
    public static final float POPUP_VISIBLE_TIME     = 5f;

    public static final float ENEMY_VISIBLE_TIME_AFTER_DEAD_ON_GROUND = 3f;
    public static final float CASE_VISIBLE_TIME                       = 1.5f;

    public static float SCROLL_PANE_MIN_SPEED = 300f;
    public static float SCROLL_PANE_MAX_SPEED = 1500f;

    public static final String BUNDLE = "i18n/Bundle";

    public static final String MODEL_PERSON_PLAYER               = "models/person-player.g3db";
    public static final String MODEL_PERSON_ENEMY                = "models/person-enemy.g3db";
    public static final String MODEL_WALKABLE_GROUND_H10         = "models/walkable-ground-h10.g3db";
    public static final String MODEL_WALKABLE_GROUND_H20         = "models/walkable-ground-h20.g3db";
    public static final String MODEL_WALKABLE_GROUND_H30         = "models/walkable-ground-h30.g3db";
    public static final String MODEL_WALKABLE_GROUND_V5          = "models/walkable-ground-v5.g3db";
    public static final String MODEL_WALKABLE_GROUND_V11         = "models/walkable-ground-v11.g3db";
    public static final String MODEL_WALKABLE_GRASS              = "models/walkable-grass.g3db";
    public static final String MODEL_SKY_BLUE                    = "models/sky-blue.g3db";
    public static final String MODEL_BUILDING_ENEMY_EMITTER      = "models/building-enemy-emitter.g3db";
    public static final String MODEL_WEAPON_PISTOL_PLAYER        = "models/weapon-pistol-player.g3db";
    public static final String MODEL_WEAPON_PISTOL_ENEMY         = "models/weapon-pistol-enemy.g3db";
    public static final String MODEL_WEAPON_ASSAULT_RIFLE_PLAYER = "models/weapon-assault-rifle-player.g3db";
    public static final String MODEL_WEAPON_ASSAULT_RIFLE_ENEMY  = "models/weapon-assault-rifle-enemy.g3db";
    public static final String MODEL_WEAPON_KNIFE_ENEMY          = "models/weapon-knife-enemy.g3db";
    public static final String MODEL_AMMUNITION_BULLET_PLAYER    = "models/ammunition-bullet-player.g3db";
    public static final String MODEL_AMMUNITION_BULLET_ENEMY     = "models/ammunition-bullet-enemy.g3db";
    public static final String MODEL_AMMUNITION_CASE_PLAYER      = "models/ammunition-case-player.g3db";
    public static final String MODEL_AMMUNITION_CASE_ENEMY       = "models/ammunition-case-enemy.g3db";

    public static final String LEVEL_001 = "levels/level-001.g3db";

    public static final String TEXTURE_ATLAS = "textures/texture-atlas-350x.atlas";
    public static final String TEXTURE_ATLAS_LOADING = "textures/texture-atlas-loading-350x.atlas";
    public static final String TEXTURE_BUTTON_CLOSE_UP        = "button_close_up";
    public static final String TEXTURE_BUTTON_CLOSE_DOWN      = "button_close_down";
    public static final String TEXTURE_BUTTON_PLUS_UP         = "button_plus_up";
    public static final String TEXTURE_BUTTON_PLUS_DOWN       = "button_plus_down";
    public static final String TEXTURE_BUTTON_PLUS_DISABLED   = "button_plus_disabled";
    public static final String TEXTURE_BUTTON_MINUS_UP        = "button_minus_up";
    public static final String TEXTURE_BUTTON_MINUS_DOWN      = "button_minus_down";
    public static final String TEXTURE_BUTTON_MINUS_DISABLED  = "button_minus_disabled";
    public static final String TEXTURE_BUTTON_SELECT_BOX_UP   = "button_selectbox_up";
    public static final String TEXTURE_BUTTON_SELECT_BOX_DOWN = "button_selectbox_down";
    public static final String TEXTURE_BUTTON_SETTINGS_UP     = "button_settings_up";
    public static final String TEXTURE_BUTTON_SETTINGS_DOWN   = "button_settings_down";
    public static final String TEXTURE_BUTTON_GAME_PAUSE_UP   = "button_game_pause_up";
    public static final String TEXTURE_BUTTON_GAME_PAUSE_DOWN = "button_game_pause_down";
    public static final String TEXTURE_BUTTON_GAME_RIGHT_UP   = "button_game_right_up";
    public static final String TEXTURE_BUTTON_GAME_RIGHT_DOWN = "button_game_right_down";
    public static final String TEXTURE_BUTTON_GAME_LEFT_UP    = "button_game_left_up";
    public static final String TEXTURE_BUTTON_GAME_LEFT_DOWN  = "button_game_left_down";
    public static final String TEXTURE_BUTTON_GAME_UP_UP      = "button_game_up_up";
    public static final String TEXTURE_BUTTON_GAME_UP_DOWN    = "button_game_up_down";
    public static final String TEXTURE_BUTTON_GAME_DOWN_UP    = "button_game_down_up";
    public static final String TEXTURE_BUTTON_GAME_DOWN_DOWN  = "button_game_down_down";
    public static final String TEXTURE_BUTTON_GAME_BACKPACK_UP   = "button_game_backpack_up";
    public static final String TEXTURE_BUTTON_GAME_BACKPACK_DOWN = "button_game_backpack_down";
    public static final String TEXTURE_BUTTON_GAME_RELOAD_UP     = "button_game_reload_up";
    public static final String TEXTURE_BUTTON_GAME_RELOAD_DOWN   = "button_game_reload_down";
    public static final String TEXTURE_BUTTON_GAME_ENTER_UP      = "button_game_enter_up";
    public static final String TEXTURE_BUTTON_GAME_ENTER_DOWN    = "button_game_enter_down";
    public static final String TEXTURE_BUTTON_ACHIEVEMENTS_UP       = "button_achievements_up";
    public static final String TEXTURE_BUTTON_ACHIEVEMENTS_DOWN     = "button_achievements_down";
    public static final String TEXTURE_BUTTON_ACHIEVEMENTS_DISABLED = "button_achievements_disabled";
    public static final String TEXTURE_BUTTON_LEADERBOARDS_UP       = "button_leaderboards_up";
    public static final String TEXTURE_BUTTON_LEADERBOARDS_DOWN     = "button_leaderboards_down";
    public static final String TEXTURE_BUTTON_LEADERBOARDS_DISABLED = "button_leaderboards_disabled";
    public static final String TEXTURE_GAMES_CONRTROLLER_SIGNED_IN  = "games_controller_signed_in";
    public static final String TEXTURE_GAMES_CONRTROLLER_SIGNED_OUT = "games_controller_signed_out";
    public static final String TEXTURE_TABLE_BACKGROUND             = "table_background";
    public static final String TEXTURE_TABLE_BACKGROUND_SECONDARY   = "table_background_secondary";
    public static final String TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX = "screen_background_on_dialog_box";
    public static final String TEXTURE_BAR_FILL_BACKGROUND = "bar_fill_background";
    public static final String TEXTURE_BAR_FILL_VALUE      = "bar_fill_value";
    public static final String TEXTURE_BAR_FILL_UPGRADE    = "bar_fill_upgrade";
    public static final String TEXTURE_BUTTON_D       = "button_down";
    public static final String TEXTURE_BUTTON_D_GREEN = "button_down_green";
    public static final String TEXTURE_BUTTON_D_RED   = "button_down_red";
    public static final String TEXTURE_CHECKBOX_ON    = "checkbox_on";
    public static final String TEXTURE_CHECK_MARK     = "check_mark";
    public static final String TEXTURE_BOUGHT_ICON    = "bought_icon";
    public static final String TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF = "ui_elem_bg_or_up_or_off";
    public static final String TEXTURE_SCROLL_PANE_KNOB        = "scroll_pane_knob";
    public static final String TEXTURE_BAR_FILL                = "bar_fill";
    public static final String TEXTURE_BAR_FILL_HEALTH         = "bar_fill_health";

    public static final String PFX_AMMUNITION_GROUND_HIT_128PX = "effects/ammunition-hit-ground-128px.pfx";
    public static final String PFX_AMMUNITION_GRASS_HIT_128PX = "effects/ammunition-hit-grass-128px.pfx";

    public static final int HANDS_ID = -1;
    public static final int KNIFE_ID = 0;
    public static final int PISTOL_ID = 1;
    public static final int ASSAULT_RIFLE_ID = 2;
    public static final int SHOTGUN_ID = 3;
    public static final int SNIPER_ID = 4;
    public static final int MACHINE_GUN_ID = 5;
    public static final int ROCKET_LAUNCHER_ID = 6;

    public static final short CATEGORY_PLAYER              = 1;
    public static final short CATEGORY_ENEMY               = 2;
    public static final short CATEGORY_WEAPON_PLAYER       = 4;
    public static final short CATEGORY_WEAPON_ENEMY        = 8;
    public static final short CATEGORY_CASE                = 16;
    public static final short CATEGORY_ENEMY_EMITTER       = 32;
    public static final short CATEGORY_PARTICLE            = 64;
    public static final short CATEGORY_SOLID               = 128;
    public static final short CATEGORY_SENSOR_OBJECT       = 256;
    public static final short CATEGORY_SENSOR_MOVEMENT     = 512;
    public static final short CATEGORY_ENEMY_SENSOR_PLAYER = 1024;
    public static final short CATEGORY_ENEMY_SHOOTABLE     = 2048;
    public static final short CATEGORY_COIN                = 4096;

    public static final short MASK_PLAYER              = CATEGORY_ENEMY | CATEGORY_WEAPON_ENEMY | CATEGORY_ENEMY_EMITTER | CATEGORY_SOLID | CATEGORY_SENSOR_OBJECT | CATEGORY_ENEMY_SENSOR_PLAYER | CATEGORY_COIN;
    public static final short MASK_ENEMY               = CATEGORY_PLAYER | CATEGORY_WEAPON_PLAYER | CATEGORY_WEAPON_ENEMY | CATEGORY_SOLID | CATEGORY_SENSOR_OBJECT | CATEGORY_SENSOR_MOVEMENT | CATEGORY_ENEMY_SENSOR_PLAYER | CATEGORY_ENEMY_SHOOTABLE;
    public static final short MASK_WEAPON_PLAYER       = CATEGORY_ENEMY | CATEGORY_SOLID;
    public static final short MASK_WEAPON_ENEMY        = CATEGORY_PLAYER | CATEGORY_ENEMY | CATEGORY_SOLID;
    public static final short MASK_CASE                = CATEGORY_CASE | CATEGORY_SOLID;
    public static final short MASK_ENEMY_EMITTER       = CATEGORY_PLAYER | CATEGORY_SENSOR_MOVEMENT;
    public static final short MASK_PARTICLE            = CATEGORY_SOLID;
    public static final short MASK_SOLID               = CATEGORY_PLAYER | CATEGORY_ENEMY | CATEGORY_WEAPON_PLAYER | CATEGORY_WEAPON_ENEMY | CATEGORY_CASE | CATEGORY_PARTICLE | CATEGORY_SENSOR_MOVEMENT | CATEGORY_ENEMY_SENSOR_PLAYER | CATEGORY_ENEMY_SHOOTABLE;
    public static final short MASK_SENSOR_OBJECT       = CATEGORY_PLAYER | CATEGORY_ENEMY;
    public static final short MASK_SENSOR_MOVEMENT     = CATEGORY_ENEMY | CATEGORY_ENEMY_EMITTER | CATEGORY_SOLID;
    public static final short MASK_ENEMY_SENSOR_PLAYER = CATEGORY_PLAYER | CATEGORY_ENEMY | CATEGORY_SOLID;
    public static final short MASK_ENEMY_SHOOTABLE     = CATEGORY_ENEMY | CATEGORY_SOLID;
    public static final short MASK_COIN                = CATEGORY_PLAYER;

    /*GAP                         = (int)(scale * Gdx.graphics.getPpcY() * 0.15f);
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
        DIALOG_BOX_HEIGHT        = (int)(DIALOG_BOX_WIDTH * 0.6f);*/

    private Constants() {}
}