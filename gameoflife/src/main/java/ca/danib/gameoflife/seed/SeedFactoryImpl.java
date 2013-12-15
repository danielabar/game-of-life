package ca.danib.gameoflife.seed;

import ca.danib.gameoflife.model.Seed;

public class SeedFactoryImpl implements ISeedFactory {

	@Override
	public ISeedStrategy getSeedStrategy(Seed seed) {
		switch (seed) {
		case BLINKER:
			return new BlinkerStrategyImpl();
		case GLIDER:
			return new GliderStrategyImpl();
		case RAND_TOSS:
			return new RandTossStrategyImpl();
		default:
            throw new UnsupportedOperationException("Unrecognized seed");
		}
	}

}
