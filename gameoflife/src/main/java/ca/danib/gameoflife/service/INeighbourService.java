package ca.danib.gameoflife.service;

import ca.danib.gameoflife.model.Board;
import ca.danib.gameoflife.model.Cell;

public interface INeighbourService {
	public Integer getNumberOfLivingNeighbours(Cell cell, Board board);
}
