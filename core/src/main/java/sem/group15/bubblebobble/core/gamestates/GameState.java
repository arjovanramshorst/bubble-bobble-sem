package sem.group15.bubblebobble.core.gamestates;

import sem.group15.bubblebobble.core.GameController;

/**
 * Created by daan on 20-10-15.
 */
public interface GameState {

    GameState handleState(GameController controller, float elapsed);

}
