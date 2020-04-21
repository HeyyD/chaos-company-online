import * as Phaser from 'phaser';

import Tile from './tile';
import { TILE_WIDTH, TILE_HEIGHT } from './constants';

export class Tilemap {

  private tilemap: Tile[][] = [];

  public constructor(scene: Phaser.Scene, map: number[][]) {

    for (let i = 0; i < map.length; i++) {
      this.tilemap[i] = [];
      for (let j = map[i].length - 1; j >= 0; j--) {
        const x = (i * TILE_WIDTH / 2) + (j * TILE_WIDTH / 2);
        const y = (-(i * TILE_HEIGHT / 2) + (j * TILE_HEIGHT / 2)) * -1;

        this.tilemap[i][j] = new Tile(scene, 'tilemap', x, y, map[i][j]);
      }
    }
  }
}
