package ca.danib.gameoflife.model;

import java.util.Map;

public class Game {
	
	private Integer rows;
	private Integer columns;
	private Map<Position, Cell> positionCells;
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getColumns() {
		return columns;
	}
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	public Map<Position, Cell> getPositionCells() {
		return positionCells;
	}
	public void setPositionCells(Map<Position, Cell> cells) {
		this.positionCells = cells;
	}
	
}
