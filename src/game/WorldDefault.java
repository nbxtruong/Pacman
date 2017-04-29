package game;

import java.util.*;

import base.Drawable;
import base.Obstacle;
import game.GameBoard;

public class WorldDefault implements World {
	protected Vector<Obstacle> obstacleEntities = new Vector<Obstacle>();
	protected GameBoard gameBoard;
	private ObstacleChecker obstacleChecker;

	public WorldDefault(GameBoard g, ObstacleChecker o) {
		gameBoard = g;
		obstacleChecker = o;
	}

	public void addObstacle(Obstacle obs) {
		obstacleEntities.addElement(obs);
		if (obs instanceof Drawable)
			gameBoard.addDrawableEntity((Drawable) obs);
		obstacleChecker.addObstacle((Obstacle) obs);
	}

	public void removeObstacle(Obstacle obs) {
		obstacleEntities.removeElement(obs);
		if (obs instanceof Drawable)
			gameBoard.removeDrawableEntity((Drawable) obs);
		obstacleChecker.removeObstacle((Obstacle) obs);
	}
}
