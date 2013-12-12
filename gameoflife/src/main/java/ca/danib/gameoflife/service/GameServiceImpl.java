package ca.danib.gameoflife.service;

import java.util.HashMap;
import java.util.Map;

import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Game;
import ca.danib.gameoflife.model.Position;

public class GameServiceImpl implements IGameService {

	@Override
	public Game initializeGame(Integer rows, Integer columns) {
		Game game = new Game();
		game.setRows(rows);
		game.setColumns(columns);
		game.setPositionCells(buildCells(rows, columns));
		return game;
	}

	protected Map<Position, Cell> buildCells(Integer rows, Integer columns) {
		Map<Position, Cell> cells = new HashMap<Position, Cell>();
		for(int row=0; row<rows; row++) {
			for(int column=0; column<columns; column++) {
				Position position = new Position(row, column);
				cells.put(position, buildCell(position));
			}
		}
		return cells;
	}

	private Cell buildCell(Position position) {
		Cell cell = new Cell(position);
		return cell;
	}

}
