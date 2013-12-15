package ca.danib.gameoflife.seed;

import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

public interface ISeedStrategy {
	public LifeStatus initializeLifeStatus(Position position, Integer rows, Integer columns);
}
