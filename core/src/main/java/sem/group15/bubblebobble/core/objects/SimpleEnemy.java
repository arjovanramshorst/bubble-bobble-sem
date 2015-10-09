package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Matthijs on 10/8/15.
 */
public class SimpleEnemy extends Enemy{


    /**
     * Creates an Enemy with position (X,Y) on the grid.
     *
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public SimpleEnemy(float xPosition, float yPosition) {
        super(xPosition, yPosition);
    }


    /**
     *  Handles collisions with other objects. If there is a WallCollision set the attribute wallCollision to true.
     * @param other Object that needs to be checked for collision.
    */
    @Override
    public void handleCollision(GameObject other) {
        super.handleCollision(other);

        if (! remove && location.overlaps(other.getBody())) {

            if (other instanceof WallObject) {
                if (between(overlapLeft(other), 0, MAX_WALL_OVERLAP)) {
                    setLeft(other.getRight());
                    setDirection(Direction.RIGHT);
                }
                if (between(overlapRight(other), 0, MAX_WALL_OVERLAP)) {
                    setRight(other.getLeft());
                    setDirection(Direction.LEFT);
                }
            }
            if (other instanceof BubbleObject && ! other.remove()) {
                remove = true;
            }
        }
    }
    /**
     * Draws the sprite at the correct location.
     * @param spriteBatch SpriteBatch that the sprites need to be added to.
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        switch (direction) {
            case LEFT:
                spriteBatch.draw(assets.simpleEnemyLeft, getLeft(), getBottom());
                break;
            case RIGHT:
                spriteBatch.draw(assets.simpleEnemyRight, getLeft(), getBottom());
                break;
        }
    }

    /**
     * Sets the horizontal direction of the enemy, and adjusts its horizontal speed accordingly.
     * @param direction the direction in which the enemy is going.
     */
    public void setDirection(Direction direction) {
        switch(direction) {
            case LEFT:
                this.speedX = -ENEMY_SPEED;
                break;
            case RIGHT:
                this.speedX = ENEMY_SPEED;
                break;
        }
        this.direction = direction;
    }

}
