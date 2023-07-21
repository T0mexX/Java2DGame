package main;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

	private Game game;
	private GamePanel gamePanel;
	
	public GameWindow(Game game, GamePanel gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(gamePanel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.pack();
		this.setVisible(true); //needs to be at the bottom
		this.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				game.windowFocusLost();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}
		});
	
	
	}
}
