package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "/res/playerAnimations1_1x1_32x32.png";
	public static final String LEVEL_ATLAS = "/res/outside_sprites.png";
	public static final String LEVEL_1_DATA = "/res/lvl1Data.png";
	public static final String MENU_BUTTONS_ATLAS = "/res/menuButtonsAtlas.png";
	public static final String MENU_BACKGROUND_IMG = "/res/menuBackgroundImg.png";
	public static final String PAUSE_BACKGROUND_IMG = "/res/pauseBackgroundImg.png";
	public static final String VOLUME_BUTTONS_ATLAS = "/res/volumeButtonsAtlas.png";
	public static final String SOUND_BUTTON_ATLAS = "/res/soundButtonAtlas.png";
	public static final String URM_BUTTONS_ATLAS = "/res/urmButtonsAtlas.png";
	public static final String LVL_COMPLETED_BACKGROUND_IMG = "/res/lvlCompletedBgImg.png";
	
	public static BufferedImage GetSpriteAtlas(String filePath) {
		BufferedImage img = null; 
		
		File file = new File(".");
		for(String fileNames : file.list()) System.out.println(fileNames);
		System.out.println(filePath);
//		if (filePath.equals(MENU_BUTTONS_ATLAS))
//			return null;
		
		
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
}
