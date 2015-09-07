package sem.group15.bubblebobble.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sem.group15.bubblebobble.core.objects.GameObject;

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
                gameObjects.get(i).checkCollision(gameObjects.get(i2));
            }
        }
    }

    private void draw(SpriteBatch batch) {
        for(GameObject object : gameObjects) {
            object.draw(batch);
        }
    }

}
