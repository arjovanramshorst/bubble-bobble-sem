package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.LogicController;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class EnemyObjectTest {

    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
    }

    @Test
    public void testHorizontalSpeed() throws Exception {
        EnemyObject enemy = new EnemyObject(0, BubbleBobble.SPRITE_SIZE -2, null);
        enemy.setDirection(1);
        enemy.update(0.1f);
        assertEquals(100, enemy.currentSpeedX, 1);
    }

    @Test
    public void testHorizontalSpeed2() throws Exception {
        EnemyObject enemy = new EnemyObject(0, BubbleBobble.SPRITE_SIZE -2, null);
        enemy.setDirection(0);
        enemy.update(0.1f);
        assertEquals(-100, enemy.currentSpeedX, 1);
    }


    @Test
    public void testHandleCollision() {
        EnemyObject enemy = new EnemyObject(0, BubbleBobble.SPRITE_SIZE -2, null);
        ImmutableObject wall = new FloorObject(0,0, null);
        enemy.setDirection(0);
        enemy.handleCollision(wall);
        enemy.update(0.1f);
        assertEquals(100, enemy.currentSpeedX, 1);
    }
}