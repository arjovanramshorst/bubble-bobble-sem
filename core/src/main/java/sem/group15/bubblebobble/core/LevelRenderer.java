package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
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
        render(this.level, 0);
    }


    /**
     * Renders the playing world.
     */
    public void renderWorld(Level level) {
        for (GameObject object : level.getObjects()) {
            object.draw(batch);
        }
    }

    /**
     * Renders the player's score.
     */
    public void renderScore() {
        String scoreString = "score: " + level.getPlayer().score;
        font.draw(batch, scoreString, 50, Gdx.graphics.getHeight() - 30);
    }

    /**
     * Renders the player's lives.
     */
    public void renderLives() {
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
        draw();
    }

    /**
     * Render the pause screen.
     */
    public void renderPause() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderWorld(this.level);
        renderScore();
        renderLives();
        Gdx.gl.glBlendColor(0, 0, 0, 0.5f);
        font.draw(batch, "Press enter to continue", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        draw();
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
        renderWorld(this.level);
        Gdx.gl.glBlendColor(1, 1, 1, 0.5f);
        String str = "You made it to level " + currentLevelNumber
                + "\nScore: " + level.getPlayer().score
                + "\n\nPress enter to start a new game!";
        font.drawMultiLine(batch, str, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        draw();
    }

    private void draw() {
        draw(0);
    }

    public void draw(float verticalOffset) {
        batch.setTransformMatrix((new Matrix4()).setTranslation(0,verticalOffset, 0));
        batch.end();
    }

    /**
     * Set spritebatch for levelrenderer.
     * @param batch batch to set.
     */
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Set the bitmapfont for the levelrenderer.
     * @param font bitmapfont to be set
     */
    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public void renderTransition(Level nextLevel, float verticalOffset) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        render(this.level, - Gdx.graphics.getHeight() + verticalOffset);
        render(nextLevel, verticalOffset);
    }

    public void render(Level level, float verticalOffset) {
        batch.begin();
        renderWorld(level);
        renderScore();
        renderLives();
        draw(verticalOffset);

    }
}
