package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import com.google.gson.Gson;
import fi.chaocompany.online.network.Http;
import fi.chaocompany.online.network.MapMessage;
import fi.chaocompany.online.state.RoomState;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class Main extends Game {

	private final String LOG_TAG = Main.class.getSimpleName();

	private static final String api = "http://localhost:8080/api";

	@Override
	public void create () {
		Http http = new Http();
		try {
			http.get(api + "/map", (ResponseHandler<MapMessage>) response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String string = EntityUtils.toString(entity);
						MapMessage map = new Gson().fromJson(string, MapMessage.class);
						setScreen(new RoomState(map.getMap()));
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
		/*
		WebSocket socket = WebSocket.getInstance();
		socket.registerOnConnectListener(() -> {
			socket.subscribe("/map", new StompFrameHandler() {
				@Override
				public Type getPayloadType(StompHeaders stompHeaders) {
					return MapMessage.class;
				}

				@Override
				public void handleFrame(StompHeaders stompHeaders, Object o) {
					int[][] map = ((MapMessage) o).getMap();
					Gdx.app.postRunnable(() -> {
						setScreen(new RoomState(map));
					});
				}
			});
			socket.send("/game/map", new MapMessage());
		});
		 */
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
