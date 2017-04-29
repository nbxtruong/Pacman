package pacman;

import game.CanvasDefault;
import game.GameEntity;

import java.awt.*;

import base.Collideable;
import base.Drawable;

public class Pacgum implements Drawable, GameEntity, Collideable {
	protected static Image image = 
		Toolkit.getDefaultToolkit().createImage("pacgum.gif");
	protected CanvasDefault canvas;
	protected Point position;

	public Pacgum(CanvasDefault c, Point pos) {
		this.canvas = c;
		position = pos;

		MediaTracker tracker = new MediaTracker(c);
		int id = 3;
		tracker.addImage(image, id);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}
	}

	public Point getPos() {
		return position;
	}

	public void draw(Graphics g) {
		g.drawImage(image, (int) getPos().getX(), 
				           (int) getPos().getY(), 
				            32, 32, null);
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX() + 4, 
				              (int) position.getY() + 4, 
				              24, 24));
	}
}
