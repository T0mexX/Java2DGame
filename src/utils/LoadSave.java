package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "/playerAnimations1_1x1_32x32.png";
	public static final String LEVEL_ATLAS = null;
	
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
}
