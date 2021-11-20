package game;

import card.Deck;
import card.GetBankMoneyCard;
import gui_fields.GUI_Street;
import gui_main.GUI;
import org.junit.Assert;
import org.junit.Test;
import player.Bank;
import player.Cars;
import player.Player;
import player.PlayerManager;


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
    public void GameConstructorTest() {
        Main.setLanguage();

        Game game = new Game();
        Game.sleep(10000);
    }

}
