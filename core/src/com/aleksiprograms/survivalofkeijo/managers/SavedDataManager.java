package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.resources.SavedData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class SavedDataManager {

    private FileHandle fileHandle;
    private Json json;
    public SavedData savedData;

    public SavedDataManager() {
        fileHandle = Gdx.files.local(Constants.SAVED_DATA_FILE_NAME);
        json = new Json();
        if (fileHandle.exists()) {
            savedData = json.fromJson(SavedData.class, Base64Coder.decodeString(fileHandle.readString()));
        } else {
            savedData = new SavedData();
        }
    }

    public void save() {
        json.setOutputType(JsonWriter.OutputType.json);
        fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(savedData)), false);
    }
}