package entities;



import utils.Constants.PlayerData;
import utils.Constants.PlayerData.PlayerAnimations;
import main.Game;
import utils.Constants.Directions;
import utils.LoadSave;

public class Player extends Entity{
	
	private final int UPS;
	private static int rows = 5;
	private static int columns = 4;
	private static int imgSizeX = 32;
	private static int imgSizeY = 32;
	private float horizSpeed;
	private float vertSpeed;
//	private Directions currentDirection = Directions.RIGHT;
	private boolean up, left, down, right, jump, mainAttack, crouch;
	private int crouchTimer = 0;
	private PlayerAnimations currentAnimation = PlayerAnimations.IDLE;
	
	public Player(float x, float y, int UPS) {
		super(x, y);
		horizSpeed = PlayerData.HORIZ_SPEED;
		vertSpeed = PlayerData.VERT_SPEED;
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
		if (up && !down) {
			y += -vertSpeed;
//			currentAnimation = PlayerAnimations.RUNNING;
		} else if (down && !up) {
			y += +vertSpeed;
//			currentAnimation = PlayerAnimations.RUNNING;
		} 
		if (left && !right) {
			x += -horizSpeed;
			currentAnimation = PlayerAnimations.RUNNING;
			currentDirection = Directions.LEFT;
		} else if (right && !left) {
			x += horizSpeed;
			currentAnimation = PlayerAnimations.RUNNING;
			currentDirection = Directions.RIGHT;
		} else { 
			if (crouch) {
				if (currentAnimation != PlayerAnimations.CHARGED_JUMP_HOLD) {
					currentAnimation = PlayerAnimations.CHARGING_JUMP;
					crouchTimer++;
					if (crouchTimer >= PlayerData.GetAnimDuration(PlayerAnimations.CHARGING_JUMP)) {
						crouchTimer = 0;
						currentAnimation = PlayerAnimations.CHARGED_JUMP_HOLD;
					} 					
				}
			} else {
				crouchTimer = 0;
				currentAnimation = PlayerAnimations.IDLE;				
			}
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
