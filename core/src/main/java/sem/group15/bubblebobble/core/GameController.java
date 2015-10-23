package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import sem.group15.bubblebobble.core.gamestates.GameState;
import sem.group15.bubblebobble.core.gamestates.NewState;
import sem.group15.bubblebobble.core.objects.Player;

import java.io.IOException;


/**
 * Overlooking logic class that controls and draws the game objects
 * Created by arjo on 7-9-15.
 */
public class GameController {
    /**
     * Player that is used by the user.
     */
    private Player player;
    /**
     * Level that is played.
     */
    private Level currentLevel;

    /**
     * the player XY spawn.
     */
    public static final int PLAYER_XY_SPAWN = 64;

    /**
     * Levelrenderer that renders the level.
     */
    private LevelRenderer levelRenderer;
    /**
     * the number of the currentLebel.
     */
    private int currentLevelNumber;
    /**
     * the maximum level.
     */
    private static final int MAX_LEVEL = 3;


    private GameState gameState;

    /**
     * Creates a new GameController object.
     */
    public GameController() {
        player = new Player(PLAYER_XY_SPAWN, PLAYER_XY_SPAWN);
        levelRenderer = new LevelRenderer();
        gameState = new NewState(this);
        currentLevelNumber = 1;
    }

    /**
     * Main game loop. Calls the appropriate method to handle the current game
     * state.
     *
     * @param elapsed time elapsed since last frame.
     */
    public final void run(final float elapsed) {
        gameState = gameState.handleState(elapsed);
    }

    /**
     * initialize the level with number levelNumber.
     * @param levelNumber Number of the level to retrieve.
     * @return
     */
    public final Level getLevel(final int levelNumber) {
        int levelToLoad = Math.min(levelNumber, MAX_LEVEL);
        try {
            currentLevel = LevelParser.parse(Gdx.files.internal("levels/" + levelToLoad + ".lvl"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return currentLevel;
    }

    /**
     * Initiates all objects the game needs.
     */
    public final void startLevel(Level level) {
        this.currentLevel = level;
        currentLevel.setPlayer(player);
        player.respawn();
        levelRenderer.setLevel(currentLevel);
    }

    /**
     * Returns true if the start key is pressed (enter for now).
     *
     * @return true if enter is pressed.
     */
    public boolean checkForStartKey() {
        return Gdx.input.isKeyPressed(Input.Keys.ENTER);
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
     * Get current level.
     *
     * @return currentLevel
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Get level renderer.
     *
     * @return levelRenderer
     */
    public LevelRenderer getLevelRenderer() {
        return levelRenderer;
    }

    /**
     * Get max level.
     *
     * @return MAX_LEVEL
     */
    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    /**
     * Sets the level renderer.
     * for testing purposes.
     *
     * @Param levelRenderer
     */
    public void setLevelRenderer(LevelRenderer levelRenderer) {
        this.levelRenderer = levelRenderer;
    }

    /**
     * Sets the game state.
     * for testing purposes.
     *
     * @Param gameState
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
