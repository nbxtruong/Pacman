package pacman;

import game.*;

import java.awt.*;

import base.Collideable;
import base.Drawable;

public class Pacman extends GameMovable 
                    implements Drawable, GameEntity, Collideable {
	protected Image image = 
		Toolkit.getDefaultToolkit().createImage("pac1.gif");
	protected CanvasDefault canvas;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean animate = true;
	protected boolean vulnerable = false;
	protected int vulnerableTimer = 0;

	public void setUnvulnerable(int timer) {
		vulnerableTimer = timer;
	}

	public boolean isVulnerable() {
		return (vulnerableTimer <= 0);
	}

	public Pacman(CanvasDefault c) {
		canvas = c;
		MediaTracker tracker = new MediaTracker(c);
		int id = 1;
		tracker.addImage(image, id);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}
	}

	public void draw(Graphics g) {
		Point tmp = getMove().getDir();
		animate = true;
		if (tmp.getX() == 1) {
			spriteType = (isVulnerable() ? 0 : 4);
		} else if (tmp.getX() == -1) {
			spriteType = (isVulnerable() ? 1 : 5);
		} else if (tmp.getY() == 1) {
			spriteType = (isVulnerable() ? 3 : 7);
		} else if (tmp.getY() == -1) {
			spriteType = (isVulnerable() ? 2 : 6);
		} else {
			spriteType = 9;
			spriteNumber = 0;
			animate = false;
		}
		g.drawImage(image, (int) getPos().getX(), 
							(int) getPos().getY(),
							(int) getPos().getX() + 32, 
							(int) getPos().getY() + 32,
							spriteNumber * 32, 
							spriteType * 32, 
							(spriteNumber + 1) * 32,
							(spriteType + 1) * 32, 
							null);
	}

	public void animateHandler() {
		if (animate) {
			spriteNumber++;
			spriteNumber = spriteNumber % 6;
			if (!isVulnerable())
				vulnerableTimer--;
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, 32, 32));
	}
}
