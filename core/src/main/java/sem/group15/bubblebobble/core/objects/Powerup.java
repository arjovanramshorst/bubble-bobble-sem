package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sem.group15.bubblebobble.core.BubbleBobble;

/**
 * Created by daan on 14-10-15.
 */
public class Powerup extends Gravity{


    private float aliveTime;
    private static final float SPEED_BOOST = 2;
    public Powerup(final float xPosition, final float yPosition){
        super(new Rectangle(xPosition, yPosition, BubbleBobble.SPRITE_SIZE, BubbleBobble.SPRITE_SIZE));
        aliveTime = 0;
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
        aliveTime += elapsed;
        if(aliveTime > 10){
            remove = true;
        }
    }

    @Override
    public void handleCollision(GameObject other) {
        super.handleCollision(other);
        if (location.overlaps(other.getBody())) {
            if (other instanceof Player) {
                remove = true;
            }else {
                remove = true;
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(assets.powerup, getLeft(), getBottom());
    }

    public float getAliveTime(){
        return aliveTime;
    }

    public float getSpeedBoost(){
        return SPEED_BOOST;
    }
}