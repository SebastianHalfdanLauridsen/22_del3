
package player;
import game.Board;
import game.Fields;
import game.Game;
import game.Main;
import gui_fields.GUI_Car;
import gui_fields.GUI_Street;
import gui_main.GUI;
import org.junit.Assert;
import org.junit.Test;

public class BankTest {


    //Test om balance bliver skiftet
    @Test //TODO test determineWinningPlayer and hasLost methods via this class
    // Test at spilleren der har tabts balance bliver sat til 0, test at andre spillere ikke har tabt
    public void changeBalanceTest() {
        Main.setLanguage();

        changeBalanceTestAssert(1,5, 1 + 5);
        changeBalanceTestAssert(0,-5, 0);
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
        payFieldRentTestAssert(0, 0, 0);
        payFieldRentTestAssert(-10, 0, 0);
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

