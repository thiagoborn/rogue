package applet;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Vector;

import scene.MapScene;

public class Game extends AppletBase {

	public enum GameState {
		Running, Waiting_User_Input,
	}

	private static final long serialVersionUID = 2870126228386851447L;;

	public static Game game() {

		return (Game) instance;
	}
	public Font font;

	public FontMetrics fm;

	/**
	 * 
	 */

	public Vector<AnimMessage> amsg;

	public Vector<String> msg;

	public MapScene scenemap;

	public Game() {
		font = new Font("Courier", Font.PLAIN, 13);
		fm = getFontMetrics(font);
		msg = new Vector<String>();
		amsg = new Vector<AnimMessage>();
		scenemap = new MapScene();
	}

	@Override
	public void activate() {
		setScene(scenemap);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Dimension getDimension() {
		return new Dimension(800, 600);
	}

	@Override
	public void paint(Graphics g) {
		//		
		// Color xorc = Color.black;
		// Color c = Color.white;
		//		
		// g.setColor(c);
		// g.setXORMode(xorc);
		// g.fillRect(0, 0, 100, 100);
		//			
		//		
		// g.fillRect(50, 50, 10, 10);
		//
		//				
		// g.setXORMode(xorc);
		// g.fillRect(0, 0, 100, 100);
		// g.setPaintMode();
	}

	public void postAnimMessage(String s, int tx, int ty) {
		int len = s.length();
		int ox = (len * scenemap.tw) >> 1;
		int x = (tx * scenemap.tw) - ox;
		x += scenemap.tw >> 1;

		int y = (scenemap.th * ty);
		amsg.add(new AnimMessage(x, y, s));
	}

	public void postMessage(String s) {
		if (s == null)
			return;
		if (msg.size() > 5) {
			msg.remove(0);
		}
		msg.add(s);
	}

	@Override
	public void update(int diftime) {
		// TODO Auto-generated method stub

	}

};
