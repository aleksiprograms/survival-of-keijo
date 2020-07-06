package com.aleksiprograms.survivalofkeijo.managers;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters.EnemyEmitter;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class EnemyManager {

    public int health;
    private TheGame game;
    private int wave;
    private boolean inTheBeginningOfNewWave;
    private Array<EnemyEmitter> enemyEmitters;
    private int emitEnemyFrom;
    private int emitEnemyFromPrevious = -1;
    private int emitEnemyFromIterations;
    private int enemiesInWorld;
    private int maxEnemiesInWorld;
    private int maxEnemiesInWave;
    private int enemiesEmittedInWave;
    private int enemiesKilledInWave;
    private float timerEnemyEmit;
    private float timeBetweenEnemyEmits;
    private int waveStartTime = 5;
    private float startWaveTimer;
    private int weaponID;
    private Array<Integer> weapons;
    private int weaponsIndex;
    private int[][] numberOfWeapons;
    private float[][] percentsOfWeapons = {
            {0.2f, 0.2f, 0.3f, 0.4f, 0.4f, 0.4f, 0.4f, 0.4f, 0.4f, 0.4f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
            {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}};

    public EnemyManager(TheGame game) {
        this.game = game;
        enemyEmitters = new Array<EnemyEmitter>();
        weapons = new Array<Integer>();
        numberOfWeapons = new int[Constants.NUMBER_OF_WEAPONS - 1][2];
    }

    public void update(float deltaTime) {
        if (inTheBeginningOfNewWave) {
            startWaveTimer += deltaTime;
            if (startWaveTimer > waveStartTime) {
                inTheBeginningOfNewWave = false;
                game.getGameScreen().getInGameHud().updateData();
            }
        } else {
            if (enemiesEmittedInWave < maxEnemiesInWave) {
                if (enemiesInWorld < maxEnemiesInWorld) {
                    timerEnemyEmit += deltaTime;
                    if (timerEnemyEmit > timeBetweenEnemyEmits) {
                        timerEnemyEmit = 0;
                        emitEnemyFrom = MathUtils.random(0, enemyEmitters.size - 1);
                        emitEnemyFromIterations = 0;
                        while (true) {
                            emitEnemyFromIterations++;
                            if (emitEnemyFrom == emitEnemyFromPrevious) {
                                if (emitEnemyFromIterations > 5) {
                                    emitEnemyFrom = emitEnemyFrom == 0 ? 1 : 0;
                                    break;
                                } else {
                                    emitEnemyFrom = MathUtils.random(0, enemyEmitters.size - 1);
                                }
                            } else {
                                emitEnemyFromPrevious = emitEnemyFrom;
                                break;
                            }
                        }
                        weaponsIndex = MathUtils.random(0, weapons.size - 1);
                        weaponID = weapons.get(weaponsIndex);
                        weapons.removeIndex(weaponsIndex);
                        enemyEmitters.get(emitEnemyFrom).emitEnemy(weaponID, health);
                        enemiesInWorld++;
                        enemiesEmittedInWave++;
                    }
                }
            }
        }
        if (enemiesKilledInWave == maxEnemiesInWave) {
            if (!inTheBeginningOfNewWave) {
                initNewWave();
            }
        }
    }

    public void enemyDied() {
        enemiesInWorld--;
        enemiesKilledInWave++;
    }

    public void addEnemyEmitter(EnemyEmitter enemyEmitter) {
        enemyEmitters.add(enemyEmitter);
    }

    public void init() {
        wave = 0;
        emitEnemyFromPrevious = -1;
        initNewWave();
    }

    private void initNewWave() {
        inTheBeginningOfNewWave = true;
        startWaveTimer = 0;
        updateNextWaveData();
        game.getGameScreen().getInGameHud().updateData();
    }

    private void updateNextWaveData() {
        wave++;
        if (wave >= 15) {
            maxEnemiesInWorld = 6;
        } else if (wave >= 10) {
            maxEnemiesInWorld = 5;
        } else if (wave >= 5) {
            maxEnemiesInWorld = 4;
        } else if (wave >= 0) {
            maxEnemiesInWorld = 4; //3
        }
        maxEnemiesInWave = 30 + (wave - 1) * 5;
        setNumberOfWeapons(wave - 1);
        weapons.clear();
        for (int i = 0; i < numberOfWeapons.length; i++) {
            for (int j = 0; j < numberOfWeapons[i][1]; j++) {
                weapons.add(numberOfWeapons[i][0]);
            }
        }
        for (int i = weapons.size - 1; i < maxEnemiesInWave; i++) {
            weapons.add(Constants.PISTOL_ID);
        }
        timeBetweenEnemyEmits = 2;
        enemiesEmittedInWave = 0;
        enemiesKilledInWave = 0;
        enemiesInWorld = 0;
        timerEnemyEmit = timeBetweenEnemyEmits;
        health = 100 + ((wave - 1) * 50);
    }

    private void setNumberOfWeapons(int wave) {
        for (int i = 0; i < numberOfWeapons.length; i++) {
            if ((i + 1) == Constants.ASSAULT_RIFLE_ID) {
                numberOfWeapons[i][0] = Constants.ASSAULT_RIFLE_ID;
                numberOfWeapons[i][1] = (int) (maxEnemiesInWave
                        * (wave < percentsOfWeapons[0].length
                        ? percentsOfWeapons[0][wave] : 0.1f));
            } else if ((i + 1) == Constants.SHOTGUN_ID) {
                numberOfWeapons[i][0] = Constants.SHOTGUN_ID;
                numberOfWeapons[i][1] = (int) (maxEnemiesInWave
                        * (wave < percentsOfWeapons[0].length
                        ? percentsOfWeapons[1][wave] : 0.1f));
            } else if ((i + 1) == Constants.SNIPER_ID) {
                numberOfWeapons[i][0] = Constants.SNIPER_ID;
                numberOfWeapons[i][1] = (int) (maxEnemiesInWave
                        * (wave < percentsOfWeapons[0].length
                        ? percentsOfWeapons[2][wave] : 0.1f));
            } else if ((i + 1) == Constants.MACHINE_GUN_ID) {
                numberOfWeapons[i][0] = Constants.MACHINE_GUN_ID;
                numberOfWeapons[i][1] = (int) (maxEnemiesInWave
                        * (wave < percentsOfWeapons[0].length
                        ? percentsOfWeapons[3][wave] : 0.1f));
            } else if ((i + 1) == Constants.ROCKET_LAUNCHER_ID) {
                numberOfWeapons[i][0] = Constants.ROCKET_LAUNCHER_ID;
                numberOfWeapons[i][1] = (int) (maxEnemiesInWave
                        * (wave < percentsOfWeapons[0].length
                        ? percentsOfWeapons[4][wave] : 0.1f));
            }
        }
    }

    public void clear() {
        enemyEmitters.clear();
    }

    public boolean isInTheBeginningOfNewWave() {
        return inTheBeginningOfNewWave;
    }
}