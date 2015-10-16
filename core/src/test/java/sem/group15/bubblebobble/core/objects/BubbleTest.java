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
 * Created by woute on 9/25/2015.
 */
public class BubbleTest {

    private Bubble bubble;

    /**
     * setup mokito for mocking
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
//        Gdx.app = mock(Application.class);
//        Gdx.input = mock(Input.class);
//        bubble = Mockito.mock(Bubble.class, Mockito.CALLS_REAL_METHODS);
//        bubble.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        bubble = new Bubble(0,0, GameObject.Direction.LEFT);
    }

    @Test
    public void testConstructorDirection() {
        assertEquals(-600, bubble.speedX, 0);
        Bubble bubble1 = new Bubble(0,0, GameObject.Direction.RIGHT);
        assertEquals(600, bubble1.speedX, 0);
    }

    /**
     * Test if speeds get updated correctly
     */
    @Test
    public void testUpdateSpeed() {
        bubble.speedX = 40;
        bubble.update(0.1f);
        assertTrue(bubble.speedX < 40);
        bubble.speedX = 20;
        bubble.update(0.1f);
//        verify(bubble).getOutOfGame();
    }

    /**
     * Test if it gets marked to be removed if its been there too long
     */
    @Test
    public void testUpdateLifespan() {
        bubble.update(0.1f);
        assertFalse(bubble.remove);
        bubble.update(Bubble.BUBBLE_LIFESPAN);
        assertTrue(bubble.remove);
    }

    @Test
    public void testMakeFilledBubble() {
        Enemy enemy = Mockito.mock(Enemy.class);
        assertEquals(0, bubble.newObjects.size(), 0);
        bubble.makeFilledBubble(enemy);
        assertEquals(1, bubble.newObjects.size(), 0);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDraw() {
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        bubble.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }
}
