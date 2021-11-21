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

    @Test
    public void runGameTest() {
        Main.setLanguage();

        setup();
        game.runGame();
    }

    @Test
    public void GUIStreetActionTest() {
        setup();

        int fieldIndex1 = 1;
        int fieldIndex2 = 2;
        int startBalance = 10;
        int expectedBalPlayer1;
        int expectedBalPlayer2;

        playerManager.createPlayer("Bilen", startBalance, cars.getCars()[0]);
        playerManager.createPlayer("Skibet", startBalance, cars.getCars()[1]);

        Player player1 = playerManager.getPlayers(0);
        Player player2 = playerManager.getPlayers(1);
        bank.receiveField(fieldIndex2, player2);
        board.displayScoreboard(playerManager);

        //set player1 to field1
        board.displayInstantMovingPlayer(player1.getGUIPlayer(),fieldIndex1);
        game.movePlayer(player1,fieldIndex1);
        Game.sleep(2000);

        expectedBalPlayer1 = startBalance
                - Integer.parseInt(((GUI_Street) gui.getFields()[fieldIndex1]).getRent());
        //player 1 buys field 1
        game.GUIStreetAction(player1, fieldIndex1);
        Assert.assertEquals(player1, bank.isOwned(fieldIndex1));
        Assert.assertEquals(expectedBalPlayer1, player1.getGUIPlayer().getBalance());
        Game.sleep(2000);

        //move player1 to field2
        board.displayInstantMovingPlayer(player1.getGUIPlayer(),fieldIndex2);
        game.movePlayer(player1,fieldIndex2);
        Game.sleep(2000);

        expectedBalPlayer1 = startBalance
                - Integer.parseInt(((GUI_Street) gui.getFields()[fieldIndex1]).getRent())
                - Integer.parseInt(((GUI_Street) gui.getFields()[fieldIndex2]).getRent());
        expectedBalPlayer2 = startBalance
                + Integer.parseInt(((GUI_Street) gui.getFields()[fieldIndex2]).getRent());

        //player 1 pays player 2 for field 2
        game.GUIStreetAction(player1, fieldIndex2);
        Assert.assertEquals(player2, bank.isOwned(fieldIndex2));
        Assert.assertEquals(expectedBalPlayer1, player1.getGUIPlayer().getBalance());
        Assert.assertEquals(expectedBalPlayer2, player2.getGUIPlayer().getBalance());
        Game.sleep(2000);
        //move player1 back to field1
        board.displayInstantMovingPlayer(player1.getGUIPlayer(),fieldIndex1);
        game.movePlayer(player1,fieldIndex1);
        Game.sleep(2000);

        //player 1 does nothing as field 1 is his own field
        game.GUIStreetAction(player1, fieldIndex1);
        Assert.assertEquals(player1, bank.isOwned(fieldIndex1));
        Assert.assertEquals(expectedBalPlayer1, player1.getGUIPlayer().getBalance());

        Game.sleep(5000);
    }

    @Test
    public void GUIChanceActionTest() {
        setup();

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        Player player = playerManager.getPlayers(0);
        deck.addCard("name", "desc", new GetBankMoneyCard(15, bank));
        board.displayScoreboard(playerManager);

        Game.sleep(2000);
        game.movePlayer(player, 3);
        board.displayInstantMovingPlayer(player.getGUIPlayer(),3);


        Game.sleep(2000);
        game.GUIChanceAction(player);

        Assert.assertEquals(25, player.getGUIPlayer().getBalance());

        Game.sleep(5000);
    }

    private void fieldActionTestMover(int fieldIndex, Player player){
        Game.sleep(2000);
        game.movePlayer(player, fieldIndex);
        board.displayInstantMovingPlayer(player.getGUIPlayer(),fieldIndex);
        game.fieldAction(player, fieldIndex);
    }

    @Test
    public void fieldActionTest() {
        setup();
        int startBalance = 10;
        int payment = 15;
        int expected;

        playerManager.createPlayer("Bilen", startBalance, cars.getCars()[0]);
        Player player = playerManager.getPlayers(0);
        deck.addCard("name", "desc", new GetBankMoneyCard(payment, bank));
        board.displayScoreboard(playerManager);

        //test buy field
        expected = startBalance - Integer.parseInt( ( (GUI_Street) (gui.getFields()[1]) ).getRent() );
        fieldActionTestMover(1, player);
        Assert.assertEquals(expected, player.getGUIPlayer().getBalance());
        Assert.assertFalse(player.isInJail());

        //test chance field
        expected += payment;
        fieldActionTestMover(Game.MAX_FIELDS/8, player);
        Assert.assertEquals(expected, player.getGUIPlayer().getBalance());
        Assert.assertFalse(player.isInJail());

        //test jail field
        fieldActionTestMover(Game.MAX_FIELDS/4, player);
        Assert.assertEquals(expected, player.getGUIPlayer().getBalance());
        Assert.assertFalse(player.isInJail());

        //test Parking field
        fieldActionTestMover(Game.MAX_FIELDS/2, player);
        Assert.assertEquals(expected, player.getGUIPlayer().getBalance());
        Assert.assertFalse(player.isInJail());

        //test goTo Jail field
        fieldActionTestMover((int)(Game.MAX_FIELDS / (4.0/3.0)), player);
        Assert.assertEquals(expected, player.getGUIPlayer().getBalance());
        Assert.assertTrue(player.isInJail());

        Game.sleep(5000);
    }

    @Test
    public void movePlayerTest() {
        setup();

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        Player player = playerManager.getPlayers(0);

        game.movePlayer(player, 1);
        Assert.assertEquals(1, player.getFieldPosition());
        Assert.assertEquals(10, player.getGUIPlayer().getBalance());

        game.movePlayer(player, 23);
        Assert.assertEquals(23, player.getFieldPosition());
        Assert.assertEquals(10, player.getGUIPlayer().getBalance());

        game.movePlayer(player, 25);
        Assert.assertEquals(25 % Game.MAX_FIELDS, player.getFieldPosition());
        Assert.assertEquals(12, player.getGUIPlayer().getBalance());
    }
}
