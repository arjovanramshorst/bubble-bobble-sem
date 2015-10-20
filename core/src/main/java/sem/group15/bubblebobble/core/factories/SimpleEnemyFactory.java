package sem.group15.bubblebobble.core.factories;

import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.SimpleEnemy;

/**
 * Created by arjo on 8-10-15.
 */
public class SimpleEnemyFactory extends EnemyFactory {

    @Override
    public Enemy createObject(float x, float y) {
        Enemy enemy = new SimpleEnemy(x, y);
        setRandomDirection(enemy);
        enemy.setState(Enemy.State.NORMAL);
        return enemy;
    }
}
