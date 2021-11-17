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
    private static ResourceBundle language;

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

        Locale locale = new Locale("no");
        language = ResourceBundle.getBundle("language", locale);
    }

    public static Logger getLogr() {
        return logr;
    }

    public static ResourceBundle getLanguage() {
        return language;
    }

}
