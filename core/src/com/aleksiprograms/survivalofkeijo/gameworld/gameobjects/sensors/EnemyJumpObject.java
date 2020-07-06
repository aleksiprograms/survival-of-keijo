package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.badlogic.gdx.utils.Array;

public class EnemyJumpObject extends EnemyGuideObject {

    protected boolean jumpToRight;

    public void init(
            float x,
            float y,
            float z,
            int bigArea,
            Array<Integer> toBigAreas,
            Array<Integer> toSmallAreas,
            boolean jumpToRight) {
        super.init(x, y, z, bigArea, toBigAreas, toSmallAreas);
        this.jumpToRight = jumpToRight;
    }

    public boolean isJumpToRight() {
        return jumpToRight;
    }
}