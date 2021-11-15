package game;
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
    private double balance;

    public void changePlayerBalance(double balance) {
        this.balance = balance;
        if (balance < 0) {
            balance = 0;
        }
    }

    public void buyField(PlayerManager playerManager) {

    }

    //public void payRent(int id, player) {
        // player.balance = balance - field_rent;
    //}
}

