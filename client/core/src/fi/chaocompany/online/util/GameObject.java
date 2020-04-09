package fi.chaocompany.online.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Vector2;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.network.models.ServerGameObject;

public abstract class GameObject {

    private static final String LOG_TAG = GameObject.class.getSimpleName();

    private TextureRegion sprite;

    private float x;
    private float y;

    protected abstract TextureRegion initSprite(Texture texture);
    public abstract void update();

    public GameObject(Texture texture, float x, float y) {
        this.sprite = this.initSprite(texture);
        this.x = x;
        this.y = y;
    }

    public GameObject(Texture texture, Vector2 pos) {
        this.x = pos.x;
        this.y = pos.y;

        // objects.put(objects.size(), this);
        this.sendToServer(texture);
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

    private void sendToServer(Texture texture) {
        String path = ((FileTextureData)texture.getTextureData()).getFileHandle().path();
        String clazz = getClass().getName();

        WebSocket.getInstance().send("/game/object/add", new ServerGameObject(getX(), getY(), path, clazz));
    }
}
