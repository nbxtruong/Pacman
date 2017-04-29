package base;

import java.awt.*;

public class MoveDefault implements Move {
	Point _direction;
	int _speed;

	public MoveDefault(Point p, int speed) {
		_direction = p;
		_speed = speed;
	}

	public Point getDir() {
		return _direction;
	}

	public int getSpeed() {
		return _speed;
	}

	public void setDir(Point p) {
		_direction = p;
	}

	public void setSpeed(int i) {
		_speed = i;
	}
}
