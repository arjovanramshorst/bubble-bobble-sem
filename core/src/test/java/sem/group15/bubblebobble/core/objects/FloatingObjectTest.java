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
public class FloatingObjectTest {

    FloatingObject floatingObject;

    @Before
    public void setUp(){
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        floatingObject = mock(FloatingObject.class, Mockito.CALLS_REAL_METHODS);
        floatingObject.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
    }

    @Test
    public void testUpdate(){
        floatingObject.timeFromFired = 0.1f;

        floatingObject.update(0.1f);
        assertTrue(floatingObject.timeFromFired == 0.2f);
    }

}
