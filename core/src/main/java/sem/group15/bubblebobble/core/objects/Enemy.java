package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.Logger;

import java.util.Random;

/**
 * The enemy objects
 * Created by TUDelft SID on 8-9-2015.
 */
public abstract class Enemy extends GravityObject {

    protected static final float MAX_WALL_OVERLAP = 10f;
    public static final int ENEMY_SPEED = 100;
    public Direction direction;

    /**
     * Creates an Enemy with position (X,Y) on the grid.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public Enemy(float xPosition, float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        //Generates random integer between 1 and 2.
        int random = 1 + (int) (Math.random() * ((2 - 1) + 1));
        assert (random == 1 || random == 2);
        if (random == 1) {
            setDirection(Direction.RIGHT);
        } else {
            setDirection(Direction.LEFT);
        }
    }

    /**
     * If the enemy collides with a wall, the direction should change in the opposite direction.
     * @param elapsed time elapsed since last gameloop.
     */
    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        location.x += speedX * elapsed;
        location.y += speedY * elapsed;


    }


    /**
     * Sets the horizontal direction of the enemy, and adjusts its horizontal speed accordingly.
     * @param direction the direction in which the enemy is going.
     */
    public abstract void setDirection(Direction direction);

}
