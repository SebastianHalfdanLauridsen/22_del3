package card;
import player.Bank;
import player.Player;

/**
 * //TODO
 */
public class PayBankCard extends AbstractCard{
    int payAmount;
    private final Bank bank;

    public PayBankCard(int payAmount, Bank bank) {
        this.payAmount = payAmount;
        this.bank = bank;
    }

    public void action(Player player) {
        bank.changeBalance(player, -payAmount);
    }
}
