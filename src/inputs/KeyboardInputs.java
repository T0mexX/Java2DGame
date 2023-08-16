package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameStates.GameState;
import main.Game;
import utils.Constants.Directions;

public class KeyboardInputs implements KeyListener{
	
	private Game game;
	private boolean up = false, left = false, down = false, right = false, space = false, lCtrl = false; 
	private float x = 0, y = 0; 
	
	public KeyboardInputs(Game game) {
		
		this.game = game;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (GameState.currentState) {
		case MENU:
			game.getMenu().keyPressed(e);
			break;
		case GAME_ACTIVE:
			game.getGameActive().keyPressed(e);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (GameState.currentState) {
		case MENU:
			game.getMenu().keyReleased(e);
			break;
		case GAME_ACTIVE:
			game.getGameActive().keyReleased(e);
			break;
		}
	}
	

}
