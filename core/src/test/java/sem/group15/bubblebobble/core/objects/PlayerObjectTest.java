package sem.group15.bubblebobble.core.objects;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.LogicController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by TUDelft SID on 22-9-2015.
 */
public class PlayerObjectTest {


    private PlayerObject player;


    /**
     * Used for setting the speed of the player object.
     */
    final float speed = 100;

    /**
     * Set up the player, the player's location and speed.
     */
    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);
        player = mock(PlayerObject.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doNothing().when(player).playDeadSound();
        Mockito.doNothing().when(player).playJumpSound();
        player.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.currentSpeedX = speed;
    }
    
    /**
     *Tests if the enemy dies after a collision with an enemy when there are no lives left.
     */
    @Test
    public void collisionEnemyNoLives() {
        player.isAlive = true;
        player.lives = 1;
        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.handleCollision(enemy);
        assertFalse(player.isAlive);
        player.update(1 / speed);
        assertEquals(0, player.currentSpeedX, 0.01);
    }

    /**
     * TEsts if the enemy respawns after a collision with an enemy when there are lives left.
     */
    @Test
    public void collisionEnemyMultipleLives() {
        player.lives = 2;
        player.isAlive = true;
        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        player.handleCollision(enemy);
        assertTrue(player.isAlive);
        assertTrue(player.lives == 1);
        verify(player).respawn();

    }
    /**
     * Test if respawned timer is updated accordingly
     */
    @Test
    public void testRespawned() {
        player.respawned = 0;
        player.isAlive = true;
        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
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

        EnemyObject enemy = mock(EnemyObject.class, Mockito.CALLS_REAL_METHODS);
        enemy.location = new Rectangle(BubbleBobble.SPRITE_SIZE - 2, 0, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);

        player.update(0.1f);
        player.handleCollision(enemy);
        assertEquals(player.PLAYER_LIVES - 1, player.lives, 0);
        assertTrue(player.isAlive);

        enemy.location.x = LogicController.PLAYER_XY_SPAWN;
        enemy.location.y = LogicController.PLAYER_XY_SPAWN;
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
        assertNotEquals(LogicController.PLAYER_XY_SPAWN, player.location.x, 0.1f);
        assertNotEquals(LogicController.PLAYER_XY_SPAWN, player.location.y, 0.1f);
        player.respawn();
        assertEquals(LogicController.PLAYER_XY_SPAWN, player.location.x, 0.1f);
        assertEquals(LogicController.PLAYER_XY_SPAWN, player.location.y, 0.1f);

    }

    /**
     * Tests if a collision with a filledBubble is handled accordingly.
     */
    @Test
    public void testHandleCollisionFilledBubble() {
        player.update(0.1f);
        assertTrue(player.score == 0);
        FilledBubbleObject filledBubble = mock(FilledBubbleObject.class, Mockito.CALLS_REAL_METHODS);
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
        BubbleObject bubble = mock(BubbleObject.class, Mockito.CALLS_REAL_METHODS);
        bubble.location = new Rectangle(0, 0, BubbleBobble.SPRITE_SIZE, 1);
        player.update(0.01f);
        player.handleCollision(bubble);
        assertTrue(player.canJump);
    }

    /**
     * Tests if the player can move to the left.
     */
    @Test
    public void testMovementLeft() {
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        player.isAlive = true;
        player.update(0.1f);
        assertTrue(player.currentSpeedX == -100f);
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
        assertTrue(player.currentSpeedX == 100f);
        assertTrue(player.getDirection() == GameObject.Direction.RIGHT);
    }

    /**
     * Tests if the player interacts properly when there is no movement key pressed.
     */
    @Test
    public void noMovement() {
        player.isAlive = true;
        player.update(0.1f);
        assertTrue(player.currentSpeedX == 0);
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
        assertTrue(player.currentSpeedY == 300f);
        verify(player).playJumpSound();
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
        assertTrue(player.currentSpeedY == 300f);
        verify(player).playJumpSound();
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
        verify(player, never()).playJumpSound();
    }



}