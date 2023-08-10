package structs;

public class Rect {
	public Vector2D pos;
	public Vector2D size;
	
	public Rect(float x, float y, float sizeX, float sizeY) {
		pos = new Vector2D(x, y);
		size = new Vector2D(sizeX, sizeY);
	}
	public Rect(Vector2D pos, Vector2D size) {
		this.pos = new Vector2D(pos.x, pos.y);
		this.size = new Vector2D(size.x, size.y);
	}
	
	public void setSize(float sizeX, float sizeY) {
		size.x = sizeX;
		size.y = sizeY;
	}
	public void setSize(Vector2D newSize) {
		size = newSize;
	}
	public Rect copy() {
		return new Rect(pos, size);
	}
}
