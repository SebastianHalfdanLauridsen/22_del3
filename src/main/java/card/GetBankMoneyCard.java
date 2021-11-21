package card;
import player.Bank;
import player.Player;

/**
 * A chance card which pays the player a given amount of money from the bank
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
