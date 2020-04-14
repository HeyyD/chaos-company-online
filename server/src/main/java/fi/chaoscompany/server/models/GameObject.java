package fi.chaoscompany.server.models;

public class GameObject {

    private int id;

    private String sessionId;
    private String texture;
    private String clazz;

    private float x;
    private float y;

    public GameObject() {
    }

    public GameObject(GameObject o) {
        this.id = o.getId();
        this.x = o.getX();
        this.y = o.getY();
        this.texture = o.getTexture();
        this.clazz = o.getClazz();
        this.sessionId = o.getSessionId();
    }

    public GameObject(int id, float x, float y, String texture, String clazz) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.clazz = clazz;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
