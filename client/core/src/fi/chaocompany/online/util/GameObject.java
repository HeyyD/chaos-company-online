package fi.chaocompany.online.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class GameObject {

    private TextureRegion sprite;

    private float x;
    private float y;

    protected abstract TextureRegion initSprite(Texture texture);
    protected abstract void update();

    public GameObject(Texture texture, Vector2 pos, List<GameObject> objects) {
        this.sprite = this.initSprite(texture);

        this.x = pos.x;
        this.y = pos.y;

        objects.add(this);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getSprite(), getX(), getY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());
    }

    public TextureRegion getSprite() {
        return sprite;
    }

    public void setSprite(TextureRegion sprite) {
        this.sprite = sprite;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
