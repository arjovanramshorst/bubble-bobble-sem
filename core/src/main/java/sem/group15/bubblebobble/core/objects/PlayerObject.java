package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.LogicController;

/**
 * Created by arjo on 7-9-15.
 */
public class PlayerObject extends GravityObject {

    public static final int PLAYER_LIVES = 3;
    public static final float INVULNERABLE_TIME = 5f;
    private final float MAX_WALL_OVERLAP = 10f;
    public int score, lives;
    protected boolean isAlive;
    private boolean fired;
    protected boolean floating;
    private Direction direction;

    public float respawned;

    /**
     * creates player object with a position
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public PlayerObject(float xPosition, float yPosition) {
        super(
                new Rectangle(xPosition,yPosition, BubbleBobble.SPRITE_SIZE,BubbleBobble.SPRITE_SIZE)
        );
        isAlive = true;
        fired = false;
        direction = Direction.RIGHT;
        score = 0;
        lives = PLAYER_LIVES;
        respawned = 0;
    }
    
     /**
     * updates the player parameters.
     * @param elapsed time elapsed since last gameloop.
     */

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        if (isAlive) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                speedX = -100;
                direction = Direction.LEFT;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                speedX = 100;
                direction = Direction.RIGHT;
            } else {
                speedX = 0;
            }
            if (fired) {
                fired = Gdx.input.isKeyPressed(Input.Keys.SPACE);
            } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                fireBubble();
                fired = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && canJump || floating) {
                timeSinceLastFloorContact = 0;
                speedY = 300;
                canJump = false;
                playJumpSound();
            }
            if (respawned > 0) {
                respawned = respawned - elapsed;
            }

        } else {
            handleDeath();
        }
        location.x += speedX * elapsed;
        location.y += speedY * elapsed;

    }

    /**
     * This method handles what happens after the player dies.
     * In the future it might include the logic for a death animation.
     */
    private void handleDeath() {
        speedX = 0;
    }

    /**
     * method that is called when the player fires a bubble.
     */
    private void fireBubble() {
        BubbleObject bubble = new BubbleObject(0, getBottom(), direction);
        switch (direction) {
            case LEFT:
                bubble.setRight(getLeft());
                break;
            case RIGHT:
                bubble.setLeft(getRight());
                break;
        }
        newObjects.add(bubble);
    }

    /**
     * If the player collides with an enemyObject, set the attribute isAliv e to false.
     * @param other Object that needs to be checked for collision.
     */
    @Override
    public void handleCollision(GameObject other) {
        super.handleCollision(other);

        if (location.overlaps(other.getBody())) {

            if (other instanceof Enemy && isAlive && respawned <= 0f) {
                logger.log("Player touched Enemy.");
                lives--;
                respawned = INVULNERABLE_TIME;
                playDeadSound();
                //set alive false if ran out of lives.
                if (lives == 0) {
                    isAlive = false;
                }
                else {
                    respawn();
                }
            }

            if (other instanceof WallObject) {
                if (between(overlapLeft(other), 0, MAX_WALL_OVERLAP)) {
                    setLeft(other.getRight());
                    logger.log("Player touched wall on left.");
                }
                if (between(overlapRight(other), 0, MAX_WALL_OVERLAP)) {
                    setRight(other.getLeft());
                    logger.log("Player touched wall on right.");
                }
            }
            if (other instanceof  FilledBubbleObject) {
                score += 100;
                logger.log("Player touched filled bubble");
            }
            if (other instanceof BubbleObject) {
                if (between(other.overlapTop(this), 0, 5)) {
                    canJump = true;
                    logger.log("Player touched bubble");
                }
            }
        }
    }

    /**
     * respawn player at starting location.
     */
    public void respawn() {
        location.x = LogicController.PLAYER_XY_SPAWN;
        location.y = LogicController.PLAYER_XY_SPAWN;
    }

    /**
     * draws the player, checks for flags to select the right texture.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (isAlive) {
            switch (direction) {
                case LEFT:
                    spriteBatch.draw(assets.playerLeft, getLeft(), getBottom());
                    break;
                case RIGHT:
                    spriteBatch.draw(assets.playerRight, getLeft(), getBottom());
                    break;
            }
        }
        else {
            spriteBatch.draw(assets.playerDead, getLeft(), getBottom());
        }

    }

    /**
     * Plays the deadSound when a player dies.
     */
    public void playDeadSound() {
        assets.playerDeathSound.play(1.0f);
    }
    /**
     * Plays the jumpSound when the player jumps
     */
    public void playJumpSound() {
        assets.playerJumpSound.play(1.0f);
    }
    public boolean isAlive() {
        return isAlive;
    }

    public Direction getDirection() {
        return direction;
    }
}



