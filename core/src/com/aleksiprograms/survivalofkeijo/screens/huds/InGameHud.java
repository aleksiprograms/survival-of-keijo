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
    private Label labelAmmoRest;
    private Label labelAmmoInMagLow;
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

    public static boolean btRightPressed = false;
    public static boolean btLeftPressed = false;
    public static boolean btUpPressed = false;
    public static boolean btDownPressed = false;
    public static boolean btShootPressed = false;
    public static boolean btReloadPressed = false;

    public InGameHud(TheGame game) {
        super(game);

        super.pad(Constants.GAP);
        super.top();
        super.left();
        super.setFillParent(true);

        labelHealthTitle = new Label("", game.styles.skinLabelWhiteSmall);
        progressBarHealth = new ProgressBar(0, 500, 1, false, game.styles.skinProgressBarHealth);
        progressBarHealth.setVisualInterpolation(new Interpolation.Pow(1));
        labelHealth = new Label("0", game.styles.skinLabelWhiteBig);
        labelHealthLow = new Label("0", game.styles.skinLabelRedBig);
        labelMaxHealth = new Label("0", game.styles.skinLabelWhiteBig);
        labelHealthSeparator = new Label("", game.styles.skinLabelWhiteBig);
        labelAmmoTitle = new Label("", game.styles.skinLabelWhiteSmall);
        labelAmmoInMag = new Label("0", game.styles.skinLabelWhiteBig);
        labelAmmoRest = new Label("0", game.styles.skinLabelWhiteBig);
        labelAmmoInMagLow = new Label("0", game.styles.skinLabelRedBig);
        labelAmmoRestLow = new Label("0", game.styles.skinLabelRedBig);
        labelAmmoSeparator = new Label("", game.styles.skinLabelWhiteBig);
        labelAmmoReloading = new Label("", game.styles.skinLabelRedSmall);
        labelWeapon = new Label("", game.styles.skinLabelWhiteSmall);
        labelScoreTitle = new Label("", game.styles.skinLabelWhiteSmall);
        labelScore = new Label("0", game.styles.skinLabelWhiteBig);
        labelWaveTitle = new Label("", game.styles.skinLabelWhiteSmall);
        labelWave = new Label("1", game.styles.skinLabelWhiteBig);
        labelMoney = new Label("0", game.styles.skinLabelWhiteBig);

        labelWaveBeginningWaveTitle = new Label("WAVE", game.styles.skinLabelWhiteBig);
        labelWaveBeginningWave = new Label("", game.styles.skinLabelWhiteHuge);

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

        final ImageButton btPause = new ImageButton(game.styles.skinImageButtonPause);
        ImageButton btRight = new ImageButton(game.styles.skinImageButtonRight);
        ImageButton btLeft = new ImageButton(game.styles.skinImageButtonLeft);
        ImageButton btUp = new ImageButton(game.styles.skinImageButtonUp);
        ImageButton btDown = new ImageButton(game.styles.skinImageButtonDown);
        ImageButton btBackpack = new ImageButton(game.styles.skinImageButtonBackpack);
        ImageButton btReload = new ImageButton(game.styles.skinImageButtonReload);
        btEnterShop = new ImageButton(game.styles.skinImageButtonEnter);
        btEnterHome = new ImageButton(game.styles.skinImageButtonEnter);
        Stack stack = new Stack();
        stack.add(btEnterShop);
        stack.add(btEnterHome);
        btEnterShop.setVisible(false);
        btEnterHome.setVisible(false);
        btEnterShop.addListener(inputListenerEnterShop);
        btEnterHome.addListener(inputListenerEnterHome);

        Table tableShoot1 = new Table();
        tableShoot1.setTouchable(Touchable.enabled);
        Table tableTopRight = new Table();
        Table tableMoney = new Table();
        tableMoney.setTouchable(Touchable.enabled);
        tableMoney.add(labelMoney).padTop((Constants.IMAGE_BUTTON_SIZE_SMALL - labelMoney.getHeight()) / 2f).align(Align.topRight).expandY();
        tableTopRight.add(tableMoney).padRight(Constants.GAP * 2).align(Align.topRight).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).growY();
        tableTopRight.add(btPause).align(Align.topRight).padBottom(Constants.GAP).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM);
        tableTopRight.row();
        tableTopRight.add(tableShoot1).colspan(2).grow();

        tableWaveBeginning = new Table();
        tableWaveBeginning.add(labelWaveBeginningWaveTitle).padRight(Constants.GAP);
        tableWaveBeginning.add(labelWaveBeginningWave);
        Table tableShoot3 = new Table();
        tableShoot3.setTouchable(Touchable.enabled);
        tableShoot3.add(tableWaveBeginning);
        Table tableBottom2 = new Table();
        tableBottom2.add(btBackpack).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).padTop(Constants.GAP).padRight(Constants.GAP).padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        tableBottom2.add(tableShoot3).grow();
        tableBottom2.add(btReload).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM).padTop(Constants.GAP).padLeft(Constants.GAP).padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);

        Table tableShoot4 = new Table();
        tableShoot4.setTouchable(Touchable.enabled);
        tableShoot4.add(stack).expand().align(Align.bottom).width(Constants.IMAGE_BUTTON_SIZE_MEDIUM * 2).height(Constants.IMAGE_BUTTON_SIZE_MEDIUM);

        Table tableBottom1 = new Table();
        tableBottom1.add(btLeft).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(Constants.GAP).padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        tableBottom1.add(btRight).align(Align.bottomLeft).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP);
        tableBottom1.add(tableShoot4).grow().align(Align.bottom).padLeft(Constants.GAP).padRight(Constants.GAP);
        tableBottom1.add(btDown).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(Constants.GAP);
        tableBottom1.add(btUp).align(Align.bottomRight).width(Constants.IMAGE_BUTTON_SIZE_BIG).height(Constants.IMAGE_BUTTON_SIZE_BIG).padTop(Constants.GAP).padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);

        Table tableShoot2 = new Table();
        tableShoot2.setTouchable(Touchable.enabled);

        super.add(tableData).align(Align.topLeft).growX().padLeft(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0);
        super.add(tableTopRight).fillY().padRight(game.saveDataManager.saveData.isAspectRatio169() ? 200 : 0).align(Align.topRight);
        super.row();
        super.add(tableShoot2).colspan(2).grow();
        super.row();
        super.add(tableBottom2).colspan(2).growX();
        super.row();
        super.add(tableBottom1).colspan(2).growX();

        btPause.addListener(inputListenerPause);
        btBackpack.addListener(inputListenerEnterBackpack);

        tableShoot2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    /*if (btLeftPressed || btRightPressed || btUpPressed || btDownPressed) {
                        if (pointer >= buttonsDown) {
                            btShootPressed = true;
                        } else {
                            btShootPressed = false;
                        }
                    } else {
                        btShootPressed = true;
                    }*/
                btShootPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    /*if (pointer > buttonsDown) {
                        btShootPressed = true;
                    } else {
                        btShootPressed = false;
                    }*/
                btShootPressed = false;
            }
        });

        tableData.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = false;
            }
        });

        tableMoney.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = false;
            }
        });

        tableShoot1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = false;
            }
        });

        tableShoot3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = false;
            }
        });

        tableShoot4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!(btEnterShop.isPressed() || btEnterHome.isPressed())) {
                    btShootPressed = true;
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btShootPressed = false;
            }
        });

        btLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btLeftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btLeftPressed = false;
            }
        });

        btRight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btRightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btRightPressed = false;
            }
        });

        btUp.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btUpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btUpPressed = false;
            }
        });

        btDown.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btDownPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btDownPressed = false;
            }
        });

        btReload.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btReloadPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btReloadPressed = false;
            }
        });
    }

    @Override
    public void updateHud() {
        super.updateHud();
        if (game.gameWorld.enemyManager.inTheBeginningOfNewWave) {
            tableWaveBeginning.setVisible(true);
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

    public void setBtEnterGunStoreVisibility(boolean visible) {
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

    /*public void setScore(long score) {
        labelScore.setText(game.styles.getFormattedNumber(score));
    }

    public void setCombo(long combo) {
        labelWave.setText(game.styles.getFormattedNumber(combo));
    }

    public void setDistance(long distance) {
        labelDistance.setText(game.styles.getFormattedNumber(distance) + " m");
    }*/
}
