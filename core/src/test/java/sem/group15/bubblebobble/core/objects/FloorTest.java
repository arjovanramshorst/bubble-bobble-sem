package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

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
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        floor = mock(Floor.class, Mockito.CALLS_REAL_METHODS);
        floor.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);

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

}
