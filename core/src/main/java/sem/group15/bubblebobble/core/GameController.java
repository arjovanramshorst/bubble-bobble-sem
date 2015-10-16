package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import sem.group15.bubblebobble.core.objects.Player;
import java.io.IOException;


/**
 * Overlooking logic class that controls and draws the game objects
 * Created by arjo on 7-9-15.
 */
public class GameController {

    private Player player;

    private Level currentLevel;

    public void setLevelRenderer(LevelRenderer levelRenderer) {
        this.levelRenderer = levelRenderer;
    }

    private LevelRenderer levelRenderer;
    private int currentLevelNumber;
    private static final int MAX_LEVEL = 3;

    public static final int PLAYER_XY_SPAWN = 64;

    public enum GameState {
        NEW,
        PLAY,
        PAUSE,
        LOST
    }

    private GameState state;

    /**
     * Creates a new GameController object.
     */
    public GameController() {
        player = new Player(PLAYER_XY_SPAWN, PLAYER_XY_SPAWN);
        levelRenderer = new LevelRenderer();
        state = GameState.NEW;
        currentLevelNumber = 1;
    }

    /**
     * Resets the GameController so a new game can be started.
     */
    protected void resetController() {
        player = new Player(PLAYER_XY_SPAWN, PLAYER_XY_SPAWN);
        state = GameState.PLAY;
        currentLevelNumber = 1;
    }

    /**
     * Main game loop. Calls the appropriate method to handle the current game
     * state.
     *
     * @param elapsed time elapsed since last frame.
     */
    public final void run(final float elapsed) {
        switch (state) {
            case NEW:
                handleStateNew();
                break;
            case PLAY:
                handleStatePlay(elapsed);
                break;
            case PAUSE:
                handleStatePause();
                break;
            case LOST:
                handleStateLost();
                break;
        }
    }

    /**
     * Is called when the gamestate is new.
     */
    protected void handleStateNew() {
        levelRenderer.renderNew();
        if (checkForStartKey()) {
            state = GameState.PLAY;
            startLevel(currentLevelNumber);
        }
    }

    /**
     * Handles the game when it is in the 'play' state.
     *
     * @param elapsed elapsed time since last frame.
     */
    private void handleStatePlay(float elapsed) {
        currentLevel.run(elapsed);
        if (checkForLose()) {
            state = GameState.LOST;
        }
        if (currentLevel.levelFinished()) {
            currentLevelNumber++;
            startLevel(Math.min(currentLevelNumber, MAX_LEVEL));
        }
        if (checkForPauseKey()) {
            state = GameState.PAUSE;
        }
        levelRenderer.render();
    }

    /**
     * Is called when the gamestate is paused.
     */
    protected void handleStatePause() {
        levelRenderer.renderPause();
        if (checkForStartKey()) {
            state = GameState.PLAY;
        }
    }

    /**
     * Is called when the gamestate is Lost.
     */
    protected void handleStateLost() {
        levelRenderer.renderLost(currentLevelNumber);
        if (checkForStartKey()) {
            resetController();
            startLevel(1);
        }
    }

    /**
     * Returns true if the start key is pressed (enter for now).
     *
     * @return true if enter is pressed.
     */
    protected boolean checkForStartKey() {
        return Gdx.input.isKeyPressed(Input.Keys.ENTER);
    }

    /**
     * Returns true if the pause key is pressed (escape for now).
     *
     * @return true if escape is pressed.
     */
    private boolean checkForPauseKey() {
        return Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }

    /**
     * Initiates all objects the game needs.
     *
     * @param levelNumber level number.
     */
    public final void startLevel(final int levelNumber) {
        try {
            currentLevel = LevelParser.parse(Gdx.files.internal("levels/" + levelNumber + ".lvl"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        currentLevel.setPlayer(player);
        player.respawn();
        levelRenderer.setLevel(currentLevel);
    }

    /**
     * Checks if the game is lost.
     *
     * @return true if the player lost the game.
     */
    protected boolean checkForLose() {
        return !player.isAlive();
    }

    /**
     * Get player.
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set Player.
     *
     * @param player player to be set as player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get level number.
     *
     * @return currentLevelNumeber
     */
    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    /**
     * Set currentLevelNumber.
     *
     * @param currentLevelNumber number to set currentLevelNumber to
     */
    public void setCurrentLevelNumber(int currentLevelNumber) {
        this.currentLevelNumber = currentLevelNumber;
    }

    /**
     * Get state of the controller.
     *
     * @return state
     */
    public GameState getState() {
        return state;
    }

    /**
     * Set the state of the controller.
     *
     * @param state new state
     */
    public void setState(GameState state) {
        this.state = state;

    }
}
