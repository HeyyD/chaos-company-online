package fi.chaocompany.online.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

    private TextureRegion sprite;

    private float x;
    private float y;

    public Player(Texture texture) {
        this.sprite = new TextureRegion(texture, 0, 0, PlayerConstants.FRAME_WIDTH_PIXELS, PlayerConstants.FRAME_HEIGHT_PIXELS);
        this.x = 400;
        this.y = 5;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.sprite, this.x, this.y, PlayerConstants.FRAME_WIDTH_PIXELS, PlayerConstants.FRAME_HEIGHT_PIXELS);
    }
}
