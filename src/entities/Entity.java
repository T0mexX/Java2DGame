package entities;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public abstract class Entity {
	
	protected float x, y;
	protected float defaultSizeX, defaultSizeY; 
	protected float sizeX, sizeY;
	protected BufferedImage[][] animations;
	private int animTick, animIndex, frameIndex, animDelta = 30;
	private int numSpritesPerAnim = 1;
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		updateAnimationTick();
	}
	
	public void render(Graphics g) {
		Toolkit.getDefaultToolkit().sync();
		g.drawImage(animations[animIndex][frameIndex], (int)x, (int)y, (int)sizeX, (int)sizeY, null);
	}
	
	protected void updateAnimationTick() {
		animTick++;
		if (animTick >= animDelta) {
			animTick = 0;
			frameIndex++;
			if (frameIndex >= numSpritesPerAnim) {
				frameIndex = 0;
			}
		}
		
	}
	
	public void updatePos(float xDelta, float yDelta) {
		this.x += xDelta;
		this.y += yDelta;
//		System.out.println("x: " + x + "(" + xDelta + ") | y: " + y + "(" + yDelta + ")");
		
	}
	
	public void setPos(int xDelta, int yDelta) {
		this.x = xDelta;
		this.y = yDelta;
	}
	
	protected void setAnimation(int animIndex, int numSpritesPerAnim) {
		System.out.println("Entity.setAnimation(): animIndex: " + animIndex + " | numSpritesPerAnim: " + numSpritesPerAnim);
		this.animIndex = animIndex;
		this.numSpritesPerAnim = numSpritesPerAnim;
	}
	
	protected void loadAnimations(String path, int rows, int columns, int sizeX, int sizeY) {
		InputStream is = getClass().getResourceAsStream(path);
		System.out.println(path);
		try {
			BufferedImage img = ImageIO.read(is);
			
			animations = new BufferedImage[rows][columns];
			for (int j = 0; j < rows; j++) {
				for (int i = 0; i < columns; i++) {
					animations[j][i] = img.getSubimage(i * sizeX, j * sizeY, sizeX, sizeY);
				}
			}
			this.defaultSizeX = sizeX;
			this.sizeX = sizeX;
			this.defaultSizeY = sizeY;
			this.sizeY = sizeY;
			
			
		} catch (IOException e) {
			System.out.println("CIAOOO");
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
