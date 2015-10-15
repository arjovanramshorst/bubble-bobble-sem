package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.Logger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StrongEnemyTest {

    private StrongEnemy enemy;

    /**
     * Set up mockito
     */
    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        enemy = Mockito.mock(StrongEnemy.class, Mockito.CALLS_REAL_METHODS);
        enemy.setLogger(Logger.getLogger(Enemy.class.getName()));
        enemy.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.setDirection(GameObject.Direction.LEFT);
    }

    /**
     * If enemy facing Left hits a wall on the left, direction and speed should be set to right.
     */
    @Test
    public void testHandleCollisionWallLeftFacingLeft() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle( 2 - BubbleBobble.SPRITE_SIZE, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals(Enemy.ENEMY_SPEED*2, enemy.speedX, 0.01f);
    }

    /**
     * If enemy facing right hits a wall on the left, direction and speed should be kept to right.
     */
    @Test
    public void testHandleCollisionWallLeftFacingRight() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(2 - BubbleBobble.SPRITE_SIZE, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals(Enemy.ENEMY_SPEED*2, enemy.speedX, 0.01f);
    }

    /**
     * If enemy facing right hits a wall on the right, direction and speed should be kept to left.
     */
    @Test
    public void testHandleCollisionWallRightFacingLeft() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals(-Enemy.ENEMY_SPEED*2, enemy.speedX, 0.01f);
    }

    /**
     * If enemy facing right hits a wall on the right, direction and speed should be set to left.
     */
    @Test
    public void testHandleCollisionWallRightFacingRight() {
        Wall wall = Mockito.mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.handleCollision(wall);
        assertEquals( - Enemy.ENEMY_SPEED*2, enemy.speedX, 0.01f);
    }

    /**
     * Test if a collision with a bubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionBubble() {
        enemy.update((float) (1 / enemy.speedX));
        assertFalse(enemy.remove());
        Bubble bubble = Mockito.mock(Bubble.class, Mockito.CALLS_REAL_METHODS);
        bubble.location = new Rectangle(BubbleBobble.SPRITE_SIZE/2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.update((float) (1 / enemy.speedX));
        enemy.handleCollision(bubble);
        assertTrue(enemy.remove());
    }

    /**
     * Test if a collision with a bubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionPlayer() {
        Player player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        player.location = new Rectangle(BubbleBobble.SPRITE_SIZE-2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.update(1.1f);
        enemy.handleCollision(player);
        verify(enemy).updatePath(player.location.x);
    }

    /**
     * If player is to the Left of the location.
     * But the player is to the right, direction should change to LEFT.
     */
    @Test
    public void testUpdatePathPlayerToLeft() {
        enemy.setDirection(GameObject.Direction.RIGHT);
        enemy.updatePath(-1);
        assertEquals(GameObject.Direction.LEFT, enemy.direction);
    }

    /**
     * If player is to the right of the location.
     * But the player is to the right, direction should change to right.
     */
    @Test
    public void testUpdatePathPlayerToRight() {
        enemy.setDirection(GameObject.Direction.LEFT);
        enemy.updatePath(33);
        assertEquals(GameObject.Direction.RIGHT, enemy.direction);
    }
}