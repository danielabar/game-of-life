package ca.danib.gameoflife.model;


public class Game {

	private Integer rows;
	private Integer columns;
	private Board board;

	public Game(Integer rows, Integer columns, Board board) {
		super();
		this.rows = rows;
		this.columns = columns;
		this.board = board;
	}

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
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board positionCells) {
		this.board = positionCells;
	}

}
