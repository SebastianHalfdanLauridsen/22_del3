package player;

import game.Board;
import game.Game;
import game.Main;
import gui_main.GUI;
import org.junit.Test;
import org.junit.Assert;

public class CarsTest {
    Game game;

    GUI gui;
    Cars cars;
    Board board;
    PlayerManager playerManager;

    @Test
    public void carsTest() {
        setup();
        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        playerManager.createPlayer("Skibet", 10, cars.getCars()[1]);
        playerManager.createPlayer("Katten", 10, cars.getCars()[2]);
        playerManager.createPlayer("Hunden", 10, cars.getCars()[3]);

        board.displayScoreboard(playerManager);

        Game.sleep(5000);
    }

    private void setup(){
        Main.setLanguage();

        game = new Game();

        gui = game.getGui();
        board = game.getBoard();
        playerManager = game.getPlayerManager();
        cars = game.getCars();
    }
}
