package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.WeaponWithAmmo;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.resources.UIDimensions;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class InGameHud extends AbstractHud {

    private Table tableData;
    private Table tableHealth;
    private Label labelHealthTitle;
    private ProgressBar progressBarHealth;
    private Stack stackHealth;
    private Label labelHealth;
    private Label labelHealthLow;
    private Label labelMaxHealth;
    private Label labelHealthSeparator;
    private Table tableAmmo;
    private Label labelAmmoTitle;
    private Stack stackInMag;
    private Label labelAmmoInMag;
    private Label labelAmmoInMagLow;
    private Stack stackRest;
    private Label labelAmmoRest;
    private Label labelAmmoRestLow;
    private Label labelAmmoSeparator;
    private Label labelAmmoReloading;
    private Label labelWeapon;
    private Label labelScoreTitle;
    private Label labelScore;
    private Table tableWave;
    private Label labelWaveTitle;
    private Label labelWave;
    private Label labelMoney;

    private Table tableWaveBeginning;
    private Label labelWaveBeginningWaveTitle;
    private Label labelWaveBeginningWave;

    private Stack stackEnterBuilding;
    private ImageButton btEnterShop;
    private ImageButton btEnterHome;
    private boolean btEnterGunStoreVisibility;
    private boolean btEnterHomeVisibility;

    private ImageButton btPause;
    private ImageButton buttonRight;
    private ImageButton buttonLeft;
    private ImageButton buttonUp;
    private ImageButton buttonDown;
    private ImageButton btBackpack;
    private ImageButton buttonReload;

    private Table tableShootTopRight;
    private Table tableTopRight;
    private Table tableMoney;
    private Table tableMiddle;
    private Table tableShootBottomRow1;
    private Table tableShootBottomRow2;
    private Table tableBottomRow1;
    private Table tableBottomRow2;

    public static boolean buttonRightPressed = false;
    public static boolean buttonLeftPressed = false;
    public static boolean buttonUpPressed = false;
    public static boolean buttonDownPressed = false;
    public static boolean buttonReloadPressed = false;
    public static boolean buttonShootPressed = false;

    private InputListener inputListenerRight;
    private InputListener inputListenerLeft;
    private InputListener inputListenerUp;
    private InputListener inputListenerDown;
    private InputListener inputListenerReload;
    private InputListener inputListenerShoot;

    public InGameHud(TheGame game) {
        super(game);
        initializeHud();
        createHudTable();
    }

    @Override
    public void createHudTable() {
        super.createHudTable();

        tableHealth.clear();
        tableAmmo.clear();
        tableWave.clear();
        tableData.clear();
        tableMoney.clear();
        tableTopRight.clear();
        tableWaveBeginning.clear();
        tableShootBottomRow1.clear();
        tableBottomRow1.clear();
        tableShootBottomRow2.clear();
        tableBottomRow2.clear();
        super.clear();

        labelHealthTitle.setStyle(game.styles.labelStyleWhiteSmall);
        /*labelHealth.setFontScale(UIDimensions.scale);
        labelHealthLow.setFontScale(UIDimensions.scale);
        labelMaxHealth.setFontScale(UIDimensions.scale);
        labelHealthSeparator.setFontScale(UIDimensions.scale);
        labelAmmoTitle.setFontScale(UIDimensions.scale);
        labelAmmoInMag.setFontScale(UIDimensions.scale);
        labelAmmoInMagLow.setFontScale(UIDimensions.scale);
        labelAmmoRest.setFontScale(UIDimensions.scale);
        labelAmmoRestLow.setFontScale(UIDimensions.scale);
        labelAmmoSeparator.setFontScale(UIDimensions.scale);
        labelAmmoReloading.setFontScale(UIDimensions.scale);
        labelWeapon.setFontScale(UIDimensions.scale);
        labelScoreTitle.setFontScale(UIDimensions.scale);
        labelScore.setFontScale(UIDimensions.scale);
        labelWaveTitle.setFontScale(UIDimensions.scale);
        labelWave.setFontScale(UIDimensions.scale);
        labelMoney.setFontScale(UIDimensions.scale);*/

        tableHealth.add(stackHealth).align(Align.left);
        tableHealth.add(labelHealthSeparator).align(Align.left);
        tableHealth.add(labelMaxHealth).align(Align.left).padRight(UIDimensions.GAP);
        tableHealth.add(progressBarHealth).align(Align.left).width(UIDimensions.PROGRESS_BAR_WIDTH_HEALTH).height(labelAmmoInMag.getHeight()).expandX();

        tableAmmo.add(stackInMag).align(Align.left);
        tableAmmo.add(labelAmmoSeparator).align(Align.left);
        tableAmmo.add(stackRest).align(Align.left).padRight(UIDimensions.GAP);
        tableAmmo.add(labelAmmoReloading).align(Align.left).expandX();
        //tableAmmo.add(labelWeapon).align(Align.left).expandX();

        tableWave.add(labelWave).align(Align.left);

        tableData.add(labelHealthTitle).align(Align.left).padRight(UIDimensions.GAP);
        tableData.add(tableHealth).align(Align.left).expandX();
        tableData.row();
        tableData.add(labelAmmoTitle).align(Align.left).padRight(UIDimensions.GAP);
        tableData.add(tableAmmo).align(Align.left).expandX();
        tableData.row();
        tableData.add(labelScoreTitle).align(Align.left).padRight(UIDimensions.GAP);
        tableData.add(labelScore).align(Align.left).expandX();
        tableData.row();
        tableData.add(labelWaveTitle).align(Align.left).padRight(UIDimensions.GAP);
        tableData.add(tableWave).align(Align.left).expandX();

        tableMoney.add(labelMoney).padTop((UIDimensions.IMAGE_BUTTON_SIZE_SMALL - labelMoney.getHeight()) / 2f).align(Align.topRight).expandY();
        tableTopRight.add(tableMoney).padRight(UIDimensions.GAP * 2).align(Align.topRight).height(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM).growY();
        tableTopRight.add(btPause).align(Align.topRight).padBottom(UIDimensions.GAP).width(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM).height(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM);
        tableTopRight.row();
        tableTopRight.add(tableShootTopRight).colspan(2).grow();

        tableWaveBeginning.add(labelWaveBeginningWaveTitle).padRight(UIDimensions.GAP);
        tableWaveBeginning.add(labelWaveBeginningWave);

        tableShootBottomRow1.add(tableWaveBeginning);

        tableBottomRow1.add(btBackpack).align(Align.bottomLeft).width(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM).height(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM).padTop(UIDimensions.GAP).padRight(UIDimensions.GAP).padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        tableBottomRow1.add(tableShootBottomRow1).grow();
        tableBottomRow1.add(buttonReload).align(Align.bottomRight).width(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM).height(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM).padTop(UIDimensions.GAP).padLeft(UIDimensions.GAP).padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);

        tableShootBottomRow2.add(stackEnterBuilding).expand().align(Align.bottom).width(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM * 2).height(UIDimensions.IMAGE_BUTTON_SIZE_MEDIUM);

        tableBottomRow2.add(buttonLeft).align(Align.bottomLeft).width(UIDimensions.IMAGE_BUTTON_SIZE_BIG).height(UIDimensions.IMAGE_BUTTON_SIZE_BIG).padTop(UIDimensions.GAP).padRight(UIDimensions.GAP).padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        tableBottomRow2.add(buttonRight).align(Align.bottomLeft).width(UIDimensions.IMAGE_BUTTON_SIZE_BIG).height(UIDimensions.IMAGE_BUTTON_SIZE_BIG).padTop(UIDimensions.GAP);
        tableBottomRow2.add(tableShootBottomRow2).grow().align(Align.bottom).padLeft(UIDimensions.GAP).padRight(UIDimensions.GAP);
        tableBottomRow2.add(buttonDown).align(Align.bottomRight).width(UIDimensions.IMAGE_BUTTON_SIZE_BIG).height(UIDimensions.IMAGE_BUTTON_SIZE_BIG).padTop(UIDimensions.GAP).padRight(UIDimensions.GAP);
        tableBottomRow2.add(buttonUp).align(Align.bottomRight).width(UIDimensions.IMAGE_BUTTON_SIZE_BIG).height(UIDimensions.IMAGE_BUTTON_SIZE_BIG).padTop(UIDimensions.GAP).padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);

        super.pad(UIDimensions.GAP);
        super.add(tableData).align(Align.topLeft).growX().padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        super.add(tableTopRight).fillY().padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0).align(Align.topRight);
        super.row();
        super.add(tableMiddle).colspan(2).grow();
        super.row();
        super.add(tableBottomRow1).colspan(2).growX();
        super.row();
        super.add(tableBottomRow2).colspan(2).growX();

        setTouchableAreas();
    }

    @Override
    public void updateHudData() {
        super.updateHudData();
        if (game.gameWorld.enemyManager.inTheBeginningOfNewWave) {
            tableWaveBeginning.setVisible(true);
            //labelWaveBeginningWaveTitle.setText("");//remove
            labelWaveBeginningWave.setText(game.styles.getFormattedInt(game.gameWorld.enemyManager.wave));
        } else {
            tableWaveBeginning.setVisible(false);
        }
        labelHealthTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("inGameTitleHealth"));
        if (game.gameWorld.player.health <= 0) {
            labelHealth.setText(game.styles.getFormattedInt(0));
        } else {
            labelHealth.setText(game.styles.getFormattedInt(game.gameWorld.player.health));
        }
        labelHealthLow.setText(game.styles.getFormattedInt(game.gameWorld.player.health));
        if (game.gameWorld.player.health < 0.1f * game.gameWorld.player.maxHealth) {
            labelHealth.setVisible(false);
            labelHealthLow.setVisible(true);
        } else {
            labelHealth.setVisible(true);
            labelHealthLow.setVisible(false);
        }
        labelHealthSeparator.setText(" / ");
        labelMaxHealth.setText(game.styles.getFormattedInt(game.gameWorld.player.maxHealth));
        labelScoreTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("inGameTitleScore"));
        labelScore.setText(game.styles.getFormattedInt(0));
        labelWaveTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("inGameTitleWave"));
        labelWave.setText(game.styles.getFormattedInt(game.gameWorld.enemyManager.wave));
        progressBarHealth.setRange(0, game.gameWorld.player.maxHealth);
        progressBarHealth.setValue(game.gameWorld.player.health);
        if (game.gameWorld.player.currentWeapon.reloading) {
            labelAmmoReloading.setText("(RELOADING...)");
        } else {
            labelAmmoReloading.setText("");
        }
        labelWeapon.setText("(" + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get(game.gameWorld.player.currentWeapon.weaponData.nameID) + ")");
        if (game.gameWorld.player.currentWeapon instanceof WeaponWithAmmo) {
            labelAmmoTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("inGameTitleAmmo"));
            labelAmmoInMag.setText(game.styles.getFormattedInt(((WeaponWithAmmo) game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoRest.setText(game.styles.getFormattedInt(game.gameWorld.player.currentWeapon.weaponData.numberOfAmmo - ((WeaponWithAmmo) game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoInMagLow.setText(game.styles.getFormattedInt(((WeaponWithAmmo) game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoRestLow.setText(game.styles.getFormattedInt(game.gameWorld.player.currentWeapon.weaponData.numberOfAmmo - ((WeaponWithAmmo) game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoSeparator.setText(" + ");
            if (((WeaponWithAmmo) game.gameWorld.player.currentWeapon).inMagazine < 0.35f * ((WeaponWithAmmo) game.gameWorld.player.currentWeapon).weaponData.magazineSize) {
                labelAmmoInMag.setVisible(false);
                labelAmmoInMagLow.setVisible(true);
            } else {
                labelAmmoInMag.setVisible(true);
                labelAmmoInMagLow.setVisible(false);
            }
            if (((WeaponWithAmmo) game.gameWorld.player.currentWeapon).weaponData.numberOfAmmo - ((WeaponWithAmmo) game.gameWorld.player.currentWeapon).inMagazine - 1 < 2f * ((WeaponWithAmmo) game.gameWorld.player.currentWeapon).weaponData.magazineSize) {
                labelAmmoRest.setVisible(false);
                labelAmmoRestLow.setVisible(true);
            } else {
                labelAmmoRest.setVisible(true);
                labelAmmoRestLow.setVisible(false);
            }
        } else {
            labelAmmoTitle.setText("SHARPNESS");
        }
        labelMoney.setText("");
        //labelMoney.setText(game.styles.getFormattedFloatMoney(game.gameWorld.player.money) + " \u20AC");
    }

    public void setButtonEnterShopVisibility(boolean visible) {
        if (visible != btEnterGunStoreVisibility) {
            btEnterGunStoreVisibility = visible;
            btEnterShop.setVisible(visible);
        }
    }

    public void setBtEnterHomeVisibility(boolean visible) {
        if (visible != btEnterHomeVisibility) {
            btEnterHomeVisibility = visible;
            btEnterHome.setVisible(visible);
        }
    }

    @Override
    public void initializeHud() {
        super.initializeHud();

        super.top();
        super.left();
        super.setFillParent(true);

        labelHealthTitle = new Label("", game.styles.labelStyleWhiteSmall);
        progressBarHealth = new ProgressBar(0, 500, 1, false, game.styles.progressBarStyleHealth);
        progressBarHealth.setVisualInterpolation(new Interpolation.Pow(1));
        labelHealth = new Label("0", game.styles.labelStyleWhiteBig);
        labelHealthLow = new Label("0", game.styles.labelStyleRedBig);
        labelMaxHealth = new Label("0", game.styles.labelStyleWhiteBig);
        labelHealthSeparator = new Label("", game.styles.labelStyleWhiteBig);
        labelAmmoTitle = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoInMag = new Label("0", game.styles.labelStyleWhiteBig);
        labelAmmoRest = new Label("0", game.styles.labelStyleWhiteBig);
        labelAmmoInMagLow = new Label("0", game.styles.labelStyleRedBig);
        labelAmmoRestLow = new Label("0", game.styles.labelStyleRedBig);
        labelAmmoSeparator = new Label("", game.styles.labelStyleWhiteBig);
        labelAmmoReloading = new Label("", game.styles.labelStyleRedSmall);
        labelWeapon = new Label("", game.styles.labelStyleWhiteSmall);
        labelScoreTitle = new Label("", game.styles.labelStyleWhiteSmall);
        labelScore = new Label("0", game.styles.labelStyleWhiteBig);
        labelWaveTitle = new Label("", game.styles.labelStyleWhiteSmall);
        labelWave = new Label("1", game.styles.labelStyleWhiteBig);
        labelMoney = new Label("0", game.styles.labelStyleWhiteBig);

        labelWaveBeginningWaveTitle = new Label("WAVE", game.styles.labelStyleWhiteBig);
        labelWaveBeginningWave = new Label("", game.styles.labelStyleWhiteHuge);

        stackHealth = new Stack();
        stackHealth.add(labelHealth);
        stackHealth.add(labelHealthLow);
        tableHealth = new Table();

        stackInMag = new Stack();
        stackInMag.add(labelAmmoInMag);
        stackInMag.add(labelAmmoInMagLow);
        stackRest = new Stack();
        stackRest.add(labelAmmoRest);
        stackRest.add(labelAmmoRestLow);
        tableAmmo = new Table();

        tableWave = new Table();

        tableData = new Table();
        tableData.left();
        tableData.setTouchable(Touchable.enabled);

        btPause = new ImageButton(game.styles.imageButtonStylePause);
        buttonRight = new ImageButton(game.styles.imageButtonStyleRight);
        buttonLeft = new ImageButton(game.styles.imageButtonStyleLeft);
        buttonUp = new ImageButton(game.styles.imageButtonStyleUp);
        buttonDown = new ImageButton(game.styles.imageButtonStyleDown);
        btBackpack = new ImageButton(game.styles.imageButtonStyleBackpack);
        buttonReload = new ImageButton(game.styles.imageButtonStyleReload);
        btEnterShop = new ImageButton(game.styles.imageButtonStyleEnter);
        btEnterHome = new ImageButton(game.styles.imageButtonStyleEnter);
        stackEnterBuilding = new Stack();
        stackEnterBuilding.add(btEnterShop);
        stackEnterBuilding.add(btEnterHome);
        btEnterShop.setVisible(false);
        btEnterHome.setVisible(false);
        btEnterShop.addListener(inputListenerEnterShop);
        btEnterHome.addListener(inputListenerEnterHome);

        tableShootTopRight = new Table();
        tableTopRight = new Table();
        tableMoney = new Table();
        tableWaveBeginning = new Table();
        tableShootBottomRow1 = new Table();
        tableBottomRow1 = new Table();
        tableShootBottomRow2 = new Table();
        tableBottomRow2 = new Table();
        tableMiddle = new Table();

        inputListenerLeft = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonLeftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonLeftPressed = false;
            }
        };

        inputListenerRight = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonRightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonRightPressed = false;
            }
        };

        inputListenerUp = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonUpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonUpPressed = false;
            }
        };

        inputListenerDown = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonDownPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonDownPressed = false;
            }
        };

        inputListenerReload = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonReloadPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonReloadPressed = false;
            }
        };

        inputListenerShoot = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonShootPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonShootPressed = false;
            }
        };
    }

    private void setTouchableAreas() {
        tableShootTopRight.setTouchable(Touchable.enabled);
        tableMoney.setTouchable(Touchable.enabled);
        tableShootBottomRow1.setTouchable(Touchable.enabled);
        tableShootBottomRow2.setTouchable(Touchable.enabled);
        tableMiddle.setTouchable(Touchable.enabled);

        btPause.addListener(inputListenerPause);
        btBackpack.addListener(inputListenerEnterBackpack);

        buttonLeft.addListener(inputListenerLeft);
        buttonRight.addListener(inputListenerRight);
        buttonUp.addListener(inputListenerUp);
        buttonDown.addListener(inputListenerDown);
        buttonReload.addListener(inputListenerReload);

        tableMiddle.addListener(inputListenerShoot);
        tableData.addListener(inputListenerShoot);
        tableMoney.addListener(inputListenerShoot);
        tableShootTopRight.addListener(inputListenerShoot);
        tableShootBottomRow1.addListener(inputListenerShoot);
        tableShootBottomRow2.addListener(inputListenerShoot);
    }
}