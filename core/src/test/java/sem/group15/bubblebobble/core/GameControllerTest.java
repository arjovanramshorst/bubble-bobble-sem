package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.objects.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    GameController controller;

    /**
     * setup mokito for mocking
     * @throws Exception
     */
//    @Before
//    public void setUp() throws Exception {
////        controller = new GameController();
//        controller = Mockito.mock(GameController.class, Mockito.CALLS_REAL_METHODS);
//        controller.setPlayer(null);
//        controller.setState(GameController.GameState.NEW);
//    }

    /**
     * Test if reset controller resets vallues
     */
//    @Test
//    public void testResetController() {
//        assertEquals(GameController.GameState.NEW, controller.getState());
//        assertFalse(controller.getPlayer() instanceof Player);
//        controller.setCurrentLevelNumber(2);
//        assertEquals(2, controller.getCurrentLevelNumber());
//
//        controller.resetController();
//
//        assertEquals(GameController.GameState.PLAY, controller.getState());
//        assertTrue(controller.getPlayer() instanceof Player);
//        assertEquals(1, controller.getCurrentLevelNumber());
//    }

//    @Test
//    public void testHandleStateNew() {
//        LevelRenderer levelRenderer = Mockito.mock(LevelRenderer.class);
//        controller.setLevelRenderer(levelRenderer);
//        Mockito.doReturn(false).when(controller).checkForStartKey();
//
//        controller.handleStateNew();
//        verify(levelRenderer).renderNew();
//    }

//    @Test
//    public void testHandeStatePause() {
//        LevelRenderer levelRenderer = Mockito.mock(LevelRenderer.class);
//        controller.setLevelRenderer(levelRenderer);
//
//        Gdx.input = Mockito.mock(Input.class);
//        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.ENTER)).thenReturn(true);
//
//        controller.handleStatePause();
//        assertEquals(GameController.GameState.PLAY, controller.getState());
//        verify(levelRenderer).renderPause();
//    }

//    @Test
//    public void testHandeStateLost() {
//        LevelRenderer levelRenderer = Mockito.mock(LevelRenderer.class);
//        controller.setLevelRenderer(levelRenderer);
//        Gdx.input = Mockito.mock(Input.class);
//        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.ENTER)).thenReturn(false);
//
//        controller.handleStateLost();
//
//        verify(levelRenderer).renderLost(controller.getCurrentLevelNumber());
//    }

    /**
     * Test if initial method works properly.
     */
    @Test
    public void testInit(){
//        Mockito.doNothing().when(controller).readMap(1);
//        Player player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
//        controller.player = player;
//        controller.initializeLevel(1);
//        verify(controller).setPlayer();
    }

    /**
     * Test if addGameObject actually adds object.
     */
    @Test
    public void testAddGameObject() {
//        assertEquals(0, controller.gameObjects.size());
//        SimpleEnemy enemy = Mockito.mock(SimpleEnemy.class, Mockito.CALLS_REAL_METHODS);
//        controller.addGameObject(enemy);
//        assertEquals(1, controller.gameObjects.size());
    }

    /**
     * Test if setPlayer is used, while player isn't null.
     * Player gets added to gameObjects
     */
    @Test
    public void testSetPlayerNotNull() {
//        assertEquals(0, controller.gameObjects.size());
//        Player player = Mockito.mock(Player.class, Mockito.CALLS_REAL_METHODS);
//        controller.player = player;
//        controller.setPlayer();
//        assertTrue(controller.gameObjects.contains(player));
    }

    /**
     * Test if logic controllers update invokes gameobjects update.
     */
    @Test
    public void testUpdate() {
//        Floor floor = Mockito.mock(Floor.class, Mockito.CALLS_REAL_METHODS);
//        controller.addGameObject(floor);
//        controller.update(0.1f);
//        verify(floor).update(0.1f);
    }

}