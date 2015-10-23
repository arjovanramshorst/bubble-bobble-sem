package sem.group15.bubblebobble.core.objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sem.group15.bubblebobble.core.enemybehaviour.JumpRandom;

/**
 * Created by Matthijs on 10/8/15.
 */
public class SimpleEnemy extends Enemy {

    /**
     * Creates an Enemy with position (X,Y) on the grid.
     *
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public SimpleEnemy(final float xPosition, final float yPosition) {
        super(xPosition, yPosition);
        jumpBehaviour = new JumpRandom();
    }


    /**
     * Sets the horizontal direction of the enemy, and adjusts its horizontal speed accordingly.
     *
     * @param direction the direction in which the enemy is going.
     */
    public void setDirection(final Direction direction) {
        switch (direction) {
            case LEFT:
                this.speedX = -ENEMY_SPEED;
                break;
            case RIGHT:
                this.speedX = ENEMY_SPEED;
                break;
        }
        this.direction = direction;
        jump();
    }

    /**
     * sets the appropriate textures.
     */
    @Override
    public void setTextures() {
        angryLeftTexture = assets.getSimpleAngryEnemyLeft();
        angryRightTexture = assets.getSimpleAngryEnemyRight();
        normalLeftTexture = assets.getSimpleEnemyLeft();
        normalRightTexture = assets.getSimpleEnemyRight();
    }

}
