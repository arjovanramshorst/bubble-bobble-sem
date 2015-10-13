package sem.group15.bubblebobble.core;

import com.badlogic.gdx.files.FileHandle;
import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.objects.GameObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

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
}
