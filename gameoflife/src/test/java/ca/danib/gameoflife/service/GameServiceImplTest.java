package ca.danib.gameoflife.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ca.danib.gameoflife.model.Board;
import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Game;
import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

// FIXME This is using real neighbour service, use mockito instead. Could have integration test to test collaboration with real service.
public class GameServiceImplTest {

	private GameServiceImpl fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new GameServiceImpl();
	}

	@Test
	public void testInitializeGame() {
		Integer rows = 4;
		Integer columns = 4;
		Integer expectedNumberOfCells = rows * columns;

		Game game = fixture.initializeGame(rows, columns);
		Map<Position, Cell> positionCells = game.getBoard().getPositionCells();

		assertNotNull(game);
		assertEquals(rows, game.getRows());
		assertEquals(columns, game.getColumns());

		assertNotNull(game.getBoard());
		assertEquals(expectedNumberOfCells.intValue(), positionCells.size());

		Set<Position> actualPositionKeys = positionCells.keySet();
		for(int row=0; row<rows; row++) {
			for(int column=0; column<columns; column++) {
				Position expectedPosition = new Position(row,column);
				assertTrue(actualPositionKeys.contains(expectedPosition));
				Cell actualCell = positionCells.get(expectedPosition);
				assertNotNull(actualCell);
				assertEquals(expectedPosition, actualCell.getPosition());
			}
		}
		assertTrue(atLeastOneCellAlive(positionCells));
		assertTrue(atLeastOneCellDead(positionCells));
	}

	private boolean atLeastOneCellDead(Map<Position, Cell> positionCells) {
		Collection<Cell> cells = positionCells.values();
		for (Cell cell : cells) {
			if(LifeStatus.DEAD.equals(cell.getLifeStatus())) {
				return true;
			}
		}
		return false;
	}

	private boolean atLeastOneCellAlive(Map<Position, Cell> positionCells) {
		Collection<Cell> cells = positionCells.values();
		for (Cell cell : cells) {
			if(LifeStatus.ALIVE.equals(cell.getLifeStatus())) {
				return true;
			}
		}
		return false;
	}

	@Ignore("wip on varying seed strategy")
	@Test
	public void testInitializeLifeStatus_rowEvenlyDivisibleBy() {
		Integer row = 9;
		Integer column = 1;
		LifeStatus result = fixture.initializeLifeStatus(new Position(row, column), null, null);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Ignore("wip on varying seed strategy")
	@Test
	public void testInitializeLifeStatus_rowNotEvenlyDivisibleBy() {
		Integer row = 10;
		Integer column = 1;
		LifeStatus result = fixture.initializeLifeStatus(new Position(row, column), null, null);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Ignore("wip on varying seed strategy")
	@Test
	public void testInitializeLifeStatus_rowZero() {
		Integer row = 0;
		Integer column = 1;
		LifeStatus result = fixture.initializeLifeStatus(new Position(row, column), null, null);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Test
	public void testApplyLifeRules_underPopulated_living0() {
		Cell cell = new Cell(null, LifeStatus.ALIVE);
		Integer numberAlive = 0;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Test
	public void testApplyLifeRules_underPopulated_living1() {
		Cell cell = new Cell(null, LifeStatus.ALIVE);
		Integer numberAlive = 1;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Test
	public void testApplyLifeRules_surviving_living2() {
		Cell cell = new Cell(null, LifeStatus.ALIVE);
		Integer numberAlive = 2;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Test
	public void testApplyLifeRules_surviving_living3() {
		Cell cell = new Cell(null, LifeStatus.ALIVE);
		Integer numberAlive = 3;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Test
	public void testApplyLifeRules_overCrowded_living4() {
		Cell cell = new Cell(null, LifeStatus.ALIVE);
		Integer numberAlive = 4;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Test
	public void testApplyLifeRules_overCrowded_living5() {
		Cell cell = new Cell(null, LifeStatus.ALIVE);
		Integer numberAlive = 5;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Test
	public void testApplyLifeRules_comingBackToLife_living3() {
		Cell cell = new Cell(null, LifeStatus.DEAD);
		Integer numberAlive = 3;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Test
	public void testApplyLifeRules_stayingDead_living4() {
		Cell cell = new Cell(null, LifeStatus.DEAD);
		Integer numberAlive = 4;

		LifeStatus result = fixture.applyLifeRules(cell, numberAlive);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Test
	public void testBuildNextGeneration() {
		Game currentGen = buildGame();
		currentGen.draw();
		Game nextGen = fixture.buildNextGeneration(currentGen);
		nextGen.draw();

		assertThat(nextGen.getColumns(), is(currentGen.getColumns()));
		assertThat(nextGen.getRows(), is(currentGen.getRows()));
	}

	private Game buildGame() {
		Game game = new Game(4, 4, buildBoard());
		return game;
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

}
