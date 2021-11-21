package card;

import java.util.Collections;
import java.util.LinkedList;

/**
 * //TODO
 */
public class Deck {
    int cardCount;
    LinkedList<AbstractCard> cards;

    public Deck() {
        cards = new LinkedList<>();
        cardCount = 0;
    }

    /**
     *
     * @param name
     * @param description
     * @param card
     */
    public void addCard(String name, String description, AbstractCard card) {
        card.setName(name).setDescription(description);
        cards.add(card);
        cardCount++;
    }

    /**
     *
     * @return
     */
    public AbstractCard drawCard() {
        AbstractCard card = cards.poll();
        cards.addLast(card);
        return card;
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public LinkedList<AbstractCard> getCards() {
        return cards;
    }
}