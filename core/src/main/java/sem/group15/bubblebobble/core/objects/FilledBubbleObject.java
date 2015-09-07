package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by TUDelft SID on 7-9-2015.
 */
public class FilledBubbleObject extends FloatingObject {

    protected float duration;

    protected FilledBubbleObject(Rectangle size, Texture texture, float xPosition, float yPosition) {
        super(size, texture, xPosition, yPosition);
    }


    /**
     * This updates the FilledBubbleObject after a game loop has passed.
     * This updates the location, speed and lasting-duration.
     * The initial speed of the FilledBubbleObject is FOR THE MOMENT 50.
     * @param elapsed
     */
    public  void update(float elapsed) {
        xSpeed = 0;
        ySpeed = 50;
        duration = 0;
    }

    /**
     * This methods checks if the bubble collides with another object.
     * @param other Object that needs to be checked for a collision.
     */
    public  void checkCollision(GameObject other) {

    }

    /**
     * This handles the collision for this FilledBubbleObject. It should only be used to update this object, not the other.
     * If the FilledBubbleObject collides with an ImmutableObject, the y speed should change to 0 and the x speed should
     * change to either the right or left.
     * @param collided GameObject that collided with this. (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */
    protected  void handleCollision(GameObject collided) {
        if (collided instanceof ImmutableObject) {
            // should not go through the immutableObject - stop y speed and go x speed untill objects don't collide.
        }

        if (collided instanceof PlayerObject) {
            // dispose + add to score.
        }

    }

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public  void draw(SpriteBatch spriteBatch) {

    }

}
