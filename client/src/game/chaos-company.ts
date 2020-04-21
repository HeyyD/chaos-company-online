import * as Phaser from 'phaser';
import { Tilemap, } from '../tilemap/tilemap';
import { TILE_WIDTH, TILE_HEIGHT, } from '../tilemap/constants';

function onPreload(): void {
  console.log('on preload');

  this.load.spritesheet('tilemap', '../../assets/tileset.png', {
    frameWidth: TILE_WIDTH,
    frameHeight: TILE_HEIGHT,
  });
}

const startDrag = (scene: Phaser.Scene, camera: Phaser.Cameras.Scene2D.Camera): void => {
  scene.input.off('pointerdown');

  scene.input.on('pointermove', () => {
    const x = scene.input.activePointer.x;
    const y = scene.input.activePointer.y;
  
    camera.pan(x, y, 50);
  });

  scene.input.on('pointerup', () => {
    scene.input.off('pointermove');
    scene.input.off('pointerup');
    scene.input.on('pointerdown', () => startDrag(scene, camera));
  });
};

function onCreate(): void {
  console.log('on create');

  const map: number[][] = [
    [0, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 7,],
    [2, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 5,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 3,],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0,],
  ];

  const tilemap = new Tilemap(this, map);
  const camera: Phaser.Cameras.Scene2D.Camera = this.cameras.main;

  this.input.on('pointerdown', () => startDrag(this, camera));

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

export default class ChaosCompany extends Phaser.Game {
  constructor() {
    super(config);
  }
}