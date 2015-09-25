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
 * Overlooking logic class that controls and draws the game objects
 * Created by arjo on 7-9-15.
 */
public class LogicController {
    private List<GameObject> gameObjects;
    public PlayerObject player;
    private Level levelMap;

    private int currentLevel;

    private static final int MAX_LEVEL = 3;


    public LogicController() {
        gameObjects = new ArrayList<GameObject>();
    }

    public void init(int level) {
        currentLevel = level;
        gameObjects.clear();
        setPlayer();
        readMap(level);
    }

    /**
     * add an object to be drawn to the controller
     * @param object object to be drawn
     */
    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }

    private void setPlayer(){
        if(this.player == null) {
            this.player = new PlayerObject(200,200);
        }
        gameObjects.add(this.player);
    }
    private void readMap(int level) {
        levelMap= new Level(level);
        gameObjects.addAll(levelMap.getMap());
    }

    /**
     * MAIN GAME LOOP
     * @param elapsed time elapsed since last update
     * @param batch spriteBatch to be drawn
     */
    public void loop(float elapsed, SpriteBatch batch) {
        if (checkForLose()) {
            player = null;
            init(1);
        }
        else if (levelMap.levelFinished()) {
            init(Math.min(currentLevel + 1, MAX_LEVEL));
        }
        update(elapsed);
        checkCollisions();
        handleNewObjects();
        removeObjects();
        draw(batch);
    }

    /**
     * Checks if the game is lost.
     * @return true if the player lost the game.
     */
    private boolean checkForLose() {
        return !player.isAlive();
    }



    /**
     * updates all objects
     * @param elapsed time elapsed since latest update
     */

    private void update(float elapsed) {
        for(GameObject object : gameObjects) {
            object.update(elapsed);
        }
    }

    /**
     * check for all collisions and handle them in their respective objects
     */

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

    /**
     * draw all sprites
     * @param batch spritebatch to be drawn
     */

    private void draw(SpriteBatch batch) {
        for(GameObject object : gameObjects) {
            object.draw(batch);
        }
    }

}
