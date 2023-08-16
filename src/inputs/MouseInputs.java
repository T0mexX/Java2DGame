package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gameStates.GameState;
import main.Game;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{
	
	private Game game;
	
	public MouseInputs(Game game) {
		this.game = game;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameState.currentState) {
		case GAME_ACTIVE:
			game.getGameActive().mouseDragged(e);
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameState.currentState) {
		case GAME_ACTIVE:
			game.getGameActive().mouseMoved(e);
			break;
		case MENU:
			game.getMenu().mouseMoved(e);
			break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (GameState.currentState) {
		case GAME_ACTIVE:
			game.getGameActive().mouseClicked(e);
			break;
		case MENU:
			game.getMenu().mouseClicked(e);
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameState.currentState) {
		case GAME_ACTIVE:
			game.getGameActive().mousePressed(e);
			break;
		case MENU:
			game.getMenu().mousePressed(e);
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameState.currentState) {
		case GAME_ACTIVE:
			game.getGameActive().mouseReleased(e);
			break;
		case MENU:
			game.getMenu().mouseReleased(e);
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
