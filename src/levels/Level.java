package levels;

import java.util.Vector;

import main.Game;
import structs.Rect;

public class Level {

	private int[][] lvlData;
	private int nullTileIndex;
	private int tilesArraySize;
	private int solidTilesMaxIndex;
	private Vector<Rect> levelHitboxRects;
	
	
	public Level(int[][] lvlData, int nullTileIndex, int tilesArraySize, int solidTilesMaxIndex) {
		this.lvlData = lvlData;
		this.nullTileIndex = nullTileIndex;
		this.tilesArraySize = tilesArraySize;
		this.solidTilesMaxIndex = solidTilesMaxIndex;
		this.levelHitboxRects = new Vector<Rect>();
		
		for (int j = 0; j < lvlData.length; j++) {
			for (int i = 0; i < lvlData[0].length; i++) {
				if (lvlData[j][i] <= solidTilesMaxIndex && lvlData[j][i] != nullTileIndex) {
					levelHitboxRects.add(new Rect(i * Game.TILES_SIZE, j * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
//System.out.println("newRect posX: " + levelHitboxRects.get(levelHitboxRects.size() - 1).pos.x);
//System.out.println("newRect posY: " + levelHitboxRects.get(levelHitboxRects.size() - 1).pos.y);
//System.out.println("newRect sizeX: " + levelHitboxRects.get(levelHitboxRects.size() - 1).size.x);
//System.out.println("newRect sizeY: " + levelHitboxRects.get(levelHitboxRects.size() - 1).size.y);
				}
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
		return tilesArraySize;
	}
	public int getSolidTilesMaxIndex() {
		return solidTilesMaxIndex;
	}
	public Vector<Rect> getLevelHitboxRects() {
		return levelHitboxRects;
	}
}
