package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by arjo on 8-9-15.
 */
public class FloorObject extends ImmutableObject {

    public FloorObject(int locationX, int locationY) {
        super(new Rectangle(locationX, locationY, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE), new Texture(Gdx.files.internal("floor.png")));
    }
    public FloorObject(int locationX, int locationY, Texture texture) {
        super(
                new Rectangle(locationX, locationY, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE),
                texture
        );
    }

    @Override
    public void update(float elapsed) {

    }

    @Override
    public void handleCollision(GameObject collided) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }
}