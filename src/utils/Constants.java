package utils;

public class Constants {
	
	
	public static enum Directions {
		UP,
		LEFT,
		DOWN,
		RIGHT
	}
	
	public static class GameData {
		
	}
	
	public static class PlayerData {
		
		public static final int HORIZ_SPEED = 1;
		public static final int VERT_SPEED = 1;
		
		public static enum PlayerAnimations {
			IDLE(0),
			RUNNING(1),
			JUMP(2),
			FALLING(3),
			GROUND(4),
			HIT(5),
			ATTACK(6),
			ATTACK_JUMP(7);
			
			public final int value;
			
			private PlayerAnimations(int value) {
				this.value = value;
			}
		}
			
		public static int GetSpriteAmount(PlayerAnimations player_action) {
			switch (player_action) {
			case IDLE:
				return 8;
			case RUNNING:
				return 4;
			case JUMP:
				return 0;
			case FALLING:
				return 0;
			case GROUND:
				return 0;
			case HIT:
				return 0;
			case ATTACK:
				return 0;
			case ATTACK_JUMP:
				return 0;
			default:
				return -1;
			}
		}		
	}
}
