package applet;

public class Camera {
	private int tx, ty;
	private int x, y;

	public int getX() {
		return x >> 10;
	}

	public int getY() {
		return y >> 10;
	}

	public void setPosAsTarget() {
		x = tx;
		y = ty;
	}

	public void setTarget(int x, int y) {
		tx = x << 10;
		ty = y << 10;
	}

	public void update(int diftime) {
		diftime *= 2;
		int dx = tx - x;
		int dy = ty - y;
		dx >>= 10;
		dy >>= 10;
		x += dx * diftime;
		y += dy * diftime;
	}
}