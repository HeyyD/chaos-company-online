package fi.chaocompany.online;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import fi.chaocompany.online.map.TileMap;
import fi.chaocompany.online.player.Player;
import fi.chaocompany.online.player.PlayerConstants;

public class RoomState implements Screen {

    private static final String LOG_TAG = RoomState.class.getSimpleName();

    private SpriteBatch batch;
    private TileMap tileMap;
    private OrthographicCamera camera;
    private Player player;

    public RoomState() {
        // Set camera controls
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                float panSpeed = 1f;
                Vector3 pos = camera.position;
                pos.x -= deltaX * panSpeed;
                pos.y += deltaY * panSpeed;
                camera.position.set(pos);
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                // Move player
                Vector3 point = camera.unproject(new Vector3(x, y, 0));
                tileMap.selectTile(point.x, point.y);
                return super.tap(x, y, count, button);
            }
        }));

        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean scrolled(int amount) {

                float min = 1f;
                float max = 3;
                float zoomSpeed = 0.5f;

                float zoomAmount = camera.zoom + amount * zoomSpeed;
                if (zoomAmount > min && zoomAmount < max) {
                    camera.zoom = zoomAmount;
                }
                return super.scrolled(amount);
            }
        });
        Gdx.input.setInputProcessor(multiplexer);

        batch = new SpriteBatch();
        int[][] map = new int[][]{
                {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8},
                {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 7},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9, 6},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 0}
        };

        Texture texture = new Texture("tileset.png");
        this.tileMap = new TileMap(map, texture);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false);

        this.player = new Player(new Texture("player/player_1.png"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(this.camera.combined);

        this.player.update();

        batch.begin();
        this.tileMap.drawMap(batch);
        this.player.draw(batch);
        batch.end();

        this.camera.update();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
