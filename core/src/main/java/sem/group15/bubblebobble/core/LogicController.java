package sem.group15.bubblebobble.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sem.group15.bubblebobble.core.objects.BubbleObject;
import sem.group15.bubblebobble.core.objects.FilledBubbleObject;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.PlayerObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjo on 7-9-15.
 */
public class LogicController {
    private List<GameObject> gameObjects;
    private PlayerObject player;
    private float timeTilLastShot = 1f;


    public LogicController() {
        gameObjects = new ArrayList<GameObject>();
    }

    public void setPlayer(PlayerObject _player){
        player=_player;
    }
    public void readMap(String filename) {
        // TODO: Implement levelparser;
    }

    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }

    public void loop(float elapsed, SpriteBatch batch) {
        update(elapsed);
        checkCollisions();
        draw(batch);
    }

    private void update(float elapsed) {
        for(GameObject object : gameObjects) {
            object.update(elapsed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && timeTilLastShot > .1f) {
            addGameObject(new BubbleObject(player.getBody().getX(), player.getBody().getY(), player.getDirection()));
            timeTilLastShot = 0;
        }else{
            timeTilLastShot += elapsed;
        }

    }

    private void checkCollisions() {
        for(int i = 0; i < gameObjects.size() - 1; i++) {
            for(int i2 = i+1; i2 < gameObjects.size(); i2++) {
                gameObjects.get(i).handleCollision(gameObjects.get(i2));
                gameObjects.get(i2).handleCollision(gameObjects.get(i));
                checkAlive(i);
            }
        }
    }

    /**
     * Check if the obj is one of the objects that has an alive value,
     * if it has, and isAlive is false, remove object.
     * @param i
     */
    private void checkAlive(int i) {
        if (gameObjects.get(i) instanceof FilledBubbleObject){
            if (!((FilledBubbleObject) gameObjects.get(i)).isAlive()) {
                gameObjects.remove(gameObjects.get(i));
                //do what needs to be done when filled bubble is popped (fruit? add score?)
            }
        }
    }

    private void draw(SpriteBatch batch) {
        for(GameObject object : gameObjects) {
            object.draw(batch);
        }
    }

}
