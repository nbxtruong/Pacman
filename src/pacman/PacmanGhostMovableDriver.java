package pacman;

import game.GameMovableDriverDefault;
import base.Move;
import base.Movable;

public class PacmanGhostMovableDriver extends GameMovableDriverDefault {
	public Move getMove(Movable m) {
		boolean again = true;
		Move tmp1, tmp2;
		tmp1 = super.getMove(m);
		tmp2 = m.getMove();
		while (again) {
			if ((tmp1.getDir().getX() == tmp2.getDir().getX())
					&& (tmp1.getDir().getY() != -tmp2.getDir().getY())) {
				again = false;
				break;
			}
			if ((tmp1.getDir().getX() != -tmp2.getDir().getX())
					&& (tmp1.getDir().getY() == tmp2.getDir().getY())) {
				again = false;
				break;
			}
			if ((tmp1.getDir().getX() != tmp2.getDir().getX())
					&& (tmp1.getDir().getY() != tmp2.getDir().getY())) {
				again = false;
				break;
			}
			tmp1 = super.getMove(m);
		}
		return tmp1;
	}
}
