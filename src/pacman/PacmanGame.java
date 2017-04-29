package pacman;

import game.*;

import java.awt.*;

public class PacmanGame implements Game {
	private CanvasDefault canvas;
	private Frame frame;
	private GameLevel gl;

	public void initialize() {
		canvas = new CanvasDefault();
		canvas.setSize(640, 640);
		frame = new Frame("Addictive Idiotic Game");
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}

	public void start() {
		gl = new PacmanGameLevelDefault(canvas);
		gl.start();
	}

	public void restore() {
	}

	public void save() {
	}

	public void suspend() {
	}

	public void resume() {
	}
}
