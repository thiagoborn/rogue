package scene;

import java.awt.Graphics;

import applet.AppletBase;


// comment

public abstract class Scene {

	AppletBase base;
	public Scene prev;

	public Scene() {
		base = AppletBase.instance;
	}

	abstract public void activate();

	abstract public void deactivate();

	abstract public void onEnter();

	abstract public void onExit();

	abstract public void render(Graphics g);

	abstract public void update(int diftime);
}
