package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by TUDelft SID on 7-9-2015.
 */
public class BubbleObject extends FloatingObject {

    private static final int MINSPEED = 30;

    /**
     * Creates a new Bubble object and it will start to float
     * @param xPosition x position of player who shoots the bubble.
     * @param yPosition y position of player who shoots the bubble.
     * @param direction 1 if player is look right, -1 if player is looking left.
     */
    public BubbleObject(float xPosition, float yPosition, int direction) {
        super(new Rectangle(32,32,xPosition,yPosition), new Texture(Gdx.files.internal("aqua-ball.png")));
        xSpeed = 600 * direction;
        ySpeed = 0;
    }

    /**
     * This updates the BubbleObject after a game loop has passed.
     * This updates the location, speed and lasting-duration.
     * The initial speed of the FilledBubble is FOR THE MOMENT 50.
     * @param elapsed
     */
    public void update(float elapsed) {
        //after shoot

        //slow down bubble until very slow and go up.
        if(xSpeed < MINSPEED){
            getOutOfGame();
        }else{
            xSpeed -= xSpeed * (elapsed * 4);
        }

        location.x += xSpeed * elapsed;
        location.y += ySpeed * elapsed;
    }

    /**
     * This method is called if either the bubble lost too much speed or collides with a wall object.
     */
    public void getOutOfGame(){
        xSpeed = 0;
        ySpeed = 50;
    }

    /**
     * This methods checks if the bubble collides with another object.
     * @param other Object that needs to be checked for a collision.
     */

    public void checkCollision(GameObject other) {

    }


    /**
     * This handles the collision for this bubble. It should only be used to update this object, not the other.
     * If the bubble collides with an ImmutableObject, the y speed should change to 0 and the x speed should
     * change to either the right or left.
     * @param collided GameObject that collided with this. (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */

    protected void handleCollision(GameObject collided) {
        if (collided instanceof ImmutableObject) {
            // should not go through the immutableObject - stop y speed and go x speed untill objects don't collide.
        }

    }

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }

}
