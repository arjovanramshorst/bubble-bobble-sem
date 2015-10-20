package sem.group15.bubblebobble.core;


import sem.group15.bubblebobble.core.objects.Enemy;
import sem.group15.bubblebobble.core.objects.FilledBubble;
import sem.group15.bubblebobble.core.objects.Fruit;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.Player;

import java.util.Iterator;
import java.util.List;


/**
 * Created by Daan Vermunt on 16-9-2015.
 */
public class Level {

    private List<GameObject> gameObjects;
    private Player player;

    /**
     * Creates a new Level containing the gameObjects send to it by the parser.
     */
    public Level(final List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Run the main game loop (update, collision, objects)
     * @param elapsed time elapsed since last frame.
     */
    public final void run(final float elapsed) {
        update(elapsed);
        checkCollisions();
        handleNewObjects();
        removeObjects();
    }

    /**
     * updates all objects.
     * @param elapsed time elapsed since latest update
     */
    public final void update(final float elapsed) {
        for (GameObject object : gameObjects) {
            if(!object.remove()) {
                object.update(elapsed);
            }
        }
    }

    /**
     * check for all collisions and handle them in their respective objects.
     */
    protected void checkCollisions() {
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int i2 = i + 1; i2 < gameObjects.size(); i2++) {
                if(!gameObjects.get(i).remove() && !gameObjects.get(i2).remove() ) {
                    gameObjects.get(i).handleCollision(gameObjects.get(i2));
                    gameObjects.get(i2).handleCollision(gameObjects.get(i));
                }
            }
        }
    }

    /**
     * Checks all objects for any new objects that should be added to the gameObjects list.
     */
    protected void handleNewObjects() {
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).addNewObjectsTo(gameObjects);
        }
    }

    /**
     * Removes all objects with remove flag set to true.
     * Used as some sort of garbage collection.
     */
    protected void removeObjects() {
        Iterator<GameObject> iter = gameObjects.iterator();
        while (iter.hasNext()) {
            if (iter.next().remove()) {
                iter.remove();
            }
        }
    }

    /**
     * Checks if all enemies are dead.
     * @return true if all enemies are dead.
     */
    public final boolean levelFinished() {
        for (GameObject object : gameObjects) {
            if (object instanceof Enemy || object instanceof FilledBubble || object instanceof Fruit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the list of objects in game.
     * @return
     */
    public List<GameObject> getObjects() {
        return gameObjects;
    }

    /**
     * Get the player object.
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player, and adds it to the list of GameObjects.
     * @param player
     */
    public void setPlayer(Player player) {
        gameObjects.add(0, player);
        this.player = player;
    }
}
