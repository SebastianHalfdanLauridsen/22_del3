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

        //playerSetup();
        board.displayScoreboard(gui, playerManager);
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


