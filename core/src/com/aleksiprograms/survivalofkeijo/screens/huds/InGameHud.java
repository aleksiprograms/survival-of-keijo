package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.WeaponWithAmmo;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
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

    private Label labelHealthTitle;
    private ProgressBar progressBarHealth;
    private Label labelHealth;
    private Label labelHealthLow;
    private Label labelMaxHealth;
    private Label labelHealthSeparator;
    private Label labelAmmoTitle;
    private Label labelAmmoInMag;
    private Label labelAmmoInMagLow;
    private Label labelAmmoRest;
    private Label labelAmmoRestLow;
    private Label labelAmmoSeparator;
    private Label labelAmmoReloading;
    private Label labelWeapon;
    private Label labelScoreTitle;
    private Label labelScore;
    private Label labelWaveTitle;
    private Label labelWave;
    private Label labelMoney;

    private Table tableWaveBeginning;
    private Label labelWaveBeginningWaveTitle;
    private Label labelWaveBeginningWave;

    private ImageButton btEnterShop;
    private ImageButton btEnterHome;
    private boolean btEnterGunStoreVisibility;
    private boolean btEnterHomeVisibility;

    public static boolean buttonRightPressed = false;
    public static boolean buttonLeftPressed = false;
    public static boolean buttonUpPressed = false;
    public static boolean buttonDownPressed = false;
    public static boolean buttonReloadPressed = false;
    public static boolean buttonShootPressed = false;

    public InGameHud(TheGame game) {
        super(game);
        initializeHud();
    }

    public void setButtonEnterShopVisibility(boolean visible) {
        if (visible != btEnterGunStoreVisibility) {
            btEnterGunStoreVisibility = visible;
            btEnterShop.setVisible(visible);
        }
    }

    public void setBtEnterHospitalVisibility(boolean visible) {
        if (visible != btEnterHomeVisibility) {
            btEnterHomeVisibility = visible;
            btEnterHome.setVisible(visible);
        }
    }

    @Override
    public void updateHudData() {
        super.updateHudData();
        if (game.gameWorld.enemyManager.inTheBeginningOfNewWave) {
            tableWaveBeginning.setVisible(true);
            labelWaveBeginningWaveTitle.setText("");//remove
            labelWaveBeginningWave.setText("");//remove
            //labelWaveBeginningWave.setText(game.styles.getFormattedInt(game.gameWorld.enemyManager.wave));
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
        labelWave.setText("-");
        //labelWave.setText(game.styles.getFormattedInt(game.gameWorld.enemyManager.wave));
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

    private void initializeHud() {
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

        Stack stackHealth = new Stack();
        stackHealth.add(labelHealth);
        stackHealth.add(labelHealthLow);
        Table tableHealth = new Table();
        tableHealth.add(stackHealth).align(Align.left);
        tableHealth.add(labelHealthSeparator).align(Align.left);
        tableHealth.add(labelMaxHealth).align(Align.left).padRight(Constants.GAP);
        tableHealth.add(progressBarHealth).align(Align.left).width(Constants.PROGRESS_BAR_WIDTH_HEALTH).height(labelAmmoInMag.getHeight()).expandX();

        Stack stackInMag = new Stack();
        stackInMag.add(labelAmmoInMag);
        stackInMag.add(labelAmmoInMagLow);
        Stack stackRest = new Stack();
        stackRest.add(labelAmmoRest);
        stackRest.add(labelAmmoRestLow);
        Table tableAmmo = new Table();
        tableAmmo.add(stackInMag).align(Align.left);
        tableAmmo.add(labelAmmoSeparator).align(Align.left);
        tableAmmo.add(stackRest).align(Align.left).padRight(Constants.GAP);
        tableAmmo.add(labelAmmoReloading).align(Align.left).expandX();
        //tableAmmo.add(labelWeapon).align(Align.left).expandX();

        Table tableWave = new Table();
        tableWave.add(labelWave).align(Align.left);

        Table tableData = new Table();
        tableData.left();
        tableData.setTouchable(Touchable.enabled);
        tableData.add(labelHealthTitle).align(Align.left).padRight(Constants.GAP);
        tableData.add(tableHealth).align(Align.left).expandX();
        tableData.row();
        tableData.add(labelAmmoTitle).align(Align.left).padRight(Constants.GAP);
        tableData.add(tableAmmo).align(Align.left).expandX();
        tableData.row();
        tableData.add(labelScoreTitle).align(Align.left).padRight(Constants.GAP);
        tableData.add(labelScore).align(Align.left).expandX();
        tableData.row();
        tableData.add(labelWaveTitle).align(Align.left).padRight(Constants.GAP);
        tableData.add(tableWave).align(Align.left).expandX();

        ImageButton btPause = new ImageButton(game.styles.imageButtonStylePause);
        ImageButton buttonRight = new ImageButton(game.styles.imageButtonStyleRight);
        ImageButton buttonLeft = new ImageButton(game.styles.imageButtonStyleLeft);
        ImageButton buttonUp = new ImageButton(game.styles.imageButtonStyleUp);
        ImageButton buttonDown = new ImageButton(game.styles.imageButtonStyleDown);
        ImageButton btBackpack = new ImageButton(game.styles.imageButtonStyleBackpack);
        ImageButton buttonReload = new ImageButton(game.styles.imageButtonStyleReload);
        btEnterShop = new ImageButton(game.styles.imageButtonStyleEnter);
        btEnterHome = new ImageButton(game.styles.imageButtonStyleEnter);
        Stack stackEnterBuilding = new Stack();
        stackEnterBuilding.add(btEnterShop);
        stackEnterBuilding.add(btEnterHome);
        btEnterShop.setVisible(false);
        btEnterHome.setVisible(false);
        btEnterShop.addListener(inputListenerEnterShop);
        btEnterHome.addListener(inputListenerEnterHome);

        Table tableShootTopRight = new Table();
        tableShootTopRight.setTouchable(Touchable.enabled);
        Table tableTopRight = new Table();
        Table tableMoney = new Table();
        tableMoney.setTouchable(Touchable.enabled);
        tableMoney.add(labelMoney).padTop((Constants.IMAGE_BUTTON_SIZE_SMALL - labelMoney.getHeight()) / 2f).align(Align.topRight).expandY();
        tableTopRight.add(tableMoney).padRight(Constants.GAP * 2).align(Align.topRight).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).growY();
        tableTopRight.add(btPause).align(Align.topRight).padBottom(Constants.GAP).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM);
        tableTopRight.row();
        tableTopRight.add(tableShootTopRight).colspan(2).grow();

        tableWaveBeginning = new Table();
        tableWaveBeginning.add(labelWaveBeginningWaveTitle).padRight(Constants.GAP);
        tableWaveBeginning.add(labelWaveBeginningWave);

        Table tableShootBottomRow1 = new Table();
        tableShootBottomRow1.setTouchable(Touchable.enabled);
        tableShootBottomRow1.add(tableWaveBeginning);

        Table tableBottomRow1 = new Table();
        tableBottomRow1.add(btBackpack).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).padTop(Constants.GAP).padRight(Constants.GAP).padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        tableBottomRow1.add(tableShootBottomRow1).grow();
        tableBottomRow1.add(buttonReload).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).padTop(Constants.GAP).padLeft(Constants.GAP).padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);

        Table tableShootBottomRow2 = new Table();
        tableShootBottomRow2.setTouchable(Touchable.enabled);
        tableShootBottomRow2.add(stackEnterBuilding).expand().align(Align.bottom).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM * 2).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM);

        Table tableBottomRow2 = new Table();
        tableBottomRow2.add(buttonLeft).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(Constants.GAP).padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        tableBottomRow2.add(buttonRight).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP);
        tableBottomRow2.add(tableShootBottomRow2).grow().align(Align.bottom).padLeft(Constants.GAP).padRight(Constants.GAP);
        tableBottomRow2.add(buttonDown).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(Constants.GAP);
        tableBottomRow2.add(buttonUp).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);

        Table tableMiddle = new Table();
        tableMiddle.setTouchable(Touchable.enabled);

        super.pad(Constants.GAP);
        super.add(tableData).align(Align.topLeft).growX().padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        super.add(tableTopRight).fillY().padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0).align(Align.topRight);
        super.row();
        super.add(tableMiddle).colspan(2).grow();
        super.row();
        super.add(tableBottomRow1).colspan(2).growX();
        super.row();
        super.add(tableBottomRow2).colspan(2).growX();

        InputListener inputListenerLeft = new InputListener() {
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

        InputListener inputListenerRight = new InputListener() {
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

        InputListener inputListenerUp = new InputListener() {
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

        InputListener inputListenerDown = new InputListener() {
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

        InputListener inputListenerReload = new InputListener() {
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

        InputListener inputListenerShoot = new InputListener() {
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