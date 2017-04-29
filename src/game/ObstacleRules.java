package game;

import java.util.*;

import base.Movable;
import base.Obstacle;

//Determine what happens when obstacles are met
public interface ObstacleRules {
	public abstract void obstacleEncountered(Vector<Obstacle> obs,
			Movable m) throws IllegalMoveException;
}
