package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.factories.EnemyFactory;
import sem.group15.bubblebobble.core.factories.SimpleEnemyFactory;
import sem.group15.bubblebobble.core.factories.StrongEnemyFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class FilledBubbleTest {

    private FilledBubble fbubble;

    @Before
    public void setUp() {
        Enemy enemey = Mockito.mock(Enemy.class);
        fbubble = new FilledBubble(0, 0, enemey);
    }

    /**
     * Test constructor with simple enemy.
     */
    @Test
    public void testConstructorSimpleEnemy() {
        SimpleEnemy simpleEnemy = Mockito.mock(SimpleEnemy.class);
        fbubble = new FilledBubble(0,0, simpleEnemy);
        assertTrue(fbubble.getFactory() instanceof SimpleEnemyFactory);
    }

    /**
     * Test constructor with simple enemy.
     */
    @Test
    public void testConstructorStrongEnemy() {
        StrongEnemy strongEnemy = Mockito.mock(StrongEnemy.class);
        fbubble = new FilledBubble(0,0, strongEnemy);
        assertTrue(fbubble.getFactory() instanceof StrongEnemyFactory);
    }

    /**
     *  test update function
     */
    @Test
    public void testUpdate() {
        assertEquals(0f, fbubble.location.y, 0.1f);
        fbubble.update(1f);
        assertEquals(fbubble.speedY, fbubble.location.y, 0.01f);
    }

    @Test
    public void testUpdateTimeFromFired(){
        EnemyFactory factory = Mockito.mock(EnemyFactory.class);
        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.doNothing().when(enemy).setState(Enemy.State.ANGRY);
//        Mockito.doReturn(enemy).when(factory).createObject(0f, 0f);

        fbubble.setFactory(factory);
        assertEquals(0, fbubble.timeFromFired, 0.1f);
        assertFalse(fbubble.remove());
        fbubble.update(0.1f);
        assertEquals(0.1f, fbubble.timeFromFired, 0.1f);
        assertFalse(fbubble.remove());
        fbubble.update(5f);
        assertEquals(5.1f, fbubble.timeFromFired, 0.1f);
        assertTrue(fbubble.remove());
    }

    /**
     * test handle collision with immutable object
     */
    @Test
    public void testHandleCollisionImmutable(){
        assertEquals(50, fbubble.speedY, 0.01f);
        Immutable obj = Mockito.mock(Immutable.class, Mockito.CALLS_REAL_METHODS);
        obj.location = new Rectangle(10,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.handleCollision(obj);
        assertTrue(fbubble.speedY == 0);
    }

    /**
     * test handle collision with player quick to spawn a cherry
     */
    @Test
    public void testHandleCollisionPlayerFast(){
        fbubble.remove = false;
        Player obj = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        obj.location = new Rectangle(10,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.handleCollision(obj);
        assertTrue(fbubble.remove);
    }

    /**
     * test handle collision with player quick to spawn a banana
     */
    @Test
    public void testHandleCollisionPlayerSlow(){
        fbubble.remove = false;
        fbubble.timeFromFired = 3;
        Player obj = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        obj.location = new Rectangle(10,0,BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE);
        fbubble.handleCollision(obj);
        assertTrue(fbubble.remove);
    }

    /**
     * Test the draw function
     */
    @Test
    public void testDraw() {
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        Texture texture = null;
        Mockito.doNothing().when(batch).draw(texture, 0, 0);
        fbubble.draw(batch);
        verify(batch).draw(texture, 0, 0);
    }
}