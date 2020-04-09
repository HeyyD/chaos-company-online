package fi.chaocompany.online.network.models;

public class ServerGameObject {
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
}
