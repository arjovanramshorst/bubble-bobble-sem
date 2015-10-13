package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by woute on 10/13/2015.
 */
public class Fruit extends Gravity {

    /**
     * Time fruit has been in the game.
     */
    private float aliveTime;
    /**
     * Basic score of a fruit.
     */
    public static final int FRUIT_SCORE = 200;

    /**
     * Create fruit object at given location.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public Fruit(final float xPosition, final float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        aliveTime = 0;
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        aliveTime += elapsed;
        if (aliveTime > 5) {
            remove = true;
        }
    }

    @Override
    public void handleCollision(final GameObject other) {
        super.handleCollision(other);
        if (location.overlaps(other.getBody())) {
            if (other instanceof Player) {
                remove = true;
            }
        }
    }

    @Override
    public void draw(final SpriteBatch batch) {

    }

}
