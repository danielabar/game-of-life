package ca.danib.gameoflife.seed;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.danib.gameoflife.model.Seed;

public class SeedFactoryImplTest {

	private SeedFactoryImpl fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new SeedFactoryImpl();
	}

	@Test
	public void testGetSeedStrategy_blinker() throws Exception {
		ISeedStrategy seedStrategy = fixture.getSeedStrategy(Seed.BLINKER);
		assertTrue(seedStrategy instanceof BlinkerStrategyImpl);
	}

	@Test
	public void testGetSeedStrategy_glider() throws Exception {
		ISeedStrategy seedStrategy = fixture.getSeedStrategy(Seed.GLIDER);
		assertTrue(seedStrategy instanceof GliderStrategyImpl);
	}

	@Test
	public void testGetSeedStrategy_randtoss() throws Exception {
		ISeedStrategy seedStrategy = fixture.getSeedStrategy(Seed.RAND_TOSS);
		assertTrue(seedStrategy instanceof RandTossStrategyImpl);
	}

}
