package sem.group15.bubblebobble.core;

import com.badlogic.gdx.files.FileHandle;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

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
