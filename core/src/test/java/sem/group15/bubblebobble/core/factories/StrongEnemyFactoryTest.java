package sem.group15.bubblebobble.core.factories;

import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.GameObject;

import static org.junit.Assert.*;

public class StrongEnemyFactoryTest {

    EnemyFactory factory;

    @Before
    public void setUp() {
        factory = new StrongEnemyFactory();
    }

    @Test
    public void testCreateObject() {
        GameObject object = factory.createObject(10, 5, Enemy.State.NORMAL);
        assertTrue(object instanceof Enemy);
        Enemy enemy = (Enemy) object;
        assertEquals(10, enemy.getLeft(), 0.1f);
        assertEquals(5, enemy.getBottom(), 0.1f);
    }

}