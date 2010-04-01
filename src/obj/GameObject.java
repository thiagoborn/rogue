package obj;

import java.awt.Color;

import map.Map;

public abstract class GameObject {
	//
	private Map map;

	private Walker owner;

	private int posX, posY;

	public char getCharacter() {
		return '!';
	}

	public Color getColor() {
		return Color.white;
	}

	public String getDescription() {
		return "#DESC#";
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isPassable() {
		return true;
	}

	public boolean isShowOutOfSight() {
		return false;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setMap(Map map) {
		if (owner != null) {
			throw new RuntimeException();
		}
		this.map = map;
	}

	public boolean isUpdateable() {
		return false;
	}

	public boolean update() {
		return false;
	}

}
