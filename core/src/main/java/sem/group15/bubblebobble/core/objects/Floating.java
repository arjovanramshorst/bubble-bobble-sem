package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * Abstract class for the different kinds of bubbles
 * Created by TUDelft SID on 7-9-2015.
 */
public abstract class Floating extends GameObject {

    /**
     * Time since floating object has been fired.
     */
    protected float timeFromFired;

    /**
     * Floating Constructor.
     * @param location location of floating object
     */
    protected Floating(final Rectangle location) {
        super(location);
    }

    /**
     * Update timeFromFired variable.
     * @param elapsed time elapsed since last gameloop.
     */
    public void update(final float elapsed) {
        timeFromFired += elapsed;
    }

}
