package com.niparasc.papanikolis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.niparasc.papanikolis.screens.Game1;
import com.niparasc.papanikolis.screens.MenuScreen;

public class Papanikolis extends Game {

	public static final String LOG = Papanikolis.class.getSimpleName();

	// Best played in
	public static final int VIEWPORT_WIDTH = 480;
	public static final int VIEWPORT_HEIGHT = 320;

	private AssetManager assetManager;
	private Preferences preferencesManager;
	private VibratorInterface vibrator;

	public Papanikolis() {
	}

	public Papanikolis(
			VibratorInterface vibrator) {
		this.vibrator = vibrator;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public Preferences getPreferencesManager() {
		return preferencesManager;
	}

	public void setPreferencesManager(Preferences preferencesManager) {
		this.preferencesManager = preferencesManager;
	}

	public VibratorInterface getVibrator() {
		return vibrator;
	}

	public void setVibrator(VibratorInterface vibrator) {
		this.vibrator = vibrator;
	}

	@Override
	public void create() {
		assetManager = new AssetManager();
		preferencesManager = Gdx.app.getPreferences("Preferences");

		// Enqueue assets for loading
		assetManager.load("papanikolis/logo.png", Texture.class);
		assetManager.load("papanikolis/submarine-64x32.png", Texture.class);
		assetManager.load("papanikolis/submarine-crashed-64x32.png",
				Texture.class);
		assetManager
				.load("papanikolis/peer-submarine-64x32.png", Texture.class);
		assetManager.load("papanikolis/peer-submarine-crashed-64x32.png",
				Texture.class);
		// Load assets
		assetManager.finishLoading();

		setScreen(new MenuScreen(this));
	}

	public void dispose() {
		super.dispose();
		Gdx.app.log(LOG, "Disposing Papanikolis");
		assetManager.dispose();
	}

}
