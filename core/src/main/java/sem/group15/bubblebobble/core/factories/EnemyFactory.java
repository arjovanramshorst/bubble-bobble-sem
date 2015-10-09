package sem.group15.bubblebobble.core.factories;

import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.GameObject;

/**
 * Created by arjo on 8-10-15.
 */
public abstract class EnemyFactory {
    public abstract Enemy createObject(float x, float y);

    protected void setRandomDirection(Enemy enemy) {
        //Generates random integer between 1 and 2.
        int random = 1 + (int) (Math.random() * ((2 - 1) + 1));
        assert (random == 1 || random == 2);
        if (random == 1) {
            enemy.setDirection(GameObject.Direction.RIGHT);
        } else {
            enemy.setDirection(GameObject.Direction.LEFT);
        }
    }
}
