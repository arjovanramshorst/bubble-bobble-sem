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

    @Test
    public void testCheckCollisions() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Enemy enemy = Mockito.mock(Enemy.class);
        Floor floor = Mockito.mock(Floor.class);
        gameObjects.add(enemy);
        gameObjects.add(floor);
        Level level = new Level(gameObjects);

        level.checkCollisions();
        verify(enemy).handleCollision(floor);
        verify(floor).handleCollision(enemy);
    }

    @Test
    public void testRemoveObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Enemy enemy = Mockito.mock(Enemy.class, Mockito.CALLS_REAL_METHODS);
        Floor floor = Mockito.mock(Floor.class);
        gameObjects.add(enemy);
        gameObjects.add(floor);
        Level level = new Level(gameObjects);

        assertEquals(2, level.getObjects().size());
        level.removeObjects();
        assertEquals(2, level.getObjects().size());
        enemy.setRemove(true);
        level.removeObjects();
        assertEquals(1, level.getObjects().size());
    }

    @Test
    public void testUpdate() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Enemy enemy = Mockito.mock(Enemy.class);
        gameObjects.add(enemy);
        Level level = new Level(gameObjects);
        level.update(0.1f);
        verify(enemy).update(0.1f);
    }

    //still get nullpointer for somereason
//    @Test
//    public void testHandleNewObjects() {
//        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
//        Player player = Mockito.mock(Player.class);
//        gameObjects.add(player);
//
//        ArrayList<GameObject> newObjects = new ArrayList<GameObject>();
//        Bubble bubble = Mockito.mock(Bubble.class);
//        newObjects.add(bubble);
//        player.setNewObjects(newObjects);
//
//        Level level = new Level(gameObjects);
//        assertFalse(level.getObjects().contains(bubble));
////        level.handleNewObjects();
//    }

    @Test
    public void testRun() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Level level = new Level(gameObjects);
        Level spyLevel = Mockito.spy(level);
        spyLevel.run(0.1f);
        //update is final soo cant spy
//        verify(spyLevel).update(0.1f);
        verify(spyLevel).checkCollisions();
        verify(spyLevel).handleNewObjects();
        verify(spyLevel).removeObjects();
    }

    @Test
    public void testSetPlayer() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        Level level = new Level(gameObjects);

        Player player = Mockito.mock(Player.class);
        assertFalse(level.getObjects().contains(player));
        assertEquals(null, level.getPlayer());
        level.setPlayer(player);
        assertEquals(player, level.getObjects().get(0));
        assertEquals(player, level.getPlayer());
    }

}
