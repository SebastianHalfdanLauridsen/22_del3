package card;
import gui_main.GUI;
import player.Player;


//TODO implement card function
// - https://drive.google.com/file/d/1ymv0T5xWIvprTZkSO6DtWz9byFvZA9h3/view
// - https://drive.google.com/file/d/15oSUaFK5NtryM21fVlUwhGYGYiCACye7/view



public class MoveOrDrawCard extends AbstractCard{
    int move;
    private Deck deck;
    private GUI gui;


    public MoveOrDrawCard(int move, Deck deck, GUI gui) {
        this.move = move;
        this.deck = deck;
        this.gui = gui;

    }

    public void action(Player player){
        String chosenElement = gui.getUserSelection(
                "Move 1 field or draw a card!",
                "Move 1 field", "Draw a card"
        );

        if(chosenElement.equals("Move 1 field")){
            player.setFieldPosition(move);
        } else if (chosenElement.equals("Draw a card")){
            deck.drawCard();
        }


    }


}
