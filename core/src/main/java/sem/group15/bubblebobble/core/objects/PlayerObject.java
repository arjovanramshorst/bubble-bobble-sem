package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by arjo on 7-9-15.
 */
public class PlayerObject extends GravityObject {

    private final float MAX_WALL_OVERLAP = 10f;

    protected boolean isAlive;

    public PlayerObject(float xPosition, float yPosition) {
        super(
                new Rectangle(xPosition,yPosition, BubbleBobble.SPRITE_SIZE,BubbleBobble.SPRITE_SIZE),
                new Texture(Gdx.files.internal("playerSprite.png"))
        );
        isAlive = true;
    }
    public PlayerObject(float xPosition, float yPosition, Texture texture) {
        super(
                new Rectangle(xPosition,yPosition, BubbleBobble.SPRITE_SIZE,BubbleBobble.SPRITE_SIZE),
                texture
        );
        isAlive = true;
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentSpeedX = -100;
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentSpeedX = 100;
        } else {
            currentSpeedX = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && canJump) {
            timeSinceLastFloorContact = 0;
            currentSpeedY = 300;
            canJump=false;
        }

        location.x += currentSpeedX * elapsed;
        location.y += currentSpeedY * elapsed;


    }

    /**
     * If the player collides with an enemyObject, set the attribute isAliv e to false.
     * @param other Object that needs to be checked for collision.
     */
    @Override
    public void handleCollision(GameObject other) {
        super.handleCollision(other);

        if (other instanceof EnemyObject) {
            isAlive = false;
        }

        if (other instanceof WallObject) {
            if(between(overlapLeft(other),0, MAX_WALL_OVERLAP)) {
                setLeft(other.getRight());
            }
            if(between(overlapRight(other), 0, MAX_WALL_OVERLAP)) {
                setRight(other.getLeft());
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, location.x, location.y);
    }


    /**
     * Checks if the player is still alive
     * @return
     */
    public boolean isAlive() {
        return isAlive;
    }

    public int getDirection(){
        if (currentSpeedX>0)
            return 1;
        return -1;

    }
}



