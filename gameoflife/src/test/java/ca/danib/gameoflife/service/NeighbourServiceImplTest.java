package ca.danib.gameoflife.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.danib.gameoflife.model.Board;
import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

public class NeighbourServiceImplTest {

	private NeighbourServiceImpl fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new NeighbourServiceImpl();
	}

	@Test
	public void testGetNumberOfLivingNeighbours() throws Exception {
		Integer cellRow = 1;
		Integer cellColumn = 1;
		Position cellPosition = new Position(cellRow, cellColumn);
		Cell cellToEvaluate = new Cell(cellPosition, LifeStatus.ALIVE);

		Board board = buildBoard();

		Integer result = fixture.getNumberOfLivingNeighbours(cellToEvaluate, board);
		assertEquals(3, result.intValue());
	}

	private Board buildBoard() {
		Map<Position, Cell> positionCells = new HashMap<Position, Cell>();
		positionCells.put(new Position(0,0), new Cell(new Position(0,0), LifeStatus.ALIVE));
		positionCells.put(new Position(0,1), new Cell(new Position(0,1), LifeStatus.DEAD));
		positionCells.put(new Position(0,2), new Cell(new Position(0,1), LifeStatus.ALIVE));
		positionCells.put(new Position(1,0), new Cell(new Position(0,1), LifeStatus.DEAD));
		positionCells.put(new Position(1,1), new Cell(new Position(1, 1), LifeStatus.ALIVE));
		positionCells.put(new Position(1,2), new Cell(new Position(0,1), LifeStatus.ALIVE));
		positionCells.put(new Position(2,0), new Cell(new Position(0,1), LifeStatus.DEAD));
		positionCells.put(new Position(2,1), new Cell(new Position(0,1), LifeStatus.DEAD));
		positionCells.put(new Position(2,2), new Cell(new Position(0,1), LifeStatus.DEAD));
		return new Board(positionCells);
	}

	@Test
	public void testGetTopLeftNeighbour() {
		Integer cellRow = 1;
		Integer cellColumn = 1;
		Position cellPosition = new Position(cellRow, cellColumn);
		Cell cellToEvaluate = new Cell(cellPosition, LifeStatus.ALIVE);

		Board board = buildBoard();

		Position expectedPosition = new Position(0,0);
		Cell actualResult = fixture.getTopLeftNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

}
