package sem.group15.bubblebobble.core.objects;

import static org.junit.Assert.*;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.junit.Before;
import org.junit.Test;
import com.badlogic.gdx.math.Rectangle;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;


import static org.mockito.Mockito.mock;

/**
 * Created by woute on 9/22/2015.
 */

public class PlayerTest {

    private PlayerObject player;

    /**
     * setup mokito for mocking
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        player = Mockito.mock(PlayerObject.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doNothing().when(player).playDeadSound();
        player.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.isAlive = true;
        player.lives = player.PLAYER_LIVES;
        player.respawned = 0;
    }


    /**
     * Lose 1 life when collide with enemy, but stay alive
     */
    @Test
    public void livesTest() {
        assertTrue(player.isAlive);
        assertEquals(3, player.lives, 0);
        assertEquals(player.respawned, 0f, 0.1);

        EnemyObject enemy = Mockito.mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);

        player.handleCollision(enemy);
        assertEquals(player.respawned, 5f, 0.1);
        assertTrue(player.isAlive);
        assertEquals(2, player.lives, 0);
    }
}