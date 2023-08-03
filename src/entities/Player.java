package entities;



import utils.Constants.PlayerData;
import utils.Constants.PlayerData.PlayerAnimations;
import utils.Constants.GameData;
import main.Game;
import utils.Constants.Directions;
import utils.LoadSave;

public class Player extends Entity{
	
	private final int UPS;
	private static int rows = 6;
	private static int columns = 4;
	private static int imgSizeX = 32;
	private static int imgSizeY = 32;
	private float horizSpeed, horizFlyingSpeed;
//	private Directions currentDirection = Directions.RIGHT;
	private boolean up, left, down, right, jump, mainAttack, crouch;
	private boolean flying = true;
	private float vectorX = 0.0f, vectorY = 0.0f; 
	private int crouchTimer = 0, jumpTimer = 0;
	private PlayerAnimations currentAnimation = PlayerAnimations.IDLE;
	
	public Player(float x, float y, int UPS) {
		super(x, y);
		horizSpeed = PlayerData.HORIZ_RUNNING_SPEED;
		horizFlyingSpeed = PlayerData.HORIZ_FLYING_SPEED;
		this.UPS = UPS;
		
		loadAnimations(LoadSave.PLAYER_ATLAS, rows, columns, imgSizeX, imgSizeY);
		setAnimation(currentAnimation.value, PlayerData.GetSpriteAmount(currentAnimation), PlayerData.GetAnimDuration(currentAnimation));
		sizeX = 96;
		sizeY = 96;
	}
	
	private void updateAnim_Mov() {
//		System.out.println("Player.updateAnim_Mov()");
		PlayerAnimations previousAnimation = currentAnimation; 
		float xDelta = 0, yDelta = 0;
		
		x += vectorX;
		vectorY += GameData.GRAVITY;
		y += vectorY;
		if (y >= 500 - sizeY) {
			y = 500 - sizeY;
			vectorY = 0;
			flying = false;
		}
		
		if (flying) {
			if (currentAnimation == PlayerAnimations.JUMP) {
				jumpTimer++;
				if (jumpTimer >= PlayerData.GetAnimDuration(PlayerAnimations.JUMP)) {
					jumpTimer = 0;
					currentAnimation = PlayerAnimations.FALLING;
				}
			}
		}
		
		if (!flying && crouch) {
			switch (currentAnimation) {
			case CHARGING_JUMP:
				jumpTimer++;
				if (jumpTimer >= PlayerData.GetAnimDuration(PlayerAnimations.CHARGING_JUMP)) {
					currentAnimation = PlayerAnimations.CHARGED_JUMP_HOLD;
				}
				break;
			case CHARGED_JUMP_HOLD:
				break;
			default:
				currentAnimation = PlayerAnimations.CHARGING_JUMP;
				jumpTimer = 1;
				break;
			}
		}
		
		if (!flying && jump) {
			currentAnimation = PlayerAnimations.JUMP;
			vectorY += PlayerData.JUMP_VECTOR;
			flying = true;
			jumpTimer++;
		}
				
		if (left && !right) {
			currentDirection = Directions.LEFT;
			if (flying) {
				xDelta += -horizFlyingSpeed;				
			} else {
				xDelta += -horizSpeed;
				currentAnimation = PlayerAnimations.RUNNING;
			}
		} else if (right && !left) {
			currentDirection = Directions.RIGHT;
			if (flying) {
				xDelta += horizFlyingSpeed;
			} else {
				xDelta += horizSpeed;
				currentAnimation = PlayerAnimations.RUNNING;
			}
		} 
		
		if (!flying && !crouch && xDelta == 0 && yDelta == 0) {
			currentAnimation = PlayerAnimations.IDLE;
		}
		
		if (currentAnimation != previousAnimation) {
			setAnimation(currentAnimation.value, PlayerData.GetSpriteAmount(currentAnimation), PlayerData.GetAnimDuration(currentAnimation));
		}
		updatePos(xDelta, yDelta);
	}
	
	@Override
	public void update() {
//		System.out.println("Player.update()");
		updateAnimationTick();
		updateAnim_Mov(); //to implement
		
	}
	
	public void setUp(boolean bool) {
		up = bool;
	}
	
	public void setLeft(boolean bool) {
		left = bool;
	}

	public void setDown(boolean bool) {
		down = bool;
	}
	
	public void setRight(boolean bool) {
		right = bool;
	}
	
	public void setJump(boolean bool) {
		jump = bool;
	}
	
	public void setMainAttack(boolean bool) {
		mainAttack = bool;
	}
	
	public void setCrouch(boolean bool) {
		crouch = bool;
	}

	public void clearAllInputs() {
		up = false;
		left = false;
		down = false;
		right = false;
	}
	
//	public void setMoving(int key) {
//		switch (key) {
//		case KeyEvent.VK_W:
//			up = true;
//			break;
//		case KeyEvent.VK_A:
//			left = true;
//			break;
//		case KeyEvent.VK_S:
//			down = true;
//			break;
//		case KeyEvent.VK_D:
//			right = true;
//			break;
//		}
//	}
	
//	public void clearMoving(int key) {
//		switch (key) {
//		case KeyEvent.VK_W:
//			up = false;
//			break;
//		case KeyEvent.VK_A:
//			left = false;
//			break;
//		case KeyEvent.VK_S:
//			down = false;
//			break;
//		case KeyEvent.VK_D:
//			right = false;
//			break;
//		}
//	}
}
