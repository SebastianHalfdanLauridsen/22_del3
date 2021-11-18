package card;
import gui_main.GUI;
import player.Player;

//TODO implement card function
// - https://drive.google.com/file/d/1ymv0T5xWIvprTZkSO6DtWz9byFvZA9h3/view
// - https://drive.google.com/file/d/15oSUaFK5NtryM21fVlUwhGYGYiCACye7/view



public class MoveMaxFiveCard extends AbstractCard{
    private GUI gui;

    public MoveMaxFiveCard(GUI gui) {
        this.gui = gui;

    }

    //Lets the player move 1 to 5 fields depending on the number input
    public void action(Player player){
        String chosenElement = gui.getUserSelection(
                "Choose how many field you want to move!",
                "1", "2", "3", "4", "5"
        );

        int numberInput = Integer.parseInt(chosenElement);
        player.setFieldPosition(player.getFieldPosition() + numberInput);

    }
}
