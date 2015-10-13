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

    @Test
    public void testLevelFinishedEmptyList(){
        Level level = new Level(new ArrayList<GameObject>());
        assertTrue(level.levelFinished());
    }

    @Test
    public void testLevelNotFinished(){
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(mock(Enemy.class));
        Level level = new Level(gameObjects);
        assertFalse(level.levelFinished());
    }

    @Test
    public void testLevelNotFinishedMultipleObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

        gameObjects.add(mock(Enemy.class));
        gameObjects.add(mock(Floor.class));
        gameObjects.add(mock(FilledBubble.class));
        gameObjects.add(mock(Enemy.class));
        gameObjects.add(mock(Wall.class));
        gameObjects.add(mock(Wall.class));
        gameObjects.add(mock(Floor.class));
        Level level = new Level(gameObjects);

        assertFalse(level.levelFinished());
    }

    @Test
    public void testLevelFinishedMultipleObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

        gameObjects.add(mock(Floor.class));
        gameObjects.add(mock(Floor.class));
        gameObjects.add(mock(Floor.class));
        gameObjects.add(mock(Floor.class));
        gameObjects.add(mock(Wall.class));
        gameObjects.add(mock(Wall.class));
        gameObjects.add(mock(Floor.class));
        Level level = new Level(gameObjects);

        assertTrue(level.levelFinished());
    }

}
