package card;

import game.Board;
import game.Game;
import game.Main;
import gui_fields.GUI_Street;
import gui_main.GUI;
import org.junit.Test;
import org.junit.Assert;
import player.Bank;
import player.Cars;
import player.Player;
import player.PlayerManager;

public class AllCardsTest {
    Game game;
    Deck deck;
    Board board;
    PlayerManager playerManager;
    Bank bank;
    Cars cars;
    GUI gui;

    @Test
    public void GetBankMoneyCardTest() {
        setup();

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);

        deck.addCard("name", "desc", new GetBankMoneyCard(2, bank));

        deck.drawCard().action(playerManager.getPlayers(0));

        Assert.assertEquals(12, playerManager.getPlayers(0).getGUIPlayer().getBalance());

    }

    @Test
    public void GetOutOfJailTest() {

    }

    @Test
    public void GetPlayersMoneyCardTest() {

    }


    @Test
    public void MoveCardTest() {
        setup();

        int fieldIndex = 23;

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        Player player = playerManager.getPlayers(0);
        board.displayInstantMovingPlayer(player.getGUIPlayer(), 3);
        deck.addCard("card1", "desc1", new MoveCard(fieldIndex, board, game));

        Game.sleep(500);
        deck.drawCard().action(player);

        Assert.assertEquals(player.getFieldPosition(), fieldIndex);
        int fieldRent = Integer.parseInt(((GUI_Street) gui.getFields()[fieldIndex]).getRent());
        Assert.assertEquals(fieldRent, player.getGUIPlayer().getBalance());
        Assert.assertEquals(player, bank.isOwned(fieldIndex));
        Game.sleep(5000);
    }

    @Test
    public void MoveFieldGetFreeCardTest() {
        setup();

        //orange and green fields
        deck.addCard("card1", "desc1", new MoveFieldGetFreeCard(10, 11, 19, 20, gui, game, board, bank));
        //red fields
        deck.addCard("card1", "desc1", new MoveFieldGetFreeCard(13, 14, gui, game, board, bank));

        playerManager.createPlayer("Bilen",10, cars.getCars()[0]);
        playerManager.createPlayer("Skibet", 10, cars.getCars()[1]);

        Player bilen = playerManager.getPlayers(0);
        Player skibet = playerManager.getPlayers(1);
        bank.receiveField(14, skibet);
        bank.receiveField(20, skibet);
        board.displayScoreboard(playerManager);

        board.displayInstantMovingPlayer(bilen.getGUIPlayer(),3);
        deck.drawCard().action(bilen);
        //bank.buyField(player.getFieldPosition(), player);
        Game.sleep(3000);
        board.displayInstantMovingPlayer(bilen.getGUIPlayer(),3);
        deck.drawCard().action(bilen);
        Game.sleep(5000);
    }

    @Test
    public void MoveMaxFiveCardTest() {
        setup();

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        Player player = playerManager.getPlayers(0);
        Game.sleep();
        game.movePlayer(player, 3);
        board.displayInstantMovingPlayer(player.getGUIPlayer(),3);
        deck.addCard("card1", "desc1", new MoveMaxFiveCard(gui, board, game));
        int playerPos = player.getFieldPosition();
        deck.drawCard().action(player);
        Assert.assertTrue("player moved too far or not far enough", playerPos + 1 <= player.getFieldPosition()
                && player.getFieldPosition() <= playerPos + 5);
        Game.sleep(5000);
    }

    @Test
    public void MoveOrDrawCardTest() {
        setup();

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        Player player = playerManager.getPlayers(0);
        Game.sleep();
        game.movePlayer(player, 3);
        board.displayInstantMovingPlayer(player.getGUIPlayer(),3);
        deck.addCard("card1", "desc1", new MoveOrDrawCard(1, gui, board, game));
        deck.addCard("card2", "desc2", new MoveOrDrawCard(60, gui, board, game));
        deck.addCard("this is another card", "this is another description", new GetBankMoneyCard(2, bank));
        int playerPos = player.getFieldPosition();
        deck.drawCard().action(player);
        Game.sleep(1000);

        game.movePlayer(player, 3);
        board.displayInstantMovingPlayer(player.getGUIPlayer(),3);
        deck.drawCard().action(player);

        Game.sleep(5000);
    }

    @Test
    public void PassPlayerMoveCardTest() {

    }

    @Test
    public void PayBankCardTest() {

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
