package game;

import org.junit.Assert;
import org.junit.Test;


public class GameTest {
    Game game;
    Deck deck;
    Board board;
    PlayerManager playerManager;
    Bank bank;
    Cars cars;
    GUI gui;

    private void setup(){
        Main.setLanguage();

        game = new Game();
        deck = game.getDeck();
        board = game.getBoard();
        playerManager = game.getPlayerManager();
        bank = game.getBank();
        cars = game.getCars();
        gui = game.getGui();
    }

    @Test
    public void firstTest() {
        Assert.assertTrue(true);
    }

}
