package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.aleksiprograms.survivalofkeijo.toolbox.TextButtonWithID;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

public class BackpackHud extends AbstractHud {

    private Label labelScreenTitle;

    private Table tableWeaponButtons;
    private Array<TextButtonWithID> buttonWeapons;
    private int checkedWeaponID;
    private Label labelMoney;
    private Label uselessLabel;

    private Label labelBasicInfoFireType;
    private Label labelBasicInfoPointsHit;
    private Label labelBasicInfoPointsKill;
    private Label labelBasicInfoAmmoPrice;
    private Label labelFireTypeTitle;
    private Label labelPointsTitle;
    private Label labelPointsTitleHit;
    private Label labelPointsTitleKill;
    private Label labelAmmoPriceTitle;
    private Label labelAmmoPriceUnit;

    private Label labelWeaponName;
    private Label labelNumberOfAmmoTitle;
    private Label labelNumberOfAmmo;
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
    private ProgressBar progressBarRateOfFire;
    private ProgressBar progressBarAmmoDamage;
    private ProgressBar progressBarAmmoSpeed;
    private ProgressBar progressBarMagazineSize;
    private ProgressBar progressBarReloadTime;
    private ProgressBar progressBarWeight;
    private ProgressBar progressBarMeleeDamage;

    private Table tableWeapon;
    private Table tableWeaponData;
    private Table tableWeaponInfoBox;
    private Table tableWeaponRows;
    private ScrollPane scrollPaneWeapon;

    private int weaponInfoRows;
    private Table tableRateOfFire;
    private Table tableAmmoDamage;
    private Table tableAmmoSpeed;
    private Table tableMagazineSize;
    private Table tableReloadTime;
    private Table tableWeight;

    public BackpackHud(final TheGame game) {
        super(game);
        initializeHud();
    }

    @Override
    public void updateHud() {
        super.updateHud();
        labelScreenTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("titleBackpack"));
        updateWeaponsTable();
        updateSelectedWeaponTable();
        labelMoney.setText(game.styles.getFormattedFloatMoney(game.gameWorld.player.money) + " \u20AC");
    }

    private void updateSelectedWeaponTable() {
        tableWeapon.clearChildren();
        tableWeaponRows.clearChildren();
        updateWeaponInfoBox();
        updateWeaponRowsData();
        updateSelectedWeaponRows();
        tableWeapon.add(tableWeaponData).width(Constants.WEAPON_TABLE_WIDTH).growY();
    }

    private void updateWeaponsTable() {
        buttonWeapons.clear();
        for (int i = 0; i < game.gameWorld.player.weapons.size; i++) {
            final TextButtonWithID table = new TextButtonWithID(game.gameWorld.player.weapons.get(i).weaponData.ID, game.styles.textButtonStyleOrange);
            table.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
            table.setTouchable(Touchable.enabled);
            table.add(new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_UP)))).width(Constants.IMAGE_BUTTON_SIZE_HUGE).height(Constants.IMAGE_BUTTON_SIZE_HUGE).align(Align.center);
            table.addListener(new InputListener() {
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
                        for (int k = 0; k < buttonWeapons.size; k++) {
                            if (buttonWeapons.get(k).ID == checkedWeaponID) {
                                buttonWeapons.get(k).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
                            }
                        }
                        checkedWeaponID = table.ID;
                        for (int k = 0; k < buttonWeapons.size; k++) {
                            if (buttonWeapons.get(k).ID == checkedWeaponID) {
                                buttonWeapons.get(k).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_DOWN)));
                            }
                        }
                        updateSelectedWeaponTable();
                        for (int i = 0; i < game.gameWorld.player.weapons.size; i++) {
                            if (checkedWeaponID == game.gameWorld.player.weapons.get(i).weaponData.ID) {
                                game.gameWorld.player.weapon = game.gameWorld.player.weapons.get(i);
                                game.gameWorld.player.weapon.updateOnEquip();
                            }
                        }
                    }
                }
            });
            buttonWeapons.add(table);
        }
        tableWeaponButtons.clearChildren();
        for (int i = 0; i < Constants.NUMBER_OF_WEAPONS; i++) {
            if (i < buttonWeapons.size) {
                tableWeaponButtons.add(buttonWeapons.get(i)).width(Constants.IMAGE_BUTTON_SIZE_HUGE).height(Constants.IMAGE_BUTTON_SIZE_HUGE).padRight(Constants.GAP).padBottom(i < Constants.UI_WEAPONS_IN_FULL_ROW ? Constants.GAP : 0);
            } else {
                tableWeaponButtons.add(new Image(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)))).width(Constants.IMAGE_BUTTON_SIZE_HUGE).height(Constants.IMAGE_BUTTON_SIZE_HUGE).padRight(Constants.GAP).padBottom(i < Constants.UI_WEAPONS_IN_FULL_ROW ? Constants.GAP : 0);
            }
            if ((i + 1) % Constants.UI_WEAPONS_IN_ROW == 0) {
                tableWeaponButtons.row();
            }
        }
        checkedWeaponID = game.gameWorld.player.weapon.weaponData.ID;
        for (int i = 0; i < buttonWeapons.size; i++) {
            if (checkedWeaponID == buttonWeapons.get(i).ID) {
                buttonWeapons.get(i).background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_DOWN)));
            }
        }
    }

    private void updateSelectedWeaponRows() {
        weaponInfoRows = 0;
        tableWeaponRows.add(tableWeaponInfoBox).padBottom(Constants.GAP).padRight(Constants.GAP).height(3 * uselessLabel.getHeight() + 2 * Constants.GAP);
        tableWeaponRows.row();
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isRateOfFire) {
            weaponInfoRows++;
            tableWeaponRows.add(tableRateOfFire).padBottom(weaponInfoRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoDamage) {
            weaponInfoRows++;
            tableWeaponRows.add(tableAmmoDamage).padBottom(weaponInfoRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isAmmoSpeed) {
            weaponInfoRows++;
            tableWeaponRows.add(tableAmmoSpeed).padBottom(weaponInfoRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isMagazineSize) {
            weaponInfoRows++;
            tableWeaponRows.add(tableMagazineSize).padBottom(weaponInfoRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isReloadTime) {
            weaponInfoRows++;
            tableWeaponRows.add(tableReloadTime).padBottom(weaponInfoRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).isWeight) {
            weaponInfoRows++;
            tableWeaponRows.add(tableWeight).padBottom(weaponInfoRows < game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).attributes ? Constants.GAP : 0).padRight(Constants.GAP).height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }

        scrollPaneWeapon.removeActor(tableWeaponRows);
        scrollPaneWeapon.setActor(tableWeaponRows);
        scrollPaneWeapon.updateVisualScroll();
        scrollPaneWeapon.layout();
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

    private void updateWeaponRowsData() {
        labelWeaponName.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).nameID));
        labelNumberOfAmmoTitle.setText(game.assetManager.get(Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelNumberOfAmmo.setText(game.styles.getFormattedInt(game.gameWorld.weaponManagerPlayer.getWeaponData(checkedWeaponID).numberOfAmmo));

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
    }

    @Override
    protected void initializeHud() {
        super.initializeHud();

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        checkedWeaponID = -1;

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

        labelWeaponName = new Label("", game.styles.labelStyleWhiteMedium);
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
        progressBarRateOfFire = new ProgressBar(game.gameWorld.weaponManagerPlayer.minRateOfFire, game.gameWorld.weaponManagerPlayer.maxRateOfFire, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarAmmoDamage = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoDamage, game.gameWorld.weaponManagerPlayer.maxAmmoDamage, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarAmmoSpeed = new ProgressBar(game.gameWorld.weaponManagerPlayer.minAmmoSpeed, game.gameWorld.weaponManagerPlayer.maxAmmoSpeed, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarMagazineSize = new ProgressBar(game.gameWorld.weaponManagerPlayer.minMagazineSize, game.gameWorld.weaponManagerPlayer.maxMagazineSize, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarReloadTime = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxReloadTime, -game.gameWorld.weaponManagerPlayer.minReloadTime, 0.01f, false, game.styles.progressBarStyleValueNotBought);
        progressBarWeight = new ProgressBar(-game.gameWorld.weaponManagerPlayer.maxWeight, -game.gameWorld.weaponManagerPlayer.minWeight, 0.01f, false, game.styles.progressBarStyleValueNotBought);

        labelRateOfFireValue.setAlignment(Align.topRight);
        labelAmmoDamageValue.setAlignment(Align.topRight);
        labelAmmoSpeedValue.setAlignment(Align.topRight);
        labelMagazineSizeValue.setAlignment(Align.topRight);
        labelReloadTimeValue.setAlignment(Align.topRight);
        labelWeightValue.setAlignment(Align.topRight);

        tableWeaponInfoBox = createBasicInfoBox();

        tableRateOfFire = createRow(labelRateOfFireTitle, labelRateOfFireValue, labelRateOfFireUnit, labelRateOfFireLevelTitle, labelRateOfFireLevel, labelRateOfFireMaxLevel, progressBarRateOfFire);
        tableAmmoDamage = createRow(labelAmmoDamageTitle, labelAmmoDamageValue, labelAmmoDamageUnit, labelAmmoDamageLevelTitle, labelAmmoDamageLevel, labelAmmoDamageMaxLevel, progressBarAmmoDamage);
        tableAmmoSpeed = createRow(labelAmmoSpeedTitle, labelAmmoSpeedValue, labelAmmoSpeedUnit, labelAmmoSpeedLevelTitle, labelAmmoSpeedLevel, labelAmmoSpeedMaxLevel, progressBarAmmoSpeed);
        tableMagazineSize = createRow(labelMagazineSizeTitle, labelMagazineSizeValue, labelMagazineSizeUnit, labelMagazineSizeLevelTitle, labelMagazineSizeLevel, labelMagazineSizeMaxLevel, progressBarMagazineSize);
        tableReloadTime = createRow(labelReloadTimeTitle, labelReloadTimeValue, labelReloadTimeUnit, labelReloadTimeLevelTitle, labelReloadTimeLevel, labelReloadTimeMaxLevel, progressBarReloadTime);
        tableWeight = createRow(labelWeightTitle, labelWeightValue, labelWeightUnit, labelWeightLevelTitle, labelWeightLevel, labelWeightMaxLevel, progressBarWeight);

        buttonWeapons = new Array<TextButtonWithID>();
        tableWeaponButtons = new Table();

        ScrollPane scrollPaneWeapons = new ScrollPane(tableWeaponButtons, game.styles.scrollPaneStyle);
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
        createTableWeapon();

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
        tableData.add(valueProgress).colspan(5).width(Constants.WEAPON_BAR_WIDTH).height(Constants.PROGRESS_BAR_DATA_THICKNESS).expandY().align(Align.bottomLeft);
        Table tableRow = new Table();
        tableRow.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND_SECONDARY)));
        tableRow.add(tableData).growY().pad(Constants.GAP);
        return tableRow;
    }

    private void createTableWeapon() {
        tableWeaponRows = new Table();
        scrollPaneWeapon = new ScrollPane(tableWeaponRows, game.styles.scrollPaneStyle);
        scrollPaneWeapon.setScrollingDisabled(true, false);
        scrollPaneWeapon.setForceScroll(false, true);
        scrollPaneWeapon.setupOverscroll(Constants.SCROLL_PANE_OVER_SCROLL, Constants.SCROLL_PANE_MIN_SPEED, Constants.SCROLL_PANE_MAX_SPEED);
        scrollPaneWeapon.setFadeScrollBars(false);
        scrollPaneWeapon.setFlickScrollTapSquareSize(Constants.SCROLL_PANE_SQUARE_SIZE);
        scrollPaneWeapon.updateVisualScroll();
        scrollPaneWeapon.layout();

        tableWeaponData = new Table();
        tableWeaponData.background(new NinePatchDrawable(game.assetManager.get(Constants.TEXTURE_ATLAS, TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableWeaponData.add(labelWeaponName).pad(Constants.GAP).align(Align.left).growX();
        tableWeaponData.add(labelNumberOfAmmoTitle).padTop(Constants.GAP).padBottom(Constants.GAP).align(Align.right);
        tableWeaponData.add(labelNumberOfAmmo).pad(Constants.GAP).align(Align.right);
        tableWeaponData.row();
        tableWeaponData.add(scrollPaneWeapon).colspan(3).align(Align.left).grow().padBottom(Constants.GAP).padRight(Constants.GAP).padLeft(Constants.GAP);
    }
}