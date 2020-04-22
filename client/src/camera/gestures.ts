export const pan = (scene: Phaser.Scene, camera: Phaser.Cameras.Scene2D.Camera): void => {
  scene.input.off('pointerdown');

  scene.input.on('pointermove', () => {
    const x = scene.input.activePointer.x;
    const y = scene.input.activePointer.y;
  
    camera.pan(x, y, 50);
  });

  scene.input.on('pointerup', () => {
    scene.input.off('pointermove');
    scene.input.off('pointerup');
    scene.input.on('pointerdown', () => pan(scene, camera));
  });
};
