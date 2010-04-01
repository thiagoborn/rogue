package util;

import java.util.Random;

public enum Direction {
	NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0);
	private int directionX;
	private int directionY;

	private Direction(int directionX, int directionY) {
		this.directionX = directionX;
		this.directionY = directionY;
	}

	public int getDirectionX() {
		return directionX;
	}

	public int getDirectionY() {
		return directionY;
	}

	public Direction getInverse() {
		switch (this) {
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;

		case WEST:
			return EAST;
		case EAST:
			return WEST;
		}
		return null;
	}

	public Direction getRandomOposedDirection(Random rnd) {
		switch (this) {
		case NORTH:
		case SOUTH:
			return rnd.nextBoolean() ? Direction.EAST : Direction.WEST;

		case WEST:
		case EAST:
			return rnd.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
		return null;
	}
}