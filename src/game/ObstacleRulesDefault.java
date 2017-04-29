package game;

import java.util.*;
 
import base.*;

public class ObstacleRulesDefault implements ObstacleRules {
	public void obstacleEncountered(Vector<Obstacle> obs, Movable m)
			throws IllegalMoveException {
		throw new IllegalMoveException();
	}
}
