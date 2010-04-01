package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import map.Map;
import map.builder.MapBuilder;
import obj.GameObject;
import obj.Player;
import applet.AnimMessage;
import applet.Camera;
import applet.Game;
import applet.Game.GameState;

public class MapScene extends Scene {
	private Camera cam;
	private Player cur;
	private int curUpdate = 0;
	private final int ffh = 450;
	private final int ffw = 600;
	private final int ffx = 10;
	private final int ffy = 10;
	private Game game;
	private int hph;
	private int hpw;
	private Map map;
	private final int minimapdoth = 2;
	private final int minimapdotw = 2;
	private int minimaph;
	private int minimapw;
	private final int minimapx = ffx + ffw + 10;
	private final int minimapy = ffy + 10;
	private int px, py;
	private GameState state;
	public int tw, th;

	@Override
	public void activate() {
		game = Game.game();
		state = GameState.Running;
		cam = new Camera();
		tw = game.fm.charWidth('\u2588');
		th = game.fm.getHeight();
		hpw = tw >> 1;
		hph = th >> 1;
		map = new Map(new MapBuilder(), tw, th);
		minimapw = map.mw * minimapdotw;
		minimaph = map.mh * minimapdoth;
		// map.initGen();
		genMap();
	}

	@Override
	public void deactivate() {
	}

	public void genMap() {
		cur = new Player();
		cur.initStats();
		map.put(cur, 0, 0);
		updateCamera(0);
		cam.setPosAsTarget();
	}

	@Override
	public void onEnter() {
	}

	@Override
	public void onExit() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, base.getWidth(), base.getHeight());
		g.setFont(game.font);
		// mapx = mapy = 0;
		map.paint(g, ffx, ffy, ffw, ffh, cam.getX(), cam.getY(), cur
				.getVisible());
		g.setColor(Color.red);
		for (int x = 0; x < game.amsg.size(); x++) {
			AnimMessage am = game.amsg.get(x);
			if (am.dist > 0) {
				int xx = ffx + am.x - cam.getX();
				int yy = ffy + am.y - (am.dist >> 10) - cam.getY();
				g.drawString(am.s, xx, yy);
			}
		}
		g.setClip(null);
		g.setColor(Color.white);
		g.drawRect(ffx, ffy, ffw, ffh);
		map.paint(g, minimapdotw, minimapdoth, minimapx, minimapy, minimapw,
				minimaph, 0, 0, cur.getVisible(), true);
		g.setClip(null);
		g.setColor(Color.white);
		g.drawRect(minimapx, minimapy, minimapw, minimaph);
		int wratio = (1024 * minimapdotw) / tw;
		int hratio = (1024 * minimapdoth) / th;
		int ifx = (cam.getX() * wratio) >> 10;
		int ify = (cam.getY() * hratio) >> 10;
		int ifw = (ffw * wratio) >> 10;
		int ifh = (ffh * hratio) >> 10;
		g.setColor(Color.red);
		g.drawRect(minimapx + ifx + 2, minimapy + ify + 2, ifw - 4, ifh - 4);
		int x = minimapx;
		int y = minimapy + minimaph;
		// g.drawString("pos: " + cur.x + "," + cur.y, x, y += th);
		// g.drawString("hp: " + cur.hp + "/" + cur.mhp, x, y += th);
		// g.drawString("ac: " + cur.ar, x, y += th);
		// g.drawString("at: " + cur.at, x, y += th);
		// g.drawString("de: " + cur.de, x, y += th);
		// g.drawString("st: " + cur.st, x, y += th);
		// g.drawString("speed: " + cur.speed, x, y += th);
		// g.drawString("hungry: " + ((100 * cur.hungryTimer) >> 16) + "%", x,
		// y += th);
		// g.drawString(cur.money + "$", x, y += th);
		y = ffh + ffy + 20;
		GameObject o = map.tileUnderWalker(cur);
		g.setColor(Color.green);
		g.drawString(o.getDescription(), ffx, y);
		y += th;
		x = ffx;
		g.setColor(Color.gray);
		for (String m : game.msg) {
			g.drawString(m, x, y);
			y += th;
		}
	}

	@Override
	public void update(int diftime) {
		updateCamera(diftime);
		// diftime = 10;
		AnimMessage last = null;
		for (int x = 0; x < game.amsg.size(); x++) {
			AnimMessage am = game.amsg.elementAt(x);
			if (last != null) {
				int ly = last.y - (last.dist >> 10);
				int my = am.y - (am.dist >> 10);
				int dist = my - ly;
				if (dist < th && dist >= 0) {
					break;
				}
				// System.out.println("x:"+x+",lasty:"+ly+",myy:"+my);
			}
			am.dist += diftime * 50;
			if (am.dist > (60 << 10)) {
				game.amsg.removeElementAt(x);
				x--;
			} else {
				last = am;
			}
		}
		switch (state) {
		case Running:
			Player w = null;
			w = map.update();
			if (w != null) {
				cur = w;
				state = GameState.Waiting_User_Input;
			} else {
				break;
			}
		case Waiting_User_Input:
			waitUserInput();
			break;
		default:
			break;
		}
	}

	public void updateCamera(int diftime) {
		int ox = (ffw >> 1);
		int oy = (ffh >> 1);
		px = (cur.getPosX() * tw) + hpw;
		py = (cur.getPosY() * th) + hph;
		int mapw = (map.mw) * tw;
		int maph = (map.mh) * th;
		int mapx = px - ox;
		int mapy = py - oy;
		if (mapx < 0) {
			mapx = 0;
		}
		if (mapy < 0) {
			mapy = 0;
		}
		if (mapx + ffw > mapw) {
			mapx = mapw - ffw;
		}
		if (mapy + ffh > maph) {
			mapy = maph - ffh;
		}
		cam.setTarget(mapx, mapy);
		cam.update(diftime);
	}

	public void waitUserInput() {
		KeyEvent e = null;
		boolean treated = false;
		while ((e = base.popKeyEvent()) != null) {
			// String m = null;
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				// cur.go(1);
				treated = true;
				break;
			case KeyEvent.VK_UP:
				// cur.go(3);
				treated = true;
				break;
			case KeyEvent.VK_LEFT:
				// cur.go(2);
				treated = true;
				break;
			case KeyEvent.VK_RIGHT:
				// .go(0);
				treated = true;
				break;
			default:
				switch (e.getKeyChar()) {
				case 'i':
					// game.sceneinv.setup(cur);
					// game.pushScene(game.sceneinv);
					break;
				case '5':
					treated = true;
					break;
				case ',':
					// GameObject o = map.tileUnderWalker(cur);
					// if (o.action(Event.PICK)) {
					// game
					// .postMessage("you picked the "
					// + o.getDescription());
					// game.postAnimMessage("picked", o.x, o.y);
					// cur.pick(o);
					// treated = true;
					// } else {
					// game.postMessage("there nothing to pick here");
					//
					// }
					break;
				}
				break;
			}
		}
		if (treated) {
			state = GameState.Running;
		}
	}
}
