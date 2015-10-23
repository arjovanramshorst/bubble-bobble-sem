package sem.group15.bubblebobble.core.gamestates;

import sem.group15.bubblebobble.core.GameController;

/**
 * Created by daan on 20-10-15.
 */
public class PauseState extends GameState {

    public PauseState(GameController controller) {
        super(controller);
    }

    @Override
    public GameState handleState(float elapsed) {
        render();
        if (controller.checkForStartKey()) {
            return new PlayState(controller);
        }
        return this;
    }

    public void render() {
        controller.getLevelRenderer().renderPause();
    }
}
