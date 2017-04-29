package pacman;

import java.util.*;
import java.lang.reflect.*;

import game.CollisionRules;
import game.Universe;
import base.Collideable;

public class PacmanCollisionRules implements CollisionRules {

	protected Universe universe;
	protected Vector<Ghost> vGhosts = new Vector<Ghost>();
	static final int timerUnvulnerable = 60;

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	public void addGhost(Ghost g) {
		vGhosts.addElement(g);
	}

	public void collisionProcessing(Vector<Collideable> collideables) {
		for (int i = 0; i < collideables.size(); i++) {
			Vector<Collideable> tmp = (Vector<Collideable>) collideables
					.elementAt(i);
			try {
				collision(tmp.elementAt(0), tmp.elementAt(1));
			} catch (Exception e) { // XXX Silent catch
			}
		}
	}

	public void collision(Pacman p, Ghost g) {
		if (!p.isVulnerable()) {
			if (g.isAlive())
				g.setAlive(false);
		} else {
			// universe.removeGameEntity(g);
			if (g.isAlive())
				System.out.println("[COLLISION] Pacman - Ghost pacman is dead");
		}
	}

	public void collision(Ghost g, SuperPacgum spg) {
	}

	public void collision(Ghost g, Pacgum spg) {
	}

	public void collision(Ghost g, Jail jail) {
		g.setAlive(true);
	}

	public void collision(Pacman p, SuperPacgum spg) {
		universe.removeGameEntity(spg);
		p.setUnvulnerable(timerUnvulnerable);
		for (int i = 0; i < vGhosts.size(); i++) {
			(vGhosts.elementAt(i)).setAfraid(timerUnvulnerable);
		}
	}

	public void collision(Pacman p, Pacgum pg) {
		universe.removeGameEntity(pg);
		if(universe.getValues()<=7){
			System.out.println("You win !!!");
		}
		// System.out.println(universe.getValues());
	}

	public void collision(Collideable e1, Collideable e2) throws Exception {
		Object[] param = new Object[2];
		Class<?>[] paramClass = new Class[2];
		Class<? extends PacmanCollisionRules> receiverClass = this.getClass();

		param[0] = e1;
		paramClass[0] = e1.getClass();
		param[1] = e2;
		paramClass[1] = e2.getClass();
		Method m = null;
		try {
			m = receiverClass.getMethod("collision", paramClass);
			m.invoke(this, param);
		} catch (Exception e) {
			Class<?> tmpclass = paramClass[0];
			Object tmpobject = param[0];
			paramClass[0] = paramClass[1];
			paramClass[1] = tmpclass;
			param[0] = paramClass[1];
			param[1] = tmpobject;
			m = receiverClass.getMethod("collision", paramClass);
			m.invoke(this, param);
		}
	}
}
