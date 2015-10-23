package sem.group15.bubblebobble.core.gamestates;

import sem.group15.bubblebobble.core.GameController;

/**
 * Created by daan on 20-10-15.
 */
public abstract class GameState {

    protected GameController controller;

    public GameState(GameController controller) {
        this.controller = controller;
    }

    public abstract GameState handleState(float elapsed);

}
