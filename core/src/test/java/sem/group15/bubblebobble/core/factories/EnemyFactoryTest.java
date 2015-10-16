package sem.group15.bubblebobble.core.factories;

import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.SimpleEnemy;

import static org.junit.Assert.*;

/**
 * Created by Matthijs on 10/16/15.
 */
public class EnemyFactoryTest {

    @Test
    public void testSetRandomDirection() throws Exception {
        Enemy enemy=Mockito.mock(Enemy.class);
        EnemyFactory factory= new SimpleEnemyFactory();
        for(int i=0;i<100;i++) {
            factory.setRandomDirection(enemy);
            Mockito.verify(enemy, Mockito.times(i+1)).setDirection(Mockito.anyObject());

        }
    }
}