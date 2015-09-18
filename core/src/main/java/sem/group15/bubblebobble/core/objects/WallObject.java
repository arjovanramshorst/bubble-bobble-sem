package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.Logger;

/**
 * Created by woute on 9/8/2015.
 */
public class WallObject extends ImmutableObject{

    private static final Logger logger = Logger.getLogger(WallObject.class.getName());

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

    public WallObject(float x, float y, Texture texture){
        super(
                new Rectangle(x, y, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE),texture
        );
    }

    /**
     * Necessary method, since every not abstract subclass of GameObject needs this method. Might be needed in the
     * future.
     * @param elapsed time elapsed since last gameloop.
     */
    public void update(float elapsed){

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
