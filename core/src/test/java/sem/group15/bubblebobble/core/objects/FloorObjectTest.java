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
public class FloorObjectTest {

    private FloorObject floorObject;

    /**
     * Set up wall for testing.
     */
    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        floorObject = mock(FloorObject.class, Mockito.CALLS_REAL_METHODS);
        floorObject.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);

    }

    /**
     * Test if wall stays in the same place if update is called.
     */
    @Test
    public void testUpdate(){
        float x = floorObject.location.x;
        float y = floorObject.location.y;
        floorObject.update(1f);
        assertTrue(x == floorObject.location.x);
        assertTrue(y == floorObject.location.y);
    }

}
