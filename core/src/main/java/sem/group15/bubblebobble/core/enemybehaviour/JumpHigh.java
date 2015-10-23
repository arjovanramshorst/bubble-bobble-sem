package sem.group15.bubblebobble.core.enemybehaviour;

/**
 * Created by Matthijs on 10/23/15.
 */
public class JumpHigh implements JumpBehaviour {

    public JumpHigh() {
    }

    public float jump() {
        float ySpeed = 300;
        return ySpeed;
    }
}
