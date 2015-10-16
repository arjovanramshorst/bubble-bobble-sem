package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by TUDelft SID on 7-9-2015.
 */
public class Bubble extends Floating {

    /**
     * Minimum speed a bubble should have.
     */
    private static final int MINSPEED = 30;
    /**
     * Initial speed of a bubble.
     */
    public static final int INITIAL_SPEED = 600;
    /**
     * Lifespan of a bubble in seconds.
     */
    public static final float BUBBLE_LIFESPAN = 5;
    /**
     * Maximum allowed overlap percentage.
     */
    public static final float PERCENTAGE_OVERLAP_COLLISION = 0.5f;
    /**
     * Time a bubble has been alive.
     */
    private float aliveTime;

    /**
     * Creates a new Bubble object and it will start to float.
     *
     * @param xPosition x position of player who shoots the bubble.
     * @param yPosition y position of player who shoots the bubble.
     * @param direction Enum, RIGHT or LEFT
     */
    public Bubble(final float xPosition, final float yPosition, final Direction direction) {
        super(new Rectangle(xPosition, yPosition,
                BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        switch (direction) {
            case LEFT:
                speedX = -1 * INITIAL_SPEED;
                break;
            case RIGHT:
                speedX = INITIAL_SPEED;
                break;
        }
        speedY = 0;
        aliveTime = 0;
    }

    /**
     * This updates the BubbleObject after a game loop has passed.
     * This updates the location, speed and lasting-duration.
     * The initial speed of the BubbleObject is FOR THE MOMENT 50.
     *
     * @param elapsed Time that has elapsed
     */
    public void update(final float elapsed) {
        //after shoot
        //slow down bubble until very slow and go up.
        if (Math.abs(speedX) < MINSPEED) {
            getOutOfGame();
        } else {
            speedX -= speedX * (elapsed * 4);
        }

        location.x += speedX * elapsed;
        location.y += speedY * elapsed;

        aliveTime += elapsed;
        if (aliveTime > BUBBLE_LIFESPAN) {
            remove = true;
        }
    }

    /**
     * This method is called when:
     * either the bubble lost too much speed or collides with a wall object.
     */
    public void getOutOfGame() {
        if (Math.abs(speedX) > 0) {
            speedX = 0;
            speedY = 50;
        }
    }

    /**
     * This handles the collision for this bubble.
     * It should only be used to update this object, not the other.
     * If the bubble collides with an ImmutableObject.
     * The y speed should change to 0 and the x speed should
     * change to either the right or left.
     *
     * @param other GameObject that collided with this.
     * (only to be used to handle the collision correctly for this GameObject.)
     */
    public final void handleCollision(final GameObject other) {
        if (!remove && location.overlaps(other.getBody())) {
            if (other instanceof Enemy && overlapPercentage(other) > PERCENTAGE_OVERLAP_COLLISION) {

                logger.log("Bubble touched enemy object.");
                remove = true;
                makeFilledBubble((Enemy) other);

            }
        }
    }

    /**
     * Add a new filled bubble to list with new Objects.
     */
    protected void makeFilledBubble(Enemy enemy) {
        newObjects.add(new FilledBubble(getLeft(), getBottom(), enemy));
    }

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     *
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public final void draw(final SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.bubble, location.x, location.y);
    }

}
