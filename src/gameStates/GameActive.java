package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import background.Background;
import background.BgConstants.BgBgEnum;
import background.BgConstants.BgBuilderFunctionEnum;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.LvlCompletedOverlay;
import ui.PauseOverlay;
import utils.Constants.LvlDataId;


public class GameActive extends State implements StateMethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private LvlCompletedOverlay lvlCompletedOverlay;
	private Background background;
	private boolean paused = false, lvlCompleted = false;
	private int xLvlOffset = 0;
	private int leftCameraBorder = (int)(0.4f * Game.GAME_WIDTH);
	private int rightCameraBorder = (int)(0.6f * Game.GAME_WIDTH);
	private int xLvlOffsetMax;

	public GameActive(Game game) {
		super(game);
		initClasses();
		loadMaxLvlOffset();
	}
	
	private void initClasses() {
		levelManager = new LevelManager(game);
		levelManager.setLvl(1);
		player = new Player(levelManager.getCurrentLevel().getSpawnPoint());
		player.loadLevelData(levelManager.getCurrentLevel());
		pauseOverlay = new PauseOverlay(this);
		lvlCompletedOverlay = new LvlCompletedOverlay(this);
		background = new Background(BgBuilderFunctionEnum.N1, levelManager.getCurrentLevel().getLevelData()[0].length * Game.TILES_SIZE);
	}
	
	private void loadMaxLvlOffset() {
		int lvlWidthInTiles = levelManager.getCurrentLevel().getLevelData()[0].length;
		xLvlOffsetMax = lvlWidthInTiles * Game.TILES_SIZE - Game.GAME_WIDTH - 1;

	}
	
	@Override
	public void update() {
		if (paused)
			pauseOverlay.update();
		else if (lvlCompleted)
			lvlCompletedOverlay.update();
		else {
			levelManager.update();
			player.update();
			moveCamera();
		}
	}
	
	private void moveCamera() {
		int xPlayerPos = (int)player.getXPos();
		if (xPlayerPos < leftCameraBorder + xLvlOffset) 
			xLvlOffset = Math.max(xPlayerPos - leftCameraBorder, 0);
		else  if (xPlayerPos > rightCameraBorder + xLvlOffset)
			xLvlOffset = Math.min(xPlayerPos - rightCameraBorder, xLvlOffsetMax);
	}

	@Override
	public void draw(Graphics g) {
		background.draw(g, xLvlOffset);
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}
		else if (lvlCompleted) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			lvlCompletedOverlay.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);
		if (lvlCompleted)
			lvlCompletedOverlay.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);
		if (lvlCompleted)
			lvlCompletedOverlay.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);
		if (lvlCompleted)
			lvlCompletedOverlay.mouseMoved(e);

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
			break;
		case KeyEvent.VK_T: //testing
			lvlCompleted = true;
			break;
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
	
	public void setLvlCompleted(boolean value) {
		lvlCompleted = value;
	}
	
	public void nextLvl() {
		levelManager.nextLvl();
		loadMaxLvlOffset();
		player = new Player(levelManager.getCurrentLevel().getSpawnPoint());
		player.loadLevelData(levelManager.getCurrentLevel());
	}
}
