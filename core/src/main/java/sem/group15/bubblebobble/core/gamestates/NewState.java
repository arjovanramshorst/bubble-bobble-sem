package sem.group15.bubblebobble.core.gamestates;

import sem.group15.bubblebobble.core.GameController;

/**
 * Created by daan on 20-10-15.
 */
public class NewState extends GameState {

    public NewState(GameController controller) {
        super(controller);
    }

    @Override
    public GameState handleState(float elapsed) {
        render();
        if (controller.checkForStartKey()) {
            controller.startLevel(controller.getLevel(1));
            return new PlayState(controller);
        }
        return this;
    }

    public void render() {
        controller.getLevelRenderer().renderNew();
    }

}
