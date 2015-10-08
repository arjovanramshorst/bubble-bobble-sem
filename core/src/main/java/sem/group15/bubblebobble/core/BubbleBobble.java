package sem.group15.bubblebobble.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;


/**
 * ApplicationListener implementation that renders the objects on the screen.
 */
public class BubbleBobble implements ApplicationListener {
	public static final int SPRITE_SIZE = 32;


	LogicController controller;
	SpriteBatch batch;
	Level level;
    private String scoreString;
    BitmapFont bitmapFont;
	private static final Logger logger = Logger.getLogger(BubbleBobble.class.getName());


	/**
	 * creates the map and loads the game objects
	 */
	//@Override
	public void create() {
		batch = new SpriteBatch();
		// Loads and initializes the assets used in the game.
		Assets.getAssets();
		controller = new LogicController();
		controller.init(1);

		logger.log("Finished adding objects.");

        bitmapFont = new BitmapFont();
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
	 * render the score on the screen
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		controller.loop(Gdx.graphics.getDeltaTime(), batch);
        // draw score on screen
        bitmapFont.setColor(1, 1, 1, 1);
        scoreString = "score: " + controller.player.score;
        bitmapFont.draw(batch, scoreString, 50, Gdx.graphics.getHeight() - 30);

        batch.end();

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
