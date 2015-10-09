package sem.group15.bubblebobble.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.objects.*;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Marketing Lorre on 28-9-2015.
 */
public class LevelTest {
    public Level level;

    @Before
    public void setUp(){
        level = mock(Level.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    public void testLevelFinishedEmptyList(){
        assertTrue(level.levelFinished(new ArrayList<GameObject>()));
    }

    @Test
    public void testLevelNotFinished(){
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(mock(Enemy.class));
        assertFalse(level.levelFinished(gameObjects));
    }

    @Test
    public void testLevelNotFinishedMultipleObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

        gameObjects.add(mock(Enemy.class));
        gameObjects.add(mock(FloorObject.class));
        gameObjects.add(mock(FilledBubbleObject.class));
        gameObjects.add(mock(Enemy.class));
        gameObjects.add(mock(WallObject.class));
        gameObjects.add(mock(WallObject.class));
        gameObjects.add(mock(FloorObject.class));


        assertFalse(level.levelFinished(gameObjects));
    }

    @Test
    public void testLevelFinishedMultipleObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

        gameObjects.add(mock(FloorObject.class));
        gameObjects.add(mock(FloorObject.class));
        gameObjects.add(mock(FloorObject.class));
        gameObjects.add(mock(FloorObject.class));
        gameObjects.add(mock(WallObject.class));
        gameObjects.add(mock(WallObject.class));
        gameObjects.add(mock(FloorObject.class));


        assertTrue(level.levelFinished(gameObjects));
    }

}
