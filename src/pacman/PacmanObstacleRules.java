package pacman;

import game.IllegalMoveException;
import game.ObstacleRules;

import java.util.*;
import java.lang.reflect.*;

import base.Movable;
import base.Obstacle;

public class PacmanObstacleRules implements ObstacleRules {

	public void obstacleEncountered(Vector<Obstacle> obs, Movable m)
			throws IllegalMoveException {
		for (int i = 0; i < obs.size(); i++) {
			try {
				obstacle(m, (Obstacle) obs.elementAt(i));
			} catch (Exception e) { // simple explicit transmission
				throw new IllegalMoveException();
			}
		}
	}

	public void obstacle(Ghost g, Wall w) throws IllegalMoveException {
		if (g.isAlive())
			throw new IllegalMoveException();
	}

	public void obstacle(Movable e1, Obstacle e2) throws Exception {
		Object[] param = new Object[2];
		Class<?>[] paramClass = new Class[2];
		Class<?> receiverClass = this.getClass();
		param[0] = e1;
		paramClass[0] = e1.getClass();
		param[1] = e2;
		paramClass[1] = e2.getClass();
		Method m = null;
		try {
			m = receiverClass.getMethod("obstacle", paramClass);
			m.invoke(this, param);
		} catch (Exception e) {
			throw e;
		}
	}
}
