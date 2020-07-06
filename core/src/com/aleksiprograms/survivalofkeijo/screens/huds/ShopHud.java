package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponButton;
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
        initialize();
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
    public void updateData() {
        super.updateData();
        labelScreenTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("titleShop"));
        tableWeapon.clearChildren();
        tableWeaponRows.clearChildren();
        updateWeaponsTable();
        updateWeaponInfoBox();
        updateWeaponData();
        recreateTableWeapon();
        if (!(game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isBought())) {
            tableWeapon.add(tableWeaponTopNotBought).align(Align.left).growX();
            tableWeapon.row();
            tableWeapon.add(scrollPaneWeapon).align(Align.left).grow()
                    .padRight(Constants.GAP).padLeft(Constants.GAP);
            tableWeapon.row();
            tableWeapon.add(tableWeaponBottomNotBought).align(Align.left).growX();
        } else {
            tableWeapon.add(tableWeaponTopBought).align(Align.left).growX();
            tableWeapon.row();
            tableWeapon.add(scrollPaneWeapon).align(Align.left).grow()
                    .padRight(Constants.GAP).padLeft(Constants.GAP);
            tableWeapon.row();
            tableWeapon.add(tableWeaponBottomBought).align(Align.left).growX();
        }
        labelMoney.setText(game.getStyles().getFormattedFloatMoney(
                game.getGameWorld().getPlayer().getMoney()) + " \u20AC");
    }

    private void updateWeaponsTable() {
        for (int i = 0; i < Constants.NUMBER_OF_WEAPONS; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_WEAPONS; j++) {
                if (weaponButtons.get(j).weaponButtonID == WEAPONS_IDS[i]) {
                    if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            WEAPONS_IDS[i]).isBought()) {
                        weaponButtons.get(j).labelWeaponLevel.setText("lvl "
                                + game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                weaponButtons.get(j).weaponButtonID).getLevel());
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
        labelWeaponNameBuyAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get(game.getGameWorld()
                .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getNameID()));
        labelYourAmmoTitleBuyAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelYourAmmoBuyAmmo.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getNumberOfAmmo()));
        labelYourMoneyTitleBuyAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelYourMoneyTitle"));
        labelYourMoneyBuyAmmo.setText(game.getStyles().getFormattedFloatMoney(
                game.getGameWorld().getPlayer().getMoney()) + " \u20AC");
        buttonBuyAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonBuy"));
        labelMagsBuyAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelMags"));
        labelAmmoBuyAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelAmmo"));
        labelGuideBuyAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelBuyAmmoGuide"));
        if (sliderNumberOfMagsBuyAmmo.isVisible()) {
            labelNumberOfMagsOrAmmoTitleBuyAmmo.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelNumberOfMagsToBuyTitle"));
            labelNumberOfMagsOrAmmoBuyAmmo.setText(
                    (int) (sliderNumberOfMagsBuyAmmo.getValue()) + "");
            labelPriceBuyAmmo.setText(game.getStyles().getFormattedFloatMoney(
                    (int) (sliderNumberOfMagsBuyAmmo.getValue()) * game.getGameWorld()
                            .getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                            .getMagazineSize() * game.getGameWorld().getWeaponManagerPlayer()
                            .getWeaponData(checkedWeaponID).getAmmoPrice()) + " \u20AC");
            if (game.getGameWorld().getPlayer().getMoney() >= ((int) (sliderNumberOfMagsBuyAmmo
                    .getValue()) * game.getGameWorld().getWeaponManagerPlayer()
                    .getWeaponData(checkedWeaponID).getMagazineSize() * game.getGameWorld()
                    .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getAmmoPrice())) {
                buttonBuyAmmo.setDisabled(false);
            } else {
                buttonBuyAmmo.setDisabled(true);
            }
        } else if (sliderNumberOfAmmoBuyAmmo.isVisible()) {
            labelNumberOfMagsOrAmmoTitleBuyAmmo.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoToBuyTitle"));
            labelNumberOfMagsOrAmmoBuyAmmo.setText(
                    (int) (sliderNumberOfAmmoBuyAmmo.getValue()) + "");
            labelPriceBuyAmmo.setText(game.getStyles().getFormattedFloatMoney(
                    (int) (sliderNumberOfAmmoBuyAmmo.getValue()) * game.getGameWorld()
                            .getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                            .getAmmoPrice()) + " \u20AC");
            if (game.getGameWorld().getPlayer().getMoney() >= ((int) (sliderNumberOfAmmoBuyAmmo
                    .getValue()) * game.getGameWorld().getWeaponManagerPlayer()
                    .getWeaponData(checkedWeaponID).getAmmoPrice())) {
                buttonBuyAmmo.setDisabled(false);
            } else {
                buttonBuyAmmo.setDisabled(true);
            }
        }
    }

    private void recreateTableWeapon() {
        weaponNumberOfRows = 0;
        tableWeaponRows.add(tableWeaponInfoBox).padBottom(Constants.GAP).padRight(Constants.GAP)
                .height(3 * uselessLabel.getHeight() + 2 * Constants.GAP);
        tableWeaponRows.row();
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isRateOfFire()) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableRateOfFire).padBottom(
                    weaponNumberOfRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoDamage()) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableAmmoDamage).padBottom(
                    weaponNumberOfRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoSpeed()) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableAmmoSpeed).padBottom(
                    weaponNumberOfRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isMagazineSize()) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableMagazineSize).padBottom(
                    weaponNumberOfRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isReloadTime()) {
            weaponNumberOfRows++;
            tableWeaponRows.add(tableReloadTime).padBottom(
                    weaponNumberOfRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
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
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isRateOfFire()) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableRateOfFireUpgrade).padBottom(weaponNumberOfRowsUpgrade
                    < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                    .getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoDamage()) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableAmmoDamageUpgrade).padBottom(weaponNumberOfRowsUpgrade
                    < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoSpeed()) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableAmmoSpeedUpgrade).padBottom(weaponNumberOfRowsUpgrade
                    < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isMagazineSize()) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableMagazineSizeUpgrade).padBottom(weaponNumberOfRowsUpgrade
                    < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRowsUpgrade.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isReloadTime()) {
            weaponNumberOfRowsUpgrade++;
            tableWeaponRowsUpgrade.add(tableReloadTimeUpgrade).padBottom(weaponNumberOfRowsUpgrade
                    < game.getGameWorld().getWeaponManagerPlayer().getWeaponData
                    (checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
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
        labelFireTypeTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelFireTypeTitle"));
        labelPointsTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelPointsTitle"));
        labelPointsTitleHit.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelPointsTitleHit") + " ");
        labelPointsTitleKill.setText(" " + game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelPointsTitleKill") + " ");
        labelAmmoPriceTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelAmmoPriceTitle"));
        labelBasicInfoFireType.setText(game.getGameWorld().getWeaponManagerPlayer()
                .getWeaponData(checkedWeaponID).getFireType());
        labelBasicInfoPointsHit.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getPointsHit()));
        labelBasicInfoPointsKill.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getPointsKill()));
        labelBasicInfoAmmoPrice.setText(game.getStyles().getFormattedFloatMoney(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getAmmoPrice()));
        labelAmmoPriceUnit.setText(" \u20AC");
    }

    private void updateWeaponData() {
        labelWeaponNameNotBought.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get(game.getGameWorld()
                .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getNameID()));
        labelWeaponNameBought.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get(game.getGameWorld()
                .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getNameID()));
        labelWeaponPrice.setText(game.getStyles().getFormattedFloatMoney(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getPrice()) + " \u20AC");
        labelNumberOfAmmoTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelNumberOfAmmo.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getNumberOfAmmo()));
        buttonBuyWeapon.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonBuy"));
        buttonGetAmmo.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonGetAmmo"));
        buttonUpgrade.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonUpgrade"));
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()
                < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).getMAX_LEVEL()) {
            buttonUpgrade.setDisabled(false);
        } else {
            buttonUpgrade.setDisabled(true);
        }

        if (game.getGameWorld().getPlayer().getMoney()
                >= game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).getPrice()) {
            buttonBuyWeapon.setDisabled(false);
        } else {
            buttonBuyWeapon.setDisabled(true);
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isRateOfFire()) {
            labelRateOfFireTitle.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelRateOfFire"));
            labelRateOfFireValue.setText(game.getStyles().getFormattedFloat(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getRateOfFire()));
            labelRateOfFireUnit.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
            progressBarRateOfFire.setValue(game.getGameWorld().getWeaponManagerPlayer()
                    .getWeaponData(checkedWeaponID).getRateOfFire());
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoDamage()) {
            labelAmmoDamageTitle.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamage"));
            labelAmmoDamageValue.setText(game.getStyles().getFormattedInt
                    (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getAmmoDamage()));
            labelAmmoDamageUnit.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
            progressBarAmmoDamage.setValue(game.getGameWorld().getWeaponManagerPlayer()
                    .getWeaponData(checkedWeaponID).getAmmoDamage());
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoSpeed()) {
            labelAmmoSpeedTitle.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeed"));
            labelAmmoSpeedValue.setText(game.getStyles().getFormattedFloat(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getAmmoSpeed()));
            labelAmmoSpeedUnit.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
            progressBarAmmoSpeed.setValue(game.getGameWorld().getWeaponManagerPlayer()
                    .getWeaponData(checkedWeaponID).getAmmoSpeed());
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isMagazineSize()) {
            labelMagazineSizeTitle.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelMagazineSize"));
            labelMagazineSizeValue.setText(game.getStyles().getFormattedInt(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getMagazineSize()));
            labelMagazineSizeUnit.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
            progressBarMagazineSize.setValue(game.getGameWorld().getWeaponManagerPlayer()
                    .getWeaponData(checkedWeaponID).getMagazineSize());
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isReloadTime()) {
            labelReloadTimeTitle.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelReloadTime"));
            labelReloadTimeValue.setText(game.getStyles().getFormattedFloat(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getReloadTime()));
            labelReloadTimeUnit.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
            progressBarReloadTime.setValue(-game.getGameWorld().getWeaponManagerPlayer()
                    .getWeaponData(checkedWeaponID).getReloadTime());
        }
    }

    private void updateWeaponDataUpgrade() {
        labelWeaponNameUpgrade.setText(game.getAssetManager().get(
                Constants.BUNDLE,
                I18NBundle.class).get(game.getGameWorld().getWeaponManagerPlayer()
                .getWeaponData(checkedWeaponID).getNameID()));
        labelYourMoneyTitleUpgrade.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelYourMoneyTitle"));
        labelYourMoneyUpgrade.setText(game.getStyles().getFormattedFloatMoney(
                game.getGameWorld().getPlayer().getMoney()) + " \u20AC");
        labelNumberOfAmmoTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelNumberOfAmmo.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getNumberOfAmmo()));
        buttonBuyUpgrade.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("buttonBuy"));
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()
                < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).getMAX_LEVEL()) {
            buttonBuyUpgrade.setDisabled(false);
            labelPriceUpgrade.setText(game.getStyles().getFormattedFloatMoney(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getUpgradePrices().get(game.getGameWorld()
                            .getWeaponManagerPlayer().getWeaponData(
                                    checkedWeaponID).getLevel())) + " \u20AC");
        } else {
            buttonBuyUpgrade.setDisabled(true);
            labelPriceUpgrade.setText("");
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isRateOfFire()) {
            labelRateOfFireTitleUpgrade.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelRateOfFire"));
            labelRateOfFireValueUpgrade.setText(game.getStyles().getFormattedFloat(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getRateOfFire()));
            labelRateOfFireUnitUpgrade.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()
                    >= game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getMAX_LEVEL()) {
                labelRateOfFireUpgradeUpgrade.setText("");
                labelRateOfFireUpgradeUnitUpgrade.setText("");
                labelRateOfFireCompletedUpgrade.setText("COMPLETED123");
                progressBarRateOfFireValueBought.setVisible(false);
                progressBarRateOfFireUpgradeBought.setVisible(false);
                progressBarRateOfFireUpgradeDone.setVisible(true);
                progressBarRateOfFireUpgradeDone.setValue(game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getRateOfFire());
            } else {
                labelRateOfFireUpgradeUpgrade.setText(game.getStyles().getFormattedFloatWithSign(
                        game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                checkedWeaponID).getRateOfFireUpgrades().get(game.getGameWorld()
                                .getWeaponManagerPlayer().getWeaponData(
                                        checkedWeaponID).getLevel())));
                labelRateOfFireUpgradeUnitUpgrade.setText(" " + game.getAssetManager().get(
                        Constants.BUNDLE, I18NBundle.class).get("labelRateOfFireUnit"));
                labelRateOfFireCompletedUpgrade.setText("");
                progressBarRateOfFireValueBought.setVisible(true);
                progressBarRateOfFireUpgradeBought.setVisible(true);
                progressBarRateOfFireUpgradeDone.setVisible(false);
                progressBarRateOfFireValueBought.setValue(game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getRateOfFire());
                progressBarRateOfFireUpgradeBought.setValue(game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getRateOfFire()
                        + game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getRateOfFireUpgrades().get(game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()));
            }
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoDamage()) {
            labelAmmoDamageTitleUpgrade.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamage"));
            labelAmmoDamageValueUpgrade.setText(game.getStyles().getFormattedInt(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getAmmoDamage()));
            labelAmmoDamageUnitUpgrade.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()
                    >= game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getMAX_LEVEL()) {
                labelAmmoDamageUpgradeUpgrade.setText("");
                labelAmmoDamageUpgradeUnitUpgrade.setText("");
                labelAmmoDamageCompletedUpgrade.setText("COMPLETED123");
                progressBarAmmoDamageValueBought.setVisible(false);
                progressBarAmmoDamageUpgradeBought.setVisible(false);
                progressBarAmmoDamageUpgradeDone.setVisible(true);
                progressBarAmmoDamageUpgradeDone.setValue(game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getAmmoDamage());
            } else {
                labelAmmoDamageUpgradeUpgrade.setText(game.getStyles().getFormattedIntWithSign(
                        game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                                .getAmmoDamageUpgrades().get(game.getGameWorld().getWeaponManagerPlayer()
                                .getWeaponData(checkedWeaponID).getLevel())));
                labelAmmoDamageUpgradeUnitUpgrade.setText(" " + game.getAssetManager().get(
                        Constants.BUNDLE, I18NBundle.class).get("labelAmmoDamageUnit"));
                labelAmmoDamageCompletedUpgrade.setText("");
                progressBarAmmoDamageValueBought.setVisible(true);
                progressBarAmmoDamageUpgradeBought.setVisible(true);
                progressBarAmmoDamageUpgradeDone.setVisible(false);
                progressBarAmmoDamageValueBought.setValue(game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getAmmoDamage());
                progressBarAmmoDamageUpgradeBought.setValue(game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getAmmoDamage() + game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getAmmoDamageUpgrades()
                        .get(game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                checkedWeaponID).getLevel()));
            }
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoSpeed()) {
            labelAmmoSpeedTitleUpgrade.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeed"));
            labelAmmoSpeedValueUpgrade.setText(game.getStyles().getFormattedFloat(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getAmmoSpeed()));
            labelAmmoSpeedUnitUpgrade.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()
                    >= game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getMAX_LEVEL()) {
                labelAmmoSpeedUpgradeUpgrade.setText("");
                labelAmmoSpeedUpgradeUnitUpgrade.setText("");
                labelAmmoSpeedCompletedUpgrade.setText("COMPLETED123");
                progressBarAmmoSpeedValueBought.setVisible(false);
                progressBarAmmoSpeedUpgradeBought.setVisible(false);
                progressBarAmmoSpeedUpgradeDone.setVisible(true);
                progressBarAmmoSpeedUpgradeDone.setValue(game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getAmmoSpeed());
            } else {
                labelAmmoSpeedUpgradeUpgrade.setText(game.getStyles().getFormattedFloatWithSign(
                        game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                                .getAmmoSpeedUpgrades().get(game.getGameWorld()
                                .getWeaponManagerPlayer().getWeaponData(
                                        checkedWeaponID).getLevel())));
                labelAmmoSpeedUpgradeUnitUpgrade.setText(" " + game.getAssetManager().get(
                        Constants.BUNDLE, I18NBundle.class).get("labelAmmoSpeedUnit"));
                labelAmmoSpeedCompletedUpgrade.setText("");
                progressBarAmmoSpeedValueBought.setVisible(true);
                progressBarAmmoSpeedUpgradeBought.setVisible(true);
                progressBarAmmoSpeedUpgradeDone.setVisible(false);
                progressBarAmmoSpeedValueBought.setValue(game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getAmmoSpeed());
                progressBarAmmoSpeedUpgradeBought.setValue(game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getAmmoSpeed() + game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getAmmoSpeedUpgrades()
                        .get(game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                checkedWeaponID).getLevel()));
            }
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isMagazineSize()) {
            labelMagazineSizeTitleUpgrade.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelMagazineSize"));
            labelMagazineSizeValueUpgrade.setText(game.getStyles().getFormattedInt(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getMagazineSize()));
            labelMagazineSizeUnitUpgrade.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()
                    >= game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getMAX_LEVEL()) {
                labelMagazineSizeUpgradeUpgrade.setText("");
                labelMagazineSizeUpgradeUnitUpgrade.setText("");
                labelMagazineSizeCompletedUpgrade.setText("COMPLETED123");
                progressBarMagazineSizeValueBought.setVisible(false);
                progressBarMagazineSizeUpgradeBought.setVisible(false);
                progressBarMagazineSizeUpgradeDone.setVisible(true);
                progressBarMagazineSizeUpgradeDone.setValue(game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(
                                checkedWeaponID).getMagazineSize());
            } else {
                labelMagazineSizeUpgradeUpgrade.setText(game.getStyles().getFormattedIntWithSign(
                        game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                                .getMagazineSizeUpgrades().get(game.getGameWorld()
                                .getWeaponManagerPlayer().getWeaponData(
                                        checkedWeaponID).getLevel())));
                labelMagazineSizeUpgradeUnitUpgrade.setText(" " + game.getAssetManager().get(
                        Constants.BUNDLE, I18NBundle.class).get("labelMagazineSizeUnit"));
                labelMagazineSizeCompletedUpgrade.setText("");
                progressBarMagazineSizeValueBought.setVisible(true);
                progressBarMagazineSizeUpgradeBought.setVisible(true);
                progressBarMagazineSizeUpgradeDone.setVisible(false);
                progressBarMagazineSizeValueBought.setValue(game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getMagazineSize());
                progressBarMagazineSizeUpgradeBought.setValue(game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getMagazineSize() + game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getMagazineSizeUpgrades()
                        .get(game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                checkedWeaponID).getLevel()));
            }
        }

        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isReloadTime()) {
            labelReloadTimeTitleUpgrade.setText(game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelReloadTime"));
            labelReloadTimeValueUpgrade.setText(game.getStyles().getFormattedFloat(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getReloadTime()));
            labelReloadTimeUnitUpgrade.setText(" " + game.getAssetManager().get(
                    Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getLevel()
                    >= game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                    checkedWeaponID).getMAX_LEVEL()) {
                labelReloadTimeUpgradeUpgrade.setText("");
                labelReloadTimeUpgradeUnitUpgrade.setText("");
                labelReloadTimeCompletedUpgrade.setText("COMPLETED123");
                progressBarReloadTimeValueBought.setVisible(false);
                progressBarReloadTimeUpgradeBought.setVisible(false);
                progressBarReloadTimeUpgradeDone.setVisible(true);
                progressBarReloadTimeUpgradeDone.setValue(-game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getReloadTime());
            } else {
                labelReloadTimeUpgradeUpgrade.setText(game.getStyles().getFormattedFloatWithSign(
                        game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                                .getReloadTimeUpgrades().get(game.getGameWorld()
                                .getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                                .getLevel())));
                labelReloadTimeUpgradeUnitUpgrade.setText(" " + game.getAssetManager().get(
                        Constants.BUNDLE, I18NBundle.class).get("labelReloadTimeUnit"));
                labelReloadTimeCompletedUpgrade.setText("");
                progressBarReloadTimeValueBought.setVisible(true);
                progressBarReloadTimeUpgradeBought.setVisible(true);
                progressBarReloadTimeUpgradeDone.setVisible(false);
                progressBarReloadTimeValueBought.setValue(-game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getReloadTime());
                progressBarReloadTimeUpgradeBought.setValue(-game.getGameWorld().getWeaponManagerPlayer()
                        .getWeaponData(checkedWeaponID).getReloadTime() - game.getGameWorld()
                        .getWeaponManagerPlayer().getWeaponData(checkedWeaponID).getReloadTimeUpgrades()
                        .get(game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                checkedWeaponID).getLevel()));
            }
        }
    }

    @Override
    protected void initialize() {
        super.initialize();

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        uselessLabel = new Label("0", game.getStyles().getLabelStyleBlueSmall());

        labelBasicInfoFireType = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelBasicInfoAmmoPrice = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelBasicInfoPointsHit = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelBasicInfoPointsKill = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelAmmoPriceUnit = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelPointsTitleHit = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelPointsTitleKill = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelFireTypeTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelAmmoPriceTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelPointsTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());

        labelWeaponNameNotBought = new Label("", game.getStyles().getLabelStyleWhiteMedium());
        labelWeaponNameBought = new Label("", game.getStyles().getLabelStyleWhiteMedium());
        labelWeaponPrice = new Label("", game.getStyles().getLabelStyleWhiteMedium());
        labelNumberOfAmmoTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelNumberOfAmmo = new Label("", game.getStyles().getLabelStyleWhiteMedium());

        labelRateOfFireTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelRateOfFireValue = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelRateOfFireUnit = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoDamageTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelAmmoDamageValue = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelAmmoDamageUnit = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoSpeedTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelAmmoSpeedValue = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelAmmoSpeedUnit = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelMagazineSizeTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelMagazineSizeValue = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelMagazineSizeUnit = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        labelReloadTimeTitle = new Label("", game.getStyles().getLabelStyleWhiteTiny());
        labelReloadTimeValue = new Label("", game.getStyles().getLabelStyleBlueSmall());
        labelReloadTimeUnit = new Label("", game.getStyles().getLabelStyleWhiteSmall());
        progressBarRateOfFire = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinRateOfFire(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxRateOfFire(),
                0.01f, false, game.getStyles().getProgressBarStyleValueNotBought());
        progressBarAmmoDamage = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoDamage(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoDamage(),
                0.01f, false, game.getStyles().getProgressBarStyleValueNotBought());
        progressBarAmmoSpeed = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoSpeed(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoSpeed(),
                0.01f, false, game.getStyles().getProgressBarStyleValueNotBought());
        progressBarMagazineSize = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinMagazineSize(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxMagazineSize(),
                0.01f, false, game.getStyles().getProgressBarStyleValueNotBought());
        progressBarReloadTime = new ProgressBar(
                -game.getGameWorld().getWeaponManagerPlayer().getMaxReloadTime(),
                -game.getGameWorld().getWeaponManagerPlayer().getMinReloadTime(),
                0.01f, false, game.getStyles().getProgressBarStyleValueNotBought());
        labelRateOfFireValue.setAlignment(Align.topRight);
        labelAmmoDamageValue.setAlignment(Align.topRight);
        labelAmmoSpeedValue.setAlignment(Align.topRight);
        labelMagazineSizeValue.setAlignment(Align.topRight);
        labelReloadTimeValue.setAlignment(Align.topRight);

        labelWeaponNameUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());
        labelYourMoneyTitleUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelYourMoneyUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());
        labelPriceUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());
        labelRateOfFireTitleUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelRateOfFireValueUpgrade = new Label("",
                game.getStyles().getLabelStyleBlueSmall());
        labelRateOfFireUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelRateOfFireUpgradeUpgrade = new Label("",
                game.getStyles().getLabelStyleGreenSmall());
        labelRateOfFireUpgradeUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelRateOfFireCompletedUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoDamageTitleUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelAmmoDamageValueUpgrade = new Label("",
                game.getStyles().getLabelStyleBlueSmall());
        labelAmmoDamageUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoDamageUpgradeUpgrade = new Label("",
                game.getStyles().getLabelStyleGreenSmall());
        labelAmmoDamageUpgradeUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoDamageCompletedUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoSpeedTitleUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelAmmoSpeedValueUpgrade = new Label("",
                game.getStyles().getLabelStyleBlueSmall());
        labelAmmoSpeedUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoSpeedUpgradeUpgrade = new Label("",
                game.getStyles().getLabelStyleGreenSmall());
        labelAmmoSpeedUpgradeUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelAmmoSpeedCompletedUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelMagazineSizeTitleUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelMagazineSizeValueUpgrade = new Label("",
                game.getStyles().getLabelStyleBlueSmall());
        labelMagazineSizeUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelMagazineSizeUpgradeUpgrade = new Label("",
                game.getStyles().getLabelStyleGreenSmall());
        labelMagazineSizeUpgradeUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelMagazineSizeCompletedUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelReloadTimeTitleUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelReloadTimeValueUpgrade = new Label("",
                game.getStyles().getLabelStyleBlueSmall());
        labelReloadTimeUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelReloadTimeUpgradeUpgrade = new Label("",
                game.getStyles().getLabelStyleGreenSmall());
        labelReloadTimeUpgradeUnitUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelReloadTimeCompletedUpgrade = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        progressBarRateOfFireValueBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinRateOfFire(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxRateOfFire(),
                0.01f, false, game.getStyles().getProgressBarStyleValueBought());
        progressBarRateOfFireUpgradeBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinRateOfFire(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxRateOfFire(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgrade());
        progressBarAmmoDamageValueBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoDamage(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoDamage(),
                0.01f, false, game.getStyles().getProgressBarStyleValueBought());
        progressBarAmmoDamageUpgradeBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoDamage(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoDamage(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgrade());
        progressBarAmmoSpeedValueBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoSpeed(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoSpeed(),
                0.01f, false, game.getStyles().getProgressBarStyleValueBought());
        progressBarAmmoSpeedUpgradeBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoSpeed(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoSpeed(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgrade());
        progressBarMagazineSizeValueBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinMagazineSize(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxMagazineSize(),
                0.01f, false, game.getStyles().getProgressBarStyleValueBought());
        progressBarMagazineSizeUpgradeBought = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinMagazineSize(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxMagazineSize(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgrade());
        progressBarReloadTimeValueBought = new ProgressBar(
                -game.getGameWorld().getWeaponManagerPlayer().getMaxReloadTime(),
                -game.getGameWorld().getWeaponManagerPlayer().getMinReloadTime(),
                0.01f, false, game.getStyles().getProgressBarStyleValueBought());
        progressBarReloadTimeUpgradeBought = new ProgressBar(
                -game.getGameWorld().getWeaponManagerPlayer().getMaxReloadTime(),
                -game.getGameWorld().getWeaponManagerPlayer().getMinReloadTime(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgrade());
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

        labelWeaponNameBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());
        labelYourAmmoTitleBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelYourAmmoBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());
        labelNumberOfMagsOrAmmoBuyAmmo = new Label("1",
                game.getStyles().getLabelStyleWhiteMedium());
        labelGuideBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteSmall());
        labelMagsBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelAmmoBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelNumberOfMagsOrAmmoTitleBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelYourMoneyTitleBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteTiny());
        labelYourMoneyBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());
        labelPriceBuyAmmo = new Label("",
                game.getStyles().getLabelStyleWhiteMedium());

        progressBarRateOfFireUpgradeDone = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinRateOfFire(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxRateOfFire(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgradeDone());
        progressBarAmmoDamageUpgradeDone = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoDamage(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoDamage(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgradeDone());
        progressBarAmmoSpeedUpgradeDone = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinAmmoSpeed(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxAmmoSpeed(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgradeDone());
        progressBarMagazineSizeUpgradeDone = new ProgressBar(
                game.getGameWorld().getWeaponManagerPlayer().getMinMagazineSize(),
                game.getGameWorld().getWeaponManagerPlayer().getMaxMagazineSize(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgradeDone());
        progressBarReloadTimeUpgradeDone = new ProgressBar(
                -game.getGameWorld().getWeaponManagerPlayer().getMaxReloadTime(),
                -game.getGameWorld().getWeaponManagerPlayer().getMinReloadTime(),
                0.01f, false, game.getStyles().getProgressBarStyleUpgradeDone());
        progressBarRateOfFireUpgradeDone.setVisible(false);
        progressBarAmmoDamageUpgradeDone.setVisible(false);
        progressBarAmmoSpeedUpgradeDone.setVisible(false);
        progressBarMagazineSizeUpgradeDone.setVisible(false);
        progressBarReloadTimeUpgradeDone.setVisible(false);

        tableWeaponInfoBox = createBasicInfoBox();

        tableRateOfFire = createRow(
                labelRateOfFireTitle,
                labelRateOfFireValue,
                labelRateOfFireUnit,
                progressBarRateOfFire);
        tableAmmoDamage = createRow(
                labelAmmoDamageTitle,
                labelAmmoDamageValue,
                labelAmmoDamageUnit,
                progressBarAmmoDamage);
        tableAmmoSpeed = createRow(
                labelAmmoSpeedTitle,
                labelAmmoSpeedValue,
                labelAmmoSpeedUnit,
                progressBarAmmoSpeed);
        tableMagazineSize = createRow(
                labelMagazineSizeTitle,
                labelMagazineSizeValue,
                labelMagazineSizeUnit,
                progressBarMagazineSize);
        tableReloadTime = createRow(
                labelReloadTimeTitle,
                labelReloadTimeValue,
                labelReloadTimeUnit,
                progressBarReloadTime);

        tableRateOfFireUpgrade = createRowUpgrade(
                labelRateOfFireTitleUpgrade,
                labelRateOfFireValueUpgrade,
                labelRateOfFireUnitUpgrade,
                labelRateOfFireUpgradeUpgrade,
                labelRateOfFireUpgradeUnitUpgrade,
                labelRateOfFireCompletedUpgrade,
                progressBarRateOfFireValueBought,
                progressBarRateOfFireUpgradeBought,
                progressBarRateOfFireUpgradeDone);
        tableAmmoDamageUpgrade = createRowUpgrade(
                labelAmmoDamageTitleUpgrade,
                labelAmmoDamageValueUpgrade,
                labelAmmoDamageUnitUpgrade,
                labelAmmoDamageUpgradeUpgrade,
                labelAmmoDamageUpgradeUnitUpgrade,
                labelAmmoDamageCompletedUpgrade,
                progressBarAmmoDamageValueBought,
                progressBarAmmoDamageUpgradeBought,
                progressBarAmmoDamageUpgradeDone);
        tableAmmoSpeedUpgrade = createRowUpgrade(
                labelAmmoSpeedTitleUpgrade,
                labelAmmoSpeedValueUpgrade,
                labelAmmoSpeedUnitUpgrade,
                labelAmmoSpeedUpgradeUpgrade,
                labelAmmoSpeedUpgradeUnitUpgrade,
                labelAmmoSpeedCompletedUpgrade,
                progressBarAmmoSpeedValueBought,
                progressBarAmmoSpeedUpgradeBought,
                progressBarAmmoSpeedUpgradeDone);
        tableMagazineSizeUpgrade = createRowUpgrade(
                labelMagazineSizeTitleUpgrade,
                labelMagazineSizeValueUpgrade,
                labelMagazineSizeUnitUpgrade,
                labelMagazineSizeUpgradeUpgrade,
                labelMagazineSizeUpgradeUnitUpgrade,
                labelMagazineSizeCompletedUpgrade,
                progressBarMagazineSizeValueBought,
                progressBarMagazineSizeUpgradeBought,
                progressBarMagazineSizeUpgradeDone);
        tableReloadTimeUpgrade = createRowUpgrade(
                labelReloadTimeTitleUpgrade,
                labelReloadTimeValueUpgrade,
                labelReloadTimeUnitUpgrade,
                labelReloadTimeUpgradeUpgrade,
                labelReloadTimeUpgradeUnitUpgrade,
                labelReloadTimeCompletedUpgrade,
                progressBarReloadTimeValueBought,
                progressBarReloadTimeUpgradeBought,
                progressBarReloadTimeUpgradeDone);

        weaponButtons = new Array<WeaponButton>();
        Table tableWeaponButtons = new Table();
        for (int i = 0; i < Constants.NUMBER_OF_WEAPONS; i++) {
            final WeaponButton tableWeaponButton = new WeaponButton(WEAPONS_IDS[i]);
            tableWeaponButton.background(new NinePatchDrawable(game.getAssetManager().get(
                    Constants.TEXTURE_ATLAS,
                    TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
            tableWeaponButton.setTouchable(Touchable.enabled);
            Stack stack = new Stack();
            stack.add(new Image(new NinePatchDrawable(game.getAssetManager().get(
                    Constants.TEXTURE_ATLAS,
                    TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_UP))));
            tableWeaponButton.labelWeaponLevel = new Label("",
                    game.getStyles().getLabelStyleWhiteSmall());
            Table table = new Table();
            table.add(tableWeaponButton.labelWeaponLevel)
                    .align(Align.bottomRight).expand().pad(Constants.GAP);
            stack.add(table);
            tableWeaponButton.add(stack).align(Align.center)
                    .width(Constants.IMAGE_BUTTON_SIZE_HUGE)
                    .height(Constants.IMAGE_BUTTON_SIZE_HUGE);
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
                    if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_HUGE && y > 0
                            && y < Constants.IMAGE_BUTTON_SIZE_HUGE) {
                        for (int k = 0; k < weaponButtons.size; k++) {
                            if (weaponButtons.get(k).weaponButtonID == checkedWeaponID) {
                                weaponButtons.get(k).background(new NinePatchDrawable(game
                                        .getAssetManager()
                                        .get(Constants.TEXTURE_ATLAS, TextureAtlas.class)
                                        .createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
                            }
                        }
                        checkedWeaponID = tableWeaponButton.weaponButtonID;
                        for (int k = 0; k < weaponButtons.size; k++) {
                            if (weaponButtons.get(k).weaponButtonID == checkedWeaponID) {
                                weaponButtons.get(k).background(new NinePatchDrawable(game
                                        .getAssetManager()
                                        .get(Constants.TEXTURE_ATLAS, TextureAtlas.class)
                                        .createPatch(Constants.TEXTURE_BUTTON_DOWN)));
                            }
                        }
                        updateData();
                    }
                }
            });
            weaponButtons.add(tableWeaponButton);
            tableWeaponButtons.add(tableWeaponButton)
                    .width(Constants.IMAGE_BUTTON_SIZE_HUGE)
                    .height(Constants.IMAGE_BUTTON_SIZE_HUGE)
                    .padRight(Constants.GAP)
                    .padBottom(i < Constants.UI_WEAPONS_IN_FULL_ROW ? Constants.GAP : 0);
            if ((i + 1) % Constants.UI_WEAPONS_IN_ROW == 0) {
                tableWeaponButtons.row();
            }
        }

        checkedWeaponID = weaponButtons.get(0).weaponButtonID;
        weaponButtons.get(0).background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_DOWN)));

        scrollPaneWeapons = new ScrollPane(tableWeaponButtons,
                game.getStyles().getScrollPaneStyle());
        scrollPaneWeapons.setScrollingDisabled(true, false);
        scrollPaneWeapons.setForceScroll(false, true);
        scrollPaneWeapons.setupOverscroll(
                Constants.SCROLL_PANE_OVER_SCROLL,
                Constants.SCROLL_PANE_MIN_SPEED,
                Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeapons.setFadeScrollBars(false);
        scrollPaneWeapons.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeapons.updateVisualScroll();
        scrollPaneWeapons.layout();

        Table tableWeapons = new Table();
        tableWeapons.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableWeapons.add(scrollPaneWeapons).growY().pad(Constants.GAP);

        labelScreenTitle = new Label("", game.getStyles().getLabelStyleWhiteHugeWithBorder());
        labelMoney = new Label("", game.getStyles().getLabelStyleWhiteBig());
        final ImageButton btClose = new ImageButton(game.getStyles().getImageButtonStyleClose());
        Table tableTopRight = new Table();
        tableTopRight.add(labelMoney).align(Align.right).padRight(Constants.GAP * 2).expandX();
        tableTopRight.add(btClose).align(Align.right)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        Table tableTop = new Table();
        tableTop.add().align(Align.left)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL * 5)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);
        tableTop.add(labelScreenTitle).expandX().align(Align.center);
        tableTop.add(tableTopRight).align(Align.right)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL * 5)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL);

        tableWeapon = new Table();
        tableWeapon.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableWeaponRows = new Table();
        scrollPaneWeapon = new ScrollPane(tableWeaponRows,
                game.getStyles().getScrollPaneStyle());
        scrollPaneWeapon.setScrollingDisabled(true, false);
        scrollPaneWeapon.setForceScroll(false, true);
        scrollPaneWeapon.setupOverscroll(
                Constants.SCROLL_PANE_OVER_SCROLL,
                Constants.SCROLL_PANE_MIN_SPEED,
                Constants.SCROLL_PANE_MAX_SPEED);
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

        super.add(tableTop)
                .height(Constants.TABLE_TOP_HEIGHT)
                .growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        super.row();
        super.add(tableWeapons).growY().align(Align.left).width(
                Constants.UI_WEAPONS_IN_ROW
                        * (Constants.IMAGE_BUTTON_SIZE_HUGE + Constants.GAP)
                        + 2 * Constants.GAP + Constants.SCROLL_PANE_THICKNESS);
        super.add(tableWeapon).width(Constants.WEAPON_TABLE_WIDTH).align(Align.right).growY();

        InputListener inputListenerClose = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_SMALL
                        && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_SMALL) {
                    if (game.getGameScreen().getCurrentGameState().equals(GameState.IN_SHOP)) {
                        game.getGameScreen().getShopHud().saveScrollAmount();
                    }
                    game.getGameScreen().changeGameState(GameState.IN_GAME);
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
        tableRows.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRows.add(labelFireTypeTitle).align(Align.bottomLeft)
                .width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH)
                .height(uselessLabel.getHeight())
                .padLeft(Constants.GAP).padTop(Constants.GAP);
        tableRows.add(tableDataRow1).align(Align.topLeft).padTop(Constants.GAP)
                .width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH)
                .padRight(Constants.GAP);
        tableRows.row();
        tableRows.add(labelPointsTitle).align(Align.topLeft).padLeft(Constants.GAP)
                .width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH)
                .height(uselessLabel.getHeight());
        tableRows.add(tableDataRow2).align(Align.topLeft).padRight(Constants.GAP)
                .width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH);
        tableRows.row();
        tableRows.add(labelAmmoPriceTitle).align(Align.left)
                .width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH)
                .height(uselessLabel.getHeight())
                .padLeft(Constants.GAP).padBottom(Constants.GAP);
        tableRows.add(tableDataRow3).align(Align.topLeft)
                .width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH)
                .padRight(Constants.GAP).padBottom(Constants.GAP);
        return tableRows;
    }

    private Table createRow(Label title, Label value, Label unit, ProgressBar valueProgress) {
        Table tableData = new Table();
        tableData.add(title).width(Constants.WEAPON_TITLE_WIDTH).align(Align.left);
        tableData.add(value).width(Constants.WEAPON_VALUE_WIDTH).align(Align.topLeft);
        tableData.add(unit).width(Constants.WEAPON_UNIT_WIDTH).align(Align.topLeft);
        tableData.row();
        tableData.add(valueProgress).colspan(5)
                .width(Constants.WEAPON_BAR_WIDTH)
                .height(Constants.PROGRESS_BAR_DATA_THICKNESS)
                .expandY().align(Align.bottomLeft);
        Table tableRow = new Table();
        tableRow.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRow.add(tableData).growY().pad(Constants.GAP);
        return tableRow;
    }

    private Table createRowUpgrade(
            Label title, Label value, Label unit,
            Label upgrade, Label unitUpgrade, Label labelCompleted,
            ProgressBar valueProgress, ProgressBar upgradeProgress, ProgressBar doneProgress) {
        Table tableData = new Table();
        tableData.add(title).width(Constants.WEAPON_TITLE_WIDTH_UPGRADE).align(Align.left);
        tableData.add(value).width(Constants.WEAPON_VALUE_WIDTH_UPGRADE).align(Align.topLeft);
        tableData.add(unit).width(Constants.WEAPON_UNIT_WIDTH_UPGRADE).align(Align.topLeft);
        Table tableUpgrade = new Table();
        tableUpgrade.add(upgrade).align(Align.topLeft)
                .width(Constants.WEAPON_UPGRADE_WIDTH_UPGRADE);
        tableUpgrade.add(unitUpgrade).align(Align.topLeft)
                .width(Constants.WEAPON_UNIT_WIDTH_UPGRADE);
        Table tableCompleted = new Table();
        tableCompleted.add(labelCompleted).expandX().align(Align.right);
        Stack stackUpgrade = new Stack();
        stackUpgrade.add(tableUpgrade);
        stackUpgrade.add(tableCompleted);
        tableData.add(stackUpgrade).align(Align.topLeft)
                .width(Constants.WEAPON_UPGRADE_WIDTH_UPGRADE
                        + Constants.WEAPON_UNIT_WIDTH_UPGRADE);
        Stack stackBar = new Stack();
        stackBar.add(upgradeProgress);
        stackBar.add(valueProgress);
        stackBar.add(doneProgress);
        tableData.row();
        tableData.add(stackBar).colspan(7).expandY().align(Align.bottomLeft)
                .width(Constants.WEAPON_BAR_WIDTH_UPGRADE)
                .height(Constants.PROGRESS_BAR_DATA_THICKNESS);
        Table tableRow = new Table();
        tableRow.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRow.add(tableData).growY().pad(Constants.GAP);
        return tableRow;
    }

    private void createTableWeaponTopNotBought() {
        tableWeaponTopNotBought = new Table();
        tableWeaponTopNotBought.add(labelWeaponNameNotBought)
                .pad(Constants.GAP).align(Align.left).growX();
    }

    private void createTableWeaponTopBought() {
        tableWeaponTopBought = new Table();
        tableWeaponTopBought.add(labelWeaponNameBought)
                .pad(Constants.GAP).align(Align.left).expandX();
        tableWeaponTopBought.add(labelNumberOfAmmoTitle)
                .padTop(Constants.GAP).padBottom(Constants.GAP).align(Align.right);
        tableWeaponTopBought.add(labelNumberOfAmmo)
                .pad(Constants.GAP).align(Align.right);
    }

    private void createTableWeaponBottomNotBought() {
        tableWeaponBottomNotBought = new Table();
        final Table tableSelectedBuy = new Table();
        buttonBuyWeapon = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        buttonBuyWeapon.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonBuyWeapon.getWidth()
                        && y > 0 && y < buttonBuyWeapon.getHeight()) {
                    if (game.getGameWorld().getPlayer().getMoney()
                            >= game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getPrice()) {
                        game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                checkedWeaponID).setBought(true);
                        game.getGameWorld().getPlayer().setMoney(
                                game.getGameWorld().getPlayer().getMoney()
                                        - game.getGameWorld().getWeaponManagerPlayer()
                                        .getWeaponData(checkedWeaponID).getPrice());
                        saveScrollAmount();
                        updateData();
                        if (checkedWeaponID == Constants.PISTOL_ID) {
                            game.getGameWorld().getPlayer().addWeapon(
                                    game.getGameObjectPools().getPistolPlayerPool().obtain(),
                                    game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(Constants.PISTOL_ID),
                                    game.getGameWorld().getPlayer(), null, true);
                        } else if (checkedWeaponID == Constants.ASSAULT_RIFLE_ID) {
                            game.getGameWorld().getPlayer().addWeapon(
                                    game.getGameObjectPools().getAssaultRiflePlayerPool().obtain(),
                                    game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(Constants.ASSAULT_RIFLE_ID),
                                    game.getGameWorld().getPlayer(), null, true);
                        } else if (checkedWeaponID == Constants.SHOTGUN_ID) {
                            game.getGameWorld().getPlayer().addWeapon(
                                    game.getGameObjectPools().getShotgunPool().obtain(),
                                    game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(Constants.SHOTGUN_ID),
                                    game.getGameWorld().getPlayer(), null, true);
                        } else if (checkedWeaponID == Constants.SNIPER_ID) {
                            game.getGameWorld().getPlayer().addWeapon(
                                    game.getGameObjectPools().getSniperPool().obtain(),
                                    game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(Constants.SNIPER_ID),
                                    game.getGameWorld().getPlayer(), null, true);
                        } else if (checkedWeaponID == Constants.MACHINE_GUN_ID) {
                            game.getGameWorld().getPlayer().addWeapon(
                                    game.getGameObjectPools().getMachineGunPool().obtain(),
                                    game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(Constants.MACHINE_GUN_ID),
                                    game.getGameWorld().getPlayer(), null, true);
                        } else if (checkedWeaponID == Constants.ROCKET_LAUNCHER_ID) {
                            game.getGameWorld().getPlayer().addWeapon(
                                    game.getGameObjectPools().getRocketLauncherPool().obtain(),
                                    game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(Constants.ROCKET_LAUNCHER_ID),
                                    game.getGameWorld().getPlayer(), null, true);
                        }
                    } else {
                        game.getAlertManager().showPopup(game.getAssetManager().get(
                                Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                    }
                }
            }
        });
        tableSelectedBuy.add(labelWeaponPrice).expandX().align(Align.right);
        tableSelectedBuy.add(buttonBuyWeapon).padLeft(Constants.GAP).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);
        tableWeaponBottomNotBought.add(tableSelectedBuy)
                .growX().pad(Constants.GAP).align(Align.bottomRight);
    }

    private void createTableWeaponBottomBought() {
        tableWeaponBottomBought = new Table();

        buttonGetAmmo = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        buttonGetAmmo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonGetAmmo.getWidth()
                        && y > 0 && y < buttonGetAmmo.getHeight()) {
                    game.getGameScreen().getStageExtra().clear();
                    game.getGameScreen().getStageExtra().addActor(tableBuyAmmo);
                    game.getGameScreen().setShowStageExtra(true);
                    updateAmmoTable();
                }
            }
        });

        buttonUpgrade = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        buttonUpgrade.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < buttonUpgrade.getWidth()
                        && y > 0 && y < buttonUpgrade.getHeight()) {
                    if (!buttonUpgrade.isDisabled()) {
                        game.getGameScreen().getStageExtra().clear();
                        game.getGameScreen().getStageExtra().addActor(tableWeaponUpgrade);
                        game.getGameScreen().setShowStageExtra(true);
                        updateUpgradeTable();
                    } else {
                        game.getAlertManager().showPopup("This weapon is fully upgraded.");
                    }
                }
            }
        });

        Table tableButtons = new Table();
        tableButtons.add(buttonGetAmmo)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT)
                .align(Align.bottomRight).growX().padRight(Constants.GAP);
        tableButtons.add(buttonUpgrade)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT)
                .align(Align.bottomRight);
        tableWeaponBottomBought.add(tableButtons)
                .growX().pad(Constants.GAP).align(Align.bottomRight);
    }

    private void createTableWeaponUpgrade() {
        final ImageButton btCloseInDialogBox = new ImageButton(
                game.getStyles().getImageButtonStyleClose());
        btCloseInDialogBox.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_SMALL
                        && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_SMALL) {
                    game.getGameScreen().setShowStageExtra(false);
                    saveScrollAmountUpgrade();
                    updateData();
                }
            }
        });
        Table tableTop = new Table();
        tableTop.add(labelWeaponNameUpgrade).align(Align.topLeft).growX().expandY();
        tableTop.add(btCloseInDialogBox)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .padLeft(Constants.GAP).align(Align.right);

        tableWeaponRowsUpgrade = new Table();
        scrollPaneWeaponUpgrade = new ScrollPane(tableWeaponRowsUpgrade,
                game.getStyles().getScrollPaneStyle());
        scrollPaneWeaponUpgrade.setScrollingDisabled(true, false);
        scrollPaneWeaponUpgrade.setForceScroll(false, true);
        scrollPaneWeaponUpgrade.setupOverscroll(
                Constants.SCROLL_PANE_OVER_SCROLL,
                Constants.SCROLL_PANE_MIN_SPEED,
                Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeaponUpgrade.setFadeScrollBars(false);
        scrollPaneWeaponUpgrade.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeaponUpgrade.updateVisualScroll();
        scrollPaneWeaponUpgrade.layout();

        buttonBuyUpgrade = new TextButton("", game.getStyles().getTextButtonStyleOrange());
        buttonBuyUpgrade.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0 && y < Constants.TEXT_BUTTON_HEIGHT) {
                    if (!buttonBuyUpgrade.isDisabled()) {
                        if (game.getGameWorld().getPlayer().getMoney()
                                >= (game.getGameWorld().getWeaponManagerPlayer()
                                .getWeaponData(checkedWeaponID).getUpgradePrices()
                                .get(game.getGameWorld().getWeaponManagerPlayer()
                                        .getWeaponData(checkedWeaponID).getLevel()))) {
                            game.getGameWorld().getPlayer().setMoney(game.getGameWorld().getPlayer()
                                    .getMoney() - (game.getGameWorld().getWeaponManagerPlayer()
                                    .getWeaponData(checkedWeaponID).getUpgradePrices()
                                    .get(game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getLevel())));
                            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                    checkedWeaponID).isRateOfFire()) {
                                game.getGameWorld().getWeaponManagerPlayer()
                                        .getWeaponData(checkedWeaponID)
                                        .setRateOfFire(game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getRateOfFire()
                                                + game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getRateOfFireUpgrades()
                                                .get(game.getGameWorld().getWeaponManagerPlayer()
                                                        .getWeaponData(checkedWeaponID).getLevel()));
                            }
                            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                    checkedWeaponID).isAmmoDamage()) {
                                game.getGameWorld().getWeaponManagerPlayer()
                                        .getWeaponData(checkedWeaponID)
                                        .setAmmoDamage(game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getAmmoDamage()
                                                + game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getAmmoDamageUpgrades()
                                                .get(game.getGameWorld().getWeaponManagerPlayer()
                                                        .getWeaponData(checkedWeaponID).getLevel()));
                            }
                            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                    checkedWeaponID).isAmmoSpeed()) {
                                game.getGameWorld().getWeaponManagerPlayer()
                                        .getWeaponData(checkedWeaponID)
                                        .setAmmoSpeed(game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getAmmoSpeed()
                                                + game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getAmmoSpeedUpgrades()
                                                .get(game.getGameWorld().getWeaponManagerPlayer()
                                                        .getWeaponData(checkedWeaponID).getLevel()));
                            }
                            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                    checkedWeaponID).isMagazineSize()) {
                                game.getGameWorld().getWeaponManagerPlayer()
                                        .getWeaponData(checkedWeaponID)
                                        .setMagazineSize(game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getMagazineSize()
                                                + game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getMagazineSizeUpgrades()
                                                .get(game.getGameWorld().getWeaponManagerPlayer()
                                                        .getWeaponData(checkedWeaponID).getLevel()));
                            }
                            if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                                    checkedWeaponID).isReloadTime()) {
                                game.getGameWorld().getWeaponManagerPlayer()
                                        .getWeaponData(checkedWeaponID)
                                        .setReloadTime(game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getReloadTime()
                                                + game.getGameWorld().getWeaponManagerPlayer()
                                                .getWeaponData(checkedWeaponID).getReloadTimeUpgrades()
                                                .get(game.getGameWorld().getWeaponManagerPlayer()
                                                        .getWeaponData(checkedWeaponID).getLevel()));
                            }
                            game.getGameWorld().getWeaponManagerPlayer()
                                    .getWeaponData(checkedWeaponID)
                                    .setLevel(game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getLevel() + 1);

                            updateUpgradeTable();
                            updateData();
                            game.getGameScreen().getInGameHud().updateData();
                        } else {
                            game.getAlertManager().showPopup(game.getAssetManager().get(
                                    Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    } else {
                        game.getAlertManager().showPopup("This weapon is fully upgraded!!!!!!");
                    }
                }
            }
        });
        Table tableBottom = new Table();
        tableBottom.add(labelYourMoneyTitleUpgrade).padRight(Constants.GAP).align(Align.left);
        tableBottom.add(labelYourMoneyUpgrade).align(Align.left);
        tableBottom.add(labelPriceUpgrade).expandX().align(Align.right);
        tableBottom.add(buttonBuyUpgrade).padLeft(Constants.GAP).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);

        Table table = new Table();
        table.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        table.add(tableTop).pad(Constants.GAP).align(Align.left).growX();
        table.row();
        table.add(scrollPaneWeaponUpgrade)
                .align(Align.left).grow().padRight(Constants.GAP).padLeft(Constants.GAP);
        table.row();
        table.add(tableBottom).pad(Constants.GAP).align(Align.left).growX();

        tableWeaponUpgrade = new Table();
        tableWeaponUpgrade.setFillParent(true);
        tableWeaponUpgrade.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableWeaponUpgrade.add(table)
                .width(Constants.DIALOG_BOX_WIDTH_WEAPON)
                .height(Constants.DIALOG_BOX_HEIGHT_WEAPON);
    }

    private void createTableBuyAmmo() {
        Table tableRow11 = new Table();
        tableRow11.add(labelWeaponNameBuyAmmo).align(Align.left).growX();
        tableRow11.add(labelYourAmmoTitleBuyAmmo).padRight(Constants.GAP).align(Align.right);
        tableRow11.add(labelYourAmmoBuyAmmo).align(Align.right);
        final ImageButton btCloseInDialogBox = new ImageButton(
                game.getStyles().getImageButtonStyleClose());
        btCloseInDialogBox.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_SMALL
                        && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_SMALL) {
                    game.getGameScreen().setShowStageExtra(false);
                    updateData();
                }
            }
        });
        Table tableRow1 = new Table();
        tableRow1.add(tableRow11).align(Align.topLeft).expandY().growX();
        tableRow1.add(btCloseInDialogBox)
                .width(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .height(Constants.IMAGE_BUTTON_SIZE_SMALL)
                .padLeft(Constants.GAP).align(Align.right);
        Table tableRow2 = new Table();
        tableRow2.add(labelGuideBuyAmmo).align(Align.left).expandX();
        ImageButton buttonMags = new ImageButton(
                game.getStyles().getImageButtonStyleMagazines());
        buttonMags.setChecked(true);
        buttonMags.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY
                        && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
                    sliderNumberOfMagsBuyAmmo.setVisible(true);
                    sliderNumberOfAmmoBuyAmmo.setVisible(false);
                    updateAmmoTable();
                }
            }
        });
        ImageButton buttonAmmo = new ImageButton(game.getStyles().getImageButtonStyleMagazines());
        buttonAmmo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_TINY
                        && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_TINY) {
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
        tableRow3.add(buttonMags)
                .width(Constants.IMAGE_BUTTON_SIZE_TINY)
                .height(Constants.IMAGE_BUTTON_SIZE_TINY)
                .padRight(Constants.GAP / 2f);
        tableRow3.add(labelMagsBuyAmmo).padRight(Constants.GAP * 2);
        tableRow3.add(buttonAmmo)
                .width(Constants.IMAGE_BUTTON_SIZE_TINY)
                .height(Constants.IMAGE_BUTTON_SIZE_TINY).
                padRight(Constants.GAP / 2f);
        tableRow3.add(labelAmmoBuyAmmo);
        Table tableRow4 = new Table();
        tableRow4.add(labelNumberOfMagsOrAmmoTitleBuyAmmo).padRight(Constants.GAP);
        tableRow4.add(labelNumberOfMagsOrAmmoBuyAmmo);
        sliderNumberOfMagsBuyAmmo = new Slider(
                1, 50, 1, false, game.getStyles().getSliderStyle());
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
        sliderNumberOfAmmoBuyAmmo = new Slider(
                1, 50, 1, false, game.getStyles().getSliderStyle());
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
        buttonBuyAmmo = new TextButton("",
                game.getStyles().getTextButtonStyleOrange());
        buttonBuyAmmo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < Constants.TEXT_BUTTON_WIDTH && y > 0
                        && y < Constants.TEXT_BUTTON_HEIGHT) {
                    if (sliderNumberOfMagsBuyAmmo.isVisible()) {
                        if (game.getGameWorld().getPlayer().getMoney()
                                >= ((int) (sliderNumberOfMagsBuyAmmo.getValue())
                                * game.getGameWorld().getWeaponManagerPlayer()
                                .getWeaponData(checkedWeaponID).getMagazineSize()
                                * game.getGameWorld().getWeaponManagerPlayer()
                                .getWeaponData(checkedWeaponID).getAmmoPrice())) {
                            game.getGameWorld().getPlayer().setMoney(
                                    game.getGameWorld().getPlayer().getMoney()
                                            - ((int) (sliderNumberOfMagsBuyAmmo.getValue())
                                            * game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getMagazineSize()
                                            * game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getAmmoPrice()));
                            game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                                    .setNumberOfAmmo(game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getNumberOfAmmo()
                                            + ((int) (sliderNumberOfMagsBuyAmmo.getValue())
                                            * game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getMagazineSize()));
                            game.getGameWorld().getPlayer().getWeapon()
                                    .updateWeaponWhenMoreAmmoIsGiven();
                        } else {
                            game.getAlertManager().showPopup(game.getAssetManager().get(
                                    Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    } else if (sliderNumberOfAmmoBuyAmmo.isVisible()) {
                        if (game.getGameWorld().getPlayer().getMoney()
                                >= ((int) (sliderNumberOfAmmoBuyAmmo.getValue())
                                * game.getGameWorld().getWeaponManagerPlayer()
                                .getWeaponData(checkedWeaponID).getAmmoPrice())) {
                            game.getGameWorld().getPlayer().setMoney(
                                    game.getGameWorld().getPlayer().getMoney()
                                            - ((int) (sliderNumberOfAmmoBuyAmmo.getValue())
                                            * game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getAmmoPrice()));
                            game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                                    .setNumberOfAmmo(game.getGameWorld().getWeaponManagerPlayer()
                                            .getWeaponData(checkedWeaponID).getNumberOfAmmo()
                                            + ((int) (sliderNumberOfAmmoBuyAmmo.getValue())));
                            game.getGameWorld().getPlayer().getWeapon()
                                    .updateWeaponWhenMoreAmmoIsGiven();
                        } else {
                            game.getAlertManager().showPopup(game.getAssetManager().get(
                                    Constants.BUNDLE, I18NBundle.class).get("popupNoMoney"));
                        }
                    }
                    updateAmmoTable();
                    updateData();
                    game.getGameScreen().getInGameHud().updateData();
                }
            }
        });
        Table tableRow6 = new Table();
        tableRow6.add(labelYourMoneyTitleBuyAmmo).padRight(Constants.GAP).align(Align.left);
        tableRow6.add(labelYourMoneyBuyAmmo).align(Align.left);
        tableRow6.add(labelPriceBuyAmmo).expandX().align(Align.right);
        tableRow6.add(buttonBuyAmmo).padLeft(Constants.GAP).align(Align.right)
                .width(Constants.TEXT_BUTTON_WIDTH)
                .height(Constants.TEXT_BUTTON_HEIGHT);

        Table table = new Table();
        table.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));

        table.add(tableRow1)
                .padLeft(Constants.GAP).padRight(Constants.GAP).padTop(Constants.GAP)
                .padBottom(Constants.GAP).growX().expandY().align(Align.top);
        table.row();
        table.add(tableRow2)
                .padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP)
                .expandX().align(Align.bottomLeft);
        table.row();
        table.add(tableRow3)
                .padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP)
                .expandX().align(Align.bottomLeft);
        table.row();
        table.add(tableRow4)
                .padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP)
                .expandX().align(Align.topLeft);
        table.row();
        table.add(tableRow5)
                .padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP)
                .growX().align(Align.topLeft);
        table.row();
        table.add(tableRow6)
                .padLeft(Constants.GAP).padRight(Constants.GAP).padBottom(Constants.GAP)
                .growX().expandY().align(Align.bottom);
        tableBuyAmmo = new Table();
        tableBuyAmmo.setFillParent(true);
        tableBuyAmmo.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_SCREEN_BACKGROUND_ON_DIALOG_BOX)));
        tableBuyAmmo.add(table)
                .width(Constants.DIALOG_BOX_WIDTH_WEAPON)
                .height(Constants.DIALOG_BOX_HEIGHT_WEAPON);
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