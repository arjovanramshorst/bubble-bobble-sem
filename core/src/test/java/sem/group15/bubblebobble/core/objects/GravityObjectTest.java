package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.LogicController;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class GravityObjectTest {

    @Before
    public void setUp() {
        Gdx.app = mock(Application.class);
        Gdx.input = mock(Input.class);

    }

    @Test
    public void testHandleCollision() throws Exception {
        GravityObject player = new PlayerObject(0, BubbleBobble.SPRITE_SIZE - 2, null);
        FloorObject floor = new FloorObject(0, 0 , null);
        player.update(0.1f);
        player.handleCollision(floor);
        assertEquals((float) BubbleBobble.SPRITE_SIZE, player.getBody().y, 0.01);
    }

    @Test
    public void testHandleCollisionNoCollision() throws Exception {
        GravityObject player = new PlayerObject(0, BubbleBobble.SPRITE_SIZE + 2, null);
        FloorObject floor = new FloorObject(0, 0, null);
        player.handleCollision(floor);
        assertEquals((float) BubbleBobble.SPRITE_SIZE + 2, player.getBody().y, 0.01);
    }

}