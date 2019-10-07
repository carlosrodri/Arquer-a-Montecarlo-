package controllers;

import models.dao.Game;

public class Controller {

	public Controller() {
		Game game = new Game(true, true);
		Game gameTwo = new Game(true, false);
		Game gameThree = new Game(false, false);
		game.generateDeparture();
		gameTwo.generateDeparture();
		gameThree.generateDeparture();
		game.printStatics();
	}

}
