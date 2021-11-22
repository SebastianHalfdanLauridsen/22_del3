package card;
import game.Board;
import game.Game;
import game.Main;
import gui_main.GUI;
import player.Player;

/**
 * Allows the player to move a given amount or draw a card
 */
public class MoveOrDrawCard extends AbstractCard{
    int moves;
    private final GUI gui;
    private final Board board;
    private final Game game;

    public MoveOrDrawCard(int move, GUI gui, Board board, Game game) {
        this.moves = move;
        this.gui = gui;
        this.board = board;
        this.game = game;
    }

    public void action(Player player) {
        String choice1 = Main.getLanguage().getString("moveOrDrawMessage1") + moves +  Main.getLanguage().getString("moveOrDrawMessage2");
        String choice2 = Main.getLanguage().getString("moveOrDrawMessage3");
        String chosenElement = gui.getUserSelection(
                Main.getLanguage().getString("moveOrDrawMessage1") + moves + Main.getLanguage().getString("moveOrDrawMessage4"),
                choice1, choice2
        );

        if(chosenElement.equals(choice1)){
            int playerPosition = player.getFieldPosition();
            //% in case moves + playerPosition is more than MAX_FIELDS
            int newPlayerPosition = (moves + playerPosition) % Game.MAX_FIELDS;
            board.displayMovingPlayer(player.getGUIPlayer(), moves, playerPosition);
            game.movePlayer(player, newPlayerPosition);
            game.fieldAction(player, newPlayerPosition);

        } else {
            game.GUIChanceAction(player);
        }
    }
}
