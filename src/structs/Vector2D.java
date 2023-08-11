package structs;

public class Vector2D {
	public float x;
	public float y;
	
	public Vector2D() {
		this.x = 0.0f;
		this.y = 0.0f;
	}
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D copy() {
		return new Vector2D(x, y);
	}
	public Vector2D absCopy() {
		return new Vector2D(Math.abs(x), Math.abs(y));
	}
	
	public static Vector2D add(Vector2D vector1, Vector2D vector2) {
		return new Vector2D(vector1.x + vector2.x, vector1.y + vector2.y);
	}
	public void add(Vector2D vector2) {
		x = x + vector2.x;
		y = y + vector2.y;
		return;
	}
	public static Vector2D sub(Vector2D vector1, Vector2D vector2) {
		return new Vector2D(vector1.x - vector2.x, vector1.y - vector2.y);
	}
	public void sub(Vector2D vector2) {
		x = x - vector2.x;
		y = y - vector2.y;
		return;
	}
	
	public static Vector2D div(Vector2D vector1, Vector2D vector2) {
		return new Vector2D(vector1.x / vector2.x, vector1.y / vector2.y);
	}
	public static Vector2D div(Vector2D vector1, int scalar) {
		return new Vector2D(vector1.x / scalar, vector1.y / scalar);
	}
	public void div(Vector2D vector2) {
		x /= vector2.x;
		y /= vector2.y;
		return;
	}
	
	public static Vector2D mul(Vector2D vector1, Vector2D vector2) {
		return new Vector2D(vector1.x * vector2.x, vector1.y * vector2.y);
	}
	public static <N extends Number> Vector2D mul(Vector2D vector1, N n) {
		return new Vector2D(vector1.x * (float)n, vector1.y * (float)n);
	}
	public void mul(Vector2D vector2) {
		x *= vector2.x;
		y *= vector2.y;
		return;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")"; 
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Vector2D vector = (Vector2D)o;
		return this.x == vector.x && this.y == vector.y;
	}
}
