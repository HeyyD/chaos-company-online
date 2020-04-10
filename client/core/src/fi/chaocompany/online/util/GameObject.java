package fi.chaocompany.online.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Vector2;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.network.models.ServerGameObject;
import fi.chaocompany.online.network.models.UpdateMessage;

public abstract class GameObject {

    private static final String LOG_TAG = GameObject.class.getSimpleName();

    private TextureRegion sprite;

    private float x;
    private float y;

    private Vector2 targetPos;

    protected abstract TextureRegion initSprite(Texture texture);

    public GameObject(Texture texture, float x, float y) {
        this.sprite = this.initSprite(texture);
        this.x = x;
        this.y = y;

        this.targetPos = new Vector2(this.x, this.y);
    }

    public GameObject(Texture texture, Vector2 pos) {
        this.x = pos.x;
        this.y = pos.y;

        this.targetPos = new Vector2(this.x, this.y);
        this.sendToServer(texture);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getSprite(), getX(), getY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());
    }

    public void update(int id) {
        Vector2 currentPos = new Vector2(this.x, this.y);

        if (!currentPos.equals(targetPos)) {
            WebSocket socket = WebSocket.getInstance();
            UpdateMessage update = new UpdateMessage(id, targetPos.x, targetPos.y, socket.getId());

            socket.send("/game/update", update);
        }
    }

    public Vector2 getTargetPos() {
        return targetPos;
    }

    public void setTargetPos(Vector2 targetPos) {
        this.targetPos = targetPos;
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
