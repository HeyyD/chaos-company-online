import * as Phaser from 'phaser';
import { Tilemap } from './tilemap/tilemap';

function onPreload(): void {
  console.log('on preload');

  const TILE_WIDTH_PIXELS: number = 128;
  const TILE_HEIGHT_PIXELS: number = 64;

  this.load.spritesheet('tilemap', '../../assets/tileset.png', {
    frameWidth: TILE_WIDTH_PIXELS,
    frameHeight: TILE_HEIGHT_PIXELS
  });
}

function onCreate(): void {
  console.log('on create');

  const map: number[][] = [
    [0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8],
    [1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 6],
    [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 0]
  ];

  const tilemap = new Tilemap(this, map);
}

const config: Phaser.Types.Core.GameConfig = {
  title: 'Chaos Company',
  type: Phaser.AUTO,
  width: 800,
  height: 600,
  scene: {
    preload: onPreload,
    create: onCreate,
  },
};

window.onload = (): void => {
  new Phaser.Game(config);
};