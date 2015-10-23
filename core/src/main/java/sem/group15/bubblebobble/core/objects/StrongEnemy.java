package sem.group15.bubblebobble.core.objects;

import sem.group15.bubblebobble.core.enemybehaviour.JumpHigh;

/**
 * Created by Matthijs on 10/8/15.
 */
public class StrongEnemy extends Enemy {

    /**
     *
     */
    private float switchDelay;

    /**
     * Creates an StrongEnemy with position (X,Y) on the grid.
     *
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     */
    public StrongEnemy(final float xPosition, final float yPosition) {
        super(xPosition, yPosition);
        jumpBehaviour = new JumpHigh();
    }


    @Override
    public void update(final float elapsed) {
        super.update(elapsed);
        switchDelay -= elapsed;
    }

    /**
     * Sets the horizontal direction of the enemy, and adjusts its horizontal speed accordingly.
     *
     * @param direction the direction in which the enemy is going.
     */
    public void setDirection(final Direction direction) {
        switch (direction) {
            case LEFT:
                this.speedX = -ENEMY_SPEED * 2;
                break;
            case RIGHT:
                this.speedX = ENEMY_SPEED * 2;
                break;
        }
        this.direction = direction;
        switchDelay = 1;
    }

    /**
     * updated the direction of the enemy based on the player's position.
     *
     * @param playerX x coordinate of the player
     */
    public void updatePath(final float playerX) {
        if (playerX > location.x) {
            setDirection(Direction.RIGHT);
        } else {
            setDirection(Direction.LEFT);
        }

        jump();
    }


    /**
     * Handles collisions with other objects.
     * If there is a WallCollision set the attribute wallCollision to true.
     *
     * @param other Object that needs to be checked for collision.
     */
    @Override
    public void handleCollision(final GameObject other) {
        super.handleCollision(other);

        if (other instanceof Player && switchDelay < 0) {
            updatePath(other.location.x);
        }
    }


    /**
     * sets the appropriate textures.
     */
    @Override
    public void setTextures() {
        angryLeftTexture = assets.getStrongEnemyLeft();
        angryRightTexture = assets.getStrongEnemyRight();
        normalLeftTexture = assets.getStrongEnemyLeft();
        normalRightTexture = assets.getStrongEnemyRight();
    }

}
