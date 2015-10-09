package com.niparasc.papanikolis.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.niparasc.papanikolis.Papanikolis;
import com.niparasc.papanikolis.directors.Director;
import com.niparasc.papanikolis.directors.Game1Director;
import com.niparasc.papanikolis.stages.Box2DStage;

public class Game1 extends GameScreen {

	public static final String LOG = Game1.class.getSimpleName();

	public enum State {
		READINESS_TEST, SAILING, CRASHED
	}

	private State state = State.READINESS_TEST;

	private Label distanceLabel;
	private TextButton menuButton;
	private Label bestDistanceLabel;
	private Label infoLabel;

	// private Label fpsLabel;
	// private Label actorsLabel;

	private boolean multiplayer = false;
	private boolean host = false;

	public Game1(Papanikolis game, boolean multiplayer, boolean host) {
		super(game);
		this.multiplayer = multiplayer;
		this.host = host;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public TextButton getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(TextButton menuButton) {
		this.menuButton = menuButton;
	}

	public Label getBestDistanceLabel() {
		return bestDistanceLabel;
	}

	public void setBestDistanceLabel(Label bestDistanceLabel) {
		this.bestDistanceLabel = bestDistanceLabel;
	}

	public Label getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(Label infoLabel) {
		this.infoLabel = infoLabel;
	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		/* === SINGLEPLAYER === */

		if (!multiplayer) {
			switch (state) {
			case READINESS_TEST:
				break;
			case SAILING:
				// The director updates the actors (by giving instructions how
				// to act, all game logic is here)
				director.direct();
				// Update physics world
				gameStage.update(delta);
				// gameStage.getDebugRenderer().render(gameStage.getWorld(),
				// gameStage.getCamera().combined);
				distanceLabel.setText(" Distance: "
						+ ((Game1Director) director).getDistance());
				break;
			case CRASHED:
				// Reposition the camera to the submarine's position.
				// For some reason Libgdx positions the camera to its initial
				// position (at game start)
				// when pausing and resuming the game activity (home button on
				// phone).
				Camera camera = gameStage.getCamera();
				((Game1Director) director).followSubmarine(camera);
				break;
			}

			// The actors act based on the director's instructions
			gameStage.act();
			gameStage.draw();

			getUIStage().act();
			getUIStage().draw();
		}

		/* === MULTIPLAYER === */

		else {
			switch (state) {
			case READINESS_TEST:
				break;
			case SAILING:
				// The director updates the actors (by giving instructions how
				// to act, all game logic is here)
				director.direct();
				// Update physics world
				gameStage.update(delta);
				// gameStage.getDebugRenderer().render(gameStage.getWorld(),
				// gameStage.getCamera().combined);
				distanceLabel.setText(" Distance: "
						+ ((Game1Director) director).getDistance());
				break;
			case CRASHED:
				// Reposition the camera
				Camera camera = gameStage.getCamera();
				break;
			}

			// The actors act based on the director's instructions
			gameStage.act();
			gameStage.draw();

			getUIStage().act();
			getUIStage().draw();
		}

		// fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
		// actorsLabel.setText("Actors: " + gameStage.getActors().size);

		// Table.drawDebug(getUIStage());
	}

	public void show() {
		super.show();

		getTable().top();

		distanceLabel = new Label(" Distance: 0", getSkin());
		getTable().add(distanceLabel).expandX().uniform().align(Align.left);

		menuButton = new TextButton("Menu", getSkin());
		getTable().add(menuButton).size(120, 40).expandX().uniform();

		bestDistanceLabel = new Label("Best: "
				+ game.getPreferencesManager().getInteger("bestDistance", 0),
				getSkin());
		getTable().add(bestDistanceLabel).expandX().uniform()
				.align(Align.center);

		getTable().row();

		infoLabel = new Label(
				"At your command chief!\n\nTouch screen to fire the engines!",
				getSkin());
		infoLabel.setAlignment(Align.center);
		getTable().add(infoLabel).colspan(3).expand();

		getTable().row();

		// fpsLabel = new Label("FPS: ", getSkin());
		// getTable().add(fpsLabel).expandX().uniform();
		//
		// actorsLabel = new Label("Actors: ", getSkin());
		// getTable().add(actorsLabel).expandX().uniform();
	}

	public void dispose() {
		super.dispose();
		Gdx.app.log(LOG, "Disposing Game1");
	}

	@Override
	protected Director createDirector(Papanikolis game, Box2DStage gameStage) {
		Director director;
		director = new Game1Director(game, gameStage);
		return director;
	}

}
