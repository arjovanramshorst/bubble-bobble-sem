package sem.group15.bubblebobble.core.objects;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.Logger;
import sem.group15.bubblebobble.core.GameController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by TUDelft SID on 22015.
 */
public class PlayerObjectTest {


    private Player player;


    /**
     * Used for setting the initial speed of the player object.
     */
    private static final float INIT_SPEED = 100;

    /**
     * Set up the player, the player's location and initial speed.
     */

    @Before
    public void setUp() {
        Gdx.input = mock(Input.class);
        player = new Player(0,0);
    }

    /**
     *Tests if the enemy dies after a collision with an enemy when there are no lives left.
     */
    @Test
    public void collisionEnemyNoLives() {
        player.isAlive = true;
        player.lives = 1;
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.handleCollision(enemy);

        assertFalse(player.isAlive);
        player.update(1 / INIT_SPEED);
        assertEquals(0, player.speedX, 0.01);
    }

    /**
     * TEsts if the enemy respawns after a collision with an enemy when there are lives left.
     */
    @Test
    public void collisionEnemyMultipleLives() {
        player.lives = 2;
        player.isAlive = true;
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.handleCollision(enemy);
        assertTrue(player.isAlive);
        assertTrue(player.lives == 1);
//        verify(player).respawn();

    }
    /**
     * Test if respawned timer is updated accordingly
     */
    @Test
    public void testRespawned() {
        player.respawned = 0;
        player.isAlive = true;
        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.handleCollision(enemy);
        assertEquals(player.INVULNERABLE_TIME, player.respawned, 0.01f);

        player.update(0.5f);
        assertEquals(player.INVULNERABLE_TIME - 0.5f, player.respawned, 0.01f);
    }

    /**
     * Tests if the collision with an enemy is handled accordingly.
     */
    @Test
    public void testHandleCollisionEnemy() {
        player.update(0.1f);
        player.isAlive = true;
        player.lives = player.PLAYER_LIVES;
        player.respawned = 0f;

        Enemy enemy = mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.update(0.1f);
        player.handleCollision(enemy);
        assertEquals(player.PLAYER_LIVES - 1, player.lives, 0);
        assertTrue(player.isAlive);

        enemy.location.x = GameController.PLAYER_XY_SPAWN;
        enemy.location.y = GameController.PLAYER_XY_SPAWN;
        player.respawned = 0;
        player.handleCollision(enemy);
        player.respawned = 0;
        player.handleCollision(enemy);
        assertEquals(0, player.lives, 0);
        assertFalse(player.isAlive);
    }

    /**
     * test if respawn works correctly
     */
    @Test
    public void testRespawn() {
        assertNotEquals(GameController.PLAYER_XY_SPAWN, player.location.x, 0.1f);
        assertNotEquals(GameController.PLAYER_XY_SPAWN, player.location.y, 0.1f);
        player.respawn();
        assertEquals(GameController.PLAYER_XY_SPAWN, player.location.x, 0.1f);
        assertEquals(GameController.PLAYER_XY_SPAWN, player.location.y, 0.1f);

    }

    /**
     * Tests if a collision with a filledBubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionFilledBubble() {
        player.update(0.1f);
        assertTrue(player.score == 0);
        FilledBubble filledBubble = mock(FilledBubble.class, Mockito.CALLS_REAL_METHODS);
        filledBubble.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.update(0.1f);
        player.handleCollision(filledBubble);
        assertTrue(player.score == 100);

    }

    /**
     * Tests if a collision ith a bubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionBubble() {
        player.canJump = false;
        Bubble bubble = mock(Bubble.class, Mockito.CALLS_REAL_METHODS);
        bubble.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, 1);
        player.update(0.01f);
        player.handleCollision(bubble);
        assertTrue(player.canJump);
    }

    /**
     * test collision with wall on right
     */
    @Test
    public void testHandleCollisionWall(){
        player.location.x = 5;
        player.location.y = 32;
        Wall wall = mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(32,32,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        assertNotEquals(wall.getLeft(), player.getRight(), 0.1f);
        player.handleCollision(wall);
        assertEquals(wall.getLeft(), player.getRight(), 0.1f);
    }
    /**
     * test collision with wall on left
     */
    @Test
    public void testHandleCollisionWallLeft(){
        player.location.x = 60;
        player.location.y = 32;
        Wall wall = mock(Wall.class, Mockito.CALLS_REAL_METHODS);
        wall.location = new Rectangle(32,32,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        assertNotEquals(wall.getRight(), player.getLeft(), 0.1f);
        player.handleCollision(wall);
        assertEquals(wall.getRight(), player.getLeft(), 0.1f);
    }
    /**
     * Tests if the player can move to the left.
     */
    @Test
    public void testMovementLeft() {
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        player.isAlive = true;
        player.update(0.1f);
        assertTrue(player.speedX == -100f);
        assertTrue(player.getDirection() == GameObject.Direction.LEFT);
    }
    /**
     * Tests if the player can move to the right.
     */
    @Test
    public void testMovementRight() {
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        player.isAlive = true;
        player.update(0.1f);
        assertTrue(player.speedX == 100f);
        assertTrue(player.getDirection() == GameObject.Direction.RIGHT);
    }

    /**
     * test if the player can jump.
     */
    @Test
    public void testJump1() {
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        player.canJump = true;
        player.isAlive = true;
        player.update(0.1f);
        assertFalse(player.canJump);
        assertTrue(player.speedY == 300f);
    }
    /**
     * test if the player can jump when standing on a bubble.
     */
    @Test
    public void testJump2() {
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        player.canJump = false;
        player.floating = true;
        player.isAlive = true;
        player.update(0.1f);
        assertFalse(player.canJump);
        assertTrue(player.speedY == 300f);
    }
    /**
     * test if the player cannot jump when it is already jumping.
     */
    @Test
    public void testJump3() {
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        player.canJump = false;
        player.floating = false;
        player.isAlive = true;
        player.update(0.1f);
        assertFalse(player.canJump);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDrawRight() {
        player.setDirection(GameObject.Direction.RIGHT);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        player.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDrawLeft() {
        player.setDirection(GameObject.Direction.LEFT);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        player.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDrawDead() {
        player.isAlive = false;
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        player.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }

    /**
     * Test if collision with fruit is handled correctly.
     */
    @Test
    public void testHandlecollisionFruit() {
        Banana fruit = new Banana(30, 0);
        fruit.update(0.6f);
        assertTrue(fruit.getAliveTime() > 0.5);
        fruit.location.y = 0;
        assertEquals(0, player.score, 0.1f);
        player.handleCollision(fruit);

        assertEquals(Fruit.FRUIT_SCORE * fruit.multiplier, player.score, 0.1f);
    }

    /**
     * Test if collision with powerup works
     */
    @Test
    public void testHandleCollisionPowerup() {
        Powerup powerup = new Powerup(30, 0);
        assertEquals(0, player.xSpeedPowerup, 0.1f);
        assertEquals(0, player.powerUpTime, 0.1f);
        player.handleCollision(powerup);
        assertEquals(powerup.getSpeedBoost(), player.xSpeedPowerup, 0.1f);
        assertEquals(powerup.getActiveTime(), player.powerUpTime, 0.1f);
    }

    /**
     * Test if firebubble adds bubble to newObjects
     */
    @Test
    public void testFireBubbleRight() {
        assertEquals(0, player.newObjects.size());
        player.setDirection(GameObject.Direction.RIGHT);
        player.fireBubble();
        assertEquals(1, player.newObjects.size());
        assertTrue(player.newObjects.get(0) instanceof Bubble);
    }

    /**
     * Test if firebubble adds bubble to newObjects
     */
    @Test
    public void testFireBubbleLeft() {
        assertEquals(0, player.newObjects.size());
        player.setDirection(GameObject.Direction.LEFT);
        player.fireBubble();
        assertEquals(1, player.newObjects.size());
        assertTrue(player.newObjects.get(0) instanceof Bubble);
    }

}