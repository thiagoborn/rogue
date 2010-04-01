package obj;

public class Door extends GameObject {
	public static enum DoorState {
		OPEN,
		CLOSED
	}
	public Door(DoorState open) {
	}

	@Override
	public char getCharacter() {
		return '.';
	}

	@Override
	public String getDescription() {
		return "door";
	}

}
