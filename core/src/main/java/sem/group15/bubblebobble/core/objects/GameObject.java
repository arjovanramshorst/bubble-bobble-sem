package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arjo on 6-9-15.
 */
public abstract class GameObject {

    protected Rectangle location;
    protected Texture texture;

    protected GameObject(Rectangle location, Texture texture) {
        this.location = location;
        this.texture = texture;
    }

    /**
     * This updates this object after a game loop has passed. This updates all the variables, like location or speed,
     * depending on the kind of subclass this is.
     * @param elapsed time elapsed since last gameloop.
     */
    public abstract void update(float elapsed);

    /**
     * Checks the List of all objects that are currently on screen for collisions. (Only checks collisions for GameObjects
     * that are necessary for this GameObject.)
     *
     * TO THINK ABOUT: Possible to check all collisions only one way and then handle them for both objects. Probably by
     * using int startingPoint in the LogicController. If a collision is found, this should call both this Objects'
     * handleCollision method, and the collided object.
     *
     * ALSO: I'm not sure if this should happen in this object, or in the logicController. That might be a better place.
     * @param other Object that needs to be checked for a collision.
     */
    public abstract void checkCollision(GameObject other);

    /**
     * This handles the collision for this object. It should only be used to update this object, not the other.
     * @param collided GameObject that collided with this. (only to be used to handle the collision correctly for this
     *                 GameObject.)
     */
    protected abstract void handleCollision(GameObject collided);

    /**
     * This adds this sprite to the SpriteBatch, supplied by the LogicController.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public abstract void draw(SpriteBatch spriteBatch);
}
