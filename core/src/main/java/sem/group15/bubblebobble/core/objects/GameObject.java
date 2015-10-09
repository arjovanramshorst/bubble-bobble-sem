package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.Assets;
import sem.group15.bubblebobble.core.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjo on 6-9-15.
 */
public abstract class GameObject {

    protected Assets assets;

    protected Rectangle location;
    protected float speedX;
    protected float speedY;

    protected Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    protected enum Direction {
        LEFT, RIGHT
    }

    /**
     * List that contains items that need to be added to the game.
     * After each cycle this list is cleared.
     */
    protected List<GameObject> newObjects;

    /**
     * If this boolean is set to true, this item will be removed from the List.
     * This is not the same as is alive or something,
     * it only means that this item can be picked up by the garbage collector.
     */
    protected boolean remove;

    protected GameObject(Rectangle location) {
        this.location = location;
        this.texture = texture;
        newObjects = new ArrayList<GameObject>();
        remove = false;
        logger = Logger.getLogger(this.getClass().getName());
        assets = Assets.getAssets();
    }

    /**
     * This updates this object after a game loop has passed.
     * This updates all the variables, like location or speed,
     * depending on the kind of subclass this is.
     * @param elapsed time elapsed since last gameloop.
     */
    public abstract void update(float elapsed);

    /**
     * This handles the collision for this object.
     * It should only be used to update this object, not the other.
     * @param collided GameObject that collided with this.
     *                 (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */
    public abstract void handleCollision(GameObject collided);

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public abstract void draw(SpriteBatch spriteBatch);

    public final Rectangle getBody() {
        return location;
    }

    public final float getLeft() {
        return location.getX();
    }

    public final float getRight() {
        return location.getX() + location.getWidth();
    }

    public final float getTop() {
        return location.getY() + location.getHeight();
    }

    public final float getBottom() {
        return location.getY();
    }

    public final void setLeft(float x) {
        location.x = x;
    }

    public final void setRight(float x) {
        location.x = x - location.getWidth();
    }

    public final void setTop(float y) {
        location.y = y - location.getHeight();
    }

    public final void setBottom(float y) {
        location.y = y;
    }

    public final boolean collidesWith(GameObject other)
    {
        return getBody().overlaps(other.getBody());
    }

    public final float overlapLeft(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getLeft() >= other.getLeft()) {
            overlap = other.getRight() - getLeft();
        }
        return overlap;
    }

    public final float overlapRight(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getRight() <= other.getRight()) {
            overlap = getRight() - other.getLeft();
        }
        return overlap;
    }
    public final float overlapTop(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getTop() <= other.getTop()) {
            overlap = getTop() - other.getBottom();
        }
        return overlap;
    }
    public final float overlapBottom(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getBottom() >= other.getBottom()) {
            overlap = other.getTop() - getBottom();
        }
        return overlap;
    }

    public final boolean between(float value, float low, float high) {
        return (value > low && value < high);
    }

    public final void addNewObjectsTo(List<GameObject> gameObjects) {
        if(! newObjects.isEmpty()) {
            for(GameObject object : newObjects) {
                gameObjects.add(object);
            }
            newObjects.clear();
        }
    }

    /**
     * Returns the smallest of either the horizontal or vertical overlap.
     * @param other GameObject to compare to.
     * @return percentage overlap.
     */
    public final float overlapPercentage(GameObject other) {
        float maxVerticalOverlap = Math.max(overlapTop(other), overlapBottom(other));
        float maxHorizontalOverlap = Math.max(overlapLeft(other), overlapRight(other));
        return Math.min(maxHorizontalOverlap, maxVerticalOverlap) / BubbleBobble.SPRITE_SIZE;
    }

    public final boolean remove() {
        return remove;
    }
}
