package game;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;
import player.Player;
import player.PlayerManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The board class is mainly responsible for displaying various visual elements
 *   mainly through the GUI
 */
public class Board {
    private final ArrayList<String> playerNames = new ArrayList<>();
    private final GUI gui;
    private int userCount = 0;
    private final String[] ordinal = {
            Main.getLanguage().getString("ordinal1"),
            Main.getLanguage().getString("ordinal2"),
            Main.getLanguage().getString("ordinal3"),
            Main.getLanguage().getString("ordinal4")
    };

    public Board(GUI gui) {
        this.gui = gui;
    }

    /**
     * Determines if the name has already been entered in playerNames
     * @param name the name to be compared to playerNames
     * @return true if a duplicate
     */
    private boolean isDuplicateName(String name) {
        for (int index = 0; index < playerNames.size() - 1; index++) {
            if (name.equalsIgnoreCase(playerNames.get(index))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lets the user enter the amount of players and their names
     */
    public void displayStartUI() {
        gui.showMessage(Main.getLanguage().getString("welcomeMessage"));
        userCount = gui.getUserInteger(Main.getLanguage().getString("playerAskAmount") + ":", Game.MIN_PLAYER, Game.MAX_PLAYER);

        for (int currentUser = 0; currentUser < userCount; currentUser++) {
            receiveUserInput(currentUser);
        }
    }

    /**
     * Lets the user enter their names, determines if they are blank or duplicates
     * and saves the names
     * @param currentUser the current user index who may enter their name
     */
    private void receiveUserInput(int currentUser) {
        String userInput = gui.getUserString(
                Main.getLanguage().getString("enterOrdinalName1")
                        + ordinal[currentUser]
                        + Main.getLanguage().getString("enterOrdinalName2") + ":");

        if (userInput.isBlank()) {
            gui.showMessage(Main.getLanguage().getString("inputIsBlank"));
            receiveUserInput(currentUser);
        }

        playerNames.add(userInput);

        if (currentUser > 0 && isDuplicateName(userInput)) {
            gui.showMessage(Main.getLanguage().getString("inputIsDuplicate"));
            playerNames.remove(currentUser);
            receiveUserInput(currentUser);
        }
        playerNames.removeIf(String::isBlank);
    }


    /**
     * Displays the scoreboard on the GUI
     */
    public void displayScoreboard(PlayerManager playerManager) {
        for (int i = 0; i < playerManager.getPlayerCount(); i++) {
            gui.addPlayer( playerManager.getPlayers(i).getGUIPlayer() );
        }
    }

    public void displayCar(GUI_Player player, GUI_Field field) {
        field.setCar(player, true);
    }

    /**
     *
     * @param player
     * @param moves
     * @param currentFieldIndex
     */
    public void displayMovingPlayer(GUI_Player player, int moves, int currentFieldIndex){
        for (int nextFieldIndex = currentFieldIndex; nextFieldIndex <= currentFieldIndex+moves; nextFieldIndex++) {
            Game.sleep(400);
            //removes all images of 'player's car from all fields
            for (GUI_Field f : gui.getFields()) {
                f.setCar(player, false);
            }
            //displays the car on the next field
            if (nextFieldIndex >= Game.MAX_FIELDS) {
                displayCar(player,gui.getFields()[nextFieldIndex % Game.MAX_FIELDS]);
            } else {
                displayCar(player,gui.getFields()[nextFieldIndex]);
            }
        }
    }

    /**
     * Removes all images of the player's car on every field and displays the car on the newFieldIndex
     * @param player the player to move
     * @param newFieldIndex
     */
    public void displayInstantMovingPlayer(GUI_Player player, int newFieldIndex) {
        //removes all images of 'player's car from all fields
        for (GUI_Field f : gui.getFields()) {
            f.setCar(player, false);
        }
        displayCar(player,gui.getFields()[newFieldIndex]);
    }

    public void displayDie(GUI gui, int face) {
        gui.setDie(face);
    }

    /**
     * Shows who the winner is to the users and lets the user exit the game
     * @param winningPlayer the player to be displayed in the messages as the winner
     */
    public void displayEndGame(Player winningPlayer) {
        gui.showMessage(Main.getLanguage().getString("displayEndGameMessage1"));
        gui.showMessage(winningPlayer.getGUIPlayer().getName()
                + Main.getLanguage().getString("displayEndGameMessage2")
                + winningPlayer.getGUIPlayer().getBalance()
                + "!");
        gui.getUserButtonPressed(Main.getLanguage().getString("displayEndGameMessage3"), Main.getLanguage().getString("displayEndGameMessage4"));
        System.exit(0);
    }

    /**
     * Displays a border with the player's color around the field
     * if it is a field of type GUI_Ownable
     */
    public void displayBoughtField(Player player, GUI_Field field) {
        if(field instanceof GUI_Ownable o) {
            o.setBorder(player.getGUIPlayer().getPrimaryColor());
        }
    }

    public void changeFieldDescription(String description, int fieldIndex) {
        gui.getFields()[fieldIndex].setDescription(description);
    }
    public String getPlayerName(int index) {
        return playerNames.get(index);
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] names) {
        Collections.addAll(playerNames, names);
    }

    public int getUserCount() {
        return userCount;
    }
}
