package game;

import java.awt.*;
import java.util.*;

import base.Drawable;

//a kind of Composite of Drawable Entities :
public class GameBoardDefault implements GameBoard {
	protected Image image = Toolkit.getDefaultToolkit().createImage(
			"image.gif");
	private Image _buffer;
	private Graphics _bufferGraphics;
	protected Vector<Drawable> drawableEntities = new Vector<Drawable>();
	protected CanvasDefault defaultCanvas;

	public GameBoardDefault(CanvasDefault defaultCanvas) {
		this.defaultCanvas = defaultCanvas;
		_buffer = defaultCanvas.createImage(defaultCanvas.getWidth(),
				defaultCanvas.getHeight());
		_bufferGraphics = _buffer.getGraphics();
	}

	protected void createBackgroundImage(String backgroundName) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		image = toolkit.createImage(backgroundName);
		MediaTracker tracker = new MediaTracker(defaultCanvas);
		int id = 0;
		tracker.addImage(image, id);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}
	}

	public void addDrawableEntity(Drawable e) {
		drawableEntities.addElement(e);
	}

	public void removeDrawableEntity(Drawable e) {
		drawableEntities.removeElement(e);
	}

	public void paint(Graphics g) {
		_bufferGraphics.drawImage(image, 0, 0, defaultCanvas.getWidth(),
				defaultCanvas.getHeight(), defaultCanvas);
		Iterator<Drawable> it = drawableEntities.iterator();
		for (; it.hasNext();) {
			(it.next()).draw(_bufferGraphics);
		}
		g.drawImage(_buffer, 0, 0, defaultCanvas.getWidth(), defaultCanvas
				.getHeight(), null);
	}

}








