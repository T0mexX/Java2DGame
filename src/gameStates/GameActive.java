package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;


public class GameActive extends State implements StateMethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;

	public GameActive(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(0, 0);
		player.loadLevelData(levelManager.getCurrentLevel());
		pauseOverlay = new PauseOverlay();
	}
	
	@Override
	public void update() {
		if (paused)
			pauseOverlay.update();
		else {
			levelManager.update();
			player.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		if (paused)
			pauseOverlay.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!paused)
			player.setPos(e.getX(),e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keypressedgameasctive");
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_CONTROL:
			player.setCrouch(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		case KeyEvent.VK_CONTROL:
			player.setCrouch(false);
			break;
		}			

	}
	
	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		player.clearAllInputs();
	}
}