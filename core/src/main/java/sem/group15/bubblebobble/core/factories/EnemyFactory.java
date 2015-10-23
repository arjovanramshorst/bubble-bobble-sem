package sem.group15.bubblebobble.core.factories;

import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.GameObject;

/**
 * Created by arjo on 8-10-15.
 */
public abstract class EnemyFactory {
    /**
     * creates an enemy.
     * @param x location x.
     * @param y location y.
     * @return returns Enemy.
     */
    public abstract Enemy createObject(float x, float y, Enemy.State state);

    protected void instantiateEnemy(Enemy enemy) {
        setRandomDirection(enemy);
    }

    /**
     * sets random direction for enemy.
     * @param enemy Enemy to set.
     */
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
