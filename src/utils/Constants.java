package utils;

public class Constants {
	
	
	public static enum Directions {
		UP,
		LEFT,
		DOWN,
		RIGHT
	}
	
	public static class GameData {
		public static final float GRAVITY = 0.2f;
	}
	
	public static class PlayerData {
		
		public static final float HORIZ_RUNNING_SPEED = 2.0f;
		public static final float HORIZ_FLYING_SPEED = 2.0f;
		public static final float JUMP_VECTOR = -10.0f;
		
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
				return 4;
			case JUMP:
				return 4;
			case CHARGING_JUMP:
				return 4;
			case CHARGED_JUMP_HOLD:
				return 4;
			case FALLING:
				return 2;
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
		
		public static int GetAnimDuration(PlayerAnimations player_action) { //duration in s/120
			switch (player_action) { //120 UPS
			case IDLE:
				return 60; //2
			case RUNNING:
				return 80; //4
			case JUMP:
				return 80; //4
			case CHARGING_JUMP: //4
				return 40;
			case CHARGED_JUMP_HOLD: //4
				return 120; 
			case FALLING: 
				return 60; //2
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
