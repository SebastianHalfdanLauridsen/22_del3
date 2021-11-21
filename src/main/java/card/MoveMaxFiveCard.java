package card;
import game.Board;
import game.Game;
import game.Main;
import gui_main.GUI;
import player.Player;

/**
 * Allows the player to move between 1 and 5 via a dropdown list and performs the action of the field
 */
public class MoveMaxFiveCard extends AbstractCard{
    private final GUI gui;
    private final Board board;
    private final Game game;

    public MoveMaxFiveCard(GUI gui, Board board, Game game) {
        this.gui = gui;
        this.board = board;
        this.game = game;
    }

    //Lets the player move 1 to 5 fields depending on the number input
    public void action(Player player) {
        String chosenElement = gui.getUserSelection(
                Main.getLanguage().getString("moveMaxFiveMessage"),
                "1", "2", "3", "4", "5"
        );

        int numberInput = Integer.parseInt(chosenElement);
        int fieldIndex = numberInput + player.getFieldPosition();

        board.displayMovingPlayer(player.getGUIPlayer(), numberInput, player.getFieldPosition());
        game.movePlayer(player, fieldIndex);
        game.fieldAction(player, fieldIndex);
    }
}
