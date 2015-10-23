package sem.group15.bubblebobble.core.factories;

import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.StrongEnemy;

/**
 * Created by arjo on 8-10-15.
 */
public class StrongEnemyFactory extends EnemyFactory {

    @Override
    public Enemy createObject(final float x, final float y, Enemy.State state) {
        Enemy enemy = new StrongEnemy(x, y);
        instantiateEnemy(enemy);
        return enemy;
    }
}
