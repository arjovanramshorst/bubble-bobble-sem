package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arjo on 7-9-15.
 */
public class PlayerObject extends GravityObject {

    public PlayerObject(float xPosition, float yPosition) {
        super(new Rectangle(), new Texture(Gdx.files.internal("aqua-ball.png")), xPosition, yPosition);
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentSpeedX = -100;
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentSpeedX = 100;
        } else {
            currentSpeedX = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && currentSpeedY <= 0) {
            timeSinceLastFloorContact = 0;
            currentSpeedY = 300;
        }

        xPosition += currentSpeedX * elapsed;
        yPosition += currentSpeedY * elapsed;
    }

    @Override
    public void checkCollision(GameObject other) {

    }

    @Override
    protected void handleCollision(GameObject other) {
        super.handleCollision(other);
        if(other instanceof ImmutableObject) {
            //xPosition = other.xPosition - this.size.getWidth();
        }

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, xPosition, yPosition);
    }
}
