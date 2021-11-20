package card;
import game.Board;
import game.Game;
import gui_main.GUI;
import player.Player;

/**
 * //TODO
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
        //TODO language resource bundle
        String choice1 = "Move " + moves +  " field(s)";
        String choice2 = "Draw a card";
        String chosenElement = gui.getUserSelection(
                "Move" + moves + " field(s) or draw a card!",
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
