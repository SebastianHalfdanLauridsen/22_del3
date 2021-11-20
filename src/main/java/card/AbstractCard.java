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

    public abstract void action(Player player);

}