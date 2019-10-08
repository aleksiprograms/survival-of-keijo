package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.badlogic.gdx.utils.Pool;

public abstract class AreaObject implements Pool.Poolable {

    public int area;
    public float x;
    public float y;
    public float width;
    public float height;
    public boolean nullObject;

    public void init(int area, float x, float y, float width, float height, boolean nullObject) {
        this.area = area;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.nullObject = nullObject;
    }

    @Override
    public void reset() {}
}