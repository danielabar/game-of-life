package ca.danib.gameoflife.seed;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

public class BlinkerStrategyImplTest {

	private BlinkerStrategyImpl fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new BlinkerStrategyImpl();
	}

	@Test
	public void testInitializeLifeStatus_row0_col0_dead() throws Exception {
		Integer row = 0;
		Integer column = 0;
		Position position = new Position(row, column);

		LifeStatus result = fixture.initializeLifeStatus(position, null, null);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Test
	public void testInitializeLifeStatus_row1_col1_alive() throws Exception {
		Integer row = 1;
		Integer column = 1;
		Position position = new Position(row, column);

		LifeStatus result = fixture.initializeLifeStatus(position, null, null);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Test
	public void testInitializeLifeStatus_row2_col1_alive() throws Exception {
		Integer row = 2;
		Integer column = 1;
		Position position = new Position(row, column);

		LifeStatus result = fixture.initializeLifeStatus(position, null, null);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Test
	public void testInitializeLifeStatus_row3_col1_alive() throws Exception {
		Integer row = 3;
		Integer column = 1;
		Position position = new Position(row, column);

		LifeStatus result = fixture.initializeLifeStatus(position, null, null);
		assertEquals(LifeStatus.ALIVE, result);
	}

}
