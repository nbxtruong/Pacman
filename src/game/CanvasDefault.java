package game;

import java.awt.*;

//default Canvas are links towards a GameBoard
public class CanvasDefault extends Canvas {

	private static final long serialVersionUID = 1L;
	private GameBoard _gameBoard;
	private Graphics _gc;

	public void setGameBoard(GameBoard gameBoard) {
		this._gameBoard = gameBoard;
		_gc = getGraphics();
	}

	public void paint(Graphics g) {
		try {
			_gameBoard.paint(g);
		} catch (NullPointerException e) {
		}
	}

	public void repaint() {
		paint(_gc);
	}
}
