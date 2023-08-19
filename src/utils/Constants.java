package utils;

import java.awt.Dimension;

import main.Game;
import structs.Vector2D;

public class Constants {
	
	public static class UI {
		public static final int SOUND_BUTTON_IMG_SIZE = 42;
		public static final int URM_BUTTON_IMG_SIZE = 56;
		public static final int VOLUME_BUTTON_IMG_XSIZE = 28;
		public static final float VOLUME_BUTTON_XSIZE_ON_BARXSIZE = 28f/215f; 
		public static final float VOLUME_BUTTON_YSIZE_ON_BARYSIZE = 0.8f; 
		
		

		public static enum UrmButtonEnum {
			RESUME(0),
			RETURN(1),
			HOME(2);
			
			public final int buttonIdIndex;
			
			private UrmButtonEnum(int index) {
				buttonIdIndex = index;
			}
		}
		
		public static class PauseOverlayConst {
			public static final int BACKGROUND_YPOS = (int)(225 * Game.SCALE);
			public static final int BACKGROUND_XSIZE = (int)(258 * Game.SCALE);
			public static final int BACKGROUND_YSIZE = (int)(389 * Game.SCALE);
			
			public static final int SOUND_BUTTON_SIZE = (int)(42 * Game.SCALE);
			public static final int MUSIC_BUTTON_XPOS = (int)(475 * Game.SCALE); 
			public static final int MUSIC_BUTTON_YPOS = (int)(167 * Game.SCALE);
			public static final int SFX_BUTTON_XPOS = (int)(475 * Game.SCALE);
			public static final int SFX_BUTTON_YPOS = (int)(213 * Game.SCALE);
			
			public static final int URM_BUTTON_SIZE = (int)(56 * Game.SCALE);
			public static final int RESUME_BUTTON_XPOS = (int)(492 * Game.SCALE);
			public static final int RESUME_BUTTON_YPOS = (int)(355 * Game.SCALE);
			public static final int REPLAY_BUTTON_XPOS = (int)(417 * Game.SCALE);
			public static final int REPLAY_BUTTON_YPOS = (int)(355 * Game.SCALE);
			public static final int MENU_BUTTON_XPOS = (int)(343 * Game.SCALE);
			public static final int MENU_BUTTON_YPOS = (int)(355 * Game.SCALE);
			
			public static final int VOLUME_BAR_XSIZE = (int)(215 * Game.SCALE);
			public static final int VOLUME_BAR_YSIZE = (int)(44 * Game.SCALE);
			public static final int VOLUME_BAR_XPOS = (int)(Game.GAME_WIDTH / 2);
			public static final int VOLUME_BAR_YPOS = (int)(300 * Game.SCALE);
			
			
		}
		
		public static class LvlCompletedOverlayConst {
			public static final int BACKGROUND_YPOS = (int)(170 * Game.SCALE);
			public static final int BACKGROUND_XSIZE = (int)(224 * Game.SCALE);
			public static final int BACKGROUND_YSIZE = (int)(204 * Game.SCALE);
			
			public static final int URM_BUTTON_SIZE = (int)(56 * Game.SCALE);
			public static final int NEXT_BUTTON_XPOS = (int)(473 * Game.SCALE);
			public static final int NEXT_BUTTON_YPOS = (int)(215 * Game.SCALE);
			public static final int MENU_BUTTON_XPOS = (int)(358 * Game.SCALE);
			public static final int MENU_BUTTON_YPOS = (int)(215 * Game.SCALE);

		}
		
		public static class MenuConst {
			public static final int BACKGROUND_YPOS = (int)(45 * Game.SCALE);
			public static final int BACKGROUND_XSIZE = (int)(282 * Game.SCALE);
			public static final int BACKGROUND_YSIZE = (int)(336 * Game.SCALE);
			
			public static final int BTN_IMG_XSIZE = 140;
			public static final int BTN_IMG_YSIZE = 56;
			public static final int BTN_XSIZE = (int)(140 * Game.SCALE); 
			public static final int BTN_YSIZE = (int)(56 * Game.SCALE); 
		}
	}
	
	public static enum Directions {
		UP,
		LEFT,
		DOWN,
		RIGHT 
	}
	
	public static enum LvlDataId {
		LVL1("/res/lvls/1.png", TileAtlasId.DEFAULT, new Vector2D(100 * Game.SCALE, 300 * Game.SCALE)),
		LVL2("/res/lvls/2.png", TileAtlasId.DEFAULT, new Vector2D(100 * Game.SCALE, 300 * Game.SCALE)),
		LVL3("/res/lvls/3.png", TileAtlasId.DEFAULT, new Vector2D(100 * Game.SCALE, 300 * Game.SCALE));
		
		public final String filePath;
		public final TileAtlasId tileAtlasId;
		public final Vector2D spawnPoint;
		
		LvlDataId(String filePath, TileAtlasId tileAtlasId, Vector2D spawnPoint) {
			this.filePath = filePath;
			this.tileAtlasId = tileAtlasId;
			this.spawnPoint = spawnPoint;
		}
		
		public static enum TileAtlasId {
			DEFAULT("/res/defaultTileAtlas.png", 12, 4, 11, 48); // filePath, columns, rows, nullTileIndex, maxSolidTileIndex
			
			public final String filePath;
			public final int xSize;
			public final int ySize;
			public final int nullTileIndex;
			public final int maxSolidTileIndex;
			
			TileAtlasId(String filePath, int xSize, int ySize, int nullTileIndex, int maxSolidTileIndex) {
				this.filePath = filePath;
				this.xSize = xSize;
				this.ySize = ySize;
				this.nullTileIndex = nullTileIndex;
				this.maxSolidTileIndex = maxSolidTileIndex;
			}
		}
	}
	
	public static class GameData {
		public static final float GRAVITY = 0.05f * Game.SCALE;
	}
	
	public static class PlayerData {
		
		public static final float HITBOX_X_FRACTION = 0.4f;
		public static final float HITBOX_Y_FRACTION = 1f;
		public static final float HORIZ_RUNNING_SPEED = 1.0f * Game.SCALE;
		public static final float HORIZ_FLYING_SPEED = 1.0f * Game.SCALE;
		public static final float JUMP_VECTOR = -2.7f * Game.SCALE, CHARGED_JUMP_VECTOR = -3.7f * Game.SCALE;
		
		public static enum PlayerEffects {
			JUMP_ENERGY(0);
			
			public final int value;
			
			private PlayerEffects(int value) {
				this.value = value;
			}
			
		}
		
		
		public static enum PlayerAnimations {
			IDLE(0, 2, 60),  // index, animFrames, animDuration
			RUNNING(1, 4, 80), 
			JUMP(2, 4, 80), 
			CHARGING_JUMP(3, 4, 60), 
			CHARGED_JUMP_HOLD(4, 4, 120), 
			FALLING(5, 2, 60), 
			GROUND(6, 0, 0),
			HIT(7, 0, 0),
			ATTACK(8, 0, 0),
			ATTACK_JUMP(9, 0, 0);
			
			public final int value;
			public final int spritesAmount;
			public final int animDuration;
			
			private PlayerAnimations(int value, int framesNumber, int animDuration) {
				this.value = value;
				this.spritesAmount = framesNumber;
				this.animDuration = animDuration;
			}
		}
	}
}
