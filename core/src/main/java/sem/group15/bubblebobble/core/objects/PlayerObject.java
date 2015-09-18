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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int score;
    protected boolean isAlive;

    private boolean fired;

    private Direction direction;

    private Texture textureLeft, textureRight;

    public PlayerObject(float xPosition, float yPosition) {
        super(
                new Rectangle(xPosition,yPosition, BubbleBobble.SPRITE_SIZE,BubbleBobble.SPRITE_SIZE),
                null
        );
        textureLeft = new Texture(Gdx.files.internal("playerSprite.png"));
        textureRight = new Texture(Gdx.files.internal("playerSpriteRight.png"));
        isAlive = true;
        fired = false;
        direction = Direction.RIGHT;

        score = 0;
    }
    public PlayerObject(float xPosition, float yPosition, Texture texture) {
        super(
                new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE),
                texture
        );
        isAlive = true;
        score = 0;
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentSpeedX = -100;
            direction = Direction.LEFT;
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentSpeedX = 100;
            direction = Direction.RIGHT;
        } else {
            currentSpeedX = 0;
        }
        if(fired) {
            fired = Gdx.input.isKeyPressed(Input.Keys.SPACE);
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireBubble();
            fired = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && canJump) {
            timeSinceLastFloorContact = 0;
            currentSpeedY = 300;
            canJump=false;
        }

        location.x += currentSpeedX * elapsed;
        location.y += currentSpeedY * elapsed;
    }

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

            if (other instanceof EnemyObject) {
                isAlive = false;
            }

            if (other instanceof WallObject) {
                if (between(overlapLeft(other), 0, MAX_WALL_OVERLAP)) {
                    setLeft(other.getRight());
                }
                if (between(overlapRight(other), 0, MAX_WALL_OVERLAP)) {
                    setRight(other.getLeft());
                }
            }
            if (other instanceof FilledBubbleObject) {
                score += 10;
            }
        }
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        switch(direction) {
            case LEFT:
                spriteBatch.draw(textureLeft, getLeft(),getBottom());
                break;
            case RIGHT:
                spriteBatch.draw(textureRight, getLeft(), getBottom());
                break;
        }
    }
}



