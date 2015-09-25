package sem.group15.bubblebobble.core.editor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sem.group15.bubblebobble.core.BubbleBobble;
import sem.group15.bubblebobble.core.objects.FloorObject;
import sem.group15.bubblebobble.core.objects.GameObject;
import sem.group15.bubblebobble.core.objects.WallObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjo on 22-9-15.
 */
public class LevelEditor implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont bitmapFont;
    private List<GameObject> objects;

    private enum Selected {
        WALL,
        FLOOR,
        EMPTY
    }

    private Selected currentlySelected;

    @Override
    public void create() {
        batch = new SpriteBatch();
        objects = new ArrayList<GameObject>();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);
        currentlySelected = Selected.WALL;

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        checkKeyboardInput();
        checkMouseInput();
        renderObjects();
        addSprites();
        batch.end();
    }

    private void checkKeyboardInput() {
         (Gdx.input.isKeyPressed(Input.Keys.S)) {
            save();
        } else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            currentlySelected = Selected.FLOOR;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            currentlySelected = Selected.WALL;
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            currentlySelected = Selected.EMPTY;
        }
    }

    private void save() {
        try {
            File file = new File("tmp.lvl");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);
            for (GameObject o : objects) {
                writeLineToFile(writer, o);
            }
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void writeLineToFile(BufferedWriter writer, GameObject o) throws IOException {
        String line = "";
        if (o instanceof WallObject) {
            line += "Wall";
        } else if (o instanceof FloorObject) {
            line += "Floor";
        }
        line += ", " + (int) (o.getLeft() / BubbleBobble.SPRITE_SIZE) + ", " +
                (int) (o.getBottom() / BubbleBobble.SPRITE_SIZE);
        writer.write(line + "\n");
    }

    private void renderObjects() {
        for (GameObject object : objects) {
            object.draw(batch);
        }
    }

    private void checkMouseInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            checkUIClick(x, y);
        }
    }

    private void checkUIClick(int x, int y) {
        float xPosition = ((int) (x / BubbleBobble.SPRITE_SIZE)) * BubbleBobble.SPRITE_SIZE;
        float yPosition = ((int) (y / BubbleBobble.SPRITE_SIZE)) * BubbleBobble.SPRITE_SIZE;
        GameObject newObject = null;
        if (currentlySelected == Selected.FLOOR) {
            newObject = new FloorObject(xPosition, yPosition);
        } else if (currentlySelected == Selected.WALL) {
            newObject = new WallObject(xPosition, yPosition);
        } else if (currentlySelected == Selected.EMPTY) {
            newObject = new FloorObject(xPosition, yPosition);
        }
        GameObject existingObject = checkCollision(newObject);
        if (existingObject != null) {
            objects.remove(existingObject);
        }
        if (currentlySelected != Selected.EMPTY) {
            objects.add(newObject);
        }
    }

    private void addSprites() {
        if (currentlySelected == Selected.FLOOR) {
            bitmapFont.draw(batch, "currently selected: FLOOR", 0, BubbleBobble.SPRITE_SIZE);
        } else if (currentlySelected == Selected.WALL) {
            bitmapFont.draw(batch, "currently selected: WALL", 0, BubbleBobble.SPRITE_SIZE);
        } else if (currentlySelected == Selected.EMPTY) {
            bitmapFont.draw(batch, "currently selected: ERASE", 0, BubbleBobble.SPRITE_SIZE);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private GameObject checkCollision(GameObject object) {
        for (GameObject o : objects) {
            if (object.collidesWith(o)) {
                return o;
            }
        }
        return null;
    }
}
