package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.Assets;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjo on 6-9-15.
 */
public abstract class GameObject {

    /**
     * Asset manager.
     */
    protected Assets assets;
    /**
     * location of the object.
     */
    protected Rectangle location;
    /**
     * speed of the object.
     */
    protected float speedX;
    /**
     * speed of the object.
     */
    protected float speedY;
    /**
     * prints the interaction.
     */
    protected Logger logger;

    /**
     * Set the Logger.
     * @param logger what you want the logger to be.
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    public enum Direction {
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

    /**
     * Abstract GameObject constructor.
     * @param location of the GameObject
     */
    protected GameObject(Rectangle location) {
        this.location = location;
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

    /**
     * Get the location.
     * @return location: Rectangle.
     */
    public final Rectangle getBody() {
        return location;
    }

    /**
     * Get the X value of the left side.
     * @return value of left side.
     */
    public final float getLeft() {
        return location.getX();
    }

    /**
     * Value of right X location.
     * @return Right X location.
     */
    public final float getRight() {
        return location.getX() + location.getWidth();
    }

    /**
     * Get Y value of the top.
     * @return Top Y Value
     */
    public final float getTop() {
        return location.getY() + location.getHeight();
    }

    /**
     * Get X value of the bottom.
     * @return Bottom Y value.
     */
    public final float getBottom() {
        return location.getY();
    }

    /**
     * Set left x value.
     * @param x value for left side of the rectangle.
     */
    public final void setLeft(float x) {
        location.x = x;
    }

    /**
     * Set right x value.
     * @param x value for right side of the rectangle.
     */
    public final void setRight(float x) {
        location.x = x - location.getWidth();
    }

    /**
     * Set top y value.
     * @param y value for top side of the rectangle.
     */
    public final void setTop(float y) {
        location.y = y - location.getHeight();
    }

    /**
     * Set bottom y value.
     * @param y value for bottom side of the rectangle.
     */
    public final void setBottom(float y) {
        location.y = y;
    }

    /**
     * Check if there is overlap of rectangles.
     * @param other rectangle of other object.
     * @return true if overlap, else false.
     */
    public final boolean collidesWith(GameObject other)
    {
        return getBody().overlaps(other.getBody());
    }

    /**
     * Check how much overlap there is on the left side of the rectangle.
     * @param other rectangle of other object.
     * @return amount of overlap.
     */
    public final float overlapLeft(GameObject other) {
        float overlap = 0;
        if (collidesWith(other) && getLeft() >= other.getLeft()) {
            overlap = other.getRight() - getLeft();
        }
        return overlap;
    }

    /**
     * Check how much overlap there is on the right side of the rectangle.
     * @param other rectangle of other object.
     * @return amount of overlap.
     */
    public final float overlapRight(GameObject other) {
        float overlap = 0;
        if (collidesWith(other) && getRight() <= other.getRight()) {
            overlap = getRight() - other.getLeft();
        }
        return overlap;
    }

    /**
     * Check how much overlap there is on the top side of the rectangle.
     * @param other rectangle of other object.
     * @return amount of overlap.
     */
    public final float overlapTop(GameObject other) {
        float overlap = 0;
        if (collidesWith(other) && getTop() <= other.getTop()) {
            overlap = getTop() - other.getBottom();
        }
        return overlap;
    }

    /**
     * Check how much overlap there is on the bottom side of the rectangle.
     * @param other rectangle of other object.
     * @return amount of overlap.
     */
    public final float overlapBottom(GameObject other) {
        float overlap = 0;
        if (collidesWith(other) && getBottom() >= other.getBottom()) {
            overlap = other.getTop() - getBottom();
        }
        return overlap;
    }

    /**
     * Calculate if a value if between two other values.
     * @param value value you want be be between.
     * @param low lower bound
     * @param high upper bound
     * @return True if value is between low and high
     */
    public final boolean between(float value, float low, float high) {
        return (value > low && value < high);
    }
    
    /**
     * Add new objects to a list.
     * @param gameObjects list to add the objects to.
     */
    public final void addNewObjectsTo(List<GameObject> gameObjects) {
        if (!newObjects.isEmpty()) {
            for (GameObject object : newObjects) {
                if(object instanceof Enemy) {
                    gameObjects.add(1, object);
                } else {
                    gameObjects.add(object);
                }
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

    /**
     * Setter for remove.
     * @param remove
     */
    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public final boolean remove() {
        return remove;
    }

    /**
     * Set the new objects list.
     * @param newObjects
     */
    public void setNewObjects(List<GameObject> newObjects) {
        this.newObjects = newObjects;
    }
}
