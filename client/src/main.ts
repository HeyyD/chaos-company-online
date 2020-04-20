import * as Phaser from 'phaser';

function onPreload(): void {
  console.log('on preload');
}

function onCreate(): void {
  console.log('on create');
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