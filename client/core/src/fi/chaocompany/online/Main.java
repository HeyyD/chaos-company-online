package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.state.RoomState;

public class Main extends Game {
	@Override
	public void create () {
		setScreen(new RoomState());

		new Thread(() -> {
			WebSocket.getInstance();
		}).start();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
