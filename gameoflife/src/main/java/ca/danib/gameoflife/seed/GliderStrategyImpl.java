package ca.danib.gameoflife.seed;

import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

public class GliderStrategyImpl implements ISeedStrategy {

	@Override
	public LifeStatus initializeLifeStatus(Position position, Integer rows, Integer columns) {
		if(position.getRow() == 1 && position.getColumn() == 2) {
			return LifeStatus.ALIVE;
		}
		if(position.getRow() == 2 && (position.getColumn() == 3 || position.getColumn() == 4)) {
			return LifeStatus.ALIVE;
		}
		if(position.getRow() == 3 && (position.getColumn() == 2 || position.getColumn() == 3)) {
			return LifeStatus.ALIVE;
		}
		return LifeStatus.DEAD;
	}

}
