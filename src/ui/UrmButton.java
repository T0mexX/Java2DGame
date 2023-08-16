package ui;

import java.awt.image.BufferedImage;

import utils.Constants.UI.UrmButtonEnum;
import utils.Constants;
import utils.LoadSave;

public class UrmButton extends Button{
	private int buttonIdIndex;

	public UrmButton(int xPos, int yPos, int xSize, int ySize, UrmButtonEnum buttonEnum) {
		super(xPos, yPos, xSize, ySize);
		this.buttonIdIndex = buttonEnum.buttonIdIndex;
		loadImages();
	}
	
	private void loadImages() {
		BufferedImage tmpImg = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS_ATLAS);
		images = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			images[i] = tmpImg.getSubimage(Constants.UI.URM_BUTTON_IMG_SIZE * i, Constants.UI.URM_BUTTON_IMG_SIZE * buttonIdIndex, Constants.UI.URM_BUTTON_IMG_SIZE, Constants.UI.URM_BUTTON_IMG_SIZE);
		}
	}
}
