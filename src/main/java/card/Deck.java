package card;

import java.util.Collections;
import java.util.LinkedList;

/**
 * The deck holds all the cards in the game and can shuffle and draw them
 */
public class Deck {
    int cardCount;
    LinkedList<AbstractCard> cards;

    public Deck() {
        cards = new LinkedList<>();
        cardCount = 0;
    }

    /**
     * Adds an AbstractCard with a name and description to the cards LinkedList and adds 1 to the card counter
     * @param name the name of the card
     * @param description the description of the card, usually a description of its action
     * @param card the type of card, a subclass of AbstractCard
     */
    public void addCard(String name, String description, AbstractCard card) {
        card.setName(name).setDescription(description);
        cards.add(card);
        cardCount++;
    }

    /**
     * Draws the topmost card in the deck and adds it to the bottom of the deck.
     * @return the card that is drawn
     */
    public AbstractCard drawCard() {
        AbstractCard card = cards.poll();
        cards.addLast(card);
        return card;
    }

    /**
     * Shuffles the cards like a real deck of cards, via java.util.Collections
     */
    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public LinkedList<AbstractCard> getCards() {
        return cards;
    }
}