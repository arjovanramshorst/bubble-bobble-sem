package sem.group15.bubblebobble.core;

import sem.group15.bubblebobble.core.objects.EnemyObject;
import sem.group15.bubblebobble.core.objects.FloorObject;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.WallObject;

import java.io.File;
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
     Wall (object type), 200(x location), 100(y location)
     Wall (object type), 200(x location), 100(y location)
     Wall (object type), 200(x location), 100(y location)
     Wall (object type), 200(x location), 100(y location)
     Floor (object type), 200(x location), 100(y location)
     Floor (object type), 200(x location), 100(y location)
     Floor (object type), 200(x location), 100(y location)
     */
    public LevelParser(){

    }

    public List<GameObject> parse(String filename) throws IOException{
        Scanner scanner = new Scanner(new File(filename));
        List<GameObject> result = new ArrayList<GameObject>();


        while (scanner.hasNext()){
            String object =  scanner.nextLine();
            result.add(getObject(object));
        }

        return result;
    }

    private GameObject getObject(String enemyString){
        String[] enemyArray = enemyString.split(",");
        String objectType = enemyArray[0];
        float xPos = Float.parseFloat(enemyArray[1]);
        float yPos = Float.parseFloat(enemyArray[2]);

        switch (objectType) {
            case "Enemy":
                return new EnemyObject(xPos,yPos);
            case "Floor":
                return new FloorObject(xPos,yPos);
            case "Wall":
                return new WallObject(xPos,yPos);
        }
        return null;
    }

}
