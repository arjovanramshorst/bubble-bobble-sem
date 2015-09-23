package sem.group15.bubblebobble.core.objects;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Test for gameobject class
 */
public class GameObjectTest {

    /**
     * Test horizontal overlap
     * @throws Exception
     */
    @Test
    public void testHorizontalOverlap() throws Exception {
        GameObject object = Mockito.mock(GameObject.class, Mockito.CALLS_REAL_METHODS);
        object.location = new Rectangle(0, 0 ,32 , 32);
        GameObject object2 = Mockito.mock(GameObject.class, Mockito.CALLS_REAL_METHODS);
        object2.location = new Rectangle(30,0,32,32);
        assertEquals(2, object2.overlapLeft(object), 0.1);
        assertEquals(2, object.overlapRight(object2), 0.1);
    }

    /**
     * Test vertical overlap
     * @throws Exception
     */
    @Test
    public void testVerticalOverlap() throws Exception {
        GameObject object = Mockito.mock(GameObject.class, Mockito.CALLS_REAL_METHODS);
        object.location = new Rectangle(0, 0 , 32 , 32);
        GameObject object2 = Mockito.mock(GameObject.class, Mockito.CALLS_REAL_METHODS);
        object2.location = new Rectangle(0, 30 , 32 , 32);
        assertEquals(2, object2.overlapBottom(object), 0.1);
        assertEquals(2, object.overlapTop(object2), 0.1);
    }
}