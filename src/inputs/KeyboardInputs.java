package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import utils.Constants.Directions;

public class KeyboardInputs implements KeyListener{
	
	private Game game;
	private boolean up = false, left = false, down = false, right = false; 
	private float x = 0, y = 0; 
	
	public KeyboardInputs(Game game) {
		
		this.game = game;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("keyPressed()");
//		game.getPlayer().setMoving(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			if (!up) {
				game.getPlayer().setUp();
				up = true;
			}
			break;
		case KeyEvent.VK_A:
			if (!left) {
				game.getPlayer().setLeft();
				left = true;
			}
			break;
		case KeyEvent.VK_S:
			if (!down) {
				game.getPlayer().setDown();
				down = true;
			}
			break;
		case KeyEvent.VK_D:
			if (!right) {
				game.getPlayer().setRight();
				right = true;
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("keyReleased()");
//		game.getPlayer().clearMoving(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			game.getPlayer().clearUp();
			up = false;
			break;
		case KeyEvent.VK_A:
			game.getPlayer().clearLeft();
			left = false;
			break;
		case KeyEvent.VK_S:
			game.getPlayer().clearDown();
			down = false;
			break;
		case KeyEvent.VK_D:
			game.getPlayer().clearRight();
			right = false;
			break;
		}			
	}

}
