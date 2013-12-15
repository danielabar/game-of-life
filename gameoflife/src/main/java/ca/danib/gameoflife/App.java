package ca.danib.gameoflife;

import ca.danib.gameoflife.model.Game;
import ca.danib.gameoflife.service.GameServiceImpl;
import ca.danib.gameoflife.service.IGameService;

public class App  {

	public static void main( String[] args ) throws InterruptedException {
        IGameService gameService = new GameServiceImpl();
        Integer rows = 45;
		Integer columns = 45;
		Game game = gameService.initializeGame(rows, columns);
		game.draw();
		Game nextGeneration = gameService.buildNextGeneration(game);
		nextGeneration.draw();
		while (true) {
			nextGeneration = gameService.buildNextGeneration(nextGeneration);
			nextGeneration.draw();
			Thread.sleep(200);
		}
    }
}
