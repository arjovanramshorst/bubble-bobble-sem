package sem.group15.bubblebobble.core.objects;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by TUDelft SID on 22-9-2015.
 */
public class PlayerObjectTest {


    private PlayerObject player;

    /**
     * Used for setting the height, width and location of an object.
     */
    final float size = 32;

    /**
     * Used for setting the speed of the player object.
     */
    final float speed = 100;

    /**
     * Set up the player, the player's location and speed.
     */

    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        player = mock(PlayerObject.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doNothing().when(player).playDeadSound();
        player.location = new Rectangle(0, 0, size, size);
        player.currentSpeedX = speed;
    }

    /**
     * Test if the boolean value isAlive changes when
     * the player collides with an enemy.
     */
    @Test
    public void isAliveTest() {
        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(size - 2, 0, size, size);
        player.handleCollision(enemy);
        assertFalse(player.isAlive);
        player.update(1 / speed);
        assertEquals(0, player.currentSpeedX, 0.01);
    }

    /**
     * Tests if the collision with an enemy is handled accordingly.
     */
    @Test
    public void testHandleCollisionEnemy() {
        player.update(0.1f);
        player.isAlive = true;
        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(size - 2, 0, size, size);
        player.update(0.1f);
        player.handleCollision(enemy);
        assertFalse(player.isAlive);
    }

    /**
     * Tests if a collision with a filledBubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionFilledBubble() {
        player.update(0.1f);
        assertTrue(player.score == 0);
        FilledBubbleObject filledBubble = mock(FilledBubbleObject.class, Mockito.CALLS_REAL_METHODS);
        filledBubble.location = new Rectangle(size - 2, 0, size, size);
        player.update(0.1f);
        player.handleCollision(filledBubble);
        assertTrue(player.score == 100);

    }

    /**
     * Tests if a collision ith a bubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionBubble() {
        player.canJump = false;
        BubbleObject bubble = mock(BubbleObject.class, Mockito.CALLS_REAL_METHODS);
        bubble.location = new Rectangle(0, 0, size, 1);
        player.update(0.01f);
        player.handleCollision(bubble);
        assertTrue(player.canJump);
    }

}