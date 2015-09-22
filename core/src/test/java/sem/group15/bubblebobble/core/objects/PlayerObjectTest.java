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

public class PlayerObjectTest {

    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
    }

    @Test
    public void isAliveTest() {
        PlayerObject player = mock(PlayerObject.class, Mockito.CALLS_REAL_METHODS);
        player.location = new Rectangle(0,0, 32,32);
        player.currentSpeedX = 100;
        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(30,0,32,32);
        player.handleCollision(enemy);
        assertFalse(player.isAlive);
        player.update(1/60);
        assertEquals(0, player.currentSpeedX, 0.01);

    }

}