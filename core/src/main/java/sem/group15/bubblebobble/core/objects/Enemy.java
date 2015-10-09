package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * The enemy objects
 * Created by TUDelft SID on 8-9-2015.
 */
public abstract class Enemy extends Gravity {

    /**
     * Maximum overlap with a wall.
     */
    protected static final float MAX_WALL_OVERLAP = 10f;
    /**
     * Basic speed of an enemy.
     */
    public static final int ENEMY_SPEED = 100;
    /**
     * The Direction the enemy is moving.
     */
    public Direction direction;

    /**
     * Creates an Enemy with position (X,Y) on the grid.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public Enemy(float xPosition, float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
    }

    /**
     * Update the location of the enemy.
     * @param elapsed time elapsed since last gameloop.
     */
    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        location.x += speedX * elapsed;
        location.y += speedY * elapsed;


    }


    /**
     * Sets the horizontal direction of the enemy.
     * And Adjusts its horizontal speed accordingly.
     * @param direction the direction in which the enemy is going.
     */
    public abstract void setDirection(Direction direction);

}
