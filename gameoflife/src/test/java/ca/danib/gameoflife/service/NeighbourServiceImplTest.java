package ca.danib.gameoflife.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
	public void testGetNumberOfLivingNeighbours() {
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
		positionCells.put(new Position(0,2), new Cell(new Position(0,2), LifeStatus.ALIVE));
		positionCells.put(new Position(0,3), new Cell(new Position(0,3), LifeStatus.DEAD));
		positionCells.put(new Position(1,0), new Cell(new Position(1,0), LifeStatus.DEAD));
		positionCells.put(new Position(1,1), new Cell(new Position(1,1), LifeStatus.ALIVE));
		positionCells.put(new Position(1,2), new Cell(new Position(1,2), LifeStatus.ALIVE));
		positionCells.put(new Position(1,3), new Cell(new Position(1,3), LifeStatus.DEAD));
		positionCells.put(new Position(2,0), new Cell(new Position(2,0), LifeStatus.DEAD));
		positionCells.put(new Position(2,1), new Cell(new Position(2,1), LifeStatus.DEAD));
		positionCells.put(new Position(2,2), new Cell(new Position(2,2), LifeStatus.DEAD));
		positionCells.put(new Position(2,3), new Cell(new Position(2,3), LifeStatus.DEAD));
		positionCells.put(new Position(3,0), new Cell(new Position(3,0), LifeStatus.DEAD));
		positionCells.put(new Position(3,1), new Cell(new Position(3,1), LifeStatus.DEAD));
		positionCells.put(new Position(3,2), new Cell(new Position(3,2), LifeStatus.DEAD));
		positionCells.put(new Position(3,3), new Cell(new Position(3,3), LifeStatus.DEAD));
		return new Board(positionCells);
	}

	@Test
	public void testGetTopLeftNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(0,0);
		Cell actualResult = fixture.getTopLeftNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetTopMiddleNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(0,1);
		Cell actualResult = fixture.getTopMiddleNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetTopRightNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(0,2);
		Cell actualResult = fixture.getTopRightNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetLeftNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(1,0);
		Cell actualResult = fixture.getLeftNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetRightNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(1,2);
		Cell actualResult = fixture.getRightNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomLeftNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(2,0);
		Cell actualResult = fixture.getBottomLeftNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomMiddleNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(2,1);
		Cell actualResult = fixture.getBottomMiddleNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomRightNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		Position expectedPosition = new Position(2,2);
		Cell actualResult = fixture.getBottomRightNeighbour(cellToEvaluate, board);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testCountTheLiving() {
		List<Cell> cells = Arrays.asList(
				new Cell(new Position(0, 0), LifeStatus.ALIVE),
				new Cell(new Position(0, 1), LifeStatus.DEAD),
				new Cell(new Position(0, 1), LifeStatus.ALIVE)
				);
		Integer result = fixture.countTheLiving(cells);
		assertThat(result.intValue(), is(2));
	}

	@Test
	public void testCountTheLiving_allDead() {
		List<Cell> cells = Arrays.asList(
				new Cell(new Position(0, 0), LifeStatus.DEAD),
				new Cell(new Position(0, 1), LifeStatus.DEAD),
				new Cell(new Position(0, 1), LifeStatus.DEAD)
				);
		Integer result = fixture.countTheLiving(cells);
		assertThat(result.intValue(), is(0));
	}

	@Test
	public void testGetAllNeighbours() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Board board = buildBoard();

		List<Cell> allNeighbours = fixture.getAllNeighbours(cellToEvaluate, board);
		assertThat(allNeighbours.size(), is(8));
	}

}
