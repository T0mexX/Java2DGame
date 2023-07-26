package main;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import inputs.*;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private Game game;
	
	public GamePanel(Game game) {
		this.game = game; 
		mouseInputs = new MouseInputs(game);
		addKeyListener(new KeyboardInputs(game));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
		setPanelSize();
		
	}
	
//	private void importImg(String path) {
//		InputStream is = getClass().getResourceAsStream(path);
//		try {
//			BufferedImage img = ImageIO.read(is);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		System.out.println("size: " + GAME_WIDTH + ":" + GAME_HEIGHT);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}
}
