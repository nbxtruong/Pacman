package game;

public interface Universe {

	public abstract void addGameEntity(GameEntity gameEntity);

	public abstract void removeGameEntity(GameEntity gameEntity);

	public abstract void animate();

	public abstract void collision();

	public abstract int getValues();
}
