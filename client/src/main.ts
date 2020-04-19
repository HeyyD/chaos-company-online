import * as Phaser from 'phaser';

function onPreload(): void {
  console.log('on preload');
  this.load.setBaseURL('http://labs.phaser.io');

  this.load.image('sky', 'assets/skies/space3.png');
  this.load.image('logo', 'assets/sprites/phaser3-logo.png');
  this.load.image('red', 'assets/particles/red.png');
}

function onCreate(): void {
  console.log('on create');

  this.add.image(400, 300, 'sky');

  const particles = this.add.particles('red');

  const emitter = particles.createEmitter({
    speed: 100,
    scale: { start: 1, end: 0, },
    blendMode: 'ADD',
  });

  const logo = this.physics.add.image(400, 100, 'logo');

  logo.setVelocity(100, 200);
  logo.setBounce(1, 1);
  logo.setCollideWorldBounds(true);

  emitter.startFollow(logo);
}

const config: Phaser.Types.Core.GameConfig = {
  title: 'Starfall',
  type: Phaser.AUTO,
  width: 800,
  height: 600,
  parent: 'game',
  backgroundColor: '#18216D',
  physics: {
    default: 'arcade',
    arcade: {
      gravity: { y: 200, },
    },
  },
  scene: {
    preload: onPreload,
    create: onCreate,
  },
};

export class StarfallGame extends Phaser.Game {
  constructor(config: Phaser.Types.Core.GameConfig) {
    super(config);
  }
}

window.onload = (): void => {
  new StarfallGame(config);
};