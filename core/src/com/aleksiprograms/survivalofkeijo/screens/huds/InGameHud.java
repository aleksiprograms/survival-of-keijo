package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;

public class InGameHud extends AbstractHud {

    private Label labelHealthTitle;
    private ProgressBar progressBarHealth;
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

    private TextButton buttonEnterShop;
    private TextButton buttonEnterHospital;
    private boolean buttonEnterShopVisibility;
    private boolean buttonEnterHospitalVisibility;

    public static boolean buttonRightPressed = false;
    public static boolean buttonLeftPressed = false;
    public static boolean buttonUpPressed = false;
    public static boolean buttonReloadPressed = false;
    public static boolean buttonShootPressed = false;

    public InGameHud(TheGame game) {
        super(game);
        initializeHud();
    }

    public void setButtonEnterShopVisibility(boolean visible) {
        if (visible != buttonEnterShopVisibility) {
            buttonEnterShopVisibility = visible;
            buttonEnterShop.setVisible(visible);
        }
    }

    public void setBtEnterHospitalVisibility(boolean visible) {
        if (visible != buttonEnterHospitalVisibility) {
            buttonEnterHospitalVisibility = visible;
            buttonEnterHospital.setVisible(visible);
        }
    }

    @Override
    public void updateHud() {
        super.updateHud();
        buttonEnterShop.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonEnterBuilding"));
        buttonEnterHospital.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonEnterBuilding"));
        if (game.gameWorld.enemyManager.inTheBeginningOfNewWave) {
            tableWaveBeginning.setVisible(true);
            labelWaveBeginningWaveTitle.setText("");//remove
            labelWaveBeginningWave.setText("");//remove
            //labelWaveBeginningWave.setText(game.styles.getFormattedInt(game.gameWorld.enemyManager.wave));
        } else {
            tableWaveBeginning.setVisible(false);
        }
        labelHealthTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("inGameTitleHealth"));
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
            labelAmmoTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("inGameTitleAmmo"));
            labelAmmoInMag.setText(game.styles.getFormattedInt((game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoRest.setText(game.styles.getFormattedInt(game.gameWorld.player.currentWeapon.weaponData.numberOfAmmo - (game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoInMagLow.setText(game.styles.getFormattedInt((game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoRestLow.setText(game.styles.getFormattedInt(game.gameWorld.player.currentWeapon.weaponData.numberOfAmmo - (game.gameWorld.player.currentWeapon).inMagazine));
            labelAmmoSeparator.setText(" + ");
            if ((game.gameWorld.player.currentWeapon).inMagazine < 0.35f * (game.gameWorld.player.currentWeapon).weaponData.magazineSize) {
                labelAmmoInMag.setVisible(false);
                labelAmmoInMagLow.setVisible(true);
            } else {
                labelAmmoInMag.setVisible(true);
                labelAmmoInMagLow.setVisible(false);
            }
            if ((game.gameWorld.player.currentWeapon).weaponData.numberOfAmmo - (game.gameWorld.player.currentWeapon).inMagazine - 1 < 2f * (game.gameWorld.player.currentWeapon).weaponData.magazineSize) {
                labelAmmoRest.setVisible(false);
                labelAmmoRestLow.setVisible(true);
            } else {
                labelAmmoRest.setVisible(true);
                labelAmmoRestLow.setVisible(false);
            }
        labelMoney.setText("");
        //labelMoney.setText(game.styles.getFormattedFloatMoney(game.gameWorld.player.money) + " \u20AC");
    }

    @Override
    protected void initializeHud() {
        super.initializeHud();
        
        super.top();
        super.left();
        super.setFillParent(true);

        labelHealthTitle = new Label("", game.styles.labelStyleWhiteSmallWithBorder);
        progressBarHealth = new ProgressBar(0, 500, 1, false, game.styles.progressBarStyleHealth);
        progressBarHealth.setVisualInterpolation(new Interpolation.Pow(1));
        labelAmmoTitle = new Label("", game.styles.labelStyleWhiteSmallWithBorder);
        labelAmmoInMag = new Label("0", game.styles.labelStyleWhiteBigWithBorder);
        labelAmmoRest = new Label("0", game.styles.labelStyleWhiteBigWithBorder);
        labelAmmoInMagLow = new Label("0", game.styles.labelStyleRedBigWithBorder);
        labelAmmoRestLow = new Label("0", game.styles.labelStyleRedBigWithBorder);
        labelAmmoSeparator = new Label("", game.styles.labelStyleWhiteBigWithBorder);
        labelAmmoReloading = new Label("", game.styles.labelStyleRedSmallWithBorder);
        labelWeapon = new Label("", game.styles.labelStyleWhiteSmallWithBorder);
        labelScoreTitle = new Label("", game.styles.labelStyleWhiteSmallWithBorder);
        labelScore = new Label("0", game.styles.labelStyleWhiteBigWithBorder);
        labelWaveTitle = new Label("", game.styles.labelStyleWhiteSmallWithBorder);
        labelWave = new Label("1", game.styles.labelStyleWhiteBigWithBorder);
        labelMoney = new Label("0", game.styles.labelStyleWhiteBigWithBorder);

        labelWaveBeginningWaveTitle = new Label("WAVE", game.styles.labelStyleWhiteBig);
        labelWaveBeginningWave = new Label("", game.styles.labelStyleWhiteHugeWithBorder);

        Table tableHealth = new Table();
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
        ImageButton buttonShoot = new ImageButton(game.styles.imageButtonStyleDown);
        ImageButton btBackpack = new ImageButton(game.styles.imageButtonStyleBackpack);
        ImageButton buttonReload = new ImageButton(game.styles.imageButtonStyleReload);
        buttonEnterShop = new TextButton("", game.styles.textButtonStyleEnterBuilding);
        buttonEnterHospital = new TextButton("", game.styles.textButtonStyleEnterBuilding);
        Stack stackEnterBuilding = new Stack();
        stackEnterBuilding.add(buttonEnterShop);
        stackEnterBuilding.add(buttonEnterHospital);
        buttonEnterShop.setVisible(false);
        buttonEnterHospital.setVisible(false);

        Table tableTopRight = new Table();
        Table tableMoney = new Table();
        tableMoney.add(labelMoney).padTop((Constants.IMAGE_BUTTON_SIZE_SMALL - labelMoney.getHeight()) / 2f).align(Align.topRight).expandY();
        tableTopRight.add(tableMoney).padRight(Constants.GAP * 2).align(Align.topRight).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).growY();
        tableTopRight.add(btPause).align(Align.topRight).padBottom(Constants.GAP).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM);

        tableWaveBeginning = new Table();
        tableWaveBeginning.add(labelWaveBeginningWaveTitle).padRight(Constants.GAP);
        tableWaveBeginning.add(labelWaveBeginningWave);

        Table tableBottom1Middle = new Table();
        tableBottom1Middle.add(tableWaveBeginning);

        Table tableBottom1 = new Table();
        tableBottom1.add(btBackpack).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).padTop(Constants.GAP).padRight(Constants.GAP).padLeft(Constants.USE_169_ASPECT_RATION ? 200 : 0);
        tableBottom1.add(tableBottom1Middle).grow();
        tableBottom1.add(buttonReload).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).padTop(Constants.GAP).padLeft(Constants.GAP).padRight(Constants.USE_169_ASPECT_RATION ? 200 : 0);

        Table tableBottom2Middle = new Table();
        tableBottom2Middle.add(stackEnterBuilding).expand().align(Align.bottom).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT);

        Table tableBottom2 = new Table();
        tableBottom2.add(buttonLeft).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(Constants.GAP).padLeft(Constants.USE_169_ASPECT_RATION ? 200 : 0);
        tableBottom2.add(buttonRight).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP);
        tableBottom2.add(tableBottom2Middle).grow().align(Align.bottom).padLeft(Constants.GAP).padRight(Constants.GAP);
        tableBottom2.add(buttonShoot).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(Constants.GAP);
        tableBottom2.add(buttonUp).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(Constants.USE_169_ASPECT_RATION ? 200 : 0);

        super.pad(Constants.GAP);
        super.add(tableData).align(Align.topLeft).expand().padLeft(Constants.USE_169_ASPECT_RATION ? 200 : 0);
        super.add(tableTopRight).padRight(Constants.USE_169_ASPECT_RATION ? 200 : 0).align(Align.topRight);
        super.row();
        super.add(tableBottom1).colspan(2).growX();
        super.row();
        super.add(tableBottom2).colspan(2).growX();

        InputListener inputListenerPause = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {
                    game.gameScreen.setGameState(GameState.PAUSED);
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = true;
                    game.gameScreen.pausedHud.updateHud();
                    game.gameScreen.stage.addActor(game.gameScreen.pausedHud);
                }
            }
        };

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
                if (!buttonEnterShop.isPressed() && !buttonEnterHospital.isPressed()) {
                    buttonShootPressed = true;
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonShootPressed = false;
            }
        };

        InputListener inputListenerEnterBackpack = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {
                    game.gameScreen.setGameState(GameState.IN_BACKPACK);
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = true;
                    game.gameScreen.backpackHud.updateHud();
                    game.gameScreen.stage.addActor(game.gameScreen.backpackHud);
                }
            }
        };

        InputListener inputListenerEnterShop = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.gameScreen.setGameState(GameState.IN_SHOP);
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = true;
                    game.gameScreen.shopHud.updateHud();
                    game.gameScreen.stage.addActor(game.gameScreen.shopHud);
                }
            }
        };

        InputListener inputListenerEnterHospital = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {}
            }
        };

        btPause.addListener(inputListenerPause);
        btBackpack.addListener(inputListenerEnterBackpack);
        buttonEnterShop.addListener(inputListenerEnterShop);
        buttonEnterHospital.addListener(inputListenerEnterHospital);

        buttonLeft.addListener(inputListenerLeft);
        buttonRight.addListener(inputListenerRight);
        buttonUp.addListener(inputListenerUp);
        buttonShoot.addListener(inputListenerShoot);
        buttonReload.addListener(inputListenerReload);
    }
}