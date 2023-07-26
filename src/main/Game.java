package main;

import java.awt.Graphics;
import java.awt.Toolkit;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private final int FPS = 120; 
	private final int UPS = 200;
	private Thread gameThread;
	private Player player;
	private LevelManager levelManager;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN__HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN__HEIGHT;
	
	public Game() {
		player = new Player(0, 0, UPS);
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(this, gamePanel);
		levelManager = new LevelManager(this);
		gamePanel.requestFocus();
		startGameLoop();
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		player.update();
		levelManager.update();
	}
	
	public void render(Graphics g) {
		player.render(g);
		levelManager.draw(g);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getFPS() {
		return FPS;
	}
	
	public int getUPS() {
		return UPS;
	}
	
	public void windowFocusLost() {
		player.clearAllInputs();
	}
	
	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0/FPS;
		double timePerUpdate = 1000000000.0/UPS;
		
		long previousTime = System.nanoTime();
		long currentTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		
		double deltaU = 0;
		double deltaF = 0;
 		
		double lastCheck =  System.currentTimeMillis();
		
		while (true) {
			
			currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if (deltaF >= 1) {
				gamePanel.repaint();;
				frames++;
				deltaF--;
			}
			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}

		}
	}
	
	
	
	
}
