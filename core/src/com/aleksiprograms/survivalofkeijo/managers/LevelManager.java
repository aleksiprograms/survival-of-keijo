package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.LevelData;

public class LevelManager {

    private TheGame game;
    public int currentLevel;
    private LevelData[] arrayLevelData = new LevelData[Constants.NUMBER_OF_LEVELS];
    private String[] levelNames = {"FIRST","SECOND","THIRD"};
    private String[] levelImageNames = {"1","2","3"};

    public LevelManager(TheGame game) {
        this.game = game;
        for (int i = 0; i < Constants.NUMBER_OF_LEVELS; i++) {
            arrayLevelData[i] = new LevelData(
                    i + 1,
                    levelNames[i],
                    levelImageNames[i]);
        }
    }

    public LevelData[] getArrayLevelData() {
        return arrayLevelData;
    }
}