package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.Logger;

/**
 * Created by arjo on 7-9-15.
 */
public class PlayerObject extends GravityObject {

    private static final Logger logger = Logger.getLogger(PlayerObject.class.getName());
    private Sound deadSound, jumpSound;

    private final float MAX_WALL_OVERLAP = 10f;

    public int score;
    protected boolean isAlive;

    private boolean fired;

    private Direction direction;

    private Texture textureLeft, textureRight,textureDead;

    public PlayerObject(float xPosition, float yPosition) {
        super(
                new Rectangle(xPosition,yPosition, BubbleBobble.SPRITE_SIZE,BubbleBobble.SPRITE_SIZE),
                null
        );
        textureLeft = new Texture(Gdx.files.internal("playerSprite.png"));
        textureRight = new Texture(Gdx.files.internal("playerSpriteRight.png"));
        textureDead = new Texture(Gdx.files.internal("playerDead.png"));
        deadSound= Gdx.audio.newSound(Gdx.files.internal("Player Death.wav"));
        jumpSound= Gdx.audio.newSound(Gdx.files.internal("Jump.wav"));

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
        if(isAlive) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                currentSpeedX = -100;
                direction = Direction.LEFT;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                currentSpeedX = 100;
                direction = Direction.RIGHT;
            } else {
                currentSpeedX = 0;
            }
            if (fired) {
                fired = Gdx.input.isKeyPressed(Input.Keys.SPACE);
            } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                fireBubble();
                fired = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && canJump) {
                timeSinceLastFloorContact = 0;
                currentSpeedY = 300;
                canJump = false;
                jumpSound.play(1.0f);
            }
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
        if(location.overlaps(other.getBody())){

            if (other instanceof EnemyObject&&!isAlive) {
                logger.log("Player touched EnemyObject.");
                isAlive = false;
                deadSound.play(1.0f);
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
            if (other instanceof  FilledBubbleObject)
                score+=100;
        }
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        if(isAlive) {
            switch (direction) {
                case LEFT:
                    spriteBatch.draw(textureLeft, getLeft(), getBottom());
                    break;
                case RIGHT:
                    spriteBatch.draw(textureRight, getLeft(), getBottom());
                    break;
            }
        }
        else
            spriteBatch.draw(textureDead, getLeft(), getBottom());

    }
}



