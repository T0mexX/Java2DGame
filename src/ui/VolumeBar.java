package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.Constants;
import utils.LoadSave;

public class VolumeBar extends Button{
	private BufferedImage background;
	private int xBgPos, yBgPos;
	private int xBgPosForDraw, yBgPosForDraw, xBgSize, yBgSize;
	private float percentage;
	private int xButtonPosMax, xButtonPosMin;
	
	
	public VolumeBar(int xPos, int yPos, int xSize, int ySize, float percentage) {
		super((xPos - xSize / 2) + (int)(xSize * percentage), yPos, (int)(xSize * Constants.UI.VOLUME_BUTTON_XSIZE_ON_BARXSIZE), (int)(ySize * Constants.UI.VOLUME_BUTTON_YSIZE_ON_BARYSIZE));
		System.out.println("VolumeButton: " + super.toString());
		xBgSize = xSize;
		yBgSize = ySize;
		xBgPos = xPos;
		yBgPos = yPos;
		xBgPosForDraw = xPos - xBgSize / 2;
		yBgPosForDraw = yPos - yBgSize / 2;
		this.percentage = percentage;
		xButtonPosMax = xBgPosForDraw;
		xButtonPosMin = xBgPosForDraw + xBgSize;
		loadImgs();
	}
	
	private void loadImgs() {
		BufferedImage tmpImg = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS_ATLAS);
		images = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			images[i] = tmpImg.getSubimage(i * Constants.UI.VOLUME_BUTTON_IMG_XSIZE, 0, Constants.UI.VOLUME_BUTTON_IMG_XSIZE, tmpImg.getHeight());
		}
		background = tmpImg.getSubimage(3 * Constants.UI.VOLUME_BUTTON_IMG_XSIZE, 0, tmpImg.getWidth() - 3 * Constants.UI.VOLUME_BUTTON_IMG_XSIZE, tmpImg.getHeight());
	}
	
	@Override 
	public void draw(Graphics g) {
		g.drawImage(background, xBgPosForDraw, yBgPosForDraw, xBgSize, yBgSize, null);
		super.draw(g);
	}
	
	public void setVolumePercentage(int xPos) {
		if (xPos <= xButtonPosMin) {
			super.xPos = xButtonPosMin;
			super.xPosForDraw = xPos - xSize/2;
			percentage = 0.0f;
		}
		else if (xPos >= xButtonPosMax) {
			super.xPos = xButtonPosMax;
			super.xPosForDraw = xPos - xSize/2;
			percentage = 1.0f;
		}
		else {
			percentage = xPos - xButtonPosMin / xSize;
			super.xPos = (int)(xButtonPosMin + xBgSize * percentage);
			super.xPosForDraw = xPos - xSize/2;
		}
	}
	
	public float getVolumePercentage() {
		return percentage;
	}
}
