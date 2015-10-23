package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by Wouter on 10/16/2015.
 */
public class Cherry extends Fruit {

    /**
     * Create a Cherry object at given location.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public Cherry(final float xPosition, final float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        multiplier = 2;
    }

    @Override
    public final void draw(final SpriteBatch batch) {
        batch.draw(assets.getCherry(), getLeft(), getBottom());
    }
}
