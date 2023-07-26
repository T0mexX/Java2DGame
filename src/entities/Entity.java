package entities;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import utils.Constants.Directions;
import utils.LoadSave;


public abstract class Entity {
	
	protected float x, y;
	protected float defaultSizeX, defaultSizeY; 
	protected float sizeX, sizeY;
	protected BufferedImage[][] animations;
	private int animTick, animIndex, frameIndex, animDelta = 20;
	private int numSpritesPerAnim = 1;
	protected Directions currentDirection = Directions.RIGHT; //default: right
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		updateAnimationTick();
	}
	
	public void render(Graphics g) {
		Toolkit.getDefaultToolkit().sync();
		if (currentDirection == Directions.LEFT) {
			g.drawImage(animations[animIndex][frameIndex], (int)(x + sizeX), (int)y, (int)-sizeX, (int)sizeY, null);			
		} else {
			g.drawImage(animations[animIndex][frameIndex], (int)x, (int)y, (int)sizeX, (int)sizeY, null);
		}
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
	
	protected void setAnimation(int animIndex, int numSpritesPerAnim, int animDelta) {
		int previousAnimIndex = this.animIndex;
		this.animDelta = animDelta;
//		System.out.println("Entity.setAnimation(): animIndex: " + animIndex + " | numSpritesPerAnim: " + numSpritesPerAnim);
		this.animIndex = animIndex;
		this.numSpritesPerAnim = numSpritesPerAnim;
		if (this.animIndex != previousAnimIndex) {
			frameIndex = 0;
			animTick = 0;
		}
		
	}
	
	protected void loadAnimations(String filePath,int rows, int columns, int sizeX, int sizeY) {
		BufferedImage img = LoadSave.GetSpriteAtlas(filePath);
		
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
	}
	
	
}
