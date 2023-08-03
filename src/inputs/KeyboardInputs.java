package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
//		System.out.println("keyPressed()");
//		game.getPlayer().setMoving(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			if (!up) {
				game.getPlayer().setUp(true);
				up = true;
			}
			break;
		case KeyEvent.VK_A:
			if (!left) {
				game.getPlayer().setLeft(true);
				left = true;
			}
			break;
		case KeyEvent.VK_S:
			if (!down) {
				game.getPlayer().setDown(true);
				down = true;
			}
			break;
		case KeyEvent.VK_D:
			if (!right) {
				game.getPlayer().setRight(true);
				right = true;
			}
			break;
		case KeyEvent.VK_SPACE:
			if (!space) {
				game.getPlayer().setJump(true);
				System.out.println("setJump()");
				space = true;
			}
			break;
		case KeyEvent.VK_CONTROL:
			if (!lCtrl) {
				game.getPlayer().setCrouch(true);
				System.out.println("setCrouch()");
				lCtrl = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("keyReleased()");
//		game.getPlayer().clearMoving(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			game.getPlayer().setUp(false);
			up = false;
			break;
		case KeyEvent.VK_A:
			game.getPlayer().setLeft(false);
			left = false;
			break;
		case KeyEvent.VK_S:
			game.getPlayer().setDown(false);
			down = false;
			break;
		case KeyEvent.VK_D:
			game.getPlayer().setRight(false);
			right = false;
			break;
		case KeyEvent.VK_SPACE:
			game.getPlayer().setJump(false);
			space = false;
			break;
		case KeyEvent.VK_CONTROL:
			game.getPlayer().setCrouch(false);
			lCtrl = false;
			break;
		}			
	}

}
