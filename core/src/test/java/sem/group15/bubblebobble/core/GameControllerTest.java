package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sem.group15.bubblebobble.core.gamestates.GameState;
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
    @Before
    public void setUp() throws Exception {
        controller = Mockito.mock(GameController.class, Mockito.CALLS_REAL_METHODS);
        controller.setPlayer(null);
    }

    @Test
    public void testCheckForStartKey (){
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.ENTER)).thenReturn(true);
        assertTrue(controller.checkForStartKey());
    }

    @Test
    public void testStartLevel (){
        Level level = Mockito.mock(Level.class);
        controller.setCurrentLevelNumber(1);
        LevelRenderer renderer = Mockito.mock(LevelRenderer.class);
        controller.setLevelRenderer(renderer);
        assertEquals(renderer, controller.getLevelRenderer());
        Player player = Mockito.mock(Player.class);
        controller.setPlayer(player);

        controller.startLevel(level);

        assertEquals(1, controller.getCurrentLevelNumber());
        assertEquals(level, controller.getCurrentLevel());
        verify(level).setPlayer(controller.getPlayer());
        verify(player).respawn();
        verify(renderer).setLevel(level);
    }

    @Test(expected = NullPointerException.class)
    public void testGetLevel (){
        controller.getLevel(0);
    }

    @Test
    public void testRun (){
        GameState state = Mockito.mock(GameState.class);
        controller.setGameState(state);
        controller.run(0.1f);
        verify(state).handleState(0.1f);
    }

}