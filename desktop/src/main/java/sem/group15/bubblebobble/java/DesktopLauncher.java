package sem.group15.bubblebobble.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Launches the game.
 */

public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 20 * (int) BubbleBobble.SPRITE_SIZE;
		config.height = 20 * (int) BubbleBobble.SPRITE_SIZE;
		new LwjglApplication(new BubbleBobble(), config);
	}
}
