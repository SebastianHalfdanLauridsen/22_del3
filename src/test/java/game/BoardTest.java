package game;

import java.util.List;

import die.Die;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_main.GUI;

import org.junit.Assert;
import org.junit.Test;
import player.Player;
import player.PlayerManager;

public class BoardTest {
    Fields fields;
    GUI gui;
    Board board;
    PlayerManager playerManager;

    private void setup(){
        Main.setLanguage();
        fields = new Fields();
        gui = new GUI(fields.getFields());
        board = new Board(gui);
        playerManager = new PlayerManager();

    }

    @Test
    public void displayStartUITest() {
        setup();

        board.displayStartUI();

        List<String> names = board.getPlayerNames();
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
        setup();

        playerManager.createPlayer("Kevse", 10, new GUI_Car());
        playerManager.createPlayer("Sbebastian", 15, new GUI_Car());
        playerManager.createPlayer("Chan", 20, new GUI_Car());

        Game.sleep();
        board.displayScoreboard(playerManager);
        Game.sleep(10000);
    }

    @Test
    public void displayCarTest() {
        setup();

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
        setup();

        playerManager.createPlayer("Kevse", 10, new GUI_Car());
        Player player = playerManager.getPlayers(0);

        board.displayMovingPlayer(player.getGUIPlayer(), 12, 0);
        Game.sleep();
        board.displayMovingPlayer(player.getGUIPlayer(), 15, 12);
        Game.sleep(5000);
    }

    @Test
    public void displayInstantMovingPlayerTest() {
        setup();

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
        setup();

        for (int i = 0; i < 100; i++) {
            Game.sleep(75);
            board.displayDie(gui, new Die().getFace());
        }
        Game.sleep(5000);
    }

    @Test
    public void displayEndGameTest() {
        setup();

        playerManager.createPlayer("name",10,new GUI_Car());
        board.displayEndGame(playerManager.getPlayers(0));
    }

    @Test
    public void displayBoughtFieldTest() {
        setup();

        playerManager.createPlayer("Kevse", 10, new GUI_Car());
        Player player = playerManager.getPlayers(0);

        Game.sleep(1000);
        GUI_Field[] field = gui.getFields();
        for(GUI_Field f : field) {
            Game.sleep(75);
            board.displayBoughtField(player,f);
        }

        Game.sleep(5000);
    }
}
