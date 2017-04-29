package game;

import base.Collideable;

public interface CollisionChecker {
	public abstract void addCollideable(Collideable p);

	public abstract void removeCollideable(Collideable p);

	public abstract void setCollisionRules(CollisionRules collisionRules);

	public abstract void computeAllCollisions();
}
