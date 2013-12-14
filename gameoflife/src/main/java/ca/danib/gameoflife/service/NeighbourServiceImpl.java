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
		Position positionTopLeft = new Position(position.getRow()-1, position.getColumn()-1);
		return game.getBoard().getCellAtPosition(positionTopLeft);
	}

	protected Cell getTopMiddleNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();

		int topRow = position.getRow()-1;
		if (topRow < 0) {
			topRow = game.getRows()-1;
		}


		Position positionTopMiddle = new Position(topRow, position.getColumn());
		return game.getBoard().getCellAtPosition(positionTopMiddle);
	}


	public Cell getTopRightNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()-1, position.getColumn()+1);
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getLeftNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow(), position.getColumn()-1);
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getRightNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow(), position.getColumn()+1);
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getBottomLeftNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()+1, position.getColumn()-1);
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getBottomMiddleNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()+1, position.getColumn());
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

	public Cell getBottomRightNeighbour(Cell cell, Game game) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()+1, position.getColumn()+1);
		return game.getBoard().getCellAtPosition(positionTopRight);
	}

}
