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
    [0, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7],
    [2, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 3],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0]
  ];

  const tilemap = new Tilemap(this, map);
  const camera: Phaser.Cameras.Scene2D.Camera = this.cameras.main;

  camera.centerOn(600, 0);
  camera.setZoom(0.5);
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