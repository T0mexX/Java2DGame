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
	
	@Override
	public String toString() {
		return "collisionResult: (timeElapsed: " + timeElapsed + " | targetRect: " + targetRect.toString() + " | contactNormal: " + contactNormal.toString() + ")";
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		CollisionResult colRes = (CollisionResult)o;
		return this.contactNormal.equals(colRes.contactNormal) && this.targetRect.equals(colRes.targetRect) && this.timeElapsed == colRes.timeElapsed;
	}
}
