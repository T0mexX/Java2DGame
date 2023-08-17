package background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import background.BgConstants.BgBuilderFunctionEnum;
import main.Game;

public class Background {
	public BufferedImage background;
	public Vector <BackgroundEntity> bgEntities = new Vector<>();
	
	public Background(BgBuilderFunctionEnum bgBuilderFunctionId, int lvlWidth) {
		bgBuilderFunctionId.bgConstructor.buildBackground(this, lvlWidth);
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		for (BackgroundEntity bgEntity: bgEntities) {
			g.drawImage(bgEntity.entityId.image, (int)(bgEntity.xPos - xLvlOffset * bgEntity.offsetPercentage), bgEntity.yPos, bgEntity.xSize, bgEntity.ySize, null);
		}
	}
}
