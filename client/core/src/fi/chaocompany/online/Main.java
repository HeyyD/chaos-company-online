package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fi.chaocompany.online.network.Http;
import fi.chaocompany.online.state.RoomState;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class Main extends Game {

	private final String LOG_TAG = Main.class.getSimpleName();

	@Override
	public void create () {
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

		Http http = new Http();
		try {
			http.get("http://localhost:8080/map", (ResponseHandler<String>) response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String string = EntityUtils.toString(entity);
						Gdx.app.log(LOG_TAG, string);
						return string;
					}
					return null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		setScreen(new RoomState(map));
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
