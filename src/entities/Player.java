package entities;



import utils.Constants.PlayerData;
import utils.Constants.PlayerData.PlayerAnimations;

public class Player extends Entity{
	
	private static int rows = 2;
	private static int columns = 8;
	private static String path = "/test_animation_2x8_32x32.png";
	private static int imgSizeX = 32;
	private static int imgSizeY = 32;
	private float horizSpeed;
	private float vertSpeed;

	private boolean up, left, down, right, mainAttack;
	private PlayerAnimations currentAnimation = PlayerAnimations.IDLE;
	
	public Player(float x, float y) {
		super(x, y);
		horizSpeed = PlayerData.HORIZ_SPEED;
		vertSpeed = PlayerData.VERT_SPEED;
		loadAnimations(path, rows, columns, imgSizeX, imgSizeY);
		setAnimation(currentAnimation.value, PlayerData.GetSpriteAmount(currentAnimation));
	}
	
	private void updateAnim_Mov() {
//		System.out.println("Player.updateAnim_Mov()");
		float xDelta = 0, yDelta = 0;
		if (up && !down) {
			y += -vertSpeed;
		} else if (down && !up) {
			y += +vertSpeed;
		}
		if (left && !right) {
			x += -horizSpeed;
		} else if (right && !left) {
			x += horizSpeed;
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
