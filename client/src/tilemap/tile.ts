import * as Phaser from 'phaser';

export default class Tile extends Phaser.GameObjects.Sprite {
  public constructor(scene: Phaser.Scene, texture: string, x: number, y: number, frame: number) {
    super(scene, x, y, texture, frame);
    scene.add.existing(this);
  }
}
