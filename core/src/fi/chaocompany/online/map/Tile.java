package fi.chaocompany.online.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {

    private TextureRegion texture;
    private int x;
    private int y;

    public Tile(TextureRegion texture, int x, int y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch, float x, float y, float width, float height) {
        batch.draw(texture, x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
