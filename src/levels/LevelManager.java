package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;
import utils.Constants.LvlDataId;

public class LevelManager {

	private Game game;
	
	private BufferedImage[] levelSprites;
	
	private int currentLevelNum = 1;
	private Level level;
	
	public LevelManager(Game game) {
		this.game = game;
//		System.out.println("Default tile index: " + LevelData.GetLevelNullTileIndex(currentLevelNum));
	}
	
	public void setLvl(LvlDataId lvlDataId) {
		level = new Level(lvlDataId);		
		loadLevelSprites(lvlDataId);

	}
	
	private void loadLevelSprites(LvlDataId lvlDataId) {
		int rows = lvlDataId.tileAtlasId.ySize;
		int columns = lvlDataId.tileAtlasId.xSize;
		BufferedImage img = LoadSave.GetSpriteAtlas(lvlDataId.tileAtlasId.filePath);
		levelSprites = new BufferedImage[rows * columns];
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				levelSprites[j * columns + i] = img.getSubimage(Game.TILES_DEFAULT_SIZE * i, Game.TILES_DEFAULT_SIZE * j, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
			}
		}
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		int xTilesOffset = xLvlOffset / Game.TILES_SIZE;
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
			for (int i = 0 + xTilesOffset; i < Game.TILES_IN_WIDTH + xTilesOffset + 1; i++) {
				g.drawImage(levelSprites[level.getSpriteIndex(i, j)], i * Game.TILES_SIZE - xLvlOffset, j * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE,  null);				
			}
		}
		
		
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return level;
	}
}
