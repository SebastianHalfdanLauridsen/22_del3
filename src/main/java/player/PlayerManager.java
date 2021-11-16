package player;
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

    private ArrayList<Player> childPlayers;
    private static int playerCount;


    public PlayerManager() {
        this.childPlayers = new ArrayList<>();
    }


    public void createPlayer(String name, int balance, GUI_Car car) {
        GUI_Player GuiPlayer = new GUI_Player(name, balance, car);
        Player player = new Player(name, GuiPlayer);
        addPlayer(player);
    }

    private void addPlayer(Player player) {
        childPlayers.add(player);
    }

    public Player getChildPlayers(int index) {
        return childPlayers.get(index);
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int playerCount) {
        PlayerManager.playerCount = playerCount;
    }
}
