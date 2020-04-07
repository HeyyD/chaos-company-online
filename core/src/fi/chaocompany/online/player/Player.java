package fi.chaocompany.online.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import fi.chaocompany.online.map.Tile;
import fi.chaocompany.online.pathfinding.Node;

import java.util.*;

public class Player {

    private TextureRegion sprite;

    private float x;
    private float y;

    private float targetX;
    private float targetY;

    private int direction;

    private List<Tile> path;

    private Map<Integer, Animation<TextureRegion>> animations;
    private float stateTime;

    public Player(Texture texture, Vector2 pos) {
        this.sprite = new TextureRegion(texture, 0, 0, PlayerConstants.FRAME_WIDTH_PIXELS, PlayerConstants.FRAME_HEIGHT_PIXELS);
        this.x = pos.x;
        this.y = pos.y;

        this.targetX = this.x;
        this.targetY = this.y;

        this.animations = initAnimations(texture);
        this.direction = 0;
        this.stateTime = 1f;
        this.path = new ArrayList<>();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.sprite, this.x, this.y, PlayerConstants.FRAME_WIDTH_PIXELS, PlayerConstants.FRAME_HEIGHT_PIXELS);
    }

    public void moveTo(Collection<Node> path) {
        this.path = new ArrayList<>();
        for (Node n: path) {
            this.path.add((Tile) n);
        }
        this.setTargetPosition();
    }

    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();

        Vector2 currentPos = new Vector2(this.x, this.y);
        Vector2 targetPos = new Vector2(this.targetX, this.targetY);

        if (!currentPos.equals(targetPos)) {
            this.move();
            this.sprite = this.animations.get(direction).getKeyFrame(stateTime, true);
        } else {
            this.sprite = this.animations.get(direction).getKeyFrames()[0];
        }
    }

    private void move() {
        float speed = 5f;
        Vector2 currentPos = new Vector2(this.x, this.y);
        Vector2 targetPos = new Vector2(this.targetX, this.targetY);
        float distance = currentPos.dst(targetPos);
        Vector2 dir = new Vector2(this.targetX - this.x, this.targetY - this.y).nor();
        Vector2 velocity = new Vector2(dir.x * speed, dir.y * speed);

        // Could this be better?
        if(dir.x > 0 && dir.y > 0){
            direction = 1;
        }else if(dir.x > 0){
            direction = 2;
        }else if(dir.x < 0 && dir.y > 0){
            direction = 0;
        }else{
            direction = 3;
        }

        if (distance >= 5f) {
            this.x = currentPos.x + velocity.x;
            this.y = currentPos.y + velocity.y;
        } else if (distance != 0) {
            this.x = this.targetX;
            this.y = this.targetY;
            if (this.path.size() > 0) {
                this.setTargetPosition();
            }
        }

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private void setTargetPosition() {
        Tile targetTile = this.path.remove(0);

        this.targetX = targetTile.getX();
        this.targetY = targetTile.getY();
    }

    private Map<Integer, Animation<TextureRegion>> initAnimations(Texture sheet) {
        TextureRegion[][] frames = TextureRegion.split(
                sheet,
                sheet.getWidth() / PlayerConstants.SPRITE_SHEET_WIDTH,
                sheet.getHeight() / PlayerConstants.SPRITE_SHEET_HEIGHT
        );

        Map<Integer, Animation<TextureRegion>> ret = new HashMap<>();
        for (int i = 0; i < PlayerConstants.SPRITE_SHEET_HEIGHT; i++) {
            ret.put(i, new Animation<TextureRegion>(1/4f, this.getAnimationRow(frames, i)));
        }

        return ret;
    }

    private TextureRegion[] getAnimationRow(TextureRegion[][] sheet, int row) {
        TextureRegion [] frames = new TextureRegion[PlayerConstants.SPRITE_SHEET_HEIGHT];
        int index = 0;

        for (int i = 0; i < PlayerConstants.SPRITE_SHEET_WIDTH; i++) {
            frames[index] = sheet[row][i];
            index++;
        }
        return frames;
    }
}
