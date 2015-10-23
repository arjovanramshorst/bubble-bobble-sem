package sem.group15.bubblebobble.core.gameStates;

import sem.group15.bubblebobble.core.GameController;

/**
 * Created by daan on 20-10-15.
 */
public class NewState implements GameState {
    @Override
    public GameState handleState(GameController controller, float elapsed) {
        controller.getLevelRenderer().renderNew();
        if (controller.checkForStartKey()) {
            controller.startLevel(controller.getCurrentLevelNumber());
            return new PlayState();
        }
        return this;
    }

}
