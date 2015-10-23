package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.Logger;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Test class for enemy object.
 */
public class SimpleEnemyTest {


    private SimpleEnemy enemy;

    /**
     * Set up mockito
     */
    @Before
    public void setUp() {
        enemy = new SimpleEnemy(0,0);
    }

    /**
     * Test if movement speed is faster when angry.
     */
    @Test
    public void testUpdateAngryState() {
        assertEquals(0, enemy.location.x, 0.1f);
        enemy.setState(Enemy.State.ANGRY);
        enemy.setDirection(GameObject.Direction.RIGHT);
        enemy.speedX = 100;
        enemy.update(0.1f);
        assertEquals(enemy.speedX * 0.1f * Enemy.ANGRY_MULTIPLIER, enemy.location.x, 0.1f);
    }

    /**
     * Test if angryTime gets updated when angry.
     */
    @Test
    public void testUpdateTimeAngry() {
        /*
        prevent warping due to long fall to cause nullpointer
         */
        Gdx.graphics = Mockito.mock(Graphics.class);
        
        assertEquals(0, enemy.timeAngry, 0.1f);
        enemy.setState(Enemy.State.ANGRY);
        enemy.update(1f);
        assertEquals(1, enemy.timeAngry, 0.1f);
        assertEquals(Enemy.State.ANGRY, enemy.state);
        enemy.update(9.1f);
        assertEquals(0, enemy.timeAngry, 0.1f);
        assertEquals(Enemy.State.NORMAL, enemy.state);
    }

    /**
     * If enemy facing Left hits a wall on the left, direction and speed should be set to right.
     */
    @Test
    public void testHandleCollisionWallLeftFacingLeft() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle( 2 - BubbleBobble.SPRITE_SIZE, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals(Enemy.ENEMY_SPEED, enemy.speedX, 0.01f);
    }

    /**
     * If enemy facing right hits a wall on the left, direction and speed should be kept to right.
     */
    @Test
    public void testHandleCollisionWallLeftFacingRight() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(2 - BubbleBobble.SPRITE_SIZE, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals(Enemy.ENEMY_SPEED, enemy.speedX, 0.01f);
    }

    /**
     * If enemy facing right hits a wall on the right, direction and speed should be kept to left.
     */
    @Test
    public void testHandleCollisionWallRightFacingLeft() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals(-Enemy.ENEMY_SPEED, enemy.speedX, 0.01f);
    }

    /**
     * If enemy facing right hits a wall on the right, direction and speed should be set to left.
     */
    @Test
    public void testHandleCollisionWallRightFacingRight() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals( - Enemy.ENEMY_SPEED, enemy.speedX, 0.01f);
    }

//    /**
//     * Test if a collision with a bubble is handled accordingly.
//     */
//    @Test
//    public void testHandleCollisionBubble() {
//        enemy.update((float) (1 / enemy.speedX));
//        assertFalse(enemy.remove());
//        Bubble bubble = Mockito.mock(Bubble.class, Mockito.CALLS_REAL_METHODS);
//        bubble.location = new Rectangle(BubbleBobble.SPRITE_SIZE/2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
//        enemy.update((float) (1 / enemy.speedX));
//        enemy.handleCollision(bubble);
//        assertTrue(enemy.remove());
//    }

    @Test
    public void testHandleDoubleCollisionBubble() {
        enemy.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE );
        Enemy enemy2 = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        Bubble bubble = Mockito.mock(Bubble.class, Mockito.CALLS_REAL_METHODS);
        bubble.setLogger(Logger.getLogger(Bubble.class.getName()));
        enemy2.location = new Rectangle(0,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        bubble.location = new Rectangle(0,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        doNothing().when(bubble).makeFilledBubble(enemy);
        enemy.update(0.01f);
        enemy2.update(0.01f);
        bubble.update(0.01f);
        assertFalse(enemy.remove() || enemy2.remove() || bubble.remove());
        enemy.handleCollision(bubble);
        bubble.handleCollision(enemy);
        enemy2.handleCollision(bubble);
        bubble.handleCollision(enemy2);
        assertTrue(enemy.remove());
        assertFalse(enemy2.remove());
    }

    @Test
    public void testDirectionAndSpeedIsSetAccordingly() {
        enemy.setDirection(GameObject.Direction.LEFT);
        assertEquals(-Enemy.ENEMY_SPEED, enemy.speedX, 0.01f);
        enemy.setDirection(GameObject.Direction.RIGHT);
        assertEquals(Enemy.ENEMY_SPEED, enemy.speedX, 0.01f);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDrawRight() {
        enemy.setDirection(GameObject.Direction.RIGHT);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        enemy.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDrawLeft() {
        enemy.setDirection(GameObject.Direction.LEFT);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        enemy.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }


}
