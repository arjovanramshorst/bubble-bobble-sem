package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by woute on 10/13/2015.
 */
public abstract class Fruit extends Gravity {

    /**
     * Time fruit has been in the game.
     */
    private float aliveTime;
    /**
     * Basic score of a fruit.
     */
    public static final int FRUIT_SCORE = 200;
    /**
     * Multiplier for the fruit score.
     */
    protected float multiplier;

    /**
     * Abstract fruit object.
     * @param location Rectangle with the location and size.
     */
    public Fruit(final Rectangle location) {
        super(location);
        aliveTime = 0;
        speedY = 100;
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        location.y += 2 * speedY * elapsed;
        aliveTime += elapsed;
        if (aliveTime > 5) {
            remove = true;
        }
    }

    @Override
    public void handleCollision(final GameObject other) {
        super.handleCollision(other);
        if (location.overlaps(other.getBody())) {
            if (other instanceof Player && aliveTime > 0.5) {
                remove = true;
            }
        }
    }

    /**
     * Get time bubble is alive.
     * @return aliveTime
     */
    public final float getAliveTime() {
        return aliveTime;
    }

}
