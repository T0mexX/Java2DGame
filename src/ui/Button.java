package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import structs.Rect;

public class Button {
	protected BufferedImage[] images;
	protected int xPos, yPos, xSize, ySize, stateIndex = 0;
	protected int xPosForDraw, yPosForDraw;
	protected boolean mouseOver = false, mousePressed = false;
	protected Rect hitbox;
	
	public Button(int xPos, int yPos, int xSize, int ySize) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xSize = xSize;
		this.ySize = ySize;
		xPosForDraw = xPos - xSize / 2;
		yPosForDraw = yPos - ySize / 2;
		hitbox = new Rect(xPosForDraw, yPosForDraw, xSize, ySize);
	}
	
	public void update() {
		stateIndex = 0;
		if (mouseOver)
			stateIndex = 1;
		if (mousePressed)
			stateIndex = 2;
	}
	
	public void draw(Graphics g) {
		g.drawImage(images[stateIndex], xPosForDraw, yPosForDraw, xSize, ySize, null);
	}
	
	public Rect getHitbox() {
		return hitbox;
	}
	
	public void setMouseOver(boolean value) {
		mouseOver = value;
	}
	
	public void setMousePressed(boolean value) {
		mousePressed = value;
	}
	
	public boolean getMousePressed() {
		return mousePressed;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
}
