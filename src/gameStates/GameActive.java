package gameStates;

import java.awt.Color;
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
	private int xLvlOffset = 0;
	private int leftCameraBorder = (int)(0.4f * Game.GAME_WIDTH);
	private int rightCameraBorder = (int)(0.6f * Game.GAME_WIDTH);
	private int xLvlOffsetMax;

	public GameActive(Game game) {
		super(game);
		initClasses();
		
		int lvlWidthInTiles = levelManager.getCurrentLevel().getLevelData()[0].length;
		xLvlOffsetMax = lvlWidthInTiles * Game.TILES_SIZE - Game.GAME_WIDTH - 1;
	}
	
	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(500, 400);
		player.loadLevelData(levelManager.getCurrentLevel());
		pauseOverlay = new PauseOverlay(this);
	}
	
	@Override
	public void update() {
		if (paused)
			pauseOverlay.update();
		else {
			levelManager.update();
			player.update();
			moveCamera();
		}
	}
	
	private void moveCamera() {
		System.out.println("leftcambord: " + leftCameraBorder + " | rightcambord: " + rightCameraBorder + " | xlvloffsetmax: " + xLvlOffsetMax);
		int xPlayerPos = (int)player.getXPos();
		if (xPlayerPos < leftCameraBorder + xLvlOffset) 
			xLvlOffset = Math.max(xPlayerPos - leftCameraBorder, 0);
		else  if (xPlayerPos > rightCameraBorder + xLvlOffset)
			xLvlOffset = Math.min(xPlayerPos - rightCameraBorder, xLvlOffsetMax);
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
		else 
			player.setPos(e.getX(),e.getY());
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
	
	public void setPaused(boolean value) {
		paused = value;
	}
}
