package sem.group15.bubblebobble.core.objects;

import sem.group15.bubblebobble.core.BubbleBobble;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The enemy objects
 * Created by TUDelft SID on 8-9-2015.
 */
public abstract class Enemy extends Gravity {

    /**
     * Maximum overlap with a wall.
     */
    protected static final float MAX_WALL_OVERLAP = 10f;
    /**
     * Basic speed of an enemy.
     */
    public static final int ENEMY_SPEED = 100;
    /**
     * Multiplier when an enemy is angry.
     */
    protected static final float ANGRY_MULTIPLIER = 1.5f;
    /**
     * Amount of time an enemy stays angry.
     */
    protected static final float ANGRY_TIME = 10f;
    protected static  Texture normalLeftTexture;
    protected static  Texture normalRightTexture;
    protected static  Texture angryLeftTexture;
    protected static  Texture angryRightTexture;

    /**
     * The Direction the enemy is moving.
     */
    public Direction direction;
    public State state;
    protected float timeAngry;

    /**
     * Creates an Enemy with position (X,Y) on the grid.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public Enemy(final float xPosition, final float yPosition) {
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        setTextures();
    }

    /**
     * Sets the state of this object.
     * @param state declares if angry or not
     */
    public void setState(State state) {
        this.state = state;

    }

    /**
     * Update the location of the enemy.
     * @param elapsed time elapsed since last gameloop.
     */
    @Override
    public void update(final float elapsed) {
        super.update(elapsed);
        float multiplier = 1;
        if (state == State.ANGRY) {
            multiplier = ANGRY_MULTIPLIER;
        }
        location.x += speedX * elapsed * multiplier;
        location.y += speedY * elapsed * multiplier;
        timeAngry += elapsed;
        if (timeAngry > ANGRY_TIME){
            setState(State.NORMAL);
            timeAngry = 0;
        }



    }

    /**
     * Draws the sprite at the correct location.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (state == State.NORMAL) {
            switch (direction) {
                case LEFT:
                    spriteBatch.draw(normalLeftTexture, getLeft(), getBottom());
                    break;
                case RIGHT:
                    spriteBatch.draw(normalRightTexture, getLeft(), getBottom());
                    break;
            }
        }
        else if (state == State.ANGRY){
            switch (direction) {
                case LEFT:
                    spriteBatch.draw(angryLeftTexture, getLeft(), getBottom());
                    break;
                case RIGHT:
                    spriteBatch.draw(angryRightTexture, getLeft(), getBottom());
                    break;
            }
        }
    }


    /**
     * Sets the horizontal direction of the enemy.
     * And Adjusts its horizontal speed accordingly.
     * @param direction the direction in which the enemy is going.
     */
    public abstract void setDirection(Direction direction);

    /**
     * Sets the required textures normal left/right and angry left/right
     */
    public abstract void setTextures();

    public enum State {
        NORMAL,
        ANGRY
    }
}
