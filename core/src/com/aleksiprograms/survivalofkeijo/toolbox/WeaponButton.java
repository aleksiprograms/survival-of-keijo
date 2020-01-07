package com.aleksiprograms.survivalofkeijo.toolbox;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class WeaponButton extends Table {

    public int ID;
    public Label labelWeaponLevel;

    public WeaponButton(int ID) {
        super();
        this.ID = ID;
    }
}