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

    private Texture texture;
    private TextureRegion sprite;

    private float x;
    private float y;

    private Vector2 targetPos;
    private Vector2 previousPos;

    protected abstract TextureRegion initSprite(Texture texture);

    public abstract void update();

    public GameObject(Texture texture, float x, float y) {
        this.texture = texture;
        this.sprite = this.initSprite(texture);
        this.x = x;
        this.y = y;

        this.targetPos = new Vector2(this.x, this.y);
        this.previousPos = new Vector2(this.x, this.y);
    }

    public GameObject(Texture texture, Vector2 pos) {
        this(texture, pos.x, pos.y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getSprite(), getX(), getY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());
    }

    public void updateServer(int id) {
        WebSocket socket = WebSocket.getInstance();
        UpdateMessage update = new UpdateMessage(id, targetPos.x, targetPos.y, socket.getId());

        socket.send("/game/update", update);
    }

    public Vector2 getPreviousPos() {
        return previousPos;
    }

    public void setPreviousPos(Vector2 previousPos) {
        this.previousPos = previousPos;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void uploadToServer() {
        String path = ((FileTextureData) getTexture().getTextureData()).getFileHandle().path();
        String clazz = getClass().getName();

        WebSocket.getInstance().send("/game/object/add", new ServerGameObject(getX(), getY(), path, clazz));
    }
}
