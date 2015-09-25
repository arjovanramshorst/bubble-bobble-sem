package sem.group15.bubblebobble.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import sem.group15.bubblebobble.core.BubbleBobble;

public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 20 * BubbleBobble.SPRITE_SIZE;
		config.height = 20 * BubbleBobble.SPRITE_SIZE;
		new LwjglApplication(new BubbleBobble(), config);
	}
}
