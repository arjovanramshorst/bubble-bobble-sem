package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by woute on 9/8/2015.
 */
public class WallObject extends ImmutableObject{

    private float locX, locY;

    /**
     * Create wall object with given x,y coordinates
     * and texture
     * @param x x coordinate from the bottom left corner
     * @param y y coordinate from the bottom left corner
     * @param texture texture for the wall sprite
     */
    public WallObject(float x, float y, Texture texture){
        super(new Rectangle(x, y, 32, 32), texture);
        locX = x;
        locY = y;
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
        spriteBatch.draw(texture, locX, locY);
    }

}
