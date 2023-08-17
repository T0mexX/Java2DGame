package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;

import main.Game;
import structs.Rect;
import utils.Constants.Directions;
import utils.LoadSave;


public abstract class Entity {
	
	protected float x, y;
	protected int defaultSizeX, defaultSizeY; 
	protected float sizeX, sizeY;
	protected float halfSizeX, halfSizeY;
	protected float halfHitboxSizeX;
	protected float halfHitboxSizeY;
	protected BufferedImage[][] animations;
	private int animTick, animIndex, frameIndex, animDelta = 20;
	private int numSpritesPerAnim = 1;
	protected Directions currentDirection = Directions.RIGHT; //default: right
	
	protected Rect hitboxRect;
	
	public Entity(float x, float y, int defaultSizeX, int defaultSizeY, float hitboxSizeX, float hitboxSizeY) {
		this.x = x;
		this.y = y;
		this.defaultSizeX = defaultSizeX;
		this.defaultSizeY = defaultSizeY;
		this.sizeX = defaultSizeX * Game.SCALE;
		this.sizeY = defaultSizeY * Game.SCALE;
		this.halfSizeX = this.sizeX / 2;
		this.halfSizeY = this.sizeY / 2;
		this.halfHitboxSizeX = hitboxSizeX * Game.SCALE / 2;
		this.halfHitboxSizeY = hitboxSizeY * Game.SCALE / 2;
		hitboxRect = new Rect(x - halfHitboxSizeX, y - halfHitboxSizeY, hitboxSizeX * Game.SCALE, hitboxSizeY * Game.SCALE);
	}
	
	public void update() {
		updateAnimationTick();
	}
	
	public void render(Graphics g, int xLvlOffset) {
		Toolkit.getDefaultToolkit().sync();
		if (currentDirection == Directions.LEFT) {
			g.drawImage(animations[animIndex][frameIndex], (int)(x + halfSizeX - xLvlOffset), (int)(y - halfSizeY), (int)-sizeX, (int)sizeY, null);			
		} else {
			g.drawImage(animations[animIndex][frameIndex], (int)(x - halfSizeX - xLvlOffset), (int)(y - halfSizeY), (int)sizeX, (int)sizeY, null);
		}
		drawHitbox(g, xLvlOffset);
	}
	
	protected void drawHitbox(Graphics g, int xLvlOffset) {
		//for debugging
		g.setColor(Color.RED);
		g.drawRect((int)hitboxRect.pos.x - xLvlOffset, (int)hitboxRect.pos.y, (int)hitboxRect.size.x, (int)hitboxRect.size.y);
	}
	
	protected void updateHitbox() {
		hitboxRect.pos.x = x - halfHitboxSizeX;
		hitboxRect.pos.y = y - halfHitboxSizeY;
	}
	
	public Rect getHitbox() {
		return hitboxRect;
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
		
	}
	
	public void setPos(int xDelta, int yDelta) {
		this.x = xDelta;
		this.y = yDelta;
	}
	
	protected void setAnimation(int animIndex, int numSpritesPerAnim, int animDuration) {
		int previousAnimIndex = this.animIndex;
		animDelta = animDuration / numSpritesPerAnim;
		this.animIndex = animIndex;
		this.numSpritesPerAnim = numSpritesPerAnim;
		if (this.animIndex != previousAnimIndex) {
			frameIndex = 0;
			animTick = 0;
		}
		
	}
	
	protected void loadAnimations(String filePath,int rows, int columns) {
		BufferedImage img = LoadSave.GetSpriteAtlas(filePath);
		
		animations = new BufferedImage[rows][columns];
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				animations[j][i] = img.getSubimage(i * defaultSizeX, j * defaultSizeY, defaultSizeX, defaultSizeY);
			}
		}
	}
	
	public float getXPos() {
		return x;
	}
	public float getYPos() {
		return y;
	}
}
