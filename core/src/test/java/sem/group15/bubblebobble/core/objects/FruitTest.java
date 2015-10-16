package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class FruitTest {
    Fruit fruit;

    @Before
    public void setUp() throws Exception {
        fruit = Mockito.mock(Fruit.class, Mockito.CALLS_REAL_METHODS);
        fruit.location = new Rectangle(0, 0, 32, 32);
    }

    @Test
    public void testUpdateLocation() throws Exception {
        assertEquals(0, fruit.getBottom(), 0.1);
        fruit.update(0.5f);
        assertEquals(fruit.speedY, fruit.getBottom(), 0.1);
    }

    @Test
    public void testUpdateAliveTime() throws Exception {
        assertEquals(0, fruit.getAliveTime(), 0.1);
        fruit.update(0.5f);
        assertEquals(0.5f, fruit.getAliveTime(), 0.1);
    }

    @Test
    public void testUpdateRemoveAfter5s() throws Exception {
        assertFalse(fruit.remove());
        fruit.update(5.1f);
        assertTrue(fruit.remove());
    }

    @Test
    public void testHandleCollisionWithPlayer() throws Exception {
        Player player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        player.location = new Rectangle(0, 0, 32, 32);
        fruit.update(0.51f);

        assertFalse(fruit.remove());
        fruit.handleCollision(player);
        assertTrue(fruit.remove());
    }

    @Test
    public void testGetAliveTime() throws Exception {
        assertEquals(0, fruit.getAliveTime(), 0.1f);
        fruit.update(1f);
        assertEquals(1, fruit.getAliveTime(), 0.1f);
    }

}