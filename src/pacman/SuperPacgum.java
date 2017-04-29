package pacman;

import game.CanvasDefault;
import game.GameEntity;

import java.awt.*;

import base.Collideable;
import base.Drawable;

public class SuperPacgum implements Drawable, GameEntity, Collideable {
	protected static Image image = 
		Toolkit.getDefaultToolkit().createImage("superpacgum.gif");
	protected CanvasDefault defaultCanvas;
	protected Point position;

	public SuperPacgum(CanvasDefault defaultCanvas, Point pos) {
		this.defaultCanvas = defaultCanvas;
		position = pos;

		MediaTracker tracker = new MediaTracker(defaultCanvas);
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
		return (new Rectangle((int) position.getX(), 
							  (int) position.getY(),
							  32, 32));
	}
}
