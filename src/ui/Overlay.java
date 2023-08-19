package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface Overlay {
	public void update();
	public void draw(Graphics g);
	
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	public void mouseMoved(MouseEvent e);
	public void mouseDragged(MouseEvent e);
	public void resetButtonsMousePressed();
}
