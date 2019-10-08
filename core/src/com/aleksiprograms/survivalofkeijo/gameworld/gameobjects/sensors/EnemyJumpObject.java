package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors;

import com.badlogic.gdx.utils.Array;

public class EnemyJumpObject extends EnemyGuideObject {

    public boolean rightJump;

    public void init(float x, float y, float z, int bigArea, Array<Integer> toBigAreas, Array<Integer> toSmallAreas, boolean rightJump) {
        super.init(x, y, z, bigArea, toBigAreas, toSmallAreas);
        this.rightJump = rightJump;
    }
}