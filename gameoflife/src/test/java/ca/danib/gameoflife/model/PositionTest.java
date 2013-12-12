package ca.danib.gameoflife.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testEquals() {
		Integer row = 0;
		Integer column = 0;
		Position p1 = new Position(row, column);
		Position p2 = new Position(row, column);
		assertTrue(p1.equals(p2));
	}

}
