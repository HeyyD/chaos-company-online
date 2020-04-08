package fi.chaocompany.online;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fi.chaocompany.online.network.Message;
import fi.chaocompany.online.network.WebSocket;
import fi.chaocompany.online.state.RoomState;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

public class Main extends Game {

	private final String LOG_TAG = Main.class.getSimpleName();

	@Override
	public void create () {
		WebSocket socket = WebSocket.getInstance();
		socket.registerOnConnectListener(() -> {
			socket.subscribe("/position", new StompFrameHandler() {
				@Override
				public Type getPayloadType(StompHeaders stompHeaders) {
					return Message.class;
				}

				@Override
				public void handleFrame(StompHeaders stompHeaders, Object o) {
					String msg = ((Message) o).getMsg();
					Gdx.app.log(LOG_TAG, "Received : " + msg);
				}
			});

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
