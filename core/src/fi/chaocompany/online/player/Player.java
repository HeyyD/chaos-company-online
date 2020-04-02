package fi.chaocompany.online.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private TextureRegion sprite;

    private float x;
    private float y;

    private float targetX;
    private float targetY;

    public Player(Texture texture) {
        this.sprite = new TextureRegion(texture, 0, 0, PlayerConstants.FRAME_WIDTH_PIXELS, PlayerConstants.FRAME_HEIGHT_PIXELS);
        this.x = 400;
        this.y = 5;

        this.targetX = this.x;
        this.targetY = this.y;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.sprite, this.x, this.y, PlayerConstants.FRAME_WIDTH_PIXELS, PlayerConstants.FRAME_HEIGHT_PIXELS);
    }

    public void moveTo(float x, float y) {
        this.targetX = x;
        this.targetY = y;
    }

    public void update() {
        float speed = 10f;
        Vector2 currentPos = new Vector2(this.x, this.y);
        Vector2 targetPos = new Vector2(this.targetX, this.targetY);
        float distance = currentPos.dst(targetPos);
        Vector2 direction = new Vector2(this.targetX - this.x, this.targetY - this.y).nor();
        Vector2 velocity = new Vector2(direction.x * speed, direction.y * speed);

        if (distance >= 5f) {
            this.x = currentPos.x + velocity.x;
            this.y = currentPos.y + velocity.y;
        } else if (distance != 0) {
            this.x = this.targetX;
            this.y = this.targetY;
        }
    }
}
