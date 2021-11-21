package card;
import player.Player;

/**
 * Gives the player a get out of jail card to use instantly as they go to jail to get out
 */
public class GetOutOfJailCard extends AbstractCard{

    public void action(Player player) {
        player.setJailCard(true);
    }
}

