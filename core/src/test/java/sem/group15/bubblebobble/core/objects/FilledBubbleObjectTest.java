package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FilledBubbleObjectTest {

    private FilledBubbleObject fbubble;

    @Before
    public void setUp() {
        fbubble = Mockito.mock(FilledBubbleObject.class, Mockito.CALLS_REAL_METHODS);
        fbubble.location = new Rectangle(0,0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.ySpeed = 50;
    }

    /**
     *  test update function
     */
    @Test
    public void testUpdate() {
        assertEquals(0f, fbubble.location.y, 0.1f);
        fbubble.update(1f);
        assertEquals(fbubble.ySpeed, fbubble.location.y, 0.01f);
    }

    /**
     * test handle collision with immutable object
     */
    @Test
    public void testHandleCollisionImmutable(){
        assertEquals(50, fbubble.ySpeed, 0.01f);
        ImmutableObject obj = Mockito.mock(ImmutableObject.class, Mockito.CALLS_REAL_METHODS);
        obj.location = new Rectangle(10,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.handleCollision(obj);
        assertTrue(fbubble.ySpeed == 0);
    }

    /**
     * test handle collision with player
     */
    @Test
    public void testHandleCollisionPlayer(){
        fbubble.remove = false;
        PlayerObject obj = Mockito.mock(PlayerObject.class, Mockito.CALLS_REAL_METHODS);
        obj.location = new Rectangle(10,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.handleCollision(obj);
        assertTrue(fbubble.remove);
    }
}