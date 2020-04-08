package fi.chaocompany.online.pathfinding;

import com.badlogic.gdx.Gdx;

import java.util.*;

public class Astar {

    public static final String LOG_TAG = Astar.class.getSimpleName();

    private List<Node> openSet;
    private Set<Node> closedSet;

    public Astar() {
        this.openSet = new ArrayList<>();
        this.closedSet = new HashSet<>();
    }

    public Collection<Node> findPath(Node startNode, Node targetNode) {
        openSet.add(startNode);

        while (openSet.size() > 0) {
            Node currentNode = openSet.get(0);
            for (int i = 1; i < openSet.size(); i++) {
                if(openSet.get(i).getF() < currentNode.getF() || (openSet.get(i).getF() == currentNode.getF() && openSet.get(i).getH() < currentNode.getH())){
                    currentNode = openSet.get(i);
                }
            }

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if (currentNode == targetNode) {
                return retracePath(startNode, targetNode);
            }

            for (Node n: currentNode.getNeighbours()) {
                if (n.isFull() || closedSet.contains(n)) {
                    continue;
                }

                int cost = currentNode.getG() + getDistance(currentNode, n);

                if (cost < n.getG() || !openSet.contains(n)) {
                    n.setG(cost);
                    n.setH(getDistance(n, targetNode));
                    n.setParent(currentNode);

                    if (!openSet.contains(n)) {
                        openSet.add(n);
                    }
                }
            }

        }
        Gdx.app.error(LOG_TAG, "Could not find path");
        return Collections.emptyList();
    }

    private Collection<Node> retracePath(Node startNode, Node targetNode) {
        ArrayList<Node> path = new ArrayList<Node>();
        Node currentNode = targetNode;

        while (currentNode != startNode) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    private int getDistance(Node currentNode, Node targetNode){
        int distanceX = Math.abs(Math.round(currentNode.getPosition().x) - Math.round(targetNode.getPosition().x));
        int distanceY = Math.abs(Math.round(currentNode.getPosition().y) - Math.round(targetNode.getPosition().y));

        if(distanceX > distanceY)
            return 14 * distanceY + 10 * (distanceX - distanceY);

        return 14 * distanceX + 10 * (distanceY - distanceX);
    }
}
