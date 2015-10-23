package sem.group15.bubblebobble.core.enemybehaviour;

import java.util.Random;

/**
 * Created by Matthijs on 10/23/15.
 */
public class JumpRandom implements JumpBehaviour {
    public JumpRandom() {
    }

    public float jump() {
        Random random = new Random();
        return random.nextFloat() * 300;
    }
}
