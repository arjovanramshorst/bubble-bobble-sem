package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by woute on 9/8/2015.
 */
public class Wall extends Immutable {


    /**
     * Create wall object with given x,y coordinates.
     * and texture.
     * @param x x coordinate from the bottom left corner
     * @param y y coordinate from the bottom left corner
     */
    public Wall(final float x, final float y) {
        super(
                new Rectangle(x, y, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE)
        );
    }

    /**
     * Necessary method, since every not abstract subclass of GameObject needs this method.
     * Might be needed in the future.
     * @param elapsed time elapsed since last gameloop.
     */
    public void update(final float elapsed) {
    }


    /**
     * Check for collisions.
     * @param collided GameObject that collided with this.
     * (only to be used to handle the collision correctly for this.
     */
    public final void handleCollision(final GameObject collided) {

    }

    /**
     * Draw sprite for wallobject.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public final void draw(final SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.getWall(), location.x, location.y);
    }

}
