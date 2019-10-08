package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.resources.SaveData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class SaveDataManager {

    private FileHandle fileHandle;
    private Json json;
    public SaveData saveData;

    public SaveDataManager() {
        fileHandle = Gdx.files.local("save_data.json");
        json = new Json();
        if (fileHandle.exists()) {
            saveData = json.fromJson(SaveData.class, Base64Coder.decodeString(fileHandle.readString()));
        } else {
            saveData = new SaveData();
        }
    }

    public void save() {
        json.setOutputType(JsonWriter.OutputType.json);
        fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(saveData)), false);
    }
}