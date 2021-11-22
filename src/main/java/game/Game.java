package game;

import card.*;
import die.Hand;
import gui_fields.*;
import gui_main.GUI;

import player.Bank;
import player.Cars;
import player.Player;
import player.PlayerManager;

import java.awt.Color;
import java.util.logging.Level;

/**
 * Runs Monopoly Junior and all of its main functions
 */
public class Game {
    private final GUI gui;
    private final Board board;
    private final PlayerManager playerManager;
    private final Bank bank;
    private final Fields fields;
    private final Cars cars;
    private final Deck deck;
    private Hand hand;

    public static final int MAX_FIELDS = 24;
    public static final int START_FIELD = 0;
    public static final int CROSS_START_MONEY = 2;

    public static final int MIN_PLAYER = 2;
    public static final int MAX_PLAYER = 4;

    public Game() {
        this.fields = new Fields();
        this.cars = new Cars();
        this.playerManager = new PlayerManager();
        this.gui = new GUI(fields.getFields(), new Color(229, 239, 222));
        this.board = new Board(gui);
        this.bank = new Bank(playerManager, board, fields);
        this.deck = new Deck();
    }

    /**
     * Creates all the cards and shuffles them
     */
    private void cardSetup() {
        new Cards(deck, board, bank, playerManager, gui, this);
        deck.shuffleCards();
    }

    /**
     * Prompts players to enter their names and adds them to PlayerManager
     *   and displays their cars on the start field
     */
    private void playerSetup() {
        board.displayStartUI();
        //create players
        for(int i = 0; i < board.getUserCount(); i++) {
            String nextPlayerName = board.getPlayerName(i);
            playerManager.createPlayer(nextPlayerName,35, cars.getCars()[i]);
        }
        //display cars
        for(int i = 0; i < playerManager.getPlayerCount(); i++) {
            GUI_Player playerObject = playerManager.getPlayers(i).getGUIPlayer();
            GUI_Field startFieldObject = gui.getFields()[START_FIELD];
            board.displayCar(playerObject, startFieldObject);
        }
    }

    /**
     * Begins the game, runs playerSetup(), displays all the players on the scoreboard, runs cardSetup(),
     *   creates a new hand with a normal die
     *   and runs the rounds until a player has won and displays the end game info after
     */
    public void runGame() {

        playerSetup();
        board.displayScoreboard(playerManager);
        cardSetup();
        sleep();
        hand = new Hand(1, 6);

        while( !(bank.playerHasWon()) ) {
            playRound();
        }
        board.displayEndGame(bank.getWinningPlayer());
    }

    /**
     * Runs each player's turn once every round if they are not in jail
     */
    private void playRound() {
        for (int playerIndex = 0; playerIndex < playerManager.getPlayerCount(); playerIndex++) {
            Player currentPlayer = playerManager.getPlayers(playerIndex);
            if (currentPlayer.isInJail() && !currentPlayer.hasJailCard()) {
                gui.showMessage(currentPlayer.getGUIPlayer().getName() + Main.getLanguage().getString("isInJailMessage"));
                currentPlayer.setInJail(false);
                continue;
            } else if (currentPlayer.isInJail() && currentPlayer.hasJailCard()){
                currentPlayer.setJailCard(false);
                currentPlayer.setInJail(false);
            }
            playTurn(currentPlayer);
        }
    }

    /**
     * Lets the player roll the die, displays its value in the gui,
     *   moves the player according to the die value and begins the fieldAction
     * @param currentPlayer The player whose turn it is
     */
    private void playTurn(Player currentPlayer) {
        int currentFieldIndex = currentPlayer.getFieldPosition();

        sleep();
        gui.showMessage(currentPlayer.getGUIPlayer().getName() + Main.getLanguage().getString("rollMessage"));
        int diceSum = hand.roll();

        //select field, % in case currentFieldIndex + diceSum is more than MAX_FIELDS
        int nextFieldIndex = (currentFieldIndex + diceSum) % Game.MAX_FIELDS;

        board.displayDie(gui, diceSum);

        board.displayMovingPlayer(currentPlayer.getGUIPlayer(), diceSum, currentFieldIndex);
        movePlayer(currentPlayer, nextFieldIndex);
        fieldAction(currentPlayer, nextFieldIndex);
    }

    /**
     * The player buys the field if it is not owned, pays rent to the owner otherwise
     * @param player the player who buys the field or pays rent
     * @param field the field to be bought or rented
     */
    public void GUIStreetAction(Player player, int field) {
        Player fieldOwner = bank.isOwned(field);
        if (fieldOwner == null) {
            bank.buyField(field, player);
        } else if (fieldOwner != player) {
            bank.payFieldRent(player, fieldOwner, (GUI_Street) fields.getFields()[field]);
        }
    }

    /**
     * The player draws a chance card, the description is displayed
     *   and the action of the chance card is called
     * @param player the player who draws a chance card and is affected by its actions
     */
    public void GUIChanceAction(Player player) {
        System.out.println("GUI_Chance!");

        AbstractCard card = deck.drawCard();
        String description = "";
        try {
            description = card.getDescription();
        } catch (NullPointerException e){
            Main.getLogger().log(Level.INFO, "Card that was drawn was null");
            e.getStackTrace();
            System.exit(-1);
        }
        System.out.println("Player " + player.getGUIPlayer().getName() + " drew chancecard" + "\n\""+ description + "\"");

        gui.displayChanceCard(description);
        Game.sleep();
        card.action(player);
    }

    /**
     * Moves the player to jail and sets their in jail status to true
     * @param player the player to be put in jail
     */
    private void GUIGoToJailAction(Player player) {
        player.setInJail(true);
        int jailField = player.getFieldPosition() - MAX_FIELDS/2;
        movePlayer(player, jailField);
        board.displayInstantMovingPlayer(player.getGUIPlayer(), jailField);
    }

    /**
     * Determines which field the player is on and performs the required action
     * @param player the player on the field
     * @param fieldIndex the field
     */
    public void fieldAction(Player player, int fieldIndex) {
        String fieldType = fields.getFields()[fieldIndex].getClass().getSimpleName();
        switch (fieldType) {
            case "GUI_Street" -> {
                System.out.println("GUI_Street!");
                GUIStreetAction(player, fieldIndex);
            }
            case "GUI_Chance" -> GUIChanceAction(player);
            case "GUI_Tax" -> {
                System.out.println(player.getGUIPlayer().getName() + " went to jail");
                Game.sleep();
                GUIGoToJailAction(player);
            }
        }
    }

    /**
     * Moves the player to a field in the backend and pays the player if they pass start
     * @param player the player to move field or get payed
     * @param fieldIndex the field
     */
    public void movePlayer(Player player, int fieldIndex) {
        if (fieldIndex >= MAX_FIELDS || fieldIndex == 0) {
            bank.changeBalance(player,CROSS_START_MONEY);
            player.setFieldPosition(fieldIndex % MAX_FIELDS);
        } else {
            player.setFieldPosition(fieldIndex);
        }
    }

    public GUI getGui() {
        return gui;
    }
    public Board getBoard() {
        return board;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public Bank getBank() {
        return bank;
    }
    public Fields getFields() {
        return fields;
    }
    public Cars getCars() {
        return cars;
    }
    public Deck getDeck() {
        return deck;
    }

    //thanks to https://github.com/diplomit-dtu/
    public static void sleep(){
        sleep(600);
    }
    public static void sleep(long ms){
        long time0 = System.currentTimeMillis();
        long time1 = System.currentTimeMillis();

        while((time1 - time0) < ms) {
            time1 = System.currentTimeMillis();
        }
    }
}


