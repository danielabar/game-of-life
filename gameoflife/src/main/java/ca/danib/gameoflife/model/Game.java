package ca.danib.gameoflife.model;


public class Game {

	private Integer rows;
	private Integer columns;
	private Board positionCells;

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
	public Board getPositionCells() {
		return positionCells;
	}
	public void setPositionCells(Board positionCells) {
		this.positionCells = positionCells;
	}

}
