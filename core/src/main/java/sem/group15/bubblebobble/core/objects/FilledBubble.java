package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.factories.EnemyFactory;
import sem.group15.bubblebobble.core.factories.SimpleEnemyFactory;
import sem.group15.bubblebobble.core.factories.StrongEnemyFactory;

/**
 * Created by TUDelft SID on 7-9-2015.
 */
public class FilledBubble extends Floating {

    /**
     * seconds the filledBubble is alive.
     */
    public static final float FILLED_LIFESPAN = 5;
    /**
     * The factory that makes the bubbles.
     */
    private EnemyFactory factory;

    /**
     * Constructor for the filledBubbleObject.
     * @param xPosition - xPosition of the Rectangle
     * @param yPosition - yPosition of the Rectangle
     * @param enemy - enemy to be in the bubble
     */
    public FilledBubble(final float xPosition, final float yPosition, final Enemy enemy) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        if (enemy instanceof SimpleEnemy){
            factory = new SimpleEnemyFactory();}
        else if (enemy instanceof StrongEnemy){
            factory = new StrongEnemyFactory();}

        speedY = 50;
        speedX = 0;
    }

    /**
     * This updates the FilledBubbleObject after a game loop has passed.
     * This updates the location, speed and lasting-duration.
     * The initial speed of the FilledBubbleObject is FOR THE MOMENT 50.
     * @param elapsed - time that has passed
     */
    public final void update(final float elapsed) {
        super.update(elapsed);
        location.y += speedY * elapsed;
        location.y = Math.min(location.y, Gdx.graphics.getHeight() - BubbleBobble.SPRITE_SIZE);
        if (timeFromFired > FILLED_LIFESPAN) {
            remove = true;
            Enemy enemy = factory.createObject(location.x, location.y);
            enemy.state = Enemy.State.ANGRY;
            newObjects.add(enemy);

        }

    }

    /**
     * This handles the collision for this FilledBubbleObject.
     * It should only be used to update this object, not the other.
     * If the FilledBubbleObject collides with an ImmutableObject,
     * the y speed should change to 0 and the x speed should
     * change to either the right or left.
     * @param collided GameObject that collided with this.
     * (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */
    public final void handleCollision(final GameObject collided) {
        if (location.overlaps(collided.getBody())) {
            if (collided instanceof Immutable) {
                // should not go through the immutableObject - stop y speed and go x speed untill objects don't collide.
                speedY = 0;}

            if (collided instanceof Player) {
                remove = true;
                if (timeFromFired < Bubble.BUBBLE_LIFESPAN/2) {
                    newObjects.add(new Cherry(getLeft(), getBottom()));
                }
                else {
                    newObjects.add(new Banana(getLeft(), getBottom()));
                }
            }
        }
    }

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public final void draw(final SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.filledBubble, location.x, location.y);
    }

}
