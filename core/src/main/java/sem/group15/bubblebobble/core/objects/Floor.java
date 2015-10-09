package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * object which the player can jump through from the bottom
 * Created by arjo on 8-9-15.
 */
public class Floor extends Immutable {

    public Floor(float locationX, float locationY) {
        super(new Rectangle(locationX, locationY, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
    }
   
    @Override
    public void update(float elapsed) {

    }

    @Override
    public void handleCollision(GameObject collided) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.wall, location.x, location.y);
    }
}
