package fi.chaocompany.online.state;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import fi.chaocompany.online.map.Tile;
import fi.chaocompany.online.map.TileConstants;
import fi.chaocompany.online.map.TileMap;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.network.models.ServerGameObject;
import fi.chaocompany.online.pathfinding.Astar;
import fi.chaocompany.online.pathfinding.Node;
import fi.chaocompany.online.player.Player;
import fi.chaocompany.online.util.GameObject;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class RoomState implements Screen {

    private static final String LOG_TAG = RoomState.class.getSimpleName();

    private Map<Integer, GameObject> objects;

    private SpriteBatch batch;
    private TileMap tileMap;
    private OrthographicCamera camera;
    private Player player;

    public RoomState(int[][] map, Map<Integer, GameObject> objects) {
        // Set camera controls
        this.objects = objects;

        // Start listening to websocket messages
        WebSocket.getInstance().subscribe("/object", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return ServerGameObject.class;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                ServerGameObject object = (ServerGameObject) o;
                Gdx.app.postRunnable(() -> {
                    try {
                        GameObject gameObject = ((ServerGameObject) o).toGameObject();
                        objects.put(object.getId(), gameObject);
                        // Set the player object
                        if (gameObject instanceof Player && object.getSessionId().equals(WebSocket.getInstance().getId())) {
                            player = (Player) gameObject;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        WebSocket.getInstance().subscribe("/delete", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return Integer.class;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                int key = (int) o;
                objects.remove(key);
            }
        });

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
                if (player != null) {
                    Vector2 cell = screenToCell(x, y);
                    Vector2 playerCell = worldToCell(player.getX(), player.getY());

                    try {
                        Tile currentTile = tileMap.selectTile(playerCell.x, playerCell.y);
                        Tile targetTile = tileMap.selectTile(cell.x, cell.y);

                        Astar astar = new Astar();
                        Collection<Node> path = astar.findPath(currentTile, targetTile);
                        player.moveTo(path);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Gdx.app.error(LOG_TAG, "Tile not selectable");
                    }
                }
                return false;
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

            @Override
            public boolean keyUp(int keycode) {
                Gdx.app.log(LOG_TAG, "EXIT");
                if (keycode == Input.Keys.ESCAPE) {
                    for (Map.Entry<Integer, GameObject> entry : objects.entrySet()) {
                        if (entry.getValue().equals(player)) {
                            WebSocket.getInstance().send("/game/delete", entry.getKey());
                        }
                    }
                    Gdx.app.exit();
                }
                return super.keyUp(keycode);
            }
        });
        Gdx.input.setInputProcessor(multiplexer);

        batch = new SpriteBatch();

        Texture texture = new Texture("tileset.png");
        this.tileMap = new TileMap(map, texture);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false);

        Tile tile  = this.tileMap.selectTile(7, 3);
        new Player(new Texture("player_1.png"), new Vector2(tile.getX(), tile.getY()));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(this.camera.combined);
        batch.begin();
        this.tileMap.drawMap(batch);
        this.objects.values().forEach(o -> {
            o.update();
            o.draw(batch);
        });
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

    private Matrix4 getInverseMatrix() {
        //... and the inverse matrix
        Matrix4 invIsoTransform = new Matrix4(getIsometricTransform());
        return invIsoTransform.inv();
    }

    private Matrix4 getIsometricTransform() {
        //create the isometric transform
        Matrix4 isoTransform = new Matrix4();
        isoTransform.idt();
        isoTransform.translate(0.0f, 0.25f, 0.0f);
        isoTransform.scale((float)(Math.sqrt(2.0) / 2.0), (float)(Math.sqrt(2.0) / 4.0), 1.0f);
        isoTransform.rotate(0.0f, 0.0f, 1.0f, -45.0f);

        return isoTransform;
    }

    private Vector2 worldToCell(float x, float y) {
        float halfTileWidth = TileConstants.TILE_WIDTH_PIXELS * 0.5f;
        float halfTileHeight = TileConstants.TILE_HEIGHT_PIXELS * 0.5f;

        float row = (1.0f/2) * (x/halfTileWidth + y/halfTileHeight);
        float col = (1.0f/2) * (x/halfTileWidth - y/halfTileHeight);

        return  new Vector2((int)col,(int)row);
    }

    private Vector2 screenToWorld(float x, float y){
        Vector3 touch = new Vector3(x,y,0);
        this.camera.unproject(touch);
        touch.mul(getInverseMatrix());
        touch.mul(getIsometricTransform());
        return  new Vector2(touch.x,touch.y);
    }


    private Vector2 screenToCell(float x, float y) {
        Vector2 world = screenToWorld(x,y);
        world.y -= TileConstants.TILE_HEIGHT_PIXELS * 0.5f;
        return worldToCell(world.x,world.y);
    }
}
