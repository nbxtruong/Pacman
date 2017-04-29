package game;

public interface Game {
	public void start();

	public void initialize();

	public void restore();

	public void save();

	public void suspend();

	public void resume();
}
