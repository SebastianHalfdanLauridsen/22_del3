package card;
import player.Bank;
import player.Player;

/**
 * Makes the player pay a given amount to the bank.
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
