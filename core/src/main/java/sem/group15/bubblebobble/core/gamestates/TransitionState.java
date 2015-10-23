package sem.group15.bubblebobble.core.gamestates;

import com.badlogic.gdx.Gdx;
import sem.group15.bubblebobble.core.GameController;
import sem.group15.bubblebobble.core.Level;
import sem.group15.bubblebobble.core.objects.Player;

/**
 * Created by arjo on 23-10-15.
 */
public class TransitionState extends GameState {

    private static final float TRANSITION_TIME = 3;

    private float timeSinceStart;

    private GameState current;
    private Level nextLevel;
    private float playerLeft, playerBottom;
    private Player player;

    public TransitionState(GameController controller, GameState current) {
        super(controller);
        this.current = current;
        timeSinceStart = 0;
        nextLevel = controller.getLevel(controller.getCurrentLevelNumber());
        player = controller.getPlayer();
        playerLeft = player.getLeft();
        playerBottom = player.getBottom();

    }

    @Override
    public GameState handleState(float elapsed) {
        timeSinceStart += elapsed;
        float percentageComplete = timeSinceStart / TRANSITION_TIME;
        float offset = percentageComplete * Gdx.graphics.getHeight();
        updatePlayerPosition(percentageComplete, offset);
        render(offset);

        if (timeSinceStart >= TRANSITION_TIME) {
            controller.startLevel(nextLevel);
            return current;
        }
        return this;
    }

    public void render(float verticalOffset) {
        controller.getLevelRenderer().renderTransition(nextLevel, verticalOffset);
    }

    private void updatePlayerPosition(float percentage, float offset) {
        player.setLeft(playerLeft + percentage * (GameController.PLAYER_XY_SPAWN - playerLeft));
        player.setBottom(playerBottom + percentage * (GameController.PLAYER_XY_SPAWN - playerBottom) - offset);
    }
}
