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
import ca.danib.gameoflife.model.Game;
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
		Game game = new Game(4, 4, board);

		Integer result = fixture.getNumberOfLivingNeighbours(cellToEvaluate, game);
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
		Game game = buildGame();

		Position expectedPosition = new Position(0,0);
		Cell actualResult = fixture.getTopLeftNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetTopLeftNeighbour_cellIsAtTopLeftOfBoard() {
		Cell cellToEvaluate = new Cell(new Position(0, 0), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(3, 3);
		Cell actualResult = fixture.getTopLeftNeighbour(cellToEvaluate, game);
		assertThat("top left wraps to bottom right", actualResult.getPosition(), is(expectedPosition));
	}

	// Nice to have TDF's so building objects is reusable among test classes
	private Game buildGame() {
		Game game = new Game(4, 4, buildBoard());
		return game;
	}

	@Test
	public void testGetTopMiddleNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(0,1);
		Cell actualResult = fixture.getTopMiddleNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetTopMiddleNeighbour_cellToEvaluateIsAtTop() {
		Cell cellToEvaluate = new Cell(new Position(0, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(3,1);
		Cell actualResult = fixture.getTopMiddleNeighbour(cellToEvaluate, game);
		assertThat("When cell to evaluate is at top, top neighbour wraps to bottom", actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetTopRightNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(0,2);
		Cell actualResult = fixture.getTopRightNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetTopRightNeighbour_cellIsAtTopRightOfBoard() {
		Cell cellToEvaluate = new Cell(new Position(0, 3), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(3,0);
		Cell actualResult = fixture.getTopRightNeighbour(cellToEvaluate, game);
		assertThat("top right wraps to bottom left", actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetLeftNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(1,0);
		Cell actualResult = fixture.getLeftNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetLeftNeighbour_cellIsATLeftEdgeOfBoard() {
		Cell cellToEvaluate = new Cell(new Position(2, 0), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(2,3);
		Cell actualResult = fixture.getLeftNeighbour(cellToEvaluate, game);
		assertThat("left wraps to right", actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetRightNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(1,2);
		Cell actualResult = fixture.getRightNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetRightNeighbour_cellIsAtRightEdgeOfBaord() {
		Cell cellToEvaluate = new Cell(new Position(1, 3), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(1,0);
		Cell actualResult = fixture.getRightNeighbour(cellToEvaluate, game);
		assertThat("right wraps to left", actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomLeftNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(2,0);
		Cell actualResult = fixture.getBottomLeftNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomLeftNeighbour_cellIsAtBottomEdgeOfBoard() {
		Cell cellToEvaluate = new Cell(new Position(3, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(0,0);
		Cell actualResult = fixture.getBottomLeftNeighbour(cellToEvaluate, game);
		assertThat("bottom wraps to top, left decrements normally", actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomMiddleNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(2,1);
		Cell actualResult = fixture.getBottomMiddleNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomMiddleNeighbour_cellIsAtBottomEdgeOfBoard() {
		Cell cellToEvaluate = new Cell(new Position(3, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(0,1);
		Cell actualResult = fixture.getBottomMiddleNeighbour(cellToEvaluate, game);
		assertThat("bottom wraps to top", actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomRightNeighbour() {
		Cell cellToEvaluate = new Cell(new Position(1, 1), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(2,2);
		Cell actualResult = fixture.getBottomRightNeighbour(cellToEvaluate, game);
		assertThat(actualResult.getPosition(), is(expectedPosition));
	}

	@Test
	public void testGetBottomRightNeighbour_cellIsATBottomRightEdgeOfBoard() {
		Cell cellToEvaluate = new Cell(new Position(3, 3), LifeStatus.ALIVE);
		Game game = buildGame();

		Position expectedPosition = new Position(0,0);
		Cell actualResult = fixture.getBottomRightNeighbour(cellToEvaluate, game);
		assertThat("bottom right wraps to top left", actualResult.getPosition(), is(expectedPosition));
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
		Game game = buildGame();

		List<Cell> allNeighbours = fixture.getAllNeighbours(cellToEvaluate, game);
		assertThat(allNeighbours.size(), is(8));
	}

	@Test
	public void testCalculateTopRow_positionAtTopEdge() {
		Game game = buildGame();
		Position position = new Position(0, 0);

		Integer result = fixture.calculateTopRow(position, game);
		assertThat("top edge wraps to bottom", result, is(3));
	}

	@Test
	public void testCalculateTopRow_positionNotAtTopEdge() {
		Game game = buildGame();
		Position position = new Position(1, 0);

		Integer result = fixture.calculateTopRow(position, game);
		assertThat("top is position minus one", result, is(0));
	}

	@Test
	public void testCalculateBottomRow_positionAtBottomEdge() {
		Game game = buildGame();
		Position position = new Position(3,3);

		Integer result = fixture.calculateBottomRow(position, game);
		assertThat("bottom edge wraps to top", result, is(0));
	}

	@Test
	public void testCalculateBottomRow_positionNotAtBottomEdge() {
		Game game = buildGame();
		Position position = new Position(2,3);

		Integer result = fixture.calculateBottomRow(position, game);
		assertThat("bottom is position plus one", result, is(3));
	}

	@Test
	public void testCalculateLeftColumn_positionAtLeftEdge() {
		Game game = buildGame();
		Position position = new Position(0,0);

		Integer result = fixture.calculateLeftColumn(position, game);
		assertThat("left edge wraps to right", result, is(3));
	}

	@Test
	public void testCalculateLeftColumn_positionNotAtLeftEdge() {
		Game game = buildGame();
		Position position = new Position(0,1);

		Integer result = fixture.calculateLeftColumn(position, game);
		assertThat("left is position minus one", result, is(0));
	}

	@Test
	public void testCalculateRightColumn_positionAtRightEdge() {
		Game game = buildGame();
		Position position = new Position(0,3);

		Integer result = fixture.calculateRightColumn(position, game);
		assertThat("right edge wraps to left", result, is(0));
	}

	@Test
	public void testCalculateRightColumn_positionNotAtRightEdge() {
		Game game = buildGame();
		Position position = new Position(0,2);

		Integer result = fixture.calculateRightColumn(position, game);
		assertThat("right is position plus one", result, is(3));
	}

}
