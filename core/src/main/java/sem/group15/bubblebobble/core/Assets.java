package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

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

    private Assets() {
        initialize();
    }

    private void initialize() {
        bubble = loadTexture("bubble-empty.png");
        simpleEnemyLeft = loadTexture("enemyLeft.png");
        simpleEnemyRight = loadTexture("enemy.png");
        strongEnemyLeft = loadTexture("strongEnemyLeft.png");
        strongEnemyRight = loadTexture("strongEnemyRight.png");
        filledBubble = loadTexture("filled-bubble.png");
        playerLeft = loadTexture("playerSprite.png");
        playerRight = loadTexture("playerSpriteRight.png");
        playerDead = loadTexture("playerDead.png");
        wall = loadTexture("sprite_wall_brick.png");
        playerDeathSound = Gdx.audio.newSound(Gdx.files.internal("Player Death.wav"));
        playerJumpSound = Gdx.audio.newSound(Gdx.files.internal("Jump.wav"));
    }

    private static Texture loadTexture(final String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
