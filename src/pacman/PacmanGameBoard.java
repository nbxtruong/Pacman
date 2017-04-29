package pacman;

import game.CanvasDefault;
import game.GameBoardDefault;

public class PacmanGameBoard extends GameBoardDefault {
	public PacmanGameBoard(CanvasDefault defaultCanvas) {
		super(defaultCanvas);
		createBackgroundImage("image.gif");
	}
}
