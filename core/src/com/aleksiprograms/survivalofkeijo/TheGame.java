package com.aleksiprograms.survivalofkeijo;

import com.aleksiprograms.survivalofkeijo.gameworld.GameWorld;
import com.aleksiprograms.survivalofkeijo.managers.AlertManager;
import com.aleksiprograms.survivalofkeijo.managers.LevelManager;
import com.aleksiprograms.survivalofkeijo.managers.ParticleEffectManager;
import com.aleksiprograms.survivalofkeijo.managers.SavedDataManager;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.resources.GameObjectPools;
import com.aleksiprograms.survivalofkeijo.resources.Styles;
import com.aleksiprograms.survivalofkeijo.screens.AbstractScreen;
import com.aleksiprograms.survivalofkeijo.screens.CreditsScreen;
import com.aleksiprograms.survivalofkeijo.screens.GameScreen;
import com.aleksiprograms.survivalofkeijo.screens.LoadingScreen;
import com.aleksiprograms.survivalofkeijo.screens.MainMenuScreen;
import com.aleksiprograms.survivalofkeijo.screens.SettingsScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g3d.particles.batches.ModelInstanceParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.PointSpriteParticleBatch;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Locale;

public class TheGame extends Game {

    private Locale locale;
    private AssetManager assetManager;
    private GameObjectPools gameObjectPools;
    private GameWorld gameWorld;
    private ModelBatch modelBatch;
    private SpriteBatch spriteBatch;
    private Styles styles;
    private PerspectiveCamera cameraGame;
    private OrthographicCamera cameraUI;
    private ExtendViewport viewport;
    private ParticleEffectManager particleEffectManager;
    private SavedDataManager savedDataManager;
    private LevelManager levelManager;

    private AbstractScreen currentScreen;
    private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;
    private CreditsScreen creditsScreen;
    private SettingsScreen settingsScreen;

    private AlertManager alertManager;

    @Override
    public void create() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        cameraGame = new PerspectiveCamera(
                Constants.FIELD_OF_VIEW_Y,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        cameraUI = new OrthographicCamera();
        viewport = new ExtendViewport(
                Constants.VIRTUAL_WIDTH,
                Constants.VIRTUAL_HEIGHT,
                getCameraUI());
        assetManager = new AssetManager();
        savedDataManager = new SavedDataManager();
        if (savedDataManager.getSavedData().getLanguage() == null) {
            if (java.util.Locale.getDefault().getLanguage().equals("fi")) {
                locale = Constants.LOCALE_FINNISH;
            } else {
                locale = Constants.LOCALE_ENGLISH;
            }
            getAssetManager().load(Constants.BUNDLE, I18NBundle.class);
        } else {
            setLocale();
            I18NBundleLoader.I18NBundleParameter loadParam
                    = new I18NBundleLoader.I18NBundleParameter(getLocale());
            getAssetManager().load(Constants.BUNDLE, I18NBundle.class, loadParam);
        }
        getAssetManager().load(Constants.TEXTURE_ATLAS_LOADING, TextureAtlas.class);
        getAssetManager().finishLoading();
        spriteBatch = new SpriteBatch();
        LoadingScreen loadingScreen = new LoadingScreen(this);
        loadingScreen.updateData();
        setScreen(loadingScreen);
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
        currentScreen = (AbstractScreen) screen;
    }

    private void setLocale() {
        if (savedDataManager.getSavedData().getLanguage().equals(Constants.STRING_ENGLISH)) {
            locale = Constants.LOCALE_ENGLISH;
        } else if (savedDataManager.getSavedData().getLanguage().equals(Constants.STRING_FINNISH)) {
            locale = Constants.LOCALE_FINNISH;
        } else {
            locale = Constants.LOCALE_ENGLISH;
        }
    }

    public void loadAssets() {
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.load(Constants.LEVEL_001, Model.class);
        assetManager.load(Constants.LEVEL_002, Model.class);
        assetManager.load(Constants.LEVEL_003, Model.class);
        assetManager.load(Constants.MODEL_PERSON_PLAYER, Model.class);
        assetManager.load(Constants.MODEL_PERSON_ENEMY, Model.class);
        assetManager.load(Constants.MODEL_WEAPON_PISTOL_PLAYER, Model.class);
        assetManager.load(Constants.MODEL_WEAPON_PISTOL_ENEMY, Model.class);
        assetManager.load(Constants.MODEL_WEAPON_ASSAULT_RIFLE_PLAYER, Model.class);
        assetManager.load(Constants.MODEL_WEAPON_ASSAULT_RIFLE_ENEMY, Model.class);
        assetManager.load(Constants.MODEL_AMMUNITION_BULLET_PLAYER, Model.class);
        assetManager.load(Constants.MODEL_AMMUNITION_BULLET_ENEMY, Model.class);
        assetManager.load(Constants.MODEL_AMMUNITION_CASE_PLAYER, Model.class);
        assetManager.load(Constants.MODEL_AMMUNITION_CASE_ENEMY, Model.class);
        assetManager.load(Constants.MODEL_BUILDING_ENEMY_EMITTER, Model.class);
        assetManager.load(Constants.MODEL_SKY_BLUE, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_GROUND_H10, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_GROUND_V5, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_GROUND_V11, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_GROUND_TOP, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_GRASS, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_ROCK_H10, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_ROCK_V5, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_ROCK_V11, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_ROCK_TOP, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_ROCK_SURFACE, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_SNOW_H10, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_SNOW_V5, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_SNOW_V11, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_SNOW_TOP, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_SNOW_SURFACE, Model.class);
        assetManager.load(Constants.MODEL_WALKABLE_ICE, Model.class);

        particleEffectManager = new ParticleEffectManager(this);
        PointSpriteParticleBatch pointSpriteParticleBatch = new PointSpriteParticleBatch();
        pointSpriteParticleBatch.setCamera(cameraGame);
        ModelInstanceParticleBatch modelInstanceParticleBatch = new ModelInstanceParticleBatch();
        particleEffectManager.add(pointSpriteParticleBatch);
        particleEffectManager.add(modelInstanceParticleBatch);
        ParticleEffectLoader.ParticleEffectLoadParameter loadParam
                = new ParticleEffectLoader.ParticleEffectLoadParameter(
                particleEffectManager.getBatches());
        assetManager.load(Constants.PFX_AMMUNITION_GROUND_HIT,
                ParticleEffect.class, loadParam);
        assetManager.load(Constants.PFX_AMMUNITION_GRASS_HIT,
                ParticleEffect.class, loadParam);
    }

    public void loadRest() {
        Bullet.init();
        styles = new Styles(this);
        levelManager = new LevelManager();
        gameObjectPools = new GameObjectPools(this);
        modelBatch = new ModelBatch();
        gameWorld = new GameWorld(this);

        gameScreen = new GameScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        creditsScreen = new CreditsScreen(this);
        settingsScreen = new SettingsScreen(this);

        alertManager = new AlertManager(this);
    }

    public void changeLocale() {
        setLocale();
        assetManager.unload(Constants.BUNDLE);
        I18NBundleLoader.I18NBundleParameter loadParam
                = new I18NBundleLoader.I18NBundleParameter(getLocale());
        assetManager.load(Constants.BUNDLE, I18NBundle.class, loadParam);
        assetManager.finishLoading();
    }

    @Override
    public void render() {
        super.render();
        if (!(currentScreen instanceof LoadingScreen)) {
            alertManager.updateAndDraw(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        super.pause();
        savedDataManager.save();
    }

    @Override
    public void dispose() {
    }

    public AbstractScreen getCurrentScreen() {
        return currentScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public CreditsScreen getCreditsScreen() {
        return creditsScreen;
    }

    public SettingsScreen getSettingsScreen() {
        return settingsScreen;
    }

    public Locale getLocale() {
        return locale;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public GameObjectPools getGameObjectPools() {
        return gameObjectPools;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public ModelBatch getModelBatch() {
        return modelBatch;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Styles getStyles() {
        return styles;
    }

    public PerspectiveCamera getCameraGame() {
        return cameraGame;
    }

    public OrthographicCamera getCameraUI() {
        return cameraUI;
    }

    public ExtendViewport getViewport() {
        return viewport;
    }

    public ParticleEffectManager getParticleEffectManager() {
        return particleEffectManager;
    }

    public SavedDataManager getSavedDataManager() {
        return savedDataManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public AlertManager getAlertManager() {
        return alertManager;
    }
}