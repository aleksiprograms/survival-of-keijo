package com.aleksiprograms.survivalofkeijo;

import com.aleksiprograms.survivalofkeijo.gameworld.GameWorld;
import com.aleksiprograms.survivalofkeijo.managers.LevelManager;
import com.aleksiprograms.survivalofkeijo.managers.ParticleEffectManager;
import com.aleksiprograms.survivalofkeijo.managers.SaveDataManager;
import com.aleksiprograms.survivalofkeijo.resources.Constants;
import com.aleksiprograms.survivalofkeijo.resources.GamePools;
import com.aleksiprograms.survivalofkeijo.resources.Styles;
import com.aleksiprograms.survivalofkeijo.screens.CreditsScreen;
import com.aleksiprograms.survivalofkeijo.screens.GameScreen;
import com.aleksiprograms.survivalofkeijo.screens.HomeScreen;
import com.aleksiprograms.survivalofkeijo.screens.LoadingScreen;
import com.aleksiprograms.survivalofkeijo.screens.SettingsScreen;
import com.aleksiprograms.survivalofkeijo.screens.StatsScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
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

import java.util.Locale;

public class TheGame extends Game {

	public Locale locale;
	public GameWorld gameWorld;
	public GamePools gamePools;
	public ModelBatch modelBatch;
	public SpriteBatch spriteBatch;
	public AssetManager assetManager;
	public Styles styles;
	public PerspectiveCamera camera;
	public ParticleEffectManager particleEffectManager;
	public SaveDataManager saveDataManager;
	public LevelManager levelManager;

	public GameScreen gameScreen;
	public HomeScreen homeScreen;
	public CreditsScreen creditsScreen;
	public SettingsScreen settingsScreen;
	public StatsScreen statsScreen;
	public LoadingScreen loadingScreen;

	@Override
	public void create() {
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		saveDataManager = new SaveDataManager();
		assetManager = new AssetManager();
		if (saveDataManager.saveData.getLanguage() == null) {
			if (java.util.Locale.getDefault().getLanguage().equals("fi")) {
				locale = Constants.LOCALE_FINNISH;
			} else {
				locale = Constants.LOCALE_ENGLISH;
			}
			assetManager.load(Constants.BUNDLE, I18NBundle.class);
		} else {
			setLocale();
			I18NBundleLoader.I18NBundleParameter loadParam = new I18NBundleLoader.I18NBundleParameter(locale);
			assetManager.load(Constants.BUNDLE, I18NBundle.class, loadParam);
		}
		assetManager.load(Constants.TEXTURE_ATLAS_LOADING, TextureAtlas.class);
		assetManager.finishLoading();
		spriteBatch = new SpriteBatch();
		loadingScreen = new LoadingScreen(this);
		loadingScreen.updateScreen();
		setScreen(loadingScreen);
	}

	private void setLocale() {
		if (saveDataManager.saveData.getLanguage().equals(Constants.STRING_ENGLISH)) {
			locale = Constants.LOCALE_ENGLISH;
		} else if (saveDataManager.saveData.getLanguage().equals(Constants.STRING_FINNISH)) {
			locale = Constants.LOCALE_FINNISH;
		} else {
			locale = Constants.LOCALE_ENGLISH;
		}
	}

	public void loadAssets() {
		assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
		assetManager.load(Constants.LEVEL_001, Model.class);
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
		assetManager.load(Constants.MODEL_WEAPON_KNIFE_ENEMY, Model.class);
		assetManager.load(Constants.MODEL_BUILDING_ENEMY_EMITTER, Model.class);
		assetManager.load(Constants.MODEL_SKY_BLUE, Model.class);
		assetManager.load(Constants.MODEL_WALKABLE_GROUND_H30, Model.class);
		assetManager.load(Constants.MODEL_WALKABLE_GROUND_H10, Model.class);
		assetManager.load(Constants.MODEL_WALKABLE_GROUND_H20, Model.class);
		assetManager.load(Constants.MODEL_WALKABLE_GROUND_V5, Model.class);
		assetManager.load(Constants.MODEL_WALKABLE_GROUND_V11, Model.class);
		assetManager.load(Constants.MODEL_WALKABLE_GRASS, Model.class);

		particleEffectManager = new ParticleEffectManager(this);
		PointSpriteParticleBatch pointSpriteParticleBatch = new PointSpriteParticleBatch();
		pointSpriteParticleBatch.setCamera(camera);
		ModelInstanceParticleBatch modelInstanceParticleBatch = new ModelInstanceParticleBatch();
		particleEffectManager.add(pointSpriteParticleBatch);
		particleEffectManager.add(modelInstanceParticleBatch);
		ParticleEffectLoader.ParticleEffectLoadParameter loadParam = new ParticleEffectLoader.ParticleEffectLoadParameter(particleEffectManager.getBatches());
		assetManager.load(Constants.PFX_AMMUNITION_GROUND_HIT_128PX, ParticleEffect.class, loadParam);
		assetManager.load(Constants.PFX_AMMUNITION_GRASS_HIT_128PX, ParticleEffect.class, loadParam);
	}

	public void loadRest() {
		Bullet.init();
		//Gdx.gl20.glLineWidth(2);
		styles = new Styles(this);
		levelManager = new LevelManager(this);
		gamePools = new GamePools(this);
		modelBatch = new ModelBatch();
		gameWorld = new GameWorld(this);

		gameScreen = new GameScreen(this);
		homeScreen = new HomeScreen(this);
		creditsScreen = new CreditsScreen(this);
		settingsScreen = new SettingsScreen(this);
		statsScreen = new StatsScreen(this);
	}

	public void changeLocale() {
		setLocale();
		assetManager.unload(Constants.BUNDLE);
		I18NBundleLoader.I18NBundleParameter loadParam = new I18NBundleLoader.I18NBundleParameter(locale);
		assetManager.load(Constants.BUNDLE, I18NBundle.class, loadParam);
		assetManager.finishLoading();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void pause() {
		super.pause();
		saveDataManager.save();
	}

	@Override
	public void dispose () {}
}