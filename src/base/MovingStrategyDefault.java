package base;

import java.awt.Point;

public class MovingStrategyDefault implements MovingStrategy {
	public Move getMove() {
		return (new MoveDefault(new Point(0, 0), 0));
	}
}
