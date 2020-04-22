export const pan = (scene: Phaser.Scene, camera: Phaser.Cameras.Scene2D.Camera): void => {
  // pan button is mouse wheel, the integer code is 1
  if (scene.input.activePointer.button === 1) {
    scene.input.off('pointerdown');

    const originX = scene.input.activePointer.worldX;
    const originY = scene.input.activePointer.worldY;
  
    scene.input.on('pointermove', () => {
      const x = originX - scene.input.activePointer.worldX;
      const y = originY - scene.input.activePointer.worldY;
    
      camera.setScroll(camera.scrollX + x, camera.scrollY + y);
    });
  
    scene.input.on('pointerup', () => {
      scene.input.off('pointermove');
      scene.input.off('pointerup');
      scene.input.on('pointerdown', () => pan(scene, camera));
    }); 
  }
};
