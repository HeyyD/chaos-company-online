package fi.chaocompany.online.network.models;

public class UpdateMessage {
    String msg;

    public UpdateMessage() {}

    public UpdateMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
