package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

import java.util.Random;

/**
 * Created by TUDelft SID on 8-9-2015.
 */
public class EnemyObject extends GravityObject {

    protected boolean wallCollision;
  //  protected float timeSinceLastWallCollision;
    protected boolean isAlive;

    /**
     * Creates an EnemyObject with position (X,Y) on the grid.
     * @param xPosition
     * @param yPosition
     */
    public EnemyObject(float xPosition, float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE), new Texture(Gdx.files.internal("enemy.png")));
        //Generates random integer between 1 and 2.
        int random = 1 + (int) (Math.random() * ((2 - 1) + 1));
        assert (random == 1 || random == 2);
        if (random == 1) {
            currentSpeedX = 100;
        } else {
            currentSpeedX = -100;
        }
      //  this.timeSinceLastWallCollision = 0;
        isAlive = true;
    }

    /**
     * Creates an EnemyObject used only for testing purposes.
     * @param xPosition
     * @param yPosition
     * @param texture
     */
    public EnemyObject(float xPosition, float yPosition, Texture texture) {
        super(
                new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE),
                texture
        );
        //Generates random integer between 1 and 2.
        int random = 1 + (int) (Math.random() * ((2 - 1) + 1));
        assert (random == 1 || random == 2);
        if (random == 1) {
            currentSpeedX = 100;
        } else {
            currentSpeedX = -100;
        }
      //  this.timeSinceLastWallCollision = 0;
        isAlive = true;
    }


    /**
     * If the enemy collides with a wall, the direction should change in the opposite direction.
     * @param elapsed time elapsed since last gameloop.
     */
    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        location.x += currentSpeedX * elapsed;
    }

    /**
     * Handles collisions with other objects. If there is a WallCollision set the attribute wallCollision to true.
     * @param other Object that needs to be checked for collision.
     */
    @Override
    public void handleCollision(GameObject other) {
        super.handleCollision(other);
        //wallobject

        if(other instanceof WallObject) {
            if(overlapLeft(other) > 0) {
                setLeft(other.getRight());
                currentSpeedX *= -1;
            }
            else if (overlapRight(other) > 0) {
                setRight(other.getLeft());
                currentSpeedX *= -1;
            }
        }
        if (other instanceof BubbleObject) {
            isAlive = false;
        }
    }

    /**
     * Checks if the enemy is still alive
     * @return
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Draws the sprite at the correct location.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }

}