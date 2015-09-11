package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arjo on 7-9-15.
 */
public abstract class GravityObject extends GameObject {
    private final int GRAVITY_SPEED = 100;
    private final int MAX_GRAVITY_SPEED = -300;


    protected float timeSinceLastFloorContact;

    protected float currentSpeedX;
    protected float currentSpeedY;

    protected GravityObject(Rectangle location, Texture texture) {
        super(location, texture);
        this.currentSpeedX = 0;
        this.currentSpeedY = 0;
        this.timeSinceLastFloorContact = 0;
    }

    /**
     * Updates the vertical speed. Just as real gravity, this is based on the time that has passed since last
     * floorcontact. Also, there is a max speed.
     * @param elapsed time elapsed since last gameloop.
     */
    public void update(float elapsed) {
        timeSinceLastFloorContact += elapsed;
        currentSpeedY = Math.max(
                currentSpeedY - (GRAVITY_SPEED * timeSinceLastFloorContact * timeSinceLastFloorContact),
                MAX_GRAVITY_SPEED
        );
    }

    /**
     * Checks if this object collides with another object, and handles that collision if necessary.
     *
     * @param other Object that needs to be checked for collision.
     */
    public void handleCollision(GameObject other) {
        if (other instanceof FloorObject) {
            if (location.overlaps(other.getBody()) && currentSpeedY < 0) {
                location.y = other.getBody().getY() + other.getBody().getHeight();
                currentSpeedY = 0;
            }
        }
        if (other instanceof WallObject) {
            if (location.overlaps(other.getBody())) {
                if(location.x>other.getBody().getX()){
                    location.x = other.getBody().getX() + other.getBody().getWidth();

                }
                else{
                    location.x = other.getBody().getX() - other.getBody().getWidth();

                }
                currentSpeedX = 0;
            }
        }
    }
}
