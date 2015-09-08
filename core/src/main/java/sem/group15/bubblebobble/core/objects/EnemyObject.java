package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by TUDelft SID on 8-9-2015.
 */
public class EnemyObject extends GravityObject{

    protected boolean wallCollision;


    public EnemyObject(float xPosition, float yPosition) {
        super(new Rectangle(32,32,xPosition,yPosition), new Texture(Gdx.files.internal("aqua-ball.png")));
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);

        if (!wallCollision) {
            currentSpeedX = 100;
        } else {
            currentSpeedX = -1 * currentSpeedX;
        }

        location.x += currentSpeedX * elapsed;
        location.y += currentSpeedY * elapsed;
    }



    public void checkCollision(GameObject other) {

    }

    @Override
    public void handleCollision(GameObject other) { 
        super.handleCollision(other);
        if(other instanceof ImmutableObject) {

        }

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }
}
