package sem.group15.bubblebobble.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;


/**
 * ApplicationListener implementation that renders the objects on the screen.
 */
public class BubbleBobble implements ApplicationListener {

	public static final float SPRITE_SIZE = 32;
	private GameController controller;

	/**
	 * creates the map and loads the game objects.
	 */
	@Override
	public void create() {
		// Loads and initializes the assets used in the game.
		Assets.getAssets();
		controller = new GameController();
	}

	/**
	 * method for resizing the screen
	 * @param width
	 * @param height
	 */

	@Override
	public void resize(int width, int height) {
	}

	/**
	 * Main game loop.
	 */
	@Override
	public void render() {
		controller.run(Gdx.graphics.getDeltaTime());
	}

	/**
	 * method that is called when the game is paused.
	 */
	@Override
	public void pause() {
	}

	/**
	 * method that is called when the game is resumed.
	 */
	@Override
	public void resume() {
	}


	/**
	 * method that is called when the game is disposed.
	 */
	@Override
	public void dispose() {
	}
}
