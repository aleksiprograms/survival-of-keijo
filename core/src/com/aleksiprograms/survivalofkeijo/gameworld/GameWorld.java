package com.aleksiprograms.survivalofkeijo.gameworld;

import com.aleksiprograms.survivalofkeijo.TheGame;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.PhysicalObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.RenderableObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Ammunition;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.BulletEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.BulletPlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.Case;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.CaseEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.ammunition.CasePlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.collectibles.Coin;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.emitters.EnemyEmitter;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Building;
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
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Shop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Sky;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowH10;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowSurface;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowTop;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowV11;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SnowV5;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Surface;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.SurfaceComplex;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.environment.Top;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Enemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.people.Player;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.BigAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyDropObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyGuideObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.EnemyJumpObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.sensors.SmallAreaObject;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.AssaultRifleEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.AssaultRiflePlayer;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.PistolEnemy;
import com.aleksiprograms.survivalofkeijo.gameworld.gameobjects.weapons.PistolPlayer;
import com.aleksiprograms.survivalofkeijo.managers.BigAreaManager;
import com.aleksiprograms.survivalofkeijo.managers.CoinManager;
import com.aleksiprograms.survivalofkeijo.managers.EnemyManager;
import com.aleksiprograms.survivalofkeijo.managers.WeaponManager;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameWorld {

    private TheGame game;
    private boolean paused;
    private btDynamicsWorld dynamicsWorld;
    private DebugDrawer debugDrawer;
    private Environment environment;
    private Player player;
    private Sky sky;
    private ModelCache modelCacheLevel;
    private ModelCache modelCacheEnemiesAndTheirWeapons;
    private ModelCache modelCacheAmmunitionAndCases;
    private Array<RenderableObject> allLevelObjects;
    private Array<RenderableObject> allEnemiesAndTheirWeapons;
    private Array<Enemy> visibleEnemies;
    private Array<RenderableObject> allAmmunitionAndCases;
    private Array<Coin> allCoins;
    private Array<Building> enterableBuildings;
    private Array<BigAreaManager> bigAreaManagers;
    private EnemyManager enemyManager;
    private CoinManager coinManager;
    private WeaponManager weaponManagerPlayer;
    private WeaponManager weaponManagerEnemy;

    // These are there, so that the garbage collector does not crass the app.
    private btCollisionConfiguration collisionConfig;
    private btDispatcher dispatcher;
    private btBroadphaseInterface broadphase;
    private btConstraintSolver constraintSolver;
    private btGhostPairCallback ghostPairCallback;

    public GameWorld(TheGame game) {
        this.game = game;
        paused = false;
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(
                dispatcher, broadphase, constraintSolver, collisionConfig);
        dynamicsWorld.setGravity(new Vector3(0, -10f, 0));
        ghostPairCallback = new btGhostPairCallback();
        dynamicsWorld.getBroadphase().getOverlappingPairCache()
                .setInternalGhostPairCallback(ghostPairCallback);
        new WorldContactListener();
        debugDrawer = new DebugDrawer();
        debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
        dynamicsWorld.setDebugDrawer(debugDrawer);
        environment = new Environment();
        environment.set(new ColorAttribute(
                ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
        environment.add(new DirectionalLight().set(
                0.65f, 0.65f, 0.65f, 0, -0.7f, -0.3f));

        modelCacheLevel = new ModelCache();
        modelCacheEnemiesAndTheirWeapons = new ModelCache();
        modelCacheAmmunitionAndCases = new ModelCache();
        allLevelObjects = new Array<RenderableObject>();
        allEnemiesAndTheirWeapons = new Array<RenderableObject>();
        visibleEnemies = new Array<Enemy>();
        allAmmunitionAndCases = new Array<RenderableObject>();
        allCoins = new Array<Coin>();
        enterableBuildings = new Array<Building>();
        bigAreaManagers = new Array<BigAreaManager>();
        enemyManager = new EnemyManager(game);
        coinManager = new CoinManager();

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
        for (int i = 0; i < player.getWeapons().size; i++) {
            freeObject(player.getWeapons().get(i));
        }
        freeObject(player);
        for (int i = 0; i < allAmmunitionAndCases.size; i++) {
            freeObject(allAmmunitionAndCases.get(i));
        }
        allAmmunitionAndCases.clear();
        for (int i = 0; i < allEnemiesAndTheirWeapons.size; i++) {
            freeObject(allEnemiesAndTheirWeapons.get(i));
        }
        allEnemiesAndTheirWeapons.clear();
        for (int i = 0; i < allLevelObjects.size; i++) {
            freeObject(allLevelObjects.get(i));
        }
        allLevelObjects.clear();
        for (int i = 0; i < bigAreaManagers.size; i++) {
            game.getGameObjectPools().getBigAreaManagerPool().free(bigAreaManagers.get(i));
        }
        enemyManager.clear();
        game.getParticleEffectManager().clear();
    }

    public void resetWorld() {
        paused = false;
        for (int i = 0; i < player.getWeapons().size; i++) {
            freeObject(player.getWeapons().get(i));
        }
        freeObject(player);
        for (int i = 0; i < allAmmunitionAndCases.size; i++) {
            freeObject(allAmmunitionAndCases.get(i));
        }
        allAmmunitionAndCases.clear();
        for (int i = 0; i < allEnemiesAndTheirWeapons.size; i++) {
            freeObject(allEnemiesAndTheirWeapons.get(i));
        }
        allEnemiesAndTheirWeapons.clear();
        for (int i = 0; i < allCoins.size; i++) {
            freeObject(allCoins.get(i));
        }
        allCoins.clear();
        weaponManagerPlayer.reset();
        weaponManagerEnemy.reset();
        initPlayer();
        enemyManager.init();
        game.getParticleEffectManager().clear();
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
            player.getWeapon().update(deltaTime);
        }
        modelBatch.render(sky);
        modelBatch.render(player, environment);
        modelBatch.render(player.getWeapon(), environment);
        modelBatch.render(modelCacheLevel, environment);

        for (int i = 0; i < allCoins.size; i++) {
            if (!paused) {
                if (allCoins.get(i).isFree()) {
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
        for (int i = 0; i < allAmmunitionAndCases.size; i++) {
            if (!paused) {
                if (allAmmunitionAndCases.get(i).isFree()) {
                    freeObject(allAmmunitionAndCases.get(i));
                    allAmmunitionAndCases.removeIndex(i);
                    continue;
                }
                allAmmunitionAndCases.get(i).update(deltaTime);
            }
            modelCacheAmmunitionAndCases.add(allAmmunitionAndCases.get(i));
        }
        modelCacheAmmunitionAndCases.end();
        modelBatch.render(modelCacheAmmunitionAndCases, environment);
        PhysicalObject closestShootableEnemy = null;
        PhysicalObject closestNotShootableEnemy = null;
        modelCacheEnemiesAndTheirWeapons.begin();
        visibleEnemies.clear();
        for (int i = 0; i < allEnemiesAndTheirWeapons.size; i++) {
            if (!paused) {
                if (allEnemiesAndTheirWeapons.get(i).isFree()) {
                    freeObject(allEnemiesAndTheirWeapons.get(i));
                    allEnemiesAndTheirWeapons.removeIndex(i);
                    continue;
                }
                allEnemiesAndTheirWeapons.get(i).update(deltaTime);
            }
            if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy) {
                ((Enemy) allEnemiesAndTheirWeapons.get(i)).setVisibleToPlayer(false);
                ((Enemy) allEnemiesAndTheirWeapons.get(i))
                        .getWeapon().setVisibleToPlayer(false);
            }
            if (isObjectVisible(camera, allEnemiesAndTheirWeapons.get(i))) {
                modelCacheEnemiesAndTheirWeapons.add(allEnemiesAndTheirWeapons.get(i));
                if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy) {
                    visibleEnemies.add((Enemy) allEnemiesAndTheirWeapons.get(i));
                }
                if (!paused) {
                    if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy) {
                        ((Enemy) allEnemiesAndTheirWeapons.get(i)).setVisibleToPlayer(true);
                        ((Enemy) allEnemiesAndTheirWeapons.get(i))
                                .getWeapon().setVisibleToPlayer(true);
                    }
                    if (allEnemiesAndTheirWeapons.get(i) instanceof Enemy
                            && !(((Enemy) allEnemiesAndTheirWeapons.get(i)).isDead())) {
                        if (isEnemyShootable((Enemy) allEnemiesAndTheirWeapons.get(i))) {
                            closestShootableEnemy = isEnemyClosestToPlayer(
                                    closestShootableEnemy,
                                    (PhysicalObject) allEnemiesAndTheirWeapons.get(i));
                        } else {
                            closestNotShootableEnemy = isEnemyClosestToPlayer(
                                    closestNotShootableEnemy,
                                    (PhysicalObject) allEnemiesAndTheirWeapons.get(i));
                        }
                    }
                }
            }
        }
        modelCacheEnemiesAndTheirWeapons.end();
        modelBatch.render(modelCacheEnemiesAndTheirWeapons, environment);

        if (!paused) {
            if (closestShootableEnemy != null) {
                player.setTarget(closestShootableEnemy);
            } else if (closestNotShootableEnemy != null) {
                player.setTarget(closestNotShootableEnemy);
            } else {
                player.setTarget(null);
            }
        }

        if (!paused) {
            game.getParticleEffectManager().update();
        }
        game.getParticleEffectManager().begin();
        game.getParticleEffectManager().draw();
        game.getParticleEffectManager().end();
        modelBatch.render(game.getParticleEffectManager(), environment);
        modelBatch.end();

        if (Constants.DEBUG_DRAW_WORLD) {
            debugDrawer.begin(camera);
            dynamicsWorld.debugDrawWorld();
            debugDrawer.end();
        }

        if (!paused) {
            dynamicsWorld.stepSimulation(
                    deltaTime, Constants.MAX_SUB_STEPS, Constants.FIXED_TIME_STEP);
        }
    }

    private boolean isObjectVisible(PerspectiveCamera camera, RenderableObject gameObject) {
        Vector3 position = new Vector3();
        gameObject.transform.getTranslation(position);
        position.add(gameObject.getCenter());
        return camera.frustum.boundsInFrustum(position, gameObject.getDimensions());
    }

    private PhysicalObject isEnemyClosestToPlayer(PhysicalObject current, PhysicalObject object) {
        if (current == null) {
            return object;
        } else {
            if (((player.getRigidBody().getCenterOfMassPosition().x
                    - object.getRigidBody().getCenterOfMassPosition().x)
                    * (player.getRigidBody().getCenterOfMassPosition().x
                    - object.getRigidBody().getCenterOfMassPosition().x)
                    + (player.getRigidBody().getCenterOfMassPosition().y
                    - object.getRigidBody().getCenterOfMassPosition().y)
                    * (player.getRigidBody().getCenterOfMassPosition().y
                    - object.getRigidBody().getCenterOfMassPosition().y))
                    <
                    ((player.getRigidBody().getCenterOfMassPosition().x
                            - current.getRigidBody().getCenterOfMassPosition().x)
                            * (player.getRigidBody().getCenterOfMassPosition().x
                            - current.getRigidBody().getCenterOfMassPosition().x)
                            + (player.getRigidBody().getCenterOfMassPosition().y
                            - current.getRigidBody().getCenterOfMassPosition().y)
                            * (player.getRigidBody().getCenterOfMassPosition().y
                            - current.getRigidBody().getCenterOfMassPosition().y))) {
                return object;
            } else {
                return current;
            }
        }
    }

    private boolean isEnemyShootable(Enemy enemy) {
        Vector3 enemyShootableRayFrom = new Vector3();
        Vector3 enemyShootableRayTo = new Vector3();
        ClosestRayResultCallback enemyShootableCallback = new ClosestRayResultCallback(
                enemyShootableRayFrom, enemyShootableRayTo);
        enemyShootableCallback.setCollisionFilterGroup(
                Constants.CATEGORY_ENEMY_SHOOTABLE);
        enemyShootableCallback.setCollisionFilterMask(
                Constants.MASK_ENEMY_SHOOTABLE);
        enemyShootableRayFrom.set(
                player.getRigidBody().getCenterOfMassPosition().x,
                player.getRigidBody().getCenterOfMassPosition().y + 0.5f,
                player.getRigidBody().getCenterOfMassPosition().z);
        enemyShootableRayTo.set(
                enemy.getRigidBody().getCenterOfMassPosition().x,
                enemy.getRigidBody().getCenterOfMassPosition().y + 0.5f,
                enemy.getRigidBody().getCenterOfMassPosition().z);
        enemyShootableCallback.setCollisionObject(null);
        enemyShootableCallback.setClosestHitFraction(1);
        enemyShootableCallback.setRayFromWorld(enemyShootableRayFrom);
        enemyShootableCallback.setRayToWorld(enemyShootableRayTo);
        dynamicsWorld.rayTest(enemyShootableRayFrom, enemyShootableRayTo, enemyShootableCallback);
        if (enemyShootableCallback.hasHit()
                && enemyShootableCallback.getCollisionObject().userData instanceof Enemy) {
            return enemyShootableCallback.getCollisionObject().userData == enemy
                    || ((Enemy) enemyShootableCallback.getCollisionObject().userData).isDead();
        } else {
            return false;
        }
    }

    public EnemyGuideObject isObjectClosestToEnemy(
            Enemy enemy, EnemyGuideObject current, EnemyGuideObject object) {
        if (current == null) {
            return object;
        } else {
            if (Math.abs(enemy.getRigidBody().getCenterOfMassPosition().x - object.getX())
                    < Math.abs(enemy.getRigidBody().getCenterOfMassPosition().x - current.getX())) {
                return object;
            } else {
                return current;
            }
        }
    }

    public BigAreaObject getNewBigArea(float x, float y) {
        for (int i = 0; i < bigAreaManagers.size; i++) {
            for (int j = 0; j < bigAreaManagers.get(i).getBigAreas().size; j++) {
                if (x >= bigAreaManagers.get(i).getBigAreas().get(j).getX()
                        - bigAreaManagers.get(i).getBigAreas().get(j).getWidth() / 2
                        && x <= bigAreaManagers.get(i).getBigAreas().get(j).getX()
                        + bigAreaManagers.get(i).getBigAreas().get(j).getWidth() / 2
                        && y >= bigAreaManagers.get(i).getBigAreas().get(j).getY()
                        - bigAreaManagers.get(i).getBigAreas().get(j).getHeight() / 2
                        && y <= bigAreaManagers.get(i).getBigAreas().get(j).getY()
                        + bigAreaManagers.get(i).getBigAreas().get(j).getHeight() / 2) {
                    return bigAreaManagers.get(i).getBigAreas().get(j);
                }
            }
        }
        return bigAreaManagers.get(0).getBigAreaNull(); // null
    }

    public SmallAreaObject getNewSmallArea(float x, float y, int bigAreaId) {
        for (int i = 0; i < bigAreaManagers.size; i++) {
            if (bigAreaManagers.get(i).getBigAreaID() == bigAreaId) {
                for (int j = 0; j < bigAreaManagers.get(i).getSmallAreas().size; j++) {
                    if (x >= bigAreaManagers.get(i).getSmallAreas().get(j).getX()
                            - bigAreaManagers.get(i).getSmallAreas().get(j).getWidth() / 2
                            && x <= bigAreaManagers.get(i).getSmallAreas().get(j).getX()
                            + bigAreaManagers.get(i).getSmallAreas().get(j).getWidth() / 2
                            && y >= bigAreaManagers.get(i).getSmallAreas().get(j).getY()
                            - bigAreaManagers.get(i).getSmallAreas().get(j).getHeight() / 2
                            && y <= bigAreaManagers.get(i).getSmallAreas().get(j).getY()
                            + bigAreaManagers.get(i).getSmallAreas().get(j).getHeight() / 2) {
                        return bigAreaManagers.get(i).getSmallAreas().get(j);
                    }
                }
                return bigAreaManagers.get(0).getSmallAreaNull(); // null
            }
        }
        return bigAreaManagers.get(0).getSmallAreaNull(); // null
    }

    public void addEnemy(Enemy enemy, float x, float y, float z, int weaponID, int health) {
        allEnemiesAndTheirWeapons.add(enemy);
        enemy.init(x, y, z, 0, health);
        if (weaponID == Constants.PISTOL_ID) {
            enemy.setWeapon(game.getGameObjectPools().getPistolEnemyPool().obtain());
        } else if (weaponID == Constants.ASSAULT_RIFLE_ID) {
            enemy.setWeapon(game.getGameObjectPools().getAssaultRifleEnemyPool().obtain());
        } else if (weaponID == Constants.SHOTGUN_ID) {
            enemy.setWeapon(game.getGameObjectPools().getShotgunPool().obtain());
        } else if (weaponID == Constants.SNIPER_ID) {
            enemy.setWeapon(game.getGameObjectPools().getSniperPool().obtain());
        } else if (weaponID == Constants.MACHINE_GUN_ID) {
            enemy.setWeapon(game.getGameObjectPools().getMachineGunPool().obtain());
        } else if (weaponID == Constants.ROCKET_LAUNCHER_ID) {
            enemy.setWeapon(game.getGameObjectPools().getRocketLauncherPool().obtain());
        }
        enemy.getWeapon().init(
                weaponManagerEnemy.getWeaponData(weaponID), enemy, player, false);
        allEnemiesAndTheirWeapons.add(enemy.getWeapon());
    }

    public void addAmmunition(
            Ammunition ammunition,
            int damage,
            float x,
            float y,
            float z,
            float angle,
            Vector3 velocity,
            short categoryBits,
            short maskBits) {
        allAmmunitionAndCases.add(ammunition);
        ammunition.init(damage, x, y, z, angle, velocity, categoryBits, maskBits);
    }

    public void addCase(
            Case newCase,
            float x,
            float y,
            float z,
            float angle,
            Vector3 linearVelocity,
            Vector3 angularVelocity) {
        allAmmunitionAndCases.add(newCase);
        newCase.init(x, y, z, angle, linearVelocity, angularVelocity);
    }

    public void addCoin(Coin coin, float x, float y, float z) {
        allCoins.add(coin);
        coin.init(x, y, z);
    }

    private void freeObject(RenderableObject renderableObject) {
        if (renderableObject instanceof Player) {
            game.getGameObjectPools().getPlayerPool()
                    .free((Player) renderableObject);
        } else if (renderableObject instanceof Enemy) {
            game.getGameObjectPools().getEnemyPool()
                    .free((Enemy) renderableObject);
        } else if (renderableObject instanceof PistolPlayer) {
            game.getGameObjectPools().getPistolPlayerPool()
                    .free((PistolPlayer) renderableObject);
        } else if (renderableObject instanceof PistolEnemy) {
            game.getGameObjectPools().getPistolEnemyPool()
                    .free((PistolEnemy) renderableObject);
        } else if (renderableObject instanceof AssaultRiflePlayer) {
            game.getGameObjectPools().getAssaultRiflePlayerPool()
                    .free((AssaultRiflePlayer) renderableObject);
        } else if (renderableObject instanceof AssaultRifleEnemy) {
            game.getGameObjectPools().getAssaultRifleEnemyPool()
                    .free((AssaultRifleEnemy) renderableObject);
        } else if (renderableObject instanceof BulletPlayer) {
            game.getGameObjectPools().getBulletPlayerPool()
                    .free((BulletPlayer) renderableObject);
        } else if (renderableObject instanceof BulletEnemy) {
            game.getGameObjectPools().getBulletEnemyPool()
                    .free((BulletEnemy) renderableObject);
        } else if (renderableObject instanceof CasePlayer) {
            game.getGameObjectPools().getCasePlayerPool()
                    .free((CasePlayer) renderableObject);
        } else if (renderableObject instanceof CaseEnemy) {
            game.getGameObjectPools().getCaseEnemyPool()
                    .free((CaseEnemy) renderableObject);
        } else if (renderableObject instanceof GroundH10) {
            game.getGameObjectPools().getGroundH10Pool()
                    .free((GroundH10) renderableObject);
        } else if (renderableObject instanceof GroundV5) {
            game.getGameObjectPools().getGroundV5Pool()
                    .free((GroundV5) renderableObject);
        } else if (renderableObject instanceof GroundV11) {
            game.getGameObjectPools().getGroundV11Pool()
                    .free((GroundV11) renderableObject);
        } else if (renderableObject instanceof GroundTop) {
            game.getGameObjectPools().getGroundTopPool()
                    .free((GroundTop) renderableObject);
        } else if (renderableObject instanceof Grass) {
            game.getGameObjectPools().getGrassPool()
                    .free((Grass) renderableObject);
        } else if (renderableObject instanceof RockH10) {
            game.getGameObjectPools().getRockH10Pool()
                    .free((RockH10) renderableObject);
        } else if (renderableObject instanceof RockV5) {
            game.getGameObjectPools().getRockV5Pool()
                    .free((RockV5) renderableObject);
        } else if (renderableObject instanceof RockV11) {
            game.getGameObjectPools().getRockV11Pool()
                    .free((RockV11) renderableObject);
        } else if (renderableObject instanceof RockTop) {
            game.getGameObjectPools().getRockTopPool()
                    .free((RockTop) renderableObject);
        } else if (renderableObject instanceof RockSurface) {
            game.getGameObjectPools().getRockSurfacePool()
                    .free((RockSurface) renderableObject);
        } else if (renderableObject instanceof SnowH10) {
            game.getGameObjectPools().getSnowH10Pool()
                    .free((SnowH10) renderableObject);
        } else if (renderableObject instanceof SnowV5) {
            game.getGameObjectPools().getSnowV5Pool()
                    .free((SnowV5) renderableObject);
        } else if (renderableObject instanceof SnowV11) {
            game.getGameObjectPools().getSnowV11Pool()
                    .free((SnowV11) renderableObject);
        } else if (renderableObject instanceof SnowTop) {
            game.getGameObjectPools().getSnowTopPool()
                    .free((SnowTop) renderableObject);
        } else if (renderableObject instanceof SnowSurface) {
            game.getGameObjectPools().getSnowSurfacePool()
                    .free((SnowSurface) renderableObject);
        } else if (renderableObject instanceof Ice) {
            game.getGameObjectPools().getIcePool()
                    .free((Ice) renderableObject);
        } else if (renderableObject instanceof EnemyEmitter) {
            game.getGameObjectPools().getEnemyEmitterPool()
                    .free((EnemyEmitter) renderableObject);
        } else if (renderableObject instanceof Coin) {
            game.getGameObjectPools().getCoinPool()
                    .free((Coin) renderableObject);
        } else if (renderableObject instanceof Sky) {
            game.getGameObjectPools().getBackgroundPool()
                    .free((Sky) renderableObject);
        }
    }

    private void initPlayer() {
        player = game.getGameObjectPools().getPlayerPool().obtain();
        player.init(0, 0.9f, 0, 0);
        player.addWeapon(
                game.getGameObjectPools().getPistolPlayerPool().obtain(),
                weaponManagerPlayer.getWeaponData(Constants.PISTOL_ID),
                player, null, true);
        player.addWeapon(
                game.getGameObjectPools().getAssaultRiflePlayerPool().obtain(),
                weaponManagerPlayer.getWeaponData(Constants.ASSAULT_RIFLE_ID),
                player, null, true);
        weaponManagerPlayer.getWeaponData(Constants.PISTOL_ID).setBought(true);
        weaponManagerPlayer.getWeaponData(Constants.ASSAULT_RIFLE_ID).setBought(true);
    }

    private void createObjects(int levelID) {
        int numberOfBigAreas = 0;
        Model levelModel;
        if (levelID == Constants.LEVEL_001_ID) {
            levelModel = game.getAssetManager().get(Constants.LEVEL_001, Model.class);
            numberOfBigAreas = 7;
        } else if (levelID == Constants.LEVEL_002_ID) {
            levelModel = game.getAssetManager().get(Constants.LEVEL_002, Model.class);
            numberOfBigAreas = 7;
        } else if (levelID == Constants.LEVEL_003_ID) {
            levelModel = game.getAssetManager().get(Constants.LEVEL_003, Model.class);
            numberOfBigAreas = 7;
        } else {
            levelModel = game.getAssetManager().get(Constants.LEVEL_001, Model.class);
            numberOfBigAreas = 7;
        }

        for (int i = 1; i <= numberOfBigAreas; i++) {
            bigAreaManagers.add(game.getGameObjectPools().getBigAreaManagerPool().obtain());
            bigAreaManagers.peek().init(i);
        }
        initPlayer();
        sky = game.getGameObjectPools().getBackgroundPool().obtain();

        for (int i = 0; i < levelModel.nodes.size; i++) {
            String nodeID = levelModel.nodes.get(i).id;
            ModelInstance modelInstance = new ModelInstance(levelModel, nodeID);
            Node node = modelInstance.getNode(nodeID);
            modelInstance.transform.set(node.globalTransform);
            float x = node.translation.x;
            float y = node.translation.y;
            float z = node.translation.z;
            node.translation.set(0, 0, 0);
            node.rotation.idt();
            node.scale.set(1, 1, 1);
            modelInstance.calculateTransforms();
            BoundingBox boundingBox = new BoundingBox();
            if (nodeID.toLowerCase().contains("ground-h10")) {
                allLevelObjects.add(game.getGameObjectPools().getGroundH10Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("ground-v5")) {
                allLevelObjects.add(game.getGameObjectPools().getGroundV5Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("ground-v11")) {
                allLevelObjects.add(game.getGameObjectPools().getGroundV11Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("ground-top")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.getGameObjectPools().getGroundTopPool().obtain());
                ((Top) allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (nodeID.toLowerCase().contains("grass")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.getGameObjectPools().getGrassPool().obtain());
                ((Surface) allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (nodeID.toLowerCase().contains("rock-h10")) {
                allLevelObjects.add(game.getGameObjectPools().getRockH10Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("rock-v5")) {
                allLevelObjects.add(game.getGameObjectPools().getRockV5Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("rock-v11")) {
                allLevelObjects.add(game.getGameObjectPools().getRockV11Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("rock-top")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.getGameObjectPools().getRockTopPool().obtain());
                ((Top) allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (nodeID.toLowerCase().contains("rock-surface")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.getGameObjectPools().getRockSurfacePool().obtain());
                ((Surface) allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (nodeID.toLowerCase().contains("snow-h10")) {
                allLevelObjects.add(game.getGameObjectPools().getSnowH10Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("snow-v5")) {
                allLevelObjects.add(game.getGameObjectPools().getSnowV5Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("snow-v11")) {
                allLevelObjects.add(game.getGameObjectPools().getSnowV11Pool().obtain());
                ((PhysicalObject) allLevelObjects.peek()).init(x, y, z, 0);
            } else if (nodeID.toLowerCase().contains("snow-top")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.getGameObjectPools().getSnowTopPool().obtain());
                ((Top) allLevelObjects.peek()).init(x, y, z, widthFactor);
            } else if (nodeID.toLowerCase().contains("snow-surface")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.getGameObjectPools().getSnowSurfacePool().obtain());
                ((SurfaceComplex) allLevelObjects.peek()).init(
                        x, y, z, widthFactor, Bullet.obtainStaticNodeShape(modelInstance.nodes));
            } else if (nodeID.toLowerCase().contains("ice")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                int widthFactor = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                allLevelObjects.add(game.getGameObjectPools().getIcePool().obtain());
                ((SurfaceComplex) allLevelObjects.peek()).init(
                        x, y, z, widthFactor, Bullet.obtainStaticNodeShape(modelInstance.nodes));
            } else if (nodeID.toLowerCase().contains("shop")) {
                //allLevelObjects.add(new Shop(game));
                //((PhysicalObject)allLevelObjects.peek()).init(x, y, z, 0);
                //enterableBuildings.add((Building)allLevelObjects.peek());
                Shop shop = new Shop(game);
                shop.init(x, y, z, 0);
                enterableBuildings.add(shop);
            } else if (nodeID.toLowerCase().contains("enemy-emitter")) {
                allLevelObjects.add(game.getGameObjectPools().getEnemyEmitterPool().obtain());
                ((EnemyEmitter) allLevelObjects.peek()).init(x, y, z, 0);
                enemyManager.addEnemyEmitter((EnemyEmitter) allLevelObjects.peek());
            } else if (nodeID.toLowerCase().contains("ej")) {
                String bigAreasS = nodeID.substring(
                        nodeID.indexOf("(") + 1, nodeID.indexOf(")"));
                String smallAreasS = nodeID.substring(
                        nodeID.indexOf("{") + 1, nodeID.indexOf("}"));
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(bigAreasS);
                Array<Integer> bigAreas = new Array<Integer>();
                while (matcher.find()) {
                    bigAreas.add(Integer.parseInt(matcher.group()));
                }
                matcher = pattern.matcher(smallAreasS);
                Array<Integer> smallAreas = new Array<Integer>();
                while (matcher.find()) {
                    smallAreas.add(Integer.parseInt(matcher.group()));
                }
                for (int j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).getBigAreaID()
                            == Character.getNumericValue(nodeID.charAt(3))) {
                        bigAreaManagers.get(j).getEnemyGuides().add(
                                game.getGameObjectPools().getEnemyJumpObjectPool().obtain());
                        ((EnemyJumpObject) bigAreaManagers.get(j).getEnemyGuides().peek()).init(
                                x, y, z, Character.getNumericValue(nodeID.charAt(3)),
                                bigAreas, smallAreas, nodeID.toLowerCase().contains("-r-"));
                    }
                }
            } else if (nodeID.toLowerCase().contains("ed")) {
                String bigAreasS = nodeID.substring(
                        nodeID.indexOf("(") + 1, nodeID.indexOf(")"));
                String smallAreasS = nodeID.substring(
                        nodeID.indexOf("{") + 1, nodeID.indexOf("}"));
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(bigAreasS);
                Array<Integer> bigAreas = new Array<Integer>();
                while (matcher.find()) {
                    bigAreas.add(Integer.parseInt(matcher.group()));
                }
                matcher = pattern.matcher(smallAreasS);
                Array<Integer> smallAreas = new Array<Integer>();
                while (matcher.find()) {
                    smallAreas.add(Integer.parseInt(matcher.group()));
                }
                for (int j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).getBigAreaID()
                            == Character.getNumericValue(nodeID.charAt(3))) {
                        bigAreaManagers.get(j).getEnemyGuides().add(
                                game.getGameObjectPools().getEnemyDropObjectPool().obtain());
                        ((EnemyDropObject) bigAreaManagers.get(j).getEnemyGuides().peek()).init(
                                x, y, z, Character.getNumericValue(nodeID.charAt(3)),
                                bigAreas, smallAreas, nodeID.toLowerCase().contains("-r-"));
                    }
                }
            } else if (nodeID.toLowerCase().contains("area-big")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                modelInstance.calculateBoundingBox(boundingBox);
                int bigAreaId = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                for (int j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).getBigAreaID() == bigAreaId) {
                        bigAreaManagers.get(j).getBigAreas().add(
                                game.getGameObjectPools().getBigAreaObjectPool().obtain());
                        bigAreaManagers.get(j).getBigAreas().peek().init(
                                bigAreaId, x, y,
                                boundingBox.getWidth(), boundingBox.getDepth(), false);
                    }
                }
            } else if (nodeID.toLowerCase().contains("area-small")) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(nodeID.toLowerCase());
                modelInstance.calculateBoundingBox(boundingBox);
                int bigAreaId = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                int smallAreaId = matcher.find() ? Integer.parseInt(matcher.group()) : 0;
                System.out.println("big = " + bigAreaId + " / small = " + smallAreaId);
                for (int j = 0; j < bigAreaManagers.size; j++) {
                    if (bigAreaManagers.get(j).getBigAreaID() == bigAreaId) {
                        bigAreaManagers.get(j).getSmallAreas().add(
                                game.getGameObjectPools().getSmallAreaObjectPool().obtain());
                        bigAreaManagers.get(j).getSmallAreas().peek().init(
                                smallAreaId, x, y,
                                boundingBox.getWidth(), boundingBox.getDepth(), false);
                    }
                }
            }
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public btDynamicsWorld getDynamicsWorld() {
        return dynamicsWorld;
    }

    public Player getPlayer() {
        return player;
    }

    public Sky getSky() {
        return sky;
    }

    public Array<Enemy> getVisibleEnemies() {
        return visibleEnemies;
    }

    public Array<Building> getEnterableBuildings() {
        return enterableBuildings;
    }

    public Array<BigAreaManager> getBigAreaManagers() {
        return bigAreaManagers;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public WeaponManager getWeaponManagerPlayer() {
        return weaponManagerPlayer;
    }
}