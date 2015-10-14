package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.FilledBubble;
import sem.group15.bubblebobble.core.objects.GameObject;

import java.util.List;


/**
 * Created by Daan Vermunt on 16-9-2015.
 */
public class Level {

    private List<GameObject> map;
    private List<GameObject> possiblePowerups;

    /**
     * Constructor for a new level, finds the level file using levelNumber.
     * Calls the Parser and generates a list of all GameObjects.
     * @param levelNumber, the number of the level to be loaded.
     */
    public Level(final int levelNumber) {
        //parse level and get map.
        LevelParser parser = new LevelParser();
        try {
            map = parser.parse(Gdx.files.internal("levels/" + levelNumber + ".lvl"));
            possiblePowerups = parser.parsePowerups(Gdx.files.internal("levels/" + levelNumber + ".lvl"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("file does not exist");
        }
    }

    /**
     * Checks if all enemies are dead.
     * @param objects, a List including all current GameObjects in the game.
     * @return true if all enemies are dead.
     */
    public final boolean levelFinished(final List<GameObject> objects) {
        for (GameObject object : objects) {
            if (object instanceof Enemy || object instanceof FilledBubble) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter for the list of all gameObjects.
     * @return map, a List with all GameObjects of a level.
     */
    public final List<GameObject> getMap() {
        return map;
    }

    /**
     * Returns a powerup if there is one available for the current level.
     * @return powerup, a powerup for the current level.
     */
    public final GameObject getPowerup() {
        if (!possiblePowerups.isEmpty()) {
            GameObject powerup = possiblePowerups.get(0);
            possiblePowerups.remove(0);
            return powerup;
        } else {
            return null;
        }
    }



}
