package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import map.builder.MapBuilder;
import map.builder.MapRandom;
import map.builder.TileType;
import obj.Door;
import obj.Floor;
import obj.GameObject;
import obj.Player;
import obj.Walker;
import obj.Wall;
import obj.Door.DoorState;
import obj.Floor.FloorType;
import obj.Player.Visibility;

public class Map {

	public int mw, mh;
	// Tile map[][];
	private Vector<GameObject> obj[][];
	public int th;
	public int tw;
	private MapRandom rnd;
	private Vector<GameObject> update = new Vector<GameObject>();

	@SuppressWarnings("unchecked")
	public Map(MapBuilder map, int tfw, int tfh) {
		rnd = map.getRandom();
		th = tfh;
		tw = tfw;
		mw = map.getWidth();
		mh = map.getHeight();
		obj = new Vector[mw][mh];
		for (int x = 0; x < mw; x++) {
			for (int y = 0; y < mh; y++) {
				obj[x][y] = new Vector<GameObject>();

			}
		}

		for (int x = 0; x < mw; x++) {
			for (int y = 0; y < mh; y++) {
				GameObject o = null;
				TileType tile = map.getTile(x, y);
				switch (tile) {
				case FLOOR:
					o = new Floor(FloorType.CORRIDOR);
					break;
				case ROOM:
					o = new Floor(FloorType.ROOM);
					break;
				case DOOR:
					obj[x][y].add(new Floor(FloorType.CORRIDOR));
					o = new Door(DoorState.OPEN);
					break;
				case VOID:
					o = new Wall();
					break;
				default:
					break;
				}
				obj[x][y].add(o);
			}
		}

	}

	int curUpdate;

	public Player update() {

		for (; curUpdate < update.size(); curUpdate++) {

			GameObject o = update.elementAt(curUpdate);
			if (o.update()) {
				if (o.getClass() == Player.class) {
					curUpdate++;
					return (Player) o;
				}
			}
		}

		if (curUpdate == update.size()) {
			// fim da lista
			curUpdate = 0;
		}
		return null;
	}

	public GameObject getTop(int x, int y) {
		return obj[x][y].lastElement();
	}

	public Vector<Point> listOfType(Class<? extends GameObject> t)// , Object
	// subtype)
	{
		Vector<Point> v = new Vector<Point>();
		for (int x = 0; x < mw; x++) {
			for (int y = 0; y < mh; y++) {

				GameObject o = obj[x][y].lastElement();
				if (o.getClass() == t) {
					v.add(new Point(x, y));
				}

			}
		}
		return v;
	}

	public void move(GameObject o, int nx, int ny) {
		if (nx >= 0 && ny >= 0 && nx < mw && ny < mh && o.getPosX() >= 0
				&& o.getPosY() >= 0) {
			if (!obj[o.getPosX()][o.getPosY()].remove(o)) {
				throw new RuntimeException("hummm isso naaao!");
			}
			// FIXME put security verify
		}
		o.setPosX(nx);
		o.setPosY(ny);

		obj[nx][ny].add(o);

	}

	public void paint(Graphics g, int fx, int fy, int fw, int fh, int ox,
			int oy, Visibility visible[][]) {
		paint(g, tw, th, fx, fy, fw, fh, ox, oy, visible, false);
	}

	public void paint(Graphics g, int tw, int th, int fx, int fy, int fw,
			int fh, int ox, int oy, Visibility visible[][], boolean rects) {
		g.setClip(fx, fy, fw, fh);
		int mx = fx - ox;
		int my = fy - oy;
		int cx = mx;
		for (int x = 0; x < mw; x++) {
			Vector<GameObject> xo[] = obj[x];
			Visibility xv[] = visible[x];

			if (((cx + tw) > fx) && (cx < (fx + fw))) {
				int cy = my;
				for (int y = 0; y < mh; y++) {
					if (((cy + th) > fy) && (cy < (fy + fh))) {
						Vector<GameObject> vec = xo[y];
						GameObject o = null;
						Visibility v = xv[y];
						if (v == Visibility.DISCOVERED) {
							for (int z = vec.size() - 1; z >= 0; z--) {
								o = vec.elementAt(z);
								if (o.isShowOutOfSight()) {
									break;
								}
								o = null;
							}

						} else {
							o = vec.lastElement();
						}

						if (o != null) {
							Color c = null;
							switch (v) {
							case DISCOVERED:
								c = new Color(o.getColor().getRed() >> 2, o
										.getColor().getGreen() >> 2, o
										.getColor().getBlue() >> 2);

								break;
							case VISIBLE:
								c = o.getColor();
								break;

							case HIDDEN:
								c = Color.black;
								break;
							}

							g.setColor(c);
							if (rects) {
								g.fillRect(cx, cy, tw, th);
							} else {
								String so = "" + o.getCharacter();
								g.drawString(so, cx, cy + th);
							}
						}

					}
					cy += th;
				}
			}
			cx += tw;
		}
	}

	public boolean passable(int x, int y) {
		for (GameObject o : obj[x][y]) {
			if (!o.isPassable()) {
				return false;
			}
		}
		return true;
	}

	public void remove(GameObject o, int x, int y) {
		if (!obj[x][y].remove(o)) {
			throw new RuntimeException();
		}
		if (o.isUpdateable()) {
			if (!update.remove(o)) {
				throw new RuntimeException();
			}
		}
	}

	public GameObject tileUnderWalker(Walker w) {
		Vector<GameObject> v = obj[w.getPosX()][w.getPosY()];
		return v.elementAt(v.size() - 2);
	}

	public void put(GameObject o, int x, int y) {
		obj[x][y].add(o);
		o.setPosX(x);
		o.setPosY(y);
		o.setMap(this);
	}

	public int getHeight() {

		return mh;
	}

	public int getWidth() {
		return mw;
	}

};