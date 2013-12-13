package ca.danib.gameoflife.service;

import ca.danib.gameoflife.model.Board;
import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Position;

public class NeighbourServiceImpl implements INeighbourService {

	@Override
	public Integer getNumberOfLivingNeighbours(Cell cell, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Cell getTopLeftNeighbour(Cell cell, Board board) {
		Position position = cell.getPosition();
		// TODO: Edge cases if negative, "wrap around" to other side of board
		Position positionTopLeft = new Position(position.getRow()-1, position.getColumn()-1);
		return board.getCellAtPosition(positionTopLeft);
	}

}
