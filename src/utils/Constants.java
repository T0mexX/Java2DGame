package utils;

import java.awt.Dimension;

import main.Game;

public class Constants {
	
	public static class UI {
		public static final int SOUND_BUTTON_IMG_SIZE = 42;
		
		public static class PauseOverlayConst {
			public static final int BACKGROUND_YPOS = (int)(30 * Game.SCALE);
			public static final int BACKGROUND_XSIZE = (int)(258 * Game.SCALE);
			public static final int BACKGROUND_YSIZE = (int)(389 * Game.SCALE);
			
			public static final int SOUND_BUTTON_SIZE = (int)(42 * Game.SCALE);
			public static final int MUSIC_BUTTON_XPOS = (int)(475 * Game.SCALE); 
			public static final int MUSIC_BUTTON_YPOS = (int)(167 * Game.SCALE);
			public static final int SFX_BUTTON_XPOS = (int)(475 * Game.SCALE);
			public static final int SFX_BUTTON_YPOS = (int)(213 * Game.SCALE);
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
	
	public static class LevelData {
			
			static final Dimension[] LVL_IMG_DIMENSIONS = {new Dimension(0,0),
											   			   new Dimension(12, 4) //1
											   			   };
			static final int[] LVL_NULL_TILE_INDEXES = {0, 
														   11 //1
														   };
			static final int[] LVL_MAX_SOLID_TILES_INDEXES = {0,
															  48 //1
															  };
			
		public static int GetLevelTilesImageRows(int lvlNumber) {
			return LVL_IMG_DIMENSIONS[lvlNumber].height;
		}
		public static int GetLevelTilesImageColumns(int lvlNumber) {
			return LVL_IMG_DIMENSIONS[lvlNumber].width;
		}
		public static int GetLevelTilesArraySize(int lvlNumber) {
			return LVL_IMG_DIMENSIONS[lvlNumber].height * LVL_IMG_DIMENSIONS[lvlNumber].width;
		}
		public static int GetLevelNullTileIndex(int lvlNumber) {
			return LVL_NULL_TILE_INDEXES[lvlNumber];
		}
		public static int GetLevelMaxSolidTilesIndex(int lvlNumber) {
			return LVL_MAX_SOLID_TILES_INDEXES[lvlNumber];
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
