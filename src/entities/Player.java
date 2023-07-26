package entities;



import utils.Constants.PlayerData;
import utils.Constants.PlayerData.PlayerAnimations;
import utils.LoadSave;

public class Player extends Entity{
	
	private static int rows = 5;
	private static int columns = 4;
	private static int imgSizeX = 32;
	private static int imgSizeY = 32;
	private float horizSpeed;
	private float vertSpeed;

	private boolean up, left, down, right, jump, mainAttack;
	private PlayerAnimations currentAnimation = PlayerAnimations.IDLE;
	
	public Player(float x, float y) {
		super(x, y);
		horizSpeed = PlayerData.HORIZ_SPEED;
		vertSpeed = PlayerData.VERT_SPEED;
		
		loadAnimations(LoadSave.PLAYER_ATLAS, rows, columns, imgSizeX, imgSizeY);
		setAnimation(currentAnimation.value, PlayerData.GetSpriteAmount(currentAnimation), PlayerData.GetAnimDelta(currentAnimation));
		sizeX = 96;
		sizeY = 96;
	}
	
	private void updateAnim_Mov() {
//		System.out.println("Player.updateAnim_Mov()");
		PlayerAnimations previousAnimation = currentAnimation; 
		float xDelta = 0, yDelta = 0;
		if (up && !down) {
			y += -vertSpeed;
			currentAnimation = PlayerAnimations.RUNNING;
		} else if (down && !up) {
			y += +vertSpeed;
			currentAnimation = PlayerAnimations.RUNNING;
		} 
		else if (left && !right) {
			x += -horizSpeed;
			currentAnimation = PlayerAnimations.RUNNING;
		} else if (right && !left) {
			x += horizSpeed;
			currentAnimation = PlayerAnimations.RUNNING;
		}
		else { 
			if (jump) {
				currentAnimation = PlayerAnimations.CHARGED_JUMP_HOLD;
			} else {
				currentAnimation = PlayerAnimations.IDLE;				
			}
		}
		if (currentAnimation != previousAnimation) {
			setAnimation(currentAnimation.value, PlayerData.GetSpriteAmount(currentAnimation), PlayerData.GetAnimDelta(currentAnimation));
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
