package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arjo on 7-9-15.
 */
public abstract class Gravity extends GameObject {

    protected float timeSinceLastFloorContact;
    protected boolean canJump;


    protected Gravity(Rectangle location) {
        super(location);
        this.speedX = 0;
        this.speedY = 0;
        this.timeSinceLastFloorContact = 0;
        canJump = false;
    }

    /**
     * Updates the vertical speed.
     * Just as real gravity,
     * this is based on the time that has passed since last floorcontact.
     * Also, there is a max speed.
     * @param elapsed time elapsed since last gameloop.
     */
    public void update(float elapsed) {
        timeSinceLastFloorContact += elapsed;
        int gravitySpeed = 100;
        int maxGravitySpeed = -300;
        speedY = Math.max(speedY - (gravitySpeed * timeSinceLastFloorContact * timeSinceLastFloorContact),
                maxGravitySpeed
        );

        if (getTop() <= 0) {
            setBottom(Gdx.graphics.getHeight());
        }
    }

    /**
     * Checks if this object collides with another object,
     * and handles that collision if necessary.
     *
     * @param other Object that needs to be checked for collision.
     */
    public void handleCollision(GameObject other) {
        if (other instanceof Floor) {
            float maxDiffLanding = 10f;
            if (between(overlapBottom(other), 0, maxDiffLanding) && speedY < 0) {
                setBottom(other.getTop());
                speedY = 0;
                timeSinceLastFloorContact = 0;
                canJump = true;
            }
        }
    }
}
