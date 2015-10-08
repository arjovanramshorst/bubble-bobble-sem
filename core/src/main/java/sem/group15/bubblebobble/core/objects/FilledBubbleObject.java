package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by TUDelft SID on 7-9-2015.
 */
public class FilledBubbleObject extends FloatingObject {

    
    /**
     * Constructor for the filledBubbleObject.
     * @param xPosition - xPosition of the Rectangle
     * @param yPosition - yPosition of the Rectangle
     */
    public FilledBubbleObject(float xPosition, float yPosition) {
        super(new Rectangle(xPosition, yPosition, 32, 32));

        speedY = 50;
        speedX = 0;
    }


    /**
     * This updates the FilledBubbleObject after a game loop has passed.
     * This updates the location, speed and lasting-duration.
     * The initial speed of the FilledBubbleObject is FOR THE MOMENT 50.
     * @param elapsed - time that has passed
     */
    public  void update(float elapsed) {
        location.y += speedY * elapsed;
    }

    /**
     * This handles the collision for this FilledBubbleObject. It should only be used to update this object, not the other.
     * If the FilledBubbleObject collides with an ImmutableObject, the y speed should change to 0 and the x speed should
     * change to either the right or left.
     * @param collided GameObject that collided with this. (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */
    public  void handleCollision(GameObject collided) {
        if (location.overlaps(collided.getBody())) {
            if (collided instanceof ImmutableObject) {
                // should not go through the immutableObject - stop y speed and go x speed untill objects don't collide.
                speedY = 0;
            }

            if (collided instanceof PlayerObject) {
                remove = true;
            }
        }
    }

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public  void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.filledBubble, location.x, location.y);
    }

}
