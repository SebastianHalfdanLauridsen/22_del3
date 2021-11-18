package card;
import player.Player;

//TODO implement card function
// - https://drive.google.com/file/d/1ymv0T5xWIvprTZkSO6DtWz9byFvZA9h3/view
// - https://drive.google.com/file/d/15oSUaFK5NtryM21fVlUwhGYGYiCACye7/view



public class MoveCard extends AbstractCard{
    int Strandpromenaden;

    public MoveCard(int Strandpromenaden) {
        this.Strandpromenaden = Strandpromenaden;

    }

    public void action(Player player){
        player.setFieldPosition(Strandpromenaden);
    }

}
