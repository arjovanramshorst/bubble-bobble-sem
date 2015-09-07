package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

/**
 * Created by arjo on 7-9-15.
 */
public class TestObject extends GameObject {

    public TestObject(float xPosition, float yPosition) {
        super(new Rectangle(), new Texture(Gdx.files.internal("ground5.png")), xPosition, yPosition);
    }

    @Override
    public void update(float elapsed) {

    }

    @Override
    public void checkCollision(GameObject other) {

    }

    @Override
    protected void handleCollision(GameObject collided) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, xPosition, yPosition);
    }
}
