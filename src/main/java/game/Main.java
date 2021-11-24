package game;

import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static ResourceBundle language;

    public static boolean runGame = false;

    public static void main(String[] args) throws InterruptedException {
        new LanguagePane();

        Main.setupLogger();

        while (!Game.gameRunning) {
            if(runGame) {
                Game game = new Game();
                game.runGame();
            }
            Game.sleep();
        }
    }

    /**
     * Allows the user to select a language through the terminal
     */
    private static void selectLanguage() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please select a language");
            System.out.println("Default if not found");
            System.out.println("{English, Norsk, Dansk}");
            String lang = scanner.nextLine();
            lang = lang.toLowerCase().substring(0, 2);
            setLanguage(lang);
        } catch (NoSuchElementException e) {
            logr.log(Level.SEVERE, "The scanner could not read the input");
            System.exit(-1);
        }
    }

    /**
     * Uses java.util.logging.* to setup a logger
     */
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

    public static Logger getLogger() {
        return logr;
    }
    public static void setLanguage(String lang){
        Locale locale = new Locale(lang);
        language = ResourceBundle.getBundle("language", locale);
    }
    public static void setLanguage() {
        Locale locale = Locale.getDefault();
        language = ResourceBundle.getBundle("language", locale);
    }
    public static ResourceBundle getLanguage() {
        return language;
    }

}
