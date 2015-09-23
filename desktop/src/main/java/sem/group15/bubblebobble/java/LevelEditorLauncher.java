package sem.group15.bubblebobble.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.editor.LevelEditor;

/**
 * Created by arjo on 22-9-15.
 */
public class LevelEditorLauncher {
    public static void main (String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 20*BubbleBobble.SPRITE_SIZE;
        config.height = 20*BubbleBobble.SPRITE_SIZE;
        config.resizable = false;
        new LwjglApplication(new LevelEditor(), config);
    }
}
