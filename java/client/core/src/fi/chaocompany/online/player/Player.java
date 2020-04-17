package fi.chaocompany.online.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import fi.chaocompany.online.map.Tile;
import fi.chaocompany.online.pathfinding.Node;
import fi.chaocompany.online.util.GameObject;

import java.util.*;

public class Player extends GameObject {

    private final static String LOG_TAG = Player.class.getSimpleName();

    private float targetX;
    private float targetY;

    private Vector2 previousPos;

    private int direction;

    private List<Tile> path;

    private Map<Integer, Animation<TextureRegion>> animations;
    private float stateTime;

    public Player(Texture texture, float x, float y) {
        super(texture, x, y);
        this.targetX = getX();
        this.targetY = getY();

        this.previousPos = new Vector2(getX(), getY());

        this.animations = initAnimations(texture);
        this.direction = 0;
        this.stateTime = 1f;
        this.path = new ArrayList<>();
    }

    public Player(Texture texture, Vector2 pos) {
        super(texture, pos);
    }

    public void moveTo(Collection<Node> path) {
        this.path = new ArrayList<>();
        for (Node n: path) {
            this.path.add((Tile) n);
        }
        this.setTargetPosition();
    }

    @Override
    protected TextureRegion initSprite(Texture texture) {
        return new TextureRegion(texture, 0, 0, PlayerConstants.FRAME_WIDTH_PIXELS, PlayerConstants.FRAME_HEIGHT_PIXELS);
    }

    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();

        Vector2 currentPos = new Vector2(getX(), getY());
        Vector2 targetPos = new Vector2(this.targetX, this.targetY);

        if (!currentPos.equals(targetPos)) {
            this.move();
        }

        if (direction != getDirection() && !currentPos.equals(getPreviousPos())) {
            this.direction = getDirection();
        }
        if(currentPos.equals(getPreviousPos())) {
            setSprite(this.animations.get(direction).getKeyFrames()[0]);
        } else {
            setSprite(this.animations.get(direction).getKeyFrame(stateTime, true));
        }
    }

    private int getDirection() {
        Vector2 dir = new Vector2(getX() - getPreviousPos().x, getY() - getPreviousPos().y).nor();
        int direction;

        if(dir.x > 0 && dir.y > 0){
            direction = 1;
        }else if(dir.x > 0){
            direction = 2;
        }else if(dir.x < 0 && dir.y > 0){
            direction = 0;
        }else{
            direction = 3;
        }

        return direction;
    }

    private void move() {
        float speed = 5f;
        Vector2 currentPos = new Vector2(getX(), getY());
        Vector2 targetPos = new Vector2(this.targetX, this.targetY);
        float distance = currentPos.dst(targetPos);

        Vector2 dir = new Vector2(this.targetX - getX(), this.targetY - getY()).nor();
        Vector2 velocity = new Vector2(dir.x * speed, dir.y * speed);

        if (distance >= 5f) {
            setTargetPos(new Vector2(currentPos.x + velocity.x, currentPos.y + velocity.y));
        } else if (distance != 0) {
            setTargetPos(new Vector2(this.targetX, this.targetY));
            if (this.path.size() > 0) {
                this.setTargetPosition();
            }
        }
    }

    private void setTargetPosition() {
        if (this.path.size() > 0) {
            Tile targetTile = this.path.remove(0);

            this.targetX = targetTile.getX();
            this.targetY = targetTile.getY();
        }
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
