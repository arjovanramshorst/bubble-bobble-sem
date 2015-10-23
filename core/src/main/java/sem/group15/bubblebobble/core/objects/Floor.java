package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * object which the player can jump through from the bottom
 * Created by arjo on 8-9-15.
 */
public class Floor extends Immutable {

    /**
     * Create a floor object.
     * @param locationX X location of the object.
     * @param locationY Y location of the object.
     */
    public Floor(final float locationX, final float locationY) {
        super(new Rectangle(locationX, locationY, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
    }

    @Override
    public void update(final float elapsed) {

    }

    @Override
    public void handleCollision(final GameObject collided) {

    }

    @Override
    public final void draw(final SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.getWall(), location.x, location.y);
    }
}
