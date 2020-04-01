package fi.chaocompany.online.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileMap {

    private Tile[][] tiles;

    public TileMap (int[][] map, Texture texture) {
        TextureRegion[] tileSet = new TextureRegion[TileConstants.TILESET_WIDTH * TileConstants.TILESET_HEIGHT];

        // Create tile textures
        int index = 0;
        for (int i = 0; i < TileConstants.TILESET_WIDTH; i++) {
            for (int j = 0; j < TileConstants.TILESET_HEIGHT; j++) {
                tileSet[index] = new TextureRegion(texture,i * TileConstants.TILE_WIDTH_PIXELS, j * TileConstants.TILE_HEIGHT_PIXELS, TileConstants.TILE_WIDTH_PIXELS, TileConstants.TILE_HEIGHT_PIXELS);
                index++;
            }
        }

        // Create tiles of the map
        this.tiles = new Tile[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                tiles[i][j] = new Tile(tileSet[map[i][j]], i * TileConstants.TILE_WIDTH_PIXELS, j * TileConstants.TILE_HEIGHT_PIXELS);
            }
        }
    }

    public void drawMap(SpriteBatch batch) {
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        for (int x = 0; x < tiles.length; x++) {
            for (int y = tiles[x].length - 1; y >= 0; y--) {
                Tile tile = tiles[x][y];
                tile.draw(
                        batch,
                        tile.getX(),
                        tile.getY(),
                        TileConstants.TILE_WIDTH_PIXELS,
                        TileConstants.TILE_HEIGHT_PIXELS
                );
            }
        }
    }
}
