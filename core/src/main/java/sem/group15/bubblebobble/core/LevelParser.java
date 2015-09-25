package sem.group15.bubblebobble.core;

import com.badlogic.gdx.files.FileHandle;
import sem.group15.bubblebobble.core.objects.EnemyObject;
import sem.group15.bubblebobble.core.objects.FloorObject;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.WallObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Marketing Lorre on 16-9-2015.
 */
public class LevelParser {
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

    public static List<GameObject> parse(FileHandle file) throws IOException {
        List<GameObject> result = new ArrayList<GameObject>();
        Scanner sc = new Scanner(file.read());
        while (sc.hasNext()) {
            String object =  sc.nextLine();
            try {
                result.add(getObject(object));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return result;
    }

    private static GameObject getObject(String line) throws IOException {
        String[] enemyArray = line.split(",");
        String objectType = enemyArray[0];
        float xPos = BubbleBobble.SPRITE_SIZE * Float.parseFloat(enemyArray[1]);
        float yPos = BubbleBobble.SPRITE_SIZE * Float.parseFloat(enemyArray[2]);

        switch (objectType) {
            case "Enemy":
                return new EnemyObject(xPos, yPos);
            case "Floor":
                return new FloorObject(xPos, yPos);
            case "Wall":
                return new WallObject(xPos, yPos);
        }
        throw new IOException("String: " + line + " is not a valid object!");
    }
}
