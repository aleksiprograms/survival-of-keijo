package com.aleksiprograms.survivalofkeijo.gameworld;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters.EnemyEmitter;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Building;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.collectibles.Coin;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundTop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Ice;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockH10;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockSurface;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockTop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockV11;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.RockV5;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Shop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.BulletEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.BulletPlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.CaseEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.CasePlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Sky;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Grass;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundH10;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundV11;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.GroundV5;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowH10;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowSurface;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowTop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowV11;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowV5;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Surface;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SurfaceComplex;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Top;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyDropObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.DeadAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Enemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyGuideObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyJumpObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Player;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SensorObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.TutorialObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Ammunition;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Case;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.AssaultRifleEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.AssaultRiflePlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.PistolEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.PistolPlayer;
import com.aleksiprograms.survivalofkeijo.managers.BigAreaManager;
import com.aleksiprograms.survivalofkeijo.managers.CoinManager;
import com.aleksiprograms.survivalofkeijo.managers.EnemyManager;
import com.aleksiprograms.survivalofkeijo.managers.WeaponManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btGhostPairCallback;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.utils.Array;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.RenderableObject;
import com.aleksiprograms.survivalofkeijo.resources.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameWorld {

    public TheGame game;
    public boolean paused;
    public btDynamicsWorld dynamicsWorld;
    public WorldContactListener worldContactListener;
    public ModelBuilder modelBuilder;
    public ModelLoader modelLoader;
    private btCollisionConfiguration collisionConfig;
    private btDispatcher dispatcher;
    private btBroadphaseInterface broadphase;
    private btConstraintSolver constraintSolver;
    private btGhostPairCallback ghostPairCallback;
    private DebugDrawer debugDrawer;
    public Environment environment;
    public int i;
    public int j;
    public int levelNumber;
    public Model levelModel;
    public int numberOfBigAreas;
    public String id;
    public ModelInstance modelInstance;
    public Node node;
    public float x;
    public float y;
    public float z;
    public BoundingBox boundingBox;

    private ModelCache modelCacheLevel;
    private ModelCache modelCacheEnemiesAndTheirWeapons;
    private ModelCache modelCacheAmmunitionAndCases;
    private Array<RenderableObject> allLevelObjects;
    private Array<RenderableObject> allEnemiesAndTheirWeapons;
    public Array<Enemy> visibleEnemies;
    private Array<RenderableObject> allAmmunitionAndCases;
    private Array<Coin> allCoins;
    public Array<Building> enterableBuildings;
    public Sky sky;
    public Player player;
    public Array<SensorObject> sensorObjects;
    public Array<BigAreaManager> bigAreaManagers;
    public EnemyManager enemyManager;
    public CoinManager coinManager;
    private PhysicalObject closestNotShootableEnemy;
    private PhysicalObject closestShootableEnemy;
    private Vector3 enemyShootableRayFrom;
    private Vector3 enemyShootableRayTo;
    private ClosestRayResultCallback enemyShootableCallback;
    private Vector3 objectPositionForVisibilityCheck;
    public WeaponManager weaponManagerPlayer;
    public WeaponManager weaponManagerEnemy;

    public GameWorld(TheGame game) {
        this.game = game;
        paused = false;
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);
        dynamicsWorld.setGravity(new Vector3(0, -10f, 0));
        ghostPairCallback = new btGhostPairCallback();
        dynamicsWorld.getBroadphase().getOverlappingPairCache().setInternalGhostPairCallback(ghostPairCallback);
        worldContactListener = new WorldContactListener(game);
        modelBuilder = new ModelBuilder();
        modelLoader = new ObjLoader();
        debugDrawer = new DebugDrawer();
        debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
        dynamicsWorld.setDebugDrawer(debugDrawer);
        boundingBox = new BoundingBox();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
        environment.add(new DirectionalLight().set(0.65f, 0.65f, 0.65f, 0, -0.7f, -0.3f));

        modelCacheLevel = new ModelCache();
        modelCacheEnemiesAndTheirWeapons = new ModelCache();
        modelCacheAmmunitionAndCases = new ModelCache();
        allLevelObjects = new Array<RenderableObject>();
        allEnemiesAndTheirWeapons = new Array<RenderableObject>();
        visibleEnemies = new Array<Enemy>();
        allAmmunitionAndCases = new Array<RenderableObject>();
        allCoins = new Array<Coin>();
        enterableBuildings = new Array<Building>();
        sensorObjects = new Array<SensorObject>();
        bigAreaManagers = new Array<BigAreaManager>();
        enemyManager = new EnemyManager(game);
        coinManager = new CoinManager();

        enemyShootableRayFrom = new Vector3();
        enemyShootableRayTo = new Vector3();
        enemyShootableCallback = new ClosestRayResultCallback(enemyShootableRayFrom, enemyShootableRayTo);
        enemyShootableCallback.setCollisionFilterGroup(Constants.CATEGORY_ENEMY_SHOOTABLE);
        enemyShootableCallback.setCollisionFilterMask(Constants.MASK_ENEMY_SHOOTABLE);

        objectPositionForVisibilityCheck = new Vector3();

        weaponManagerPlayer = new WeaponManager();
        weaponManagerEnemy = new WeaponManager();
    }

    public void createWorld(int levelID) {
        paused = false;
        weaponManagerPlayer.reset();
        weaponManagerEnemy.reset();
        createObjects(levelID);
        modelCacheLevel.begin();
        modelCacheLevel.add(allLevelObjects);
        modelCacheLevel.end();
        enemyManager.init();
    }

    public void clearWorld() {
        freeObject(sky);
        for (i = 0; i < player.weapons.size; i++) {
            freeObject(player.weapons.get(i));
        }
        freeObject(player);
        for (i = 0; i < allAmmunitionAndCases.size; i++) {
            freeObject(allAmmunitionAndCases.get(i));
        }
        allAmmunitionAndCases.clear();
        for (i = 0; i < allEnemiesAndTheirWeapons.size; i++) {
            freeObject(allEnemiesAndTheirWeapons.get(i));
        }
        allEnemiesAndTheirWeapons.clear();
        for (i = 0; i < allLevelObjects.size; i++) {
            freeObject(allLevelObjects.get(i));
        }
        allLevelObjects.clear();
        for (i = 0; i < bigAreaManagers.size; i++) {
            game.gamePools.bigAreaManagerPool.free(bigAreaManagers.get(i));
        }
        enemyManager.clear();
        game.particleEffectManager.clear();
    }

    public void resetWorld() {
        paused = false;
        for (i = 0; i < player.weapons.size; i++) {
            freeObject(player.weapons.get(i));
        }
        freeObject(player);
        for (i = 0; i < allAmmunitionAndCases.size; i++) {
            freeObject(allAmmunitionAndCases.get(i));
        }
        allAmmunitionAndCases.clear();
        for (i = 0; i < allEnemiesAndTheirWeapons.size; i++) {
            freeObject(allEnemiesAndTheirWeapons.get(i));
        }
        allEnemiesAndTheirWeapons.clear();
        for (i = 0; i < allCoins.size; i++) {
            freeObject(allCoins.get(i));
        }
        allCoins.clear();
        weaponManagerPlayer.reset();
        weaponManagerEnemy.reset();
        initPlayer();
        enemyManager.init();
        game.particleEffectManager.clear();
    }

    public void updateAndRender(
            float deltaTime,
            PerspectiveCamera camera,
            ModelBatch modelBatch) {

        modelBatch.begin(camera);
        if (!paused) {
            enemyManager.update(deltaTime);
            coinManager.update(deltaTime);
            sky.update(deltaTime);
            player.update(deltaTime);
            player.weapon.update(deltaTime);
        }
        modelBatch.render(sky);
        modelBatch.render(player, environment);
        modelBatch.render(player.weapon, environment);
        modelBatch.render(modelCacheLevel, environment);

        for (i = 0; i < allCoins.size; i++) {
            if (!paused) {
                if (allCoins.get(i).free) {
                    freeObject(allCoins.get(i));
                    allCoins.removeIndex(i);
                    continue;
                }
                allCoins.get(i).update(deltaTime);
            }
            if (isObjectVisible(camera, allCoins.get(i))) {
                modelBatch.render(allCoins.get(i), environment);
            }
        }

        modelCacheAmmunitionAndCases.begin();
        for (i = 0; i < allAmmunitionAndCases.size; i++) {
            if (!paused) {
                if (allAmmunitionAndCases.get(i).free) {
                    freeObject(allAmmunitionAndCases.get(i));
                    allAmmunitionAndCases.removeIndex(i);
                    continue;
                }
                allAmmunitionAndCases.get(i).update(deltaTime);
            }
            modelCacheAmmunitionAndCases.add(allAmmunitionAndCases.get(i));
            //modelBatch.render(allAmmunitionAndCases.get(i), environment);
        }
        modelCacheAmmunitionAndCases.end();
        modelBatch.render(modelCacheAmmunitionAndCases, environment);
        closestShootableEnemy = null;
        closestNotShootableEnemy = null;
        modelCacheEnemiesAndTheirWeapons.begin();
        visibleEnemies.clear();
        for (int i = 0; i < allEnemiesAndTheirWeapons.size; i++) {
            if (!paused) {
                if (allEnemiesAndTheirWeapons.get(i).free) {
                    freeObject(allEnemiesAndTheirWeapons.get(i));
                    allEnemiesAndTheirWeapons.removeIndex(i);
                    continue;
                }
                allEnemiesAndTheirWeapons.get(i).update(deltaTime);
            }
            if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy) {
                ((Enemy) allEnemiesAndTheirWeapons.get(i)).visibleToPlayer = false;
                ((Enemy) allEnemiesAndTheirWeapons.get(i)).weapon.visibleToPlayer = false;
            }
            if (isObjectVisible(camera, allEnemiesAndTheirWeapons.get(i))) {
                modelCacheEnemiesAndTheirWeapons.add(allEnemiesAndTheirWeapons.get(i));
                if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy) {
                    visibleEnemies.add((Enemy) allEnemiesAndTheirWeapons.get(i));
                }
                //modelBatch.render(allEnemiesAndTheirWeapons.get(i), environment);
                if (!paused) {
                    if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy) {
                        ((Enemy) allEnemiesAndTheirWeapons.get(i)).visibleToPlayer = true;
                        ((Enemy) allEnemiesAndTheirWeapons.get(i)).weapon.visibleToPlayer = true;
                    }
                    if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy && !(((Enemy) allEnemiesAndTheirWeapons.get(i)).dead)) {
                        if (isEnemyShootable((Enemy) allEnemiesAndTheirWeapons.get(i))) {
                            closestShootableEnemy = isEnemyClosestToPlayer(closestShootableEnemy, (PhysicalObject) allEnemiesAndTheirWeapons.get(i));
                        } else {
                            closestNotShootableEnemy = isEnemyClosestToPlayer(closestNotShootableEnemy, (PhysicalObject) allEnemiesAndTheirWeapons.get(i));
                        }
                    }
                }
            }
        }
        modelCacheEnemiesAndTheirWeapons.end();
        modelBatch.render(modelCacheEnemiesAndTheirWeapons, environment);

        if (!paused) {
            if (closestShootableEnemy != null) {
                player.target = closestShootableEnemy;
            } else if (closestNotShootableEnemy != null) {
                player.target = closestNotShootableEnemy;
            } else {
                player.target = null;
            }
        }

        if (!paused) {
            game.particleEffectManager.update();
        }
        game.particleEffectManager.begin();
        game.particleEffectManager.draw();
        game.particleEffectManager.end();
        modelBatch.render(game.particleEffectManager, environment);
        modelBatch.end();

        if (Constants.DEBUG_DRAW_WORLD) {
            debugDrawer.begin(camera);
            dynamicsWorld.debugDrawWorld();
            debugDrawer.end();
        }

        if (!paused) {
            dynamicsWorld.stepSimulation(deltaTime, Constants.MAX_SUB_STEPS, Constants.FIXED_TIME_STEP);
        }
    }

    private boolean isObjectVisible(PerspectiveCamera camera, RenderableObject gameObject) {
        gameObject.transform.getTranslation(objectPositionForVisibilityCheck);
        objectPositionForVisibilityCheck.add(gameObject.center);
        return camera.frustum.boundsInFrustum(objectPositionForVisibilityCheck, gameObject.dimensions);
    }

    private PhysicalObject isEnemyClosestToPlayer(PhysicalObject current, PhysicalObject object) {
        if (current == null) {
            return object;
        } else {
            if (((player.rigidBody.getCenterOfMassPosition().x - object.rigidBody.getCenterOfMassPosition().x) * (player.rigidBody.getCenterOfMassPosition().x - object.rigidBody.getCenterOfMassPosition().x) +
                    (player.rigidBody.getCenterOfMassPosition().y - object.rigidBody.getCenterOfMassPosition().y) * (player.rigidBody.getCenterOfMassPosition().y - object.rigidBody.getCenterOfMassPosition().y))
                    <
                    ((player.rigidBody.getCenterOfMassPosition().x - current.rigidBody.getCenterOfMassPosition().x) * (player.rigidBody.getCenterOfMassPosition().x - current.rigidBody.getCenterOfMassPosition().x) +
                            (player.rigidBody.getCenterOfMassPosition().y - current.rigidBody.getCenterOfMassPosition().y) * (player.rigidBody.getCenterOfMassPosition().y - current.rigidBody.getCenterOfMassPosition().y))) {
                return object;
            } else {
                return current;
            }
        }
    }

    private boolean isEnemyShootable(Enemy enemy) {
        enemyShootableRayFrom.set(
                player.rigidBody.getCenterOfMassPosition().x,
                player.rigidBody.getCenterOfMassPosition().y + 0.5f,
                player.rigidBody.getCenterOfMassPosition().z);
        enemyShootableRayTo.set(
                enemy.rigidBody.getCenterOfMassPosition().x,
                enemy.rigidBody.getCenterOfMassPosition().y + 0.5f,
                enemy.rigidBody.getCenterOfMassPosition().z);
        enemyShootableCallback.setCollisionObject(null);
        enemyShootableCallback.setClosestHitFraction(1);
        enemyShootableCallback.setRayFromWorld(enemyShootableRayFrom);
        enemyShootableCallback.setRayToWorld(enemyShootableRayTo);
        dynamicsWorld.rayTest(enemyShootableRayFrom, enemyShootableRayTo, enemyShootableCallback);
        if (enemyShootableCallback.hasHit() && enemyShootableCallback.getCollisionObject().userData instanceof Enemy) {
            return enemyShootableCallback.getCollisionObject().userData == enemy || ((Enemy) enemyShootableCallback.getCollisionObject().userData).dead;
        } else {
            return false;
        }
    }

    public EnemyGuideObject isObjectClosestToEnemy(Enemy enemy, EnemyGuideObject current, EnemyGuideObject object) {
        if (current == null) {
            return object;
        } else {
            if (Math.abs(enemy.rigidBody.getCenterOfMassPosition().x - object.x) < Math.abs(enemy.rigidBody.getCenterOfMassPosition().x - current.x)) {
                return object;
            } else {
                return current;
            }
        }
    }

    public BigAreaObject getNewBigArea(float x, float y) {
        for (i = 0; i < bigAreaManagers.size; i++) {
            for (j = 0; j < bigAreaManagers.get(i).bigAreas.size; j++) {
                if (x >= bigAreaManagers.get(i).bigAreas.get(j).x - bigAreaManagers.get(i).bigAreas.get(j).width / 2 &&
                        x <= bigAreaManagers.get(i).bigAreas.get(j).x + bigAreaManagers.get(i).bigAreas.get(j).width / 2 &&
                        y >= bigAreaManagers.get(i).bigAreas.get(j).y - bigAreaManagers.get(i).bigAreas.get(j).height / 2 &&
                        y <= bigAreaManagers.get(i).bigAreas.get(j).y + bigAreaManagers.get(i).bigAreas.get(j).height / 2) {
                    return bigAreaManagers.get(i).bigAreas.get(j);
                }
            }
        }
        return bigAreaManagers.get(0).bigAreaNull; // null
    }

    public SmallAreaObject getNewSmallArea(float x, float y, int bigAreaId) {
        for (i = 0; i < bigAreaManagers.size; i++) {
            if (bigAreaManagers.get(i).bigAreaId == bigAreaId) {
                for (j = 0; j < bigAreaManagers.get(i).smallAreas.size; j++) {
                    if (x >= bigAreaManagers.get(i).smallAreas.get(j).x - bigAreaManagers.get(i).smallAreas.get(j).width / 2 &&
                            x <= bigAreaManagers.get(i).smallAreas.get(j).x + bigAreaManagers.get(i).smallAreas.get(j).width / 2 &&
                            y >= bigAreaManagers.get(i).smallAreas.get(j).y - bigAreaManagers.get(i).smallAreas.get(j).height / 2 &&
                            y <= bigAreaManagers.get(i).smallAreas.get(j).y + bigAreaManagers.get(i).smallAreas.get(j).height / 2) {
                        return bigAreaManagers.get(i).smallAreas.get(j);
                    }
                }
                return bigAreaManagers.get(0).smallAreaNull; // null
            }
        }
        return bigAreaManagers.get(0).smallAreaNull; // null
    }

    public void addEnemy(Enemy enemy, float x, float y, float z, int weaponID, int health) {
        allEnemiesAndTheirWeapons.add(enemy);
        enemy.init(x, y, z, 0, health);
        if (weaponID == Constants.PISTOL_ID) {
            enemy.weapon = game.gamePools.pistolEnemyPool.obtain();
        } else if (weaponID == Constants.ASSAULT_RIFLE_ID) {
            enemy.weapon = game.gamePools.assaultRifleEnemyPool.obtain();
        } else if (weaponID == Constants.SHOTGUN_ID) {
            enemy.weapon = game.gamePools.shotgunPool.obtain();
        } else if (weaponID == Constants.SNIPER_ID) {
            enemy.weapon = game.gamePools.sniperPool.obtain();
        } else if (weaponID == Constants.MACHINE_GUN_ID) {
            enemy.weapon = game.gamePools.machineGunPool.obtain();
        } else if (weaponID == Constants.ROCKET_LAUNCHER_ID) {
            enemy.weapon = game.gamePools.rocketLauncherPool.obtain();
        }
        enemy.weapon.init(weaponManagerEnemy.getWeaponData(weaponID), enemy, player, false);
        allEnemiesAndTheirWeapons.add(enemy.weapon);
    }

    public void addAmmunition(Ammunition ammunition, int damage, float x, float y, float z, float angle, Vector3 velocity, short categoryBits, short maskBits) {
        allAmmunitionAndCases.add(ammunition);
        ammunition.init(damage, x, y, z, angle, velocity, categoryBits, maskBits);
    }

    public void addCase(Case casecase, float x, float y, float z, float angle, Vector3 linearVelocity, Vector3 angularVelocity) {
        allAmmunitionAndCases.add(casecase);
        casecase.init(x, y, z, angle, linearVelocity, angularVelocity);
    }

    public void addCoin(Coin coin, float x, float y, float z) {
        allCoins.add(coin);
        coin.init(x, y, z);
    }

    private void freeObject(RenderableObject renderableObject) {
        if (renderableObject instanceof Player) {
            game.gamePools.playerPool.free((Player) renderableObject);
        } else if (renderableObject instanceof Enemy) {
            game.gamePools.enemyPool.free((Enemy) renderableObject);
        } else if (renderableObject instanceof PistolPlayer) {
            game.gamePools.pistolPlayerPool.free((PistolPlayer) renderableObject);
        } else if (renderableObject instanceof PistolEnemy) {
            game.gamePools.pistolEnemyPool.free((PistolEnemy) renderableObject);
        } else if (renderableObject instanceof AssaultRiflePlayer) {
            game.gamePools.assaultRiflePlayerPool.free((AssaultRiflePlayer) renderableObject);
        } else if (renderableObject instanceof AssaultRifleEnemy) {
            game.gamePools.assaultRifleEnemyPool.free((AssaultRifleEnemy) renderableObject);
        } else if (renderableObject instanceof BulletPlayer) {
            game.gamePools.bulletPlayerPool.free((BulletPlayer) renderableObject);
        } else if (renderableObject instanceof BulletEnemy) {
            game.gamePools.bulletEnemyPool.free((BulletEnemy) renderableObject);
        } else if (renderableObject instanceof CasePlayer) {
            game.gamePools.casePlayerPool.free((CasePlayer) renderableObject);
        } else if (renderableObject instanceof CaseEnemy) {
            game.gamePools.caseEnemyPool.free((CaseEnemy) renderableObject);
        } else if (renderableObject instanceof GroundH10) {
            game.gamePools.groundH10Pool.free((GroundH10) renderableObject);
        } else if (renderableObject instanceof GroundV5) {
            game.gamePools.groundV5Pool.free((GroundV5) renderableObject);
        } else if (renderableObject instanceof GroundV11) {
            game.gamePools.groundV11Pool.free((GroundV11) renderableObject);
        } else if (renderableObject instanceof GroundTop) {
            game.gamePools.groundTopPool.free((GroundTop) renderableObject);
        } else if (renderableObject instanceof Grass) {
            game.gamePools.grassPool.free((Grass) renderableObject);
        } else if (renderableObject instanceof RockH10) {
            game.gamePools.rockH10Pool.free((RockH10) renderableObject);
        } else if (renderableObject instanceof RockV5) {
            game.gamePools.rockV5Pool.free((RockV5) renderableObject);
        } else if (renderableObject instanceof RockV11) {
            game.gamePools.rockV11Pool.free((RockV11) renderableObject);
        } else if (renderableObject instanceof RockTop) {
            game.gamePools.rockTopPool.free((RockTop) renderableObject);
        } else if (renderableObject instanceof RockSurface) {
            game.gamePools.rockSurfacePool.free((RockSurface) renderableObject);
        } else if (renderableObject instanceof SnowH10) {
            game.gamePools.snowH10Pool.free((SnowH10) renderableObject);
        } else if (renderableObject instanceof SnowV5) {
            game.gamePools.snowV5Pool.free((SnowV5) renderableObject);
        } else if (renderableObject instanceof SnowV11) {
            game.gamePools.snowV11Pool.free((SnowV11) renderableObject);
        } else if (renderableObject instanceof SnowTop) {
            game.gamePools.snowTopPool.free((SnowTop) renderableObject);
        } else if (renderableObject instanceof SnowSurface) {
            game.gamePools.snowSurfacePool.free((SnowSurface) renderableObject);
        } else if (renderableObject instanceof Ice) {
            game.gamePools.icePool.free((Ice) renderableObject);
        } else if (renderableObject instanceof EnemyEmitter) {
            game.gamePools.enemyEmitterPool.free((EnemyEmitter) renderableObject);
        } else if (renderableObject instanceof Coin) {
            game.gamePools.coinPool.free((Coin) renderableObject);
        } else if (renderableObject instanceof Sky) {
            game.gamePools.backgroundPool.free((Sky) renderableObject);
        }
    }

    private void initPlayer() {
        player = game.gamePools.playerPool.obtain();
        player.init(0, 0.9f, 0, 0);
        player.addWeapon(game.gamePools.pistolPlayerPool.obtain(), weaponManagerPlayer.getWeaponData(Constants.PISTOL_ID), player, null, true);
        player.addWeapon(game.gamePools.assaultRiflePlayerPool.obtain(), weaponManagerPlayer.getWeaponData(Constants.ASSAULT_RIFLE_ID), player, null, true);
        weaponManagerPlayer.getWeaponData(Constants.PISTOL_ID).bought = true;
        weaponManagerPlayer.getWeaponData(Constants.ASSAULT_RIFLE_ID).bought = true;
    }

    private void createObjects(int levelID) {
        //levelNumber = 3;
        numberOfBigAreas = 0;
        if (levelID == Constants.LEVEL_001_ID) {
            levelModel = game.assetManager.get(Constants.LEVEL_001, Model.class);
            numberOfBigAreas = 7;
        } else if (levelID == Constants.LEVEL_002_ID) {
            levelModel = game.assetManager.get(Constants.LEVEL_002, Model.class);
            numberOfBigAreas = 7;
        } else if (levelID == Constants.LEVEL_003_ID) {
            levelModel = game.assetManager.get(Constants.LEVEL_003, Model.class);
            numberOfBigAreas = 7;
        } else {
            levelModel = game.assetManager.get(Constants.LEVEL_001, Model.class);
            numberOfBigAreas = 7;
        }

        for (i = 1; i <= numberOfBigAreas; i++) {
            bigAreaManagers.add(game.gamePools.bigAreaManagerPool.obtain());
            bigAreaManagers.peek().init(i);
        }
        initPlayer();

        //player.weapon = game.gamePools.assaultRiflePlayerPool.obtain();
        //player.weapon.updateScreen(game.weaponManagerPlayer.getWeaponData(Constants.ASSAULT_RIFLE_ID), player, null, true, true);
        //player.weapon = game.gamePools.assaultRiflePlayerPool.obtain();
        //player.weapon.updateScreen(game.weaponManagerPlayer.getWeaponData(Constants.ASSAULT_RIFLE_ID), player, null, true);

        sky = game.gamePools.backgroundPool.obtain();

        for (i = 0; i < levelModel.nodes.size; i++) {
            id = levelModel.nodes.get(i).id;
            modelInstance = new ModelInstance(levelModel, id);
            node = modelInstance.getNode(id);
            modelInstance.transform.set(node.globalTransform);
            x = node.translation.x;
            y = node.translation.y;
            z = node.translation.z;
            node.translation.set(0, 0, 0);
            node.rotation.idt();
            node.scale.set(1, 1, 1);
            modelInstance.calculateTransforms();
            if (id.toLowerCase().contains("ground-h10")) {
                allLevelObjects.add(game.gamePools.groundH10Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("ground-v5")) {
                allLevelObjects.add(game.gamePools.groundV5Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("ground-v11")) {
                allLevelObjects.add(game.gamePools.groundV11Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("ground-top")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.gamePools.groundTopPool.obtain());
                ((Top)allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (id.toLowerCase().contains("grass")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.gamePools.grassPool.obtain());
                ((Surface)allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (id.toLowerCase().contains("rock-h10")) {
                allLevelObjects.add(game.gamePools.rockH10Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("rock-v5")) {
                allLevelObjects.add(game.gamePools.rockV5Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("rock-v11")) {
                allLevelObjects.add(game.gamePools.rockV11Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("rock-top")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.gamePools.rockTopPool.obtain());
                ((Top)allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (id.toLowerCase().contains("rock-surface")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.gamePools.rockSurfacePool.obtain());
                ((Surface)allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (id.toLowerCase().contains("snow-h10")) {
                allLevelObjects.add(game.gamePools.snowH10Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("snow-v5")) {
                allLevelObjects.add(game.gamePools.snowV5Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("snow-v11")) {
                allLevelObjects.add(game.gamePools.snowV11Pool.obtain());
                ((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
            } else if (id.toLowerCase().contains("snow-top")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.gamePools.snowTopPool.obtain());
                ((Top)allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (id.toLowerCase().contains("snow-surface")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.gamePools.snowSurfacePool.obtain());
                ((SurfaceComplex)allLevelObjects.peek()).init(x, y, z, widthFactor, Bullet.obtainStaticNodeShape(modelInstance.nodes));
            } else if (id.toLowerCase().contains("ice")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.gamePools.icePool.obtain());
                ((SurfaceComplex)allLevelObjects.peek()).init(x, y, z, widthFactor, Bullet.obtainStaticNodeShape(modelInstance.nodes));
            } else if (id.toLowerCase().contains("shop")) {
                //allLevelObjects.add(new Shop(game));
                //((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
                //enterableBuildings.add((Building)allLevelObjects.peek());
                Shop shop = new Shop(game);
                shop.init(x, y, z, 0);
                enterableBuildings.add(shop);
            } else if (id.toLowerCase().contains("enemy-emitter")) {
                allLevelObjects.add(game.gamePools.enemyEmitterPool.obtain());
                ((EnemyEmitter)allLevelObjects.peek()).init(x, y, z, 0);
                enemyManager.addEnemyEmitter((EnemyEmitter)allLevelObjects.peek());
            } else if (id.toLowerCase().contains("coin-emitter")) {
                //coinManager.addCoinEmitter(game.gamePools.coinEmitterPool.obtain(), x, y, z);
            } else if (id.toLowerCase().contains("ej")) {
                String bigAreasS = id.substring(id.indexOf("(") + 1, id.indexOf(")"));
                String smallAreasS = id.substring(id.indexOf("{") + 1, id.indexOf("}"));
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(bigAreasS);
                Array<Integer> bigAreas = new Array<Integer>();
                while(matcher.find()) {
                    bigAreas.add(Integer.parseInt(matcher.group()));
                }
                matcher = pattern.matcher(smallAreasS);
                Array<Integer> smallAreas = new Array<Integer>();
                while(matcher.find()) {
                    smallAreas.add(Integer.parseInt(matcher.group()));
                }
                for (j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).bigAreaId == Character.getNumericValue(id.charAt(3))) {
                        bigAreaManagers.get(j).enemyGuides.add(game.gamePools.enemyJumpObjectPool.obtain());
                        ((EnemyJumpObject)bigAreaManagers.get(j).enemyGuides.peek()).init(x, y, z, Character.getNumericValue(id.charAt(3)), bigAreas, smallAreas, id.toLowerCase().contains("-r-"));
                    }
                }
            } else if (id.toLowerCase().contains("ed")) {
                String bigAreasS = id.substring(id.indexOf("(") + 1, id.indexOf(")"));
                String smallAreasS = id.substring(id.indexOf("{") + 1, id.indexOf("}"));
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(bigAreasS);
                Array<Integer> bigAreas = new Array<Integer>();
                while(matcher.find()) {
                    bigAreas.add(Integer.parseInt(matcher.group()));
                }
                matcher = pattern.matcher(smallAreasS);
                Array<Integer> smallAreas = new Array<Integer>();
                while(matcher.find()) {
                    smallAreas.add(Integer.parseInt(matcher.group()));
                }
                for (j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).bigAreaId == Character.getNumericValue(id.charAt(3))) {
                        bigAreaManagers.get(j).enemyGuides.add(game.gamePools.enemyDropObjectPool.obtain());
                        ((EnemyDropObject)bigAreaManagers.get(j).enemyGuides.peek()).init(x, y, z, Character.getNumericValue(id.charAt(3)), bigAreas, smallAreas, id.toLowerCase().contains("-r-"));
                    }
                }
            } else if (id.toLowerCase().contains("area-big")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                modelInstance.calculateBoundingBox(boundingBox);
                int bigAreaId = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                for (j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).bigAreaId == bigAreaId) {
                        bigAreaManagers.get(j).bigAreas.add(game.gamePools.bigAreaObjectPool.obtain());
                        bigAreaManagers.get(j).bigAreas.peek().init(bigAreaId, x, y, boundingBox.getWidth(), boundingBox.getDepth(), false);
                    }
                }
            } else if (id.toLowerCase().contains("area-small")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(id.toLowerCase());
                modelInstance.calculateBoundingBox(boundingBox);
                int bigAreaId = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                int smallAreaId = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                System.out.println("big = " + bigAreaId + " / small = " + smallAreaId);
                for (j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).bigAreaId == bigAreaId) {
                        bigAreaManagers.get(j).smallAreas.add(game.gamePools.smallAreaObjectPool.obtain());
                        bigAreaManagers.get(j).smallAreas.peek().init(smallAreaId, x, y, boundingBox.getWidth(), boundingBox.getDepth(), false);
                    }
                }
            }/*else if (ID.toLowerCase().contains("enemy")) {
                enemy = game.gamePools.enemyPool.obtain();
                addPhysicalObject(enemy, x, y, z);
                allEnemiesAndTheirWeapons.add(enemy.weapon);
            }*/
            /*else if (ID.toLowerCase().contains("tree_a")) {
                addPhysicalObject(game.gamePools.treeAPool.obtain(), x, y, z);
            } else if (ID.toLowerCase().contains("tree_b")) {
                addPhysicalObject(game.gamePools.treeBPool.obtain(), x, y, z);
            } */else if (id.toLowerCase().contains("tutorial")) {
                sensorObjects.add(new TutorialObject(game, modelInstance.transform, com.badlogic.gdx.physics.bullet.Bullet.obtainStaticNodeShape(node, true)));
            } else if (id.toLowerCase().contains("spikes-up")) {
                sensorObjects.add(new DeadAreaObject(game, modelInstance.transform, com.badlogic.gdx.physics.bullet.Bullet.obtainStaticNodeShape(node, true)));
                modelInstance.calculateBoundingBox(boundingBox);
                for (float sx = x - boundingBox.getWidth() / 2 + 0.25f; sx < x + boundingBox.getWidth() / 2; sx += 0.5f) {
                    for (float sz = -0.75f + 0.25f; sz < 0.75f; sz += 0.5f) {
                        //allEnemiesAndTheirWeapons.add(renderableObject);
                        //renderableObject.init(x, y, z, angle);
                        //addSpike(game.gamePools.spikePool.obtain(), sx, y, sz, 0);
                    }
                }
            } else if (id.toLowerCase().contains("spikes-down")) {
                sensorObjects.add(new DeadAreaObject(game, modelInstance.transform, com.badlogic.gdx.physics.bullet.Bullet.obtainStaticNodeShape(node, true)));
                modelInstance.calculateBoundingBox(boundingBox);
                for (float sx = x - boundingBox.getWidth() / 2 + 0.25f; sx < x + boundingBox.getWidth() / 2; sx += 0.5f) {
                    for (float sz = -0.75f + 0.25f; sz < 0.75f; sz += 0.5f) {
                        //addSpike(game.gamePools.spikePool.obtain(), sx, y, sz, 180);
                    }
                }
            } else if (id.toLowerCase().contains("spikes-right")) {
                sensorObjects.add(new DeadAreaObject(game, modelInstance.transform, com.badlogic.gdx.physics.bullet.Bullet.obtainStaticNodeShape(node, true)));
                modelInstance.calculateBoundingBox(boundingBox);
                for (float sy = y - boundingBox.getHeight() / 2 + 0.25f; sy < y + boundingBox.getHeight() / 2; sy += 0.5f) {
                    for (float sz = -0.75f + 0.25f; sz < 0.75f; sz += 0.5f) {
                        //addSpike(game.gamePools.spikePool.obtain(), x, sy, sz, 270);
                    }
                }
            } else if (id.toLowerCase().contains("spikes-left")) {
                sensorObjects.add(new DeadAreaObject(game, modelInstance.transform, com.badlogic.gdx.physics.bullet.Bullet.obtainStaticNodeShape(node, true)));
                modelInstance.calculateBoundingBox(boundingBox);
                for (float sy = y - boundingBox.getHeight() / 2 + 0.25f; sy < y + boundingBox.getHeight() / 2; sy += 0.5f) {
                    for (float sz = -0.75f + 0.25f; sz < 0.75f; sz += 0.5f) {
                        //addSpike(game.gamePools.spikePool.obtain(), x, sy, sz, 90);
                    }
                }
            }
        }
    }
}