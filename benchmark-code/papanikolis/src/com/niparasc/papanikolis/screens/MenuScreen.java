package com.niparasc.papanikolis.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.niparasc.papanikolis.Papanikolis;

public class MenuScreen extends AbstractScreen {

	public static final String LOG = MenuScreen.class.getSimpleName();

	private Label welcomeLabel;

	public MenuScreen(Papanikolis game) {
		super(game);
		
	}

	@Override
	public void show() {
		game.setScreen(new Game1(game, false, false));
	}

	public void dispose() {
		super.dispose();
		Gdx.app.log(LOG, "Disposing MenuScreen");
	}

}
