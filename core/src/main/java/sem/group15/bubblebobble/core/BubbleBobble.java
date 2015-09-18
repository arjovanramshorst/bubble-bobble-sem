package sem.group15.bubblebobble.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import sem.group15.bubblebobble.core.objects.*;

import java.awt.*;

public class BubbleBobble implements ApplicationListener {
	public static final int SPRITE_SIZE = 32;


	LogicController controller;
	SpriteBatch batch;

	private static final Logger logger = Logger.getLogger(BubbleBobble.class.getName());

	@Override
	public void create () {
		logger.log("Creating game.");
		batch = new SpriteBatch();
		controller = new LogicController();

        PlayerObject player=new PlayerObject(250f, 250f);

		logger.log("Adding objects.");
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
