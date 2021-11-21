package player;

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

    /**
     * //TODO
     * @param fieldIndex
     * @param player
     */
    public void receiveField(int fieldIndex, Player player) {
        GUI_Field field = fields.getFields()[fieldIndex];

        player.addOwnedField(fieldIndex);
        board.displayBoughtField(player, field);
        board.changeFieldDescription(Main.getLanguage().getString("ownerDescription") + player.getGUIPlayer().getName(), fieldIndex);
    }

    /**
     * Lets the player pay the fieldOwner the amount of rent from the fieldOwners field
     * @param player the payee of rent
     * @param fieldOwner the owner of the field to receive rent
     * @param field the field with a rent
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

    /**
     * //TODO
     * @param player
     * @return
     */
    private boolean hasLost(Player player) {
        int playerBalance = player.getGUIPlayer().getBalance();
        if(playerBalance <= 0) {
            player.getGUIPlayer().setBalance(0);

            return true;
        }
        return false;
    }

    /**
     * //TODO
     * @param playerIndex
     */
    private void sellAllFields(int playerIndex) {
        //"sell" all owned fields to bank
        Player player = playerManager.getPlayers(playerIndex);
        for(int fieldIndex = 0; fieldIndex < player.getOwnedFields().size(); fieldIndex++) {
            GUI_Street streetField = (GUI_Street) fields.getFields()[player.getOwnedFields().get(fieldIndex)];
            int streetValue = getFieldRent(streetField);
            changeBalance(player, streetValue);
        }

    }

    /**
     * Determines the winning player in regard to how much money and property value each player has.
     * The losing player cannot sell their fields and win.
     */
    private void determineWinningPlayer() {
        ArrayList<Integer> balances = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < playerManager.getPlayerCount(); playerIndex++) {
            if(hasLost(playerManager.getPlayers(playerIndex))) {
                balances.add(0);
                continue;
            }
            sellAllFields(playerIndex);
            int playerBalance = playerManager.getPlayers(playerIndex).getGUIPlayer().getBalance();
            balances.add(playerBalance);
        }
        int balanceOfWinner = Collections.max(balances);
        int winningPlayerIndex = balances.indexOf(balanceOfWinner);
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