package map.builder;

public class Room {
	private int id;
	private int x, y;
	private int width, height;

	Room(int id, int x, int y, int width, int height) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	int getHeight() {
		return height;
	}

	int getWidth() {
		return width;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	

}
