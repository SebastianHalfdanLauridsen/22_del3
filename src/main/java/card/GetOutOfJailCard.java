package card;
import player.Player;

/**
 * //TODO
 */
public class GetOutOfJailCard extends AbstractCard{

    public void action(Player player) {
        player.setJailCard(true);
    }
}

