package fi.chaocompany.online;

import com.badlogic.gdx.Game;

public class Main extends Game {
	@Override
	public void create () {
		setScreen(new RoomState());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}