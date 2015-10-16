package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import org.junit.Test;

import javax.xml.soap.Text;

/**
 * Created by arjo on 7-10-15.
 */
public final class Assets {

    private static Assets singleton;

    /**
     * The texture for a bubble.
     */
    public Texture bubble;
    /**
     * The texture for a simpleEnemyLeft.
     */
    public Texture simpleEnemyLeft;
    /**
     * The texture for a simpleEnemyRight.
     */
    public Texture simpleEnemyRight;
    /**
     * The texture for a simpleAngryEnemyRight.
     */
    public Texture simpleAngryEnemyRight;
    /**
     * The texture for a simpleAngryEnemyLeft.
     */
    public Texture simpleAngryEnemyLeft;
    /**
     * The texture for a strongEnemyLeft.
     */
    public Texture strongEnemyLeft;
    /**
     * The texture for a strongEnemyRight.
     */
    public Texture strongEnemyRight;
    /**
     * The texture for a filledBubble.
     */
    public Texture filledBubble;
    /**
     * The texture for a playerLeft.
     */
    public Texture playerLeft;
    /**
     * The texture for a playerRight.
     */
    public Texture playerRight;
    /**
     * The texture for a playerDead.
     */
    public Texture playerDead;
    /**
     * The texture for a wall.
     */
    public Texture wall;
    /**
     * The texture for a powerup.
     */
    public Texture powerup;

    /**
     * The texture for a banana
     */
    public Texture banana;
    /**
     * The texture for a cherry
     */
    public Texture cherry;
    /**
     * The sound for a playerDeathSound.
     */
    public Sound playerDeathSound;
    /**
     * The sound for a playerJumpSound.
     */
    public Sound playerJumpSound;


    /**
     * A getter for all Assets.
     * @return Assets.
     */
    public static Assets getAssets() {
        if (singleton == null) {
            singleton = new Assets();
        }
        return singleton;
    }

    /**
     * Private constructor, can only be called from getAssets method.
     */
    private Assets() {
        initialize();
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
            playerLeft = loadTexture("playerSprite.png");
            playerRight = loadTexture("playerSpriteRight.png");
            playerDead = loadTexture("playerDead.png");
            wall = loadTexture("sprite_wall_brick.png");
            powerup = loadTexture("powerup.png");
            banana = loadTexture("banana-icon.png");
            cherry = loadTexture("cherry-icon.png");
            playerDeathSound = Gdx.audio.newSound(Gdx.files.internal("Player Death.wav"));
            playerJumpSound = Gdx.audio.newSound(Gdx.files.internal("Jump.wav"));
        }
        catch (NullPointerException e) {

        }

    }

    /**
     * Loads a texture with filename file.
     * @param file filename.
     * @return Texture containing the file.
     */
    private static Texture loadTexture(final String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
