package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.BubbleBobble;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Marketing Lorre on 15-10-2015.
 */
public class PowerupTest {

    private Powerup powerup;

    @Before
    public void setup(){
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        powerup = mock(Powerup.class, CALLS_REAL_METHODS);
        powerup.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
    }

    @Test
    public void testUpdate(){
        float aliveTime1 = powerup.getAliveTime();
        powerup.update(1);
        aliveTime1 += 1;
        assertTrue(aliveTime1 == powerup.getAliveTime());
    }

    @Test
    public void testRemove(){
        assertFalse(powerup.remove);
        powerup.update(11);
        assertTrue(powerup.remove);
    }

    @Test
    public void testGetters(){
        assertTrue(powerup.getSpeedBoost() == 2);
    }

}
