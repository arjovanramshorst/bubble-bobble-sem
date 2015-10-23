package sem.group15.bubblebobble.core.gameStates;

import sem.group15.bubblebobble.core.GameController;

/**
 * Created by daan on 20-10-15.
 */
public class PauseState implements GameState {

    @Override
    public GameState handleState(GameController controller, float elapsed) {
        controller.getLevelRenderer().renderPause();
        if (controller.checkForStartKey()) {
            return new PlayState();
        }
        return this;
    }
}
