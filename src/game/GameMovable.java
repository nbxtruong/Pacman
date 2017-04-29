package game;

import java.awt.*;

import base.MoveDefault;
import base.Move;
import base.Movable;

//Proxy using GameMovableDrivers
public abstract class GameMovable implements Movable {
	Point position = new Point(50, 50);
	Move move = new MoveDefault(new Point(0, 0), 0);
	GameMovableDriver moveDriver = new GameMovableDriverDefault();

	public void setPos(Point p) {
		position = p;
	}

	public Point getPos() {
		return position;
	}

	public void setMove(Move m) {
		move = m;
	}

	public Move getMove() {
		return move;
	}

	public void setDriver(GameMovableDriver drv) {
		moveDriver = drv;
	}

	// assign and apply the Move coming from the Driver
	public void animate() {
		Move m = moveDriver.getMove(this);
		move.setDir(m.getDir());
		move.setSpeed(m.getSpeed());
		position.translate(
				(int) move.getDir().getX()
				* move.getSpeed(), 
				(int) move.getDir().getY()
				* move.getSpeed());
		animateHandler();
	}

	public abstract void animateHandler();
}



