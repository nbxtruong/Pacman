package pacman;

import java.awt.*;

import base.Drawable;
import base.Obstacle;
import game.CanvasDefault;

public class Wall implements Drawable, Obstacle {
	int x, y;
	protected static Image image = 
		Toolkit.getDefaultToolkit().createImage("wall.gif");
	CanvasDefault canvas;

	public Wall(CanvasDefault c, int xx, int yy) {
		canvas = c;
		x = xx; y = yy;
		MediaTracker tracker = new MediaTracker(c);
		int id = 2;
		tracker.addImage(image, id);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, 32, 32, null);
	}

	public Point getPos() {
		return (new Point(x, y));
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, 32, 32));
	}
}
