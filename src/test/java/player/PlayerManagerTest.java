package player;

import card.Deck;
import card.MoveCard;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerManagerTest {

    @Test
    public void createPlayerTest() {
        PlayerManager playerManager = new PlayerManager();
        GUI_Car car1 = new GUI_Car();
        GUI_Car car2 = new GUI_Car();
        GUI_Car car3 = new GUI_Car();

        playerManager.createPlayer("Kevse", 10, car1);
        playerManager.createPlayer("Sbebastian", 15, car2);
        playerManager.createPlayer("Chan", 20, car3);

        //names
        Assert.assertEquals("Kevse", playerManager.getPlayers(0).getGUIPlayer().getName());
        Assert.assertEquals("Sbebastian", playerManager.getPlayers(1).getGUIPlayer().getName());
        Assert.assertEquals("Chan", playerManager.getPlayers(2).getGUIPlayer().getName());

        //balances
        Assert.assertEquals(10, playerManager.getPlayers(0).getGUIPlayer().getBalance());
        Assert.assertEquals(15, playerManager.getPlayers(1).getGUIPlayer().getBalance());
        Assert.assertEquals(20, playerManager.getPlayers(2).getGUIPlayer().getBalance());

        //cars
        Assert.assertEquals(car1, playerManager.getPlayers(0).getGUIPlayer().getCar());
        Assert.assertEquals(car2, playerManager.getPlayers(1).getGUIPlayer().getCar());
        Assert.assertEquals(car3, playerManager.getPlayers(2).getGUIPlayer().getCar());
    }

    @Test
    public void playerCountTest() {
        playerCountTestAssert(0, new PlayerManager());
        playerCountTestAssert(1, new PlayerManager());
        playerCountTestAssert(1000, new PlayerManager());
    }

    private void playerCountTestAssert(int amountOfPlayers, PlayerManager playerManager) {
        for (int i = 0; i < amountOfPlayers; i++) {
            playerManager.createPlayer("", 0, new GUI_Car());
        }
        Assert.assertEquals(amountOfPlayers, playerManager.getPlayerCount());
    }
}
