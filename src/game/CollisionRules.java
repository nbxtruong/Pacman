package game;

import java.util.*;

import base.Collideable;

//Determine what happens when collisions occur
public interface CollisionRules {
	public abstract void setUniverse(Universe universe);

	public abstract void collisionProcessing(Vector<Collideable> collideables);
}
