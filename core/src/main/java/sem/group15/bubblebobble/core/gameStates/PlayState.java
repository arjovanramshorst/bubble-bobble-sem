package sem.group15.bubblebobble.core.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import sem.group15.bubblebobble.core.GameController;
import sem.group15.bubblebobble.core.objects.Player;

/**
 * Created by daan on 20-10-15.
 */
public class PlayState implements GameState {

    @Override
    public GameState handleState(GameController controller, float elapsed) {
        GameState newState = this;
        controller.getCurrentLevel().run(elapsed);

        if (checkForLose(controller.getPlayer())) {
            newState = new LostState();
        }

        if (controller.getCurrentLevel().levelFinished()) {
            controller.setCurrentLevelNumber(controller.getCurrentLevelNumber() + 1);
            controller.startLevel(Math.min(controller.getCurrentLevelNumber(), controller.getMaxLevel()));
        }

        if (checkForLose(controller.getPlayer())) {
            newState = new LostState();
        }

        if (checkForPauseKey()) {
            return new PauseState();
        }

        controller.getLevelRenderer().render();
        return newState;
    }

    private boolean checkForLose(Player player) {
        return !player.isAlive();
    }

    /**
     * Returns true if the pause key is pressed (escape for now).
     *
     * @return true if escape is pressed.
     */
    private boolean checkForPauseKey() {
        return Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }
}
