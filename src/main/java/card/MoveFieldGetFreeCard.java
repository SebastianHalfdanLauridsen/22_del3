package card;

import game.Board;
import gui_main.GUI;
import player.Bank;
import player.Player;
import game.Game;

import java.util.Arrays;

/**
 * //TODO
 */
public class MoveFieldGetFreeCard extends AbstractCard {
    private final int[] field;
    private final GUI gui;
    private final Game game;
    private final Board board;
    private final Bank bank;
    private final String[] choice;

    public MoveFieldGetFreeCard(int field1, int field2, GUI gui, Game game, Board board, Bank bank) {
        this.field = new int[]{field1, field2};
        this.choice = new String[2];
        this.gui = gui;
        this.game = game;
        this.board = board;
        this.bank = bank;
    }

    public MoveFieldGetFreeCard(int field1, int field2, int field3, int field4, GUI gui, Game game, Board board, Bank bank) {
        this.field = new int[]{field1, field2, field3, field4};
        this.choice = new String[4];
        this.gui = gui;
        this.game = game;
        this.board = board;
        this.bank = bank;
    }

    public void action(Player player) {
        String chosenElement;
        //Choice array filler
        for (int i = 0; i < field.length; i++) {
            choice[i] = gui.getFields()[field[i]].getTitle();
        }

        chosenElement = gui.getUserSelection( //TODO language bundle
                "Choose which of the two free fields you wanna stay on!",
                choice
        );

        int index = Arrays.asList(choice).indexOf(chosenElement);
        int chosenField = field[index];

        game.movePlayer(player, chosenField);
        board.displayInstantMovingPlayer(player.getGUIPlayer(), chosenField);
        //the player receives the field, or pays rent to the owner via GUIStreetAction
        if (bank.isOwned(chosenField) == null) {
            bank.receiveField(chosenField, player);
        } else {
            game.GUIStreetAction(player, chosenField);
        }
    }
}
