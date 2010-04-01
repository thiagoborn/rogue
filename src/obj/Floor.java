package obj;

public class Floor extends GameObject {
	public static enum FloorType {
		CORRIDOR, ROOM
	}

	public Floor(FloorType type) {
	}

	@Override
	public char getCharacter() {
		return '.';
	}

	@Override
	public String getDescription() {
		return "floor";
	}

}
