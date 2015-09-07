package sem.group15.bubblebobble.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import sem.group15.bubblebobble.core.objects.TestObject;

public class BubbleBobble implements ApplicationListener {
	LogicController controller;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		controller = new LogicController();

		/*
		This is there to check if the drawing process works.
		 */
		controller.addGameObject(new TestObject(50f,50f));
		controller.addGameObject(new TestObject(250f, 250f));
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
