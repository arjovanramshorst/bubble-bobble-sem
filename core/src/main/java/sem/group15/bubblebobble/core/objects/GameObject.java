package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjo on 6-9-15.
 */
public abstract class GameObject {

    protected Rectangle location;
    protected Texture texture;

    /**
     * List that contains items that need to be added to the game. After each cycle this list is cleared.
     */
    protected List<GameObject> newObjects;

    /**
     * If this boolean is set to true, this item will be removed from the List. This is not the same as is alive
     * or something, it only means that this item can be picked up by the garbage collector.
     */
    protected boolean remove;

    protected GameObject(Rectangle location, Texture texture) {
        this.location = location;
        this.texture = texture;
        newObjects = new ArrayList<GameObject>();
        remove = false;
    }

    /**
     * This updates this object after a game loop has passed. This updates all the variables, like location or speed,
     * depending on the kind of subclass this is.
     * @param elapsed time elapsed since last gameloop.
     */
    public abstract void update(float elapsed);

    /**
     * This handles the collision for this object. It should only be used to update this object, not the other.
     * @param collided GameObject that collided with this. (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */
    public abstract void handleCollision(GameObject collided);

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public abstract void draw(SpriteBatch spriteBatch);

    public Rectangle getBody() {
        return location;
    }

    public float getLeft() {
        return location.getX();
    }

    public float getRight() {
        return location.getX() + location.getWidth();
    }

    public float getTop() {
        return location.getY() + location.getHeight();
    }

    public float getBottom() {
        return location.getY();
    }

    public void setLeft(float x) {
        location.x = x;
    }

    public void setRight(float x) {
        location.x = x - location.getWidth();
    }

    public void setTop(float y) {
        location.y = y - location.getHeight();
    }

    public void setBottom(float y) {
        location.y = y;
    }

    public boolean collidesWith(GameObject other)
    {
        return getBody().overlaps(other.getBody());
    }

    public float overlapLeft(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getLeft() < other.getRight()) {
            overlap = other.getRight() - getLeft();
        }
        return overlap;
    }

    public float overlapRight(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getRight() > other.getLeft()) {
            overlap = getRight() - other.getLeft();
        }
        return overlap;
    }
    public float overlapTop(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getTop() > other.getBottom()) {
            overlap = getTop() - other.getBottom();
        }
        return overlap;
    }
    public float overlapBottom(GameObject other) {
        float overlap = 0;
        if(collidesWith(other) && getBottom() < other.getTop()) {
            overlap = other.getTop() - getBottom();
        }
        return overlap;
    }

    public boolean between(float value, float low, float high) {
        return (value > low && value < high);
    }

    public void addNewObjectsTo(List<GameObject> gameObjects) {
        if(! newObjects.isEmpty()) {
            for(GameObject object : newObjects) {
                gameObjects.add(object);
            }
            newObjects.clear();
        }
    }

    public boolean remove() {
        return remove;
    }
}
