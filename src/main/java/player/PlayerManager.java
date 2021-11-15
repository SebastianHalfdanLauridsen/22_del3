package player;
import game.Bank;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.util.ArrayList;
//TODO make class
// - Create ArrayList for players
// - Create int playerCount
// - Create constructor
// - Create public method createPlayer that takes String name, int Balance and GUI_Car 'car' as parameters
//      and calls addPlayer.
// - Create private method addPlayer that adds a player to the ArrayList
// - Create toString and appropriate get and set methods

public class PlayerManager {

    private ArrayList<GUI_Player> childPlayers = new ArrayList();
    private static int playerCount;
    private static int id;

    // do we really need this constructor?
    public PlayerManager(int id) {
    }

    public void createPlayer(String name, double Balance, GUI_Car car) {
        GUI_Player player = new GUI_Player(name, balance, car);
        addPlayer(player);
    }

    private void addPlayer(GUI_Player player) {
        childPlayers.add(player);
    }

    public ArrayList<GUI_Player> getChildPlayers() {
        return childPlayers;
    }

    public void setChildPlayers(ArrayList<GUI_Player> childPlayers) {
        this.childPlayers = childPlayers;

    }
    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int playerCount) {
        PlayerManager.playerCount = playerCount;
    }
}
