package com.aleksiprograms.survivalofkeijo.resources;

import java.util.Locale;

public class Constants {

    //DEVELOPMENT SETTINGS (THESE SHOULD BE FALSE IN PRODUCTION)
    public static final boolean DEBUG_DRAW_WORLD = false;
    public static final boolean DEBUG_DRAW_UI = false;
    public static final boolean USE_169_ASPECT_RATION = false;

    public static final Locale LOCALE_ENGLISH = new Locale("en");
    public static final Locale LOCALE_FINNISH = new Locale("fi");
    public static final String STRING_ENGLISH = "ENGLISH";
    public static final String STRING_FINNISH = "SUOMI";

    public static final float FIXED_TIME_STEP = 1f / 60f;
    public static final int MAX_SUB_STEPS = 1;
    public static final int FIELD_OF_VIEW_Y = 67;
    public static final int VIRTUAL_WIDTH = 1920;
    public static final int VIRTUAL_HEIGHT = 1080;
    public static final String SAVED_DATA_FILE_NAME = "saveddata.json";

    public static final int NUMBER_OF_LEVELS = 3;
    public static final int NUMBER_OF_WEAPONS = 6;

    public static final int UI_WEAPONS_IN_ROW = 3;
    public static final int UI_WEAPONS_IN_FULL_ROW
            = Constants.NUMBER_OF_WEAPONS - (Constants.NUMBER_OF_WEAPONS % UI_WEAPONS_IN_ROW);

    public static final int GAP = 24;
    public static final int IMAGE_BUTTON_SIZE_HUGE = 240;
    public static final int IMAGE_BUTTON_SIZE_BIG = 220;
    public static final int IMAGE_BUTTON_SIZE_MEDIUM = 170;
    public static final int IMAGE_BUTTON_SIZE_SMALL = 130;
    public static final int IMAGE_BUTTON_SIZE_TINY = 100;
    public static final int TEXT_BUTTON_WIDTH = 380;
    public static final int TEXT_BUTTON_HEIGHT = 130;
    public static final int TEXT_BUTTON_LANGUAGE_HEIGHT = 90;
    public static final int TEXT_SIZE_HUGE = 85;
    public static final int TEXT_SIZE_BIG = 70;
    public static final int TEXT_SIZE_MEDIUM = 60;
    public static final int TEXT_SIZE_SMALL = 50;
    public static final int TEXT_SIZE_TINY = 40;
    public static final int TEXT_BUTTON_TEXT_SIZE = 50;
    public static final int TABLE_TOP_HEIGHT = IMAGE_BUTTON_SIZE_SMALL;
    public static final int BOUGHT_ICON_SIZE = 40;
    public static final int SCROLL_PANE_SQUARE_SIZE = 80;
    public static final int SCROLL_PANE_OVER_SCROLL = 160;
    public static final int SCROLL_PANE_THICKNESS = 45;
    public static final float SCROLL_PANE_MIN_SPEED = 300f;
    public static final float SCROLL_PANE_MAX_SPEED = 1500f;
    public static final int PROGRESS_BAR_WIDTH_LOADING = 1200;
    public static final int PROGRESS_BAR_WIDTH_HEALTH = 700;
    public static final int PROGRESS_BAR_MIN_WIDTH = SCROLL_PANE_THICKNESS;
    public static final int PROGRESS_BAR_HEIGHT = SCROLL_PANE_THICKNESS;
    public static final int PROGRESS_BAR_DATA_THICKNESS = 20;
    public static final int LEVEL_BUTTON_WIDTH = 2 * TEXT_BUTTON_WIDTH + 3 * GAP;
    public static final int LEVEL_BUTTON_HEIGHT
            = VIRTUAL_HEIGHT - 4 * GAP - SCROLL_PANE_THICKNESS - TABLE_TOP_HEIGHT;
    public static final int TEXT_RIGHT_GAP = 25;
    public static final int TEXT_BOTTOM_GAP = 10;

    public static final int WEAPON_TITLE_WIDTH = 360;
    public static final int WEAPON_VALUE_WIDTH = 160 + 240;
    public static final int WEAPON_UNIT_WIDTH = 130;
    public static final int WEAPON_LEVEL_WIDTH = 240;
    public static final int WEAPON_BAR_WIDTH
            = WEAPON_TITLE_WIDTH + WEAPON_VALUE_WIDTH + WEAPON_UNIT_WIDTH;
    public static final int WEAPON_TABLE_WIDTH
            = 5 * GAP + SCROLL_PANE_THICKNESS + WEAPON_BAR_WIDTH;
    public static final int WEAPON_BASIC_INFO_TITLE_WIDTH = 370;
    public static final int WEAPON_BASIC_INFO_DATA_WIDTH
            = WEAPON_BAR_WIDTH - WEAPON_BASIC_INFO_TITLE_WIDTH;

    public static final int WEAPON_TITLE_WIDTH_UPGRADE = 350;
    public static final int WEAPON_VALUE_WIDTH_UPGRADE = 160 + 180;
    public static final int WEAPON_UNIT_WIDTH_UPGRADE = 110;
    public static final int WEAPON_UPGRADE_WIDTH_UPGRADE = 130 + 160;
    public static final int WEAPON_BAR_WIDTH_UPGRADE
            = WEAPON_TITLE_WIDTH_UPGRADE
            + WEAPON_VALUE_WIDTH_UPGRADE
            + WEAPON_UNIT_WIDTH_UPGRADE
            + WEAPON_UPGRADE_WIDTH_UPGRADE
            + WEAPON_UNIT_WIDTH_UPGRADE;
    public static final int WEAPON_TABLE_WIDTH_UPGRADE
            = 5 * GAP + SCROLL_PANE_THICKNESS + WEAPON_BAR_WIDTH_UPGRADE;

    public static final int DIALOG_BOX_WIDTH_WEAPON = WEAPON_TABLE_WIDTH_UPGRADE;
    public static final int DIALOG_BOX_HEIGHT_WEAPON = 800;
    public static final int DIALOG_BOX_WIDTH = 1200;
    public static final int DIALOG_BOX_HEIGHT = 650;
    public static final int POPUP_WIDTH = DIALOG_BOX_WIDTH;
    public static final int POPUP_HEIGHT = 200;
    public static final int POPUP_BOTTOM_PAD = 150;
    public static final float POPUP_VISIBLE_TIME = 5f;

    public static final float ENEMY_VISIBLE_TIME_AFTER_DEAD_ON_GROUND = 2.5f;
    public static final float CASE_VISIBLE_TIME = 2.5f;

    public static final String FONT = "fonts/RobotoCondensed-Regular.ttf";
    public static final String FONT_BOLD = "fonts/RobotoCondensed-Bold.ttf";

    public static final String BUNDLE = "i18n/Bundle";

    public static final String MODEL_PERSON_PLAYER
            = "models/person-player.g3db";
    public static final String MODEL_PERSON_ENEMY
            = "models/person-enemy.g3db";
    public static final String MODEL_WALKABLE_GROUND_H10
            = "models/walkable-ground-h10.g3db";
    public static final String MODEL_WALKABLE_GROUND_V5
            = "models/walkable-ground-v5.g3db";
    public static final String MODEL_WALKABLE_GROUND_V11
            = "models/walkable-ground-v11.g3db";
    public static final String MODEL_WALKABLE_GROUND_TOP
            = "models/walkable-ground-top.g3db";
    public static final String MODEL_WALKABLE_GRASS
            = "models/walkable-grass.g3db";
    public static final String MODEL_WALKABLE_ROCK_H10
            = "models/walkable-rock-h10.g3db";
    public static final String MODEL_WALKABLE_ROCK_V5
            = "models/walkable-rock-v5.g3db";
    public static final String MODEL_WALKABLE_ROCK_V11
            = "models/walkable-rock-v11.g3db";
    public static final String MODEL_WALKABLE_ROCK_TOP
            = "models/walkable-rock-top.g3db";
    public static final String MODEL_WALKABLE_ROCK_SURFACE
            = "models/walkable-rock-surface.g3db";
    public static final String MODEL_WALKABLE_SNOW_H10
            = "models/walkable-snow-h10.g3db";
    public static final String MODEL_WALKABLE_SNOW_V5
            = "models/walkable-snow-v5.g3db";
    public static final String MODEL_WALKABLE_SNOW_V11
            = "models/walkable-snow-v11.g3db";
    public static final String MODEL_WALKABLE_SNOW_TOP
            = "models/walkable-snow-top.g3db";
    public static final String MODEL_WALKABLE_SNOW_SURFACE
            = "models/walkable-snow-surface.g3db";
    public static final String MODEL_WALKABLE_ICE
            = "models/walkable-ice.g3db";
    public static final String MODEL_SKY_BLUE
            = "models/sky-blue.g3db";
    public static final String MODEL_BUILDING_ENEMY_EMITTER
            = "models/building-enemy-emitter.g3db";
    public static final String MODEL_WEAPON_PISTOL_PLAYER
            = "models/weapon-pistol-player.g3db";
    public static final String MODEL_WEAPON_PISTOL_ENEMY
            = "models/weapon-pistol-enemy.g3db";
    public static final String MODEL_WEAPON_ASSAULT_RIFLE_PLAYER
            = "models/weapon-assault-rifle-player.g3db";
    public static final String MODEL_WEAPON_ASSAULT_RIFLE_ENEMY
            = "models/weapon-assault-rifle-enemy.g3db";
    public static final String MODEL_AMMUNITION_BULLET_PLAYER
            = "models/ammunition-bullet-player.g3db";
    public static final String MODEL_AMMUNITION_BULLET_ENEMY
            = "models/ammunition-bullet-enemy.g3db";
    public static final String MODEL_AMMUNITION_CASE_PLAYER
            = "models/ammunition-case-player.g3db";
    public static final String MODEL_AMMUNITION_CASE_ENEMY
            = "models/ammunition-case-enemy.g3db";

    public static final String LEVEL_001 = "levels/level-001.g3db";
    public static final String LEVEL_002 = "levels/level-002.g3db";
    public static final String LEVEL_003 = "levels/level-003.g3db";

    public static final String TEXTURE_ATLAS
            = "textures/texture-atlas.atlas";
    public static final String TEXTURE_ATLAS_LOADING
            = "textures/texture-atlas-loading.atlas";
    public static final String TEXTURE_BUTTON_CLOSE_UP
            = "button-close-up";
    public static final String TEXTURE_BUTTON_CLOSE_DOWN
            = "button-close-down";
    public static final String TEXTURE_BUTTON_PLUS_UP
            = "button-plus-up";
    public static final String TEXTURE_BUTTON_PLUS_DOWN
            = "button-plus-down";
    public static final String TEXTURE_BUTTON_PLUS_DISABLED
            = "button-plus-disabled";
    public static final String TEXTURE_BUTTON_SETTINGS_UP
            = "button-settings-up";
    public static final String TEXTURE_BUTTON_SETTINGS_DOWN
            = "button-settings-down";
    public static final String TEXTURE_BUTTON_PAUSE_UP
            = "button-pause-up";
    public static final String TEXTURE_BUTTON_PAUSE_DOWN
            = "button-pause-down";
    public static final String TEXTURE_BUTTON_GAME_RIGHT_UP
            = "button-game-right-up";
    public static final String TEXTURE_BUTTON_GAME_RIGHT_DOWN
            = "button-game-right-down";
    public static final String TEXTURE_BUTTON_GAME_LEFT_UP
            = "button-game-left-up";
    public static final String TEXTURE_BUTTON_GAME_LEFT_DOWN
            = "button-game-left-down";
    public static final String TEXTURE_BUTTON_GAME_UP_UP
            = "button-game-up-up";
    public static final String TEXTURE_BUTTON_GAME_UP_DOWN
            = "button-game-up-down";
    public static final String TEXTURE_BUTTON_GAME_DOWN_UP
            = "button-game-down-up";
    public static final String TEXTURE_BUTTON_GAME_DOWN_DOWN
            = "button-game-down-down";
    public static final String TEXTURE_BUTTON_GAME_BACKPACK_UP
            = "button-game-backpack-up";
    public static final String TEXTURE_BUTTON_GAME_BACKPACK_DOWN
            = "button-game-backpack-down";
    public static final String TEXTURE_BUTTON_GAME_RELOAD_UP
            = "button-game-reload-up";
    public static final String TEXTURE_BUTTON_GAME_RELOAD_DOWN
            = "button-game-reload-down";
    public static final String TEXTURE_BUTTON_GAME_EMPTY_UP
            = "button-game-empty-up";
    public static final String TEXTURE_BUTTON_GAME_EMPTY_DOWN
            = "button-game-empty-down";
    public static final String TEXTURE_BUTTON_ACHIEVEMENTS_UP
            = "button-achievements-up";
    public static final String TEXTURE_BUTTON_ACHIEVEMENTS_DOWN
            = "button-achievements-down";
    public static final String TEXTURE_BUTTON_ACHIEVEMENTS_DISABLED
            = "button-achievements-disabled";
    public static final String TEXTURE_BUTTON_LEADERBOARDS_UP
            = "button-leaderboards-up";
    public static final String TEXTURE_BUTTON_LEADERBOARDS_DOWN
            = "button-leaderboards-down";
    public static final String TEXTURE_BUTTON_LEADERBOARDS_DISABLED
            = "button-leaderboards-disabled";
    public static final String TEXTURE_GAMES_CONRTROLLER_SIGNED_IN
            = "games-controller-signed-in";
    public static final String TEXTURE_GAMES_CONRTROLLER_SIGNED_OUT
            = "games-controller-signed-out";
    public static final String TEXTURE_TABLE_BACKGROUND
            = "table-background";
    public static final String TEXTURE_TABLE_BACKGROUND_SECONDARY
            = "table-background-secondary";
    public static final String TEXTURE_SCREEN_BACKGROUND
            = "screen-background";
    public static final String TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX
            = "screen-background-on-dialog-box";
    public static final String TEXTURE_POPUP_BACKGROUND
            = "pupup-background";
    public static final String TEXTURE_BAR_DATA_BACKGROUND
            = "bar-data-background";
    public static final String TEXTURE_BAR_DATA_VALUE
            = "bar-data-value";
    public static final String TEXTURE_BAR_DATA_UPGRADE
            = "bar-data-upgrade";
    public static final String TEXTURE_BUTTON_DOWN
            = "button-down";
    public static final String TEXTURE_BUTTON_DOWN_GREEN
            = "button-down-green";
    public static final String TEXTURE_BUTTON_DOWN_RED
            = "button-down-red";
    public static final String TEXTURE_CHECKBOX_ON
            = "checkbox-on";
    public static final String TEXTURE_CHECK_MARK
            = "check-mark";
    public static final String TEXTURE_BUTTON_UP_OR_OFF_OR_BG
            = "button-up-or-off-or-bg";
    public static final String TEXTURE_SCROLL_PANE_KNOB
            = "scroll-pane-knob";
    public static final String TEXTURE_SLIDER_KNOB
            = "slider-knob";
    public static final String TEXTURE_BAR_FILL
            = "bar-fill";
    public static final String TEXTURE_BAR_FILL_HEALTH
            = "bar-fill-health";

    public static final String PFX_AMMUNITION_GROUND_HIT
            = "effects/ammunition-hit-ground-128px.pfx";
    public static final String PFX_AMMUNITION_GRASS_HIT
            = "effects/ammunition-hit-grass-128px.pfx";

    public static final int LEVEL_001_ID = 1;
    public static final int LEVEL_002_ID = 2;
    public static final int LEVEL_003_ID = 3;

    public static final int PISTOL_ID = 0;
    public static final int ASSAULT_RIFLE_ID = 1;
    public static final int SHOTGUN_ID = 2;
    public static final int SNIPER_ID = 3;
    public static final int MACHINE_GUN_ID = 4;
    public static final int ROCKET_LAUNCHER_ID = 5;

    public static final short CATEGORY_PLAYER = 1;
    public static final short CATEGORY_ENEMY = 2;
    public static final short CATEGORY_WEAPON_PLAYER = 4;
    public static final short CATEGORY_WEAPON_ENEMY = 8;
    public static final short CATEGORY_CASE = 16;
    public static final short CATEGORY_ENEMY_EMITTER = 32;
    public static final short CATEGORY_PARTICLE = 64;
    public static final short CATEGORY_SOLID = 128;
    public static final short CATEGORY_SENSOR_MOVEMENT = 256;
    public static final short CATEGORY_ENEMY_SENSOR_PLAYER = 512;
    public static final short CATEGORY_ENEMY_SHOOTABLE = 1024;
    public static final short CATEGORY_COIN = 2048;

    public static final short MASK_PLAYER
            = CATEGORY_ENEMY
            | CATEGORY_WEAPON_ENEMY
            | CATEGORY_ENEMY_EMITTER
            | CATEGORY_SOLID
            | CATEGORY_ENEMY_SENSOR_PLAYER
            | CATEGORY_COIN;
    public static final short MASK_ENEMY
            = CATEGORY_PLAYER
            | CATEGORY_WEAPON_PLAYER
            | CATEGORY_WEAPON_ENEMY
            | CATEGORY_SOLID
            | CATEGORY_SENSOR_MOVEMENT
            | CATEGORY_ENEMY_SENSOR_PLAYER
            | CATEGORY_ENEMY_SHOOTABLE;
    public static final short MASK_WEAPON_PLAYER
            = CATEGORY_ENEMY
            | CATEGORY_SOLID;
    public static final short MASK_WEAPON_ENEMY
            = CATEGORY_PLAYER
            | CATEGORY_ENEMY
            | CATEGORY_SOLID;
    public static final short MASK_CASE
            = CATEGORY_CASE
            | CATEGORY_SOLID;
    public static final short MASK_ENEMY_EMITTER
            = CATEGORY_PLAYER
            | CATEGORY_SENSOR_MOVEMENT;
    public static final short MASK_PARTICLE
            = CATEGORY_SOLID;
    public static final short MASK_SOLID
            = CATEGORY_PLAYER
            | CATEGORY_ENEMY
            | CATEGORY_WEAPON_PLAYER
            | CATEGORY_WEAPON_ENEMY
            | CATEGORY_CASE
            | CATEGORY_PARTICLE
            | CATEGORY_SENSOR_MOVEMENT
            | CATEGORY_ENEMY_SENSOR_PLAYER
            | CATEGORY_ENEMY_SHOOTABLE;
    public static final short MASK_SENSOR_MOVEMENT
            = CATEGORY_ENEMY
            | CATEGORY_ENEMY_EMITTER
            | CATEGORY_SOLID;
    public static final short MASK_ENEMY_SENSOR_PLAYER
            = CATEGORY_PLAYER
            | CATEGORY_ENEMY
            | CATEGORY_SOLID;
    public static final short MASK_ENEMY_SHOOTABLE
            = CATEGORY_ENEMY
            | CATEGORY_SOLID;
    public static final short MASK_COIN
            = CATEGORY_PLAYER;

    private Constants() {
    }
}