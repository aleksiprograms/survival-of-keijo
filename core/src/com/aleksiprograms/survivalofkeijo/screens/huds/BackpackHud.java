package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.GameState;
import com.aleksiprograms.survivalofkeijo.toolbox.WeaponButton;
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
    private Array<WeaponButton> buttonWeapons;
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

    public BackpackHud(final TheGame game) {
        super(game);
        initialize();
    }

    @Override
    public void updateData() {
        super.updateData();
        labelScreenTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("titleBackpack"));
        updateWeaponsTable();
        updateSelectedWeaponTable();
        labelMoney.setText(game.getStyles().getFormattedFloatMoney(
                game.getGameWorld().getPlayer().getMoney()) + " \u20AC");
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
        for (int i = 0; i < game.getGameWorld().getPlayer().getWeapons().size; i++) {
            final WeaponButton table = new WeaponButton(game.getGameWorld().getPlayer()
                    .getWeapons().get(i).getWeaponData().getID());
            table.background(new NinePatchDrawable(game.getAssetManager().get(
                    Constants.TEXTURE_ATLAS,
                    TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
            table.setTouchable(Touchable.enabled);
            table.add(new Image(new NinePatchDrawable(game.getAssetManager().get(
                    Constants.TEXTURE_ATLAS,
                    TextureAtlas.class).createPatch(Constants.TEXTURE_BUTTON_GAME_RIGHT_UP))))
                    .width(Constants.IMAGE_BUTTON_SIZE_HUGE)
                    .height(Constants.IMAGE_BUTTON_SIZE_HUGE).align(Align.center);
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
                    if (x > 0 && x < Constants.IMAGE_BUTTON_SIZE_HUGE
                            && y > 0 && y < Constants.IMAGE_BUTTON_SIZE_HUGE) {
                        for (int k = 0; k < buttonWeapons.size; k++) {
                            if (buttonWeapons.get(k).weaponButtonID == checkedWeaponID) {
                                buttonWeapons.get(k).background(new NinePatchDrawable(game
                                        .getAssetManager()
                                        .get(Constants.TEXTURE_ATLAS, TextureAtlas.class)
                                        .createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG)));
                            }
                        }
                        checkedWeaponID = table.weaponButtonID;
                        for (int k = 0; k < buttonWeapons.size; k++) {
                            if (buttonWeapons.get(k).weaponButtonID == checkedWeaponID) {
                                buttonWeapons.get(k).background(new NinePatchDrawable(game
                                        .getAssetManager()
                                        .get(Constants.TEXTURE_ATLAS, TextureAtlas.class)
                                        .createPatch(Constants.TEXTURE_BUTTON_DOWN)));
                            }
                        }
                        updateSelectedWeaponTable();
                        for (int i = 0; i < game.getGameWorld().getPlayer().getWeapons().size; i++) {
                            if (checkedWeaponID == game.getGameWorld().getPlayer()
                                    .getWeapons().get(i).getWeaponData().getID()) {
                                game.getGameWorld().getPlayer().setWeapon(
                                        game.getGameWorld().getPlayer().getWeapons().get(i));
                                game.getGameWorld().getPlayer().getWeapon().updateOnEquip();
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
                tableWeaponButtons.add(buttonWeapons.get(i))
                        .width(Constants.IMAGE_BUTTON_SIZE_HUGE)
                        .height(Constants.IMAGE_BUTTON_SIZE_HUGE)
                        .padRight(Constants.GAP)
                        .padBottom(i < Constants.UI_WEAPONS_IN_FULL_ROW ? Constants.GAP : 0);
            } else {
                tableWeaponButtons.add(new Image(new NinePatchDrawable(game
                        .getAssetManager()
                        .get(Constants.TEXTURE_ATLAS, TextureAtlas.class)
                        .createPatch(Constants.TEXTURE_BUTTON_UP_OR_OFF_OR_BG))))
                        .width(Constants.IMAGE_BUTTON_SIZE_HUGE)
                        .height(Constants.IMAGE_BUTTON_SIZE_HUGE)
                        .padRight(Constants.GAP)
                        .padBottom(i < Constants.UI_WEAPONS_IN_FULL_ROW ? Constants.GAP : 0);
            }
            if ((i + 1) % Constants.UI_WEAPONS_IN_ROW == 0) {
                tableWeaponButtons.row();
            }
        }
        checkedWeaponID = game.getGameWorld().getPlayer().getWeapon().getWeaponData().getID();
        for (int i = 0; i < buttonWeapons.size; i++) {
            if (checkedWeaponID == buttonWeapons.get(i).weaponButtonID) {
                buttonWeapons.get(i).background(new NinePatchDrawable(game
                        .getAssetManager()
                        .get(Constants.TEXTURE_ATLAS, TextureAtlas.class)
                        .createPatch(Constants.TEXTURE_BUTTON_DOWN)));
            }
        }
    }

    private void updateSelectedWeaponRows() {
        weaponInfoRows = 0;
        tableWeaponRows.add(tableWeaponInfoBox)
                .padBottom(Constants.GAP).padRight(Constants.GAP)
                .height(3 * uselessLabel.getHeight() + 2 * Constants.GAP);
        tableWeaponRows.row();
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isRateOfFire()) {
            weaponInfoRows++;
            tableWeaponRows.add(tableRateOfFire).padBottom(
                    weaponInfoRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoDamage()) {
            weaponInfoRows++;
            tableWeaponRows.add(tableAmmoDamage).padBottom(
                    weaponInfoRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isAmmoSpeed()) {
            weaponInfoRows++;
            tableWeaponRows.add(tableAmmoSpeed).padBottom(
                    weaponInfoRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isMagazineSize()) {
            weaponInfoRows++;
            tableWeaponRows.add(tableMagazineSize).padBottom(
                    weaponInfoRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }
        if (game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                checkedWeaponID).isReloadTime()) {
            weaponInfoRows++;
            tableWeaponRows.add(tableReloadTime).padBottom(
                    weaponInfoRows < game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                            checkedWeaponID).getNumberOfAttributes() ? Constants.GAP : 0)
                    .padRight(Constants.GAP)
                    .height(Constants.IMAGE_BUTTON_SIZE_TINY + 2 * Constants.GAP);
            tableWeaponRows.row();
        }

        scrollPaneWeapon.removeActor(tableWeaponRows);
        scrollPaneWeapon.setActor(tableWeaponRows);
        scrollPaneWeapon.updateVisualScroll();
        scrollPaneWeapon.layout();
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
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                        .getPointsHit()));
        labelBasicInfoPointsKill.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                        .getPointsKill()));
        labelBasicInfoAmmoPrice.setText(game.getStyles().getFormattedFloatMoney(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(checkedWeaponID)
                        .getAmmoPrice()));
        labelAmmoPriceUnit.setText(" \u20AC");
    }

    private void updateWeaponRowsData() {
        labelWeaponName.setText(game.getAssetManager().get(Constants.BUNDLE, I18NBundle.class).get(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getNameID()));
        labelNumberOfAmmoTitle.setText(game.getAssetManager().get(
                Constants.BUNDLE, I18NBundle.class).get("labelNumberOfAmmoTitle"));
        labelNumberOfAmmo.setText(game.getStyles().getFormattedInt(
                game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
                        checkedWeaponID).getNumberOfAmmo()));

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
            labelAmmoDamageValue.setText(game.getStyles().getFormattedInt(
                    game.getGameWorld().getWeaponManagerPlayer().getWeaponData(
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

    @Override
    protected void initialize() {
        super.initialize();

        super.pad(Constants.GAP);
        super.center();
        super.setFillParent(true);

        checkedWeaponID = -1;

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

        labelWeaponName = new Label("", game.getStyles().getLabelStyleWhiteMedium());
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

        buttonWeapons = new Array<WeaponButton>();
        tableWeaponButtons = new Table();

        ScrollPane scrollPaneWeapons = new ScrollPane(
                tableWeaponButtons, game.getStyles().getScrollPaneStyle());
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

        labelScreenTitle = new Label("",
                game.getStyles().getLabelStyleWhiteHugeWithBorder());
        labelMoney = new Label("",
                game.getStyles().getLabelStyleWhiteBig());
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
        createTableWeapon();

        super.add(tableTop)
                .height(Constants.TABLE_TOP_HEIGHT)
                .growX().padBottom(Constants.GAP).align(Align.top).colspan(2);
        super.row();
        super.add(tableWeapons).growY().align(Align.left)
                .width(
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
        tableRows.add(labelFireTypeTitle)
                .align(Align.bottomLeft)
                .width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH)
                .height(uselessLabel.getHeight())
                .padLeft(Constants.GAP).padTop(Constants.GAP);
        tableRows.add(tableDataRow1)
                .align(Align.topLeft)
                .width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH)
                .padRight(Constants.GAP)
                .padTop(Constants.GAP);
        tableRows.row();
        tableRows.add(labelPointsTitle)
                .align(Align.topLeft)
                .width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH)
                .height(uselessLabel.getHeight())
                .padLeft(Constants.GAP);
        tableRows.add(tableDataRow2)
                .align(Align.topLeft)
                .width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH)
                .padRight(Constants.GAP);
        tableRows.row();
        tableRows.add(labelAmmoPriceTitle)
                .align(Align.left)
                .width(Constants.WEAPON_BASIC_INFO_TITLE_WIDTH)
                .height(uselessLabel.getHeight())
                .padLeft(Constants.GAP)
                .padBottom(Constants.GAP);
        tableRows.add(tableDataRow3)
                .align(Align.topLeft)
                .width(Constants.WEAPON_BASIC_INFO_DATA_WIDTH)
                .padRight(Constants.GAP)
                .padBottom(Constants.GAP);
        return tableRows;
    }

    private Table createRow(Label title, Label value, Label unit, ProgressBar valueProgress) {
        Table tableData = new Table();
        tableData.add(title).width(Constants.WEAPON_TITLE_WIDTH).align(Align.left);
        tableData.add(value).width(Constants.WEAPON_VALUE_WIDTH).align(Align.topLeft);
        tableData.add(unit).width(Constants.WEAPON_UNIT_WIDTH).align(Align.topLeft);
        tableData.row();
        tableData.add(valueProgress)
                .colspan(5)
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

    private void createTableWeapon() {
        tableWeaponRows = new Table();
        scrollPaneWeapon = new ScrollPane(tableWeaponRows, game.getStyles().getScrollPaneStyle());
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

        tableWeaponData = new Table();
        tableWeaponData.background(new NinePatchDrawable(game.getAssetManager().get(
                Constants.TEXTURE_ATLAS,
                TextureAtlas.class).createPatch(Constants.TEXTURE_TABLE_BACKGROUND)));
        tableWeaponData.add(labelWeaponName).pad(Constants.GAP).align(Align.left).growX();
        tableWeaponData.add(labelNumberOfAmmoTitle)
                .padTop(Constants.GAP).padBottom(Constants.GAP).align(Align.right);
        tableWeaponData.add(labelNumberOfAmmo).pad(Constants.GAP).align(Align.right);
        tableWeaponData.row();
        tableWeaponData.add(scrollPaneWeapon).colspan(3).align(Align.left).grow()
                .padBottom(Constants.GAP).padRight(Constants.GAP).padLeft(Constants.GAP);
    }
}