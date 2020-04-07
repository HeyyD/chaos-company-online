package fi.chaocompany.online.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameObject {

    private TextureRegion sprite;

    private float x;
    private float y;

    protected abstract TextureRegion initSprite(Texture texture);
    protected abstract void update();

    public GameObject(Texture texture) {
        this.sprite = this.initSprite(texture);
    }

    public TextureRegion getSprite() {
        return sprite;
    }

    public void setSprite(TextureRegion sprite) {
        this.sprite = sprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
