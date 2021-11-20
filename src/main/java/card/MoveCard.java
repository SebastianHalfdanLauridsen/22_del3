package card;
import game.Board;
import game.Game;
import gui_main.GUI;
import player.Player;

public class MoveCard extends AbstractCard{
    private final int fieldIndex;
    private final Board board;
    private final Game game;


    public MoveCard(int fieldIndex, Board board, Game game) {
        this.fieldIndex = fieldIndex;
        this.board = board;
        this.game = game;
    }


    public void action(Player player) {
        Game.sleep();
        board.displayInstantMovingPlayer(player.getGUIPlayer(), fieldIndex);
        game.movePlayer(player, fieldIndex);
        game.fieldAction(player, fieldIndex);
    }

}
