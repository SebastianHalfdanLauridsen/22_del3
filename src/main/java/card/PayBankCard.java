package card;
import player.Bank;
import player.Player;


//TODO implement card function
// - https://drive.google.com/file/d/1ymv0T5xWIvprTZkSO6DtWz9byFvZA9h3/view



public class PayBankCard extends AbstractCard{
    int payAmount;
    private Bank bank;

    public PayBankCard(int payAmount, Bank bank) {
        this.payAmount = payAmount;
        this.bank = bank;

    }

    public void action(Player player) {
        bank.changeBalance(player, payAmount);
    }
}
