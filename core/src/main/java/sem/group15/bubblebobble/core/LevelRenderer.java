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

    /**
     * Set level to render.
     *
     * @param level
     */
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
        for (int i = 0; i < level.getPlayer().lives; i++) {
            batch.draw(Assets.getAssets().playerLeft, 50 + (30 * i),
                    Gdx.graphics.getHeight() - (BubbleBobble.SPRITE_SIZE / 1.33f),
                    BubbleBobble.SPRITE_SIZE / 1.5f, BubbleBobble.SPRITE_SIZE / 1.5f);
        }
    }

    /**
     * Render the "new game" screen.
     */
    public void renderNew() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "Press enter to begin", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        batch.end();
    }

    /**
     * Render the pause screen.
     */
    public void renderPause() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderWorld();
        renderScore();
        renderLives();
        Gdx.gl.glBlendColor(0, 0, 0, 0.5f);
        font.draw(batch, "Press enter to continue", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        batch.end();
    }

    /**
     * Render the "lost" screen.
     *
     * @param currentLevelNumber Level that was achieved in the game.
     */
    public void renderLost(int currentLevelNumber) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderWorld();
        Gdx.gl.glBlendColor(1, 1, 1, 0.5f);
        String str = "You made it to level " + currentLevelNumber
                + "\nScore: " + level.getPlayer().score
                + "\nhighScore: " + Assets.getHighScore()
                + "\n\nPress enter to start a new game!";
        font.drawMultiLine(batch, str, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        batch.end();
    }
}
