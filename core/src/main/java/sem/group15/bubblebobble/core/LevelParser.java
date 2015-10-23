package sem.group15.bubblebobble.core;

import com.badlogic.gdx.files.FileHandle;
import sem.group15.bubblebobble.core.factories.EnemyFactory;
import sem.group15.bubblebobble.core.factories.SimpleEnemyFactory;
import sem.group15.bubblebobble.core.factories.StrongEnemyFactory;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.Floor;
import sem.group15.bubblebobble.core.objects.Wall;
import sem.group15.bubblebobble.core.objects.Powerup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Marketing Lorre on 16-9-2015.
 */
public final class LevelParser {
    //format for parsing
    /*
     200(player x), 200(player y)  OPTIONAL
     3 (number of enemies)
     0 (enemy type), 200(x location), 100(y location)
     0 (enemy type), 200(x location), 100(y location)
     0 (enemy type), 200(x location), 100(y location)
     Wall (object type), 20(x location), 100(y location)
     Wall (object type), 200(x location), 100(y location)
     Wall (object type), 200(x location), 100(y location)
     Wall (object type), 200(x location), 100(y location)
     Floor (object type), 200(x location), 100(y location)
     Floor (object type), 200(x location), 100(y location)
     Floor (object type), 200(x location), 100(y location)
     */


    /**
     * The enemy factory depending on which enemy is created.
     */
    private static EnemyFactory enemyFactory;

    private LevelParser() {
        //never called.
    }

    /**
     * The parse function parses a file from the assets folder.
     * @param file a file which should be selected from the assetsfolder.
     * @return returns a List including all GameObjects declared in the levelFile
     * @throws IOException is thrown if an unknown GameObject is declared in the levelFile.
     */
    public static Level parse(final FileHandle file) throws IOException {
        List<GameObject> result = new ArrayList<GameObject>();
        Scanner sc = new Scanner(file.read());
        if (sc.hasNext()) {
            String enemyType = sc.nextLine();
            switch (enemyType) {
                case "SimpleEnemy":
                    enemyFactory = new SimpleEnemyFactory();
                    break;
                case "StrongEnemy":
                    enemyFactory = new StrongEnemyFactory();
                    break;
                default:
                    enemyFactory = new SimpleEnemyFactory(); // default to simple enemies, if there is no line present.
                    result.add(getObject(enemyType)); // Get first line as object anyway.
                    break;
            }
        }
        while (sc.hasNext()) {
            String object =  sc.nextLine();
            result.add(getObject(object));
        }
        return new Level(result);
    }

    /**
     * Get object from string.
     * @param line String containing the object details.
     * @return the Object.
     * @throws IOException if string is not valid.
     */
    public static GameObject getObject(final String line) throws IOException {
        String[] enemyArray = line.split(",");
        String objectType = enemyArray[0];
        float xPos = BubbleBobble.SPRITE_SIZE * Float.parseFloat(enemyArray[1]);
        float yPos = BubbleBobble.SPRITE_SIZE * Float.parseFloat(enemyArray[2]);

        switch (objectType) {
            case "Enemy":
                return enemyFactory.createObject(xPos, yPos, Enemy.State.NORMAL);
            case "Floor":
                return new Floor(xPos, yPos);
            case "Wall":
                return new Wall(xPos, yPos);
            case "Powerup":
                return new Powerup(xPos, yPos);
            default:
                throw new IOException("String: " + line + " is not a valid object!");
        }

    }
}
