package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.badlogic.gdx.utils.Array;

public class EnemyDropObject extends EnemyGuideObject {

    protected boolean approachableFromRight;

    public void init(
            float x,
            float y,
            float z,
            int bigArea,
            Array<Integer> toBigAreas,
            Array<Integer> toSmallAreas,
            boolean approachableFromRight) {
        super.init(x, y, z, bigArea, toBigAreas, toSmallAreas);
        this.approachableFromRight = approachableFromRight;
    }

    public boolean isApproachableFromRight() {
        return approachableFromRight;
    }
}