package player;
import die.Die;
import game.Board;
import game.Fields;
import game.Main;
import gui_fields.*;



import java.util.ArrayList;
import java.util.Collections;

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

    private final PlayerManager playerManager;
    private final Board board;
    private final Fields fields;

    private Player winningPlayer;

    public Bank(PlayerManager playerManager, Board board, Fields fields) {
        this.playerManager = playerManager;
        this.board = board;
        this.fields = fields;
    }

    public void buyField(int fieldIndex, Player player) throws NumberFormatException {
        GUI_Field field = fields.getFields()[fieldIndex];
        int fieldPrice = -( getFieldRent((GUI_Street) field));
        changeBalance(player, fieldPrice);
        player.addOwnedField(fieldIndex);
        board.displayBoughtField(player, field);
        board.changeFieldDescription(Main.getLanguage().getString("ownerDescription") + player.getGUIPlayer().getName(), fieldIndex);
    }

    public void payFieldRent(Player currentPlayer, Player fieldOwner, GUI_Street field) {
        int fieldRent = getFieldRent( field);
        //TODO KÆVIN FIKS TOO MANY MONEY IF NO MONEY
        // - NO PAY WHEN I GO INTO MINUS AND LOSE OHNO
        changeBalance(currentPlayer, -fieldRent);
        changeBalance(fieldOwner, fieldRent);
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
     * //TODO
     */
    private void determineWinningPlayer() {
        //"sell" all owned fields to bank
        ArrayList<Integer> balance = new ArrayList<>();

        for(int playerIndex = 0; playerIndex < playerManager.getPlayerCount(); playerIndex++) {
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
        //if more than one player has maxVal
        int numberOfMaxValues = Collections.frequency(balance,maxVal);

        int winningPlayerIndex = -1;
        if (numberOfMaxValues > 1) {
            Die die = new Die();
            while (winningPlayerIndex == -1){
                for (int playerIndex = 0; playerIndex < balance.size(); playerIndex++) {
                    //rolls the die to determine winner
                    die.roll();
                    if (balance.indexOf(playerIndex) == maxVal && die.getFace() == 6) {
                        winningPlayerIndex = playerIndex;
                    }
                }
            }
        } else {
            //Finds index for that value
            winningPlayerIndex = balance.indexOf(maxVal);
        }
        //sets winning player
        this.winningPlayer = playerManager.getPlayers(winningPlayerIndex);
    }

    /**
     * //TODO
     * @param player
     * @param change
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
     * //TODO
     * @param fieldIndex
     * @return
     */
    public Player isOwned(int fieldIndex) {
        for(int playerIndex = 0; playerIndex < playerManager.getPlayerCount(); playerIndex++) {
            Player player = playerManager.getPlayers(playerIndex);
            for(int j = 0; j < player.getOwnedFields().size(); j++) {
                int ownedFieldIndex = player.getOwnedFields().get(j);
                if (ownedFieldIndex == fieldIndex) {
                    return playerManager.getPlayers(playerIndex);
                }
            }
        }
        return null;
    }

    /**
     * converts the fields subtext to only numbers and returns the price of the field
     * @param field
     * @return
     */ //TODO numberformat exc
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