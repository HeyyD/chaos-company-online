package fi.chaocompany.online.network;

public class MapMessage {
    int[][] map;

    public MapMessage() {
    }

    public MapMessage(int[][] map) {
        this.map = map;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
