package levels;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

import main.Game;
import structs.Rect;
import structs.Vector2D;
import utils.Constants.LvlDataId;
import utils.LoadSave;

public class Level {

	private LvlDataId lvlDataId;
	private int[][] lvlData;
	private int nullTileIndex;
	private int tilesSpritesArraySize;
	private int solidTilesMaxIndex;
	private Vector2D spawnPoint;
	private Vector<Rect> levelHitboxRects;
	
	
	public Level(LvlDataId lvlDataId) {
		this.lvlDataId = lvlDataId;
		this.nullTileIndex = lvlDataId.tileAtlasId.nullTileIndex;
		this.tilesSpritesArraySize = lvlDataId.tileAtlasId.xSize * lvlDataId.tileAtlasId.ySize;
		this.solidTilesMaxIndex = lvlDataId.tileAtlasId.maxSolidTileIndex;
		this.spawnPoint = lvlDataId.spawnPoint;
		this.levelHitboxRects = new Vector<Rect>();
		loadLevelData();
		loadTilesHitboxes();
	}
	
	private void loadTilesHitboxes() {
		for (int j = 0; j < lvlData.length; j++) {
			for (int i = 0; i < lvlData[0].length; i++) {
				if (lvlData[j][i] <= solidTilesMaxIndex && lvlData[j][i] != nullTileIndex) {
					levelHitboxRects.add(new Rect(i * Game.TILES_SIZE, j * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
				}
			}
		}
	}
	private void loadLevelData() {
		BufferedImage img = LoadSave.GetSpriteAtlas(lvlDataId.filePath);
		lvlData = new int[img.getHeight()][img.getWidth()];
		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= tilesSpritesArraySize) 
					value = nullTileIndex;
				lvlData[j][i] = value;
			}
		}
	}	
	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
	public int[][] getLevelData() {
		return lvlData;
	}
	public int getNullTileIndex() {
		return nullTileIndex;
	}
	public int getTilesArraySize() {
		return tilesSpritesArraySize;
	}
	public int getSolidTilesMaxIndex() {
		return solidTilesMaxIndex;
	}
	public Vector<Rect> getLevelHitboxRects() {
		return levelHitboxRects;
	}
	public Vector2D getSpawnPoint() {
		return spawnPoint;
	}
}
