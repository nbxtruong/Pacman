package game;


import game.World;

import java.util.*;
import java.util.Vector;

import base.Collideable;
import base.Drawable;
import base.Movable;

public class UniverseDefault implements Universe {
	private World world;
	private Vector<GameEntity> gameEntities = new Vector<GameEntity>();
	private GameBoard gameBoard;
	private CollisionChecker collisionChecker;

	public UniverseDefault(World w, GameBoard g, CollisionChecker col) {
		world = w;
		gameBoard = g;
		collisionChecker = col;
	}

	public void addGameEntity(GameEntity gameEntity) {
		gameEntities.add(gameEntity);
		if (gameEntity instanceof Drawable)
			gameBoard.addDrawableEntity((Drawable) gameEntity);
		if (gameEntity instanceof Collideable)
			collisionChecker.addCollideable((Collideable) gameEntity);
	}

	public void removeGameEntity(GameEntity gameEntity) {
		gameEntities.remove(gameEntity);
		if (gameEntity instanceof Drawable)
			gameBoard.removeDrawableEntity((Drawable) gameEntity);
		if (gameEntity instanceof Collideable)
			collisionChecker.removeCollideable((Collideable) gameEntity);
	}

	public void animate() {
		Iterator<GameEntity> iterator = gameEntities.iterator();
		for (; iterator.hasNext();) {
			GameEntity tmp = iterator.next();
			if (tmp instanceof Movable)
				((Movable) tmp).animate();
		}
	}

	public void collision() {
		collisionChecker.computeAllCollisions();
	}

	public int getValues(){
		return gameEntities.size();
	}
}
