package sem.group15.bubblebobble.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import sem.group15.bubblebobble.core.objects.*;

import java.awt.*;

/**
 * ApplicationListener implementation that renders the objects on the screen
 */
public class BubbleBobble implements ApplicationListener {
	public static final int SPRITE_SIZE = 32;


	LogicController controller;
	SpriteBatch batch;

    private int score;
    private String scoreString;
    BitmapFont bitmapFont;
	private static final Logger logger = Logger.getLogger(BubbleBobble.class.getName());


	/**
	 * creates the map and loads the game objects
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		controller = new LogicController();

        PlayerObject player=new PlayerObject(250f, 250f);

        controller.addGameObject(player);
        controller.setPlayer(player);


        //Temporary Map setup
		for (int i=0; i<30; i++) {
			//floor
			controller.addGameObject(new FloorObject(i * SPRITE_SIZE, 0));
			//roof
			controller.addGameObject(new WallObject( i * SPRITE_SIZE,600));
			//walls
			controller.addGameObject(new WallObject( 0,i * SPRITE_SIZE));
			controller.addGameObject(new WallObject( 600,i * SPRITE_SIZE));



		}
		for(int i=0;i<4;i++) {
			controller.addGameObject(new FloorObject(200+SPRITE_SIZE*i, 200-SPRITE_SIZE*i));
		}

		controller.addGameObject(new EnemyObject(100f, 220f));
		logger.log("Finished adding objects.");

        bitmapFont = new BitmapFont();
	}

	/**
	 * method for resizing the screen
	 * @param width
	 * @param height
	 */

	@Override
	public void resize (int width, int height) {
	}

	/**
	 * render the score on the screen
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		controller.loop(Gdx.graphics.getDeltaTime(), batch);
        // draw score on screen
        bitmapFont.setColor(1, 1, 1, 1);
        scoreString = "score: " + controller.player.score;
        bitmapFont.draw(batch, scoreString, 50, 450);

        batch.end();

	}

	/**
	 * method that is called when the game is paused
	 */
	@Override
	public void pause () {
	}

	/**
	 * method that is called when the game is resumed
	 */
	@Override
	public void resume () {
	}


	/**
	 * method that is called when the game is disposed
	 */
	@Override
	public void dispose () {
	}
}
