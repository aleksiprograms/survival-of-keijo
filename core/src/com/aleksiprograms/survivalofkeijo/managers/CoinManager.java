package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters.CoinEmitter;
import com.aleksiprograms.survivalofkeijo.toolbox.CoinType;
import com.badlogic.gdx.utils.Array;

public class CoinManager {

    private Array<CoinEmitter> coinEmitters;

    public CoinManager() {
        coinEmitters = new Array<CoinEmitter>();
    }

    public void update(float deltaTime) {
        for (int i = 0; i < coinEmitters.size; i++) {
            if (!coinEmitters.get(i).isEmitted()) {
                coinEmitters.get(i).emitCoin(CoinType.GOLD);
            }
        }
    }

    public void addCoinEmitter(CoinEmitter coinEmitter, float x, float y, float z) {
        coinEmitters.add(coinEmitter);
        coinEmitter.init(x, y, z);
    }
}