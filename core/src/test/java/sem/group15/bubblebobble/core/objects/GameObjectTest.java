package sem.group15.bubblebobble.core.objects;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;

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
        GameObject object = create(0,0);
        GameObject object2 = create(BubbleBobble.SPRITE_SIZE - 2, 0);
        assertEquals(2, object2.overlapLeft(object), 0.1);
        assertEquals(2, object.overlapRight(object2), 0.1);
    }

    /**
     * Test vertical overlap
     * @throws Exception
     */
    @Test
    public void testVerticalOverlap() throws Exception {
        GameObject object = create(0, 0);
        GameObject object2 = create(0, BubbleBobble.SPRITE_SIZE - 2);
        assertEquals(2, object2.overlapBottom(object), 0.1);
        assertEquals(2, object.overlapTop(object2), 0.1);
    }

    @Test
    public void testVerticalOverlapWhenNotTouching() throws Exception {
        GameObject object = create(0,0);
        GameObject object2 = create(BubbleBobble.SPRITE_SIZE + 2, 0);
        assertEquals(0, object.overlapTop(object2), 0.01f);
        assertEquals(0, object.overlapBottom(object2), 0.01f);
    }

    /**
     * Test overlap percentage.
     * @throws Exception
     */
    @Test
    public void testOverlapPercentage() throws Exception {
        GameObject object = create(0,0);
        GameObject object2 = create(0, BubbleBobble.SPRITE_SIZE / 2);
        assertEquals(BubbleBobble.SPRITE_SIZE / 2, object.overlapTop(object2), 0.01f);
        assertEquals(0, object.overlapBottom(object2), 0.01f);
        assertEquals(0.5, object.overlapPercentage(object2), 0.01f);
    }

    /**
     * Create new GameObject mock.
     * @param posX float x position.
     * @param posY float y position.
     * @return
     */
    private GameObject create(float posX, float posY) {
        GameObject object = Mockito.mock(GameObject.class, Mockito.CALLS_REAL_METHODS);
        object.location = new Rectangle(posX,posY, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        return object;
    }
}