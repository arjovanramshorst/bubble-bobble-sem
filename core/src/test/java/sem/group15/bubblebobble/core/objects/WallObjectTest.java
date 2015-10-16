package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Marketing Lorre on 28-9-2015.
 */
public class WallObjectTest {

    private Wall wall;

    /**
     * Set up wall for testing.
     */
    @Before
    public void setUp() {
//        Gdx.app = mock(Application.class);
//        Gdx.input = mock(Input.class);
//        wall = mock(Wall.class, Mockito.CALLS_REAL_METHODS);
//        wall.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        wall = new Wall(0,0);
    }

    /**
     * Test if wall stays in the same place if update is called.
     */
    @Test
    public void testUpdate() {
        float x = wall.location.x;
        float y = wall.location.y;
        wall.update(1f);
        assertTrue(x == wall.location.x);
        assertTrue(y == wall.location.y);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDraw() {
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        wall.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }


}
