package card;

import player.Player;

/**
 * //TODO
 */
public abstract class AbstractCard {
    private String name;
    private String description;

    public AbstractCard setName(String name) {
        this.name = name;
        return this;
    }

    public AbstractCard setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * The mutual action which all subclasses must include. This is the action of the subclass card.
     * @param player the player whomst'd've'lu'yaint'nt'ed'ies's'y'es'nt't're'ing'able'tic'ive'al'nt'ne'm'll'ble'al'ny draws the card and is mainly acted upon.
     */
    public abstract void action(Player player);
}