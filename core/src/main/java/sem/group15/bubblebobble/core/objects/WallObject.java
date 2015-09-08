package sem.group15.bubblebobble.core.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by woute on 9/8/2015.
 */
public class WallObject extends ImmutableObject{
    private float locX, locY;
    public WallObject(float x, float y, Texture texture){
        super(new Rectangle(x, y, 32, 32), texture);
        locX = x;
        locY = y;
    }

    public void update(float elapsed){

    }

    public void checkCollision(GameObject other){

    }

    public void handleCollision(GameObject collided){

    }

    public void draw(SpriteBatch spriteBatch){

        spriteBatch.draw(texture, locX, locY);
    }

}
