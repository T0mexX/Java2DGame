package ui;

import java.awt.image.BufferedImage;


import gameStates.GameState;
import utils.Constants.UI.MenuConst;
import utils.LoadSave;

public class MenuButton extends Button{
	private int buttonIdIndex, buttonStateIndex;
	private GameState linkedState;

	public MenuButton(int xPos, int yPos, int buttonIdIndex, GameState gameState) {
		super(xPos, yPos, MenuConst.BTN_XSIZE, MenuConst.BTN_YSIZE);
		this.buttonIdIndex = buttonIdIndex;
		this.linkedState = gameState;
		loadImgs();
	}
	
	private void loadImgs() {
		BufferedImage tmpImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS_ATLAS);
		images = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			images[i] = tmpImg.getSubimage(i * MenuConst.BTN_IMG_XSIZE, buttonIdIndex * MenuConst.BTN_IMG_YSIZE, MenuConst.BTN_IMG_XSIZE, MenuConst.BTN_IMG_YSIZE);
		}
	}
	
	public void applyGameState() {
		GameState.currentState = linkedState;
	}
}
