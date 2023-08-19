package main;

import java.awt.Graphics;

import gameStates.GameActive;
import gameStates.GameState;
import gameStates.Menu;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private final int FPS = 120; 
	private final int UPS = 200;
	private Thread gameThread;
	
	private Menu menu;
	private GameActive gameActive;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2.0f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public Game() {		
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(this, gamePanel);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		startGameLoop();
	}
	
	private void initClasses() {
		menu = new Menu(this);
		System.out.println("menu exists: " + menu != null);
		gameActive = new GameActive(this);
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		
		switch(GameState.currentState) {
		case MENU:
			menu.update();
			break;
		case GAME_ACTIVE:
			gameActive.update();
			break;
		case OPTIONS:
		case QUIT:
			System.exit(0);
			break; 
		}
	}
	
	public void render(Graphics g) {
		switch(GameState.currentState) {
		case MENU:
			menu.draw(g);
			break;
		case GAME_ACTIVE:
			gameActive.draw(g);
			break;
		}
	}
	
	public int getFPS() {
		return FPS;
	}
	
	public int getUPS() {
		return UPS;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public GameActive getGameActive() {
		return gameActive;
	}
	
	public void windowFocusLost() {
		switch (GameState.currentState) {
		case GAME_ACTIVE:
			gameActive.windowFocusLost();
		default:
			break; 
		}
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
