package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.Logger;

/**
 * Created by TUDelft SID on 7-9-2015.
 */
public class BubbleObject extends FloatingObject {

    private static final int MINSPEED = 30;

    private static final int INITIAL_SPEED = 600;

    /**
     * Creates a new Bubble object and it will start to float
     *
     * @param xPosition x position of player who shoots the bubble.
     * @param yPosition y position of player who shoots the bubble.
     * @param direction Enum, RIGHT or LEFT
     */
    public BubbleObject(float xPosition, float yPosition, Direction direction) {
        super(new Rectangle(xPosition, yPosition, 32, 32), new Texture(Gdx.files.internal("bubble-empty.png")));
        logger= Logger.getLogger(BubbleObject.class.getName());

        switch (direction) {
            case LEFT:
                speedX = -1 * INITIAL_SPEED;
                break;
            case RIGHT:
                speedX = INITIAL_SPEED;
                break;
        }
        speedY = 0;
    }

    /**
     * For Testing purposes
     * @param xPosition
     * @param yPosition
     * @param direction
     * @param texture
     */
    public BubbleObject(float xPosition, float yPosition, Direction direction, Texture texture) {
        super(new Rectangle(xPosition, yPosition, 32, 32), texture);
        switch (direction) {
            case LEFT:
                speedX = -1 * INITIAL_SPEED;
                break;
            case RIGHT:
                speedX = INITIAL_SPEED;
                break;
        }
        speedY = 0;
    }

    /**
     * This updates the BubbleObject after a game loop has passed.
     * This updates the location, speed and lasting-duration.
     * The initial speed of the BubbleObject is FOR THE MOMENT 50.
     *
     * @param elapsed
     */
    public void update(float elapsed) {
        //after shoot
        //slow down bubble until very slow and go up.
        if (Math.abs(speedX) < MINSPEED) {
            getOutOfGame();
        } else {
            speedX -= speedX * (elapsed * 4);
        }

        location.x += speedX * elapsed;
        location.y += speedY* elapsed;

    }

    /**
     * This method is called if either the bubble lost too much speed or collides with a wall object.
     */
    public void getOutOfGame() {

        if ( Math.abs(speedX) > 0 ) {
            speedX = 0;
            speedY = 50;
        }


    }

    /**
     * This handles the collision for this bubble. It should only be used to update this object, not the other.
     * If the bubble collides with an ImmutableObject, the y speed should change to 0 and the x speed should
     * change to either the right or left.
     *
     * @param collided GameObject that collided with this. (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */
    public void handleCollision(GameObject collided) {
        if (!remove && location.overlaps(collided.getBody())) {

            if (collided instanceof EnemyObject) {
                logger.log("Bubble touched enemy object.");
                remove = true;
                makeFilledBubble();
            }

        }

    }

    protected void makeFilledBubble() {
        newObjects.add(new FilledBubbleObject(getLeft(), getBottom()));
    }

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     *
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }

}
