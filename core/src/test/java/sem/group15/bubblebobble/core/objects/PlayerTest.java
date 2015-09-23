package sem.group15.bubblebobble.core.objects;

import static org.junit.Assert.*;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by woute on 9/22/2015.
 */

public class PlayerTest {

    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
    }

    /**
     * Lose 1 life when collide with enemy, but stay alive
     */
    @Test
    public void livesTest() {
        PlayerObject player = new PlayerObject(0,0, null)  {
            @Override
            public void playDeadSound() {
            // dont play sound in test
            }
        };
        assertTrue(player.isAlive);
        assertEquals(3, player.lives, 0);

        EnemyObject enemy = new EnemyObject(0,0, null);
        assertEquals(player.respawned, 0f, 0.1);
        player.handleCollision(enemy);
        assertEquals(player.respawned, 5f, 0.1);
        assertTrue(player.isAlive);
        assertEquals(2, player.lives, 0);
    }
}