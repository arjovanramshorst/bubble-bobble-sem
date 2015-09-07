package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by TUDelft SID on 7-9-2015.
 */
public abstract class FloatingObject extends GameObject{

    protected float ySpeed;
    protected float xSpeed;
    protected float timeFromFired;
    protected FloatingObject(Rectangle size, Texture texture, float xPosition, float yPosition) {
        super(size, texture, xPosition, yPosition);
    }


    public void update (float elapsed) {
        timeFromFired = 0;

    }

}
