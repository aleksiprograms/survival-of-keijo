package com.aleksiprograms.survivalofkeijo.screens.huds;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class AbstractHud extends Table {

    public TheGame game;

    AbstractHud(final TheGame game) {
        this.game = game;
    }

    protected void initialize() {
    }

    public void resetHud() {
    }

    public void updateData() {
    }
}