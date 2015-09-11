package sem.group15.bubblebobble.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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


    public LogicController() {
        gameObjects = new ArrayList<GameObject>();
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
    }

    private void checkCollisions() {
        for(int i = 0; i < gameObjects.size() - 1; i++) {
            for(int i2 = i+1; i2 < gameObjects.size(); i2++) {

                //ugly fix for player collision with filled bubble
                if ((gameObjects.get(i) instanceof FilledBubbleObject) && (gameObjects.get(i2) instanceof PlayerObject)){
                    if (gameObjects.get(i).getBody().overlaps(gameObjects.get(i2).getBody()) ) {
                        gameObjects.remove(i);
                        continue;
                    }
                }
                //ugly fix for player collision with filled bubble
                if ((gameObjects.get(i2) instanceof FilledBubbleObject) && (gameObjects.get(i) instanceof PlayerObject)){
                    if (gameObjects.get(i2).getBody().overlaps(gameObjects.get(i).getBody()) ) {
                        gameObjects.remove(i2);
                        continue;
                    }
                }

                gameObjects.get(i).handleCollision(gameObjects.get(i2));
                gameObjects.get(i2).handleCollision(gameObjects.get(i));
            }
        }
    }

    private void draw(SpriteBatch batch) {
        for(GameObject object : gameObjects) {
            object.draw(batch);
        }
    }

}
