package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import fi.chaocompany.online.network.Http;
import fi.chaocompany.online.state.RoomState;

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
			http.get();
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
