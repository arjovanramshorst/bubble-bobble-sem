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
public class EnemyObject extends GravityObject {

    private static final float MAX_WALL_OVERLAP = 10f;
    private static final int ENEMY_SPEED = 100;

    /**
     * Creates an EnemyObject with position (X,Y) on the grid.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public EnemyObject(float xPosition, float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE), new Texture(Gdx.files.internal("enemy.png")));

        //Generates random integer between 1 and 2.
        int random = 1 + (int) (Math.random() * ((2 - 1) + 1));
        assert (random == 1 || random == 2);
        if (random == 1) {
            setHorizontalSpeed(ENEMY_SPEED);
        } else {
            setHorizontalSpeed(-ENEMY_SPEED);
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
     * Handles collisions with other objects. If there is a WallCollision set the attribute wallCollision to true.
     * @param other Object that needs to be checked for collision.
     */
    @Override
    public void handleCollision(GameObject other) {
        super.handleCollision(other);

        if (location.overlaps(other.getBody())) {

            if (other instanceof WallObject) {
                if (between(overlapLeft(other), 0, MAX_WALL_OVERLAP)) {
                    setLeft(other.getRight());
                    setHorizontalSpeed(-1 * speedX);
                }
                if (between(overlapRight(other), 0, MAX_WALL_OVERLAP)) {
                    setRight(other.getLeft());
                    setHorizontalSpeed(speedX* -1);
                }
            }
            if (other instanceof BubbleObject && ! other.remove()) {
                remove = true;
            }
        }
    }

    /**
     * Draws the sprite at the correct location.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }


    /**
     * Sets the horizontal speed of the enemy
     * @param speed the speed in floats.
     */
    public void setHorizontalSpeed(float speed) {
        this.speedX = speed;
    }

}
