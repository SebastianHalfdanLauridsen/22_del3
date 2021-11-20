package game;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;
import player.Player;
import player.PlayerManager;

import java.util.ArrayList;
import java.util.Collections;

//TODO javacock
/**
 * displaysshit
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

    private boolean isDuplicateName(String name) {
        for (int index = 0; index < playerNames.size() - 1; index++) {
            if (name.equalsIgnoreCase(playerNames.get(index))) {
                return true;
            }
        }
        return false;
    }

    /**
     * //TODO
     */
    public void displayStartUI() {
        gui.showMessage(Main.getLanguage().getString("welcomeMessage"));
        userCount = gui.getUserInteger(Main.getLanguage().getString("playerAskAmount") + ":", Game.MIN_PLAYER, Game.MAX_PLAYER);

        for (int currentUser = 0; currentUser < userCount; currentUser++) {
            receiveUserInput(currentUser);
        }
    }

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
        //remove all blank entries
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

    public void displayChanceCard() {
        // TODO
    }

    public void displayCar(GUI_Player player, GUI_Field field) {
        field.setCar(player, true);
    }

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

    public void displayEndGame(Player winningPlayer) {
        gui.showMessage("A winner has been found!");
        gui.showMessage(winningPlayer.getGUIPlayer().getName()
                + " has won the game with a total balance of "
                + winningPlayer.getGUIPlayer().getBalance()
                + "!");
        gui.getUserButtonPressed("Press the button to exit", "exit");
        System.exit(0);
    }

    /**
     * //TODO
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

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] names) {
        Collections.addAll(playerNames, names);
    }

    public int getUserCount() {
        return userCount;
    }
}
