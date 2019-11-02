package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.StackWithID;
import com.aleksiprograms.survivalofkeijo.toolbox.TableWithID;
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
            Constants.KNIFE_ID,
            Constants.PISTOL_ID,
            Constants.ASSAULT_RIFLE_ID,
            Constants.SHOTGUN_ID,
            Constants.SNIPER_ID,
            Constants.MACHINE_GUN_ID,
            Constants.ROCKET_LAUNCHER_ID};

    private Label labelScreenTitle;

    private Array<TableWithID> weaponButtons;
    private Array<StackWithID> weaponButtonStacks;
    private int checkedWeaponID;
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
    private Table tableWeight;
    private Table tableMeleeDamage;

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
    private Label labelRateOfFireLevelTitle;
    private Label labelRateOfFireLevel;
    private Label labelRateOfFireMaxLevel;
    private Label labelAmmoDamageTitle;
    private Label labelAmmoDamageValue;
    private Label labelAmmoDamageUnit;
    private Label labelAmmoDamageLevelTitle;
    private Label labelAmmoDamageLevel;
    private Label labelAmmoDamageMaxLevel;
    private Label labelAmmoSpeedTitle;
    private Label labelAmmoSpeedValue;
    private Label labelAmmoSpeedUnit;
    private Label labelAmmoSpeedLevelTitle;
    private Label labelAmmoSpeedLevel;
    private Label labelAmmoSpeedMaxLevel;
    private Label labelMagazineSizeTitle;
    private Label labelMagazineSizeValue;
    private Label labelMagazineSizeUnit;
    private Label labelMagazineSizeLevelTitle;
    private Label labelMagazineSizeLevel;
    private Label labelMagazineSizeMaxLevel;
    private Label labelReloadTimeTitle;
    private Label labelReloadTimeValue;
    private Label labelReloadTimeUnit;
    private Label labelReloadTimeLevelTitle;
    private Label labelReloadTimeLevel;
    private Label labelReloadTimeMaxLevel;
    private Label labelWeightTitle;
    private Label labelWeightValue;
    private Label labelWeightUnit;
    private Label labelWeightLevelTitle;
    private Label labelWeightLevel;
    private Label labelWeightMaxLevel;
    private Label labelMeleeDamageTitle;
    private Label labelMeleeDamageValue;
    private Label labelMeleeDamageUnit;
    private Label labelMeleeDamageLevelTitle;
    private Label labelMeleeDamageLevel;
    private Label labelMeleeDamageMaxLevel;
    private ProgressBar progressBarRateOfFire;
    private ProgressBar progressBarAmmoDamage;
    private ProgressBar progressBarAmmoSpeed;
    private ProgressBar progressBarMagazineSize;
    private ProgressBar progressBarReloadTime;
    private ProgressBar progressBarWeight;
    private ProgressBar progressBarMeleeDamage;

    private Table tableWeaponUpgrade;
    private Table tableWeaponRowsUpgrade;
    private ScrollPane scrollPaneWeaponUpgrade;

    private int weaponNumberOfRowsUpgrade;
    private Table tableRateOfFireUpgrade;
    private Table tableAmmoDamageUpgrade;
    private Table tableAmmoSpeedUpgrade;
    private Table tableMagazineSizeUpgrade;
    private Table tableReloadTimeUpgrade;
    private Table tableWeightUpgrade;
    private Table tableMeleeDamageUpgrade;

    private Label labelWeaponNameUpgrade;
    private Label labelYourMoneyTitleUpgrade;
    private Label labelYourMoneyUpgrade;
    private Label labelRateOfFireTitleUpgrade;
    private Label labelRateOfFireValueUpgrade;
    private Label labelRateOfFireUnitUpgrade;
    private Label labelRateOfFireLevelTitleUpgrade;
    private Label labelRateOfFireLevelUpgrade;
    private Label labelRateOfFireMaxLevelUpgrade;
    private Label labelRateOfFireUpgradeUpgrade;
    private Label labelRateOfFireUpgradeUnitUpgrade;
    private Label labelRateOfFirePriceUpgrade;
    private Label labelRateOfFirePriceUnitUpgrade;
    private Label labelAmmoDamageTitleUpgrade;
    private Label labelAmmoDamageValueUpgrade;
    private Label labelAmmoDamageUnitUpgrade;
    private Label labelAmmoDamageLevelTitleUpgrade;
    private Label labelAmmoDamageLevelUpgrade;
    private Label labelAmmoDamageMaxLevelUpgrade;
    private Label labelAmmoDamageUpgradeUpgrade;
    private Label labelAmmoDamageUpgradeUnitUpgrade;
    private Label labelAmmoDamagePriceUpgrade;
    private Label labelAmmoDamagePriceUnitUpgrade;
    private Label labelAmmoSpeedTitleUpgrade;
    private Label labelAmmoSpeedValueUpgrade;
    private Label labelAmmoSpeedUnitUpgrade;
    private Label labelAmmoSpeedLevelTitleUpgrade;
    private Label labelAmmoSpeedLevelUpgrade;
    private Label labelAmmoSpeedMaxLevelUpgrade;
    private Label labelAmmoSpeedUpgradeUpgrade;
    private Label labelAmmoSpeedUpgradeUnitUpgrade;
    private Label labelAmmoSpeedPriceUpgrade;
    private Label labelAmmoSpeedPriceUnitUpgrade;
    private Label labelMagazineSizeTitleUpgrade;
    private Label labelMagazineSizeValueUpgrade;
    private Label labelMagazineSizeUnitUpgrade;
    private Label labelMagazineSizeLevelTitleUpgrade;
    private Label labelMagazineSizeLevelUpgrade;
    private Label labelMagazineSizeMaxLevelUpgrade;
    private Label labelMagazineSizeUpgradeUpgrade;
    private Label labelMagazineSizeUpgradeUnitUpgrade;
    private Label labelMagazineSizePriceUpgrade;
    private Label labelMagazineSizePriceUnitUpgrade;
    private Label labelReloadTimeTitleUpgrade;
    private Label labelReloadTimeValueUpgrade;
    private Label labelReloadTimeUnitUpgrade;
    private Label labelReloadTimeLevelTitleUpgrade;
    private Label labelReloadTimeLevelUpgrade;
    private Label labelReloadTimeMaxLevelUpgrade;
    private Label labelReloadTimeUpgradeUpgrade;
    private Label labelReloadTimeUpgradeUnitUpgrade;
    private Label labelReloadTimePriceUpgrade;
    private Label labelReloadTimePriceUnitUpgrade;
    private Label labelWeightTitleUpgrade;
    private Label labelWeightValueUpgrade;
    private Label labelWeightUnitUpgrade;
    private Label labelWeightLevelTitleUpgrade;
    private Label labelWeightLevelUpgrade;
    private Label labelWeightMaxLevelUpgrade;
    private Label labelWeightUpgradeUpgrade;
    private Label labelWeightUpgradeUnitUpgrade;
    private Label labelWeightPriceUpgrade;
    private Label labelWeightPriceUnitUpgrade;
    private Label labelMeleeDamageTitleUpgrade;
    private Label labelMeleeDamageValueUpgrade;
    private Label labelMeleeDamageUnitUpgrade;
    private Label labelMeleeDamageLevelTitleUpgrade;
    private Label labelMeleeDamageLevelUpgrade;
    private Label labelMeleeDamageMaxLevelUpgrade;
    private Label labelMeleeDamageUpgradeUpgrade;
    private Label labelMeleeDamageUpgradeUnitUpgrade;
    private Label labelMeleeDamagePriceUpgrade;
    private Label labelMeleeDamagePriceUnitUpgrade;
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
    private ProgressBar progressBarWeightValueBought;
    private ProgressBar progressBarWeightUpgradeBought;
    private ProgressBar progressBarMeleeDamageValueBought;
    private ProgressBar progressBarMeleeDamageUpgradeBought;
    private ImageButton buttonUpgradeRateOfFire;
    private ImageButton buttonUpgradeAmmoDamage;
    private ImageButton buttonUpgradeAmmoSpeed;
    private ImageButton buttonUpgradeMagazineSize;
    private ImageButton buttonUpgradeReloadTime;
    private ImageButton buttonUpgradeWeight;
    private ImageButton buttonUpgradeMeleeDamage;
    private ProgressBar progressBarRateOfFireUpgradeDone;
    private ProgressBar progressBarAmmoDamageUpgradeDone;
    private ProgressBar progressBarAmmoSpeedUpgradeDone;
    private ProgressBar progressBarMagazineSizeUpgradeDone;
    private ProgressBar progressBarReloadTimeUpgradeDone;
    private ProgressBar progressBarWeightUpgradeDone;
    private ProgressBar progressBarMeleeDamageUpgradeDone;
    private Image imageUpgradeRateOfFireDone;
    private Image imageUpgradeAmmoDamageDone;
    private Image imageUpgradeAmmoSpeedDone;
    private Image imageUpgradeMagazineSizeDone;
    private Image imageUpgradeReloadTimeDone;
    private Image imageUpgradeWeightDone;
    private Image imageUpgradeMeleeDamageDone;

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

    public ShopHud(final TheGame game) {
        super(game);
        initializeHud();
    }

    @Override
    public void updateHudData() {
        super.updateHudData();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleShop"));
        tableWeapon.clearChildren();
        tableWeaponRows.clearChildren();
        updateWeaponInfoBox();
        updateWeaponData();
        recreateTableWeaponScrollPane();
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

    private void updateWeaponUpgradeTable() {
        tableWeaponRowsUpgrade.clearChildren();
        recreateTableWeaponScrollPaneUpgrade();
        updateWeaponDataUpgrade();
    }

    private void updateBuyAmmoTable() {
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

    private void recreateTableWeaponScrollPane() {
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
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMeleeDamage) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableMeleeDamage).padBottom(weaponNumberOfRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
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
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isWeight) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableWeight).padBottom(weaponNumberOfRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }

        scrollPaneWeapon.removeActor(tableWeaponRows);
        scrollPaneWeapon.setActor(tableWeaponRows);
        scrollPaneWeapon.updateVisualScroll();
        scrollPaneWeapon.layout();
    }

    private void recreateTableWeaponScrollPaneUpgrade() {
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
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMeleeDamage) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableMeleeDamageUpgrade).padBottom(weaponNumberOfRowsUpgrade < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
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
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isWeight) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableWeightUpgrade).padBottom(weaponNumberOfRowsUpgrade < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }

        scrollPaneWeaponUpgrade.removeActor(tableWeaponRowsUpgrade);
        scrollPaneWeaponUpgrade.setActor(tableWeaponRowsUpgrade);
        scrollPaneWeaponUpgrade.updateVisualScroll();
        scrollPaneWeaponUpgrade.layout();
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

        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).price) {
            buttonBuyWeapon.setDisabled(false);
        } else {
            buttonBuyWeapon.setDisabled(true);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
            labelRateOfFireTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFire"));
            labelRateOfFireValue.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire));
            labelRateOfFireUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
            labelRateOfFireLevelTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelRateOfFireLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel + "");
            labelRateOfFireMaxLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireMaxLevel + "");
            progressBarRateOfFire.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
            labelAmmoDamageTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamage"));
            labelAmmoDamageValue.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage));
            labelAmmoDamageUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
            labelAmmoDamageLevelTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelAmmoDamageLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel + "");
            labelAmmoDamageMaxLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageMaxLevel + "");
            progressBarAmmoDamage.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
            labelAmmoSpeedTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeed"));
            labelAmmoSpeedValue.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed));
            labelAmmoSpeedUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
            labelAmmoSpeedLevelTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelAmmoSpeedLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel + "");
            labelAmmoSpeedMaxLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedMaxLevel + "");
            progressBarAmmoSpeed.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
            labelMagazineSizeTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSize"));
            labelMagazineSizeValue.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize));
            labelMagazineSizeUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
            labelMagazineSizeLevelTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelMagazineSizeLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel + "");
            labelMagazineSizeMaxLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeMaxLevel + "");
            progressBarMagazineSize.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
            labelReloadTimeTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTime"));
            labelReloadTimeValue.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime));
            labelReloadTimeUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
            labelReloadTimeLevelTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelReloadTimeLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel + "");
            labelReloadTimeMaxLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeMaxLevel + "");
            progressBarReloadTime.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isWeight) {
            labelWeightTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelWeight"));
            labelWeightValue.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weight));
            labelWeightUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelWeightUnit"));
            labelWeightLevelTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelWeightLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel + "");
            labelWeightMaxLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightMaxLevel + "");
            progressBarWeight.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weight);
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMeleeDamage) {
            labelMeleeDamageTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMeleeDamage"));
            labelMeleeDamageValue.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamage));
            labelMeleeDamageUnit.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMeleeDamageUnit"));
            labelMeleeDamageLevelTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelMeleeDamageLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel + "");
            labelMeleeDamageMaxLevel.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageMaxLevel + "");
            progressBarMeleeDamage.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamage);
        }
    }

    private void updateWeaponDataUpgrade() {
        labelWeaponNameUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).nameID));
        labelYourMoneyTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelYourMoneyTitle"));
        labelYourMoneyUpgrade.setText(game.styles.getFormattedFloatMoney(game.gameWorld.player.money) + " \u20AC");
        labelNumberOfAmmoTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelNumberOfAmmo.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo));
        buttonGetAmmo.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonGetAmmo"));
        buttonUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("buttonUpgrade"));

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
            labelRateOfFireTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFire"));
            labelRateOfFireValueUpgrade.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire));
            labelRateOfFireUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
            labelRateOfFireLevelTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelRateOfFireLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel + "");
            labelRateOfFireMaxLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireMaxLevel + "");
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).rateOfFireLevel >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireMaxLevel) {
                labelRateOfFireUpgradeUpgrade.setText("");
                labelRateOfFireUpgradeUnitUpgrade.setText("");
                labelRateOfFirePriceUpgrade.setText("");
                labelRateOfFirePriceUnitUpgrade.setText("");
                progressBarRateOfFireValueBought.setVisible(false);
                progressBarRateOfFireUpgradeBought.setVisible(false);
                progressBarRateOfFireUpgradeDone.setVisible(true);
                progressBarRateOfFireUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire);
                buttonUpgradeRateOfFire.setVisible(false);
                imageUpgradeRateOfFireDone.setVisible(true);
            } else {
                labelRateOfFireUpgradeUpgrade.setText(game.styles.getFormattedFloatWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel).upgrade));
                labelRateOfFireUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
                labelRateOfFirePriceUpgrade.setText(game.styles.getFormattedIntMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel).price));
                labelRateOfFirePriceUnitUpgrade.setText(" \u20AC");
                progressBarRateOfFireValueBought.setVisible(true);
                progressBarRateOfFireUpgradeBought.setVisible(true);
                progressBarRateOfFireUpgradeDone.setVisible(false);
                progressBarRateOfFireValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire);
                progressBarRateOfFireUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel).upgrade);
                buttonUpgradeRateOfFire.setVisible(true);
                if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel).price) {
                    buttonUpgradeRateOfFire.setDisabled(false);
                } else {
                    buttonUpgradeRateOfFire.setDisabled(true);
                }
                imageUpgradeRateOfFireDone.setVisible(false);
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
            labelAmmoDamageTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamage"));
            labelAmmoDamageValueUpgrade.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage));
            labelAmmoDamageUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
            labelAmmoDamageLevelTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelAmmoDamageLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel + "");
            labelAmmoDamageMaxLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageMaxLevel + "");
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).ammoDamageLevel >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageMaxLevel) {
                labelAmmoDamageUpgradeUpgrade.setText("");
                labelAmmoDamageUpgradeUnitUpgrade.setText("");
                labelAmmoDamagePriceUpgrade.setText("");
                labelAmmoDamagePriceUnitUpgrade.setText("");
                progressBarAmmoDamageValueBought.setVisible(false);
                progressBarAmmoDamageUpgradeBought.setVisible(false);
                progressBarAmmoDamageUpgradeDone.setVisible(true);
                progressBarAmmoDamageUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage);
                buttonUpgradeAmmoDamage.setVisible(false);
                imageUpgradeAmmoDamageDone.setVisible(true);
            } else {
                labelAmmoDamageUpgradeUpgrade.setText(game.styles.getFormattedIntWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel).upgrade));
                labelAmmoDamageUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
                labelAmmoDamagePriceUpgrade.setText(game.styles.getFormattedIntMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel).price));
                labelAmmoDamagePriceUnitUpgrade.setText(" \u20AC");
                progressBarAmmoDamageValueBought.setVisible(true);
                progressBarAmmoDamageUpgradeBought.setVisible(true);
                progressBarAmmoDamageUpgradeDone.setVisible(false);
                progressBarAmmoDamageValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage);
                progressBarAmmoDamageUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel).upgrade);
                buttonUpgradeAmmoDamage.setVisible(true);
                if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel).price) {
                    buttonUpgradeAmmoDamage.setDisabled(false);
                } else {
                    buttonUpgradeAmmoDamage.setDisabled(true);
                }
                imageUpgradeAmmoDamageDone.setVisible(false);
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
            labelAmmoSpeedTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeed"));
            labelAmmoSpeedValueUpgrade.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed));
            labelAmmoSpeedUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
            labelAmmoSpeedLevelTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelAmmoSpeedLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel + "");
            labelAmmoSpeedMaxLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedMaxLevel + "");
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).ammoSpeedLevel >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedMaxLevel) {
                labelAmmoSpeedUpgradeUpgrade.setText("");
                labelAmmoSpeedUpgradeUnitUpgrade.setText("");
                labelAmmoSpeedPriceUpgrade.setText("");
                labelAmmoSpeedPriceUnitUpgrade.setText("");
                progressBarAmmoSpeedValueBought.setVisible(false);
                progressBarAmmoSpeedUpgradeBought.setVisible(false);
                progressBarAmmoSpeedUpgradeDone.setVisible(true);
                progressBarAmmoSpeedUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed);
                buttonUpgradeAmmoSpeed.setVisible(false);
                imageUpgradeAmmoSpeedDone.setVisible(true);
            } else {
                labelAmmoSpeedUpgradeUpgrade.setText(game.styles.getFormattedFloatWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel).upgrade));
                labelAmmoSpeedUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
                labelAmmoSpeedPriceUpgrade.setText(game.styles.getFormattedIntMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel).price));
                labelAmmoSpeedPriceUnitUpgrade.setText(" \u20AC");
                progressBarAmmoSpeedValueBought.setVisible(true);
                progressBarAmmoSpeedUpgradeBought.setVisible(true);
                progressBarAmmoSpeedUpgradeDone.setVisible(false);
                progressBarAmmoSpeedValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed);
                progressBarAmmoSpeedUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel).upgrade);
                buttonUpgradeAmmoSpeed.setVisible(true);
                if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel).price) {
                    buttonUpgradeAmmoSpeed.setDisabled(false);
                } else {
                    buttonUpgradeAmmoSpeed.setDisabled(true);
                }
                imageUpgradeAmmoSpeedDone.setVisible(false);
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
            labelMagazineSizeTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSize"));
            labelMagazineSizeValueUpgrade.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize));
            labelMagazineSizeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
            labelMagazineSizeLevelTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelMagazineSizeLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel + "");
            labelMagazineSizeMaxLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeMaxLevel + "");
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).magazineSizeLevel >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeMaxLevel) {
                labelMagazineSizeUpgradeUpgrade.setText("");
                labelMagazineSizeUpgradeUnitUpgrade.setText("");
                labelMagazineSizePriceUpgrade.setText("");
                labelMagazineSizePriceUnitUpgrade.setText("");
                progressBarMagazineSizeValueBought.setVisible(false);
                progressBarMagazineSizeUpgradeBought.setVisible(false);
                progressBarMagazineSizeUpgradeDone.setVisible(true);
                progressBarMagazineSizeUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize);
                buttonUpgradeMagazineSize.setVisible(false);
                imageUpgradeMagazineSizeDone.setVisible(true);
            } else {
                labelMagazineSizeUpgradeUpgrade.setText(game.styles.getFormattedIntWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel).upgrade));
                labelMagazineSizeUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
                labelMagazineSizePriceUpgrade.setText(game.styles.getFormattedIntMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel).price));
                labelMagazineSizePriceUnitUpgrade.setText(" \u20AC");
                progressBarMagazineSizeValueBought.setVisible(true);
                progressBarMagazineSizeUpgradeBought.setVisible(true);
                progressBarMagazineSizeUpgradeDone.setVisible(false);
                progressBarMagazineSizeValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize);
                progressBarMagazineSizeUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel).upgrade);
                buttonUpgradeMagazineSize.setVisible(true);
                if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel).price) {
                    buttonUpgradeMagazineSize.setDisabled(false);
                } else {
                    buttonUpgradeMagazineSize.setDisabled(true);
                }
                imageUpgradeMagazineSizeDone.setVisible(false);
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
            labelReloadTimeTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTime"));
            labelReloadTimeValueUpgrade.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime));
            labelReloadTimeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
            labelReloadTimeLevelTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelReloadTimeLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel + "");
            labelReloadTimeMaxLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeMaxLevel + "");
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).reloadTimeLevel >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeMaxLevel) {
                labelReloadTimeUpgradeUpgrade.setText("");
                labelReloadTimeUpgradeUnitUpgrade.setText("");
                labelReloadTimePriceUpgrade.setText("");
                labelReloadTimePriceUnitUpgrade.setText("");
                progressBarReloadTimeValueBought.setVisible(false);
                progressBarReloadTimeUpgradeBought.setVisible(false);
                progressBarReloadTimeUpgradeDone.setVisible(true);
                progressBarReloadTimeUpgradeDone.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime);
                buttonUpgradeReloadTime.setVisible(false);
                imageUpgradeReloadTimeDone.setVisible(true);
            } else {
                labelReloadTimeUpgradeUpgrade.setText(game.styles.getFormattedFloatWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel).upgrade));
                labelReloadTimeUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
                labelReloadTimePriceUpgrade.setText(game.styles.getFormattedIntMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel).price));
                labelReloadTimePriceUnitUpgrade.setText(" \u20AC");
                progressBarReloadTimeValueBought.setVisible(true);
                progressBarReloadTimeUpgradeBought.setVisible(true);
                progressBarReloadTimeUpgradeDone.setVisible(false);
                progressBarReloadTimeValueBought.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime);
                progressBarReloadTimeUpgradeBought.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime - game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel).upgrade);
                buttonUpgradeReloadTime.setVisible(true);
                if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel).price) {
                    buttonUpgradeReloadTime.setDisabled(false);
                } else {
                    buttonUpgradeReloadTime.setDisabled(true);
                }
                imageUpgradeReloadTimeDone.setVisible(false);
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isWeight) {
            labelWeightTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelWeight"));
            labelWeightValueUpgrade.setText(game.styles.getFormattedFloat(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weight));
            labelWeightUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelWeightUnit"));
            labelWeightLevelTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelWeightLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel + "");
            labelWeightMaxLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightMaxLevel + "");
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).weightLevel >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightMaxLevel) {
                labelWeightUpgradeUpgrade.setText("");
                labelWeightUpgradeUnitUpgrade.setText("");
                labelWeightPriceUpgrade.setText("");
                labelWeightPriceUnitUpgrade.setText("");
                progressBarWeightValueBought.setVisible(false);
                progressBarWeightUpgradeBought.setVisible(false);
                progressBarWeightUpgradeDone.setVisible(true);
                progressBarWeightUpgradeDone.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weight);
                buttonUpgradeWeight.setVisible(false);
                imageUpgradeWeightDone.setVisible(true);
            } else {
                labelWeightUpgradeUpgrade.setText(game.styles.getFormattedFloatWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel).upgrade));
                labelWeightUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelWeightUnit"));
                labelWeightPriceUpgrade.setText(game.styles.getFormattedIntMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel).price));
                labelWeightPriceUnitUpgrade.setText(" \u20AC");
                progressBarWeightValueBought.setVisible(true);
                progressBarWeightUpgradeBought.setVisible(true);
                progressBarWeightUpgradeDone.setVisible(false);
                progressBarWeightValueBought.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weight);
                progressBarWeightUpgradeBought.setValue(-game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weight - game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel).upgrade);
                buttonUpgradeWeight.setVisible(true);
                if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).weightLevel).price) {
                    buttonUpgradeWeight.setDisabled(false);
                } else {
                    buttonUpgradeWeight.setDisabled(true);
                }
                imageUpgradeWeightDone.setVisible(false);
            }
        }

        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMeleeDamage) {
            labelMeleeDamageTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMeleeDamage"));
            labelMeleeDamageValueUpgrade.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamage));
            labelMeleeDamageUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMeleeDamageUnit"));
            labelMeleeDamageLevelTitleUpgrade.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelLevelTile") + " ");
            labelMeleeDamageLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel + "");
            labelMeleeDamageMaxLevelUpgrade.setText(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageMaxLevel + "");
            if (game.gameWorld.weaponManagerPlayer.getWeaponData(this.checkedWeaponID).meleeDamageLevel >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageMaxLevel) {
                labelMeleeDamageUpgradeUpgrade.setText("");
                labelMeleeDamageUpgradeUnitUpgrade.setText("");
                labelMeleeDamagePriceUpgrade.setText("");
                labelMeleeDamagePriceUnitUpgrade.setText("");
                progressBarMeleeDamageValueBought.setVisible(false);
                progressBarMeleeDamageUpgradeBought.setVisible(false);
                progressBarMeleeDamageUpgradeDone.setVisible(true);
                progressBarMeleeDamageUpgradeDone.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamage);
                buttonUpgradeMeleeDamage.setVisible(false);
                imageUpgradeMeleeDamageDone.setVisible(true);
            } else {
                labelMeleeDamageUpgradeUpgrade.setText(game.styles.getFormattedIntWithSign(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel).upgrade));
                labelMeleeDamageUpgradeUnitUpgrade.setText(" " + game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelMeleeDamageUnit"));
                labelMeleeDamagePriceUpgrade.setText(game.styles.getFormattedIntMoney(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel).price));
                labelMeleeDamagePriceUnitUpgrade.setText(" \u20AC");
                progressBarMeleeDamageValueBought.setVisible(true);
                progressBarMeleeDamageUpgradeBought.setVisible(true);
                progressBarMeleeDamageUpgradeDone.setVisible(false);
                progressBarMeleeDamageValueBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamage);
                progressBarMeleeDamageUpgradeBought.setValue(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamage + game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel).upgrade);
                buttonUpgradeMeleeDamage.setVisible(true);
                if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel).price) {
                    buttonUpgradeMeleeDamage.setDisabled(false);
                } else {
                    buttonUpgradeMeleeDamage.setDisabled(true);
                }
                imageUpgradeMeleeDamageDone.setVisible(false);
            }
        }
    }

    private void initializeHud() {
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
        labelRateOfFireLevelTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelRateOfFireLevel = new Label("", game.styles.labelStyleBlueSmall);
        labelRateOfFireMaxLevel = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoDamageValue = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoDamageUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageLevelTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoDamageLevel = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoDamageMaxLevel = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoSpeedValue = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoSpeedUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedLevelTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoSpeedLevel = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoSpeedMaxLevel = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelMagazineSizeValue = new Label("", game.styles.labelStyleBlueSmall);
        labelMagazineSizeUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeLevelTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelMagazineSizeLevel = new Label("", game.styles.labelStyleBlueSmall);
        labelMagazineSizeMaxLevel = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelReloadTimeValue = new Label("", game.styles.labelStyleBlueSmall);
        labelReloadTimeUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeLevelTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelReloadTimeLevel = new Label("", game.styles.labelStyleBlueSmall);
        labelReloadTimeMaxLevel = new Label("", game.styles.labelStyleWhiteSmall);
        labelWeightTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelWeightValue = new Label("", game.styles.labelStyleBlueSmall);
        labelWeightUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelWeightLevelTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelWeightLevel = new Label("", game.styles.labelStyleBlueSmall);
        labelWeightMaxLevel = new Label("", game.styles.labelStyleWhiteSmall);
        labelMeleeDamageTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelMeleeDamageValue = new Label("", game.styles.labelStyleBlueSmall);
        labelMeleeDamageUnit = new Label("", game.styles.labelStyleWhiteSmall);
        labelMeleeDamageLevelTitle = new Label("", game.styles.labelStyleWhiteTiny);
        labelMeleeDamageLevel = new Label("", game.styles.labelStyleBlueSmall);
        labelMeleeDamageMaxLevel = new Label("", game.styles.labelStyleWhiteSmall);
        progressBarRateOfFire = new ProgressBar(game.gameWorld.weaponManagerPlayer.minRateOfFire, game.gameWorld.weaponManagerPlayer.maxRateOfFire, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarAmmoDamage = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoDamage, game.gameWorld.weaponManagerPlayer.maxAmmoDamage, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarAmmoSpeed = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoSpeed, game.gameWorld.weaponManagerPlayer.maxAmmoSpeed, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarMagazineSize = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMagazineSize, game.gameWorld.weaponManagerPlayer.maxMagazineSize, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarReloadTime = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxReloadTime, -game.gameWorld.weaponManagerPlayer.minReloadTime, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarWeight = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxWeight, -game.gameWorld.weaponManagerPlayer.minWeight, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarMeleeDamage = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMeleeDamage, game.gameWorld.weaponManagerPlayer.maxMeleeDamage, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        labelRateOfFireValue.setAlignment(Align.topRight);
        labelAmmoDamageValue.setAlignment(Align.topRight);
        labelAmmoSpeedValue.setAlignment(Align.topRight);
        labelMagazineSizeValue.setAlignment(Align.topRight);
        labelReloadTimeValue.setAlignment(Align.topRight);
        labelWeightValue.setAlignment(Align.topRight);
        labelMeleeDamageValue.setAlignment(Align.topRight);

        labelWeaponNameUpgrade = new Label("", game.styles.labelStyleWhiteMedium);
        labelYourMoneyTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelYourMoneyUpgrade = new Label("", game.styles.labelStyleWhiteMedium);
        labelRateOfFireTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelRateOfFireValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelRateOfFireUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelRateOfFireLevelTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelRateOfFireLevelUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelRateOfFireMaxLevelUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelRateOfFireUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelRateOfFireUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelRateOfFirePriceUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelRateOfFirePriceUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoDamageValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoDamageUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageLevelTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoDamageLevelUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoDamageMaxLevelUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamageUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelAmmoDamageUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoDamagePriceUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelAmmoDamagePriceUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoSpeedValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoSpeedUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedLevelTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelAmmoSpeedLevelUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelAmmoSpeedMaxLevelUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelAmmoSpeedUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelAmmoSpeedPriceUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelAmmoSpeedPriceUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelMagazineSizeValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelMagazineSizeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeLevelTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelMagazineSizeLevelUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelMagazineSizeMaxLevelUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizeUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelMagazineSizeUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMagazineSizePriceUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelMagazineSizePriceUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelReloadTimeValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelReloadTimeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeLevelTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelReloadTimeLevelUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelReloadTimeMaxLevelUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimeUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelReloadTimeUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelReloadTimePriceUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelReloadTimePriceUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelWeightTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelWeightValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelWeightUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelWeightLevelTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelWeightLevelUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelWeightMaxLevelUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelWeightUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelWeightUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelWeightPriceUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelWeightPriceUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMeleeDamageTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelMeleeDamageValueUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelMeleeDamageUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMeleeDamageLevelTitleUpgrade = new Label("", game.styles.labelStyleWhiteTiny);
        labelMeleeDamageLevelUpgrade = new Label("", game.styles.labelStyleBlueSmall);
        labelMeleeDamageMaxLevelUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMeleeDamageUpgradeUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelMeleeDamageUpgradeUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
        labelMeleeDamagePriceUpgrade = new Label("", game.styles.labelStyleGreenSmall);
        labelMeleeDamagePriceUnitUpgrade = new Label("", game.styles.labelStyleWhiteSmall);
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
        progressBarWeightValueBought = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxWeight, -game.gameWorld.weaponManagerPlayer.minWeight, 0.01f, false, game.styles.progressBarStyleValueBought);
        progressBarWeightUpgradeBought = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxWeight, -game.gameWorld.weaponManagerPlayer.minWeight, 0.01f, false, game.styles.progressBarStyleUpgrade);
        progressBarMeleeDamageValueBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMeleeDamage, game.gameWorld.weaponManagerPlayer.maxMeleeDamage, 0.01f, false, game.styles.progressBarStyleValueBought);
        progressBarMeleeDamageUpgradeBought = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMeleeDamage, game.gameWorld.weaponManagerPlayer.maxMeleeDamage, 0.01f, false, game.styles.progressBarStyleUpgrade);
        labelRateOfFireValueUpgrade.setAlignment(Align.topRight);
        labelRateOfFireUpgradeUpgrade.setAlignment(Align.topRight);
        labelRateOfFirePriceUpgrade.setAlignment(Align.topRight);
        labelAmmoDamageValueUpgrade.setAlignment(Align.topRight);
        labelAmmoDamageUpgradeUpgrade.setAlignment(Align.topRight);
        labelAmmoDamagePriceUpgrade.setAlignment(Align.topRight);
        labelAmmoSpeedValueUpgrade.setAlignment(Align.topRight);
        labelAmmoSpeedUpgradeUpgrade.setAlignment(Align.topRight);
        labelAmmoSpeedPriceUpgrade.setAlignment(Align.topRight);
        labelMagazineSizeValueUpgrade.setAlignment(Align.topRight);
        labelMagazineSizeUpgradeUpgrade.setAlignment(Align.topRight);
        labelMagazineSizePriceUpgrade.setAlignment(Align.topRight);
        labelReloadTimeValueUpgrade.setAlignment(Align.topRight);
        labelReloadTimeUpgradeUpgrade.setAlignment(Align.topRight);
        labelReloadTimePriceUpgrade.setAlignment(Align.topRight);
        labelWeightValueUpgrade.setAlignment(Align.topRight);
        labelWeightUpgradeUpgrade.setAlignment(Align.topRight);
        labelWeightPriceUpgrade.setAlignment(Align.topRight);
        labelMeleeDamageValueUpgrade.setAlignment(Align.topRight);
        labelMeleeDamageUpgradeUpgrade.setAlignment(Align.topRight);
        labelMeleeDamagePriceUpgrade.setAlignment(Align.topRight);

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

        buttonUpgradeRateOfFire = new ImageButton(game.styles.imageButtonStylePlus);
        buttonUpgradeAmmoDamage = new ImageButton(game.styles.imageButtonStylePlus);
        buttonUpgradeAmmoSpeed = new ImageButton(game.styles.imageButtonStylePlus);
        buttonUpgradeMagazineSize = new ImageButton(game.styles.imageButtonStylePlus);
        buttonUpgradeReloadTime = new ImageButton(game.styles.imageButtonStylePlus);
        buttonUpgradeWeight = new ImageButton(game.styles.imageButtonStylePlus);
        buttonUpgradeMeleeDamage = new ImageButton(game.styles.imageButtonStylePlus);

        buttonUpgradeRateOfFire.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireMaxLevel) {
                        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel).price) {
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFire += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel).upgrade;
                            game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel).price;
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).rateOfFireLevel++;
                            updateWeaponUpgradeTable();
                            updateHudData();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });
        buttonUpgradeAmmoDamage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageMaxLevel) {
                        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel).price) {
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamage += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel).upgrade;
                            game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel).price;
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoDamageLevel++;
                            updateWeaponUpgradeTable();
                            updateHudData();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });
        buttonUpgradeAmmoSpeed.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedMaxLevel) {
                        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel).price) {
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeed += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel).upgrade;
                            game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel).price;
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoSpeedLevel++;
                            updateWeaponUpgradeTable();
                            updateHudData();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });
        buttonUpgradeMagazineSize.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeMaxLevel) {
                        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel).price) {
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSize += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel).upgrade;
                            game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel).price;
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).magazineSizeLevel++;
                            updateWeaponUpgradeTable();
                            updateHudData();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });
        buttonUpgradeReloadTime.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeMaxLevel) {
                        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel).price) {
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTime += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel).upgrade;
                            game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel).price;
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).reloadTimeLevel++;
                            updateWeaponUpgradeTable();
                            updateHudData();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });
        buttonUpgradeWeight.addListener(new InputListener() {
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
                            updateWeaponUpgradeTable();
                            updateHudData();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });
        buttonUpgradeMeleeDamage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageMaxLevel) {
                        if (game.gameWorld.player.money >= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel).price) {
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamage += game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel).upgrade;
                            game.gameWorld.player.money -= game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageUpgrades.get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel).price;
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).meleeDamageLevel++;
                            updateWeaponUpgradeTable();
                            updateHudData();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                }
            }
        });

        progressBarRateOfFireUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minRateOfFire, game.gameWorld.weaponManagerPlayer.maxRateOfFire, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarAmmoDamageUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoDamage, game.gameWorld.weaponManagerPlayer.maxAmmoDamage, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarAmmoSpeedUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoSpeed, game.gameWorld.weaponManagerPlayer.maxAmmoSpeed, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarMagazineSizeUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMagazineSize, game.gameWorld.weaponManagerPlayer.maxMagazineSize, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarReloadTimeUpgradeDone = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxReloadTime, -game.gameWorld.weaponManagerPlayer.minReloadTime, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarWeightUpgradeDone = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxWeight, -game.gameWorld.weaponManagerPlayer.minWeight, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarMeleeDamageUpgradeDone = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMeleeDamage, game.gameWorld.weaponManagerPlayer.maxMeleeDamage, 0.01f, false, game.styles.progressBarStyleUpgradeDone);
        progressBarRateOfFireUpgradeDone.setVisible(false);
        progressBarAmmoDamageUpgradeDone.setVisible(false);
        progressBarAmmoSpeedUpgradeDone.setVisible(false);
        progressBarMagazineSizeUpgradeDone.setVisible(false);
        progressBarReloadTimeUpgradeDone.setVisible(false);
        progressBarWeightUpgradeDone.setVisible(false);
        progressBarMeleeDamageUpgradeDone.setVisible(false);
        imageUpgradeRateOfFireDone = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECK_MARK)));
        imageUpgradeAmmoDamageDone = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECK_MARK)));
        imageUpgradeAmmoSpeedDone = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECK_MARK)));
        imageUpgradeMagazineSizeDone = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECK_MARK)));
        imageUpgradeReloadTimeDone = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECK_MARK)));
        imageUpgradeWeightDone = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECK_MARK)));
        imageUpgradeMeleeDamageDone = new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_CHECK_MARK)));
        imageUpgradeRateOfFireDone.setVisible(false);
        imageUpgradeAmmoDamageDone.setVisible(false);
        imageUpgradeAmmoSpeedDone.setVisible(false);
        imageUpgradeMagazineSizeDone.setVisible(false);
        imageUpgradeReloadTimeDone.setVisible(false);
        imageUpgradeWeightDone.setVisible(false);
        imageUpgradeMeleeDamageDone.setVisible(false);

        tableWeaponInfoBox = createBasicInfoBox();

        tableRateOfFire = createRow(labelRateOfFireTitle, labelRateOfFireValue, labelRateOfFireUnit, labelRateOfFireLevelTitle, labelRateOfFireLevel, labelRateOfFireMaxLevel, progressBarRateOfFire);
        tableAmmoDamage = createRow(labelAmmoDamageTitle, labelAmmoDamageValue, labelAmmoDamageUnit, labelAmmoDamageLevelTitle, labelAmmoDamageLevel, labelAmmoDamageMaxLevel, progressBarAmmoDamage);
        tableAmmoSpeed = createRow(labelAmmoSpeedTitle, labelAmmoSpeedValue, labelAmmoSpeedUnit, labelAmmoSpeedLevelTitle, labelAmmoSpeedLevel, labelAmmoSpeedMaxLevel, progressBarAmmoSpeed);
        tableMagazineSize = createRow(labelMagazineSizeTitle, labelMagazineSizeValue, labelMagazineSizeUnit, labelMagazineSizeLevelTitle, labelMagazineSizeLevel, labelMagazineSizeMaxLevel, progressBarMagazineSize);
        tableReloadTime = createRow(labelReloadTimeTitle, labelReloadTimeValue, labelReloadTimeUnit, labelReloadTimeLevelTitle, labelReloadTimeLevel, labelReloadTimeMaxLevel, progressBarReloadTime);
        tableWeight = createRow(labelWeightTitle, labelWeightValue, labelWeightUnit, labelWeightLevelTitle, labelWeightLevel, labelWeightMaxLevel, progressBarWeight);
        tableMeleeDamage = createRow(labelMeleeDamageTitle, labelMeleeDamageValue, labelMeleeDamageUnit, labelMeleeDamageLevelTitle, labelMeleeDamageLevel, labelMeleeDamageMaxLevel, progressBarMeleeDamage);

        tableRateOfFireUpgrade = createRowUpgrade(labelRateOfFireTitleUpgrade, labelRateOfFireValueUpgrade, labelRateOfFireUnitUpgrade, labelRateOfFireLevelTitleUpgrade, labelRateOfFireLevelUpgrade, labelRateOfFireMaxLevelUpgrade, labelRateOfFireUpgradeUpgrade, labelRateOfFireUpgradeUnitUpgrade, false, labelRateOfFirePriceUpgrade, labelRateOfFirePriceUnitUpgrade, progressBarRateOfFireValueBought, progressBarRateOfFireUpgradeBought, progressBarRateOfFireUpgradeDone, buttonUpgradeRateOfFire, imageUpgradeRateOfFireDone);
        tableAmmoDamageUpgrade = createRowUpgrade(labelAmmoDamageTitleUpgrade, labelAmmoDamageValueUpgrade, labelAmmoDamageUnitUpgrade, labelAmmoDamageLevelTitleUpgrade, labelAmmoDamageLevelUpgrade, labelAmmoDamageMaxLevelUpgrade, labelAmmoDamageUpgradeUpgrade, labelAmmoDamageUpgradeUnitUpgrade, false, labelAmmoDamagePriceUpgrade, labelAmmoDamagePriceUnitUpgrade, progressBarAmmoDamageValueBought, progressBarAmmoDamageUpgradeBought, progressBarAmmoDamageUpgradeDone, buttonUpgradeAmmoDamage, imageUpgradeAmmoDamageDone);
        tableAmmoSpeedUpgrade = createRowUpgrade(labelAmmoSpeedTitleUpgrade, labelAmmoSpeedValueUpgrade, labelAmmoSpeedUnitUpgrade, labelAmmoSpeedLevelTitleUpgrade, labelAmmoSpeedLevelUpgrade, labelAmmoSpeedMaxLevelUpgrade, labelAmmoSpeedUpgradeUpgrade, labelAmmoSpeedUpgradeUnitUpgrade, false, labelAmmoSpeedPriceUpgrade, labelAmmoSpeedPriceUnitUpgrade, progressBarAmmoSpeedValueBought, progressBarAmmoSpeedUpgradeBought, progressBarAmmoSpeedUpgradeDone, buttonUpgradeAmmoSpeed, imageUpgradeAmmoSpeedDone);
        tableMagazineSizeUpgrade = createRowUpgrade(labelMagazineSizeTitleUpgrade, labelMagazineSizeValueUpgrade, labelMagazineSizeUnitUpgrade, labelMagazineSizeLevelTitleUpgrade, labelMagazineSizeLevelUpgrade, labelMagazineSizeMaxLevelUpgrade, labelMagazineSizeUpgradeUpgrade, labelMagazineSizeUpgradeUnitUpgrade, false, labelMagazineSizePriceUpgrade, labelMagazineSizePriceUnitUpgrade, progressBarMagazineSizeValueBought, progressBarMagazineSizeUpgradeBought, progressBarMagazineSizeUpgradeDone, buttonUpgradeMagazineSize, imageUpgradeMagazineSizeDone);
        tableReloadTimeUpgrade = createRowUpgrade(labelReloadTimeTitleUpgrade, labelReloadTimeValueUpgrade, labelReloadTimeUnitUpgrade, labelReloadTimeLevelTitleUpgrade, labelReloadTimeLevelUpgrade, labelReloadTimeMaxLevelUpgrade, labelReloadTimeUpgradeUpgrade, labelReloadTimeUpgradeUnitUpgrade, true, labelReloadTimePriceUpgrade, labelReloadTimePriceUnitUpgrade, progressBarReloadTimeValueBought, progressBarReloadTimeUpgradeBought, progressBarReloadTimeUpgradeDone, buttonUpgradeReloadTime, imageUpgradeReloadTimeDone);
        tableWeightUpgrade = createRowUpgrade(labelWeightTitleUpgrade, labelWeightValueUpgrade, labelWeightUnitUpgrade, labelWeightLevelTitleUpgrade, labelWeightLevelUpgrade, labelWeightMaxLevelUpgrade, labelWeightUpgradeUpgrade, labelWeightUpgradeUnitUpgrade, true, labelWeightPriceUpgrade, labelWeightPriceUnitUpgrade, progressBarWeightValueBought, progressBarWeightUpgradeBought, progressBarWeightUpgradeDone, buttonUpgradeWeight, imageUpgradeWeightDone);
        tableMeleeDamageUpgrade = createRowUpgrade(labelMeleeDamageTitleUpgrade, labelMeleeDamageValueUpgrade, labelMeleeDamageUnitUpgrade, labelMeleeDamageLevelTitleUpgrade, labelMeleeDamageLevelUpgrade, labelMeleeDamageMaxLevelUpgrade, labelMeleeDamageUpgradeUpgrade, labelMeleeDamageUpgradeUnitUpgrade, false, labelMeleeDamagePriceUpgrade, labelMeleeDamagePriceUnitUpgrade, progressBarMeleeDamageValueBought, progressBarMeleeDamageUpgradeBought, progressBarMeleeDamageUpgradeDone, buttonUpgradeMeleeDamage, imageUpgradeMeleeDamageDone);

        weaponButtons = new Array<TableWithID>();
        weaponButtonStacks = new Array<StackWithID>();
        Table tableWeaponButtons = new Table();
        for (int i = 0; i < Constants.NUMBER_OF_WEAPONS; i++) {
            final TableWithID tableWeaponButton = new TableWithID(WEAPONS_IDS[i]);
            tableWeaponButton.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
            tableWeaponButton.setTouchable(Touchable.enabled);
            StackWithID stack = new StackWithID(WEAPONS_IDS[i]);
            stack.add(new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_UP))));
            tableWeaponButton.add(stack).width(Constants.IMAGE_BUTTON_SIZE_HUGE).height(Constants.IMAGE_BUTTON_SIZE_HUGE).align(Align.center);
            weaponButtonStacks.add(stack);
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
                                weaponButtons.get(k).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_UI_ELEM_BG_OR_UP_OR_OFF)));
                            }
                        }
                        checkedWeaponID = tableWeaponButton.ID;
                        for (int k = 0; k < weaponButtons.size; k++) {
                            if (weaponButtons.get(k).ID == checkedWeaponID) {
                                weaponButtons.get(k).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D)));
                            }
                        }
                        updateHudData();
                    }
                }
            });
            weaponButtons.add(tableWeaponButton);
            tableWeaponButtons.add(tableWeaponButton).width(Constants.IMAGE_BUTTON_SIZE_HUGE).height(Constants.IMAGE_BUTTON_SIZE_HUGE).padRight(Constants.GAP).padBottom(i < Constants.UI_WEAPONS_IN_FULL_ROW ? Constants.GAP : 0);
            if ((i + 1) % Constants.UI_WEAPONS_IN_ROW == 0) {
                tableWeaponButtons.row();
            }
        }

        ScrollPane scrollPaneWeapons = new ScrollPane(tableWeaponButtons, game.styles.scrollPaneStyle);
        scrollPaneWeapons.setScrollingDisabled(true, false);
        scrollPaneWeapons.setForceScroll(false, true);
        scrollPaneWeapons.setupOverscroll(Constants.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeapons.setFadeScrollBars(false);
        scrollPaneWeapons.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeapons.updateVisualScroll();
        scrollPaneWeapons.layout();

        checkedWeaponID = weaponButtons.get(0).ID;
        weaponButtons.get(0).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_D)));
        for (int i = 0; i < Constants.NUMBER_OF_WEAPONS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_WEAPONS; j++) {
                if (weaponButtonStacks.get(j).ID == WEAPONS_IDS[i]) {
                    if (game.gameWorld.weaponManagerPlayer.getWeaponData(WEAPONS_IDS[i]).bought) {
                        Table table = new Table();
                        table.add(new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BOUGHT_ICON)))).align(Align.topLeft).expand().width(Constants.BOUGHT_ICON_SIZE).height(Constants.BOUGHT_ICON_SIZE).pad(Constants.GAP);
                        weaponButtonStacks.get(j).add(table);
                    }
                }
            }
        }

        Table tableWeapons = new Table();
        tableWeapons.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableWeapons.add(scrollPaneWeapons).growY().pad(Constants.GAP);

        labelScreenTitle = new Label("", game.styles.labelStyleWhiteHuge);
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
        super.add(tableWeapons).growY().align(Align.left).width(Constants.UI_WEAPONS_IN_ROW * (Constants.IMAGE_BUTTON_SIZE_HUGE + Constants.GAP) + 2 * Constants.GAP + Constants.SCROLL_PANE_SCROLL_SIZE);
        super.add(tableWeapon).width(Constants.WEAPON_TABLE_WIDTH_VALUE).align(Align.right).growY();

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
        tableRows.add(labelFireTypeTitle).align(Align.bottomLeft).width(Constants.WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH).height(uselessLabel.getHeight()).padLeft(Constants.GAP).padTop(Constants.GAP);
        tableRows.add(tableDataRow1).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_DATA_TABLE_WIDTH).padRight(Constants.GAP).padTop(Constants.GAP);
        tableRows.row();
        tableRows.add(labelPointsTitle).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH).height(uselessLabel.getHeight()).padLeft(Constants.GAP);
        tableRows.add(tableDataRow2).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_DATA_TABLE_WIDTH).padRight(Constants.GAP);
        tableRows.row();
        tableRows.add(labelAmmoPriceTitle).align(Align.left).width(Constants.WEAPON_BASIC_INFO_TITLE_TABLE_WIDTH).height(uselessLabel.getHeight()).padLeft(Constants.GAP).padBottom(Constants.GAP);
        tableRows.add(tableDataRow3).align(Align.topLeft).width(Constants.WEAPON_BASIC_INFO_DATA_TABLE_WIDTH).padRight(Constants.GAP).padBottom(Constants.GAP);
        return tableRows;
    }

    private Table createRow(Label title, Label value, Label unit, Label levelTitle, Label level, Label maxLevel, ProgressBar valueProgress) {
        Table tableData = new Table();
        tableData.add(title).width(Constants.WEAPON_TITLE_WIDTH).align(Align.left);
        tableData.add(value).width(Constants.WEAPON_VALUE_WIDTH).align(Align.topLeft);
        tableData.add(unit).width(Constants.WEAPON_UNIT_WIDTH).align(Align.topLeft);
        Table tableLevel = new Table();
        tableLevel.add(levelTitle).align(Align.right).expandX();
        tableLevel.add(level).align(Align.topRight);
        tableLevel.add(new Label("/", game.styles.labelStyleWhiteSmall)).align(Align.topRight);
        tableLevel.add(maxLevel).align(Align.topRight);
        tableData.add(tableLevel).width(Constants.WEAPON_LEVEL_WIDTH).align(Align.topLeft);
        tableData.row();
        tableData.add(valueProgress).colspan(5).width(Constants.WEAPON_BAR_WIDTH_VALUE).height(Constants.PROGRESS_BAR_DATA_HEIGHT).expandY().align(Align.bottomLeft);
        Table tableRow = new Table();
        tableRow.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRow.add(tableData).growY().pad(Constants.GAP);
        return tableRow;
    }

    private Table createRowUpgrade(Label title, Label value, Label unit, Label levelTitle, Label level, Label maxLevel, Label upgrade, Label unitUpgrade, boolean negativeUpgrade, Label price, Label priceUnit, ProgressBar valueProgress, ProgressBar upgradeProgress, ProgressBar doneProgress, ImageButton buttonUpgrade, Image imageDone) {
        Stack stackUpgrade = new Stack();
        stackUpgrade.add(buttonUpgrade);
        stackUpgrade.add(imageDone);
        Table tableData = new Table();
        tableData.add(title).width(Constants.WEAPON_TITLE_WIDTH_UPGRADE).align(Align.left);
        tableData.add(value).width(Constants.WEAPON_VALUE_WIDTH_UPGRADE).align(Align.topLeft);
        tableData.add(unit).width(Constants.WEAPON_UNIT_WIDTH_UPGRADE).align(Align.topLeft);
        Table tableLevel = new Table();
        tableLevel.add(levelTitle).align(Align.right).expandX();
        tableLevel.add(level).align(Align.topRight);
        tableLevel.add(new Label("/", game.styles.labelStyleWhiteSmall)).align(Align.topRight);
        tableLevel.add(maxLevel).align(Align.topRight);
        tableData.add(tableLevel).width(Constants.WEAPON_LEVEL_WIDTH_UPGRADE).align(Align.topLeft);
        tableData.add(upgrade).width(Constants.WEAPON_UPGRADE_WIDTH_UPGRADE).align(Align.topLeft);
        tableData.add(unitUpgrade).width(Constants.WEAPON_UNIT_WIDTH_UPGRADE).align(Align.topLeft);
        Table tablePrice = new Table();
        tablePrice.add(price).expandX().align(Align.topRight);
        tablePrice.add(priceUnit).align(Align.topRight);
        tableData.add(tablePrice).width(Constants.WEAPON_PRICE_WIDTH_UPGRADE).align(Align.topLeft);
        Stack stack = new Stack();
        stack.add(upgradeProgress);
        stack.add(valueProgress);
        stack.add(doneProgress);
        tableData.row();
        tableData.add(stack).colspan(7).width(Constants.WEAPON_BAR_WIDTH_UPGRADE).height(Constants.PROGRESS_BAR_DATA_HEIGHT).expandY().align(Align.bottomLeft);
        Table tableRow = new Table();
        tableRow.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRow.add(tableData).growY().pad(Constants.GAP);
        tableRow.add(stackUpgrade).width(Constants.IMAGE_BUTTON_SIZE_TINY).height(Constants.IMAGE_BUTTON_SIZE_TINY).align(Align.left).padTop(Constants.GAP).padBottom(Constants.GAP).padRight(Constants.GAP);
        return tableRow;
    }

    private void createTableWeaponTopNotBought() {
        tableWeaponTopNotBought = new Table();
        tableWeaponTopNotBought.add(labelWeaponNameNotBought).pad(Constants.GAP).align(Align.left).growX();
    }

    private void createTableWeaponTopBought() {
        tableWeaponTopBought = new Table();
        tableWeaponTopBought.add(labelWeaponNameBought).pad(Constants.GAP).align(Align.left);
        tableWeaponTopBought.add(new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BOUGHT_ICON)))).align(Align.left).growX().width(Constants.BOUGHT_ICON_SIZE).height(Constants.BOUGHT_ICON_SIZE);
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
                        updateHudData();
                        for (int i = 0; i < weaponButtonStacks.size; i++) {
                            if (weaponButtonStacks.get(i).ID == checkedWeaponID) {
                                Table table = new Table();
                                table.add(new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BOUGHT_ICON)))).align(Align.topLeft).expand().width(Constants.BOUGHT_ICON_SIZE).height(Constants.BOUGHT_ICON_SIZE).pad(Constants.GAP);
                                weaponButtonStacks.get(i).add(table);
                            }
                        }
                        if (checkedWeaponID == Constants.KNIFE_ID) {
                            game.gameWorld.player.addWeapon(game.gamePools.knifePool.obtain(), game.gameWorld.weaponManagerPlayer.getWeaponData(Constants.KNIFE_ID), game.gameWorld.player, null, true);
                        } else if (checkedWeaponID == Constants.PISTOL_ID) {
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
                    for (int i = 0; i < game.gameScreen.stageExtra.getActors().size; i++) {
                        game.gameScreen.stageExtra.getActors().get(i).remove();
                    }
                    game.gameScreen.stageExtra.addActor(tableBuyAmmo);
                    game.gameScreen.setShowStageExtra(true);
                    updateBuyAmmoTable();
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
                    for (int i = 0; i < game.gameScreen.stageExtra.getActors().size; i++) {
                        game.gameScreen.stageExtra.getActors().get(i).remove();
                    }
                    game.gameScreen.stageExtra.addActor(tableWeaponUpgrade);
                    game.gameScreen.setShowStageExtra(true);
                    updateWeaponUpgradeTable();
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
                    updateHudData();
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

        Table tableBottom = new Table();
        tableBottom.add(labelYourMoneyTitleUpgrade).padRight(Constants.GAP).align(Align.left);
        tableBottom.add(labelYourMoneyUpgrade).align(Align.left).expandX();

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
                    updateHudData();
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
                    updateBuyAmmoTable();
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
                    updateBuyAmmoTable();
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
                updateBuyAmmoTable();
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
                updateBuyAmmoTable();
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
                            game.gameWorld.player.currentWeapon.updateWeaponWhenMoreAmmoIsGiven();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    } else if (sliderNumberOfAmmoBuyAmmo.isVisible()) {
                        if (game.gameWorld.player.money >= ((int) (sliderNumberOfAmmoBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice)) {
                            game.gameWorld.player.money -= ((int) (sliderNumberOfAmmoBuyAmmo.getValue()) * game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).ammoPrice);
                            game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo += ((int) (sliderNumberOfAmmoBuyAmmo.getValue()));
                            game.gameWorld.player.currentWeapon.updateWeaponWhenMoreAmmoIsGiven();
                        } else {
                            game.alertManager.showPopup(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                    updateBuyAmmoTable();
                    updateHudData();
                    game.gameScreen.inGameHud.updateHudData();
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
}