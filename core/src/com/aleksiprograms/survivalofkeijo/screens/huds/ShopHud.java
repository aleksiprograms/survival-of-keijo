package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

public class ShopHud extends AbstractHud {

    private int[] WEAPONS_IDS = {
            Constants.PISTOL_ID,
            Constants.ASSAULT_RIFLE_ID,
            Constants.SHOTGUN_ID,
            Constants.SNIPER_ID,
            Constants.MACHINE_GUN_ID,
            Constants.ROCKET_LAUNCHER_ID};

    private Label labelScreenTitle;

    private Array<WeaponButton> weaponButtons;
    private int checkedWeaponID;
    private ScrollPane scrollPaneWeapons;
    private Label labelMoney;
    private Label uselessLabel;

    private Table tableWeapon;
    private Table tableWeaponInfoBox;
    private Table tableWeaponTopNotBought;
    private Table tableWeaponTopBought;
    private Table tableWeaponBottomNotBought;
    private Table tableWeaponBottomBought;
    private Table tableWeaponRows;
    private ScrollPane scrollPaneWeapon;

    private int weaponNumberOfRows;
    private Table tableRateOfFire;
    private Table tableAmmoDamage;
    private Table tableAmmoSpeed;
    private Table tableMagazineSize;
    private Table tableReloadTime;

    private Label labelBasicInfoFireType;
    private Label labelBasicInfoAmmoPrice;
    private Label labelBasicInfoPointsHit;
    private Label labelBasicInfoPointsKill;
    private Label labelAmmoPriceUnit;
    private Label labelPointsTitleHit;
    private Label labelPointsTitleKill;
    private Label labelFireTypeTitle;
    private Label labelAmmoPriceTitle;
    private Label labelPointsTitle;

    private Label labelWeaponNameNotBought;
    private Label labelWeaponNameBought;
    private Label labelWeaponPrice;
    private Label labelNumberOfAmmoTitle;
    private Label labelNumberOfAmmo;
    private TextButton buttonBuyWeapon;
    private TextButton buttonGetAmmo;
    private TextButton buttonUpgrade;
    private Label labelRateOfFireTitle;
    private Label labelRateOfFireValue;
    private Label labelRateOfFireUnit;
    private Label labelAmmoDamageTitle;
    private Label labelAmmoDamageValue;
    private Label labelAmmoDamageUnit;
    private Label labelAmmoSpeedTitle;
    private Label labelAmmoSpeedValue;
    private Label labelAmmoSpeedUnit;
    private Label labelMagazineSizeTitle;
    private Label labelMagazineSizeValue;
    private Label labelMagazineSizeUnit;
    private Label labelReloadTimeTitle;
    private Label labelReloadTimeValue;
    private Label labelReloadTimeUnit;
    private ProgressBar progressBarRateOfFire;
    private ProgressBar progressBarAmmoDamage;
    private ProgressBar progressBarAmmoSpeed;
    private ProgressBar progressBarMagazineSize;
    private ProgressBar progressBarReloadTime;

    private Table tableWeaponUpgrade;
    private Table tableWeaponRowsUpgrade;
    private ScrollPane scrollPaneWeaponUpgrade;

    private int weaponNumberOfRowsUpgrade;
    private Table tableRateOfFireUpgrade;
    private Table tableAmmoDamageUpgrade;
    private Table tableAmmoSpeedUpgrade;
    private Table tableMagazineSizeUpgrade;
    private Table tableReloadTimeUpgrade;

    private Label labelWeaponNameUpgrade;
    private Label labelYourMoneyTitleUpgrade;
    private Label labelYourMoneyUpgrade;
    private Label labelPriceUpgrade;
    private TextButton buttonBuyUpgrade;
    private Label labelRateOfFireTitleUpgrade;
    private Label labelRateOfFireValueUpgrade;
    private Label labelRateOfFireUnitUpgrade;
    private Label labelRateOfFireUpgradeUpgrade;
    private Label labelRateOfFireUpgradeUnitUpgrade;
    private Label labelRateOfFireCompletedUpgrade;
    private Label labelAmmoDamageTitleUpgrade;
    private Label labelAmmoDamageValueUpgrade;
    private Label labelAmmoDamageUnitUpgrade;
    private Label labelAmmoDamageUpgradeUpgrade;
    private Label labelAmmoDamageUpgradeUnitUpgrade;
    private Label labelAmmoDamageCompletedUpgrade;
    private Label labelAmmoSpeedTitleUpgrade;
    private Label labelAmmoSpeedValueUpgrade;
    private Label labelAmmoSpeedUnitUpgrade;
    private Label labelAmmoSpeedUpgradeUpgrade;
    private Label labelAmmoSpeedUpgradeUnitUpgrade;
    private Label labelAmmoSpeedCompletedUpgrade;
    private Label labelMagazineSizeTitleUpgrade;
    private Label labelMagazineSizeValueUpgrade;
    private Label labelMagazineSizeUnitUpgrade;
    private Label labelMagazineSizeUpgradeUpgrade;
    private Label labelMagazineSizeUpgradeUnitUpgrade;
    private Label labelMagazineSizeCompletedUpgrade;
    private Label labelReloadTimeTitleUpgrade;
    private Label labelReloadTimeValueUpgrade;
    private Label labelReloadTimeUnitUpgrade;
    private Label labelReloadTimeUpgradeUpgrade;
    private Label labelReloadTimeUpgradeUnitUpgrade;
    private Label labelReloadTimeCompletedUpgrade;
    private ProgressBar progressBarRateOfFireValueBought;
    private ProgressBar progressBarRateOfFireUpgradeBought;
    private ProgressBar progressBarAmmoDamageValueBought;
    private ProgressBar progressBarAmmoDamageUpgradeBought;
    private ProgressBar progressBarAmmoSpeedValueBought;
    private ProgressBar progressBarAmmoSpeedUpgradeBought;
    private ProgressBar progressBarMagazineSizeValueBought;
    private ProgressBar progressBarMagazineSizeUpgradeBought;
    private ProgressBar progressBarReloadTimeValueBought;
    private ProgressBar progressBarReloadTimeUpgradeBought;
    private ProgressBar progressBarRateOfFireUpgradeDone;
    private ProgressBar progressBarAmmoDamageUpgradeDone;
    private ProgressBar progressBarAmmoSpeedUpgradeDone;
    private ProgressBar progressBarMagazineSizeUpgradeDone;
    private ProgressBar progressBarReloadTimeUpgradeDone;

    private Table tableBuyAmmo;
    private Label labelWeaponNameBuyAmmo;
    private Label labelYourAmmoTitleBuyAmmo;
    private Label labelYourAmmoBuyAmmo;
    private Label labelNumberOfMagsOrAmmoBuyAmmo;
    private Label labelGuideBuyAmmo;
    private Label labelMagsBuyAmmo;
    private Label labelAmmoBuyAmmo;
    private Label labelNumberOfMagsOrAmmoTitleBuyAmmo;
    private Label labelYourMoneyTitleBuyAmmo;
    private Label labelYourMoneyBuyAmmo;
    private Label labelPriceBuyAmmo;
    private Slider sliderNumberOfMagsBuyAmmo;
    private Slider sliderNumberOfAmmoBuyAmmo;
    private TextButton buttonBuyAmmo;

    private float scrollAmountPistol = 0;
    private float scrollAmountAssaultRifle = 0;
    private float scrollAmountShotgun = 0;
    private float scrollAmountSniper = 0;
    private float scrollAmountMachineGun = 0;
    private float scrollAmountRocketLauncher = 0;

    private float scrollAmountPistolUpgrade = 0;
    private float scrollAmountAssaultRifleUpgrade = 0;
    private float scrollAmountShotgunUpgrade = 0;
    private float scrollAmountSniperUpgrade = 0;
    private float scrollAmountMachineGunUpgrade = 0;
    private float scrollAmountRocketLauncherUpgrade = 0;

    public ShopHud(final TheGame game) {
        super(game);
        initializeHud();
    }

    @Override
    public void resetHud() {
        super.resetHud();

        scrollPaneWeapons.setVelocityY(0);
        scrollPaneWeapons.setScrollY(0);
        scrollPaneWeapons.updateVisualScroll();

        scrollAmountPistol = 0;
        scrollAmountAssaultRifle = 0;
        scrollAmountShotgun = 0;
        scrollAmountSniper = 0;
        scrollAmountMachineGun = 0;
        scrollAmountRocketLauncher = 0;

        scrollAmountPistolUpgrade = 0;
        scrollAmountAssaultRifleUpgrade = 0;
        scrollAmountShotgunUpgrade = 0;
        scrollAmountSniperUpgrade = 0;
        scrollAmountMachineGunUpgrade = 0;
        scrollAmountRocketLauncherUpgrade = 0;
    }

    @Override
    public void updateHud() {
        super.updateHud();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleShop"));
        tableWeapon.clearChildren();
        tableWeaponRows.clearChildren();
        updateWeaponsTable();
        updateWeaponInfoBox();
        updateWeaponData();
        recreateTableWeapon();
        if (!(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).bought)) {
            tableWeapon.add(tableWeaponTopNotBought).align(Align.left).growX();
            tableWeapon.row();
            tableWeapon.add(scrollPaneWeapon).align(Align.left).grow().padRight(Constants.GAP).padLeft(Constants.GAP);
            tableWeapon.row();
            tableWeapon.add(tableWeaponBottomNotBought).align(Align.left).growX();
        } else {
            tableWeapon.add(tableWeaponTopBought).align(Align.left).growX();
            tableWeapon.row();
            tableWeapon.add(scrollPaneWeapon).align(Align.left).grow().padRight(Constants.GAP).padLeft(Constants.GAP);
            tableWeapon.row();
            tableWeapon.add(tableWeaponBottomBought).align(Align.left).growX();
        }
        labelMoney.setText(game.styles.getFormattedFloatMoney(game.gameWorld.player.money) + " \u20AC");
    }

    private void updateWeaponsTable() {
        for (int i = 0; i < Constants.NUMBER_OF_WEAPONS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_WEAPONS; j++) {
                if (weaponButtons.get(j).ID == WEAPONS_IDS[i]) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(WEAPONS_IDS[i]).bought) {
                        weaponButtons.get(j).labelWeaponLevel.setText("lvl " + game.gameWorld.weaponManagerPlayer.getWeaponData(weaponButtons.get(j).ID).level);
                    } else {
                        weaponButtons.get(j).labelWeaponLevel.setText("");
                    }
                }
            }
        }
    }

    private void updateUpgradeTable() {
        tableWeaponRowsUpgrade.clearChildren();
        recreateTableWeaponUpgrade();
        updateWeaponDataUpgrade();
    }

    private void updateAmmoTable() {
        labelWeaponNameBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).nameID));
        labelYourAmmoTitleBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelYourAmmoBuyAmmo.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo));
        labelYourMoneyTitleBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelYourMoneyTitle"));
        labelYourMoneyBuyAmmo.setText(game.styles.getFormattedFloatMoney(game.gameWorld.player.money) + " \u20AC");
        buttonBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonBuy"));
        labelMagsBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMags"));
        labelAmmoBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmo"));
        labelGuideBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelBuyAmmoGuide"));
        if (sliderNumberOfMagsBuyAmmo.isVisible()) {
            labelNumberOfMagsOrAmmoTitleBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelNumberOfMagsToBuyTitle"));
            labelNumberOfMagsOrAmmoBuyAmmo.setText((int)(sliderNumberOfMagsBuyAmmo.getValue()) + "");
            labelPriceBuyAmmo.setText(game.styles.getFormattedFloatMoney((int)(sliderNumberOfMagsBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice) + " \u20AC");
            if (game.gameWorld.player.money >= ((int)(sliderNumberOfMagsBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice)) {
                buttonBuyAmmo.setDisabled(false);
            } else {
                buttonBuyAmmo.setDisabled(true);
            }
        } else if (sliderNumberOfAmmoBuyAmmo.isVisible()) {
            labelNumberOfMagsOrAmmoTitleBuyAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoToBuyTitle"));
            labelNumberOfMagsOrAmmoBuyAmmo.setText((int)(sliderNumberOfAmmoBuyAmmo.getValue()) + "");
            labelPriceBuyAmmo.setText(game.styles.getFormattedFloatMoney((int)(sliderNumberOfAmmoBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice) + " \u20AC");
            if (game.gameWorld.player.money >= ((int)(sliderNumberOfAmmoBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice)) {
                buttonBuyAmmo.setDisabled(false);
            } else {
                buttonBuyAmmo.setDisabled(true);
            }
        }
    }

    private void recreateTableWeapon() {
        weaponNumberOfRows = 0;
        tableWeaponRows.add(tableWeaponInfoBox).padBottom(Constants.GAP).padRight(Constants.GAP).height(3 * uselessLabel.getHeight() + 2 * Constants.GAP);
        tableWeaponRows.row();
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableRateOfFire).padBottom(weaponNumberOfRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableAmmoDamage).padBottom(weaponNumberOfRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableAmmoSpeed).padBottom(weaponNumberOfRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableMagazineSize).padBottom(weaponNumberOfRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableReloadTime).padBottom(weaponNumberOfRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }

        scrollPaneWeapon.setVelocityY(0);
        scrollPaneWeapon.removeActor(tableWeaponRows);
        scrollPaneWeapon.setActor(tableWeaponRows);
        scrollPaneWeapon.layout();
        scrollPaneWeapon.setScrollY(loadScrollAmount());
        scrollPaneWeapon.updateVisualScroll();
    }

    private void recreateTableWeaponUpgrade() {
        weaponNumberOfRowsUpgrade = 0;
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableRateOfFireUpgrade).padBottom(weaponNumberOfRowsUpgrade < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableAmmoDamageUpgrade).padBottom(weaponNumberOfRowsUpgrade < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableAmmoSpeedUpgrade).padBottom(weaponNumberOfRowsUpgrade < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableMagazineSizeUpgrade).padBottom(weaponNumberOfRowsUpgrade < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableReloadTimeUpgrade).padBottom(weaponNumberOfRowsUpgrade < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }

        scrollPaneWeaponUpgrade.setVelocityY(0);
        scrollPaneWeaponUpgrade.removeActor(tableWeaponRowsUpgrade);
        scrollPaneWeaponUpgrade.setActor(tableWeaponRowsUpgrade);
        scrollPaneWeaponUpgrade.layout();
        scrollPaneWeaponUpgrade.setScrollY(loadScrollAmountUpgrade());
        scrollPaneWeaponUpgrade.updateVisualScroll();
    }

    private void updateWeaponInfoBox() {
        labelFireTypeTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelFireTypeTitle"));
        labelPointsTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelPointsTitle"));
        labelPointsTitleHit.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelPointsTitleHit") + " ");
        labelPointsTitleKill.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelPointsTitleKill") + " ");
        labelAmmoPriceTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoPriceTitle"));
        labelBasicInfoFireType.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).fireType);
        labelBasicInfoPointsHit.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).pointsHit));
        labelBasicInfoPointsKill.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).pointsKill));
        labelBasicInfoAmmoPrice.setText(game.styles.getFormattedFloatMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice));
        labelAmmoPriceUnit.setText(" \u20AC");
    }

    private void updateWeaponData() {
        labelWeaponNameNotBought.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).nameID));
        labelWeaponNameBought.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).nameID));
        labelWeaponPrice.setText(game.styles.getFormattedFloatMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).price) + " \u20AC");
        labelNumberOfAmmoTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelNumberOfAmmo.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo));
        buttonBuyWeapon.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonBuy"));
        buttonGetAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonGetAmmo"));
        buttonUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonUpgrade"));
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).MAX_LEVEL) {
            buttonUpgrade.setDisabled(false);
        } else {
            buttonUpgrade.setDisabled(true);
        }

        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).price) {
            buttonBuyWeapon.setDisabled(false);
        } else {
            buttonBuyWeapon.setDisabled(true);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
            labelRateOfFireTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFire"));
            labelRateOfFireValue.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire));
            labelRateOfFireUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
            progressBarRateOfFire.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
            labelAmmoDamageTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamage"));
            labelAmmoDamageValue.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage));
            labelAmmoDamageUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
            progressBarAmmoDamage.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
            labelAmmoSpeedTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeed"));
            labelAmmoSpeedValue.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed));
            labelAmmoSpeedUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
            progressBarAmmoSpeed.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
            labelMagazineSizeTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSize"));
            labelMagazineSizeValue.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize));
            labelMagazineSizeUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
            progressBarMagazineSize.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
            labelReloadTimeTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTime"));
            labelReloadTimeValue.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime));
            labelReloadTimeUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
            progressBarReloadTime.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime);
        }
    }

    private void updateWeaponDataUpgrade() {
        labelWeaponNameUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).nameID));
        labelYourMoneyTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelYourMoneyTitle"));
        labelYourMoneyUpgrade.setText(game.styles.getFormattedFloatMoney(game.gameWorld.player.money) + " \u20AC");
        labelNumberOfAmmoTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelNumberOfAmmo.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo));
        buttonBuyUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonBuy"));
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).MAX_LEVEL) {
            buttonBuyUpgrade.setDisabled(false);
            labelPriceUpgrade.setText(game.styles.getFormattedFloatMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).upgradePrices.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level)) + " \u20AC");
        } else {
            buttonBuyUpgrade.setDisabled(true);
            labelPriceUpgrade.setText("");
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
            labelRateOfFireTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFire"));
            labelRateOfFireValueUpgrade.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire));
            labelRateOfFireUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).level >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).MAX_LEVEL) {
                labelRateOfFireUpgradeUpgrade.setText("");
                labelRateOfFireUpgradeUnitUpgrade.setText("");
                labelRateOfFireCompletedUpgrade.setText("COMPLETED123");
                progressBarRateOfFireValueBought.setVisible(false);
                progressBarRateOfFireUpgradeBought.setVisible(false);
                progressBarRateOfFireUpgradeDone.setVisible(true);
                progressBarRateOfFireUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire);
            } else {
                labelRateOfFireUpgradeUpgrade.setText(game.styles.getFormattedFloatWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level)));
                labelRateOfFireUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
                labelRateOfFireCompletedUpgrade.setText("");
                progressBarRateOfFireValueBought.setVisible(true);
                progressBarRateOfFireUpgradeBought.setVisible(true);
                progressBarRateOfFireUpgradeDone.setVisible(false);
                progressBarRateOfFireValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire);
                progressBarRateOfFireUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level));
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
            labelAmmoDamageTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamage"));
            labelAmmoDamageValueUpgrade.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage));
            labelAmmoDamageUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).level >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).MAX_LEVEL) {
                labelAmmoDamageUpgradeUpgrade.setText("");
                labelAmmoDamageUpgradeUnitUpgrade.setText("");
                labelAmmoDamageCompletedUpgrade.setText("COMPLETED123");
                progressBarAmmoDamageValueBought.setVisible(false);
                progressBarAmmoDamageUpgradeBought.setVisible(false);
                progressBarAmmoDamageUpgradeDone.setVisible(true);
                progressBarAmmoDamageUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage);
            } else {
                labelAmmoDamageUpgradeUpgrade.setText(game.styles.getFormattedIntWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level)));
                labelAmmoDamageUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
                labelAmmoDamageCompletedUpgrade.setText("");
                progressBarAmmoDamageValueBought.setVisible(true);
                progressBarAmmoDamageUpgradeBought.setVisible(true);
                progressBarAmmoDamageUpgradeDone.setVisible(false);
                progressBarAmmoDamageValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage);
                progressBarAmmoDamageUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level));
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
            labelAmmoSpeedTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeed"));
            labelAmmoSpeedValueUpgrade.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed));
            labelAmmoSpeedUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).level >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).MAX_LEVEL) {
                labelAmmoSpeedUpgradeUpgrade.setText("");
                labelAmmoSpeedUpgradeUnitUpgrade.setText("");
                labelAmmoSpeedCompletedUpgrade.setText("COMPLETED123");
                progressBarAmmoSpeedValueBought.setVisible(false);
                progressBarAmmoSpeedUpgradeBought.setVisible(false);
                progressBarAmmoSpeedUpgradeDone.setVisible(true);
                progressBarAmmoSpeedUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed);
            } else {
                labelAmmoSpeedUpgradeUpgrade.setText(game.styles.getFormattedFloatWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level)));
                labelAmmoSpeedUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
                labelAmmoSpeedCompletedUpgrade.setText("");
                progressBarAmmoSpeedValueBought.setVisible(true);
                progressBarAmmoSpeedUpgradeBought.setVisible(true);
                progressBarAmmoSpeedUpgradeDone.setVisible(false);
                progressBarAmmoSpeedValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed);
                progressBarAmmoSpeedUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level));
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
            labelMagazineSizeTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSize"));
            labelMagazineSizeValueUpgrade.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize));
            labelMagazineSizeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).level >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).MAX_LEVEL) {
                labelMagazineSizeUpgradeUpgrade.setText("");
                labelMagazineSizeUpgradeUnitUpgrade.setText("");
                labelMagazineSizeCompletedUpgrade.setText("COMPLETED123");
                progressBarMagazineSizeValueBought.setVisible(false);
                progressBarMagazineSizeUpgradeBought.setVisible(false);
                progressBarMagazineSizeUpgradeDone.setVisible(true);
                progressBarMagazineSizeUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize);
            } else {
                labelMagazineSizeUpgradeUpgrade.setText(game.styles.getFormattedIntWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level)));
                labelMagazineSizeUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
                labelMagazineSizeCompletedUpgrade.setText("");
                progressBarMagazineSizeValueBought.setVisible(true);
                progressBarMagazineSizeUpgradeBought.setVisible(true);
                progressBarMagazineSizeUpgradeDone.setVisible(false);
                progressBarMagazineSizeValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize);
                progressBarMagazineSizeUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level));
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
            labelReloadTimeTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTime"));
            labelReloadTimeValueUpgrade.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime));
            labelReloadTimeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).level >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).MAX_LEVEL) {
                labelReloadTimeUpgradeUpgrade.setText("");
                labelReloadTimeUpgradeUnitUpgrade.setText("");
                labelReloadTimeCompletedUpgrade.setText("COMPLETED123");
                progressBarReloadTimeValueBought.setVisible(false);
                progressBarReloadTimeUpgradeBought.setVisible(false);
                progressBarReloadTimeUpgradeDone.setVisible(true);
                progressBarReloadTimeUpgradeDone.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime);
            } else {
                labelReloadTimeUpgradeUpgrade.setText(game.styles.getFormattedFloatWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level)));
                labelReloadTimeUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
                labelReloadTimeCompletedUpgrade.setText("");
                progressBarReloadTimeValueBought.setVisible(true);
                progressBarReloadTimeUpgradeBought.setVisible(true);
                progressBarReloadTimeUpgradeDone.setVisible(false);
                progressBarReloadTimeValueBought.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime);
                progressBarReloadTimeUpgradeBought.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime - game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level));
            }
        }
    }

    @Override
    protected void initializeHud() {
        super.initializeHud();

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        uselessLabel = new Label("0", game.styles.labelStyleBlueSmall);

        labelBasicInfoFireType = new Label("", game.styles.labelStyleBlueSmall);
        labelBasicInfoAmmoPrice = new Label("", game.styles.labelStyleBlueSmall);
        labelBasicInfoPointsHit = new Label("", game.styles.labelStyleBlueSmall);
        labelBasicInfoPointsKill = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoPriceUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelPointsTitleHit = new Label("", game.styles.labelStyleWhiteSmall);
        labelPointsTitleKill = new Label("", game.styles.labelStyleWhiteSmall);
        labelFireTypeTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoPriceTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelPointsTitle = new Label("", game.styles.labelStyleWhiteTiny);

        labelWeaponNameNotBought = new Label("", game.styles.labelStyleWhiteMedium);
        labelWeaponNameBought = new Label("", game.styles.labelStyleWhiteMedium);
        labelWeaponPrice = new Label("", game.styles.labelStyleWhiteMedium);
        labelNumberOfAmmoTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelNumberOfAmmo = new Label("", game.styles.labelStyleWhiteMedium);

        labelRateOfFireTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelRateOfFireValue = new Label("", game.styles.labelStyleBlueSmall);
        labelRateOfFireUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoDamageValue = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoDamageUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoSpeedValue = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoSpeedUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelMagazineSizeValue = new Label("", game.styles.labelStyleBlueSmall);
        labelMagazineSizeUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelReloadTimeValue = new Label("", game.styles.labelStyleBlueSmall);
        labelReloadTimeUnit = new Label("", game.styles.labelStyleWhiteSmall);
        progressBarRateOfFire = new ProgressBar(game.gameWorld.weaponManagerPlayer.minRateOfFire, game.gameWorld.weaponManagerPlayer.maxRateOfFire, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarAmmoDamage = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoDamage, game.gameWorld.weaponManagerPlayer.maxAmmoDamage, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarAmmoSpeed = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoSpeed, game.gameWorld.weaponManagerPlayer.maxAmmoSpeed, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarMagazineSize = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMagazineSize, game.gameWorld.weaponManagerPlayer.maxMagazineSize, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarReloadTime = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxReloadTime, -game.gameWorld.weaponManagerPlayer.minReloadTime, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        labelRateOfFireValue.setAlignment(Align.topRight);
        labelAmmoDamageValue.setAlignment(Align.topRight);
        labelAmmoSpeedValue.setAlignment(Align.topRight);
        labelMagazineSizeValue.setAlignment(Align.topRight);
        labelReloadTimeValue.setAlignment(Align.topRight);

        labelWeaponNameUpgrade = new Label("", game.styles.labelStyleWhiteMedium);
        labelYourMoneyTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelYourMoneyUpgrade = new Label("", game.styles.labelStyleWhiteMedium);
        labelPriceUpgrade = new Label("", game.styles.labelStyleWhiteMedium);
        labelRateOfFireTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelRateOfFireValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelRateOfFireUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelRateOfFireUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelRateOfFireUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelRateOfFireCompletedUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoDamageValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoDamageUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelAmmoDamageUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageCompletedUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoSpeedValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoSpeedUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelAmmoSpeedUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedCompletedUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelMagazineSizeValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelMagazineSizeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelMagazineSizeUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeCompletedUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelReloadTimeValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelReloadTimeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelReloadTimeUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeCompletedUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        progressBarRateOfFireValueBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minRateOfFire, game.gameWorld.weaponManagerPlayer.maxRateOfFire, 0.01f, false, game.styles.progressBarStyleValueBought);
        progressBarRateOfFireUpgradeBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minRateOfFire, game.gameWorld.weaponManagerPlayer.maxRateOfFire, 0.01f, false, game.styles.progressBarStyleUpgrade);
        progressBarAmmoDamageValueBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoDamage, game.gameWorld.weaponManagerPlayer.maxAmmoDamage, 0.01f, false, game.styles.progressBarStyleValueBought);
        progressBarAmmoDamageUpgradeBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoDamage, game.gameWorld.weaponManagerPlayer.maxAmmoDamage, 0.01f, false, game.styles.progressBarStyleUpgrade);
        progressBarAmmoSpeedValueBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoSpeed, game.gameWorld.weaponManagerPlayer.maxAmmoSpeed, 0.01f, false, game.styles.progressBarStyleValueBought);
        progressBarAmmoSpeedUpgradeBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoSpeed, game.gameWorld.weaponManagerPlayer.maxAmmoSpeed, 0.01f, false, game.styles.progressBarStyleUpgrade);
        progressBarMagazineSizeValueBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMagazineSize, game.gameWorld.weaponManagerPlayer.maxMagazineSize, 0.01f, false, game.styles.progressBarStyleValueBought);
        progressBarMagazineSizeUpgradeBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMagazineSize, game.gameWorld.weaponManagerPlayer.maxMagazineSize, 0.01f, false, game.styles.progressBarStyleUpgrade);
        progressBarReloadTimeValueBought = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxReloadTime, -game.gameWorld.weaponManagerPlayer.minReloadTime, 0.01f, false, game.styles.progressBarStyleValueBought);
        progressBarReloadTimeUpgradeBought = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxReloadTime, -game.gameWorld.weaponManagerPlayer.minReloadTime, 0.01f, false, game.styles.progressBarStyleUpgrade);
        labelRateOfFireValueUpgrade.setAlignment(Align.topRight);
        labelRateOfFireUpgradeUpgrade.setAlignment(Align.topRight);
        labelAmmoDamageValueUpgrade.setAlignment(Align.topRight);
        labelAmmoDamageUpgradeUpgrade.setAlignment(Align.topRight);
        labelAmmoSpeedValueUpgrade.setAlignment(Align.topRight);
        labelAmmoSpeedUpgradeUpgrade.setAlignment(Align.topRight);
        labelMagazineSizeValueUpgrade.setAlignment(Align.topRight);
        labelMagazineSizeUpgradeUpgrade.setAlignment(Align.topRight);
        labelReloadTimeValueUpgrade.setAlignment(Align.topRight);
        labelReloadTimeUpgradeUpgrade.setAlignment(Align.topRight);

        labelWeaponNameBuyAmmo = new Label("", game.styles.labelStyleWhiteMedium);
        labelYourAmmoTitleBuyAmmo = new Label("", game.styles.labelStyleWhiteTiny);
        labelYourAmmoBuyAmmo = new Label("", game.styles.labelStyleWhiteMedium);
        labelNumberOfMagsOrAmmoBuyAmmo = new Label("1", game.styles.labelStyleWhiteMedium);
        labelGuideBuyAmmo = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagsBuyAmmo = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoBuyAmmo = new Label("", game.styles.labelStyleWhiteTiny);
        labelNumberOfMagsOrAmmoTitleBuyAmmo = new Label("", game.styles.labelStyleWhiteTiny);
        labelYourMoneyTitleBuyAmmo = new Label("", game.styles.labelStyleWhiteTiny);
        labelYourMoneyBuyAmmo = new Label("", game.styles.labelStyleWhiteMedium);
        labelPriceBuyAmmo = new Label("", game.styles.labelStyleWhiteMedium);

        progressBarRateOfFireUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minRateOfFire, game.gameWorld.weaponManagerPlayer.maxRateOfFire, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarAmmoDamageUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoDamage, game.gameWorld.weaponManagerPlayer.maxAmmoDamage, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarAmmoSpeedUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoSpeed, game.gameWorld.weaponManagerPlayer.maxAmmoSpeed, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarMagazineSizeUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMagazineSize, game.gameWorld.weaponManagerPlayer.maxMagazineSize, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarReloadTimeUpgradeDone = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxReloadTime, -game.gameWorld.weaponManagerPlayer.minReloadTime, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarRateOfFireUpgradeDone.setVisible(false);
        progressBarAmmoDamageUpgradeDone.setVisible(false);
        progressBarAmmoSpeedUpgradeDone.setVisible(false);
        progressBarMagazineSizeUpgradeDone.setVisible(false);
        progressBarReloadTimeUpgradeDone.setVisible(false);

        tableWeaponInfoBox = createBasicInfoBox();

        tableRateOfFire = createRow(labelRateOfFireTitle, labelRateOfFireValue, labelRateOfFireUnit, progressBarRateOfFire);
        tableAmmoDamage = createRow(labelAmmoDamageTitle, labelAmmoDamageValue, labelAmmoDamageUnit, progressBarAmmoDamage);
        tableAmmoSpeed = createRow(labelAmmoSpeedTitle, labelAmmoSpeedValue, labelAmmoSpeedUnit, progressBarAmmoSpeed);
        tableMagazineSize = createRow(labelMagazineSizeTitle, labelMagazineSizeValue, labelMagazineSizeUnit, progressBarMagazineSize);
        tableReloadTime = createRow(labelReloadTimeTitle, labelReloadTimeValue, labelReloadTimeUnit, progressBarReloadTime);

        tableRateOfFireUpgrade = createRowUpgrade(labelRateOfFireTitleUpgrade, labelRateOfFireValueUpgrade, labelRateOfFireUnitUpgrade, labelRateOfFireUpgradeUpgrade, labelRateOfFireUpgradeUnitUpgrade, labelRateOfFireCompletedUpgrade, progressBarRateOfFireValueBought, progressBarRateOfFireUpgradeBought, progressBarRateOfFireUpgradeDone);
        tableAmmoDamageUpgrade = createRowUpgrade(labelAmmoDamageTitleUpgrade, labelAmmoDamageValueUpgrade, labelAmmoDamageUnitUpgrade, labelAmmoDamageUpgradeUpgrade, labelAmmoDamageUpgradeUnitUpgrade, labelAmmoDamageCompletedUpgrade, progressBarAmmoDamageValueBought, progressBarAmmoDamageUpgradeBought, progressBarAmmoDamageUpgradeDone);
        tableAmmoSpeedUpgrade = createRowUpgrade(labelAmmoSpeedTitleUpgrade, labelAmmoSpeedValueUpgrade, labelAmmoSpeedUnitUpgrade, labelAmmoSpeedUpgradeUpgrade, labelAmmoSpeedUpgradeUnitUpgrade, labelAmmoSpeedCompletedUpgrade, progressBarAmmoSpeedValueBought, progressBarAmmoSpeedUpgradeBought, progressBarAmmoSpeedUpgradeDone);
        tableMagazineSizeUpgrade = createRowUpgrade(labelMagazineSizeTitleUpgrade, labelMagazineSizeValueUpgrade, labelMagazineSizeUnitUpgrade, labelMagazineSizeUpgradeUpgrade, labelMagazineSizeUpgradeUnitUpgrade, labelMagazineSizeCompletedUpgrade, progressBarMagazineSizeValueBought, progressBarMagazineSizeUpgradeBought, progressBarMagazineSizeUpgradeDone);
        tableReloadTimeUpgrade = createRowUpgrade(labelReloadTimeTitleUpgrade, labelReloadTimeValueUpgrade, labelReloadTimeUnitUpgrade, labelReloadTimeUpgradeUpgrade, labelReloadTimeUpgradeUnitUpgrade, labelReloadTimeCompletedUpgrade, progressBarReloadTimeValueBought, progressBarReloadTimeUpgradeBought, progressBarReloadTimeUpgradeDone);

        weaponButtons = new Array<WeaponButton>();
        Table tableWeaponButtons = new Table();
        for (int i = 0; i < Constants.NUMBER_OF_WEAPONS; i++) {
            final WeaponButton tableWeaponButton = new WeaponButton(WEAPONS_IDS[i]);
            tableWeaponButton.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
            tableWeaponButton.setTouchable(Touchable.enabled);
            Stack stack = new Stack();
            stack.add(new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_UP))));
            tableWeaponButton.labelWeaponLevel = new Label("", game.styles.labelStyleWhiteSmall);
            Table table = new Table();
            table.add(tableWeaponButton.labelWeaponLevel).align(Align.bottomRight).expand().pad(Constants.GAP);
            stack.add(table);
            tableWeaponButton.add(stack).width(Constants.IMAGE_BUTTON_SIZE_HUGE).height(Constants.IMAGE_BUTTON_SIZE_HUGE).align(Align.center);
            tableWeaponButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_HUGE && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_HUGE) {
                        for (int k = 0; k < weaponButtons.size; k++) {
                            if (weaponButtons.get(k).ID == checkedWeaponID) {
                                weaponButtons.get(k).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
                            }
                        }
                        checkedWeaponID = tableWeaponButton.ID;
                        for (int k = 0; k < weaponButtons.size; k++) {
                            if (weaponButtons.get(k).ID == checkedWeaponID) {
                                weaponButtons.get(k).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_DOWN)));
                            }
                        }
                        updateHud();
                    }
                }
            });
            weaponButtons.add(tableWeaponButton);
            tableWeaponButtons.add(tableWeaponButton).width(Constants.IMAGE_BUTTON_SIZE_HUGE).height(Constants.IMAGE_BUTTON_SIZE_HUGE).padRight(Constants.GAP).padBottom(i < Constants.UI_WEAPONS_IN_FULL_ROW ? Constants.GAP : 0);
            if ((i + 1) % Constants.UI_WEAPONS_IN_ROW == 0) {
                tableWeaponButtons.row();
            }
        }

        checkedWeaponID = weaponButtons.get(0).ID;
        weaponButtons.get(0).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_DOWN)));

        scrollPaneWeapons = new ScrollPane(tableWeaponButtons, game.styles.scrollPaneStyle);
        scrollPaneWeapons.setScrollingDisabled(true, false);
        scrollPaneWeapons.setForceScroll(false, true);
        scrollPaneWeapons.setupOverscroll(Constants.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeapons.setFadeScrollBars(false);
        scrollPaneWeapons.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeapons.updateVisualScroll();
        scrollPaneWeapons.layout();

        Table tableWeapons = new Table();
        tableWeapons.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableWeapons.add(scrollPaneWeapons).growY().pad(Constants.GAP);

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHugeWithBorder);
        labelMoney = new Label("", game.styles.labelStyleWhiteBig);
        final ImageButton btClose = new ImageButton(game.styles.imageButtonStyleClose);
        Table tableTopRight = new Table();
        tableTopRight.add(labelMoney).align(Align.right).padRight(Constants.GAP * 2).expandX();
        tableTopRight.add(btClose).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);
        Table tableTop = new Table();
        tableTop.add().width(Constants.IMAGE_BUTTON_SIZE_SMALL * 5).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.left);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add(tableTopRight).width(Constants.IMAGE_BUTTON_SIZE_SMALL * 5).height(Constants.IMAGE_BUTTON_SIZE_SMALL).align(Align.right);

        tableWeapon = new Table();
        tableWeapon.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableWeaponRows = new Table();
        scrollPaneWeapon = new ScrollPane(tableWeaponRows, game.styles.scrollPaneStyle);
        scrollPaneWeapon.setScrollingDisabled(true, false);
        scrollPaneWeapon.setForceScroll(false, true);
        scrollPaneWeapon.setupOverscroll(Constants.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeapon.setFadeScrollBars(false);
        scrollPaneWeapon.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeapon.updateVisualScroll();
        scrollPaneWeapon.layout();

        createTableWeaponTopNotBought();
        createTableWeaponBottomNotBought();
        createTableWeaponTopBought();
        createTableWeaponBottomBought();
        createTableWeaponUpgrade();
        createTableBuyAmmo();

        super.add(tableTop).height(Constants.TABLE_TOP_HEIGHT).growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        super.row();
        super.add(tableWeapons).growY().align(Align.left).width(Constants.UI_WEAPONS_IN_ROW * (Constants.IMAGE_BUTTON_SIZE_HUGE + Constants.GAP) + 2 * Constants.GAP + Constants.SCROLL_PANE_THICKNESS);
        super.add(tableWeapon).width(Constants.WEAPON_TABLE_WIDTH).align(Align.right).growY();

        InputListener inputListenerClose = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_SMALL && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_SMALL) {
                    if (game.gameScreen.currentGameState.equals(GameState.IN_SHOP)) {
                        game.gameScreen.shopHud.saveScrollAmount();
                    }
                    game.gameScreen.setGameState(GameState.IN_GAME);
                    game.gameScreen.stage.clear();
                    game.gameWorld.paused = false;
                    game.gameScreen.inGameHud.updateHud();
                    game.gameScreen.stage.addActor(game.gameScreen.inGameHud);
                }
            }
        };

        btClose.addListener(inputListenerClose);
    }

    private Table createBasicInfoBox() {
        Table tableDataRow1 = new Table();
        tableDataRow1.add(labelBasicInfoFireType).growX().align(Align.topLeft);
        Table tableDataRow3 = new Table();
        tableDataRow3.add(labelBasicInfoAmmoPrice).align(Align.topLeft);
        tableDataRow3.add(labelAmmoPriceUnit).growX().align(Align.topLeft);
        Table tableDataRow2 = new Table();
        tableDataRow2.add(labelPointsTitleHit).align(Align.topLeft);
        tableDataRow2.add(labelBasicInfoPointsHit).align(Align.topLeft);
        tableDataRow2.add(labelPointsTitleKill).align(Align.topLeft);
        tableDataRow2.add(labelBasicInfoPointsKill).growX().align(Align.topLeft);
        Table tableRows = new Table();
        tableRows.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRows.add(labelFireTypeTitle).align(Align.bottomLeft).width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH).height(uselessLabel.getHeight()).padLeft(Constants.GAP).padTop(Constants.GAP);
        tableRows.add(tableDataRow1).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH).padRight(Constants.GAP).padTop(Constants.GAP);
        tableRows.row();
        tableRows.add(labelPointsTitle).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH).height(uselessLabel.getHeight()).padLeft(Constants.GAP);
        tableRows.add(tableDataRow2).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH).padRight(Constants.GAP);
        tableRows.row();
        tableRows.add(labelAmmoPriceTitle).align(Align.left).width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH).height(uselessLabel.getHeight()).padLeft(Constants.GAP).padBottom(Constants.GAP);
        tableRows.add(tableDataRow3).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH).padRight(Constants.GAP).padBottom(Constants.GAP);
        return tableRows;
    }

    private Table createRow(Label title, Label value, Label unit, ProgressBar valueProgress) {
        Table tableData = new Table();
        tableData.add(title).width(Constants.WEAPON_TITLE_WIDTH).align(Align.left);
        tableData.add(value).width(Constants.WEAPON_VALUE_WIDTH).align(Align.topLeft);
        tableData.add(unit).width(Constants.WEAPON_UNIT_WIDTH).align(Align.topLeft);
        tableData.row();
        tableData.add(valueProgress).colspan(5).width(Constants.WEAPON_BAR_WIDTH).height(Constants.PROGRESS_BAR_DATA_THICKNESS).expandY().align(Align.bottomLeft);
        Table tableRow = new Table();
        tableRow.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRow.add(tableData).growY().pad(Constants.GAP);
        return tableRow;
    }

    private Table createRowUpgrade(Label title, Label value, Label unit, Label upgrade, Label unitUpgrade, Label labelCompleted, ProgressBar valueProgress, ProgressBar upgradeProgress, ProgressBar doneProgress) {
        Table tableData = new Table();
        tableData.add(title).width(Constants.WEAPON_TITLE_WIDTH_UPGRADE).align(Align.left);
        tableData.add(value).width(Constants.WEAPON_VALUE_WIDTH_UPGRADE).align(Align.topLeft);
        tableData.add(unit).width(Constants.WEAPON_UNIT_WIDTH_UPGRADE).align(Align.topLeft);
        Table tableUpgrade = new Table();
        tableUpgrade.add(upgrade).width(Constants.WEAPON_UPGRADE_WIDTH_UPGRADE).align(Align.topLeft);
        tableUpgrade.add(unitUpgrade).width(Constants.WEAPON_UNIT_WIDTH_UPGRADE).align(Align.topLeft);
        Table tableCompleted = new Table();
        tableCompleted.add(labelCompleted).expandX().align(Align.right);
        Stack stackUpgrade = new Stack();
        stackUpgrade.add(tableUpgrade);
        stackUpgrade.add(tableCompleted);
        tableData.add(stackUpgrade).width(Constants.WEAPON_UPGRADE_WIDTH_UPGRADE + Constants.WEAPON_UNIT_WIDTH_UPGRADE).align(Align.topLeft);
        Stack stackBar = new Stack();
        stackBar.add(upgradeProgress);
        stackBar.add(valueProgress);
        stackBar.add(doneProgress);
        tableData.row();
        tableData.add(stackBar).colspan(7).width(Constants.WEAPON_BAR_WIDTH_UPGRADE).height(Constants.PROGRESS_BAR_DATA_THICKNESS).expandY().align(Align.bottomLeft);
        Table tableRow = new Table();
        tableRow.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRow.add(tableData).growY().pad(Constants.GAP);
        return tableRow;
    }

    private void createTableWeaponTopNotBought() {
        tableWeaponTopNotBought = new Table();
        tableWeaponTopNotBought.add(labelWeaponNameNotBought).pad(Constants.GAP).align(Align.left).growX();
    }

    private void createTableWeaponTopBought() {
        tableWeaponTopBought = new Table();
        tableWeaponTopBought.add(labelWeaponNameBought).pad(Constants.GAP).align(Align.left).expandX();
        tableWeaponTopBought.add(labelNumberOfAmmoTitle).padTop(Constants.GAP).padBottom(Constants.GAP).align(Align.right);
        tableWeaponTopBought.add(labelNumberOfAmmo).pad(Constants.GAP).align(Align.right);
    }

    private void createTableWeaponBottomNotBought() {
        tableWeaponBottomNotBought = new Table();
        final Table tableSelectedBuy = new Table();
        buttonBuyWeapon = new TextButton("", game.styles.textButtonStyleOrange);
        buttonBuyWeapon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonBuyWeapon.getWidth() && y > 0 && y < buttonBuyWeapon.getHeight()) {
                    if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).price) {
                        game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).bought = true;
                        game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).price;
                        saveScrollAmount();
                        updateHud();
                        if (checkedWeaponID == Constants.PISTOL_ID) {
                            game.gameWorld.player.addWeapon(game.gamePools.pistolPlayerPool.obtain(), game.gameWorld.weaponManagerPlayer.getWeaponData(Constants.PISTOL_ID), game.gameWorld.player, null, true);
                        } else if (checkedWeaponID == Constants.ASSAULT_RIFLE_ID) {
                            game.gameWorld.player.addWeapon(game.gamePools.assaultRiflePlayerPool.obtain(), game.gameWorld.weaponManagerPlayer.getWeaponData(Constants.ASSAULT_RIFLE_ID), game.gameWorld.player, null, true);
                        } else if (checkedWeaponID == Constants.SHOTGUN_ID) {
                            game.gameWorld.player.addWeapon(game.gamePools.shotgunPool.obtain(), game.gameWorld.weaponManagerPlayer.getWeaponData(Constants.SHOTGUN_ID), game.gameWorld.player, null, true);
                        } else if (checkedWeaponID == Constants.SNIPER_ID) {
                            game.gameWorld.player.addWeapon(game.gamePools.sniperPool.obtain(), game.gameWorld.weaponManagerPlayer.getWeaponData(Constants.SNIPER_ID), game.gameWorld.player, null, true);
                        } else if (checkedWeaponID == Constants.MACHINE_GUN_ID) {
                            game.gameWorld.player.addWeapon(game.gamePools.machineGunPool.obtain(), game.gameWorld.weaponManagerPlayer.getWeaponData(Constants.MACHINE_GUN_ID), game.gameWorld.player, null, true);
                        } else if (checkedWeaponID == Constants.ROCKET_LAUNCHER_ID) {
                            game.gameWorld.player.addWeapon(game.gamePools.rocketLauncherPool.obtain(), game.gameWorld.weaponManagerPlayer.getWeaponData(Constants.ROCKET_LAUNCHER_ID), game.gameWorld.player, null, true);
                        }
                    } else {
                        game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                    }
                }
            }
        });
        tableSelectedBuy.add(labelWeaponPrice).expandX().align(Align.right);
        tableSelectedBuy.add(buttonBuyWeapon).padLeft(Constants.GAP).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);
        tableWeaponBottomNotBought.add(tableSelectedBuy).growX().pad(Constants.GAP).align(Align.bottomRight);
    }

    private void createTableWeaponBottomBought() {
        tableWeaponBottomBought = new Table();

        buttonGetAmmo = new TextButton("", game.styles.textButtonStyleOrange);
        buttonGetAmmo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonGetAmmo.getWidth() && y > 0 && y < buttonGetAmmo.getHeight()) {
                    game.gameScreen.stageExtra.clear();
                    game.gameScreen.stageExtra.addActor(tableBuyAmmo);
                    game.gameScreen.setShowStageExtra(true);
                    updateAmmoTable();
                }
            }
        });

        buttonUpgrade = new TextButton("", game.styles.textButtonStyleOrange);
        buttonUpgrade.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonUpgrade.getWidth() && y > 0 && y < buttonUpgrade.getHeight()) {
                    if (!buttonUpgrade.isDisabled()) {
                        game.gameScreen.stageExtra.clear();
                        game.gameScreen.stageExtra.addActor(tableWeaponUpgrade);
                        game.gameScreen.setShowStageExtra(true);
                        updateUpgradeTable();
                    } else {
                        game.alertManager.showPopup("This weapon is fully upgraded.");
                    }
                }
            }
        });

        Table tableButtons = new Table();
        tableButtons.add(buttonGetAmmo).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottomRight).growX().padRight(Constants.GAP);
        tableButtons.add(buttonUpgrade).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.bottomRight);
        tableWeaponBottomBought.add(tableButtons).growX().pad(Constants.GAP).align(Align.bottomRight);
    }

    private void createTableWeaponUpgrade() {
        final ImageButton btCloseInDialogBox = new ImageButton(game.styles.imageButtonStyleClose);
        btCloseInDialogBox.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_SMALL && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_SMALL) {
                    game.gameScreen.setShowStageExtra(false);
                    saveScrollAmountUpgrade();
                    updateHud();
                }
            }
        });
        Table tableTop = new Table();
        tableTop.add(labelWeaponNameUpgrade).align(Align.topLeft).growX().expandY();
        tableTop.add(btCloseInDialogBox).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).padLeft(Constants.GAP).align(Align.right);

        tableWeaponRowsUpgrade = new Table();
        scrollPaneWeaponUpgrade = new ScrollPane(tableWeaponRowsUpgrade, game.styles.scrollPaneStyle);
        scrollPaneWeaponUpgrade.setScrollingDisabled(true, false);
        scrollPaneWeaponUpgrade.setForceScroll(false, true);
        scrollPaneWeaponUpgrade.setupOverscroll(Constants.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeaponUpgrade.setFadeScrollBars(false);
        scrollPaneWeaponUpgrade.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeaponUpgrade.updateVisualScroll();
        scrollPaneWeaponUpgrade.layout();

        /*buttonUpgradeWeight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightMaxLevel) {
                        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel).price) {
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weight += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel).upgrade;
                            game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel).price;
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel++;
                            updateUpgradeTable();
                            updateHud();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });*/
        buttonBuyUpgrade = new TextButton("", game.styles.textButtonStyleOrange);
        buttonBuyUpgrade.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    if (!buttonBuyUpgrade.isDisabled()) {
                        if (game.gameWorld.player.money >= (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).upgradePrices.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level))) {
                            game.gameWorld.player.money -= (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).upgradePrices.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level));
                            if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
                                game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level);
                            }
                            if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
                                game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level);
                            }
                            if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
                                game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level);
                            }
                            if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
                                game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level);
                            }
                            if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
                                game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level);
                            }
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).level++;

                            updateUpgradeTable();
                            updateHud();
                            game.gameScreen.inGameHud.updateHud();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    } else {
                        game.alertManager.showPopup("This weapon is fully upgraded!!!!!!");
                    }
                }
            }
        });
        Table tableBottom = new Table();
        tableBottom.add(labelYourMoneyTitleUpgrade).padRight(Constants.GAP).align(Align.left);
        tableBottom.add(labelYourMoneyUpgrade).align(Align.left);
        tableBottom.add(labelPriceUpgrade).expandX().align(Align.right);
        tableBottom.add(buttonBuyUpgrade).padLeft(Constants.GAP).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);

        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        table.add(tableTop).pad(Constants.GAP).align(Align.left).growX();
        table.row();
        table.add(scrollPaneWeaponUpgrade).align(Align.left).grow().padRight(Constants.GAP).padLeft(Constants.GAP);
        table.row();
        table.add(tableBottom).pad(Constants.GAP).align(Align.left).growX();

        tableWeaponUpgrade = new Table();
        tableWeaponUpgrade.setFillParent(true);
        tableWeaponUpgrade.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableWeaponUpgrade.add(table).width(Constants.DIALOG_BOX_WIDTH_WEAPON).height(Constants.DIALOG_BOX_HEIGHT_WEAPON);
    }

    private void createTableBuyAmmo() {
        Table tableRow11 = new Table();
        tableRow11.add(labelWeaponNameBuyAmmo).align(Align.left).growX();
        tableRow11.add(labelYourAmmoTitleBuyAmmo).padRight(Constants.GAP).align(Align.right);
        tableRow11.add(labelYourAmmoBuyAmmo).align(Align.right);
        final ImageButton btCloseInDialogBox = new ImageButton(game.styles.imageButtonStyleClose);
        btCloseInDialogBox.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_SMALL && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_SMALL) {
                    game.gameScreen.setShowStageExtra(false);
                    updateHud();
                }
            }
        });
        Table tableRow1 = new Table();
        tableRow1.add(tableRow11).align(Align.topLeft).expandY().growX();
        tableRow1.add(btCloseInDialogBox).width(Constants.IMAGE_BUTTON_SIZE_SMALL).height(Constants.IMAGE_BUTTON_SIZE_SMALL).padLeft(Constants.GAP).align(Align.right);
        Table tableRow2 = new Table();
        tableRow2.add(labelGuideBuyAmmo).align(Align.left).expandX();
        ImageButton buttonMags = new ImageButton(game.styles.imageButtonStyleMagazines);
        buttonMags.setChecked(true);
        buttonMags.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    sliderNumberOfMagsBuyAmmo.setVisible(true);
                    sliderNumberOfAmmoBuyAmmo.setVisible(false);
                    updateAmmoTable();
                }
            }
        });
        ImageButton buttonAmmo = new ImageButton(game.styles.imageButtonStyleMagazines);
        buttonAmmo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    sliderNumberOfMagsBuyAmmo.setVisible(false);
                    sliderNumberOfAmmoBuyAmmo.setVisible(true);
                    updateAmmoTable();
                }
            }
        });
        ButtonGroup<ImageButton> btgMagsOrAmmo = new ButtonGroup<ImageButton>(buttonMags, buttonAmmo);
        btgMagsOrAmmo.setMaxCheckCount(1);
        btgMagsOrAmmo.setMinCheckCount(1);
        btgMagsOrAmmo.setUncheckLast(true);
        Table tableRow3 = new Table();
        tableRow3.add(buttonMags).width(Constants.IMAGE_BUTTON_SIZE_TINY).height(Constants.IMAGE_BUTTON_SIZE_TINY).padRight(Constants.GAP / 2f);
        tableRow3.add(labelMagsBuyAmmo).padRight(Constants.GAP * 2);
        tableRow3.add(buttonAmmo).width(Constants.IMAGE_BUTTON_SIZE_TINY).height(Constants.IMAGE_BUTTON_SIZE_TINY).padRight(Constants.GAP / 2f);
        tableRow3.add(labelAmmoBuyAmmo);
        Table tableRow4 = new Table();
        tableRow4.add(labelNumberOfMagsOrAmmoTitleBuyAmmo).padRight(Constants.GAP);
        tableRow4.add(labelNumberOfMagsOrAmmoBuyAmmo);
        sliderNumberOfMagsBuyAmmo = new Slider(1, 50, 1, false, game.styles.sliderStyle);
        sliderNumberOfMagsBuyAmmo.setValue(20);
        sliderNumberOfMagsBuyAmmo.getStyle().background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderNumberOfMagsBuyAmmo.getStyle().knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderNumberOfMagsBuyAmmo.getStyle().knob.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        sliderNumberOfMagsBuyAmmo.getStyle().knob.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        sliderNumberOfMagsBuyAmmo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                updateAmmoTable();
            }
        });
        sliderNumberOfAmmoBuyAmmo = new Slider(1, 50, 1, false, game.styles.sliderStyle);
        sliderNumberOfAmmoBuyAmmo.setValue(30);
        sliderNumberOfAmmoBuyAmmo.getStyle().background.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderNumberOfAmmoBuyAmmo.getStyle().knobBefore.setMinHeight(Constants.PROGRESS_BAR_HEIGHT);
        sliderNumberOfAmmoBuyAmmo.getStyle().knob.setMinWidth(Constants.IMAGE_BUTTON_SIZE_TINY);
        sliderNumberOfAmmoBuyAmmo.getStyle().knob.setMinHeight(Constants.IMAGE_BUTTON_SIZE_TINY);
        sliderNumberOfAmmoBuyAmmo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                updateAmmoTable();
            }
        });
        sliderNumberOfAmmoBuyAmmo.setVisible(false);
        Stack stack = new Stack();
        stack.add(sliderNumberOfMagsBuyAmmo);
        stack.add(sliderNumberOfAmmoBuyAmmo);
        Table tableRow5 = new Table();
        tableRow5.add(stack).growX();
        buttonBuyAmmo = new TextButton("", game.styles.textButtonStyleOrange);
        buttonBuyAmmo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    if (sliderNumberOfMagsBuyAmmo.isVisible()) {
                        if (game.gameWorld.player.money >= ((int) (sliderNumberOfMagsBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice)) {
                            game.gameWorld.player.money -= ((int) (sliderNumberOfMagsBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice);
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo += ((int) (sliderNumberOfMagsBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize);
                            game.gameWorld.player.weapon.updateWeaponWhenMoreAmmoIsGiven();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    } else if (sliderNumberOfAmmoBuyAmmo.isVisible()) {
                        if (game.gameWorld.player.money >= ((int) (sliderNumberOfAmmoBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice)) {
                            game.gameWorld.player.money -= ((int) (sliderNumberOfAmmoBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice);
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo += ((int) (sliderNumberOfAmmoBuyAmmo.getValue()));
                            game.gameWorld.player.weapon.updateWeaponWhenMoreAmmoIsGiven();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                    updateAmmoTable();
                    updateHud();
                    game.gameScreen.inGameHud.updateHud();
                }
            }
        });
        Table tableRow6 = new Table();
        tableRow6.add(labelYourMoneyTitleBuyAmmo).padRight(Constants.GAP).align(Align.left);
        tableRow6.add(labelYourMoneyBuyAmmo).align(Align.left);
        tableRow6.add(labelPriceBuyAmmo).expandX().align(Align.right);
        tableRow6.add(buttonBuyAmmo).padLeft(Constants.GAP).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);

        Table table = new Table();
        table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));

        table.add(tableRow1).padLeft(Constants.GAP).padRight(Constants.GAP).padTop(Constants.GAP).padBottom(Constants.GAP).growX().expandY().align(Align.top);
        table.row();
        table.add(tableRow2).padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP).expandX().align(Align.bottomLeft);
        table.row();
        table.add(tableRow3).padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP).expandX().align(Align.bottomLeft);
        table.row();
        table.add(tableRow4).padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP).expandX().align(Align.topLeft);
        table.row();
        table.add(tableRow5).padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP).growX().align(Align.topLeft);
        table.row();
        table.add(tableRow6).padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP).growX().expandY().align(Align.bottom);
        tableBuyAmmo = new Table();
        tableBuyAmmo.setFillParent(true);
        tableBuyAmmo.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableBuyAmmo.add(table).width(Constants.DIALOG_BOX_WIDTH_WEAPON).height(Constants.DIALOG_BOX_HEIGHT_WEAPON);
    }

    public void saveScrollAmount() {
        if (checkedWeaponID == Constants.PISTOL_ID) {
            scrollAmountPistol = scrollPaneWeapon.getScrollY();
        } else if (checkedWeaponID == Constants.ASSAULT_RIFLE_ID) {
            scrollAmountAssaultRifle = scrollPaneWeapon.getScrollY();
        } else if (checkedWeaponID == Constants.SHOTGUN_ID) {
            scrollAmountShotgun = scrollPaneWeapon.getScrollY();
        } else if (checkedWeaponID == Constants.SNIPER_ID) {
            scrollAmountSniper = scrollPaneWeapon.getScrollY();
        } else if (checkedWeaponID == Constants.MACHINE_GUN_ID) {
            scrollAmountMachineGun = scrollPaneWeapon.getScrollY();
        } else if (checkedWeaponID == Constants.ROCKET_LAUNCHER_ID) {
            scrollAmountRocketLauncher = scrollPaneWeapon.getScrollY();
        }
    }

    private float loadScrollAmount() {
        if (checkedWeaponID == Constants.PISTOL_ID) {
            return scrollAmountPistol;
        } else if (checkedWeaponID == Constants.ASSAULT_RIFLE_ID) {
            return scrollAmountAssaultRifle;
        } else if (checkedWeaponID == Constants.SHOTGUN_ID) {
            return scrollAmountShotgun;
        } else if (checkedWeaponID == Constants.SNIPER_ID) {
            return scrollAmountSniper;
        } else if (checkedWeaponID == Constants.MACHINE_GUN_ID) {
            return scrollAmountMachineGun;
        } else if (checkedWeaponID == Constants.ROCKET_LAUNCHER_ID) {
            return scrollAmountRocketLauncher;
        }
        return 0;
    }

    private void saveScrollAmountUpgrade() {
        if (checkedWeaponID == Constants.PISTOL_ID) {
            scrollAmountPistolUpgrade = scrollPaneWeaponUpgrade.getScrollY();
        } else if (checkedWeaponID == Constants.ASSAULT_RIFLE_ID) {
            scrollAmountAssaultRifleUpgrade = scrollPaneWeaponUpgrade.getScrollY();
        } else if (checkedWeaponID == Constants.SHOTGUN_ID) {
            scrollAmountShotgunUpgrade = scrollPaneWeaponUpgrade.getScrollY();
        } else if (checkedWeaponID == Constants.SNIPER_ID) {
            scrollAmountSniperUpgrade = scrollPaneWeaponUpgrade.getScrollY();
        } else if (checkedWeaponID == Constants.MACHINE_GUN_ID) {
            scrollAmountMachineGunUpgrade = scrollPaneWeaponUpgrade.getScrollY();
        } else if (checkedWeaponID == Constants.ROCKET_LAUNCHER_ID) {
            scrollAmountRocketLauncherUpgrade = scrollPaneWeaponUpgrade.getScrollY();
        }
    }

    private float loadScrollAmountUpgrade() {
        if (checkedWeaponID == Constants.PISTOL_ID) {
            return scrollAmountPistolUpgrade;
        } else if (checkedWeaponID == Constants.ASSAULT_RIFLE_ID) {
            return scrollAmountAssaultRifleUpgrade;
        } else if (checkedWeaponID == Constants.SHOTGUN_ID) {
            return scrollAmountShotgunUpgrade;
        } else if (checkedWeaponID == Constants.SNIPER_ID) {
            return scrollAmountSniperUpgrade;
        } else if (checkedWeaponID == Constants.MACHINE_GUN_ID) {
            return scrollAmountMachineGunUpgrade;
        } else if (checkedWeaponID == Constants.ROCKET_LAUNCHER_ID) {
            return scrollAmountRocketLauncherUpgrade;
        }
        return 0;
    }
}