package sem.group15.bubblebobble.core.objects;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by TUDelft SID on 22-9-2015.
 */
public class PlayerObjectTest {
    /**
     * @playerYco sets the player on top of the bubble.
     */
    final int playerYco = 32;

    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);

    }

    @Test
    public void isAliveTest() {
        PlayerObject player = mock(PlayerObject.class, Mockito.CALLS_REAL_METHODS);
        player.location = new Rectangle(0, 0, 32, 32);
        player.currentSpeedX = 100;
        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(30, 0, 32, 32);
        player.handleCollision(enemy);
        assertFalse(player.isAlive);
        player.update(1 / 60);
        assertEquals(0, player.currentSpeedX, 0.01);

    }

    /**
     * Tests if the collision with an enemy is handled accordingly.
     */
    @Test
    public void testHandleCollisionEnemy() {
        PlayerObject player = new PlayerObject(0, BubbleBobble.SPRITE_SIZE - 2, null) {
            @Override
            public void playDeadSound() {
            }
        };
        player.update(0.1f);
        assertTrue(player.isAlive);
        EnemyObject enemy = new EnemyObject(0, BubbleBobble.SPRITE_SIZE - 2, null);
        player.update(0.1f);
        player.handleCollision(enemy);
        assertFalse(player.isAlive);

    }

    /**
     * Tests if a collision with a filledBubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionFilledBubble() {
        PlayerObject player = new PlayerObject(0, BubbleBobble.SPRITE_SIZE - 2, null);
        player.update(0.1f);
        assertTrue(player.score == 0);
        FilledBubbleObject filledBubble = new FilledBubbleObject(0, BubbleBobble.SPRITE_SIZE - 2, null);
        player.update(0.1f);
        player.handleCollision(filledBubble);
        assertTrue(player.score == 100);

    }

    /**
     * Tests if a collisionw ith a bubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionBubble() {
        PlayerObject player = new PlayerObject(0, BubbleBobble.SPRITE_SIZE + playerYco, null);
        assertTrue(!player.canJump);
        BubbleObject bubble = new BubbleObject(0, BubbleBobble.SPRITE_SIZE, GameObject.Direction.RIGHT, null);
        player.update(0.01f);
        player.handleCollision(bubble);
        assertTrue(player.canJump);
    }

}