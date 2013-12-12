package ca.danib.gameoflife.service;

import ca.danib.gameoflife.model.Game;

public interface IGameService {
	public Game initializeGame(Integer rows, Integer columns);
}
