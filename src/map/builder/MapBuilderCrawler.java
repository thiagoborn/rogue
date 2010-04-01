package map.builder;

import util.Bounds;
import util.Direction;

class MapBuilderCrawler {

	private Direction direction;
	private Direction oposed;
	private int x;
	private int y;
	private int size;
	private MapBuilder map;
	private int walked = 0;
	private MapBuilderConfiguration config;
	private MapRandom rnd;

	MapBuilderCrawler(Direction direction, Direction oposed, int x, int y,
			int size, MapBuilder map) {
		super();
		setMap(map);
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.size = size;
		this.map = map;
		this.oposed = oposed;
	}

	MapBuilderCrawler(MapBuilder map) {
		super();
		setMap(map);

		x = rnd.nextInt(map.getWidth());
		y = rnd.nextInt(map.getHeight());
		size = config.initialCrawlerSize;
		direction = Direction.values()[rnd.nextInt(Direction.values().length)];
		oposed = direction.getRandomOposedDirection(rnd);
	}

	private boolean checkCrash() {

		boolean crash = checkCrash(x, y)
				|| checkCrash(x + direction.getDirectionX(), y
						+ direction.getDirectionY());

		return crash;
	}

	private boolean checkCrash(int x, int y) {

		int posX = x - oposed.getDirectionX();
		int posY = y - oposed.getDirectionY();

		for (int pos = 0; pos < size + 2; pos++) {
			if (map.getTile(posX, posY) != TileType.VOID) {
				return true;
			}
			posX += oposed.getDirectionX();
			posY += oposed.getDirectionY();
		}

		return false;
	}

	private void fill() {

		int posX = x;
		int posY = y;

		for (int pos = 0; pos < size; pos++) {
			map.setTile(posX, posY, TileType.FLOOR);
			posX += oposed.getDirectionX();
			posY += oposed.getDirectionY();
		}
	}

	Direction getDirection() {
		return direction;
	}

	int getSize() {
		return size;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	private void haveChildren() {
		Direction childDirection = direction.getRandomOposedDirection(rnd);
		int childSize = size;
		int rndval = rnd.nextInt(1000);
		if (rndval < config.decreaseChildrenSize) {
			childSize++;
		}
		if (rndval > config.increaseChildrenSize) {
			childSize = Math.max(1, childSize - 1);
		}

		if (walked > (childSize + 1)) {

			int multiplier = 1;
			if (childDirection == oposed) {
				multiplier = size;
			}
			int childX = x + childDirection.getDirectionX() * multiplier;
			int childY = y + childDirection.getDirectionY() * multiplier;
			// childrens are alllways 'behind' father ... thats why the
			// getInverse
			MapBuilderCrawler child = new MapBuilderCrawler(childDirection,
					direction.getInverse(), childX, childY, childSize, map);
			map.addCrawler(child);
		}

	}

	private void makeRoom() {

		int roomSize = rnd.nextInt(config.roomMinSize, config.roomMaxSize);

		if (walked > (roomSize + 1)) {
			int roomWalk = rnd.nextInt(config.roomMinSize, config.roomMaxSize);

			Direction roomDirection = direction.getRandomOposedDirection(rnd);
			int multiplier = 1;
			if (roomDirection == oposed) {
				multiplier = size;
			}
			int childX = x + roomDirection.getDirectionX() * multiplier;
			int childY = y + roomDirection.getDirectionY() * multiplier;

			Direction inverse = direction.getInverse();

			boolean ok = true;
			int posX = childX;
			int posY = childY;
			for (int pos = 0; pos < roomWalk + 2; pos++) {
				for (int i = 0; i < roomSize + 2; i++) {
					int roomScanX = direction.getDirectionX() + posX
							+ inverse.getDirectionX() * i;
					int roomScanY = direction.getDirectionY() + posY
							+ inverse.getDirectionY() * i;
					if (map.getTile(roomScanX, roomScanY) != TileType.VOID) {
						ok = false;
					}
				}
				posX += roomDirection.getDirectionX();
				posY += roomDirection.getDirectionY();
			}

			if (ok) {
				Bounds boundX = new Bounds();
				Bounds boundY = new Bounds();
				int doorPos = rnd.nextInt(roomSize);
				int doorX = childX + inverse.getDirectionX() * doorPos;
				int doorY = childY + inverse.getDirectionY() * doorPos;
				map.setTile(doorX, doorY, TileType.DOOR);
				posX = childX + roomDirection.getDirectionX();
				posY = childY + roomDirection.getDirectionY();
				for (int pos = 0; pos < roomWalk; pos++) {
					for (int i = 0; i < roomSize; i++) {
						int roomScanX = posX + inverse.getDirectionX() * i;
						int roomScanY = posY + inverse.getDirectionY() * i;
						boundX.mergePoint(roomScanX);
						boundY.mergePoint(roomScanY);
						map.setTile(roomScanX, roomScanY, TileType.ROOM);

					}
					posX += roomDirection.getDirectionX();
					posY += roomDirection.getDirectionY();
				}
				map.addRoom(new Room(map.getNextRoomID(), boundX.getIndex(),
						boundY.getIndex(), boundX.getSize(), boundY.getSize()));
			}
		}
	}

	private boolean move() {
		x += direction.getDirectionX();
		y += direction.getDirectionY();
		if (x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight()) {
			return false;
		}
		walked++;
		return true;
	}

	void setDirection(Direction direction) {
		this.direction = direction;
	}

	void setMap(MapBuilder map) {
		this.map = map;
		config = map.getConfig();
		rnd = map.getRandom();
	}

	void setSize(int size) {
		this.size = size;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	boolean update() {
		if (checkCrash())
			return false;
		fill();

		// children
		if (rnd.nextKick(config.childrenProb)) {
			haveChildren();
		}

		// rooms
		if (rnd.nextKick(config.roomProb)) {
			makeRoom();
		}
		// natural death
		if (rnd.nextKick(config.naturalDeath)) {
			return false;
		}

		if (!move())
			return false;

		return true;
	}

}
