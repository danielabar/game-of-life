package ca.danib.gameoflife.model;

import java.util.Map;

public class Board {

	private Map<Position, Cell> positionCells;

	public Board(Map<Position, Cell> positionCells) {
		super();
		this.positionCells = positionCells;
	}

	public Map<Position, Cell> getPositionCells() {
		return positionCells;
	}

	public void setPositionCells(Map<Position, Cell> positionCells) {
		this.positionCells = positionCells;
	}

}
