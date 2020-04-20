import * as Phaser from 'phaser';

export default class Tile extends Phaser.GameObjects.Sprite {
  public constructor(scene: Phaser.Scene, texture: string, x: number, y: number, frame: number) {
    super(scene, x, y, texture, frame);
  }

  /*
  public void draw(SpriteBatch batch, float x, float y, float width, float height) {
      batch.draw(texture, x, y, width, height);
  }

  @Override
  public void setNeighbours(Node[][] nodes) {
      ArrayList < Node > neighbours = new ArrayList<Node>();

      for (int x = -1; x <= 1; x++) {
          for (int y = -1; y <= 1; y++) {
              if (x != 0 && y != 0)
                  continue;

              int checkX = Math.round(this.getPosition().x) + x;
              int checkY = Math.round(this.getPosition().y) + y;

              if (checkX >= 0 && checkX < nodes.length && checkY >= 0 && checkY < nodes[0].length)
                  neighbours.add(nodes[checkX][checkY]);
          }
      }
      this.setNeighbours(neighbours);
  }
  */
}
