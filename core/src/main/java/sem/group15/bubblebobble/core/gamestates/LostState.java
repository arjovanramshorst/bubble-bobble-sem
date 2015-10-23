package sem.group15.bubblebobble.core.gamestates;

import sem.group15.bubblebobble.core.GameController;
import sem.group15.bubblebobble.core.objects.Player;

/**
 * Created by daan on 20-10-15.
 */
public class LostState implements GameState {
    @Override
    public GameState handleState(GameController controller, float elapsed) {
        controller.getLevelRenderer().renderLost(controller.getCurrentLevelNumber());
        if (controller.checkForStartKey()) {
            resetController(controller);
            controller.startLevel(1);
            return new PlayState();
        }
        return this;
    }

    /**
     * Resets the GameController so a new game can be started.
     */
    protected void resetController(GameController controller) {
        controller.setPlayer(new Player(GameController.PLAYER_XY_SPAWN, GameController.PLAYER_XY_SPAWN));
        controller.setCurrentLevelNumber(1);
    }

}
