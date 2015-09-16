package sem.group15.bubblebobble.core;

import sem.group15.bubblebobble.core.objects.GameObject;

import java.util.List;


/**
 * Created by Daan Vermunt on 16-9-2015.
 */
public class Level {


    private List<GameObject> map;

    public Level(int levelNumber){
        //parse level and get map.
        LevelParser parser = new LevelParser();
        try {
            map = parser.parse("/D:/DAAN/Studie/bubble-bobble-sem/assets/levels/"+levelNumber+".txt");

        }catch (Exception e){
            System.out.println("file does not exist");
        }
    }

    public boolean levelFinished(List<GameObject> gameObjects){
        return false;
    }

    public List<GameObject> getMap() {
        return map;
    }


}
