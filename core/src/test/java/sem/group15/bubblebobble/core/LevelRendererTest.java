package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.Player;
import sem.group15.bubblebobble.core.objects.Wall;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LevelRendererTest {

    LevelRenderer renderer;
    SpriteBatch batch;
    BitmapFont font;
    Level level;

    @Before
    public void setUp() throws Exception {
        renderer = Mockito.mock(LevelRenderer.class, Mockito.CALLS_REAL_METHODS);
        Gdx.gl = Mockito.mock(GL20.class);
        Gdx.graphics = Mockito.mock(Graphics.class);
        batch = Mockito.mock(SpriteBatch.class);
        renderer.setBatch(batch);
        font = Mockito.mock(BitmapFont.class);
        renderer.setFont(font);
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        level = new Level(gameObjects);
        renderer.setLevel(level);
    }

    /**
     * Test if render calls the right methods.
     */
    @Test
    public void testRender(){
        Mockito.doNothing().when(renderer).renderWorld(level);
        Mockito.doNothing().when(renderer).renderScore();
        Mockito.doNothing().when(renderer).renderLives();
        renderer.render();
        verify(renderer).renderWorld(level);
        verify(renderer).renderScore();
        verify(renderer).renderLives();
        verify(batch).begin();
        verify(batch).end();
    }

    /**
     * Test if render world invokes draw on objects in level.
     */
    @Test
    public void testRenderWorld(){
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Wall wall = new Wall(0, 0);
        Wall spyWall = Mockito.spy(wall);
        gameObjects.add(spyWall);
        Level level2 = new Level(gameObjects);
        Level spyLevel = Mockito.spy(level2);
        renderer.setLevel(spyLevel);
        renderer.renderWorld(spyLevel);
        verify(spyLevel).getObjects();
//        TODO: cant because method is final, remove final?
//        Mockito.verify(spyWall).draw(batch);
    }

    @Test
    public void testRenderScore() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Level level = new Level(gameObjects);
        Player player = Mockito.mock(Player.class);
        player.score = 0;
        level.setPlayer(player);
        renderer.setLevel(level);

        renderer.renderScore();
        verify(font).draw(batch, "score: 0", 50, -30);
    }

    @Test
    public void testRenderLives(){
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Level level = new Level(gameObjects);
        Player player = Mockito.mock(Player.class);
        player.lives = 3;
        level.setPlayer(player);
        renderer.setLevel(level);
        Texture texture = null;
        renderer.renderLives();
        verify(batch).draw(texture, 50, -32/1.33f, 32/1.5f, 32/1.5f);
    }

    @Test
    public void testRenderNew(){
        renderer.renderNew();
        verify(Gdx.gl).glClearColor(0, 0, 0, 0);
        verify(batch).begin();
        verify(font).draw(batch, "Press enter to begin", 0, 0);
        verify(batch).end();
    }

    @Test
    public void testRenderPause() {
        Mockito.doNothing().when(renderer).renderWorld(level);
        Mockito.doNothing().when(renderer).renderScore();
        Mockito.doNothing().when(renderer).renderLives();
        renderer.renderPause();
        verify(Gdx.gl).glClearColor(0, 0, 0, 0);
        verify(batch).begin();
        verify(renderer).renderWorld(level);
        verify(renderer).renderScore();
        verify(renderer).renderLives();
        verify(Gdx.gl).glBlendColor(0, 0, 0, 0.5f);
        verify(font).draw(batch, "Press enter to continue", 0, 0);
        verify(batch).end();
    }

    @Test
    public void testRenderLost() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Level level = new Level(gameObjects);
        Player player = Mockito.mock(Player.class);
        player.score = 0;
        level.setPlayer(player);
        renderer.setLevel(level);

        Mockito.doNothing().when(renderer).renderWorld(level);
        renderer.renderLost(1);
        verify(batch).begin();
        verify(renderer).renderWorld(level);
        verify(Gdx.gl).glBlendColor(1, 1, 1, 0.5f);
        verify(font).drawMultiLine(batch, "You made it to level 1\n" +
                "Score: 0\n" +
                "HighScore: 0\n" +
                "\n" +
                "Press enter to start a new game!", 0, 0);
        verify(batch).end();
    }

    @Test
    public void testRenderTransition() {
        Level nextLevel = Mockito.mock(Level.class);
        Mockito.doNothing().when(renderer).renderWorld(nextLevel);
        Mockito.doNothing().when(renderer).renderScore();
        Mockito.doNothing().when(renderer).renderLives();
        Mockito.doNothing().when(renderer).draw(0);
        renderer.renderTransition(nextLevel, 0);
        verify(Gdx.gl).glClearColor(0, 0, 0, 0);
        verify(Gdx.gl).glClear(GL30.GL_COLOR_BUFFER_BIT);
        verify(renderer).render(level, 0);
        verify(renderer).render(nextLevel, 0);
    }

}