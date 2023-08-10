package structs;

public class CollisionResult {
	public float timeElapsed;
	public Vector2D contactNormal;
	public Rect targetRect;
	
	public CollisionResult() {
		timeElapsed = 0.0f;
		contactNormal = new Vector2D(0,0);
	}
	private CollisionResult(float timeElapsed, Vector2D contactNormal) {
		this.timeElapsed = timeElapsed;
		this.contactNormal = contactNormal;
	}
	
	public void setContactNormal(float x, float y) {
		contactNormal.x = x;
		contactNormal.y = y;
	}
	
	public CollisionResult copy() {
		return new CollisionResult(timeElapsed, contactNormal);
	}
}
