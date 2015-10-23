package sem.group15.bubblebobble.core;

import com.badlogic.gdx.files.FileHandle;
import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.objects.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marketing Lorre on 28-9-2015.
 */
public class LevelParserTest {

    FileHandle file;

    @Before
    public void setUp(){
        file = mock(FileHandle.class);
    }

    @Test
    public void testIOExceptionIsThrownFailingInput(){
        String str = "Floors, 200, 100";
        try {
            when(file.read()).thenReturn(new ByteArrayInputStream(str.getBytes("UTF-8")));
            Level level = LevelParser.parse(file);
            assertTrue(level.getObjects().isEmpty());
            fail();
        }catch (IOException e){
            assertEquals("String: " + str + " is not a valid object!", e.getMessage());
        }

    }

    @Test
    public void parseWall() {
        String wall = "Wall, 100, 100";
        GameObject object;
        try {
            object = LevelParser.getObject(wall);
            assertTrue(object instanceof Wall);
        }
        catch (IOException ioe){
            fail("Parsing failed");
        }
    }

    @Test
    public void parseFloor() {
        String str = "Floor, 100, 100";
        GameObject object;
        try {
            object = LevelParser.getObject(str);
            assertTrue(object instanceof Floor);
        }
        catch (IOException ioe){
            fail("Parsing failed");
        }
    }

    @Test
    public void parsePowerup() {
        String str = "Powerup, 100, 100";
        GameObject object;
        try {
            object = LevelParser.getObject(str);
            assertTrue(object instanceof Powerup);
        }
        catch (IOException ioe){
            fail("Parsing failed");
        }
    }
}
