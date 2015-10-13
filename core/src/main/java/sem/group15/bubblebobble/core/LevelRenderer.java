package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sem.group15.bubblebobble.core.objects.GameObject;

/**
 * Created by arjo on 13-10-15.
 */
public class LevelRenderer {
    private Level level;
    private SpriteBatch batch;

    private BitmapFont font;

    public LevelRenderer() {
        this.batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }


    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Render the entire level, including scores and lives.
     */
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderWorld();
        renderScore();
        renderLives();
        batch.end();
    }

    /**
     * Renders the playing world.
     */
    private void renderWorld() {
        for (GameObject object : level.getObjects()) {
            object.draw(batch);
        }
    }

    /**
     * Renders the player's score.
     */
    private void renderScore() {
        String scoreString = "score: " + level.getPlayer().score;
        font.draw(batch, scoreString, 50, Gdx.graphics.getHeight() - 30);
    }

    /**
     * Renders the player's lives.
     */
    private void renderLives() {

    }
}
