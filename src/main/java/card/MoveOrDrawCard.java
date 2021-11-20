package card;
import game.Board;
import game.Game;
import gui_main.GUI;
import player.Player;


//TODO implement card function
// - https://drive.google.com/file/d/1ymv0T5xWIvprTZkSO6DtWz9byFvZA9h3/view
// - https://drive.google.com/file/d/15oSUaFK5NtryM21fVlUwhGYGYiCACye7/view



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
            int newPlayerPosition = moves + playerPosition;
            board.displayMovingPlayer(player.getGUIPlayer(), moves, playerPosition);
            game.movePlayer(player, newPlayerPosition);
            int realNewPlayerPosition = game.getRealNewIndex(playerPosition, moves);
            game.fieldAction(player, realNewPlayerPosition);
        } else {
            game.GUIChanceAction(player);
        }


    }


}
