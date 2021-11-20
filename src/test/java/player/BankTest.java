
package player;
import game.Board;
import game.Fields;
import game.Game;
import game.Main;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BankTest {


    //Test om balance bliver skiftet
    @Test //TODO test determineWinningPlayer and hasLost methods via this class
    public void determineWinningPlayerTest() {
        Main.setLanguage();
        determineWinningPlayerTest1();
        determineWinningPlayerTest2();

    }
    private void determineWinningPlayerTest1(){
        Game game = new Game();
        Bank bank = game.getBank();
        PlayerManager playerManager = game.getPlayerManager();
        Cars cars = game.getCars();
        Fields fields = game.getFields();
        Board board = game.getBoard();

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        playerManager.createPlayer("Skibet", 5, cars.getCars()[1]);

        Player player1 = playerManager.getPlayers(0);
        Player player2 = playerManager.getPlayers(1);

        board.displayScoreboard(playerManager);

        //Gives all, except 1, GUIStreetFields to player 2
        ArrayList<GUI_Street> GUIStreetFields = new ArrayList<>();
        for (int i = 0; i < 23; i++){
            GUI_Field field = fields.getFields()[i];
            if (field instanceof GUI_Street) {
                bank.receiveField(i, player2);
            }
        }

        bank.receiveField(23, player1);

        Game.sleep(1000);

        board.displayInstantMovingPlayer(player2.getGUIPlayer(), 12);
        game.movePlayer(player2, 12);

        board.displayMovingPlayer(player2.getGUIPlayer(),11, player2.getFieldPosition());
        game.movePlayer(player2, 12);
        game.fieldAction(player2, 23);

        Game.sleep(1000);
        Assert.assertEquals(player1, bank.getWinningPlayer());
        Game.sleep(5000);

    }
    private void determineWinningPlayerTest2(){
        Game game = new Game();
        Bank bank = game.getBank();
        PlayerManager playerManager = game.getPlayerManager();
        Cars cars = game.getCars();
        Board board = game.getBoard();

        playerManager.createPlayer("Bilen", 10, cars.getCars()[0]);
        playerManager.createPlayer("Skibet", 5, cars.getCars()[1]);

        Player player1 = playerManager.getPlayers(0);
        Player player2 = playerManager.getPlayers(1);

        board.displayScoreboard(playerManager);

        int fieldIndex = 23;
        bank.receiveField(fieldIndex, player1);

        Game.sleep(2000);

        game.movePlayer(player2, 3);
        board.displayInstantMovingPlayer(player2.getGUIPlayer(),3);
        Game.sleep(1000);


        game.movePlayer(player2, fieldIndex);
        board.displayInstantMovingPlayer(player2.getGUIPlayer(),fieldIndex);
        game.fieldAction(player2, fieldIndex);

        Game.sleep(1000);
        Assert.assertEquals(player1, bank.getWinningPlayer());
        Game.sleep(5000);
    }

    @Test
    public void changeBalanceTest() {
        Main.setLanguage();

        changeBalanceTestAssert(1,5, 1 + 5);
        changeBalanceTestAssert(10, -5, 5);
    }

    public void changeBalanceTestAssert(int startBal, int changeBal, int expectedEndBal) {
        PlayerManager playerManager = new PlayerManager();
        Bank bank = new Bank(playerManager, new Board(new GUI()), new Fields());

        playerManager.createPlayer("Kevse", startBal, new GUI_Car());

        bank.changeBalance(playerManager.getPlayers(0), changeBal);

        Assert.assertEquals(expectedEndBal,playerManager.getPlayers(0).getGUIPlayer().getBalance());
    }

    @Test
    public void payFieldRentTest() {
        Main.setLanguage();

        payFieldRentTestAssert(10, 5, 15);
        payFieldRentTestAssert(1000, 995, 1005);
        payFieldRentTestAssert(2, 0, 4);
    }

    private void payFieldRentTestAssert(int startBalance, int expectedPayerBalance, int expectedPayeeBalance) {
        GUI gui = new GUI(new Fields().getFields());
        PlayerManager playerManager = new PlayerManager();
        Bank bank = new Bank(playerManager, new Board(new GUI()), new Fields());

        playerManager.createPlayer("Kevse", startBalance, new GUI_Car());
        playerManager.createPlayer("Sbebastian", startBalance, new GUI_Car());

        Player player1 = playerManager.getPlayers(0);
        Player player2 = playerManager.getPlayers(1);

        GUI_Street field = (GUI_Street) gui.getFields()[22];

        bank.payFieldRent(player1, player2, field);

        Assert.assertEquals(expectedPayerBalance,player1.getGUIPlayer().getBalance());
        Assert.assertEquals(expectedPayeeBalance,player2.getGUIPlayer().getBalance());
    }

    @Test
    public void buyFieldTest() {
        Main.setLanguage();

        buyFieldTestAssert(10);
        buyFieldTestAssert(100);
        buyFieldTestAssert(6);
        buyFieldTestAssert(3);
        buyFieldTestAssert(0);
        buyFieldTestAssert(-1);
    }

    public void buyFieldTestAssert(int startBalance) {
        Fields fields = new Fields();
        GUI gui = new GUI(fields.getFields());
        Board board = new Board(gui);
        PlayerManager playerManager = new PlayerManager();
        Bank bank = new Bank(playerManager, board, fields);

        playerManager.createPlayer("Kevse", startBalance, new GUI_Car());
        playerManager.createPlayer("Sbebastian", 69, new GUI_Car());

        Player player = playerManager.getPlayers(0);
        int fieldIndex1 = 22;
        int fieldIndex2 = 2;
        GUI_Street field1 = (GUI_Street) fields.getFields()[fieldIndex1];
        GUI_Street field2 = (GUI_Street) fields.getFields()[fieldIndex2];
        int totalPrice = Integer.parseInt(field1.getRent()) + Integer.parseInt(field2.getRent());

        //Visual Test for displaying bought field, field description and change in player balance in GUI
        board.displayScoreboard(playerManager);
        Game.sleep(1000);
        bank.buyField(fieldIndex1, player);
        Game.sleep(1000);
        bank.buyField(fieldIndex2, player);
        Game.sleep(1000);

        //To find the expected value (this also tests getFieldRent())
        if (totalPrice <= startBalance) {
            Assert.assertEquals(startBalance - totalPrice,player.getGUIPlayer().getBalance());
        } else {
            Assert.assertEquals(0,player.getGUIPlayer().getBalance());
        }
        //To assert if the fields are in the array
        Assert.assertEquals((Integer) fieldIndex1,player.getOwnedFields().get(0));
        Assert.assertEquals((Integer) fieldIndex2,player.getOwnedFields().get(1));
    }

    //
    @Test
    public void isOwnedTest() {
        Main.setLanguage();
        Fields fields = new Fields();
        GUI gui = new GUI(fields.getFields());
        Board board = new Board(gui);
        PlayerManager playerManager = new PlayerManager();
        Bank bank = new Bank(playerManager, board, fields);

        playerManager.createPlayer("Kevse", 49, new GUI_Car());
        playerManager.createPlayer("Sbebastian", 69, new GUI_Car());
        Player player1 = playerManager.getPlayers(0);
        Player player2 = playerManager.getPlayers(1);

        int fieldIndex1 = 1;
        int fieldIndex4 = 4;
        int fieldIndex7 = 7;
        int fieldIndex22 = 22;
        int fieldIndex23 = 23;

        bank.buyField(fieldIndex1, player1);
        bank.buyField(fieldIndex7, player1);

        bank.buyField(fieldIndex4, player2);
        bank.buyField(fieldIndex22, player2);
        bank.buyField(fieldIndex23, player2);

        Assert.assertEquals(player1, bank.isOwned(fieldIndex1));
        Assert.assertEquals(player1, bank.isOwned(fieldIndex7));

        Assert.assertEquals(player2, bank.isOwned(fieldIndex4));
        Assert.assertEquals(player2, bank.isOwned(fieldIndex22));
        Assert.assertEquals(player2, bank.isOwned(fieldIndex23));

        Assert.assertNull(bank.isOwned(2));
        Assert.assertNull(bank.isOwned(8));
        Assert.assertNull(bank.isOwned(10));
        Assert.assertNull(bank.isOwned(11));
    }
}

