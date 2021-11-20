package card;
import player.Bank;
import player.Player;
import player.PlayerManager;

/**
 * //TODO
 */
public class GetPlayersMoneyCard extends AbstractCard {
    int payAmount;

    private final Bank bank;
    private final PlayerManager playerManager;

    public GetPlayersMoneyCard(int payAmount, Bank bank, PlayerManager playerManager) {
        this.payAmount = payAmount;
        this.bank = bank;
        this.playerManager = playerManager;
    }

    public void action(Player player) {
        int playerCount = playerManager.getPlayerCount();

        for (int playerIndex = 0; playerIndex < playerCount; playerIndex++) {
            Player thisPlayer = playerManager.getPlayers(playerIndex);
            if (thisPlayer == player) {
                continue;
            }
            bank.changeBalance(thisPlayer, -payAmount);
        }

        int receiveAmount = payAmount * (playerCount - 1);
        bank.changeBalance(player, receiveAmount);
    }
}
