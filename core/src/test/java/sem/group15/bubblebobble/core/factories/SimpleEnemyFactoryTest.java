package sem.group15.bubblebobble.core.factories;

import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.GameController;
import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.GameObject;

import static org.junit.Assert.*;

public class SimpleEnemyFactoryTest {

    EnemyFactory factory;

    @Before
    public void setUp() {
        factory = new SimpleEnemyFactory();

    }

    @Test
    public void testCreateObject() {
        GameObject object = factory.createObject(5, 10, Enemy.State.NORMAL);
        assertTrue(object instanceof Enemy);
        Enemy enemy = (Enemy) object;
        assertEquals(5, enemy.getLeft(), 0.1f);
        assertEquals(10, enemy.getBottom(), 0.1f);
    }
}