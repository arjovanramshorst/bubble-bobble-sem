package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by woute on 9/25/2015.
 */
public class BubbleObjectTest {

    private BubbleObject bubble;

    /**
     * setup mokito for mocking
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        bubble = Mockito.mock(BubbleObject.class, Mockito.CALLS_REAL_METHODS);
        bubble.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
    }

    /**
     * Test if speeds get updated correctly
     */
    @Test
    public void testUpdateSpeed() {
        bubble.xSpeed = 40;
        bubble.update(0.1f);
        assertTrue(bubble.xSpeed < 40);
        bubble.xSpeed = 20;
        bubble.update(0.1f);
        verify(bubble).getOutOfGame();
    }

    /**
     * Test if it gets marked to be removed if its been there too long
     */
    @Test
    public void testUpdateLifespan() {
        bubble.update(0.1f);
        assertFalse(bubble.remove);
        bubble.update(BubbleObject.BUBBLE_LIFESPAN);
        assertTrue(bubble.remove);
    }

}
