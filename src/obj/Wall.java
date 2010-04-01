package obj;

public class Wall extends GameObject {

	@Override
	public char getCharacter() {
		return '#';
	}

	@Override
	public String getDescription() {
		return "Wall";
	}

}
