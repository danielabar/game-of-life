package ca.danib.gameoflife.service;

import java.util.HashMap;
import java.util.Map;

import ca.danib.gameoflife.model.Board;
import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Game;
import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

public class GameServiceImpl implements IGameService {

	private INeighbourService neighbourService;

	public GameServiceImpl() {
		super();
		this.neighbourService = new NeighbourServiceImpl();
	}

	// TODO This constructor should be used by unit tests for mocking neighbour service
	protected GameServiceImpl(INeighbourService neighbourService) {
		super();
		this.neighbourService = neighbourService;
	}

	// Nice to have: GameValidator to ensure game size makes sense (eg: minimum 4x4?)
	@Override
	public Game initializeGame(Integer rows, Integer columns) {
		Board board = buildBoard(rows, columns);
		return new Game(rows, columns, board );
	}

	@Override
	public Game buildNextGeneration(Game currentGen) {
		Map<Position, Cell> nextGenPositionCells = new HashMap<Position, Cell>();
		Map<Position, Cell> currentGenPositionCells = currentGen.getBoard().getPositionCells();
		for (Cell currentGenCell : currentGenPositionCells.values()) {
		    Cell nextGenCell = new Cell(currentGenCell.getPosition(), applyLifeRules(currentGenCell, currentGen));
		    nextGenPositionCells.put(currentGenCell.getPosition(), nextGenCell);
		}
		return new Game(currentGen.getRows(), currentGen.getColumns(), new Board(nextGenPositionCells));
	}

	protected LifeStatus applyLifeRules(Cell cell, Game game) {
		Integer numberAlive = getNeighbourService().getNumberOfLivingNeighbours(cell, game);
		return applyLifeRules(cell, numberAlive);
	}

	/*
	 * 	Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives on to the next generation.
		Any dead cell with exactly three live neighbours becomes a live cell.
	 */
	protected LifeStatus applyLifeRules(Cell cell, Integer numberAlive) {
		if (underPopulated(cell, numberAlive)) {
			return LifeStatus.DEAD;
		}
		if (overCrowded(cell, numberAlive)) {
			return LifeStatus.DEAD;
		}
		if (survivingToNextGen(cell, numberAlive)) {
			return LifeStatus.ALIVE;
		}
		if (comingBackToLife(cell, numberAlive)) {
			return LifeStatus.ALIVE;
		}
		return LifeStatus.DEAD;
	}

	protected boolean comingBackToLife(Cell cell, Integer numberAlive) {
		return LifeStatus.DEAD.equals(cell.getLifeStatus()) && numberAlive == 3;
	}

	protected boolean survivingToNextGen(Cell cell, Integer numberAlive) {
		return LifeStatus.ALIVE.equals(cell.getLifeStatus()) && (numberAlive == 2 || numberAlive == 3);
	}

	protected boolean overCrowded(Cell cell, Integer numberAlive) {
		return LifeStatus.ALIVE.equals(cell.getLifeStatus()) && numberAlive > 3;
	}

	protected boolean underPopulated(Cell cell, Integer numberAlive) {
		return LifeStatus.ALIVE.equals(cell.getLifeStatus()) && numberAlive < 2;
	}

	protected Board buildBoard(Integer rows, Integer columns) {
		Map<Position, Cell> positionCells = new HashMap<Position, Cell>();
		for(int row=0; row<rows; row++) {
			for(int column=0; column<columns; column++) {
				Position position = new Position(row, column);
				positionCells.put(position, buildCell(position, rows, columns));
			}
		}
		return new Board(positionCells);
	}

	protected Cell buildCell(Position position, Integer rows, Integer columns) {
		Cell cell = new Cell(position);
		cell.setLifeStatus(initializeLifeStatus(position, rows, columns));
		return cell;
	}

	// This could be in a SeedService/Strategy
	// Random
//	protected LifeStatus initializeLifeStatus(Position position, Integer rows, Integer columns) {
//		Random random = new Random(new Date().getTime());
//
//		// TODO: Make 2 and 10 row/col "jiggles" respectively
////		int randIntBetweenZeroAndRows = random.nextInt(rows);
////		int randIntBetweenZeroAndCols = random.nextInt(columns);
////		if (randIntBetweenZeroAndRows % 2 == 0 || randIntBetweenZeroAndCols % 10 == 0) {
////			return LifeStatus.ALIVE;
////		}
//
//		// Coin Toss
//		int coinToss = random.nextInt(3);
//		if (coinToss == 1) {
//			return LifeStatus.ALIVE;
//		}
//		return LifeStatus.DEAD;
//	}

	// Glider
	protected LifeStatus initializeLifeStatus(Position position, Integer rows, Integer columns) {
		if(position.getRow() == 1 && position.getColumn() == 2) {
			return LifeStatus.ALIVE;
		}
		if(position.getRow() == 2 && (position.getColumn() == 3 || position.getColumn() == 4)) {
			return LifeStatus.ALIVE;
		}
		if(position.getRow() == 3 && (position.getColumn() == 2 || position.getColumn() == 3)) {
			return LifeStatus.ALIVE;
		}
		return LifeStatus.DEAD;
	}

	// Blinker
//	protected LifeStatus initializeLifeStatus(Integer row, Integer column) {
//		if (row == 1 && column == 1) {
//			return LifeStatus.ALIVE;
//		}
//		if (row == 2 && column == 1) {
//			return LifeStatus.ALIVE;
//		}
//		if (row == 3 && column == 1) {
//			return LifeStatus.ALIVE;
//		}
//		return LifeStatus.DEAD;
//	}

	public INeighbourService getNeighbourService() {
		return neighbourService;
	}

	public void setNeighbourService(INeighbourService neighbourService) {
		this.neighbourService = neighbourService;
	}

}
