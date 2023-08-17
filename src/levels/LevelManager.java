package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;
import utils.Constants.LevelData;

public class LevelManager {

	private Game game;
	
	private BufferedImage[] levelSprites;
	
	private int currentLevelNum = 1;
	private Level level;
	
	public LevelManager(Game game) {
		
		this.game = game;
		loadLevelSprites();
//		System.out.println("Default tile index: " + LevelData.GetLevelNullTileIndex(currentLevelNum));
		level = new Level(LoadSave.GetLevelData(LoadSave.LEVEL_1_DATA, LevelData.GetLevelTilesArraySize(currentLevelNum), LevelData.GetLevelNullTileIndex(currentLevelNum)), LevelData.GetLevelNullTileIndex(currentLevelNum), LevelData.GetLevelTilesArraySize(currentLevelNum), LevelData.GetLevelMaxSolidTilesIndex(currentLevelNum));		
	}
	
	private void loadLevelSprites() {
		int rows = LevelData.GetLevelTilesImageRows(currentLevelNum);
		int columns = LevelData.GetLevelTilesImageColumns(currentLevelNum);
//		System.out.println("LvlTilesImg: rows: " + rows + " | columns: " + columns + " | arraySize: " + LevelData.GetLevelTilesArraySize(currentLevelNum));
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprites = new BufferedImage[LevelData.GetLevelTilesArraySize(currentLevelNum)];
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				levelSprites[j * columns + i] = img.getSubimage(Game.TILES_DEFAULT_SIZE * i, Game.TILES_DEFAULT_SIZE * j, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
			}
		}
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		System.out.println("LevelManager.draw(): xLvlOffset: " + xLvlOffset);
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
