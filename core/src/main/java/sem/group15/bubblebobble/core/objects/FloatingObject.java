package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * Abstract class for the different kinds of bubbles
 * Created by TUDelft SID on 7-9-2015.
 */
public abstract class FloatingObject extends GameObject{




    protected float timeFromFired;
    protected FloatingObject(Rectangle location, Texture texture) {
        super(location, texture);
    }




    public void update (float elapsed) {
        timeFromFired += elapsed;

    }

}
