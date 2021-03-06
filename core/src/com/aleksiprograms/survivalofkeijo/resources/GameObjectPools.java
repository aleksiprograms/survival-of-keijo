package com.aleksiprograms.survivalofkeijo.resources;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameeffects.AmmunitionHitGrass;
import com.aleksiprograms.survivalofkeijo.gameworld.gameeffects.AmmunitionHitGround;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.BulletEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.BulletPlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.CaseEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.CasePlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Rocket;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Shot;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.collectibles.Coin;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters.CoinEmitter;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters.EnemyEmitter;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Grass;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundH10;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundTop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundV11;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundV5;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Ice;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockH10;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockSurface;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockTop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockV11;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockV5;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Sky;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowH10;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowSurface;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowTop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowV11;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowV5;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Enemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Player;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyDropObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyJumpObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.AssaultRifleEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.AssaultRiflePlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.MachineGun;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.PistolEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.PistolPlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.RocketLauncher;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Shotgun;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.Sniper;
import com.aleksiprograms.survivalofkeijo.managers.BigAreaManager;
import com.aleksiprograms.survivalofkeijo.toolbox.PFXPool;
import com.badlogic.gdx.utils.Pool;

public class GameObjectPools {

    private Pool<Player> playerPool;
    private Pool<Enemy> enemyPool;
    private Pool<PistolPlayer> pistolPlayerPool;
    private Pool<PistolEnemy> pistolEnemyPool;
    private Pool<AssaultRiflePlayer> assaultRiflePlayerPool;
    private Pool<AssaultRifleEnemy> assaultRifleEnemyPool;
    private Pool<Shotgun> shotgunPool;
    private Pool<Sniper> sniperPool;
    private Pool<MachineGun> machineGunPool;
    private Pool<RocketLauncher> rocketLauncherPool;
    private Pool<EnemyEmitter> enemyEmitterPool;
    private Pool<Coin> coinPool;
    private Pool<CoinEmitter> coinEmitterPool;
    private Pool<Sky> backgroundPool;
    private Pool<GroundH10> groundH10Pool;
    private Pool<GroundV5> groundV5Pool;
    private Pool<GroundV11> groundV11Pool;
    private Pool<GroundTop> groundTopPool;
    private Pool<Grass> grassPool;
    private Pool<RockH10> rockH10Pool;
    private Pool<RockV5> rockV5Pool;
    private Pool<RockV11> rockV11Pool;
    private Pool<RockTop> rockTopPool;
    private Pool<RockSurface> rockSurfacePool;
    private Pool<SnowH10> snowH10Pool;
    private Pool<SnowV5> snowV5Pool;
    private Pool<SnowV11> snowV11Pool;
    private Pool<SnowTop> snowTopPool;
    private Pool<SnowSurface> snowSurfacePool;
    private Pool<Ice> icePool;
    private Pool<BulletPlayer> bulletPlayerPool;
    private Pool<BulletEnemy> bulletEnemyPool;
    private Pool<CasePlayer> casePlayerPool;
    private Pool<CaseEnemy> caseEnemyPool;
    private Pool<Shot> shotPool;
    private Pool<Rocket> rocketPool;
    private Pool<BigAreaManager> bigAreaManagerPool;
    private Pool<BigAreaObject> bigAreaObjectPool;
    private Pool<SmallAreaObject> smallAreaObjectPool;
    private Pool<EnemyJumpObject> enemyJumpObjectPool;
    private Pool<EnemyDropObject> enemyDropObjectPool;
    private PFXPool ammunitionGroundHitPool;
    private PFXPool ammunitionGrassHitPool;
    private PFXPool rocketExplosionPool;

    public GameObjectPools(final TheGame game) {
        initialize(game);
    }

    private void initialize(final TheGame game) {
        playerPool = new Pool<Player>(1, 2) {
            @Override
            protected Player newObject() {
                return new Player(game);
            }
        };

        enemyPool = new Pool<Enemy>(6, 12) {
            @Override
            protected Enemy newObject() {
                return new Enemy(game);
            }
        };

        pistolPlayerPool = new Pool<PistolPlayer>(1, 2) {
            @Override
            protected PistolPlayer newObject() {
                return new PistolPlayer(game);
            }
        };

        pistolEnemyPool = new Pool<PistolEnemy>(6, 12) {
            @Override
            protected PistolEnemy newObject() {
                return new PistolEnemy(game);
            }
        };

        assaultRiflePlayerPool = new Pool<AssaultRiflePlayer>(1, 2) {
            @Override
            protected AssaultRiflePlayer newObject() {
                return new AssaultRiflePlayer(game);
            }
        };

        assaultRifleEnemyPool = new Pool<AssaultRifleEnemy>(6, 12) {
            @Override
            protected AssaultRifleEnemy newObject() {
                return new AssaultRifleEnemy(game);
            }
        };

        shotgunPool = new Pool<Shotgun>(6, 12) {
            @Override
            protected Shotgun newObject() {
                return new Shotgun(game);
            }
        };

        sniperPool = new Pool<Sniper>(6, 12) {
            @Override
            protected Sniper newObject() {
                return new Sniper(game);
            }
        };

        machineGunPool = new Pool<MachineGun>(6, 12) {
            @Override
            protected MachineGun newObject() {
                return new MachineGun(game);
            }
        };

        rocketLauncherPool = new Pool<RocketLauncher>(6, 12) {
            @Override
            protected RocketLauncher newObject() {
                return new RocketLauncher(game);
            }
        };

        enemyEmitterPool = new Pool<EnemyEmitter>(3, 10) {
            @Override
            protected EnemyEmitter newObject() {
                return new EnemyEmitter(game);
            }
        };

        coinPool = new Pool<Coin>(1, 1) {
            @Override
            protected Coin newObject() {
                return new Coin(game);
            }
        };

        coinEmitterPool = new Pool<CoinEmitter>(1, 1) {
            @Override
            protected CoinEmitter newObject() {
                return new CoinEmitter(game);
            }
        };

        backgroundPool = new Pool<Sky>(1, 1) {
            @Override
            protected Sky newObject() {
                return new Sky(game);
            }
        };

        groundH10Pool = new Pool<GroundH10>(5, 50) {
            @Override
            protected GroundH10 newObject() {
                return new GroundH10(game);
            }
        };

        groundV5Pool = new Pool<GroundV5>(5, 50) {
            @Override
            protected GroundV5 newObject() {
                return new GroundV5(game);
            }
        };

        groundV11Pool = new Pool<GroundV11>(5, 50) {
            @Override
            protected GroundV11 newObject() {
                return new GroundV11(game);
            }
        };

        groundTopPool = new Pool<GroundTop>(1, 2) {
            @Override
            protected GroundTop newObject() {
                return new GroundTop(game);
            }
        };

        grassPool = new Pool<Grass>(5, 100) {
            @Override
            protected Grass newObject() {
                return new Grass(game);
            }
        };

        rockH10Pool = new Pool<RockH10>(5, 50) {
            @Override
            protected RockH10 newObject() {
                return new RockH10(game);
            }
        };

        rockV5Pool = new Pool<RockV5>(5, 50) {
            @Override
            protected RockV5 newObject() {
                return new RockV5(game);
            }
        };

        rockV11Pool = new Pool<RockV11>(5, 50) {
            @Override
            protected RockV11 newObject() {
                return new RockV11(game);
            }
        };

        rockTopPool = new Pool<RockTop>(1, 2) {
            @Override
            protected RockTop newObject() {
                return new RockTop(game);
            }
        };

        rockSurfacePool = new Pool<RockSurface>(5, 100) {
            @Override
            protected RockSurface newObject() {
                return new RockSurface(game);
            }
        };

        snowH10Pool = new Pool<SnowH10>(5, 50) {
            @Override
            protected SnowH10 newObject() {
                return new SnowH10(game);
            }
        };

        snowV5Pool = new Pool<SnowV5>(5, 50) {
            @Override
            protected SnowV5 newObject() {
                return new SnowV5(game);
            }
        };

        snowV11Pool = new Pool<SnowV11>(5, 50) {
            @Override
            protected SnowV11 newObject() {
                return new SnowV11(game);
            }
        };

        snowTopPool = new Pool<SnowTop>(1, 2) {
            @Override
            protected SnowTop newObject() {
                return new SnowTop(game);
            }
        };

        snowSurfacePool = new Pool<SnowSurface>(5, 100) {
            @Override
            protected SnowSurface newObject() {
                return new SnowSurface(game);
            }
        };

        icePool = new Pool<Ice>(5, 100) {
            @Override
            protected Ice newObject() {
                return new Ice(game);
            }
        };

        bulletPlayerPool = new Pool<BulletPlayer>(5, 100) {
            @Override
            protected BulletPlayer newObject() {
                return new BulletPlayer(game);
            }
        };

        bulletEnemyPool = new Pool<BulletEnemy>(5, 100) {
            @Override
            protected BulletEnemy newObject() {
                return new BulletEnemy(game);
            }
        };

        casePlayerPool = new Pool<CasePlayer>(5, 100) {
            @Override
            protected CasePlayer newObject() {
                return new CasePlayer(game);
            }
        };

        caseEnemyPool = new Pool<CaseEnemy>(5, 100) {
            @Override
            protected CaseEnemy newObject() {
                return new CaseEnemy(game);
            }
        };

        shotPool = new Pool<Shot>(1, 1) {
            @Override
            protected Shot newObject() {
                return new Shot(game);
            }
        };

        rocketPool = new Pool<Rocket>(1, 20) {
            @Override
            protected Rocket newObject() {
                return new Rocket(game);
            }
        };

        bigAreaManagerPool = new Pool<BigAreaManager>(8, 20) {
            @Override
            protected BigAreaManager newObject() {
                return new BigAreaManager(game);
            }
        };

        bigAreaObjectPool = new Pool<BigAreaObject>(1, 100) {
            @Override
            protected BigAreaObject newObject() {
                return new BigAreaObject();
            }
        };

        smallAreaObjectPool = new Pool<SmallAreaObject>(1, 300) {
            @Override
            protected SmallAreaObject newObject() {
                return new SmallAreaObject();
            }
        };

        enemyJumpObjectPool = new Pool<EnemyJumpObject>(1, 500) {
            @Override
            protected EnemyJumpObject newObject() {
                return new EnemyJumpObject();
            }
        };

        enemyDropObjectPool = new Pool<EnemyDropObject>(1, 500) {
            @Override
            protected EnemyDropObject newObject() {
                return new EnemyDropObject();
            }
        };

        ammunitionGroundHitPool = new PFXPool(
                new AmmunitionHitGround(game), 50, 100);

        ammunitionGrassHitPool = new PFXPool(
                new AmmunitionHitGrass(game), 50, 100);
    }

    public Pool<Player> getPlayerPool() {
        return playerPool;
    }

    public Pool<Enemy> getEnemyPool() {
        return enemyPool;
    }

    public Pool<PistolPlayer> getPistolPlayerPool() {
        return pistolPlayerPool;
    }

    public Pool<PistolEnemy> getPistolEnemyPool() {
        return pistolEnemyPool;
    }

    public Pool<AssaultRiflePlayer> getAssaultRiflePlayerPool() {
        return assaultRiflePlayerPool;
    }

    public Pool<AssaultRifleEnemy> getAssaultRifleEnemyPool() {
        return assaultRifleEnemyPool;
    }

    public Pool<Shotgun> getShotgunPool() {
        return shotgunPool;
    }

    public Pool<Sniper> getSniperPool() {
        return sniperPool;
    }

    public Pool<MachineGun> getMachineGunPool() {
        return machineGunPool;
    }

    public Pool<RocketLauncher> getRocketLauncherPool() {
        return rocketLauncherPool;
    }

    public Pool<EnemyEmitter> getEnemyEmitterPool() {
        return enemyEmitterPool;
    }

    public Pool<Coin> getCoinPool() {
        return coinPool;
    }

    public Pool<CoinEmitter> getCoinEmitterPool() {
        return coinEmitterPool;
    }

    public Pool<Sky> getBackgroundPool() {
        return backgroundPool;
    }

    public Pool<GroundH10> getGroundH10Pool() {
        return groundH10Pool;
    }

    public Pool<GroundV5> getGroundV5Pool() {
        return groundV5Pool;
    }

    public Pool<GroundV11> getGroundV11Pool() {
        return groundV11Pool;
    }

    public Pool<GroundTop> getGroundTopPool() {
        return groundTopPool;
    }

    public Pool<Grass> getGrassPool() {
        return grassPool;
    }

    public Pool<RockH10> getRockH10Pool() {
        return rockH10Pool;
    }

    public Pool<RockV5> getRockV5Pool() {
        return rockV5Pool;
    }

    public Pool<RockV11> getRockV11Pool() {
        return rockV11Pool;
    }

    public Pool<RockTop> getRockTopPool() {
        return rockTopPool;
    }

    public Pool<RockSurface> getRockSurfacePool() {
        return rockSurfacePool;
    }

    public Pool<SnowH10> getSnowH10Pool() {
        return snowH10Pool;
    }

    public Pool<SnowV5> getSnowV5Pool() {
        return snowV5Pool;
    }

    public Pool<SnowV11> getSnowV11Pool() {
        return snowV11Pool;
    }

    public Pool<SnowTop> getSnowTopPool() {
        return snowTopPool;
    }

    public Pool<SnowSurface> getSnowSurfacePool() {
        return snowSurfacePool;
    }

    public Pool<Ice> getIcePool() {
        return icePool;
    }

    public Pool<BulletPlayer> getBulletPlayerPool() {
        return bulletPlayerPool;
    }

    public Pool<BulletEnemy> getBulletEnemyPool() {
        return bulletEnemyPool;
    }

    public Pool<CasePlayer> getCasePlayerPool() {
        return casePlayerPool;
    }

    public Pool<CaseEnemy> getCaseEnemyPool() {
        return caseEnemyPool;
    }

    public Pool<Shot> getShotPool() {
        return shotPool;
    }

    public Pool<Rocket> getRocketPool() {
        return rocketPool;
    }

    public Pool<BigAreaManager> getBigAreaManagerPool() {
        return bigAreaManagerPool;
    }

    public Pool<BigAreaObject> getBigAreaObjectPool() {
        return bigAreaObjectPool;
    }

    public Pool<SmallAreaObject> getSmallAreaObjectPool() {
        return smallAreaObjectPool;
    }

    public Pool<EnemyJumpObject> getEnemyJumpObjectPool() {
        return enemyJumpObjectPool;
    }

    public Pool<EnemyDropObject> getEnemyDropObjectPool() {
        return enemyDropObjectPool;
    }

    public PFXPool getAmmunitionGroundHitPool() {
        return ammunitionGroundHitPool;
    }

    public PFXPool getAmmunitionGrassHitPool() {
        return ammunitionGrassHitPool;
    }

    public PFXPool getRocketExplosionPool() {
        return rocketExplosionPool;
    }
}