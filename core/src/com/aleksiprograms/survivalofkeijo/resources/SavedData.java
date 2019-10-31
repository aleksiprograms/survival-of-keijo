package com.aleksiprograms.survivalofkeijo.resources;

import java.io.Serializable;

public class SavedData implements Serializable {

    private String language;
    private boolean sounds;

    public SavedData() {
        language = null;
        sounds = true;
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
}