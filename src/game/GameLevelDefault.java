package game;

import game.World;

public abstract class GameLevelDefault extends Thread implements GameLevel {
	protected GameBoard gameBoard;
	protected CanvasDefault canvas;
	protected World world;
	protected Universe universe;

	public GameLevelDefault(CanvasDefault c) {
		canvas = c;
	}

	protected abstract void init();

	public void start() {
		init();
		super.start();
		try {
			super.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		boolean stopGameLoop = false;
		while (!stopGameLoop) {
			drawing();
			universe.animate();
			universe.collision();
			evolution();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}

	protected void evolution() {
	}

	protected void collision() {
	}

	protected void collision_handler() {
	}

	protected void drawing() {
		canvas.repaint();
	}
}
