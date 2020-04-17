package fi.chaocompany.online.network.models;

public class UpdateMessage {
    private int id;
    private float x;
    private float y;
    private String sessionId;

    public UpdateMessage() {}


    public UpdateMessage(int id, float x, float y, String sessionId) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "UpdateMessage{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
