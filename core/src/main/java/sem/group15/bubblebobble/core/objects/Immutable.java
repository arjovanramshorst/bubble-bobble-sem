package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arjo on 7-9-15.
 */
public abstract class Immutable extends GameObject {

    /**
     * Constructor Immutable.
     * @param location location of the rectangle
     */
    protected Immutable(Rectangle location) {
        super(location);
        speedX = 0;
        speedY = 0;
    }
}
