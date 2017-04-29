package pacman;

import game.*;

import java.awt.*;

import base.*;

public class PacmanGameLevelDefault extends GameLevelDefault {

	//0: pacgums, 1 : walls, 2: superpacgums, 3: jail, 4: nothing
	static int[][] tab = {
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1 },
		{ 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 1, 0, 1, 4, 4, 4, 1, 0, 1, 0, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 0, 1, 0, 1, 4, 3, 4, 1, 0, 1, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
		{ 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1 },
		{ 1, 0, 1, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
		{ 1, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, };
 
	protected void init() {
		gameBoard = new PacmanGameBoard(canvas);

		ObstacleChecker obstacleChecker = new ObstacleCheckerDefault();
		CollisionChecker collisionChecker = new CollisionCheckerDefault();
		CollisionRules collisionRules = new PacmanCollisionRules();

		obstacleChecker.setObstacleRules(new PacmanObstacleRules());
		collisionChecker.setCollisionRules(collisionRules);
		canvas.setGameBoard(gameBoard);

		world = new WorldDefault(gameBoard, obstacleChecker);
		universe = new UniverseDefault(world, gameBoard, collisionChecker);

		collisionRules.setUniverse(universe);

		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				if (tab[j][i] == 0)
					universe.addGameEntity(new Pacgum(canvas, new Point(
							i * 32, j * 32)));
				if (tab[j][i] == 1)
					world.addObstacle(new Wall(canvas, i * 32, j * 32));
				if (tab[j][i] == 2)
					universe.addGameEntity(new SuperPacgum(canvas,
							new Point(i * 32, j * 32)));
				if (tab[j][i] == 3)
					universe.addGameEntity(new Jail(new Point(i * 32, j * 32)));
			}

		//Pacman definitions
		Pacman myPac = new Pacman(canvas);
		GameMovableDriverDefault pacDriv = new GameMovableDriverDefault();
		MovingStrategyKeyboard keyStr = new MovingStrategyKeyboard();
		pacDriv.setStrategy(keyStr);
		pacDriv.setObstacleChecker(obstacleChecker);
		canvas.addKeyListener(keyStr);
		myPac.setDriver(pacDriv);
		myPac.setPos(new Point(32, 32));
		universe.addGameEntity(myPac);

		//Ghosts definitions
		Ghost myGhost;
		int nbGhosts = 5;
		GameMovableDriverDefault ghostDriv = new PacmanGhostMovableDriver();
		MovingStrategyRandom ranStr = new MovingStrategyRandom();
		ghostDriv.setStrategy(ranStr);
		ghostDriv.setObstacleChecker(obstacleChecker);
		for (int t = 0; t < nbGhosts; t++) {
			myGhost = new Ghost(canvas);
			myGhost.setDriver(ghostDriv);
			myGhost.setPos(new Point(10 * 32, 10 * 32));
			universe.addGameEntity(myGhost);
			((PacmanCollisionRules) collisionRules).addGhost(myGhost);
		}
	}

	public PacmanGameLevelDefault(CanvasDefault defaultCanvas) {
		super(defaultCanvas);
	}
}
