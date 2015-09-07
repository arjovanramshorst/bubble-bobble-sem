package sem.group15.bubblebobble.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arjo on 7-9-15.
 */
public abstract class ImmutableObject extends GameObject {

    protected ImmutableObject(Rectangle size, Texture texture, float xPosition, float yPosition) {
        super(size, texture, xPosition, yPosition);
    }


}
