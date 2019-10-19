package com.aleksiprograms.survivalofkeijo.resources;

import java.util.Locale;

public class Constants {
    public static final Locale LOCALE_ENGLISH = new Locale("en");
    public static final Locale LOCALE_FINNISH = new Locale("fi");
    public static final String STRING_ENGLISH = "ENGLISH";
    public static final String STRING_FINNISH = "SUOMI";

    public static final float FIXED_TIME_STEP = 1f/60f;
    public static final int NUMBER_OF_LEVELS = 6;
    public static final int NUMBER_OF_WEAPONS = 7;

    public static final float ENEMY_VISIBLE_TIME_AFTER_DEAD_ON_GROUND = 4f;
    public static final float CASE_VISIBLE_TIME                       = 4f;

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
    public static final String TEXTURE_BUTTON_SAVEDGAMES_UP         = "button_savedgames_up";
    public static final String TEXTURE_BUTTON_SAVEDGAMES_DOWN       = "button_savedgames_down";
    public static final String TEXTURE_BUTTON_SAVEDGAMES_DISABLED   = "button_savedgames_disabled";
    public static final String TEXTURE_GAMES_CONRTROLLER_SIGNED_IN  = "games_controller_signed_in";
    public static final String TEXTURE_GAMES_CONRTROLLER_SIGNED_OUT = "games_controller_signed_out";
    public static final String TEXTURE_TABLE_BACKGROUND             = "table_background";
    public static final String TEXTURE_TABLE_BACKGROUND_SECONDARY   = "table_background_secondary";
    public static final String TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX = "screen_background_on_dialog_box";
    public static final String TEXTURE_BAR_FILL_BACKGROUND = "bar_fill_background";
    public static final String TEXTURE_BAR_FILL_VALUE      = "bar_fill_value";
    public static final String TEXTURE_BAR_FILL_VALUE_ATL  = "bar_fill_value_alt";
    public static final String TEXTURE_BAR_FILL_UPGRADE    = "bar_fill_upgrade";
    public static final String TEXTURE_PARTICLE       = "particle";
    public static final String TEXTURE_BUTTON_D       = "button_down";
    public static final String TEXTURE_BUTTON_D_GREEN = "button_down_green";
    public static final String TEXTURE_BUTTON_D_RED   = "button_down_red";
    public static final String TEXTURE_CHECKBOX_ON    = "checkbox_on";
    public static final String TEXTURE_CHECK_MARK     = "check_mark";
    public static final String TEXTURE_BOUGHT_ICON    = "bought_icon";
    public static final String TEXTURE_COIN           = "coin";
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

    private Constants() {}
}