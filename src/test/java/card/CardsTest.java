package card;

import org.junit.Test;
import org.junit.Assert;
import game.Board;
import game.Game;
import game.Main;
import gui_main.GUI;
import player.Bank;
import player.Cars;
import player.PlayerManager;

public class CardsTest {
    Game game;
    Deck deck;
    Board board;
    PlayerManager playerManager;
    Bank bank;
    Cars cars;
    GUI gui;

    @Test
    public void cardsTest() {
        setup();

        new Cards(deck, board, bank, playerManager, gui, game);

        for (AbstractCard d : deck.getCards()) {
            Game.sleep();
            gui.displayChanceCard(d.getDescription());
        }

        Game.sleep(5000);
    }

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
}
