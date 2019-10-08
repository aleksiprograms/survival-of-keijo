package com.aleksiprograms.survivalofkeijo.screens;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class StatsScreen extends AbstractScreen {

    private Label labelStars;
    private Label labelTotalTime;
    private Label labelBattlingTime;
    private Label labelKills;
    private Label labelFighterKills;
    private Label labelMachineGunKills;
    private Label labelShotgunKills;
    private Label labelFlamethrowerKills;
    private Label labelKnifeThrowerKills;
    private Label labelGrenadeLauncherKills;
    private Label labelRocketLauncherKills;
    private Label labelDynamiteLauncherKills;
    private Label labelBladeLauncherKills;
    private Label labelAccuracy;
    private Label labelMachineGunAccuracy;
    private Label labelShotgunAccuracy;
    private Label labelFlamethrowerAccuracy;
    private Label labelKnifeThrowerAccuracy;
    private Label labelGrenadeLauncherAccuracy;
    private Label labelRocketLauncherAccuracy;
    private Label labelDynamiteLauncherAccuracy;
    private Label labelBladeLauncherAccuracy;

    private long appTime;
    private long daysTotal;
    private long hoursTotal;
    private long minutesTotal;
    private long gameTime;
    private long daysGaming;
    private long hoursGaming;
    private long minutesGaming;

    public StatsScreen(TheGame game) {
        super(game);
        initScreen();
    }

    @Override
    public void show() {
        updateData();
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            stage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    //gameBAS.setScreen(gameBAS.homeScreen);
                }
            })));
        }
    }

    private void initScreen() {
        /*labelStars = new Label("", gameBAS.styles.skinLabelStatsText);
        labelTotalTime = new Label("", gameBAS.styles.skinLabelStatsText);
        labelBattlingTime = new Label("", gameBAS.styles.skinLabelStatsText);
        labelKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelFighterKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelMachineGunKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelShotgunKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelFlamethrowerKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelKnifeThrowerKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelGrenadeLauncherKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelRocketLauncherKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelDynamiteLauncherKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelBladeLauncherKills = new Label("", gameBAS.styles.skinLabelStatsText);
        labelAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelMachineGunAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelShotgunAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelFlamethrowerAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelKnifeThrowerAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelGrenadeLauncherAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelRocketLauncherAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelDynamiteLauncherAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);
        labelBladeLauncherAccuracy = new Label("", gameBAS.styles.skinLabelStatsText);

        Table tableStats = new Table();
        tableStats.add(new Label("Stars", gameBAS.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelStars).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Total time", gameBAS.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelTotalTime).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Battling time", gameBAS.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelBattlingTime).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("", gameBAS.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(new Label("", gameBAS.styles.skinLabelStatsText)).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Kills", gameBAS.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelMachineGunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Fighter", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFighterKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Machine gun", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelMachineGunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Shotgun", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelShotgunKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Flamethrower", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFlamethrowerKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Knife thrower", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelKnifeThrowerKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Grenade launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelGrenadeLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Rocket launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelRocketLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Dynamite launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelDynamiteLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Blade launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelBladeLauncherKills).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("", gameBAS.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(new Label("", gameBAS.styles.skinLabelStatsText)).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Accuracy", gameBAS.styles.skinLabelStatsTitle)).align(Align.left).padRight(100);
        tableStats.add(labelAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Machine gun", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelMachineGunAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Shotgun", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelShotgunAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Flamethrower", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelFlamethrowerAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Knife thrower", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelKnifeThrowerAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Grenade launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelGrenadeLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Rocket launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelRocketLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Dynamite launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelDynamiteLauncherAccuracy).align(Align.right);
        tableStats.row();
        tableStats.add(new Label("Blade launcher", gameBAS.styles.skinLabelStatsSubTitle)).align(Align.left).padLeft(50).padRight(100);
        tableStats.add(labelBladeLauncherAccuracy).align(Align.right);

        ScrollPane scrollPane = new ScrollPane(tableStats, gameBAS.styles.skinScrollPane);
        scrollPane.getStyle().vScroll.setMinHeight(15);
        scrollPane.getStyle().hScroll.setMinWidth(15);
        scrollPane.getStyle().vScrollKnob.setMinHeight(15);
        scrollPane.getStyle().hScrollKnob.setMinWidth(15);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.layout();
        scrollPane.setScrollY(gameBAS.saveManager.saveData.getPriWeaScrollY());
        scrollPane.updateVisualScroll();
*/
        final TextButton btHome = new TextButton("HOME", game.styles.skinTextButtonOrange);
        Table tableButtons = new Table();
        tableButtons.add(btHome).width(Constants.TEXT_BUTTON_WIDTH).height(Constants.TEXT_BUTTON_HEIGHT).align(Align.right);

        Table tableContent = new Table();
        tableContent.add(new Label("STATS", game.styles.skinLabelWhiteHuge)).padBottom(Constants.GAP).expandX().align(Align.top).colspan(2);
        tableContent.row();
        //tableContent.add(scrollPane).fill().expand().align(Align.center).padTop(50);
        tableContent.add(tableButtons).expand().align(Align.right).padLeft(30);

        Table table = new Table();
        table.setFillParent(true);
        //table.background(new TextureRegionDrawable(new TextureRegion(gameBAS.getTextureRegionByID(Constants.TEX_SRC_BACKGROUND))));
        table.add(tableContent).align(Align.center).grow().pad(Constants.GAP);

        stage.addActor(table);

        btHome.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < btHome.getWidth() && y > 0 && y < btHome.getHeight()) {
                    //gameBAS.sounds.getSoundByID(Constants.SOUND_SRC_BUTTON_NEG).play(gameBAS.saveManager.saveData.getSoundVolume());
                    game.setScreen(game.creditsScreen);
                }
            }
        });
    }

    private void updateData() {
        /*labelStars.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getTotalStars()) + " / " + gameBAS.styles.getFormattedNumber(3 * Constants.GAME_MODES));
        labelKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getTotalKills()));
        labelFighterKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getFighterKills()));
        labelMachineGunKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.MACHINE_GUN_ID)));
        labelShotgunKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.SHOTGUN_ID)));
        labelFlamethrowerKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.FLAMETHROWER_ID)));
        labelKnifeThrowerKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.KNIFE_THROWER_ID)));
        labelGrenadeLauncherKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.GRENADE_LAUNCHER_ID)));
        labelRocketLauncherKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.ROCKET_LAUNCHER_ID)));
        labelDynamiteLauncherKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.DYNAMITE_LAUNCHER_ID)));
        labelBladeLauncherKills.setText(gameBAS.styles.getFormattedNumber(gameBAS.saveManager.saveData.getWeaponKills(Constants.BLADE_LAUNCHER_ID)));
        labelAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getTotalAccuracy()) + " %");
        labelMachineGunAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.MACHINE_GUN_ID)) + " %");
        labelShotgunAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.SHOTGUN_ID)) + " %");
        labelFlamethrowerAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.FLAMETHROWER_ID)) + " %");
        labelKnifeThrowerAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.KNIFE_THROWER_ID)) + " %");
        labelGrenadeLauncherAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.GRENADE_LAUNCHER_ID)) + " %");
        labelRocketLauncherAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.ROCKET_LAUNCHER_ID)) + " %");
        labelDynamiteLauncherAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.DYNAMITE_LAUNCHER_ID)) + " %");
        labelBladeLauncherAccuracy.setText(String.format(Locale.US, "%.2f", gameBAS.saveManager.saveData.getWeaponAccuracy(Constants.BLADE_LAUNCHER_ID)) + " %");

        appTime = gameBAS.saveManager.saveData.getAppTime();
        daysTotal = (long)(appTime / (24 * 60 * 60));
        appTime -= daysTotal * 24 * 60 * 60;
        hoursTotal = (long)(appTime / (60 * 60));
        appTime -= hoursTotal * 60 * 60;
        minutesTotal = (long)(appTime / 60);
        if (daysTotal == 0 && hoursTotal == 0)
            labelTotalTime.setText(minutesTotal + " min");
        else if (daysTotal == 0)
            labelTotalTime.setText(hoursTotal + " h  " + minutesTotal + " min");
        else
            labelTotalTime.setText(daysTotal + " d " + hoursTotal + " h  " + minutesTotal + " min");

        gameTime = gameBAS.saveManager.saveData.getGameTime();
        daysGaming = (long)(gameTime / (24 * 60 * 60));
        gameTime -= daysGaming * 24 * 60 * 60;
        hoursGaming = (long)(gameTime / (60 * 60));
        gameTime -= hoursGaming * 60 * 60;
        minutesGaming = (long)(gameTime / 60);
        if (daysGaming == 0 && hoursGaming == 0)
            labelBattlingTime.setText(minutesGaming + " min");
        else if (daysGaming == 0)
            labelBattlingTime.setText(hoursGaming + " h  " + minutesGaming + " min");
        else
            labelBattlingTime.setText(daysGaming + " d " + hoursGaming + " h  " + minutesGaming + " min");*/
    }
}