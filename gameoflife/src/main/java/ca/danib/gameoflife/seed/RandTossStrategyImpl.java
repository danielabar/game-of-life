package ca.danib.gameoflife.seed;

import java.util.Random;

import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

//FIXME To make this testable with assertions, need to extract the Random part into a helper
public class RandTossStrategyImpl implements ISeedStrategy {

	@Override
	public LifeStatus initializeLifeStatus(Position position, Integer rows, Integer columns) {
		Random random = new Random();
		int coinToss = random.nextInt(3);
		if (coinToss == 1) {
			return LifeStatus.ALIVE;
		}
		return LifeStatus.DEAD;
	}

}
