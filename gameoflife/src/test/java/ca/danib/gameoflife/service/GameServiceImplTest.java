package ca.danib.gameoflife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Game;
import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

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
		Map<Position, Cell> positionCells = game.getPositionCells().getPositionCells();

		assertNotNull(game);
		assertEquals(rows, game.getRows());
		assertEquals(columns, game.getColumns());

		assertNotNull(game.getPositionCells());
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

	@Test
	public void testInitializeLifeStatus_rowEvenlyDivisibleBy() {
		Integer row = 9;
		Integer column = 1;
		LifeStatus result = fixture.initializeLifeStatus(row, column);
		assertEquals(LifeStatus.ALIVE, result);
	}

	@Test
	public void testInitializeLifeStatus_rowNotEvenlyDivisibleBy() {
		Integer row = 10;
		Integer column = 1;
		LifeStatus result = fixture.initializeLifeStatus(row, column);
		assertEquals(LifeStatus.DEAD, result);
	}

	@Test
	public void testInitializeLifeStatus_rowZero() {
		Integer row = 0;
		Integer column = 1;
		LifeStatus result = fixture.initializeLifeStatus(row, column);
		assertEquals(LifeStatus.ALIVE, result);
	}

}
