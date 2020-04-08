package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fi.chaocompany.online.network.OnConnectListener;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.state.RoomState;

public class Main extends Game {

	private OnConnectListener listener;

	@Override
	public void create () {
		WebSocket socket = WebSocket.getInstance();
		this.listener = socket.registerOnConnectListener(() -> {
			Gdx.app.postRunnable(() -> {
				setScreen(new RoomState());
			});
		});
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
