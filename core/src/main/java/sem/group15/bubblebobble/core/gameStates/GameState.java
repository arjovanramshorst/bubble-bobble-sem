package sem.group15.bubblebobble.core.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import sem.group15.bubblebobble.core.GameController;
import sem.group15.bubblebobble.core.LevelRenderer;

/**
 * Created by daan on 20-10-15.
 */
public interface GameState {

    GameState handleState(GameController controller, float elapsed);

}
