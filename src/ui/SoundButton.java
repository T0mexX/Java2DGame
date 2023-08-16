package ui;

import java.awt.image.BufferedImage;

import utils.Constants;
import utils.LoadSave;

public class SoundButton extends Button{
	private boolean muted = false;
	
	public SoundButton(int xPos, int yPos, int xSize, int ySize) {
		super(xPos, yPos, xSize, ySize);
		loadImgs();
	}
	
	private void loadImgs() {
		BufferedImage tmpImg = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTON_ATLAS);
		images = new BufferedImage[6];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				images[j + i * 3] = tmpImg.getSubimage(Constants.UI.SOUND_BUTTON_IMG_SIZE * j, Constants.UI.SOUND_BUTTON_IMG_SIZE * i, Constants.UI.SOUND_BUTTON_IMG_SIZE, Constants.UI.SOUND_BUTTON_IMG_SIZE);
			}
		}
	}
	
	@Override
	public void update() {
		if (!muted) {
			stateIndex = 0;
			if (mouseOver)
				stateIndex = 1;
			if (mousePressed)
				stateIndex = 2;
		}
		else {
			stateIndex = 3;
			if (mouseOver)
				stateIndex = 4;
			if (mousePressed)
				stateIndex = 5;
		}
	}
	
	public void toggleMuted() {
		muted = !muted;
	}
	
	public boolean isMuted() {
		return muted;
	}
}
