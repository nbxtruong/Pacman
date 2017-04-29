package pacman;

import game.GameEntity;

import java.awt.*;
import base.Collideable;

public class Jail implements GameEntity, Collideable {
	protected Point position;

	public Jail(Point pos) {
		position = pos;
	}

	public Point getPos() {
		return position;
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), 
				              (int) position.getY(),
			                   32, 32));
	}
}
