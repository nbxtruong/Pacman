package game;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

import base.*;

public class CollisionCheckerDefault implements CollisionChecker {
	private Vector _listCollideables;
	private Vector _listMovables;
	private Vector _listMovablesTmp;
	private CollisionRules _collisionRules;

	public CollisionCheckerDefault() {
		_listCollideables = new Vector();
		_listMovables = new Vector();
	}

	public void addCollideable(Collideable p) {
		if (p instanceof Movable)
			_listMovables.add(p);
		else
			_listCollideables.add(p);
	}

	public void removeCollideable(Collideable p) {
		if (p instanceof Movable)
			_listMovables.remove(p);
		else
			_listCollideables.remove(p);
	}

	public void setCollisionRules(CollisionRules c) {
		_collisionRules = c;
	}

	public void computeAllCollisions() {
		Vector result = new Vector();
		_listMovablesTmp = new Vector(_listMovables);
		Iterator iterator = _listMovables.iterator();

		for (; iterator.hasNext();) {
			Collideable tmp = (Collideable) iterator.next();
			computeOneCollision(tmp, result);
			_listMovablesTmp.remove(tmp);
		}
		_collisionRules.collisionProcessing(result);
	}

	public void computeOneCollision(Collideable collideable, Vector result) {
		Area collideableArea, targetArea;
		Rectangle boundingBoxTarget, boundingBoxCollideable;

		//Compute the intersection with Obstacles
		Shape intersectShape;
		if (collideable instanceof Movable)
			intersectShape = IntersectTools.getIntersectShape(
					(Movable) collideable, new MoveDefault(
							((Movable) collideable).getMove().getDir(), -1
									* ((Movable) collideable).getMove()
											.getSpeed()));
		else
			intersectShape = collideable.getBoundingBox();

		collideableArea = new Area(intersectShape);
		boundingBoxCollideable = intersectShape.getBounds();

		Iterator iterator = _listCollideables.iterator();
		while (iterator.hasNext()) {
			Collideable targetCollideable = (Collideable) iterator.next();
			if (targetCollideable != collideable) {
				Shape targetShape;
				targetShape = targetCollideable.getBoundingBox();
				boundingBoxTarget = targetShape.getBounds();
				if (boundingBoxCollideable.intersects(boundingBoxTarget)) {
					targetArea = new Area(targetShape);
					targetArea.intersect(collideableArea);
					if (!targetArea.isEmpty()) {
						Vector<Collideable> res = new Vector<Collideable>();
						res.add(collideable);
						res.add(targetCollideable);
						result.add(res);
					}
				}
			}
		}

		iterator = _listMovablesTmp.iterator();
		while (iterator.hasNext()) {
			Collideable targetColideable = (Collideable) iterator.next();
			if (targetColideable != collideable) {
				Shape targetShape;
				targetShape = IntersectTools.getIntersectShape(
						(Movable) targetColideable,
						new MoveDefault(((Movable) targetColideable).getMove()
								.getDir(), -((Movable) targetColideable)
								.getMove().getSpeed()));
				
				boundingBoxTarget = targetShape.getBounds();
				if (boundingBoxCollideable.intersects(boundingBoxTarget)) {
					targetArea = new Area(targetShape);
					targetArea.intersect(collideableArea);
					if (!targetArea.isEmpty()) {
						Vector<Collideable> res = new Vector<Collideable>();
						res.add(collideable);
						res.add(targetColideable);
						result.add(res);
					}
				}
			}
		}
	}
}
