package sem.group15.bubblebobble.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import sem.group15.bubblebobble.core.objects.*;


public class BubbleBobble implements ApplicationListener {
	public static final int SPRITE_SIZE = 32;


	LogicController controller;
	SpriteBatch batch;
	Level level;

    private int score;
    private String scoreString;
    BitmapFont bitmapFont;
	private static final Logger logger = Logger.getLogger(BubbleBobble.class.getName());

	@Override
	public void create () {
		batch = new SpriteBatch();

		controller = new LogicController();
		controller.init(1);

		logger.log("Finished adding objects.");

        bitmapFont = new BitmapFont();
	}

	@Override
	public void resize (int width, int height) {
	}

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

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
