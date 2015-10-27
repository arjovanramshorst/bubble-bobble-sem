package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


/**
 * Created by arjo on 7-10-15.
 */
public final class Assets {
    /**
     * the asset singleton.
     */
    private static Assets singleton;

    /**
     * The texture for a bubble.
     */
    private Texture bubble;
    /**
     * The texture for a simpleEnemyLeft.
     */
    private Texture simpleEnemyLeft;
    /**
     * The texture for a simpleEnemyRight.
     */
    private Texture simpleEnemyRight;
    /**
     * The texture for a simpleAngryEnemyRight.
     */
    private Texture simpleAngryEnemyRight;
    /**
     * The texture for a simpleAngryEnemyLeft.
     */
    private Texture simpleAngryEnemyLeft;
    /**
     * The texture for a strongEnemyLeft.
     */
    private Texture strongEnemyLeft;
    /**
     * The texture for a strongEnemyRight.
     */
    private Texture strongEnemyRight;
    /**
     * The texture for a filledBubble.
     */
    private Texture filledBubble;
    /**
     * The texture for a filledbubble from a strong enemy.
     */
    private Texture filledBubbleStrong;
    /**
     * The texture for a playerLeft.
     */
    private Texture playerLeft;
    /**
     * The texture for a playerRight.
     */
    private Texture playerRight;
    /**
     * The texture for a playerDead.
     */
    private Texture playerDead;
    /**
     * The texture for a wall.
     */
    private Texture wall;
    /**
     * The texture for a powerup.
     */
    private Texture powerup;

    /**
     * The texture for a banana.
     */
    private Texture banana;
    /**
     * The texture for a cherry.
     */
    private Texture cherry;
    /**
     * The sound for a playerDeathSound.
     */
    private Sound playerDeathSound;
    /**
     * The sound for a playerJumpSound.
     */
    private Sound playerJumpSound;
    /**
     * Prefence object used for highscores.
     */
    private static Preferences prefs;


    /**
     * Private constructor, can only be called from getAssets method.
     */
    private Assets() {
        initialize();
    }

    /**
     * A getter for all Assets.
     *
     * @return Assets.
     */
    public static Assets getAssets() {
        if (singleton == null) {
            singleton = new Assets();
        }
        return singleton;
    }

    /**
     * Loads a texture with filename file.
     *
     * @param file filename.
     * @return Texture containing the file.
     */
    private static Texture loadTexture(final String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public Texture getBubble() {
        return bubble;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getSimpleEnemyLeft() {
        return simpleEnemyLeft;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getSimpleEnemyRight() {
        return simpleEnemyRight;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getSimpleAngryEnemyRight() {
        return simpleAngryEnemyRight;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getSimpleAngryEnemyLeft() {
        return simpleAngryEnemyLeft;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getStrongEnemyLeft() {
        return strongEnemyLeft;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getStrongEnemyRight() {
        return strongEnemyRight;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getFilledBubble() {
        return filledBubble;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getPlayerLeft() {
        return playerLeft;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getPlayerRight() {
        return playerRight;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getPlayerDead() {
        return playerDead;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getWall() {
        return wall;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getPowerup() {
        return powerup;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getBanana() {
        return banana;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Texture getCherry() {
        return cherry;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Sound getPlayerDeathSound() {
        return playerDeathSound;
    }

    /**
     * Getter for texture.
     *
     * @return the texture.
     */
    public Sound getPlayerJumpSound() {
        return playerJumpSound;
    }

    /**
     * Initialize all textures.
     */
    private void initialize() {
        try {
            bubble = loadTexture("bubble-empty.png");
            simpleEnemyLeft = loadTexture("enemyLeft.png");
            simpleEnemyRight = loadTexture("enemy.png");
            simpleAngryEnemyLeft = loadTexture("enemyAngryleft.png");
            simpleAngryEnemyRight = loadTexture("enemyAngry.png");
            strongEnemyLeft = loadTexture("strongEnemyLeft.png");
            strongEnemyRight = loadTexture("strongEnemyRight.png");
            filledBubble = loadTexture("filled-bubble.png");
            filledBubbleStrong = loadTexture("filled-bubble-strong.png");
            playerLeft = loadTexture("playerSprite.png");
            playerRight = loadTexture("playerSpriteRight.png");
            playerDead = loadTexture("playerDead.png");
            wall = loadTexture("sprite_wall_brick.png");
            powerup = loadTexture("powerup.png");
            banana = loadTexture("banana-icon.png");
            cherry = loadTexture("cherry-icon.png");
            playerDeathSound = Gdx.audio.newSound(Gdx.files.internal("Player Death.wav"));
            playerJumpSound = Gdx.audio.newSound(Gdx.files.internal("Jump.wav"));
            prefs = Gdx.app.getPreferences("highScores");
            if (!prefs.contains("highScore")) {
                prefs.putInteger("highScore", 0);
            }

        } catch (NullPointerException e) {
            System.out.println("error loading texture");
        }

    }

    /**
     * Setter for highScore.
     */
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    /**
     * Getter for highScore.
     */
    public static int getHighScore() {
        try {
            return prefs.getInteger("highScore");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Getter for strong filled bubble.
     * @return the texture.
     */
    public Texture getFilledBubbleStrong() {
        return filledBubbleStrong;
    }
}
