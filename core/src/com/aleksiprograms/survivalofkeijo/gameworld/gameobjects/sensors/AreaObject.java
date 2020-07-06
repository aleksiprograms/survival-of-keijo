package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.badlogic.gdx.utils.Pool;

public abstract class AreaObject implements Pool.Poolable {

    protected int area;
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected boolean nullObject;

    public void init(
            int area,
            float x,
            float y,
            float width,
            float height,
            boolean nullObject) {
        this.area = area;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.nullObject = nullObject;
    }

    @Override
    public void reset() {
    }

    public int getArea() {
        return area;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean isNullObject() {
        return nullObject;
    }
}