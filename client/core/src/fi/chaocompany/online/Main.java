package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import fi.chaocompany.online.network.Http;
import fi.chaocompany.online.network.MapMessage;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.state.RoomState;
import fi.chaocompany.online.util.GameObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.util.Map;

public class Main extends Game {

	private final String LOG_TAG = Main.class.getSimpleName();

	private static final String api = "http://localhost:8080/api";

	private int[][] map;
	private Map<Integer, GameObject> objects;

	@Override
	public void create () {
		WebSocket socket = WebSocket.getInstance();
		socket.registerOnConnectListener(() -> {
			this.loadMap();
			this.loadObjects();
		});

		new Thread(() -> {
			while (isLoading()) {
				try {
					Thread.sleep(200);
					Gdx.app.log(LOG_TAG, "Loading...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Gdx.app.postRunnable(() -> {
				setScreen(new RoomState(this.map, this.objects));
			});
		}).start();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

	private void loadObjects() {
		Http http = new Http();
		try {
			http.get(api + "/game", (ResponseHandler<Map<Integer, GameObject>>) response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String string = EntityUtils.toString(entity);
						Map map = new Gson().fromJson(string, Map.class);
						this.objects = map;
						return map;
					}
					return null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadMap() {
		Http http = new Http();
		try {
			http.get(api + "/map", (ResponseHandler<MapMessage>) response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String string = EntityUtils.toString(entity);
						MapMessage map = new Gson().fromJson(string, MapMessage.class);
						this.map = map.getMap();
						return map;
					}
					return null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isLoading() {
		return this.map == null || this.objects == null;
	}
}
