package ca.danib.gameoflife.seed;

import ca.danib.gameoflife.model.Seed;

public interface ISeedFactory {
	public ISeedStrategy getSeedStrategy(Seed seed);
}
