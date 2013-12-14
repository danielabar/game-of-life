package ca.danib.gameoflife.service;

import java.util.Arrays;
import java.util.List;

import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Game;
import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

public class NeighbourServiceImpl implements INeighbourService {

	@Override
	public Integer getNumberOfLivingNeighbours(Cell cell, Game game) {
		List<Cell> allNeighbours = getAllNeighbours(cell, game);
		return countTheLiving(allNeighbours);
	}

	protected List<Cell> getAllNeighbours(Cell cell, Game game) {
		return Arrays.asList(
				getTopLeftNeighbour(cell, game),
				getTopMiddleNeighbour(cell, game),
				getTopRightNeighbour(cell, game),
				getLeftNeighbour(cell, game),
				getRightNeighbour(cell, game),
				getBottomLeftNeighbour(cell, game),
				getBottomMiddleNeighbour(cell, game),
				getBottomRightNeighbour(cell, game)
			);
	}

	protected Integer countTheLiving(List<Cell> cells) {
		int counter = 0;
		for (Cell cell : cells) {
			if(LifeStatus.ALIVE.equals(cell.getLifeStatus())) {
				counter += 1;
			}
		}
		return counter;

	}

	protected Cell getTopLeftNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopLeft = new Position(calculateTopRow(position, game), calculateLeftColumn(position, game));
		return game.getBoard().getCellAtPosition(positionTopLeft);
	}

	protected Cell getTopMiddleNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopMiddle = new Position(calculateTopRow(position, game), position.getColumn());
		return game.getBoard().getCellAtPosition(positionTopMiddle);
	}

	public Cell getTopRightNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(calculateTopRow(position, game), calculateRightColumn(position, game));
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getLeftNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow(), calculateLeftColumn(position, game));
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getRightNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow(), calculateRightColumn(position, game));
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getBottomLeftNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(calculateBottomRow(position, game), calculateLeftColumn(position, game));
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getBottomMiddleNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(calculateBottomRow(position, game), position.getColumn());
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getBottomRightNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(calculateBottomRow(position, game), calculateRightColumn(position, game));
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	protected Integer calculateTopRow(Position position, Game game) {
		int topRow = position.getRow()-1;
		if (topRow < 0) {
			topRow = game.getRows()-1;
		}
		return topRow;
	}

	protected Integer calculateBottomRow(Position position, Game game) {
		int bottomRow = position.getRow()+1;
		if (bottomRow >= game.getRows()) {
			bottomRow = 0;
		}
		return bottomRow;
	}

	protected Integer calculateLeftColumn(Position position, Game game) {
		int leftColumn = position.getColumn()-1;
		if (leftColumn < 0) {
			leftColumn = game.getColumns()-1;
		}
		return leftColumn;
	}

	protected Integer calculateRightColumn(Position position, Game game) {
		int rightColumn = position.getColumn()+1;
		if (rightColumn >= game.getColumns()) {
			rightColumn = 0;
		}
		return rightColumn;
	}

}
