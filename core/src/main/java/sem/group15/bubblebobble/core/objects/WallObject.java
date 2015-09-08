package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by woute on 9/8/2015.
 */
public class WallObject extends ImmutableObject{

    /**
     * Create wall object with given x,y coordinates
     * and texture
     * @param x x coordinate from the bottom left corner
     * @param y y coordinate from the bottom left corner
     */
    public WallObject(float x, float y){
        super(
                new Rectangle(x, y, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE),
                new Texture(Gdx.files.internal("sprite_wall_brick.png"))
        );
    }

    /**
     * walls dont need to be updated?
     * @param elapsed time elapsed since last gameloop.
     */
    public void update(float elapsed){

    }

    public void checkCollision(GameObject other){
    }

    public void handleCollision(GameObject collided){

    }

    /**
     * Draw sprite for wallobject
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, location.x, location.y);
    }

}
