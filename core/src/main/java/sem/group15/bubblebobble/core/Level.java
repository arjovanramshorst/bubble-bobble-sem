package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import sem.group15.bubblebobble.core.objects.EnemyObject;
import sem.group15.bubblebobble.core.objects.FilledBubbleObject;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.PlayerObject;

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
            map = parser.parse(Gdx.files.internal("levels/" + levelNumber + ".lvl"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("file does not exist");
        }
    }
  /** Checks if all enemies are dead.
            * @return true if all enemies are dead.
            */

    public boolean levelFinished(){
        for(GameObject object : map) {
            if(object instanceof EnemyObject || object instanceof FilledBubbleObject) {
                return false;
            }
        }
        return true;
    }

    public List<GameObject> getMap() {
        return map;
    }


}
