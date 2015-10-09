package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by arjo on 7-10-15.
 */
public class Assets {

    private static Assets singleton;

    public Texture bubble;
    public Texture simpleEnemyLeft;
    public Texture simpleEnemyRight;
    public Texture strongEnemyLeft;
    public Texture strongEnemyRight;
    public Texture filledBubble;
    public Texture playerLeft;
    public Texture playerRight;
    public Texture playerDead;
    public Texture wall;

    public Sound playerDeathSound;
    public Sound playerJumpSound;


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

    private static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
