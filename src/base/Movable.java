package base;

import java.awt.*;

public interface Movable extends BoxBoundedObject {
	public void animate();

	public Point getPos();

	public Move getMove();

	public void setMove(Move m);
}
