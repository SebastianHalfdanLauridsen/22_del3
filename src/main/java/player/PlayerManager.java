package player;

import java.util.ArrayList;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import game.Game;

public class PlayerManager {

    private int playerCount;
    private final ArrayList<Player> players;
    public ArrayList<Player> jailList;

    public PlayerManager() {
        this.players = new ArrayList<>();
        this.playerCount = 0;
    }

    //TODO javadoc
    /**
     * Create players and add to GUI and private array
     */
    public void createPlayer(String name, int balance, GUI_Car car) {
        GUI_Player GUIPlayer = new GUI_Player(name, balance,car);
        Player player = new Player(Game.START_FIELD,GUIPlayer);
        addPlayer(player);
    }

    //TODO javadoc
    /**
     * adds players to PlayerManager
     * @param player the player object to be added
     */
    private void addPlayer(Player player) {
        players.add(player);
        playerCount++;
    }
    //TODO javadoc
    /**
     * returns player at given index in array
     * @param index
     * @return
     */
    public Player getPlayers(int index) {
        return players.get(index);
    }

    public int getPlayerCount() {
        return playerCount;
    }

    //TODO maybe not
    @Override
    public String toString() {
        return "PlayerManager{" +
                "playerCount=" + playerCount +
                ", players=" + players +
                '}';
    }


}