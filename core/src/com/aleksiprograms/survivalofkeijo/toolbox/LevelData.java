package com.aleksiprograms.survivalofkeijo.toolbox;

public class LevelData {

    private int levelID;
    private String name;
    private String imageName;

    public LevelData(
            int levelID,
            String name,
            String imageName) {

        this.levelID = levelID;
        this.name = name;
        this.imageName = imageName;
    }

    public int getLevelID() {
        return levelID;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }
}