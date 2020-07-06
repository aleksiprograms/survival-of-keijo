package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.screens.huds.InGameHud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ControlManager {

    private float timeFromLastButtonLeftPress = 0;
    private float timeFromLastButtonRightPress = 0;
    private float timeFromLastButtonUpPress = 0;
    private float timeFromLastButtonShootPress = 0;
    private float timeFromLastButtonReloadPress = 0;

    private boolean buttonLeftPressed = false;
    private boolean buttonRightPressed = false;
    private boolean buttonUpPressed = false;
    private boolean buttonShootPressed = false;
    private boolean buttonReloadPressed = false;

    public ControlManager() {
    }

    public void update(float deltaTime) {
        timeFromLastButtonLeftPress += deltaTime;
        timeFromLastButtonRightPress += deltaTime;
        timeFromLastButtonUpPress += deltaTime;
        timeFromLastButtonShootPress += deltaTime;
        timeFromLastButtonReloadPress += deltaTime;

        if (timeFromLastButtonLeftPress > 0.1f) {
            buttonLeftPressed = false;
        }
        if (timeFromLastButtonRightPress > 0.1f) {
            buttonRightPressed = false;
        }
        if (timeFromLastButtonUpPress > 0.1f) {
            buttonUpPressed = false;
        }
        if (timeFromLastButtonShootPress > 0.1f) {
            buttonShootPressed = false;
        }
        if (timeFromLastButtonReloadPress > 0.1f) {
            buttonReloadPressed = false;
        }

        if (InGameHud.isButtonLeftPressed() || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            timeFromLastButtonLeftPress = 0;
            if (!buttonLeftPressed) {
                buttonLeftPressed = true;
            }
        }
        if (InGameHud.isButtonRightPressed() || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            timeFromLastButtonRightPress = 0;
            if (!buttonRightPressed)
                buttonRightPressed = true;
        }
        if (InGameHud.isButtonUpPressed() || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            timeFromLastButtonUpPress = 0;
            if (!buttonUpPressed) {
                buttonUpPressed = true;
            }
        }
        if (InGameHud.isButtonShootPressed() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            timeFromLastButtonShootPress = 0;
            if (!buttonShootPressed) {
                buttonShootPressed = true;
            }
        }
        if (InGameHud.isButtonReloadPressed() || Gdx.input.isKeyPressed(Input.Keys.R)) {
            timeFromLastButtonReloadPress = 0;
            if (!buttonReloadPressed) {
                buttonReloadPressed = true;
            }
        }
    }

    public boolean isButtonLeftPressed() {
        return buttonLeftPressed;
    }

    public boolean isButtonRightPressed() {
        return buttonRightPressed;
    }

    public boolean isButtonUpPressed() {
        return buttonUpPressed;
    }

    public boolean isButtonShootPressed() {
        return buttonShootPressed;
    }

    public boolean isButtonReloadPressed() {
        return buttonReloadPressed;
    }
}