package player;
import die.Die;
import game.Board;
import game.Fields;
import game.Main;
import gui_fields.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * //TODO
 */
public class Bank {
    private final PlayerManager playerManager;
    private final Board board;
    private final Fields fields;

    private Player winningPlayer;

    public Bank(PlayerManager playerManager, Board board, Fields fields) {
        this.playerManager = playerManager;
        this.board = board;
        this.fields = fields;
    }

    /**
     * Lets the player buy the field for the amount of rent from the given field
     * @param fieldIndex the int index of the field to be bought by the player
     * @param player the player who buys the field
     */
    public void buyField(int fieldIndex, Player player) {
        GUI_Field field = fields.getFields()[fieldIndex];
        int fieldPrice = getFieldRent((GUI_Street) field);

        changeBalance(player, -fieldPrice);
        player.addOwnedField(fieldIndex);
        board.displayBoughtField(player, field);
        board.changeFieldDescription(Main.getLanguage().getString("ownerDescription") + player.getGUIPlayer().getName(), fieldIndex);
    }

    public void receiveField(int fieldIndex, Player player) {
        GUI_Field field = fields.getFields()[fieldIndex];

        player.addOwnedField(fieldIndex);
        board.displayBoughtField(player, field);
        board.changeFieldDescription(Main.getLanguage().getString("ownerDescription") + player.getGUIPlayer().getName(), fieldIndex);
    }

    /**
     * Lets the player pay the fieldOwner the amount of rent from the fieldOwners field
     * @param player the payee
     * @param fieldOwner the owner of the field
     * @param field the field
     */
    public void payFieldRent(Player player, Player fieldOwner, GUI_Street field) {
        int fieldRent = getFieldRent( field);
        int playerBalance = player.getGUIPlayer().getBalance();
        //Checks if the field rent would make the balance go below 0
        if (playerBalance < fieldRent) {
            changeBalance(player, -playerBalance);
            changeBalance(fieldOwner, playerBalance);
        } else {
            changeBalance(player, -fieldRent);
            changeBalance(fieldOwner, fieldRent);
        }
    }

    private boolean hasLost(Player player) {
        int playerBalance = player.getGUIPlayer().getBalance();
        if(playerBalance <= 0) {
            player.getGUIPlayer().setBalance(0);

            return true;
        }
        return false;
    }

    /**
     * Determines the winning player in regard to how much money and property value each player has
     *   if two or more players have the same amount of total value to their name a die will be rolled
     *   and a winning player will be determined by chance
     */
    private void determineWinningPlayer() {
        ArrayList<Integer> balance = new ArrayList<>();
        //"sell" all owned fields to bank
        for (int playerIndex = 0; playerIndex < playerManager.getPlayerCount(); playerIndex++) {
            //the losing player may not sell their fields. THEY ARE LOSERS HAHAHAHAHAHA
            if(hasLost(playerManager.getPlayers(playerIndex))) {
                continue;
            }
            Player player = playerManager.getPlayers(playerIndex);
            for(int fieldIndex = 0; fieldIndex < player.getOwnedFields().size(); fieldIndex++) {
                GUI_Street streetField = (GUI_Street) fields.getFields()[player.getOwnedFields().get(fieldIndex)];
                int streetValue = getFieldRent(streetField);
                changeBalance(player, streetValue);
            }
            int playerBalance = playerManager.getPlayers(playerIndex).getGUIPlayer().getBalance();
            balance.add(playerBalance);
        }
        Integer maxVal = Collections.max(balance);

        int numberOfMaxValues = Collections.frequency(balance,maxVal);
        int winningPlayerIndex = -1;

        //TODO maybe make visual repr in GUI
        if (numberOfMaxValues > 1) {
            Die die = new Die();
            while (winningPlayerIndex == -1){
                //rolls the die for each eligible player to determine the winner
                for (int playerIndex = 0; playerIndex < balance.size(); playerIndex++) {
                    die.roll();
                    if (balance.indexOf(playerIndex) == maxVal && die.getFace() == 6) {
                        winningPlayerIndex = playerIndex;
                    }
                }
            }
        } else {
            winningPlayerIndex = balance.indexOf(maxVal);
        }
        this.winningPlayer = playerManager.getPlayers(winningPlayerIndex);
    }

    /**
     * Changes the balance of the player
     * @param player the player object whose balance will be changed
     * @param change the change in balance
     */
    public void changeBalance(Player player, int change) {
        int currentBalance = player.getGUIPlayer().getBalance();
        player.getGUIPlayer().setBalance(currentBalance + change);

        if(hasLost(player)) {
            determineWinningPlayer();
        }
        System.out.println(player.getGUIPlayer().getName() + " balance changed by " + change);
    }

    /**
     * Determines if any player owns a given field and returns that player
     * @param fieldIndex the field that can be owned
     * @return the player who may own the field, otherwise null
     */
    public Player isOwned(int fieldIndex) {
        for (int playerIndex = 0; playerIndex < playerManager.getPlayerCount(); playerIndex++) {
            Player player = playerManager.getPlayers(playerIndex);
            for (int j = 0; j < player.getOwnedFields().size(); j++) {
                int ownedFieldIndex = player.getOwnedFields().get(j);
                if (ownedFieldIndex == fieldIndex) {
                    return playerManager.getPlayers(playerIndex);
                }
            }
        }
        return null;
    }

    /**
     * Converts the fields' subtext to only numbers and returns the price of the field
     * @param field the field whose subtext needs to be converted
     * @return the subtext in only numbers
     */
    private int getFieldRent(GUI_Street field) throws NumberFormatException {
        String fieldPrice = field.getRent();
        //thanks to https://attacomsian.com/blog/java-extract-digits-from-string
        String fieldPriceNr = fieldPrice.replaceAll("[^0-9]", "");
        return Integer.parseInt(fieldPriceNr);
    }
    public boolean playerHasWon() {
        return winningPlayer != null;
    }
    public Player getWinningPlayer() {
        return winningPlayer;
    }
}