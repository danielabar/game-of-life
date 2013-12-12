package ca.danib.gameoflife.service;

import java.util.HashMap;
import java.util.Map;

import ca.danib.gameoflife.model.Cell;
import ca.danib.gameoflife.model.Game;
import ca.danib.gameoflife.model.LifeStatus;
import ca.danib.gameoflife.model.Position;
import ca.danib.gameoflife.model.Board;

public class GameServiceImpl implements IGameService {

	@Override
	public Game initializeGame(Integer rows, Integer columns) {
		Game game = new Game();
		game.setRows(rows);
		game.setColumns(columns);
		game.setPositionCells(buildCells(rows, columns));
		return game;
	}

	protected Board buildCells(Integer rows, Integer columns) {
		Map<Position, Cell> positionCells = new HashMap<Position, Cell>();
		for(int row=0; row<rows; row++) {
			for(int column=0; column<columns; column++) {
				Position position = new Position(row, column);
				positionCells.put(position, buildCell(position));
			}
		}
		return new Board(positionCells);
	}

	protected Cell buildCell(Position position) {
		Cell cell = new Cell(position);
		cell.setLifeStatus(initializeLifeStatus(position.getRow(), position.getColumn()));
		return cell;
	}

	// This could be in a SeedService/Strategy
	protected LifeStatus initializeLifeStatus(Integer row, Integer column) {
		if(row % 3 == 0) {
			return LifeStatus.ALIVE;
		}
		return LifeStatus.DEAD;
	}

}
