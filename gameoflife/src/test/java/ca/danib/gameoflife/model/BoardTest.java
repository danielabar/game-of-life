package ca.danib.gameoflife.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetCellAtPosition() throws Exception {
		Map<Position, Cell> positionCells = new HashMap<Position, Cell>();

		Position pos1 = new Position(0, 0);
		Cell cell1 = new Cell(pos1, LifeStatus.ALIVE);
		positionCells.put(pos1, cell1);

		Position pos2 = new Position(0, 1);
		Cell cell2 = new Cell(pos2, LifeStatus.DEAD);
		positionCells.put(pos2, cell2);

		Board board = new Board(positionCells);

		Cell actualCell1 = board.getCellAtPosition(pos1);
		assertThat(actualCell1, is(cell1));

		Cell actualCell2 = board.getCellAtPosition(pos2);
		assertThat(actualCell2, is(cell2));
	}

}
