package ca.danib.gameoflife.seed;

import org.junit.Before;
import org.junit.Test;

import ca.danib.gameoflife.model.LifeStatus;

public class RandTossStrategyImplTest {

	private RandTossStrategyImpl fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new RandTossStrategyImpl();
	}

	@Test
	public void testInitializeLifeStatus() throws Exception {
		for (int i=0; i<50; i++) {
			LifeStatus result = fixture.initializeLifeStatus(null, null, null);
			System.out.println("RandTossStrategyImpl result:" + result);
		}
	}

}
