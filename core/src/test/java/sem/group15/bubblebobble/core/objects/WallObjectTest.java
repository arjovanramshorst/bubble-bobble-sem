package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

/**
 * Created by Marketing Lorre on 28-9-2015.
 */
public class WallObjectTest {

    private WallObject wall;

    /**
     * Set up wall for testing.
     */
    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        wall = mock(WallObject.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);

    }

    /**
     * Test if wall stays in the same place if update is called.
     */
    @Test
    public void testUpdate(){
        float x = wall.location.x;
        float y = wall.location.y;
        wall.update(1f);
        assertTrue(x == wall.location.x);
        assertTrue(y == wall.location.y);
    }


}
