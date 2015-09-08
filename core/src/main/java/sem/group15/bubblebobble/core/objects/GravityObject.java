package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arjo on 7-9-15.
 */
public abstract class GravityObject extends GameObject {

    protected float timeSinceLastFloorContact;

    protected float currentSpeedX;
    protected float currentSpeedY;

    protected GravityObject(Rectangle location, Texture texture) {
        super(location, texture);
        this.currentSpeedX = 0;
        this.currentSpeedY = 0;
        this.timeSinceLastFloorContact = 0;
    }

    public void update(float elapsed) {
        timeSinceLastFloorContact += elapsed;
        currentSpeedY = Math.max(currentSpeedY - (100 * timeSinceLastFloorContact * timeSinceLastFloorContact), -300);
    }

    protected void handleCollision(GameObject other) {
        if (other instanceof ImmutableObject) {

        }
    }
}
