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
			CHARGING_JUMP(3),
			CHARGED_JUMP_HOLD(4),
			FALLING(5),
			GROUND(6),
			HIT(7),
			ATTACK(8),
			ATTACK_JUMP(9);
			
			public final int value;
			
			private PlayerAnimations(int value) {
				this.value = value;
			}
		}
			
		public static int GetSpriteAmount(PlayerAnimations player_action) {
			switch (player_action) {
			case IDLE:
				return 2;
			case RUNNING:
				return 3;
			case JUMP:
				return 0;
			case CHARGING_JUMP:
				return 0;
			case CHARGED_JUMP_HOLD:
				return 4;
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
		
		public static int GetAnimDelta(PlayerAnimations player_action) {
			switch (player_action) {
			case IDLE:
				return 60;
			case RUNNING:
				return 20;
			case JUMP:
				return 0;
			case CHARGING_JUMP:
				return 0;
			case CHARGED_JUMP_HOLD:
				return 30;
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
