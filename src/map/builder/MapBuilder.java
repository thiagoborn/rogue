package map.builder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class MapBuilder implements Iterable<Room> {

	public static void main(String a[]) throws IOException {
		MapBuilder mapBuilder = new MapBuilder();
		BufferedImage img = mapBuilder.getPreviewImage();
		for (Room room : mapBuilder) {
			System.out.println(room);
		}
		File file = new File("c:/workspace/map.gif");
		ImageIO.write(img, "GIF", file);
	}
	private TileType map[][];
	private List<Room> rooms = new LinkedList<Room>();
	private List<MapBuilderCrawler> crawlerList = new LinkedList<MapBuilderCrawler>();
	private List<MapBuilderCrawler> addList = new LinkedList<MapBuilderCrawler>();
	private List<MapBuilderCrawler> removeList = new LinkedList<MapBuilderCrawler>();

	private MapBuilderConfiguration config;

	private MapRandom random;

	public MapBuilder() {
		this(new MapBuilderConfiguration());
	}

	public MapBuilder(MapBuilderConfiguration config) {
		super();
		this.config = config;
		map = new TileType[getWidth()][getHeight()];
		fill(TileType.VOID);
		random = new MapRandom(config.seed);
		build();
	}

	void addCrawler(MapBuilderCrawler crawler) {
		addList.add(crawler);
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	private void build() {
		boolean ok;
		int count = 0;
		do {
			ok = true;
			generate();
			count++;
			if (!validate()) {
				clear();
				System.out.println("fail to validate attemp: " + count);
				random.setSeed(config.seed + count);
				ok = false;
			}
		} while (!ok);

		// filter
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				int floor = 0;
				for (int xx = -1; xx <= 1; xx++) {
					for (int yy = -1; yy <= 1; yy++) {
						if (xx == 0 && yy == 0)
							continue;
						if (getTile(x + xx, y + yy) == TileType.FLOOR) {
							floor++;
						}
					}
				}

				if (floor == 2
						&& ((getTile(x - 1, y) == TileType.FLOOR && getTile(
								x + 1, y) == TileType.FLOOR) || (getTile(x,
								y - 1) == TileType.FLOOR && getTile(x, y + 1) == TileType.FLOOR))) {
					setTile(x, y, TileType.FLOOR);
				}
			}
		}
	}

	private void clear() {
		fill(TileType.VOID);
		addList.clear();
		removeList.clear();
		crawlerList.clear();
		rooms.clear();
	}

	private void fill(TileType tile) {
		fillArea(0, 0, getWidth(), getHeight(), tile);
	}

	private void fillArea(int x1, int y1, int w, int h, TileType tile) {
		int x2 = x1 + w;
		int y2 = y1 + h;
		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				map[x][y] = tile;
			}
		}
	}

	private void generate() {
		addCrawler(new MapBuilderCrawler(this));
		while (generateStep())
			;
	}

	private boolean generateStep() {
		boolean survivors = false;
		for (MapBuilderCrawler crawler : crawlerList) {
			survivors = true;
			if (!crawler.update()) {
				removeList.add(crawler);
			}
		}

		for (MapBuilderCrawler crawler : addList) {
			survivors = true;
			crawlerList.add(crawler);
		}
		addList.clear();

		for (MapBuilderCrawler crawler : removeList) {
			crawlerList.remove(crawler);
		}
		removeList.clear();

		return survivors;
	}

	MapBuilderConfiguration getConfig() {
		return config;
	}

	public int getHeight() {
		return config.height;
	}

	int getNextRoomID() {
		return rooms.size();
	}

	public BufferedImage getPreviewImage() {
		int tileWidh = 4;
		int tileHeight = 4;
		BufferedImage img = new BufferedImage(getWidth() * tileWidh,
				getHeight() * tileHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				Color color = null;
				switch (map[x][y]) {
				case FLOOR:
					color = Color.gray;
					break;
				case VOID:
					color = Color.black;
					break;
				case ROOM:
					color = Color.white;
					break;
				case DOOR:
					color = Color.blue;
					break;
				}
				g.setColor(color);
				g.fillRect(x * tileWidh, y * tileHeight, tileWidh, tileHeight);
			}
		}
		return img;
	}

	public MapRandom getRandom() {
		return random;
	}

	public TileType getTile(int x, int y) {
		try {
			return map[x][y];
		} catch (Exception e) {
			return TileType.OUT_OF_BOUNDS;
		}
	}

	public int getWidth() {
		return config.width;
	}

	@Override
	public Iterator<Room> iterator() {
		return rooms.iterator();
	}

	void setTile(int x, int y, TileType tile) {
		map[x][y] = tile;
	}

	public boolean validate() {
		int size = getWidth() * getHeight();
		int walls = 0;
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (getTile(x, y) == TileType.VOID) {
					walls++;
				}
			}
		}
		int per = (walls * 1000) / size;
		System.out.println("validate percent: " + per);
		return per < config.validatePercent;
	}
}
