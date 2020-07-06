package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public abstract class EnemyGuideObject implements Pool.Poolable {

    protected int bigArea;
    protected float x;
    protected float y;
    protected float z;
    protected Array<Integer> toBigAreas;
    protected Array<Integer> toSmallAreas;

    public void init(
            float x,
            float y,
            float z,
            int bigArea,
            Array<Integer> toBigAreas,
            Array<Integer> toSmallAreas) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.bigArea = bigArea;
        this.toBigAreas = toBigAreas;
        this.toSmallAreas = toSmallAreas;
    }

    @Override
    public void reset() {
        toBigAreas.clear();
        toSmallAreas.clear();
    }

    public int getBigArea() {
        return bigArea;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Array<Integer> getToBigAreas() {
        return toBigAreas;
    }

    public Array<Integer> getToSmallAreas() {
        return toSmallAreas;
    }
}