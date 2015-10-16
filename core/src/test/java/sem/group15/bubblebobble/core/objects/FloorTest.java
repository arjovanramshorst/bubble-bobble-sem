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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Marketing Lorre on 28-9-2015.
 */
public class FloorTest {

    private Floor floor;

    /**
     * Set up wall for testing.
     */
    @Before
    public void setUp() {
        floor = new Floor(0,0);
    }

    /**
     * Test if wall stays in the same place if update is called.
     */
    @Test
    public void testUpdate(){
        float x = floor.location.x;
        float y = floor.location.y;
        floor.update(1f);
        assertTrue(x == floor.location.x);
        assertTrue(y == floor.location.y);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDraw() {
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        floor.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }

}
