package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class CherryTest {
    Cherry cherry;

    /**
     * Set up wall for testing.
     */
    @Before
    public void setUp() {
        cherry = new Cherry(0,0);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, cherry.multiplier, 0.1);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDraw() {
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        cherry.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }
}