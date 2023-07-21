package main;
import javax.swing.JFrame;

public class GameWindow extends JFrame{

	public GameWindow(GamePanel gamePanel) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(gamePanel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.pack();
		this.setVisible(true); //needs to be at the bottom
		
	
	
	}
}
