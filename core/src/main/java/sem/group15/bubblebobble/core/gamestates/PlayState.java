package sem.group15.bubblebobble.core.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import sem.group15.bubblebobble.core.GameController;
import sem.group15.bubblebobble.core.objects.Player;

/**
 * Created by daan on 20-10-15.
 */
public class PlayState extends GameState {

    public PlayState(GameController controller) {
        super(controller);
    }

    @Override
    public GameState handleState(float elapsed) {
        GameState newState = this;
        controller.getCurrentLevel().run(elapsed);

        if (checkForLose(controller.getPlayer())) {
            newState = new LostState(controller);
        }

        if (controller.getCurrentLevel().levelFinished()) {
            controller.setCurrentLevelNumber(controller.getCurrentLevelNumber() + 1);
            return new TransitionState(controller, this);
        }

        if (checkForPauseKey()) {
            return new PauseState(controller);
        }

        render();
        return newState;
    }


    public void render() {
        controller.getLevelRenderer().render();
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
