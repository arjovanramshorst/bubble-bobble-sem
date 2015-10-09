package sem.group15.bubblebobble.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.objects.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogicControllerTest {

    LogicController controller;

    /**
     * setup mokito for mocking
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        controller = Mockito.mock(LogicController.class, Mockito.CALLS_REAL_METHODS);
        controller.gameObjects = new ArrayList<GameObject>();
    }

    /**
     * Test if initial method works properly.
     */
    @Test
    public void testInit(){
        Mockito.doNothing().when(controller).readMap(1);
        Player player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        controller.player = player;
        controller.init(1);
        verify(controller).setPlayer();
    }
    
    /**
     * Test if addGameObject actually adds object.
     */
    @Test
    public void testAddGameObject() {
        assertEquals(0, controller.gameObjects.size());
        SimpleEnemy enemy = Mockito.mock(SimpleEnemy.class, Mockito.CALLS_REAL_METHODS);
        controller.addGameObject(enemy);
        assertEquals(1, controller.gameObjects.size());
    }

    /**
     * Test if setPlayer is used, while player isn't null.
     * Player gets added to gameObjects
     */
    @Test
    public void testSetPlayerNotNull() {
        assertEquals(0, controller.gameObjects.size());
        Player player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
        controller.player = player;
        controller.setPlayer();
        assertTrue(controller.gameObjects.contains(player));
    }

    /**
     * Test if logic controllers update invokes gameobjects update.
     */
    @Test
    public void testUpdate() {
        Floor floor = Mockito.mock(Floor.class, Mockito.CALLS_REAL_METHODS);
        controller.addGameObject(floor);
        controller.update(0.1f);
        verify(floor).update(0.1f);
    }

}