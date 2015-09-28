package sem.group15.bubblebobble.core;

import com.badlogic.gdx.files.FileHandle;
import org.junit.Before;
import org.junit.Test;

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
    public void testIOExeption(){
        String str = "Floors, 200, 100";
        try {
            when(file.read()).thenReturn(new ByteArrayInputStream(str.getBytes("UTF-8")));
            new LevelParser().parse(file);
        }catch (IOException e){
            assertTrue(true);
        }

    }
}
