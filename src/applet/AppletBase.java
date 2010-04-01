package applet;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import scene.Scene;

public abstract class AppletBase extends Applet implements Runnable,
		KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1255928845665687823L;
	public static AppletBase instance;
	Image buff;
	private Scene curscene;
	boolean exit;
	private Vector<KeyEvent> kv;
	Graphics og;

	public AppletBase() {
		instance = this;
		kv = new Vector<KeyEvent>();
		addKeyListener(this);
		setIgnoreRepaint(true);
	}

	public abstract void activate() throws Exception;

	public String convertKeyCode(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_ENTER:
			return "VK_ENTER";
		case KeyEvent.VK_BACK_SPACE:
			return "VK_BACK_SPACE";
		case KeyEvent.VK_TAB:
			return "VK_TAB";
		case KeyEvent.VK_CANCEL:
			return "VK_CANCEL";
		case KeyEvent.VK_CLEAR:
			return "VK_CLEAR";
		case KeyEvent.VK_SHIFT:
			return "VK_SHIFT";
		case KeyEvent.VK_CONTROL:
			return "VK_CONTROL";
		case KeyEvent.VK_ALT:
			return "VK_ALT";
		case KeyEvent.VK_PAUSE:
			return "VK_PAUSE";
		case KeyEvent.VK_CAPS_LOCK:
			return "VK_CAPS_LOCK";
		case KeyEvent.VK_ESCAPE:
			return "VK_ESCAPE";
		case KeyEvent.VK_SPACE:
			return "VK_SPACE";
		case KeyEvent.VK_PAGE_UP:
			return "VK_PAGE_UP";
		case KeyEvent.VK_PAGE_DOWN:
			return "VK_PAGE_DOWN";
		case KeyEvent.VK_END:
			return "VK_END";
		case KeyEvent.VK_HOME:
			return "VK_HOME";
		case KeyEvent.VK_LEFT:
			return "VK_LEFT";
		case KeyEvent.VK_UP:
			return "VK_UP";
		case KeyEvent.VK_RIGHT:
			return "VK_RIGHT";
		case KeyEvent.VK_DOWN:
			return "VK_DOWN";
		case KeyEvent.VK_COMMA:
			return "VK_COMMA";
		case KeyEvent.VK_MINUS:
			return "VK_MINUS";
		case KeyEvent.VK_PERIOD:
			return "VK_PERIOD";
		case KeyEvent.VK_SLASH:
			return "VK_SLASH";
		case KeyEvent.VK_0:
			return "VK_0";
		case KeyEvent.VK_1:
			return "VK_1";
		case KeyEvent.VK_2:
			return "VK_2";
		case KeyEvent.VK_3:
			return "VK_3";
		case KeyEvent.VK_4:
			return "VK_4";
		case KeyEvent.VK_5:
			return "VK_5";
		case KeyEvent.VK_6:
			return "VK_6";
		case KeyEvent.VK_7:
			return "VK_7";
		case KeyEvent.VK_8:
			return "VK_8";
		case KeyEvent.VK_9:
			return "VK_9";
		case KeyEvent.VK_SEMICOLON:
			return "VK_SEMICOLON";
		case KeyEvent.VK_EQUALS:
			return "VK_EQUALS";
		case KeyEvent.VK_A:
			return "VK_A";
		case KeyEvent.VK_B:
			return "VK_B";
		case KeyEvent.VK_C:
			return "VK_C";
		case KeyEvent.VK_D:
			return "VK_D";
		case KeyEvent.VK_E:
			return "VK_E";
		case KeyEvent.VK_F:
			return "VK_F";
		case KeyEvent.VK_G:
			return "VK_G";
		case KeyEvent.VK_H:
			return "VK_H";
		case KeyEvent.VK_I:
			return "VK_I";
		case KeyEvent.VK_J:
			return "VK_J";
		case KeyEvent.VK_K:
			return "VK_K";
		case KeyEvent.VK_L:
			return "VK_L";
		case KeyEvent.VK_M:
			return "VK_M";
		case KeyEvent.VK_N:
			return "VK_N";
		case KeyEvent.VK_O:
			return "VK_O";
		case KeyEvent.VK_P:
			return "VK_P";
		case KeyEvent.VK_Q:
			return "VK_Q";
		case KeyEvent.VK_R:
			return "VK_R";
		case KeyEvent.VK_S:
			return "VK_S";
		case KeyEvent.VK_T:
			return "VK_T";
		case KeyEvent.VK_U:
			return "VK_U";
		case KeyEvent.VK_V:
			return "VK_V";
		case KeyEvent.VK_W:
			return "VK_W";
		case KeyEvent.VK_X:
			return "VK_X";
		case KeyEvent.VK_Y:
			return "VK_Y";
		case KeyEvent.VK_Z:
			return "VK_Z";
		case KeyEvent.VK_OPEN_BRACKET:
			return "VK_OPEN_BRACKET";
		case KeyEvent.VK_BACK_SLASH:
			return "VK_BACK_SLASH";
		case KeyEvent.VK_CLOSE_BRACKET:
			return "VK_CLOSE_BRACKET";
		case KeyEvent.VK_NUMPAD0:
			return "VK_NUMPAD0";
		case KeyEvent.VK_NUMPAD1:
			return "VK_NUMPAD1";
		case KeyEvent.VK_NUMPAD2:
			return "VK_NUMPAD2";
		case KeyEvent.VK_NUMPAD3:
			return "VK_NUMPAD3";
		case KeyEvent.VK_NUMPAD4:
			return "VK_NUMPAD4";
		case KeyEvent.VK_NUMPAD5:
			return "VK_NUMPAD5";
		case KeyEvent.VK_NUMPAD6:
			return "VK_NUMPAD6";
		case KeyEvent.VK_NUMPAD7:
			return "VK_NUMPAD7";
		case KeyEvent.VK_NUMPAD8:
			return "VK_NUMPAD8";
		case KeyEvent.VK_NUMPAD9:
			return "VK_NUMPAD9";
		case KeyEvent.VK_MULTIPLY:
			return "VK_MULTIPLY";
		case KeyEvent.VK_ADD:
			return "VK_ADD";
		case KeyEvent.VK_SEPARATER:
			return "VK_SEPARATER";
		case KeyEvent.VK_SUBTRACT:
			return "VK_SUBTRACT";
		case KeyEvent.VK_DECIMAL:
			return "VK_DECIMAL";
		case KeyEvent.VK_DIVIDE:
			return "VK_DIVIDE";
		case KeyEvent.VK_DELETE:
			return "VK_DELETE";
		case KeyEvent.VK_NUM_LOCK:
			return "VK_NUM_LOCK";
		case KeyEvent.VK_SCROLL_LOCK:
			return "VK_SCROLL_LOCK";
		case KeyEvent.VK_F1:
			return "VK_F1";
		case KeyEvent.VK_F2:
			return "VK_F2";
		case KeyEvent.VK_F3:
			return "VK_F3";
		case KeyEvent.VK_F4:
			return "VK_F4";
		case KeyEvent.VK_F5:
			return "VK_F5";
		case KeyEvent.VK_F6:
			return "VK_F6";
		case KeyEvent.VK_F7:
			return "VK_F7";
		case KeyEvent.VK_F8:
			return "VK_F8";
		case KeyEvent.VK_F9:
			return "VK_F9";
		case KeyEvent.VK_F10:
			return "VK_F10";
		case KeyEvent.VK_F11:
			return "VK_F11";
		case KeyEvent.VK_F12:
			return "VK_F12";
		case KeyEvent.VK_F13:
			return "VK_F13";
		case KeyEvent.VK_F14:
			return "VK_F14";
		case KeyEvent.VK_F15:
			return "VK_F15";
		case KeyEvent.VK_F16:
			return "VK_F16";
		case KeyEvent.VK_F17:
			return "VK_F17";
		case KeyEvent.VK_F18:
			return "VK_F18";
		case KeyEvent.VK_F19:
			return "VK_F19";
		case KeyEvent.VK_F20:
			return "VK_F20";
		case KeyEvent.VK_F21:
			return "VK_F21";
		case KeyEvent.VK_F22:
			return "VK_F22";
		case KeyEvent.VK_F23:
			return "VK_F23";
		case KeyEvent.VK_F24:
			return "VK_F24";
		case KeyEvent.VK_PRINTSCREEN:
			return "VK_PRINTSCREEN";
		case KeyEvent.VK_INSERT:
			return "VK_INSERT";
		case KeyEvent.VK_HELP:
			return "VK_HELP";
		case KeyEvent.VK_META:
			return "VK_META";
		case KeyEvent.VK_BACK_QUOTE:
			return "VK_BACK_QUOTE";
		case KeyEvent.VK_QUOTE:
			return "VK_QUOTE";
		case KeyEvent.VK_KP_UP:
			return "VK_KP_UP";
		case KeyEvent.VK_KP_DOWN:
			return "VK_KP_DOWN";
		case KeyEvent.VK_KP_LEFT:
			return "VK_KP_LEFT";
		case KeyEvent.VK_KP_RIGHT:
			return "VK_KP_RIGHT";
		case KeyEvent.VK_DEAD_GRAVE:
			return "VK_DEAD_GRAVE";
		case KeyEvent.VK_DEAD_ACUTE:
			return "VK_DEAD_ACUTE";
		case KeyEvent.VK_DEAD_CIRCUMFLEX:
			return "VK_DEAD_CIRCUMFLEX";
		case KeyEvent.VK_DEAD_TILDE:
			return "VK_DEAD_TILDE";
		case KeyEvent.VK_DEAD_MACRON:
			return "VK_DEAD_MACRON";
		case KeyEvent.VK_DEAD_BREVE:
			return "VK_DEAD_BREVE";
		case KeyEvent.VK_DEAD_ABOVEDOT:
			return "VK_DEAD_ABOVEDOT";
		case KeyEvent.VK_DEAD_DIAERESIS:
			return "VK_DEAD_DIAERESIS";
		case KeyEvent.VK_DEAD_ABOVERING:
			return "VK_DEAD_ABOVERING";
		case KeyEvent.VK_DEAD_DOUBLEACUTE:
			return "VK_DEAD_DOUBLEACUTE";
		case KeyEvent.VK_DEAD_CARON:
			return "VK_DEAD_CARON";
		case KeyEvent.VK_DEAD_CEDILLA:
			return "VK_DEAD_CEDILLA";
		case KeyEvent.VK_DEAD_OGONEK:
			return "VK_DEAD_OGONEK";
		case KeyEvent.VK_DEAD_IOTA:
			return "VK_DEAD_IOTA";
		case KeyEvent.VK_DEAD_VOICED_SOUND:
			return "VK_DEAD_VOICED_SOUND";
		case KeyEvent.VK_DEAD_SEMIVOICED_SOUND:
			return "VK_DEAD_SEMIVOICED_SOUND";
		case KeyEvent.VK_AMPERSAND:
			return "VK_AMPERSAND";
		case KeyEvent.VK_ASTERISK:
			return "VK_ASTERISK";
		case KeyEvent.VK_QUOTEDBL:
			return "VK_QUOTEDBL";
		case KeyEvent.VK_LESS:
			return "VK_LESS";
		case KeyEvent.VK_GREATER:
			return "VK_GREATER";
		case KeyEvent.VK_BRACELEFT:
			return "VK_BRACELEFT";
		case KeyEvent.VK_BRACERIGHT:
			return "VK_BRACERIGHT";
		case KeyEvent.VK_AT:
			return "VK_AT";
		case KeyEvent.VK_COLON:
			return "VK_COLON";
		case KeyEvent.VK_CIRCUMFLEX:
			return "VK_CIRCUMFLEX";
		case KeyEvent.VK_DOLLAR:
			return "VK_DOLLAR";
		case KeyEvent.VK_EURO_SIGN:
			return "VK_EURO_SIGN";
		case KeyEvent.VK_EXCLAMATION_MARK:
			return "VK_EXCLAMATION_MARK";
		case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
			return "VK_INVERTED_EXCLAMATION_MARK";
		case KeyEvent.VK_LEFT_PARENTHESIS:
			return "VK_LEFT_PARENTHESIS";
		case KeyEvent.VK_NUMBER_SIGN:
			return "VK_NUMBER_SIGN";
		case KeyEvent.VK_PLUS:
			return "VK_PLUS";
		case KeyEvent.VK_RIGHT_PARENTHESIS:
			return "VK_RIGHT_PARENTHESIS";
		case KeyEvent.VK_UNDERSCORE:
			return "VK_UNDERSCORE";
		case KeyEvent.VK_FINAL:
			return "VK_FINAL";
		case KeyEvent.VK_CONVERT:
			return "VK_CONVERT";
		case KeyEvent.VK_NONCONVERT:
			return "VK_NONCONVERT";
		case KeyEvent.VK_ACCEPT:
			return "VK_ACCEPT";
		case KeyEvent.VK_MODECHANGE:
			return "VK_MODECHANGE";
		case KeyEvent.VK_KANA:
			return "VK_KANA";
		case KeyEvent.VK_KANJI:
			return "VK_KANJI";
		case KeyEvent.VK_ALPHANUMERIC:
			return "VK_ALPHANUMERIC";
		case KeyEvent.VK_KATAKANA:
			return "VK_KATAKANA";
		case KeyEvent.VK_HIRAGANA:
			return "VK_HIRAGANA";
		case KeyEvent.VK_FULL_WIDTH:
			return "VK_FULL_WIDTH";
		case KeyEvent.VK_HALF_WIDTH:
			return "VK_HALF_WIDTH";
		case KeyEvent.VK_ROMAN_CHARACTERS:
			return "VK_ROMAN_CHARACTERS";
		case KeyEvent.VK_ALL_CANDIDATES:
			return "VK_ALL_CANDIDATES";
		case KeyEvent.VK_PREVIOUS_CANDIDATE:
			return "VK_PREVIOUS_CANDIDATE";
		case KeyEvent.VK_CODE_INPUT:
			return "VK_CODE_INPUT";
		case KeyEvent.VK_JAPANESE_KATAKANA:
			return "VK_JAPANESE_KATAKANA";
		case KeyEvent.VK_JAPANESE_HIRAGANA:
			return "VK_JAPANESE_HIRAGANA";
		case KeyEvent.VK_JAPANESE_ROMAN:
			return "VK_JAPANESE_ROMAN";
		case KeyEvent.VK_KANA_LOCK:
			return "VK_KANA_LOCK";
		case KeyEvent.VK_INPUT_METHOD_ON_OFF:
			return "VK_INPUT_METHOD_ON_OFF";
		case KeyEvent.VK_CUT:
			return "VK_CUT";
		case KeyEvent.VK_COPY:
			return "VK_COPY";
		case KeyEvent.VK_PASTE:
			return "VK_PASTE";
		case KeyEvent.VK_UNDO:
			return "VK_UNDO";
		case KeyEvent.VK_AGAIN:
			return "VK_AGAIN";
		case KeyEvent.VK_FIND:
			return "VK_FIND";
		case KeyEvent.VK_PROPS:
			return "VK_PROPS";
		case KeyEvent.VK_STOP:
			return "VK_STOP";
		case KeyEvent.VK_COMPOSE:
			return "VK_COMPOSE";
		case KeyEvent.VK_ALT_GRAPH:
			return "VK_ALT_GRAPH";
		case KeyEvent.VK_UNDEFINED:
			return "VK_UNDEFINED";
		default:
			return "UNDEFINED";
		}
	}

	public abstract void deactivate();

	public abstract Dimension getDimension();

	public Dimension getMinimumSize() {
		// return
		return getDimension();
	}

	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	public void keyPressed(KeyEvent e) {
		// System.out.println(convertKeyCode(e.getKeyCode()));
		pushKeyEvent(e);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public abstract void paint(Graphics g);

	public KeyEvent popKeyEvent() {
		synchronized (kv) {
			if (kv.size() > 0) {
				KeyEvent e = kv.elementAt(0);
				kv.remove(0);
				return e;
			}
		}
		return null;
	}

	public void popScene() {
		curscene.onExit();
		curscene.deactivate();
		Scene prev = curscene.prev;
		curscene.prev = null;
		prev.onEnter();
		curscene = prev;
	}

	public void pushKeyEvent(KeyEvent e) {
		synchronized (kv) {
			kv.add(e);
		}
	}

	public void pushScene(Scene s) {
		s.prev = curscene;
		curscene.onExit();
		s.activate();
		s.onEnter();
		curscene = s;
	}

	private void renderApplet(Graphics g) {
		if (curscene != null) {
			curscene.render(g);
		}
		paint(g);
	}

	public final void run() {
		// assume q o sistema de GUI no vai ficar pedindo para repintar
		// usar setIgnoreRepaint(true)
		long t = System.currentTimeMillis();
		while (!exit) {
			long tn = System.currentTimeMillis();
			long difTime = tn - t;
			t = tn;
			renderApplet(og);
			Graphics g = getGraphics();
			g.drawImage(buff, 0, 0, null);
			g.dispose();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			updateApplet((int) difTime);
		}
	}

	public void setScene(Scene s) {
		Scene w = curscene;
		while (w != null) {
			w.onExit();
			w.deactivate();
			w = w.prev;
		}
		s.activate();
		s.onEnter();
		curscene = s;
	}

	public final void start() {
		try {
			activate();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		Dimension d = getDimension();
		setSize(d);
		buff = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
		og = buff.getGraphics();
		exit = false;
		Thread t = new Thread(this);
		t.start();
	}

	public final void stop() {
		deactivate();
		exit = true;
	}

	public abstract void update(int diftime);

	private void updateApplet(int diftime) {
		update(diftime);
		if (curscene != null) {
			curscene.update(diftime);
		}
	}
}
