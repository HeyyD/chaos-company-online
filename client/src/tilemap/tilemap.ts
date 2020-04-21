import * as Phaser from 'phaser';

import Tile from './tile';

export class Tilemap {

  private TILESET_WIDHT: number = 2;
  private TILESET_HEIGHT: number = 5;

  private TILE_WIDTH_PIXELS: number = 128;
  private TILE_HEIGHT_PIXELS: number = 64;

  private tilemap: Tile[][] = [];

  public constructor(scene: Phaser.Scene, map: number[][]) {
    for (let i = 0; i < map.length; i++) {
      this.tilemap[i] = [];
      for (let j = map[i].length - 1; j >= 0; j--) {
        const x = (i * this.TILE_WIDTH_PIXELS / 2) + (j * this.TILE_WIDTH_PIXELS / 2);
        const y = (i * this.TILE_HEIGHT_PIXELS / 2) + (j * this.TILE_HEIGHT_PIXELS / 2);

        this.tilemap[i][j] = new Tile(scene, 'tilemap', x, y, map[i][j]);
      }
    }
  }

  /*
  private static final String LOG_TAG = TileMap.class.getSimpleName();

  private Tile[][] tiles;

  public TileMap(int[][] map, Texture texture) {
      TextureRegion[] tileSet = new TextureRegion[TileConstants.TILESET_WIDTH * TileConstants.TILESET_HEIGHT];

      // Create tile textures
      int index = 0;
      for (int i = 0; i < TileConstants.TILESET_WIDTH; i++) {
          for (int j = 0; j < TileConstants.TILESET_HEIGHT; j++) {
              tileSet[index] = new TextureRegion(texture, i * TileConstants.TILE_WIDTH_PIXELS, j * TileConstants.TILE_HEIGHT_PIXELS, TileConstants.TILE_WIDTH_PIXELS, TileConstants.TILE_HEIGHT_PIXELS);
              index++;
          }
      }

      // Create tiles of the map
      this.tiles = new Tile[map.length][map[0].length];
      for (int i = 0; i < map.length; i++) {
          for (int j = map[i].length - 1; j >= 0; j--) {

              float x = (i * TileConstants.TILE_WIDTH_PIXELS / 2.0f ) + (j * TileConstants.TILE_WIDTH_PIXELS / 2.0f);
              float y = - (i * TileConstants.TILE_HEIGHT_PIXELS / 2.0f) + (j * TileConstants.TILE_HEIGHT_PIXELS / 2.0f);

              tiles[i][j] = new Tile(tileSet[map[i][j]], x, y, new Vector2(i, j));
          }
      }

      for (int i = 0; i < this.tiles.length; i++) {
          for (int j = this.tiles[i].length - 1; j >= 0; j--) {
              tiles[i][j].setNeighbours(this.tiles);
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

  public Tile selectTile(float x, float y) {
      return tiles[(int) x][(int) y];
  }
  */
}
