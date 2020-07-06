package com.aleksiprograms.survivalofkeijo.toolbox;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class WeaponButton extends Table {

    public int weaponButtonID;
    public Label labelWeaponLevel;

    public WeaponButton(int weaponButtonID) {
        super();
        this.weaponButtonID = weaponButtonID;
    }
}