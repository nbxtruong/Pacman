package game;

import java.awt.*;

import base.MovingStrategyDefault;
import base.MovingStrategyKeyboard;
import base.Move;
import base.Movable;
import base.MovingStrategy;

public class GameMovableDriverDefault implements GameMovableDriver {
	protected ObstacleChecker obstacleChecker;
	protected MovingStrategy strategy;

	public GameMovableDriverDefault() {
		obstacleChecker = new ObstacleCheckerDefault();
		strategy = new MovingStrategyDefault();
	}

	public void setStrategy(MovingStrategy s) {
		strategy = s;
	}

	public void setObstacleChecker(ObstacleChecker o) {
		obstacleChecker = o;
	}

	public Move getMove(Movable m) {
		Move move = strategy.getMove();
		if (strategy instanceof MovingStrategyKeyboard)
			((MovingStrategyKeyboard) strategy).lock();
		try {
			obstacleChecker.moveValidation(m, move);
		} catch (IllegalMoveException e1) {
			move = m.getMove();
			try {
				obstacleChecker.moveValidation(m, move);
			} catch (IllegalMoveException e2) { //stop moving
				move.setDir(new Point(0, 0));
				move.setSpeed(0);
			}
		}
		if (strategy instanceof MovingStrategyKeyboard) {
			((MovingStrategyKeyboard) strategy).unlock();
		}
		return move;
	}
}





