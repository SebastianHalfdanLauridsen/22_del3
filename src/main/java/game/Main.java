package game;

import card.Deck;
import card.MoveCard;
import card.PayBankCard;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.*;

public class Main {
    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static void setupLogger() {
        LogManager.getLogManager().reset();

        logr.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        //active logging level in console
        ch.setLevel(Level.ALL);
        logr.addHandler(ch);
        try {
            FileHandler fh = new FileHandler("CDIOLogger.log");
            fh.setLevel(Level.ALL);
            logr.addHandler(fh);
        } catch(IOException e) {
            logr.log(Level.SEVERE, "File logger not working", e);
        }
    }

    public static void main(String[] args) {
        Main.setupLogger();

        logr.log(Level.SEVERE, "log1");
        logr.log(Level.INFO, "log2");

        Deck deck = new Deck();
        deck.addCard("mover", "I like to move it", new MoveCard());
        deck.addCard("PayBank", "I paybank now :)", new PayBankCard());
        System.out.println(deck.getCards(0).toString());
        System.out.println(deck.getCards(1).toString());



        Locale locale = new Locale("dk");
        ResourceBundle bundle = ResourceBundle.getBundle("test", locale);
        System.out.println(bundle.getString("label1"));

    }

}
