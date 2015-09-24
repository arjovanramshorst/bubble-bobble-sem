package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Test class for enemy object.
 */
public class EnemyObjectTest {


    private EnemyObject enemy;

    /**
     * Set up mockito
     */
    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        enemy = Mockito.mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.setHorizontalSpeed(-100);
    }

    /**
     * Test if a collision with a wall is handled accordingly.
     * The speed should change with -1.
     */
    @Test
    public void testHandleCollisionWall() {
        ImmutableObject wall = Mockito.mock(ImmutableObject.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.update(1f);
        enemy.handleCollision(wall);
        assertEquals(-100, enemy.currentSpeedX, 1);
    }

    /**
     * Test if a collision with a bubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionBubble() {
        enemy.update(1 / enemy.currentSpeedX);
        assertFalse(enemy.remove());
        BubbleObject bubble = Mockito.mock(BubbleObject.class, Mockito.CALLS_REAL_METHODS);
        bubble.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        enemy.update(1 / enemy.currentSpeedX);
        enemy.handleCollision(bubble);
        assertTrue(enemy.remove());
    }
}
