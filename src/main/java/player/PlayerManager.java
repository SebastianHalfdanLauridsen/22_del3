package player;

import java.util.ArrayList;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import game.Game;

/**
 * The PlayerManager holds all the players and can create the players in itself
 *   and in the GUI at the same time.
 */
public class PlayerManager {

    private int playerCount;
    private final ArrayList<Player> players;

    public PlayerManager() {
        this.players = new ArrayList<>();
        this.playerCount = 0;
    }

    /**
     * Create players and add to GUI and private array
     */
    public void createPlayer(String name, int balance, GUI_Car car) {
        GUI_Player GUIPlayer = new GUI_Player(name, balance,car);
        Player player = new Player(Game.START_FIELD,GUIPlayer);
        addPlayer(player);
    }

    private void addPlayer(Player player) {
        players.add(player);
        playerCount++;
    }

    public Player getPlayers(int index) {
        return players.get(index);
    }
    public int getPlayerCount() {
        return playerCount;
    }
}