package ca.danib.gameoflife.service;

import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Game;

public interface INeighbourService {
	public Integer getNumberOfLivingNeighbours(Cell cell, Game game);
}
