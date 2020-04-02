package fi.chaocompany.online.pathfinding;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class Node {
    private Node parent;
    private Vector2 position;
    private List<Node> neighbours;

    private int g;
    private int h;
    private int f;

    private boolean isFull;

    public Node(Vector2 position) {
        this.parent = null;
        this.position = position;
        this.isFull = false;
    }

    public Node getParent() {
        return parent;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getF() {
        return g + h;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
