package game;

import java.awt.*;

import base.Drawable;

public interface GameBoard {
	public abstract void addDrawableEntity(Drawable e);

	public abstract void removeDrawableEntity(Drawable e);

	public abstract void paint(Graphics g);
}
