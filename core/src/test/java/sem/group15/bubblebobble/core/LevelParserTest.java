package sem.group15.bubblebobble.core;

import com.badlogic.gdx.files.FileHandle;
import org.junit.Before;
import org.junit.Test;
import sem.group15.bubblebobble.core.objects.GameObject;

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
    public void testEmptyParse(){
        String str = "Floors, 200, 100";
        try {
            when(file.read()).thenReturn(new ByteArrayInputStream(str.getBytes("UTF-8")));
            List<GameObject> gameObjectList = new LevelParser().parse(file);
            assertTrue(gameObjectList.isEmpty());
        }catch (IOException e){
            assertTrue(false);
        }

    }
}
