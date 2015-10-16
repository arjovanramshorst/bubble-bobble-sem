package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
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
        powerup = new Powerup(0,0);
    }

    /**
     * Test if updating alivetime works.
     */
    @Test
    public void testUpdate(){
        float aliveTime1 = powerup.getAliveTime();
        powerup.update(1);
        aliveTime1 += 1;
        assertTrue(aliveTime1 == powerup.getAliveTime());
    }

    /**
     * Test if removing by exceeding lifetime works.
     */
    @Test
    public void testRemove(){
        assertFalse(powerup.remove);
        powerup.update(11);
        assertTrue(powerup.remove);
    }

    /**
     * Test if collision with player works.
     */
    @Test
    public void testhandleCollisionPlayer() {
        Player player = Mockito.mock(Player.class);
        player.location = new Rectangle(30,0, 32, 32);
        assertFalse(powerup.remove);
        powerup.handleCollision(player);
        assertTrue(powerup.remove);
    }

    /**
     * Test if collision with immutable works.
     */
    @Test
    public void testhandleCollisionImmutable() {
        Immutable immutable = Mockito.mock(Immutable.class);
        immutable.location = new Rectangle(30,0, 32, 32);
        assertFalse(powerup.remove);
        powerup.handleCollision(immutable);
        assertTrue(powerup.remove);
    }

    @Test
    public void testGetters(){
        assertTrue(powerup.getSpeedBoost() == 2);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDraw() {
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        powerup.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }
}
