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

    private static boolean buttonRightPressed = false;
    private static boolean buttonLeftPressed = false;
    private static boolean buttonUpPressed = false;
    private static boolean buttonReloadPressed = false;
    private static boolean buttonShootPressed = false;
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

    public InGameHud(TheGame game) {
        super(game);
        initialize();
    }

    public static boolean isButtonRightPressed() {
        return buttonRightPressed;
    }

    public static boolean isButtonLeftPressed() {
        return buttonLeftPressed;
    }

    public static boolean isButtonUpPressed() {
        return buttonUpPressed;
    }

    public static boolean isButtonReloadPressed() {
        return buttonReloadPressed;
    }

    public static boolean isButtonShootPressed() {
        return buttonShootPressed;
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
    public void updateData() {
        super.updateData();
        buttonEnterShop.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonEnterBuilding"));
        buttonEnterHospital.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonEnterBuilding"));
        labelWaveBeginningWave.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getEnemyManager().getWave()));
        if (game.getGameWorld().getEnemyManager().isInTheBeginningOfNewWave()) {
            tableWaveBeginning.setVisible(true);
        } else {
            tableWaveBeginning.setVisible(false);
        }
        labelHealthTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("inGameTitleHealth"));
        labelScoreTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("inGameTitleScore"));
        labelScore.setText(game.getStyles().getFormattedInt(0));
        labelWaveTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("inGameTitleWave"));
        labelWave.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getEnemyManager().getWave()));
        progressBarHealth.setRange(0, game.getGameWorld().getPlayer().getMaxHealth());
        progressBarHealth.setValue(game.getGameWorld().getPlayer().getHealth());
        if (game.getGameWorld().getPlayer().getWeapon().isReloading()) {
            labelAmmoReloading.setText("(RELOADING...)");
        } else {
            labelAmmoReloading.setText("");
        }
        labelWeapon.setText(
                "(" + game.getAssetManager().get(Constants.BUNDLE, I18NBundle.class).get(
                        game.getGameWorld().getPlayer().getWeapon().getWeaponData().getNameID())
                        + ")");
        labelAmmoTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("inGameTitleAmmo"));
        labelAmmoInMag.setText(game.getStyles().getFormattedInt((
                game.getGameWorld().getPlayer().getWeapon()).getInMagazine()));
        labelAmmoRest.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getPlayer().getWeapon().getWeaponData().getNumberOfAmmo()
                        - (game.getGameWorld().getPlayer().getWeapon()).getInMagazine()));
        labelAmmoInMagLow.setText(game.getStyles().getFormattedInt(
                (game.getGameWorld().getPlayer().getWeapon()).getInMagazine()));
        labelAmmoRestLow.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getPlayer().getWeapon().getWeaponData().getNumberOfAmmo()
                        - (game.getGameWorld().getPlayer().getWeapon()).getInMagazine()));
        labelAmmoSeparator.setText(" + ");
        if ((game.getGameWorld().getPlayer().getWeapon()).getInMagazine()
                < 0.35f * (game.getGameWorld().getPlayer().getWeapon())
                .getWeaponData().getMagazineSize()) {
            labelAmmoInMag.setVisible(false);
            labelAmmoInMagLow.setVisible(true);
        } else {
            labelAmmoInMag.setVisible(true);
            labelAmmoInMagLow.setVisible(false);
        }
        if ((game.getGameWorld().getPlayer().getWeapon()).getWeaponData().getNumberOfAmmo()
                - (game.getGameWorld().getPlayer().getWeapon()).getInMagazine() - 1
                < 2f * (game.getGameWorld().getPlayer().getWeapon())
                .getWeaponData().getMagazineSize()) {
            labelAmmoRest.setVisible(false);
            labelAmmoRestLow.setVisible(true);
        } else {
            labelAmmoRest.setVisible(true);
            labelAmmoRestLow.setVisible(false);
        }
        labelMoney.setText("");
        //labelMoney.setText(game.getStyles().getFormattedFloatMoney(
        //        game.getGameWorld().getPlayer().getMoney()) + " \u20AC");
    }

    @Override
    protected void initialize() {
        super.initialize();

        super.top();
        super.left();
        super.setFillParent(true);

        labelHealthTitle = new Label("",
                game.getStyles().getLabelStyleWhiteSmallWithBorder());
        progressBarHealth = new ProgressBar(
                0, 500, 1, false,
                game.getStyles().getProgressBarStyleHealth());
        progressBarHealth.setVisualInterpolation(new Interpolation.Pow(1));
        labelAmmoTitle = new Label("",
                game.getStyles().getLabelStyleWhiteSmallWithBorder());
        labelAmmoInMag = new Label("0",
                game.getStyles().getLabelStyleWhiteBigWithBorder());
        labelAmmoRest = new Label("0",
                game.getStyles().getLabelStyleWhiteBigWithBorder());
        labelAmmoInMagLow = new Label("0",
                game.getStyles().getLabelStyleRedBigWithBorder());
        labelAmmoRestLow = new Label("0",
                game.getStyles().getLabelStyleRedBigWithBorder());
        labelAmmoSeparator = new Label("",
                game.getStyles().getLabelStyleWhiteBigWithBorder());
        labelAmmoReloading = new Label("",
                game.getStyles().getLabelStyleRedSmallWithBorder());
        labelWeapon = new Label("",
                game.getStyles().getLabelStyleWhiteSmallWithBorder());
        labelScoreTitle = new Label("",
                game.getStyles().getLabelStyleWhiteSmallWithBorder());
        labelScore = new Label("0",
                game.getStyles().getLabelStyleWhiteBigWithBorder());
        labelWaveTitle = new Label("",
                game.getStyles().getLabelStyleWhiteSmallWithBorder());
        labelWave = new Label("1",
                game.getStyles().getLabelStyleWhiteBigWithBorder());
        labelMoney = new Label("0",
                game.getStyles().getLabelStyleWhiteBigWithBorder());

        labelWaveBeginningWaveTitle = new Label("WAVE",
                game.getStyles().getLabelStyleWhiteBigWithBorder());
        labelWaveBeginningWave = new Label("",
                game.getStyles().getLabelStyleWhiteHugeWithBorder());

        Table tableHealth = new Table();
        tableHealth.add(progressBarHealth).align(Align.left).expandX()
                .width(Constants.PROGRESS_BAR_WIDTH_HEALTH)
                .height(labelAmmoInMag.getHeight());

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

        ImageButton btPause = new ImageButton(
                game.getStyles().getImageButtonStylePause());
        ImageButton buttonRight = new ImageButton(
                game.getStyles().getImageButtonStyleRight());
        ImageButton buttonLeft = new ImageButton(
                game.getStyles().getImageButtonStyleLeft());
        ImageButton buttonUp = new ImageButton(
                game.getStyles().getImageButtonStyleUp());
        ImageButton buttonShoot = new ImageButton(
                game.getStyles().getImageButtonStyleDown());
        ImageButton btBackpack = new ImageButton(
                game.getStyles().getImageButtonStyleBackpack());
        ImageButton buttonReload = new ImageButton(
                game.getStyles().getImageButtonStyleReload());
        buttonEnterShop = new TextButton("",
                game.getStyles().getTextButtonStyleEnterBuilding());
        buttonEnterHospital = new TextButton("",
                game.getStyles().getTextButtonStyleEnterBuilding());
        Stack stackEnterBuilding = new Stack();
        stackEnterBuilding.add(buttonEnterShop);
        stackEnterBuilding.add(buttonEnterHospital);
        buttonEnterShop.setVisible(false);
        buttonEnterHospital.setVisible(false);

        Table tableTopRight = new Table();
        Table tableMoney = new Table();
        tableMoney.add(labelMoney)
                .padTop((Constants.IMAGE_BUTTON_SIZE_SMALL - labelMoney.getHeight()) / 2f)
                .align(Align.topRight).expandY();
        tableTopRight.add(tableMoney).padRight(Constants.GAP * 2).align(Align.topRight).growY()
                .height(Constants.IMAGE_BUTTON_SIZE_MEDIUM);
        tableTopRight.add(btPause).align(Align.topRight).padBottom(Constants.GAP)
                .width(Constants.IMAGE_BUTTON_SIZE_MEDIUM)
                .height(Constants.IMAGE_BUTTON_SIZE_MEDIUM);

        tableWaveBeginning = new Table();
        tableWaveBeginning.add(labelWaveBeginningWaveTitle).padRight(Constants.GAP);
        tableWaveBeginning.add(labelWaveBeginningWave);

        Table tableBottom1Middle = new Table();
        tableBottom1Middle.add(tableWaveBeginning);

        Table tableBottom1 = new Table();
        tableBottom1.add(btBackpack)
                .align(Align.bottomLeft)
                .width(Constants.IMAGE_BUTTON_SIZE_MEDIUM)
                .height(Constants.IMAGE_BUTTON_SIZE_MEDIUM)
                .padTop(Constants.GAP)
                .padRight(Constants.GAP)
                .padLeft(Constants.USE_169_ASPECT_RATION ? 200 : 0);
        tableBottom1.add(tableBottom1Middle).grow();
        tableBottom1.add(buttonReload)
                .align(Align.bottomRight)
                .width(Constants.IMAGE_BUTTON_SIZE_MEDIUM)
                .height(Constants.IMAGE_BUTTON_SIZE_MEDIUM)
                .padTop(Constants.GAP)
                .padLeft(Constants.GAP)
                .padRight(Constants.USE_169_ASPECT_RATION ? 200 : 0);

        Table tableBottom2Middle = new Table();
        tableBottom2Middle.add(stackEnterBuilding).expand().align(Align.bottom)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);

        Table tableBottom2 = new Table();
        tableBottom2.add(buttonLeft)
                .align(Align.bottomLeft)
                .width(Constants.IMAGE_BUTTON_SIZE_BIG)
                .height(Constants.IMAGE_BUTTON_SIZE_BIG)
                .padTop(Constants.GAP)
                .padRight(Constants.GAP)
                .padLeft(Constants.USE_169_ASPECT_RATION ? 200 : 0);
        tableBottom2.add(buttonRight)
                .align(Align.bottomLeft)
                .width(Constants.IMAGE_BUTTON_SIZE_BIG)
                .height(Constants.IMAGE_BUTTON_SIZE_BIG)
                .padTop(Constants.GAP);
        tableBottom2.add(tableBottom2Middle)
                .grow().align(Align.bottom)
                .padLeft(Constants.GAP)
                .padRight(Constants.GAP);
        tableBottom2.add(buttonShoot)
                .align(Align.bottomRight)
                .width(Constants.IMAGE_BUTTON_SIZE_BIG)
                .height(Constants.IMAGE_BUTTON_SIZE_BIG)
                .padTop(Constants.GAP)
                .padRight(Constants.GAP);
        tableBottom2.add(buttonUp)
                .align(Align.bottomRight)
                .width(Constants.IMAGE_BUTTON_SIZE_BIG)
                .height(Constants.IMAGE_BUTTON_SIZE_BIG)
                .padTop(Constants.GAP)
                .padRight(Constants.USE_169_ASPECT_RATION ? 200 : 0);

        super.pad(Constants.GAP);
        super.add(tableData).align(Align.topLeft).expand()
                .padLeft(Constants.USE_169_ASPECT_RATION ? 200 : 0);
        super.add(tableTopRight).padRight(Constants.USE_169_ASPECT_RATION ? 200 : 0)
                .align(Align.topRight);
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
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM
                        && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {
                    game.getGameScreen().changeGameState(GameState.PAUSED);
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
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_MEDIUM
                        && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_MEDIUM) {
                    game.getGameScreen().changeGameState(GameState.IN_BACKPACK);
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
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    game.getGameScreen().changeGameState(GameState.IN_SHOP);
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
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH
                        && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                }
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