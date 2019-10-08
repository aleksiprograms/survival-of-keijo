package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public abstract class EnemyGuideObject implements Pool.Poolable {

    public int bigArea;
    public float x;
    public float y;
    public float z;
    public Array<Integer> toBigAreas;
    public Array<Integer> toSmallAreas;

    public void init(float x, float y, float z, int bigArea, Array<Integer> toBigAreas, Array<Integer> toSmallAreas) {
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
}