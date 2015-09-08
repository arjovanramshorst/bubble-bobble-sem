package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by TUDelft SID on 8-9-2015.
 */
public class EnemyObject extends GravityObject{

    protected boolean wallCollision;


    public EnemyObject(float xPosition, float yPosition) {
        super(new Rectangle(32,32,xPosition,yPosition), new Texture(Gdx.files.internal("aqua-ball.png")));
    }

    public EnemyObject(float xPosition, float yPosition, Texture texture) {
        super(
                new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE),
                texture
        );
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);

        if (!wallCollision) {
            currentSpeedX = 100;
        } else {
            currentSpeedX = -1 * currentSpeedX;
            wallCollision = false;
        }

        location.x += currentSpeedX * elapsed;
        location.y += currentSpeedY * elapsed;
    }

    @Override
    public void handleCollision(GameObject other) { 
        super.handleCollision(other);
        //wallobject
        if(other instanceof ImmutableObject) {
            if (location.overlaps(other.getBody())) {
                wallCollision = true;
            }


        }

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }
}
