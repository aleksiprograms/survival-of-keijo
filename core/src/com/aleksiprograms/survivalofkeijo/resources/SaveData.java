package com.aleksiprograms.survivalofkeijo.resources;

import java.io.Serializable;

public class SaveData implements Serializable {

    private String language;
    private boolean sounds;
    private boolean debugDrawWorld;
    private boolean debugDrawUI;
    private boolean showPauseButton;
    private boolean aspectRatio169;

    public SaveData() {
        language = null;
        sounds = true;
        debugDrawWorld = false;
        debugDrawUI = false;
        showPauseButton = true;
        aspectRatio169 = false;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isSounds() {
        return sounds;
    }

    public void setSounds(boolean sounds) {
        this.sounds = sounds;
    }

    public boolean isDebugDrawWorld() {
        return debugDrawWorld;
    }

    public void setDebugDrawWorld(boolean debugDrawWorld) {
        this.debugDrawWorld = debugDrawWorld;
    }

    public boolean isDebugDrawUI() {
        return debugDrawUI;
    }

    public void setDebugDrawUI(boolean debugDrawUI) {
        this.debugDrawUI = debugDrawUI;
    }

    public boolean isShowPauseButton() {
        return showPauseButton;
    }

    public void setShowPauseButton(boolean showPauseButton) {
        this.showPauseButton = showPauseButton;
    }

    public boolean isAspectRatio169() {
        return aspectRatio169;
    }

    public void setAspectRatio169(boolean aspectRatio169) {
        this.aspectRatio169 = aspectRatio169;
    }
}