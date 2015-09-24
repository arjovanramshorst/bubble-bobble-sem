package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.LogicController;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class GravityObjectTest {

    /**
     * The player.
     */
    private GravityObject player;

    /**
     * The floor.
     */
    private FloorObject floor;

    /**
     * initialise the mocks.
     */
    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        player = mock(PlayerObject.class, Mockito.CALLS_REAL_METHODS);
        floor = mock(FloorObject.class, Mockito.CALLS_REAL_METHODS);
        player.location = new Rectangle(0, 1, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
    }

    /**
     * Tests if a floor collision is handled accordingly.
     * The player should be able to jump after it collides with a floor.
     * The time since last floor contact should be 0.
     */
    @Test
    public void testHandleCollision() throws Exception {
        floor.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.update(0.1f);
        player.handleCollision(floor);
        assertEquals(0, player.timeSinceLastFloorContact, 0.01);
        assertTrue(player.canJump);
    }

    /**
* Tests if the player can't jump when it is not on the floor.
     * @throws Exception
     */
    @Test
    public void testHandleCollisionNoCollision() throws Exception {
        player.canJump = false;
        floor.location = new Rectangle(2, 2, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.handleCollision(floor);
        assertFalse(player.canJump);
     }

}