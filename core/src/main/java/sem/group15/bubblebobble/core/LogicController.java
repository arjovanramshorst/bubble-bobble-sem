package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sem.group15.bubblebobble.core.objects.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by arjo on 7-9-15.
 */
public class LogicController {
    private List<GameObject> gameObjects;
    public PlayerObject player;


    public LogicController() {
        gameObjects = new ArrayList<GameObject>();
    }

    public void init(int level) {
        setPlayer();
        readMap(level);
    }

    private void setPlayer(){
        this.player = new PlayerObject(200,200);
        gameObjects.add(this.player);
    }
    private void readMap(int level) {
        gameObjects.addAll((new Level(level)).getMap());
    }

    public void loop(float elapsed, SpriteBatch batch) {
        update(elapsed);
        checkCollisions();
        handleNewObjects();
        removeObjects();
        draw(batch);
    }

    private void update(float elapsed) {
        for(GameObject object : gameObjects) {
            object.update(elapsed);
        }
    }

    private void checkCollisions() {
        for(int i = 0; i < gameObjects.size(); i++) {
            for(int i2 = i+1; i2 < gameObjects.size(); i2++) {
                gameObjects.get(i).handleCollision(gameObjects.get(i2));
                gameObjects.get(i2).handleCollision(gameObjects.get(i));
            }
        }
    }

    /**
     * Checks all objects for any new objects that should be added to the gameObjects list.
     */
    private void handleNewObjects() {
        for(int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).addNewObjectsTo(gameObjects);
        }
    }

    /**
     * Removes all objects with remove flag set to true. Used as some sort of garbage collection.
     */
    private void removeObjects() {
        Iterator<GameObject> iter = gameObjects.iterator();
        while (iter.hasNext()) {
            if (iter.next().remove()) {
                iter.remove();
            }
        }
    }

    private void draw(SpriteBatch batch) {
        for(GameObject object : gameObjects) {
            object.draw(batch);
        }
    }

}
