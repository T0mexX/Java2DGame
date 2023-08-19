package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import structs.Vector2D;
import utils.LoadSave;
import utils.Constants.LvlDataId;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprites;
	private Level[] levels;
	private Level currentLevel;
	
	public LevelManager(Game game) {
		this.game = game;
		levels = new Level[LvlDataId.values().length + 1];
		
		for (LvlDataId lvlDataId : LvlDataId.values()) {
			levels[lvlDataId.lvlNum] = new Level(lvlDataId);
		}
	}
	
	public void setLvl(int lvlNum) {
		currentLevel = levels[lvlNum];		
		loadLevelSprites(currentLevel.getLvlDataId());
	}
	
	public void nextLvl() {
		setLvl(currentLevel.getLvlDataId().lvlNum + 1);
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
				g.drawImage(levelSprites[currentLevel.getSpriteIndex(i, j)], i * Game.TILES_SIZE - xLvlOffset, j * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE,  null);				
			}
		}
		
		
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
}
