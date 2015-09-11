package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

import java.util.Random;

/**
 * Created by TUDelft SID on 8-9-2015.
 */
public class EnemyObject extends GravityObject {

    protected boolean wallCollision;
    private int direction;

    public EnemyObject(float xPosition, float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE), new Texture(Gdx.files.internal("aqua-ball.png")));
        Random rand = new Random();
        direction = rand.nextInt(2);
    }

    public EnemyObject(float xPosition, float yPosition, Texture texture) {
        super(
                new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE),
                texture
        );
        Random rand = new Random();
        direction = rand.nextInt(2);
    }

    @Override
    public void update(float elapsed) {
      //  super.update(elapsed);

        //0 move left, 1 move right
        if (direction == 0) {
            currentSpeedX = -100;
        } else {
            currentSpeedX = 100;
        }

        if (wallCollision) {
            currentSpeedX = -1 * currentSpeedX;
            wallCollision = false;
        }

        location.x += currentSpeedX * elapsed;
        location.y += currentSpeedY * elapsed;
    }

    @Override
    //TODO immutableobject veranderen voor wallobject, zit nu nog niet in branch. Ook in test aanpassen.
    public void handleCollision(GameObject other) {
        super.handleCollision(other);
        //wallobject
        if (other instanceof ImmutableObject) {
            if (location.overlaps(other.getBody())) {
                wallCollision = true;
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }

    public void setDirection(int value) {
        if (value == 1 || value == 0) {
            direction = value;
        }
    }
}