package game;
import gui_fields.*;
import gui_main.GUI;
import player.Player;
import player.PlayerManager;


import java.util.ArrayList;

//TODO make class
// - Create a private ArrayList that contains nothing.
// - Create constructor
// - Create public method to change a players balance through PlayerManager (parse as parameter)
// - Create public method that lets a player buy a field with parameters PlayerManager 'playerManager'
// - Create public method that lets a player pay rent. (Maybe GUI can do?)
// - create public method that adds a player to a given index (field)
//      in the ArrayList which makes the player take ownership of that field
// - Create appropriate get and set methods as well as a custom toString method
public class Bank {

    private final ArrayList bankAccount = new ArrayList();
    GUI gui = new GUI();
    private PlayerManager playerManager;

    public Bank(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void changeBalance(Player player, int ammount) {
        player.getGui_Player().setBalance(ammount + player.getGui_Player().getBalance());
    }

    public void buyField(Player player) {
        GUI_Field field = gui.getFields()[player.getFieldPosition()];
        GUI_Ownable ownable = (GUI_Ownable) field;
        int pris = -Integer.parseInt(ownable.getRent());
        changeBalance(player, pris);
        ownable.setOwnerName(player.getName());
        player.setPlayerAssets();
    }

    public void payRent(Player owner ,Player player) {
        GUI_Field field = gui.getFields()[player.getFieldPosition()];
        int leje = Integer.parseInt(((GUI_Ownable) field).getRent());
        changeBalance(player, -leje);
        changeBalance(owner, leje);
    }
}

