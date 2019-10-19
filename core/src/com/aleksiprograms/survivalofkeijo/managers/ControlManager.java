package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.screens.huds.InGameHud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ControlManager {

    private float timeFromLastBtLeftPress   = 0;
    private float timeFromLastBtRightPress  = 0;
    private float timeFromLastBtUpPress     = 0;
    private float timeFromLastBtDownPress   = 0;
    private float timeFromLastBtShootPress  = 0;
    private float timeFromLastBtReloadPress = 0;

    public boolean btLeftPressed   = false;
    public boolean btRightPressed  = false;
    public boolean btUpPressed     = false;
    public boolean btDownPressed   = false;
    public boolean btShootPressed  = false;
    public boolean btReloadPressed = false;

    public ControlManager() {}

    public void update(float deltaTime) {
        timeFromLastBtLeftPress += deltaTime;
        timeFromLastBtRightPress += deltaTime;
        timeFromLastBtUpPress += deltaTime;
        timeFromLastBtDownPress += deltaTime;
        timeFromLastBtShootPress += deltaTime;
        timeFromLastBtReloadPress += deltaTime;

        if (timeFromLastBtLeftPress > 0.05f)
            btLeftPressed = false;
        if (timeFromLastBtRightPress > 0.05f)
            btRightPressed = false;
        if (timeFromLastBtUpPress > 0.05f)
            btUpPressed = false;
        if (timeFromLastBtDownPress > 0.05f)
            btDownPressed = false;
        if (timeFromLastBtShootPress > 0.05f)
            btShootPressed = false;
        if (timeFromLastBtReloadPress > 0.05f)
            btReloadPressed = false;

        if (InGameHud.buttonLeftPressed || Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            timeFromLastBtLeftPress = 0;
            if (!btLeftPressed)
                btLeftPressed = true;
        }
        if (InGameHud.buttonRightPressed || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            timeFromLastBtRightPress = 0;
            if (!btRightPressed)
                btRightPressed = true;
        }
        if (InGameHud.buttonUpPressed || Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            timeFromLastBtUpPress = 0;
            if (!btUpPressed)
                btUpPressed = true;
        }
        if (InGameHud.buttonDownPressed || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            timeFromLastBtDownPress = 0;
            if (!btDownPressed)
                btDownPressed = true;
        }
        if (InGameHud.buttonShootPressed || Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            timeFromLastBtShootPress = 0;
            if (!btShootPressed)
                btShootPressed = true;
        }
        if (InGameHud.buttonReloadPressed) {
            timeFromLastBtReloadPress = 0;
            if (!btReloadPressed)
                btReloadPressed = true;
        }
    }
}