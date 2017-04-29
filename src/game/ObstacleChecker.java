package game;

import base.Move;
import base.Movable;
import base.Obstacle;

public interface ObstacleChecker {
	public abstract void addObstacle(Obstacle p);

	public abstract void removeObstacle(Obstacle p);

	public abstract void setObstacleRules(ObstacleRules obstacleRules);

	public abstract void moveValidation(Movable m, Move mov)
			throws IllegalMoveException;
}
