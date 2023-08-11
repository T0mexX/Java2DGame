package entities;

import utils.Constants.PlayerData;
import utils.Constants.PlayerData.PlayerAnimations;
import utils.Constants.GameData;
import main.Game;
import structs.CollisionResult;
import structs.Rect;
import structs.Vector2D;
import utils.Constants.Directions;
import utils.LoadSave;
import static utils.HelpMethods.DoIHaveGround;
import static utils.HelpMethods.RectVsRect;
import static utils.HelpMethods.SortVectorOfPair;
import static utils.HelpMethods.VectorSwap;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.Vector;

import levels.Level;

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
	private Level level;
	private Vector<Rect> levelHitboxRects = new Vector<Rect>();
	private CollisionResult collisionResult = new CollisionResult();
	
	public Player(float x, float y, int UPS) {
		super(x, y, imgSizeX, imgSizeY, imgSizeX * PlayerData.HITBOX_X_FRACTION, imgSizeY * PlayerData.HITBOX_Y_FRACTION);
		horizSpeed = PlayerData.HORIZ_RUNNING_SPEED;
		horizFlyingSpeed = PlayerData.HORIZ_FLYING_SPEED;
		this.UPS = UPS;
		
		loadAnimations(LoadSave.PLAYER_ATLAS, rows, columns);
		setAnimation(currentAnimation.value, PlayerData.GetSpriteAmount(currentAnimation), PlayerData.GetAnimDuration(currentAnimation));
	}
	
	@Override
	public void update() {
		updateAnimationTick();
		updateAnim_Mov(); 
		updateHitbox();
	}
	
	private void updateAnim_Mov() {
		PlayerAnimations previousAnimation = currentAnimation; 
		float xDelta = 0, yDelta = 0;
		
		xDelta += vectorX;
		if (!DoIHaveGround(x, y, (int)halfHitboxSizeX - 1, (int)halfHitboxSizeY, level)) {
			//-1 to avoid detected ground when adjacent to walls
			vectorY += GameData.GRAVITY;
			flying = true;
			if (vectorY > 0)
				currentAnimation = PlayerAnimations.FALLING;
		}
		yDelta += vectorY;
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
				crouchTimer++;
				if (crouchTimer >= PlayerData.GetAnimDuration(PlayerAnimations.CHARGING_JUMP)) {
					currentAnimation = PlayerAnimations.CHARGED_JUMP_HOLD;
				}
				break;
			case CHARGED_JUMP_HOLD:
				break;
			default:
				currentAnimation = PlayerAnimations.CHARGING_JUMP;
				crouchTimer = 1;
				break;
			}
		}
		
		if (!flying && jump) {
			currentAnimation = PlayerAnimations.JUMP;
			vectorY = 0;
			switch (previousAnimation) {
			case CHARGED_JUMP_HOLD:
				vectorY += PlayerData.CHARGED_JUMP_VECTOR;
				break;
			case CHARGING_JUMP:
				vectorY += (float)(crouchTimer - PlayerData.GetAnimDuration(PlayerAnimations.CHARGING_JUMP) / 2)/(float)(PlayerData.GetAnimDuration(PlayerAnimations.CHARGING_JUMP)) * (PlayerData.CHARGED_JUMP_VECTOR - PlayerData.JUMP_VECTOR) + PlayerData.JUMP_VECTOR;
				break;
			default:
				vectorY += PlayerData.JUMP_VECTOR;
				break;
			}
			flying = true;
			jumpTimer = 1;
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
		
		Vector2D xyDelta = new Vector2D(xDelta, yDelta);
		
		Vector<SimpleEntry<Float, Rect>> collisionVect = new Vector<SimpleEntry<Float, Rect>>();
		
		for (int i = 0; i < levelHitboxRects.size(); i++) {
			if (RectVsRect(hitboxRect, xyDelta, levelHitboxRects.get(i), collisionResult)) {
				collisionVect.add(new SimpleEntry<Float, Rect>(collisionResult.timeElapsed, collisionResult.targetRect.copy()));
			}
		}
		SortVectorOfPair(collisionVect);
		if (collisionVect.size() >= 1) {
			float tmpFloat = collisionVect.get(0).getKey();
			for (int i = 1; i < collisionVect.size(); i++) {
				if (collisionVect.get(i).getKey() == tmpFloat) {
					if (hitboxRect.pos.x == collisionVect.get(i).getValue().pos.x || hitboxRect.pos.y == collisionVect.get(i).getValue().pos.y) {
						VectorSwap(collisionVect, i, i - 1);
					}
				}
			}
		}
		
		boolean xDone = false;
		boolean yDone = false;
		
		for (int i = 0; i < collisionVect.size(); i++) {
			
			
			if (!RectVsRect(hitboxRect, xyDelta, collisionVect.get(i).getValue(), collisionResult)) 
				continue;
			if (collisionResult.contactNormal.x != 0.0f) {
				if (xDone)
					continue;
				xDone = true;
			}
			else {
				if (yDone)
					continue;
				yDone = true;
			}	
			
			if (collisionResult.contactNormal.x == 0.0f) {
				flying = false;
				vectorY = 0.0f;
				currentAnimation = PlayerAnimations.RUNNING;
			}
			if (collisionResult.contactNormal.x != 0) {
				if (currentAnimation == PlayerAnimations.RUNNING) 
					currentAnimation = PlayerAnimations.IDLE;
				vectorX = 0.0f;
			}
//System.out.println("addedVector: " + Vector2D.mul(Vector2D.mul(collisionResult.contactNormal, xyDelta.absCopy()), 1 - collisionVect.get(i).getKey()).x + " " + Vector2D.mul(Vector2D.mul(collisionResult.contactNormal, xyDelta.absCopy()), 1 - collisionVect.get(i).getKey()).y);
			xyDelta.add(Vector2D.mul(Vector2D.mul(collisionResult.contactNormal, xyDelta.absCopy()), 1 - collisionVect.get(i).getKey()));
			if (xDone && yDone)
				break;
		}
		
		updatePos(xyDelta.x, xyDelta.y);
		
		if (currentAnimation != previousAnimation) {
			setAnimation(currentAnimation.value, PlayerData.GetSpriteAmount(currentAnimation), PlayerData.GetAnimDuration(currentAnimation));
		}
	}
	
	
	
	public void loadLevelData(Level level) {
		this.level = level;
		this.levelHitboxRects = level.getLevelHitboxRects();
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
}