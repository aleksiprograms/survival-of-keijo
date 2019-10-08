package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyDropObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyGuideObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyJumpObject;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class BigAreaManager implements Pool.Poolable {

    public TheGame game;
    public int bigAreaId;
    public Array<BigAreaObject> bigAreas;
    public Array<SmallAreaObject> smallAreas;
    public Array<EnemyGuideObject> enemyGuides;
    public BigAreaObject bigAreaNull;
    public SmallAreaObject smallAreaNull;

    public BigAreaManager(TheGame game) {
        this.game = game;
        bigAreas = new Array<BigAreaObject>();
        smallAreas = new Array<SmallAreaObject>();
        enemyGuides = new Array<EnemyGuideObject>();
    }

    public void init(int bigAreaId) {
        this.bigAreaId = bigAreaId;
        bigAreaNull = game.gamePools.bigAreaObjectPool.obtain();
        bigAreaNull.init(0,0,0,0,0,true);
        smallAreaNull = game.gamePools.smallAreaObjectPool.obtain();
        smallAreaNull.init(0,0,0,0,0,true);
    }

    @Override
    public void reset() {
        for (int i = 0; i < bigAreas.size; i++) {
            game.gamePools.bigAreaObjectPool.free(bigAreas.get(i));
        }
        for (int i = 0; i < smallAreas.size; i++) {
            game.gamePools.smallAreaObjectPool.free(smallAreas.get(i));
        }
        for (int i = 0; i < enemyGuides.size; i++) {
            if (enemyGuides.get(i) instanceof EnemyJumpObject) {
                game.gamePools.enemyJumpObjectPool.free((EnemyJumpObject) enemyGuides.get(i));
            } else if (enemyGuides.get(i) instanceof EnemyDropObject) {
                game.gamePools.enemyDropObjectPool.free((EnemyDropObject) enemyGuides.get(i));
            }
        }
        bigAreas.clear();
        smallAreas.clear();
        enemyGuides.clear();
        game.gamePools.bigAreaObjectPool.free(bigAreaNull);
        game.gamePools.smallAreaObjectPool.free(smallAreaNull);
    }
}