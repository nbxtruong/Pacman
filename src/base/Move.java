package base;

import java.awt.*;

public interface Move {
	public Point getDir();

	public int getSpeed();

	public void setDir(Point p);

	public void setSpeed(int i);
}
