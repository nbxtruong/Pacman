package pacman;

import game.CanvasDefault;
import game.GameEntity;
import game.GameMovable;

import java.awt.*;

import base.Collideable;
import base.Drawable;

public class Ghost extends GameMovable 
                   implements Drawable, GameEntity, Collideable {
	protected static Image image = 
		 Toolkit.getDefaultToolkit().createImage("ghost.gif");
	protected CanvasDefault canvas;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean animate = true;
	protected int afraidTimer = 0;
	protected int maxAfraidTimer = 0;
	protected boolean alive = true;

	public boolean isAfraid() {
		return afraidTimer > 0;
	}

	public void setAfraid(int timer) {
		maxAfraidTimer = afraidTimer = timer;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean aliveState) {
		alive = aliveState;
	}

	public Ghost(CanvasDefault c) {
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
		spriteType = 0;

		if (!isAlive())
			spriteType = 12;
		else if (afraidTimer > maxAfraidTimer / 2)
			spriteType = 4;
		else if (isAfraid())
			spriteType = 8;

		if (tmp.getX() == -1)
			spriteType += 1;
		else if (tmp.getY() == 1)
			spriteType += 3;
		else if (tmp.getY() == -1)
			spriteType += 2;

		g.drawImage(image, (int) getPos().getX(), (int) getPos().getY(),
				(int) getPos().getX() + 32, (int) getPos().getY() + 32,
				spriteNumber * 32, spriteType * 32, (spriteNumber + 1) * 32,
				(spriteType + 1) * 32, null);
	}

	public void animateHandler() {
		if (animate) {
			spriteNumber++;
			spriteNumber = spriteNumber % 6;
			if (isAfraid())
				afraidTimer--;
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, 32, 32));
	}
}
