package game;

import card.*;
import die.Hand;
import gui_fields.*;
import gui_main.GUI;

import player.Bank;
import player.Player;
import player.PlayerManager;

import java.awt.Color;
import java.util.logging.Level;

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

        playerSetup();
        board.displayScoreboard(playerManager);
    }


    private void cardSetup() {
        new Cards(deck, board, bank, playerManager, gui, this);
        deck.shuffleCards();
    }

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

    public void runGame(){
        cardSetup();
        sleep();
        hand = new Hand(1, 6);

        int currentFieldPos = 0;
        GUI_Field currentField = gui.getFields()[currentFieldPos];
        GUI_Field newField = currentField;

        while( !(bank.playerHasWon()) ) {
            playRound();
        }
        board.displayEndGame(bank.getWinningPlayer());
    }

    /**
     * //TODO
     */
    private void playRound() {
        for (int playerIndex = 0; playerIndex < playerManager.getPlayerCount(); playerIndex++) {
            Player currentPlayer = playerManager.getPlayers(playerIndex);
            if (currentPlayer.isInJail() && !currentPlayer.hasJailCard()) {
                gui.showMessage("haha u in jæl");
                currentPlayer.setInJail(false);
                continue;
            }
            currentPlayer.setJailCard(false);

            playTurn(currentPlayer);
        }
    }

    private void playTurn(Player currentPlayer) {
        int currentFieldIndex = currentPlayer.getFieldPosition();

        sleep();
        gui.showMessage(currentPlayer.getGUIPlayer().getName() + Main.getLanguage().getString("rollMessage"));
        hand.roll();

        int dieFace = hand.getDice().get(0).getFace();
        int diceSum = hand.sum();

        //select field
        int newFieldIndex = currentFieldIndex + diceSum;//getNewFieldIndex(currentFieldIndex, diceSum);

        board.displayDie(gui, dieFace);

        board.displayMovingPlayer(currentPlayer.getGUIPlayer(), diceSum, currentFieldIndex);
        movePlayer(currentPlayer, newFieldIndex);
        newFieldIndex = getRealNewIndex(currentFieldIndex, diceSum);

        fieldAction(currentPlayer, newFieldIndex);
    }

    public void GUIStreetAction(Player player, int field) {
        Player fieldOwner = bank.isOwned(field);
        if (fieldOwner == null) {
            bank.buyField(field, player);
        } else if (fieldOwner != player) {
            bank.payFieldRent(player, fieldOwner, (GUI_Street) fields.getFields()[field]);
        }
    }

    public void GUIChanceAction(Player player) {
        System.out.println("GUI_Chance!");

        AbstractCard card = deck.drawCard();
        String description = "";
        try {
            description = card.getDescription();
        } catch (NullPointerException e){
            Main.getLogr().log(Level.INFO, "Card that was drawn was null");
            e.getStackTrace();
            System.exit(-1);
        }
        System.out.println("Player " + player.getGUIPlayer().getName() + " drew chancecard" + "\n\""+ description + "\"");

        gui.displayChanceCard(description);
        Game.sleep();
        card.action(player);
    }

    private void GUIGoToJailAction(Player player) {
        player.setInJail(true);
        int jailField = player.getFieldPosition() - MAX_FIELDS/2;
        movePlayer(player, jailField);
        board.displayInstantMovingPlayer(player.getGUIPlayer(), jailField);
    }

    public void fieldAction(Player player, int field) {
        String fieldType = fields.getFields()[field].getClass().getSimpleName();
        switch (fieldType) {
            case "GUI_Street" -> {
                System.out.println("GUI_Street!");
                GUIStreetAction(player, field);
            }
            case "GUI_Chance" -> {
                GUIChanceAction(player);
            }
            case "GUI_Jail" -> {
                System.out.println("haha JÆEL");
            }
            case "GUI_Tax" -> {
                System.out.println(player.getGUIPlayer().getName() + " went to jail");
                Game.sleep();
                GUIGoToJailAction(player);
            }
            case "GUI_Start" -> {
                System.out.println("start field!");
            }
            default -> System.out.println("what");
        }
    }

    public void movePlayer(Player player, int field) {
        if (field >= MAX_FIELDS) {
            bank.changeBalance(player,CROSS_START_MONEY);
            player.setFieldPosition(field - MAX_FIELDS);
        } else {
            player.setFieldPosition(field);
        }
    }

    private int getRealNewIndex(int currentFieldIndex, int diceSum) {
        if ((currentFieldIndex + diceSum) >= MAX_FIELDS) {
            return currentFieldIndex + diceSum - MAX_FIELDS;
        } else {
            return currentFieldIndex + diceSum;
        }
    }

    public static void sleep(){
        sleep(600);
    }
    public static void sleep(long n){
        long time0 = System.currentTimeMillis();
        long time1 = System.currentTimeMillis();

        while((time1 - time0) < n) {
            time1 = System.currentTimeMillis();
        }
    }
}


