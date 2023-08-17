package background;

import java.awt.image.BufferedImage;
import java.util.Random;


import utils.LoadSave;
import main.Game;

public class BgConstants {
	 
	public enum BgBgEnum {
		DEFAULT("/gameActiveBg.png");
		
		public final BufferedImage image;
		private BgBgEnum(String path) {
			image = LoadSave.GetSpriteAtlas(path);
		}
	}
	
	public enum BgEntityEnum {
		SMALL_CLOUDS("/smallClouds.png"),
		BIG_CLOUDS("/bigClouds.png");
		
		public final BufferedImage image;
		
		BgEntityEnum(String path) {
			image = LoadSave.GetSpriteAtlas(path);
		}
	}
	
	public enum BgBuilderFunctionEnum {
		N1((bg, lvlWidth) -> {
			bg.background = BgBgEnum.DEFAULT.image;
			int yBigCloudsPos = 400;
			int xBigCloudsSize = (int)(448 * Game.SCALE);
			int yBigCloudsSize = (int)(101 * Game.SCALE);
			float offsetPercentage = 0.4f;
			for (int i = 0; i < lvlWidth * offsetPercentage + xBigCloudsSize; i += xBigCloudsSize) {
				bg.bgEntities.add(new BackgroundEntity(i, yBigCloudsPos, xBigCloudsSize, yBigCloudsSize, BgEntityEnum.BIG_CLOUDS, offsetPercentage));
			}
			
			Random rand = new Random();
			int xSmallCloudsSize = (int)(150);
			int ySmallCloudsSize = (int)(75);
			offsetPercentage = 0.7f;
			for (int i = 0; i < lvlWidth * offsetPercentage + 500; i += rand.nextInt(250, 1000)) {
				bg.bgEntities.add(new BackgroundEntity(i, rand.nextInt((int)(50 * Game.SCALE), (int)(150 * Game.SCALE)), xSmallCloudsSize, ySmallCloudsSize, BgEntityEnum.SMALL_CLOUDS, offsetPercentage));
			}
			System.out.println("bgVector.size: " + bg.bgEntities.size());
				
		});
		
		BgConstructor bgConstructor;
		private BgBuilderFunctionEnum(BgConstructor bgConstructor) {
			this.bgConstructor = bgConstructor;
		}
	}
}
