package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.state.RoomState;

public class Main extends Game {
	@Override
	public void create () {
		setScreen(new RoomState());

		new Thread(() -> {
			try {
				WebSocket.getInstance();
			} catch (Exception e) {
				Gdx.app.log("MAIN", "FAILED TO CONNECT");
				e.printStackTrace();
			}
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
