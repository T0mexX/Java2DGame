package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "/playerAnimations1_1x1_32x32.png";
	public static final String LEVEL_ATLAS = "/outside_sprites.png";
	public static final String LEVEL_1_DATA = "/level_1_data.png";
	public static final String MENU_BUTTONS_ATLAS = "/menuButtonsAtlas.png";
	public static final String MENU_BACKGROUND_IMG = "/menuBackgroundImg.png";
	public static final String PAUSE_BACKGROUND_IMG = "/pauseBackgroundImg.png";
	public static final String VOLUME_BUTTONS_ATLAS = "/volumeButtonsAtlas.png";
	public static final String SOUND_BUTTON_ATLAS = "/soundButtonAtlas.png";
	public static final String URM_BUTTONS_ATLAS = "/urmButtonsAtlas.png";
	
	public static BufferedImage GetSpriteAtlas(String filePath) {
		BufferedImage img = null; 
		
		InputStream is = LoadSave.class.getResourceAsStream(filePath);
		try {
			img = ImageIO.read(is);			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static int[][] GetLevelData(String filePath, int spriteArraySize, int nullTileIndex) {
		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(filePath);
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= spriteArraySize) 
					value = nullTileIndex;
				lvlData[j][i] = value;
			}
		}
		return lvlData;
	}
}
