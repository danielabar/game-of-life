package ca.danib.gameoflife.service;

import java.util.Arrays;
import java.util.List;

import ca.danib.gameoflife.model.Board;
import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;

public class NeighbourServiceImpl implements INeighbourService {

	@Override
	public Integer getNumberOfLivingNeighbours(Cell cell, Board board) {
		List<Cell> allNeighbours = getAllNeighbours(cell, board);
		return countTheLiving(allNeighbours);
	}

	protected List<Cell> getAllNeighbours(Cell cell, Board board) {
		return Arrays.asList(
				getTopLeftNeighbour(cell, board),
				getTopMiddleNeighbour(cell, board),
				getTopRightNeighbour(cell, board),
				getLeftNeighbour(cell, board),
				getRightNeighbour(cell, board),
				getBottomLeftNeighbour(cell, board),
				getBottomMiddleNeighbour(cell, board),
				getBottomRightNeighbour(cell, board)
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

	// TODO: Edge cases if negative, "wrap around" to other side of board
	protected Cell getTopLeftNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopLeft = new Position(position.getRow()-1, position.getColumn()-1);
		return board.getCellAtPosition(positionTopLeft);
	}

	protected Cell getTopMiddleNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopMiddle = new Position(position.getRow()-1, position.getColumn());
		return board.getCellAtPosition(positionTopMiddle);
	}

	public Cell getTopRightNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()-1, position.getColumn()+1);
		return board.getCellAtPosition(positionTopRight);
	}

	public Cell getLeftNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow(), position.getColumn()-1);
		return board.getCellAtPosition(positionTopRight);
	}

	public Cell getRightNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow(), position.getColumn()+1);
		return board.getCellAtPosition(positionTopRight);
	}

	public Cell getBottomLeftNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()+1, position.getColumn()-1);
		return board.getCellAtPosition(positionTopRight);
	}

	public Cell getBottomMiddleNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()+1, position.getColumn());
		return board.getCellAtPosition(positionTopRight);
	}

	public Cell getBottomRightNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		Position positionTopRight = new Position(position.getRow()+1, position.getColumn()+1);
		return board.getCellAtPosition(positionTopRight);
	}

}
