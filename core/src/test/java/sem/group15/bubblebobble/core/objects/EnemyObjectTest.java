package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.BubbleBobble;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class EnemyObjectTest {

    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
    }

    /**
     * Test if a collision with a wall is handled accordingly.
     * The speed should change with -1.
     */
    @Test
    public void testHandleCollisionWall() {
        ImmutableObject wall2 = new WallObject(0,0, null);
        EnemyObject enemy = new EnemyObject(BubbleBobble.SPRITE_SIZE, 0, null);
        ImmutableObject wall = new WallObject(2*BubbleBobble.SPRITE_SIZE,0, null);
        float speed = enemy.currentSpeedX;
        enemy.update(1/speed);
        System.out.print(enemy.location.getX());
        enemy.handleCollision(wall);
        enemy.handleCollision(wall2);
        assertEquals(-1 * speed, enemy.currentSpeedX, 1);
    }

    /**
     * Test if a collision with a bubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionBubble() {
        EnemyObject enemy = new EnemyObject(BubbleBobble.SPRITE_SIZE, 0, null);
        enemy.update(1 / enemy.currentSpeedX);
        assertFalse(enemy.remove());
        BubbleObject bubble = new BubbleObject(BubbleBobble.SPRITE_SIZE, 0, GameObject.Direction.RIGHT, null);
        enemy.update(1 / enemy.currentSpeedX);
        enemy.handleCollision(bubble);
        assertTrue(enemy.remove());
    }
}
