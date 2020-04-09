package fi.chaocompany.online.network.models;

import com.badlogic.gdx.graphics.Texture;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.util.GameObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ServerGameObject {
    private String sessionId;
    private String texture;
    private String clazz;

    private float x;
    private float y;

    public ServerGameObject() {
    }

    public ServerGameObject(float x, float y, String texture, String clazz) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.clazz = clazz;

        this.sessionId = WebSocket.getInstance().getId();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public GameObject toGameObject() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName(this.getClazz());
        Constructor<?> constructor = c.getConstructor(Texture.class, float.class, float.class);
        return (GameObject) constructor.newInstance(new Texture(this.getTexture()), this.getX(), this.getY());
    }
}
