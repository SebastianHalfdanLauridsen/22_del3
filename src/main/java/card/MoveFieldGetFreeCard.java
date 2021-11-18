package card;
import game.Board;
import player.Bank;
import gui_main.GUI;
import player.Player;
import game.Game;
import game.Board;

import gui_codebehind.GUI_BoardController;
import gui_fields.*;



//TODO implement card function
// - https://drive.google.com/file/d/1ymv0T5xWIvprTZkSO6DtWz9byFvZA9h3/view
// - https://drive.google.com/file/d/15oSUaFK5NtryM21fVlUwhGYGYiCACye7/view




public class MoveFieldGetFreeCard extends AbstractCard {

    //Declares the two fields with the same color
    int FieldColorPosition, FieldColorPosition2;
    private GUI gui;
    private Game game;
    private Board board;

    public MoveFieldGetFreeCard(int FieldColorPosition, int FieldColorPosition2, Bank bank,GUI gui, Game game, Board board) {
        this.FieldColorPosition = FieldColorPosition;
        this.FieldColorPosition2 = FieldColorPosition2;
        this.gui = gui;
        this.game = game;
        this.board = board;

    }

    //action() moves the player to the chosen field which depends on the given action card
    public void action(Player player) {
        String chosenElement = gui.getUserSelection(
                "Choose which of the two free fields you wanna stay on!",
                "Color field 1", "Color field 2"
        );

        int moves;

        //Checks which one the player chose
        if (chosenElement.equals("Color field 1")) {
            game.movePlayer(player, FieldColorPosition);
            moves = FieldColorPosition - player.getFieldPosition();
        } else {
            game.movePlayer(player, FieldColorPosition2);
            moves = FieldColorPosition2 - player.getFieldPosition();
        }

        //Checks whether the colored field is owned or not with GUIStreetAction()
        board.displayMovingPlayer(player.getGUIPlayer(), moves, player.getFieldPosition());
        game.GUIStreetAction(player, FieldColorPosition);

    }






}
