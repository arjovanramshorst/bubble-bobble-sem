package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilledBubbleTest {

    private FilledBubble fbubble;

    @Before
    public void setUp() {
        fbubble = Mockito.mock(FilledBubble.class, Mockito.CALLS_REAL_METHODS);
        fbubble.location = new Rectangle(0,0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.speedY = 50;
    }

    /**
     *  test update function
     */
    @Test
    public void testUpdate() {
        assertEquals(0f, fbubble.location.y, 0.1f);
        fbubble.update(1f);
        assertEquals(fbubble.speedY, fbubble.location.y, 0.01f);
    }

    /**
     * test handle collision with immutable object
     */
    @Test
    public void testHandleCollisionImmutable(){
        assertEquals(50, fbubble.speedY, 0.01f);
        Immutable obj = Mockito.mock(Immutable.class, Mockito.CALLS_REAL_METHODS);
        obj.location = new Rectangle(10,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.handleCollision(obj);
        assertTrue(fbubble.speedY == 0);
    }

    /**
     * test handle collision with player
     */
    @Test
    public void testHandleCollisionPlayer(){
        fbubble.remove = false;
        Player obj = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        obj.location = new Rectangle(10,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.handleCollision(obj);
        assertTrue(fbubble.remove);
    }
}