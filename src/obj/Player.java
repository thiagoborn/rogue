package obj;

import java.util.HashMap;

import map.Map;

public class Player extends Walker {
	public static enum Visibility {
		HIDDEN, DISCOVERED, VISIBLE
	}

	private HashMap<Map, Visibility[][]> visibleTable = new HashMap<Map, Visibility[][]>();
	private Visibility[][] visible;

	public void initStats() {

	}

	public Visibility[][] getVisible() {
		return visible;
	}

	public void setMap(Map map) {
		super.setMap(map);
		visible = visibleTable.get(map);
		if (visible == null) {
			visible = new Visibility[map.getWidth()][map.getHeight()];
			for (int x = 0; x < map.getWidth(); x++)
				for (int y = 0; y < map.getHeight(); y++) {
					visible[x][y] = Visibility.VISIBLE;
				}
			visibleTable.put(map, visible);
		}
	}
}
