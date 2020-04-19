import * as Phaser from 'phaser';

const config = {
  title: 'Starfall',
  width: 800,
  height: 600,
  parent: 'game',
  backgroundColor: '#18216D',
};
export class StarfallGame extends Phaser.Game {
  constructor(config) {
    super(config);
  }
}

window.onload = (): void => {
  new StarfallGame(config);
};