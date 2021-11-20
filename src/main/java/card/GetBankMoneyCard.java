package card;
import player.Bank;
import player.Player;

//TODO implement card function
// - https://drive.google.com/file/d/1ymv0T5xWIvprTZkSO6DtWz9byFvZA9h3/view



public class GetBankMoneyCard extends AbstractCard{
    int paymentAmount;
    private final Bank bank;


    public GetBankMoneyCard(int paymentAmount, Bank bank) {
        this.paymentAmount = paymentAmount;
        this.bank = bank;
    }

    public void action(Player player){
        bank.changeBalance(player, paymentAmount);
    }
}
