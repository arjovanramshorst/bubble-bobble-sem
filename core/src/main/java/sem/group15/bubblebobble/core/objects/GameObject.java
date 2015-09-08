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
}
