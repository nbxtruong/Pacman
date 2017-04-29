package game;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

import base.*;

public class ObstacleCheckerDefault implements ObstacleChecker {
	private Vector<Obstacle> _listObstacles;
	private ObstacleRules _obstacleRules;

	public ObstacleCheckerDefault() {
		_listObstacles = new Vector<Obstacle>();
		_obstacleRules = new ObstacleRulesDefault();
	}

	public void addObstacle(Obstacle p) {
		_listObstacles.add(p);
	}

	public void removeObstacle(Obstacle p) {
		_listObstacles.remove(p);
	}

	public void setObstacleRules(ObstacleRules obstacleRules) {
		_obstacleRules = obstacleRules;
	}

	// Compute the intersection with all the Obstacles
	public void moveValidation(Movable m, Move mov)
			throws IllegalMoveException {
 		Shape intersectShape = IntersectTools.getIntersectShape(m, mov);
		Iterator<Obstacle> iterator = _listObstacles.iterator();
		Vector<Obstacle> result = new Vector<Obstacle>();
		Area myArea, tmpArea;
		myArea = new Area(intersectShape);
		Rectangle tmpInte;
		tmpInte = (intersectShape.getBounds());
		while (iterator.hasNext()) {
			Obstacle tmpObstacle = iterator.next();
			Rectangle tmpB = tmpObstacle.getBoundingBox();
			if (tmpInte.intersects(tmpB)) {
				tmpArea = new Area(tmpB);
				tmpArea.intersect(myArea);
				if (!tmpArea.isEmpty()) {
					result.add(tmpObstacle);
				}
			}
		}
		if (!result.isEmpty())
			_obstacleRules.obstacleEncountered(result, m);
	}
}










