package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by daan on 14-10-15.
 */
public class Powerup extends GameObject {

    private float aliveTime;
    private float activeTime;
    private static final float SPEED_BOOST = 2;

    public Powerup(final float xPosition, final float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        aliveTime = 0;
        activeTime = 5;
    }

    @Override
    public final void update(final float elapsed) {
        aliveTime += elapsed;
        if (aliveTime > 10) {
            remove = true;
        }
    }

    @Override
    public final void handleCollision(final GameObject other) {
        if (location.overlaps(other.getBody())) {
            if (other instanceof Player) {
                remove = true;
            } else if (other instanceof Immutable) {
                remove = true;
            }
        }
    }

    @Override
    public final void draw(final SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.powerup, getLeft(), getBottom());
    }

    /**
     * Getter for activeTime.
     * @return activeTime, a float which describes the time the powerup will be active.
     */
    public final float getActiveTime() {
        return activeTime;
    }

    /**
     * Getter for activeTime.
     * @return activeTime, a float which describes the boost the powerup gives.
     */
    public final float getSpeedBoost() {
        return SPEED_BOOST;
    }

    /**
     * Getter for aliveTime
     * @return aliveTime, a float which represents the time the powerup is alive.
     */
    public final float getAliveTime() {
        return aliveTime;
    }
}