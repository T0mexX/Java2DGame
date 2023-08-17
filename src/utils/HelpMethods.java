package utils;


import java.util.Comparator;
import java.util.Vector;
import java.util.AbstractMap.SimpleEntry;

import levels.Level;
import main.Game;
import structs.CollisionResult;
import structs.Rect;
import structs.Vector2D;

public class HelpMethods {
	
	public static <T extends Object>void VectorSwap(Vector<T> vector, int index1, int index2) {
		T tmpT = vector.get(index2);
		vector.set(index2, vector.get(index1));
		vector.set(index1, tmpT);
	}
	
	public static boolean pointVsRect(Vector2D point, Rect targetRect) {
		if (point.x <= targetRect.pos.x + targetRect.size.x
			&& point.x >= targetRect.pos.x
			&& point.y <= targetRect.pos.y + targetRect.size.y
			&& point.y >= targetRect.pos.y)
			return true;
		return false;
	}
	public static boolean pointVsRect(float xPos, float yPos, Rect targetRect) {
		if (xPos <= targetRect.pos.x + targetRect.size.x
				&& xPos >= targetRect.pos.x
				&& yPos <= targetRect.pos.y + targetRect.size.y
				&& yPos >= targetRect.pos.y)
			return true;
		return false;
	}

	public static boolean VectVsRect (Vector2D entityPos, Vector2D xyDelta, Rect targetRect, CollisionResult collisionResult) {
		Vector2D nearColVectT = Vector2D.div(Vector2D.sub(targetRect.pos, entityPos), xyDelta);
		Vector2D farColVectT = Vector2D.div(Vector2D.sub(Vector2D.add(targetRect.pos, targetRect.size), entityPos), xyDelta);
		
		if (Float.isNaN(nearColVectT.x) || Float.isNaN(nearColVectT.y)) 
			return false;
		if (Float.isNaN(farColVectT.x) || Float.isNaN(farColVectT.y))
			return false;
		
		if (nearColVectT.x > farColVectT.x) {
			float tmp = nearColVectT.x;
			nearColVectT.x = farColVectT.x;
			farColVectT.x = tmp;
		}
		if (nearColVectT.y > farColVectT.y) {
			float tmp = nearColVectT.y;
			nearColVectT.y = farColVectT.y;
			farColVectT.y = tmp;
		}
		
		if (nearColVectT.x > farColVectT.y || nearColVectT.y > farColVectT.x) return false;
		
		float nearColT = Math.max(nearColVectT.x, nearColVectT.y);
		float farColT = Math.min(farColVectT.x, farColVectT.y);
		
		
		if (nearColT > 1.0f || nearColT < 0.0f) 
			return false;
		
		collisionResult.timeElapsed = nearColT;
		
		if (nearColVectT.x > nearColVectT.y) {
			if (xyDelta.x > 0) collisionResult.setContactNormal(-1, 0);
			else collisionResult.setContactNormal(1, 0);
		}
		else {
			if (xyDelta.y > 0) collisionResult.setContactNormal(0, -1);
			else collisionResult.setContactNormal(0,  1);
		}
			return true;
		
	}
	
	
	
	public static boolean RectVsRect(Rect entityRect, Vector2D xyDelta, Rect targetRect, CollisionResult collisionResult) {
		//farColT and farColT are percentages on the xyDelta vector
		Rect expandedTargetRect = new Rect(Vector2D.sub(targetRect.pos, Vector2D.div(entityRect.size, 2)), Vector2D.add(targetRect.size, entityRect.size));
		
		if (VectVsRect(Vector2D.add(entityRect.pos, Vector2D.div(entityRect.size, 2)), xyDelta, expandedTargetRect, collisionResult)) {
			collisionResult.targetRect = targetRect;
			return true;
		}
		else
			return false;
	}
	
	public static boolean DoIHaveGround(float currentX, float currentY, int halfSizeX, int halfSizeY, Level level) {
		int solidTileMaxIndex = level.getSolidTilesMaxIndex();
		int nullTileIndex = level.getNullTileIndex();
		if (IsSolid(currentX - halfSizeX, currentY + halfSizeY + 1, level.getLevelData(), solidTileMaxIndex, nullTileIndex) ||
			IsSolid(currentX + halfSizeX, currentY + halfSizeY + 1, level.getLevelData(), solidTileMaxIndex, nullTileIndex)) 
			return true;
		return false;
	}
		
	public static <N extends Number, T> void SortVectorOfPair(Vector<SimpleEntry<N, T>> vector) { //<N extends Number>, Entity>
		vector.sort(new Comparator<SimpleEntry<N, T>>() {
			@Override
			public int compare(SimpleEntry<N, T> first, SimpleEntry<N, T> second) {
				return (((Float)first.getKey()).compareTo((Float)(second.getKey())));
			}
		});
	}
	
	private static boolean IsSolid(float x, float y, int[][] lvlData, int solidTilesMaxIndex, int nullTileIndex) {
		System.out.println(lvlData[0].length);
		if (x < 0 || x >= lvlData[0].length * Game.TILES_SIZE || y < 0 || y >= Game.GAME_HEIGHT)
			return true;
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		int value = lvlData[(int)yIndex][(int)xIndex];
		
		if (value >= solidTilesMaxIndex || value < 0 || value == nullTileIndex) 
			return false;
		return true;
	}
}
