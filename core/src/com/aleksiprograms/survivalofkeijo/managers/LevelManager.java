package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.toolbox.LevelInfo;

public class LevelManager {

    private TheGame game;
    private LevelInfo[] levelInfos = new LevelInfo[Constants.NUMBER_OF_LEVELS];
    private String[] levelNames = {"FIRST","SECOND","THIRD","FOURTH","FIFTH","SIXTH"};
    private String[] levelImageNames = {"1","2","3","4","5","6"};

    public LevelManager(TheGame game) {
        this.game = game;
        for (int i = 0; i < Constants.NUMBER_OF_LEVELS; i++) {
            levelInfos[i] = new LevelInfo(
                    i,
                    levelNames[i],
                    levelImageNames[i]);
        }
    }

    public LevelInfo[] getLevelInfos() {
        return levelInfos;
    }
}