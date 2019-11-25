package com.aleksiprograms.survivalofkeijo.toolbox;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TextButtonWithID extends TextButton {

    public int ID;

    public TextButtonWithID(int ID, TextButtonStyle textButtonStyle) {
        super("", textButtonStyle);
        this.ID = ID;
    }
}