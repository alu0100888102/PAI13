package controlador;

import vista.*;

public class Game {
	private FrameGame game;

	public Game(){
		setGame(new FrameGame());
		getGame().setLocationRelativeTo(null); // Center the frame
		getGame().setVisible(false);
	}
	
	public void start(){
		getGame().setVisible(true);
		getGame().start();
	}
	
	public FrameGame getGame() {
		return game;
	}

	public void setGame(FrameGame game) {
		this.game = game;
	}
	
}
