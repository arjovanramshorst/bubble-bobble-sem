package sem.group15.bubblebobble.core.gamestates;

import sem.group15.bubblebobble.core.GameController;
import sem.group15.bubblebobble.core.objects.Player;

/**
 * Created by daan on 20-10-15.
 */
public class LostState extends GameState {

    public LostState(GameController controller) {
        super(controller);
    }

    @Override
    public GameState handleState(float elapsed) {
        render();
        if (controller.checkForStartKey()) {
            resetController(controller);
            controller.startLevel(controller.getLevel(1));
            return new PlayState(controller);
        }
        return this;
    }

    public void render() {
        controller.getLevelRenderer().renderLost(controller.getCurrentLevelNumber());
    }

    /**
     * Resets the GameController so a new game can be started.
     */
    protected void resetController(GameController controller) {
        controller.setPlayer(new Player(GameController.PLAYER_XY_SPAWN, GameController.PLAYER_XY_SPAWN));
        controller.setCurrentLevelNumber(1);
    }

}
