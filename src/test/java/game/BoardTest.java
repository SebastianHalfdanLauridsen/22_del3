package game;

import java.util.ArrayList;
import java.util.stream.Collectors;

import die.Die;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_main.GUI;

import org.junit.Assert;
import org.junit.Test;
import player.Player;
import player.PlayerManager;

public class BoardTest {


    @Test
    public void displayStartUITest() {
        Main.setLanguage();

        Fields fields = new Fields();
        GUI gui = new GUI(fields.getFields());
        Board board = new Board(gui);

        board.displayStartUI();

        ArrayList<String> names = board.getPlayerNames();
        //isDuplicate() test
        //thanks to https://stackoverflow.com/a/50540360 (BIG BRAIN)
        //the size of the original list must be the same as the size of the list with only distinct values
        Assert.assertEquals(names.size(), names.stream().distinct().count());

        //isBlank() test
        for (String name : names) {
            Assert.assertFalse(name.isBlank());
        }
    }

    @Test
    public void displayScoreboardTest() {
        Main.setLanguage();

        Fields fields = new Fields();
        GUI gui = new GUI(fields.getFields());
        Board board = new Board(gui);
        PlayerManager playerManager = new PlayerManager();

        playerManager.createPlayer("Kevse", 10, new GUI_Car());
        playerManager.createPlayer("Sbebastian", 15, new GUI_Car());
        playerManager.createPlayer("Chan", 20, new GUI_Car());

        Game.sleep();
        board.displayScoreboard(playerManager);
        Game.sleep(10000);
    }

    @Test
    public void displayCarTest() {
        Main.setLanguage();

        Fields fields = new Fields();
        GUI gui = new GUI(fields.getFields());
        Board board = new Board(gui);
        PlayerManager playerManager = new PlayerManager();

        playerManager.createPlayer("Kevse", 10, new GUI_Car());
        Player player = playerManager.getPlayers(0);

        GUI_Field[] field = gui.getFields();
        for(GUI_Field f : field) {
            Game.sleep(75);
            board.displayCar(player.getGUIPlayer(), f);
        }
        Game.sleep(5000);
    }

    @Test
    public void displayMovingPlayerTest() {
        Main.setLanguage();

        GUI gui = new GUI(new Fields().getFields());
        Board board = new Board(gui);
        PlayerManager playerManager = new PlayerManager();

        playerManager.createPlayer("Kevse", 10, new GUI_Car());
        Player player = playerManager.getPlayers(0);

        board.displayMovingPlayer(player.getGUIPlayer(), 12, 0);
        Game.sleep();
        board.displayMovingPlayer(player.getGUIPlayer(), 15, 12);
        Game.sleep(5000);
    }

    @Test
    public void displayInstantMovingPlayerTest() {
        Main.setLanguage();


        GUI gui = new GUI(new Fields().getFields());
        Board board = new Board(gui);
        PlayerManager playerManager = new PlayerManager();

        playerManager.createPlayer("Kevse", 10, new GUI_Car());
        Player player = playerManager.getPlayers(0);

        board.displayInstantMovingPlayer(player.getGUIPlayer(), 10);
        Game.sleep();
        board.displayInstantMovingPlayer(player.getGUIPlayer(),20);
        Game.sleep();
        board.displayInstantMovingPlayer(player.getGUIPlayer(),5);
        Game.sleep();
        board.displayInstantMovingPlayer(player.getGUIPlayer(),15);
        Game.sleep(5000);
    }

    @Test
    public void displayDieTest() {
        Main.setLanguage();

        GUI gui = new GUI(new Fields().getFields());
        Board board = new Board(gui);

        for (int i = 0; i < 100; i++) {
            Game.sleep(75);
            board.displayDie(gui, new Die().getFace());
        }
        Game.sleep(5000);
    }


}
