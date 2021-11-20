package card;
import player.Bank;
import player.Player;

/**
 * //TODO
 */
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
