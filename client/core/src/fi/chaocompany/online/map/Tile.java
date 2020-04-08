package fi.chaocompany.online.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import fi.chaocompany.online.pathfinding.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tile extends Node {

    private TextureRegion texture;
    private float x;
    private float y;

    public Tile(TextureRegion texture, float x, float y, Vector2 mapPosition) {
        super(mapPosition);
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch, float x, float y, float width, float height) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void setNeighbours(Node[][] nodes) {
        ArrayList<Node> neighbours = new ArrayList<Node>();

        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){
                if(x != 0 && y != 0)
                    continue;

                int checkX = Math.round(this.getPosition().x) + x;
                int checkY = Math.round(this.getPosition().y) + y;

                if(checkX >= 0 && checkX < nodes.length && checkY >= 0 && checkY < nodes[0].length)
                    neighbours.add(nodes[checkX][checkY]);
            }
        }
        this.setNeighbours(neighbours);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
