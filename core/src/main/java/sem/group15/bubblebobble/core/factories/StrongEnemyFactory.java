package sem.group15.bubblebobble.core.factories;

import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.StrongEnemy;

/**
 * Created by arjo on 8-10-15.
 */
public class StrongEnemyFactory extends EnemyFactory {

    @Override
    public Enemy createObject(float x, float y) {
        Enemy enemy = new StrongEnemy(x,y);
        setRandomDirection(enemy);
        enemy.setState(Enemy.State.NORMAL);
        return enemy;
    }
}
