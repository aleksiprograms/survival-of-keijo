package com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.toolbox.CoinType;
import com.badlogic.gdx.utils.Pool;

public class CoinEmitter implements Pool.Poolable {

    private TheGame game;
    private float x;
    private float y;
    private float z;
    public boolean emitted;

    public CoinEmitter(TheGame game) {
        this.game = game;
    }

    public void init(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        emitted = false;
    }

    public void emitCoin(CoinType coinType) {
        if (coinType.equals(CoinType.BRONZE)) {
            game.gameWorld.addCoin(game.gamePools.coinPool.obtain(), x, y, z);
        } else if (coinType.equals(CoinType.SILVER)) {
            game.gameWorld.addCoin(game.gamePools.coinPool.obtain(), x, y, z);
        } else if (coinType.equals(CoinType.GOLD)) {
            game.gameWorld.addCoin(game.gamePools.coinPool.obtain(), x, y, z);
        }
        emitted = true;
    }

    @Override
    public void reset() {}
}