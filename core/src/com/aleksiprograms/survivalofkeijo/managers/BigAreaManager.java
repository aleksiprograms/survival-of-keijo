package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyDropObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyGuideObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyJumpObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class BigAreaManager implements Pool.Poolable {

    private TheGame game;
    private int bigAreaID;
    private Array<BigAreaObject> bigAreas;
    private Array<SmallAreaObject> smallAreas;
    private Array<EnemyGuideObject> enemyGuides;
    private BigAreaObject bigAreaNull;
    private SmallAreaObject smallAreaNull;

    public BigAreaManager(TheGame game) {
        this.game = game;
        bigAreas = new Array<BigAreaObject>();
        smallAreas = new Array<SmallAreaObject>();
        enemyGuides = new Array<EnemyGuideObject>();
    }

    public void init(int bigAreaId) {
        this.bigAreaID = bigAreaId;
        bigAreaNull = game.getGameObjectPools().getBigAreaObjectPool().obtain();
        bigAreaNull.init(0, 0, 0, 0, 0, true);
        smallAreaNull = game.getGameObjectPools().getSmallAreaObjectPool().obtain();
        smallAreaNull.init(0, 0, 0, 0, 0, true);
    }

    @Override
    public void reset() {
        for (int i = 0; i < bigAreas.size; i++) {
            game.getGameObjectPools().getBigAreaObjectPool().free(bigAreas.get(i));
        }
        for (int i = 0; i < smallAreas.size; i++) {
            game.getGameObjectPools().getSmallAreaObjectPool().free(smallAreas.get(i));
        }
        for (int i = 0; i < enemyGuides.size; i++) {
            if (enemyGuides.get(i) instanceof EnemyJumpObject) {
                game.getGameObjectPools().getEnemyJumpObjectPool().free(
                        (EnemyJumpObject) enemyGuides.get(i));
            } else if (enemyGuides.get(i) instanceof EnemyDropObject) {
                game.getGameObjectPools().getEnemyDropObjectPool().free(
                        (EnemyDropObject) enemyGuides.get(i));
            }
        }
        bigAreas.clear();
        smallAreas.clear();
        enemyGuides.clear();
        game.getGameObjectPools().getBigAreaObjectPool().free(bigAreaNull);
        game.getGameObjectPools().getSmallAreaObjectPool().free(smallAreaNull);
    }

    public int getBigAreaID() {
        return bigAreaID;
    }

    public Array<BigAreaObject> getBigAreas() {
        return bigAreas;
    }

    public Array<SmallAreaObject> getSmallAreas() {
        return smallAreas;
    }

    public Array<EnemyGuideObject> getEnemyGuides() {
        return enemyGuides;
    }

    public BigAreaObject getBigAreaNull() {
        return bigAreaNull;
    }

    public SmallAreaObject getSmallAreaNull() {
        return smallAreaNull;
    }
}